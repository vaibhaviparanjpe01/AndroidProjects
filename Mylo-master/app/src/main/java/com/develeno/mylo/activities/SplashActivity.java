package com.develeno.mylo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.develeno.mylo.FireBaseInteract;
import com.develeno.mylo.R;
import com.develeno.mylo.others.Data;
import com.develeno.mylo.pojo.UserObject;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    public static void makeStatusBarTranslucent(Activity activity) {
        // Check if the version of Android is Lollipop or higher
        if (Build.VERSION.SDK_INT >= 21) {

            // Set the status bar to dark-semi-transparencies
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // Set paddingTop of toolbar to height of status bar.
            // Fixes statusbar covers toolbar issue
            //toolbar.setPadding(0, getStatusBarHeight(), 0, 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        makeStatusBarTranslucent(this);

        View v = findViewById(R.id.splash);
        ImageView logo = findViewById(R.id.logo);
        ImageView circular_logo = findViewById(R.id.circular_logo);
        TextView shopname = findViewById(R.id.shopname);

        String code = getIntent().getStringExtra("code");

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            v.setVisibility(View.VISIBLE);
            final FireBaseInteract fireBaseInteract = new FireBaseInteract(this);
            fireBaseInteract.fetchUserDetails(user.getEmail(), new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    FireBaseInteract.userObject = documentSnapshot.toObject(UserObject.class);

                    try {
                        fireBaseInteract.fetchMyDeals(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                ArrayList<String> mydeals = new ArrayList<>();
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                                    mydeals.add(snapshot.getId());
                                }
                                Data.myDeals = mydeals;
                                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                                if (code != null && !code.isEmpty()) {
                                    intent.putExtra("code", code);
                                }
                                SplashActivity.this.startActivity(intent);
                                SplashActivity.this.finish();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(SplashActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        new FireBaseInteract(SplashActivity.this).logout();
                    }
                }
            });
        } else {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something after 100ms
                    SplashActivity.this.startActivity(new Intent(SplashActivity.this, EnterNumberActivity.class));
                    SplashActivity.this.finish();
                }
            }, 1000);
        }
    }

    public void getStarted(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
