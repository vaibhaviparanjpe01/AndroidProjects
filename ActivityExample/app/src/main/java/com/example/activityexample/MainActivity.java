package com.example.activityexample;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import android.view.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //EditText t1=(EditText)findViewById(R.id.textView3);
        //final String s=t1.getText().toString();

        Button b1 = findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(MainActivity.this, Main2Activity.class);
                x.putExtra("name","uu");
                startActivity(x);

            }
        });
        Button b2 = findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "networkping.website2.me";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
            }
        });
    }
}