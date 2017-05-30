package com.example.tristan.textbasedgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nameButton = (Button) findViewById(R.id.namebutton);
        Button goButton = (Button) findViewById(R.id.gobutton);

        nameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameField = (EditText) findViewById(R.id.name);
                name = nameField.getText().toString();
                switchLayout();

            }
        });

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Game.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }

    public void switchLayout(){
        TextView newText = (TextView) findViewById(R.id.intro_speech);
        String pt1 = getResources().getString(R.string.intro);
        String pt2 = getResources().getString(R.string.intro2);
        String finalText = new String("Well then, " + name + ", "+ pt1 + "\n" + pt2);
        newText.setText(finalText);
    }

}
