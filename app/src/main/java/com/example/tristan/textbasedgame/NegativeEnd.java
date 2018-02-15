package com.example.tristan.textbasedgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Tristan on 13/02/2018.
 */

public class NegativeEnd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.negative_end);

        TextView textView = (TextView)findViewById(R.id.negative_text);

        String enemies[] = getIntent().getStringArrayExtra("badGuys");
        boolean ran = getIntent().getBooleanExtra("run", false);


        String names;
        if(enemies.length == 1){
            names = "You ran away from " + enemies[0] + ".\n\nSeriously?  You couldn't even defeat a Goblin?\nYou disappoint me.";
        }else{
            names = "You defeated ";
            for(int i = 0; i < enemies.length-1; i++){
                names += enemies[i] + ", ";
            }
            if(ran){
                names += "but ran away from " + enemies[enemies.length-1] + ".";
            }else{
                names += "but were defeated by " + enemies[enemies.length-1] + ".";
            }
            if(enemies.length == 10){
                names += "\nI guess I've got to respect the fact that you made it all the way to Steve the Minotaur.";
            }
            names += "\n\nOh well, guess it wasn't meant to be.\nI'm not angry, I'm just disappointed.";
        }

        textView.setText(names);

        Button button = (Button)findViewById(R.id.negative_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
