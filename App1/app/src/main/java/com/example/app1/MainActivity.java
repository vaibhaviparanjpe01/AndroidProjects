package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;
import android.view.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void abc(View v)
    {
        EditText t1= (EditText) findViewById(R.id.t1);    //pass by reference
        String val= t1.getText().toString();
        if(val.length()>0)
        {
            Toast.makeText(this,"Welcome : "+val, Toast.LENGTH_SHORT).show();   //for display msg
        }
    }
    public void evenodd(View v)
    {
        EditText t1= (EditText) findViewById(R.id.t1);   //pass by reference
        int i = Integer.parseInt((t1.getText().toString()));
        if(i%2==0)
        {
            Toast.makeText(this,"Value is even",Toast.LENGTH_SHORT).show();
        }
        else
        Toast.makeText(this,"Value is odd",Toast.LENGTH_SHORT).show();
    }
}