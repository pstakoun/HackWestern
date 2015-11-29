package com.stakoun.hackwestern;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class VictimActivity extends AppCompatActivity {
    private Game game;
    private Handler healthHandler = new Handler() {
        public void updateHealth(Game game) {
            ((TextView) findViewById(R.id.health)).setText(game.getHealth() + "%");
        }
    };
    private Runnable healthRunnable = new Runnable() {
        @Override
        public void run() {
            healthHandler.postDelayed(healthRunnable, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victim);
        game = new Game(this);
    }


}
