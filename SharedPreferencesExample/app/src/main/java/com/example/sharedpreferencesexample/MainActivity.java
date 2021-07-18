package com.example.sharedpreferencesexample;

import androidx.appcompat.app.AppCompatActivity;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static final String MYPREFERENCES="MyPrefs";  //appname
    public static final String Name="nameKey";  //column-name
    public static final String Phone="phoneKey";  //column-name

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences=getSharedPreferences(MYPREFERENCES,Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();

        String name=sharedPreferences.getString(Name,"");

        if(name.length()>0)
        {
            finish();
            Intent x=new Intent(MainActivity.this,WelcomeActivity.class);
            startActivity(x);
        }


        Button b1=(Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText t1=(EditText)findViewById(R.id.editTextTextPersonName);
                EditText t2=(EditText)findViewById(R.id.editTextPhone);

                String name=t1.getText().toString();
                String phone=t2.getText().toString();

                if(name.length()==0 || phone.length()==0 )
                {
                          Toast.makeText(getApplicationContext(),"Value cannot be empty",Toast.LENGTH_SHORT).show();
                          return;
                }
                editor.putString(Name,name);
                editor.putString(Phone,phone);
                editor.commit();
                finish();
                Intent x=new Intent(MainActivity.this,WelcomeActivity.class);
                startActivity(x);
            }
        });
    }
}