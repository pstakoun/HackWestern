package com.stakoun.hackwestern;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Game
{
    public Game()
    {
        SocketTask st = new SocketTask();
        st.setGame(this);
        st.execute();
    }

    public String getLocation()
    {
        return "";
    }

}

class SocketTask extends AsyncTask<Void, Void, Void>
{
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Game game;

    @Override
    public Void doInBackground(Void... voids) {
        try {
            socket = new Socket("52.24.241.238", 8080);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println("connect");
            String line;
            while ((line = in.readLine()) != "exit") {
                out.println("location " + game.getLocation());
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
