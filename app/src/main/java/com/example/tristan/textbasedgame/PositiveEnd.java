package com.example.tristan.textbasedgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Tristan on 13/02/2018.
 */

public class PositiveEnd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.positive_end);

        String enemies[] = getIntent().getStringArrayExtra("badGuys");

        String names = "You defeated";
        for(int i = 0; i < 9; i++){
            names += " " + enemies[i] + ",";
        }
        names += " and Steve the Minotaur\n\nCongratulations.";

        TextView textView = (TextView) findViewById(R.id.positive_text);
        textView.setText(names);

    }
}
