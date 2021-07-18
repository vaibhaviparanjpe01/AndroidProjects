package com.develeno.mylo.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.develeno.mylo.FireBaseInteract;
import com.develeno.mylo.R;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Toolbar toolbar = findViewById(R.id.MyToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView profile = findViewById(R.id.profilepic);
        EditText fullname = findViewById(R.id.fullname);
        EditText phone = findViewById(R.id.phone);
        EditText email = findViewById(R.id.email);

        fullname.setText(FireBaseInteract.userObject.getName());
        phone.setText(FireBaseInteract.userObject.getNumber() + "");
        email.setText(FireBaseInteract.userObject.getEmail());


        final String name = FireBaseInteract.userObject.getName();
        String s;
        if (name.contains(" ")) {
            s = name.charAt(0) + "" + name.charAt(name.indexOf(" ") + 1);
        } else {
            s = name.charAt(0) + "";
        }

        final TextDrawable drawable = TextDrawable.builder().beginConfig().textColor(Color.WHITE).bold().endConfig()
                .buildRound(s.toUpperCase(), getResources().getColor(R.color.colorPrimary));

        profile.setImageDrawable(drawable);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void updatePassword(View view) {
        final Intent intent = new Intent(this, ForgetPasswordActivity.class);
        intent.putExtra("number", FireBaseInteract.userObject.getNumber());
        startActivity(intent);
    }
}
