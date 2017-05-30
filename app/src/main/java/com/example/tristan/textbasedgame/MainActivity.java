package com.example.tristan.textbasedgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nameButton = (Button) findViewById(R.id.namebutton);
        Button goButton = (Button) findViewById(R.id.gobutton);
        
    }


}
