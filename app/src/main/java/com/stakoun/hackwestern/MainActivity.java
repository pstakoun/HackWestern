package com.stakoun.hackwestern;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void joinGame(View v)
    {
        String id = ((EditText) findViewById(R.id.game_id)).getText().toString();
        if (Game.fromId(id) != null) {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
            finish();
        }
    }

    public void createGame(View v)
    {

    }

}
