package com.example.sharedpreferencesexample;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    public static final String MYPREFERENCES="MyPrefs";  //appname
    public static final String Name="nameKey";  //column-name
    public static final String Phone="phoneKey";  //column-name
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        sharedPreferences=getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();
        String name=sharedPreferences.getString(Name," ");

        TextView tv=(TextView)findViewById(R.id.textView2);
        tv.setText("Welcome: "+name);


    }
}