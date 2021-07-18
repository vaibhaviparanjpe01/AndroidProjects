package com.example.blooddonor1;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.*;
import android.widget.*;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.*;

public class SearchMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_main);
    }

    public void searchclick(View v)
    {
        Spinner s1=findViewById(R.id.spinner3);
        Spinner s2=findViewById(R.id.spinner4);
        String bg=s1.getSelectedItem().toString();
        String city=s2.getSelectedItem().toString();
        final GridView gv=findViewById(R.id.gridView1);
        final ArrayList x=new ArrayList();
        x.add("Name");
        x.add("Mobile");

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("/test/data/");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                long count=dataSnapshot.getChildrenCount();
                Toast.makeText(getApplicationContext(),"no of records:"+count,
                        Toast.LENGTH_SHORT).show();
                for(DataSnapshot child:dataSnapshot.getChildren())
                {
                    UserData dd=child.getValue(UserData.class);
                   x.add(dd.Name);
                   x.add(dd.mobile);
                }
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,x);
                gv.setAdapter(adapter);
                //String value = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

    }
}
