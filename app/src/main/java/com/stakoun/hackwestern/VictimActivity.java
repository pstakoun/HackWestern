package com.stakoun.hackwestern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class VictimActivity extends AppCompatActivity {
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victim);
        game = new Game(this);
    }

}
