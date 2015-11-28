package com.stakoun.hackwestern;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Game
{
    private String id;

    public Game(String id)
    {
        this.id = id;
        (new SocketTask()).execute();
    }

}

class SocketTask extends AsyncTask<Void, Void, Void>
{
    private Socket socket;
    PrintWriter out;
    BufferedReader in;

    @Override
    public Void doInBackground(Void... voids) {
        try {
            socket = new Socket("hackwestern-kshen3778.c9.io", 8080);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = in.readLine()) != "exit") {
                if (line != null)
                    Log.d("IN", line);
                out.println("test");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
