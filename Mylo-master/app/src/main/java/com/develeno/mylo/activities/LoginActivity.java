package com.develeno.mylo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.develeno.mylo.FireBaseInteract;
import com.develeno.mylo.R;


public class LoginActivity extends AppCompatActivity {

    private EditText passwordInput;
    private EditText numberInput;
    private String number;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //setupToolbar();

        //usertype = getIntent().getExtras().getString("usertype", "user");
        //Button button = (Button) findViewById(R.id.login_btn);
        //button.setText("Login as " + usertype);

        findViews();
        numberInput.setText(getIntent().getExtras().getString("number", null));
        passwordInput.setText("123456");
        passwordInput.setVisibility(View.GONE);
        login(null);
    }


    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.MyToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Log in");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
          /*  case R.id.save:
                save();
                break;*/
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    public void login(View view) {

        if (checkFields()) {
            signin();
        }
        // overridePendingTransition(R.anim.exnter, R.anim.exit);
    }

    private void signin() {
        FireBaseInteract firebaseInteract = new FireBaseInteract(this);
        firebaseInteract.signInWithPassword(number, password);
        MainActivity.loggedIn = true;
    }

    public boolean checkFields() {
        number = numberInput.getText().toString().trim();
        password = passwordInput.getText().toString().trim();

        if (number.isEmpty()) {
            numberInput.setError("Required");
            return false;
        }

        if (number.length() < 10) {
            numberInput.setError("Invalid Number");
            return false;
        }

        if (password.isEmpty()) {
            passwordInput.setError("Required");
            return false;
        }

        if (password.length() < 6) {
            passwordInput.setError("Too short");
            return false;
        }
        return true;
    }

    public void findViews() {
        numberInput = findViewById(R.id.input_email);
        passwordInput = findViewById(R.id.input_password);
    }

    public void register(View view) {
//        startActivity(new Intent(this, RegisterSellerActivity.class));
    }

    public void forgotpassword(View view) {
        final Intent intent = new Intent(this, ForgetPasswordActivity.class);
        intent.putExtra("number", getIntent().getExtras().getString("number", null));
        startActivity(intent);
    }
}
