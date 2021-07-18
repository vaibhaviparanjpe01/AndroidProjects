package com.example.blooddonorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.*;
import android.view.*;
import android.content.*;       //Intent-- transfer controlling from one activity to another activity

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Button b1, b2, b3;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(MainActivity.this, RegMainActivity.class);
                startActivity(x);
            }
        });

        b2 = (Button) findViewById(R.id.button2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(MainActivity.this, SearchMainActivity.class);
                startActivity(x);
            }
        });

        b3 = (Button) findViewById(R.id.button3);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}