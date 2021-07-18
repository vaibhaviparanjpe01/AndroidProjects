package com.example.blooddonor1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.view.*;
import android.os.Bundle;


public class RegMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_main);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.exit:
                                Toast.makeText(RegMainActivity.this, "Exit", Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.reg:
                                Toast.makeText(RegMainActivity.this, "Reg", Toast.LENGTH_SHORT).show();
                                break;
                    }
                        return true;
                }
                });
                }

    public void savedata(View v)
    {
        EditText t1=findViewById(R.id.editText);
        EditText t2=findViewById(R.id.editText2);
        Spinner s1=findViewById(R.id.spinner);
        Spinner s2=findViewById(R.id.spinner2);
        String name=t1.getText().toString();
        String mobile=t2.getText().toString();
        String bg=s1.getSelectedItem().toString();
        String city=s2.getSelectedItem().toString();

        if(name.length()==0 || mobile.length()==0)
        {
            Toast.makeText(getApplicationContext(),"Value cannont be empty",Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("test/data/"+mobile);

        UserData obj=new UserData();
        obj.Name=name;
        obj.mobile=mobile;
        obj.bg=bg;
        obj.city=city;
        myRef.setValue(obj);

       // myRef.setValue("Hello, World!");

        Toast.makeText(getApplicationContext(),"Record Saved Successfully",Toast.LENGTH_SHORT).show();

    }
}
