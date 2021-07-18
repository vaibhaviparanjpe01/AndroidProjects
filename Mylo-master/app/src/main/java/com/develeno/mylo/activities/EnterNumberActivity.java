package com.develeno.mylo.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.develeno.mylo.FireBaseInteract;
import com.develeno.mylo.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class EnterNumberActivity extends AppCompatActivity {


    private static final int LOGIN = 12;
    private EditText editText;
    private String TAG = "EnterNumberActivity";
    private String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_number);
        setupToolbar();
        findViews();
    }

    private void findViews() {
        editText = findViewById(R.id.editText);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.MyToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Phone number");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*  @Override
      public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_skip, menu);
          return true;
      }
  */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.skip:
                new FireBaseInteract(this).openMainActivity();
                break;
        }
        return true;
    }


    public void onContinueButtonPress(View view) {

        number = "+91" + editText.getText().toString().trim();

        if (number.length() < 13) {
            editText.setError("number too short");
            return;
        }
/*
        Intent intent = new Intent(EnterNumberActivity.this, MainActivity.class);
        intent.putExtra("number", number);
        startActivity(intent);*/

      /*  Intent intent = new Intent(EnterNumberActivity.this, OTPVerificationActivity.class);
        intent.putExtra("number", number);
        startActivity(intent);
        finish();*/
        checkIfUserExists(number);
    }

    private void checkIfUserExists(final String number) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please Wait");
        dialog.setTitle("Checking");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        FirebaseFirestore ref = FirebaseFirestore.getInstance();
        ref.collection("users").document(number).get().addOnSuccessListener(documentSnapshot -> {
            dialog.dismiss();
            if (documentSnapshot.exists()) {
                // LoginActivity.setCredentials(credential);

                //Code for Login with Password using Msg91 API
                /*Toast.makeText(EnterNumberActivity.this, "Welcome back, please sign in", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EnterNumberActivity.this, LoginActivity.class);
                intent.putExtra("number", number + "");
                startActivity(intent);
                finish();*/

                Toast.makeText(EnterNumberActivity.this, "Welcome back, Enter OTP to sign in", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EnterNumberActivity.this, OTPVerificationActivity.class);
                intent.putExtra("number", number + "");
                startActivityForResult(intent, LOGIN);
//                finish();
            } else {
                // RegisterSellerActivity.setCredentials(credential);
                Toast.makeText(EnterNumberActivity.this, "New User, Please register yourself", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EnterNumberActivity.this, OTPVerificationActivity.class);
                intent.putExtra("number", number + "");
                intent.putExtra("register", true);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(e -> Toast.makeText(EnterNumberActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LOGIN && resultCode == RESULT_OK) {
            //Toast.makeText(EnterNumberActivity.this, "Welcome back, please sign in", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(EnterNumberActivity.this, LoginActivity.class);
            intent.putExtra("number", number + "");
            startActivity(intent);
            finish();
        }
    }
}
