package com.example.tristan.textbasedgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Tristan on 13/02/2018.
 */

public class PositiveEnd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.positive_end);

        BadGuy enemies[] = getIntent().getParcelableExtra("badGuys");
        String names = "You defeated";
        for(int i = 0; i < 9; i++){
            names += " " + enemies[i].name + ",";
        }
        names += " and Steve the Minotaur\n\nCongratulations.";

    }
}
