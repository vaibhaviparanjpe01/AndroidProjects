package com.example.blooddonorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import  java.util.*;
public class SearchMainActivity extends AppCompatActivity {
    ArrayList List1;
    Spinner s3,s4;
    Button b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_main);

        final GridView gv= (GridView)findViewById(R.id.gridview);
        b4=(Button) findViewById(R.id.button4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List1= new ArrayList();
                s3=(Spinner)findViewById(R.id.spinner3);
                s4=(Spinner)findViewById(R.id.spinner4);

                final String bg=s3.getSelectedItem().toString();
                final String city=s4.getSelectedItem().toString();


                List1.add("Name");
                List1.add("Mobile");

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("UserInfo");

                //Read from the database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            RegModel obj = ds.getValue(RegModel.class);

                            if(bg.equals(obj.getBg()) && city.equals(obj.getCity())){
                                List1.add(ds.child("Name").getValue().toString());
                                List1.add(ds.child("Mobile").getValue().toString());



                            }
                            Toast.makeText(getApplicationContext(), ds.child("Name").getValue().toString(), Toast.LENGTH_SHORT).show();
                        }
                        ArrayAdapter<String> adapter=
                                new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,List1);

                        gv.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        //Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
            }

        });
    }
}