package com.stakoun.hackwestern;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playGame(View v)
    {
        GetTypeTask gtt = new GetTypeTask();
        gtt.execute();
        postponeEnterTransition();
        try {
            gtt.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String s = gtt.getType();
        if (s == null)
            return;
        else if (s.equals("assassin")) {
            Intent intent = new Intent(this, AssassinActivity.class);
            startActivity(intent);
            finish();
        } else if (s.equals("victim")) {
            Intent intent = new Intent(this, VictimActivity.class);
            startActivity(intent);
            finish();
        }
    }

}

class GetTypeTask extends AsyncTask<Void, Void, Void> {
    String type;

    @Override
    public Void doInBackground(Void... voids) {
        try {
            type = (new BufferedReader(new InputStreamReader((new Socket("52.24.241.238", 8080)).getInputStream()))).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getType() {
        return type;
    }

}
