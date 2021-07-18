package com.newandromo.dev849565.app936843;

import android.app.Activity;
import android.content.Context;

public class AppBrainInterstitialHelper extends InterstitialHelperBase {
    static void initializeAppBrain(Context context) {
    }

    /* access modifiers changed from: protected */
    public void destroyInterstitial() {
    }

    public String getNameForReport() {
        return "appbrain";
    }

    /* access modifiers changed from: protected */
    public void hideInterstitial() {
    }

    /* access modifiers changed from: protected */
    public boolean initializeInterstitial(Activity activity) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isReadyToShowInterstitial(Context context) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean shouldShowAds() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean showInterstitial(Activity activity) {
        return false;
    }

    /* access modifiers changed from: protected */
    public RunnableTiming getRunnableTiming() {
        return RunnableTiming.HANDLED_BY_SUBCLASS;
    }
}
