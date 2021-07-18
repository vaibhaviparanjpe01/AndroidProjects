package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;

final class PreferenceUtils {
    private static final String ENABLE_ANALYTICS = "enable_analytics";
    private static final String TAG = "PreferenceUtils";

    static boolean areGlobalPrefsEnabled() {
        return false;
    }

    PreferenceUtils() {
    }

    static void addGlobalPrefs(PreferenceFragmentCompat preferenceFragmentCompat) {
        addAnalyticsPrefs(preferenceFragmentCompat);
    }

    static boolean areGlobalAnalyticsEnabled(Context context) {
        return AnalyticsUtils.isAnalyticsEnabledInPrefs(context);
    }

    static void addAnalyticsPrefs(final PreferenceFragmentCompat preferenceFragmentCompat) {
        SharedPreferences defaultSharedPreferences;
        if (AndromoFirebaseAnalytics.isFirebaseAnalyticsIncluded() || AnalyticsUtils.isAnalyticsEnabledInBuild()) {
            preferenceFragmentCompat.addPreferencesFromResource(R.xml.analytics_prefs);
            CheckBoxPreference checkBoxPreference = (CheckBoxPreference) preferenceFragmentCompat.findPreference(ENABLE_ANALYTICS);
            if (checkBoxPreference != null && (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(preferenceFragmentCompat.getActivity())) != null) {
                if (EulaHelper.isEuropeanUnion(AndromoApplication.getAppContext())) {
                    defaultSharedPreferences.edit().putBoolean(ENABLE_ANALYTICS, false).commit();
                    checkBoxPreference.setSelectable(false);
                    checkBoxPreference.setEnabled(false);
                    return;
                }
                checkBoxPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    public boolean onPreferenceChange(Preference preference, Object obj) {
                        Boolean bool = (Boolean) obj;
                        boolean z = !bool.booleanValue();
                        boolean booleanValue = bool.booleanValue();
                        Log.v(PreferenceUtils.TAG, "onPreferenceChange - GA analytics optOut state is: " + z);
                        Log.v(PreferenceUtils.TAG, "onPreferenceChange - FA analytics enabled state is: " + booleanValue);
                        AnalyticsUtils.handlePreferenceChange(preferenceFragmentCompat.getActivity(), z);
                        AndromoFirebaseAnalytics.handlePreferenceChange(booleanValue);
                        return true;
                    }
                });
            }
        }
    }
}
