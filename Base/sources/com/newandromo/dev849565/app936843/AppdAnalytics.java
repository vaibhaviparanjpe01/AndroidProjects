package com.newandromo.dev849565.app936843;

import android.content.Context;

public class AppdAnalytics {
    public void activityStart(String str) {
    }

    public void activityStop() {
    }

    public boolean getEnableAnalyticsFromPrefs() {
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
        public static final AppdAnalytics INSTANCE = new AppdAnalytics();

        private InstanceHolder() {
        }
    }

    private AppdAnalytics() {
    }

    public static AppdAnalytics getInstance() {
        return InstanceHolder.INSTANCE;
    }
}
