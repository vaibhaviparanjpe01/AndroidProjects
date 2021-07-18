package com.example.numberguessing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int min=1;
        int max=100;
       final int result=getRandomNumber(min,max);
        Button b1= (Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText t1=(EditText)findViewById(R.id.editTextNumber);
                int userGuessing= Integer.parseInt(t1.getText().toString());

                if(userGuessing < result)
                {
                    makeToast("Think of a higher number, Try Again");
                }
                else if(userGuessing > result)
                {
                    makeToast("Think of a smaller number, Try Again");
                }
                else
                {
                    makeToast("Congratulations, \n "+"You got the Number");

                }

            }
        });
    }

    public void makeToast(String str)
    {
        Toast.makeText(getApplicationContext(),str, Toast.LENGTH_SHORT).show();
    }

    public static int getRandomNumber(int min, int max)
    {
        return (int)(Math.random()*(max-min)+min);
    }

}