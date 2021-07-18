package com.develeno.mylo.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.develeno.mylo.FireBaseInteract;
import com.develeno.mylo.R;
import com.google.android.gms.tasks.OnSuccessListener;


public class ForgetPasswordActivity extends AppCompatActivity {

    private EditText resetCode;
    private EditText password;
    private EditText password2;
    private TextView tv;
    private int REQUEST_CODE = 1212;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        setupToolbar();
        findViews();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.MyToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Reset Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void findViews() {
        resetCode = findViewById(R.id.fullname);
        password = findViewById(R.id.password);
        password2 = findViewById(R.id.repassword);
        tv = findViewById(R.id.tv);
        tv.setText("Please enter the password reset code\nwe just sent to " + getIntent().getExtras().getString("number", null));
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

    public void signUp(View view) {
        try {
            if (checkFields()) {
                final Intent intent = new Intent(this, OTPVerificationActivity.class);
                intent.putExtra("number", FireBaseInteract.userObject.getNumber());
                intent.putExtra("register", false);
                startActivityForResult(intent, REQUEST_CODE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkFields() {
        TextView password = findViewById(R.id.password);
        TextView repassword = findViewById(R.id.repassword);
        String passwordString = password.getText().toString();
        String repasswordString = repassword.getText().toString();
        if (passwordString.isEmpty()) {
            password.setError("Cannot be empty");
            return false;
        }
        if (passwordString.length() < 6) {
            password.setError("Password must be at least 6 characters long");
            return false;
        }
        if (repasswordString.isEmpty()) {
            repassword.setError("Cannot be empty");
            return false;
        }
        if (repasswordString.length() < 6) {
            repassword.setError("Password must be at least 6 characters long");
            return false;
        }

        if (!passwordString.equals(repasswordString)) {
            repassword.setError("Password do not match");
            return false;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            boolean requiredValue = data.getBooleanExtra("verified", false);
            if (requiredValue) {
                resetPassword();
            }
        }
    }

    private void resetPassword() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Updating Password");
        dialog.setTitle("Please wait");
        dialog.show();
        FireBaseInteract.userObject.setPassword(password.getText().toString());
        new FireBaseInteract(this).saveUser(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dialog.dismiss();
                finish();
                Toast.makeText(ForgetPasswordActivity.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
