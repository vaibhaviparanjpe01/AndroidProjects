package com.example.blooddonor1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getSupportActionBar().hide();
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        Button b=findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent x=new Intent(MainActivity.this,RegMainActivity.class);
                startActivity(x);

            }
        });

        Button b2=findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent x=new Intent(MainActivity.this,SearchMainActivity.class);
                startActivity(x);
            }
        });

        Button b3=findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent x=new Intent(MainActivity.this,SearchMainActivity.class);
                finish();
            }
        });

       // requestWindowFeature(Window.FEATURE_NO_TITLE);    //will hide the title bar
        //getSupportActionBar().hide();  //hide the title bar
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch(id)
        {
            case R.id.reg:
                Intent x=new Intent(MainActivity.this,RegMainActivity.class);
                startActivity(x);
                return true;

            case R.id.search:
                Intent x1=new Intent(MainActivity.this,SearchMainActivity.class);
                startActivity(x1);
                return true;

            case R.id.exit:
                finish();
                return true;
            default:
                return true;
        }
    }



}
