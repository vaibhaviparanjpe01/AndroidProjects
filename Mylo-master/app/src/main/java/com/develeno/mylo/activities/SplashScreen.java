package com.develeno.mylo.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.develeno.mylo.R;
import com.develeno.mylo.adapters.IndicatorAdapter;
import com.develeno.mylo.adapters.SplashScreenPagerAdapter;
import com.develeno.mylo.others.Data;

public class SplashScreen extends AppCompatActivity {

    private static final long SPLASH_TIME_OUT = 2000;

    public boolean hasSoftKeys() {
        boolean hasSoftwareKeys = true;

        Context c = getApplicationContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Display d = getWindowManager().getDefaultDisplay();

            DisplayMetrics realDisplayMetrics = new DisplayMetrics();
            d.getRealMetrics(realDisplayMetrics);

            int realHeight = realDisplayMetrics.heightPixels;
            int realWidth = realDisplayMetrics.widthPixels;

            DisplayMetrics displayMetrics = new DisplayMetrics();
            d.getMetrics(displayMetrics);

            int displayHeight = displayMetrics.heightPixels;
            int displayWidth = displayMetrics.widthPixels;

            hasSoftwareKeys = (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0;
        } else {
            boolean hasMenuKey = ViewConfiguration.get(c).hasPermanentMenuKey();
            boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            hasSoftwareKeys = !hasMenuKey && !hasBackKey;
        }
        return hasSoftwareKeys;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        setupBottomBar();

        // Initialize the SDK before executing any other operations,
        // especially, if you're using Facebook UI elements.


        boolean isFirstRun = Data.isFirstTime(this);

        if (!isFirstRun) {
            startActivity(new Intent(this, SplashActivity.class));
            finish();
        }


        final RelativeLayout layout = findViewById(R.id.relativeLayout);
        //layout.setVisibility(View.GONE);
        //Setting up Viewpager
        final ViewPager viewPager = findViewById(R.id.pager);
        viewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(new SplashScreenPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 3) {
                    layout.setVisibility(View.GONE);
                    //startActivity(new Intent(SplashScreen.this, SplashActivity.class));
                    //finish();
                } else {
                    layout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //Setting up viewPagerIndicator
        LinearLayout indicatorLayout = findViewById(R.id.indicator);
        new IndicatorAdapter(4, indicatorLayout, this, viewPager, R.drawable.dot_transparent, R.drawable.dot_white);

        LinearLayout next = findViewById(R.id.next_layout);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
            }
        });

        TextView skip = findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getChildCount() - 1, true);
            }
        });
        //hideNavigationBar();
    }

    private void setupBottomBar() {
        if (!hasSoftKeys()) {
            RelativeLayout layout = findViewById(R.id.relativeLayout);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Logs 'install' and 'app activate' App Events.
        //AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Logs 'app deactivate' App Event.
        //  AppEventsLogger.deactivateApp(this);
    }

    private void hideNavigationBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private void waitSomeTime() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }


    public void start(View view) {
        Intent i = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(i);
    }

    public void signIn(View view) {
        Intent i = new Intent(SplashScreen.this, SplashActivity.class);
        startActivity(i);
    }
}
