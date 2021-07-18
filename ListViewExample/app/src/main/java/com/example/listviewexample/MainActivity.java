package com.example.listviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    static final String data[]=new String[]  {"Orange","Banana","Grapes","Coconut","Apple","Orange","Papaya","Mango"
                                        "Kiwi",""};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv=(ListView)findViewById(R.id.listView1);
        ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),R.layout.viewdata,data);
        lv.setAdapter(adapter);
    }
}
