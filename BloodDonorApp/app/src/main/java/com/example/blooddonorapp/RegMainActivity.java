package com.example.blooddonorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.*;
import android.view.*;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegMainActivity extends AppCompatActivity {

    Button b4;
    EditText t1,t2;
    Spinner s1,s2;
    ProgressBar pb1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_main);

        pb1=(ProgressBar)findViewById(R.id.progressBar2);
        pb1.setVisibility(View.GONE);


        b4=(Button)findViewById(R.id.button5);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {
                    pb1.setVisibility(View.VISIBLE);
                    t1=(EditText)findViewById(R.id.editTextTextPersonName);
                    t2=(EditText)findViewById(R.id.editTextPhone);
                    s1=(Spinner)findViewById(R.id.spinner);  //bg
                    s2=(Spinner)findViewById(R.id.spinner2);   //city

                    String Name=t1.getText().toString();
                    String Mobile=t2.getText().toString();
                    String bg=s1.getSelectedItem().toString();
                    String city=s2.getSelectedItem().toString();

                    if(Name.length()==0 || Mobile.length()==0)
                    {
                        Toast.makeText(getApplicationContext(),"Value cannot be empty",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("User Info");
                    String key=myRef.push().getKey();   //new key generate

                    RegModel obj=new RegModel(Name,Mobile,bg,city);

                    HashMap hm=new HashMap();
                    hm.put("Name",obj.getName());
                    hm.put("Mobile",obj.getMobile());
                    hm.put("bg",obj.getBg());
                    hm.put("city",obj.getCity());
                    myRef.child(key).updateChildren(hm);

                    //myRef.setValue(obj);
                    pb1.setVisibility(View.GONE);

                    Toast.makeText(getApplicationContext(),"Registered Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}