package com.develeno.mylo.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.develeno.mylo.fragments.FragmentGetStarted;
import com.develeno.mylo.fragments.FragmentSplashScreen;

/**
 * Created by devel_000 on 13-Sep-15.
 */
public class SplashScreenPagerAdapter extends FragmentStatePagerAdapter {

    public SplashScreenPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
//                return new FragmentGetStarted();
                FragmentSplashScreen fragmentSplashScreen = new FragmentSplashScreen();
                Bundle bundle = new Bundle();
                bundle.putInt("selection", 1);
                fragmentSplashScreen.setArguments(bundle);
                return fragmentSplashScreen;
            }
            case 1: {
                FragmentSplashScreen fragmentSplashScreen = new FragmentSplashScreen();
                Bundle bundle = new Bundle();
                bundle.putInt("selection", 2);
                fragmentSplashScreen.setArguments(bundle);
                return fragmentSplashScreen;
            }
            case 2: {
                FragmentSplashScreen fragmentSplashScreen = new FragmentSplashScreen();
                Bundle bundle = new Bundle();
                bundle.putInt("selection", 3);
                fragmentSplashScreen.setArguments(bundle);
                return fragmentSplashScreen;
            }
            case 3: {
                return new FragmentGetStarted();
            }
            default: {
                return new FragmentGetStarted();
            }
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
