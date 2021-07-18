package com.newandromo.dev849565.app936843;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

public class AndromoPreferencesFragment extends PreferenceFragmentCompat {
    public void onCreatePreferences(Bundle bundle, String str) {
        PreferenceUtils.addAnalyticsPrefs(this);
    }
}
