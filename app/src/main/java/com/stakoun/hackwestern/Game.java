package com.stakoun.hackwestern;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Game
{
    private String id;
    private Socket socket;
    PrintWriter out;
    BufferedReader in;

    public Game(String id)
    {
        this.id = id;
        try {
            socket = new Socket("hackwestern-kshen3778.c9users.io", 8080);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            listen();
        } catch (Exception e) {
            Log.e("Socket", "error");
        }
    }

    private void listen() throws IOException
    {
        String line;
        while ((line = in.readLine()) != "exit") {
            out.println("test");
        }
    }

    public static Game fromId(String id)
    {
        return new Game(id);
    }

}
