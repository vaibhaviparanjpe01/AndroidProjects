package com.newandromo.dev849565.app936843;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.newandromo.dev849565.app936843.AdHelper;

public class JavascriptAdHelper extends BannerHelperBase {
    private static final String TAG = "BannerHelperBaseBlank";

    public static boolean initializeSDK(Context context) {
        return false;
    }

    public static boolean initializeSDK(Context context, String str) {
        return false;
    }

    public static void setPersonalizedAdsConsent(Context context, boolean z) {
    }

    /* access modifiers changed from: protected */
    public int getAdResourceID() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getAdStubID() {
        return 0;
    }

    public View getAdViewHolder(LayoutInflater layoutInflater) {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean initializeAdView(Activity activity, View view) {
        return false;
    }

    public boolean insertAd(Activity activity, ViewGroup viewGroup, AdHelper.SlideDirection slideDirection) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean shouldShowAds() {
        return false;
    }

    public boolean showAdInViewStub(Activity activity) {
        return false;
    }

    public JavascriptAdHelper(int i) {
    }
}
