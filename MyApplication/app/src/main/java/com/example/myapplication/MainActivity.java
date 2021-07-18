package com.example.myapplication;
import android.widget.Toast;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.*;
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
        EditText t1=(EditText) findViewById(R.id.editText);
        int i=Integer.parseInt(t1.getText().toString());
        if(i%2==0)
        {
            Toast.makeText(this,"Value is even", Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(this,"Value is odd", Toast.LENGTH_SHORT).show();
        }
    }
}
