package com.newandromo.dev849565.app936843;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AirBopApplication extends Application implements SharedPreferences.OnSharedPreferenceChangeListener {
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
    }

    public void onCreate() {
        super.onCreate();
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
    }
}
