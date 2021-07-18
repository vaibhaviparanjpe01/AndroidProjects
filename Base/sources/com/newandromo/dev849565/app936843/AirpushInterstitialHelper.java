package com.newandromo.dev849565.app936843;

import android.app.Activity;
import android.content.Context;

public class AirpushInterstitialHelper extends InterstitialHelperBase {
    protected static final boolean AUDIO_INCLUDED = false;
    private static final String TAG = "InterstitialHelperBaseBlank";

    public static boolean initializeSDK(Context context) {
        return false;
    }

    public static boolean initializeSDK(Context context, String str) {
        return false;
    }

    public static void setPersonalizedAdsConsent(Context context, boolean z) {
    }

    /* access modifiers changed from: protected */
    public void destroyInterstitial() {
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

    public AirpushInterstitialHelper(String str, int i) {
    }

    /* access modifiers changed from: protected */
    public RunnableTiming getRunnableTiming() {
        return RunnableTiming.HANDLED_BY_SUBCLASS;
    }
}
