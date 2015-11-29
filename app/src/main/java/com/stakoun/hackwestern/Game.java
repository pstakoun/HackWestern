package com.stakoun.hackwestern;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
public class Game
{
    private Activity activity;
    private SocketTask st;
    private LocationManager lm;
    private int health;
    private double compassAngle;

    public Game(Activity activity)
    {
        this.activity = activity;
        health = 100;
        st = new SocketTask();
        st.setGame(this);
        lm = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        st.execute();
    }

    public Location getLocation()
    {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        Log.d("debug", "Loc check failed");
        return null;
    }

    public int getHealth() {
        return health;
    }

    public void hurt() {
        health -= 1;
        if (health < 0)
            health = 0;
        ((TextView) activity.findViewById(R.id.health)).setText(health + "%");
    }

    public void setCompassAngle(double d) {
        compassAngle = d;
    }

}

class SocketTask extends AsyncTask<Void, Void, Void>
{
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Game game;
    public static float diffAngle = 0f;

    @Override
    public Void doInBackground(Void... voids)
    {
        try {
            socket = new Socket("52.24.241.238", 8080);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            boolean cooldown = false;
            while ((line = in.readLine()) != "exit") {
                if (line == null)
                    continue;
                else if (line.equals("ping"))
                    out.println("pong");
                else if (line.equals("longitude"))
                    out.println(game.getLocation().getLongitude());
                else if (line.equals("latitude"))
                    out.println(game.getLocation().getLatitude());
                else if (line.equals("health"))
                    out.println(game.getHealth());
                else if (line.equals("hurt"))
                    game.hurt();
                else if (line.startsWith("angle "))
                    diffAngle = Float.parseFloat(line.split(" ")[1]);
                else
                    out.println("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setGame(Game game) {
        this.game = game;
    }

}
