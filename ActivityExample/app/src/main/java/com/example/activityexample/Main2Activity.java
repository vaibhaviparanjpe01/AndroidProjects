package com.example.activityexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String n;
        Bundle extras=getIntent().getExtras();
        if(extras==null)
        {
            n=null;
        }
        else
        {
            //fetching the string passed with intent using extras
            n=extras.getString("name");
            Toast.makeText(getApplicationContext(),n,Toast.LENGTH_SHORT).show();
        }
    }
}
