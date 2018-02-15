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

        }



        Button button = (Button)findViewById(R.id.negative_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
