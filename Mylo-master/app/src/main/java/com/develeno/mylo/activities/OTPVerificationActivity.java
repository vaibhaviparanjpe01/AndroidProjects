package com.develeno.mylo.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.develeno.mylo.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.msg91.sendotpandroid.library.SendOtpVerification;
import com.msg91.sendotpandroid.library.Verification;
import com.msg91.sendotpandroid.library.VerificationListener;

import java.util.Timer;
import java.util.TimerTask;

public class OTPVerificationActivity extends AppCompatActivity implements VerificationListener {

    private String number;
    private EditText otpField;
    private TextView tvField;
    private ProgressDialog dialog;
    private TextView timerField;
    private String verificationId;
    private Timer timer;
    private Verification mVerification;
//    private SmsVerifyCatcher smsVerifyCatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);
        findViews();
        number = getIntent().getExtras().getString("number", null);
        tvField.setText(tvField.getText() + number);

        if (number == null) {
            finish();
        }

//           smsVerifyCatcher = new SmsVerifyCatcher(this, new OnSmsCatchListener<String>() {
//            @Override
//            public void onSmsCatch(String message) {
//                if (message.contains("OTP")) {
//                    String numberOnly = message.replaceAll("[^0-9]", "");
//                    otpField.setText(numberOnly);
//                    onContinueButtonPress(null);
//                }
//            }
//        });

        getVerificationCode2();
        //otpField.setText("000000");


    }

    @Override
    protected void onStart() {
        super.onStart();
        // smsVerifyCatcher.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // smsVerifyCatcher.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // smsVerifyCatcher.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


   /* private void getVerificationCode() {

        dialog = new ProgressDialog(OTPVerificationActivity.this);
        dialog.setTitle("Please wait");
        dialog.setMessage("Sending OTP SMS to " + number);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        new FireBaseInteract(this).getVerificationCode(number, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                otpField.setText(phoneAuthCredential.getSmsCode());
                timerField.setVisibility(View.GONE);
                if (timer != null) {
                    timer.cancel();
                }
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(OTPVerificationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                dialog.dismiss();
                verificationId = s;
                startTimer();
                // Toast.makeText(OTPVerificationActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }*/

    private void startTimer() {
        final int[] n = {60};
        timer = new Timer();
        TimerTask timerTask;
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    n[0]--;
                    updateTimerText(n[0]);

                    if (n[0] <= 0) {
                        timer.cancel();
                        updateTimerText(true);
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    private void updateTimerText(int n) {
        timerField.setVisibility(View.VISIBLE);
        timerField.setTypeface(null, Typeface.NORMAL);
        timerField.setTextColor(Color.parseColor("#75000000"));
        timerField.setText("Wait for 00:" + (n < 10 ? ("0" + n) : n) + " seconds\nbefore retrying if you don't receive any code");
    }

    private void updateTimerText(boolean b) {
        timerField.setVisibility(View.VISIBLE);
        timerField.setText("Send code again");
        timerField.setTypeface(null, Typeface.BOLD);
        timerField.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        timerField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getVerificationCode();
                getVerificationCode2();
            }
        });
    }

    private void findViews() {
        otpField = findViewById(R.id.otp);
        tvField = findViewById(R.id.tv);
        timerField = findViewById(R.id.timer);
    }

    public void onContinueButtonPress(View view) {
        String code = otpField.getText().toString();
        if (!code.isEmpty() || !(code.length() < 6)) {
            //PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
            //checkIfUserExists(number);
            ProgressDialog dialog = new ProgressDialog(OTPVerificationActivity.this);
            dialog.setMessage("Verifying");
            dialog.show();
            mVerification.verify(code);
        } else {
            otpField.setError("Invalid Code");
            //onVerified("");
//            register(number);
        }
    }

    private void checkIfUserExists(final String number) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please Wait");
        dialog.setTitle("Checking");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        FirebaseFirestore ref = FirebaseFirestore.getInstance();
        ref.collection("users").document(number).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                dialog.dismiss();
                if (documentSnapshot.exists()) {
                    // LoginActivity.setCredentials(credential);
                    Toast.makeText(OTPVerificationActivity.this, "Welcome back, please sign in", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(OTPVerificationActivity.this, LoginActivity.class);
                    intent.putExtra("number", number + "");
                    startActivity(intent);
                    finish();
                } else {
                    // RegisterSellerActivity.setCredentials(credential);
                    register(number);
                }
            }
        }).addOnFailureListener(e -> Toast.makeText(OTPVerificationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    public void register(String number) {
        Toast.makeText(OTPVerificationActivity.this, "New User, Please register yourself", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(OTPVerificationActivity.this, RegisterSellerActivity.class);
        intent.putExtra("number", number + "");
        startActivity(intent);
        finish();
    }

    @Override
    public void onInitiated(String response) {
        //Toast.makeText(OTPVerificationActivity.this, response, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInitiationFailed(Exception paramException) {
        Toast.makeText(OTPVerificationActivity.this, paramException.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onVerified(String response) {
        Toast.makeText(OTPVerificationActivity.this, response, Toast.LENGTH_SHORT).show();
        final boolean register = getIntent().getBooleanExtra("register", false);
        if (register) {
            register(number);
        } else {
            Intent intent = getIntent();
            intent.putExtra("verified", true);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void onVerificationFailed(Exception paramException) {
        Toast.makeText(OTPVerificationActivity.this, paramException.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public void getVerificationCode2() {
        mVerification = SendOtpVerification.createSmsVerification
                (SendOtpVerification
                        .config(number)
                        .context(this).httpsConnection(false)
                        .autoVerification(true)
                        .build(), this);
        mVerification.initiate();
        startTimer();
    }
}
