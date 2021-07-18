package com.newandromo.dev849565.app936843;

import android.content.Context;

public class AndromoAnalytics {
    static boolean MINIMAL_TRACKING_ONLY;

    public void activityStart(String str) {
    }

    public void activityStop() {
    }

    public boolean getEnableAnalyticsFromPrefs() {
        return false;
    }

    public boolean isMinimalTrackingOnly() {
        return false;
    }

    public boolean isTrackingEnabledInBuild() {
        return false;
    }

    public void sendEvent(String str, String str2, String str3, Long l) {
    }

    public void sendView(String str) {
    }

    public void setAppOptOut(boolean z) {
    }

    public void setAppOptOutAndSaveToPrefs(boolean z) {
    }

    public void setContext(Context context) {
    }

    public void setEnableAnalyticsInPrefs(boolean z) {
    }

    private static class InstanceHolder {
        public static final AndromoAnalytics INSTANCE = new AndromoAnalytics();

        private InstanceHolder() {
        }
    }

    private AndromoAnalytics() {
    }

    public static AndromoAnalytics getInstance() {
        return InstanceHolder.INSTANCE;
    }
}
