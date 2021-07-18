package com.example.cardviewexample;

import androidx.appcompat.app.AppCompatActivity;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ee);
        //init SwipeRefreshLayout and TextView
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.simpleSwipeRefreshLayout);
        textView=(TextView)findViewById(R.id.textView);

        //implement setOnRefreshListener event on SwipeRefreshlayout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //implement Handler to wait for 3 seconds and then update UI means update value of TextView
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                })
            }
        });
    }
}