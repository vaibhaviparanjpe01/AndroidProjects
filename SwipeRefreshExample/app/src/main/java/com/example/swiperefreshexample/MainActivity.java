package com.example.swiperefreshexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.widget.*;
import java.util.Random;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myform);
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.simpleSwipeRefreshLayout);
        textView=(TextView)findViewById(R.id.textView2);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //implement handler to wait for 3 seconds and then update UI means update value of TextView
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //cancel the Visual indication of a refresh
                        swipeRefreshLayout.setRefreshing(false);
                        //generate a random integer number
                        Random r=new Random();
                        int i1=r.nextInt(80-65)+65;
                        //set the number value in TextView
                        textView.setText(String.valueOf(i1));
                    }
                },3000);
            }
        });
    }
}