package com.example.listviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    //ListView Lv;
    //String animalList[]={"Lion","Tiger","Monkey","Elephant","Dog","Cat","Camel"};
ListView simpleList;
String countryList[]={"India","China","Australia","Portugal","America","New Zealand"};
int flags[]={R.drawable.india,R.drawable.china,R.drawable.australia,R.drawable.portugal,R.drawable.newzealand};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleList=(ListView)findViewById(R.id.ListView);
        CustomAdapter customAdapter=new CustomAdapter(getApplicationContext(),countryList,flags);
        simpleList.setAdapter(customAdapter);
    }
}