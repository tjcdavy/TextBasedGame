package com.example.tristan.textbasedgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Tristan on 16/02/2018.
 */

public class OpeningPage extends AppCompatActivity {

    public String name;
    public Boolean instant;
    LinearLayout layout1;
    LinearLayout layout2;
    EditText nameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening_page);

        Button nameButton = (Button) findViewById(R.id.namebutton);
        Button goButton = (Button) findViewById(R.id.gobutton);

        layout1 = (LinearLayout) findViewById(R.id.opening_layout);
        layout2 = (LinearLayout) findViewById(R.id.introduction);
        nameField = (EditText) findViewById(R.id.name);

        instant = null;

        nameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView badName = (TextView)findViewById(R.id.name_fail);
                if(nameField.getText().toString().equals("")){
                    badName.setVisibility(View.VISIBLE);
                } else {
                    name = nameField.getText().toString();
                    switchLayout();
                    badName.setVisibility(View.GONE);
                }
            }
        });

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpeningPage.this, Game.class);
                intent.putExtra("name", name);
                startActivity(intent);

                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);
                nameField.setText("");
            }
        });
    }

    /**
     * Changes from the name entry view to the introduction
     */
    public void switchLayout(){
        checkName(name);
        TextView newText = (TextView) findViewById(R.id.intro_speech);
        String pt1 = getResources().getString(R.string.intro);
        String pt2 = getResources().getString(R.string.intro2);
        String finalText = new String("Well then, " + name + ", " + pt1 + "\n\n" + pt2);
        newText.setText(finalText);

        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.VISIBLE);
    }

    /**
     * Checks the name for special conditions
     * @param toCheck The name to be checked
     */
    public void checkName(String toCheck){
        toCheck = toCheck.toLowerCase();
        if(toCheck.equals("neo")){
            //Changes the name
            name = "Mr. Anderson";
        }else if(toCheck.equals("tristan") || toCheck.equals("gary the greyhound")){
            //Makes the player instantly win
            instant = true;
        }else if(toCheck.equals("ben") || toCheck.equals("teegan") || toCheck.equals("merlin")){
            //makes the player instantly lose
            instant = false;
        }
    }

    @Override
    public void onBackPressed(){
        if(layout2.getVisibility() == View.VISIBLE){
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.GONE);
        } else {
            finish();
        }
    }
}
