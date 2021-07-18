package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.concurrent.atomic.AtomicInteger;

public final class RequestCode {
    private static final String TAG = "RequestCode";
    private static int lastSavedValue = 0;
    private static final AtomicInteger requestCode = new AtomicInteger(0);

    public static int getNext() {
        return requestCode.incrementAndGet();
    }

    public static void loadFromPrefsIfNeeded(Context context) {
        if (requestCode.get() == 0) {
            loadFromPrefs(context);
        }
    }

    public static void saveToPrefsIfNeeded(Context context) {
        if (requestCode.get() != lastSavedValue) {
            saveToPrefs(context);
        }
    }

    public static void loadFromPrefs(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (defaultSharedPreferences != null) {
            int i = defaultSharedPreferences.getInt("last_request_code", 0);
            if (i != 0) {
                requestCode.set(i);
                return;
            }
            requestCode.set((int) (System.currentTimeMillis() & 4294967295L));
        }
    }

    public static void saveToPrefs(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (defaultSharedPreferences != null) {
            if (requestCode.get() == 0) {
                requestCode.incrementAndGet();
            }
            defaultSharedPreferences.edit().putInt("last_request_code", requestCode.get()).apply();
            lastSavedValue = requestCode.get();
        }
    }
}
