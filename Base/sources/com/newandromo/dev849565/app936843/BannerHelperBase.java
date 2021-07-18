package com.newandromo.dev849565.app936843;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import com.newandromo.dev849565.app936843.AdHelper;

public abstract class BannerHelperBase {
    private static final String TAG = "BannerHelperBase";
    private AdFallbackHandler mAdFallbackHandler;
    private boolean mIsAbstractBanner = false;
    private boolean mIsAndromoAd = false;

    /* access modifiers changed from: protected */
    public abstract int getAdResourceID();

    /* access modifiers changed from: protected */
    public abstract int getAdStubID();

    /* access modifiers changed from: protected */
    public abstract boolean initializeAdView(Activity activity, View view);

    /* access modifiers changed from: protected */
    public void onActivityDestroyed(Activity activity) {
    }

    /* access modifiers changed from: protected */
    public void onActivityPaused(Activity activity) {
    }

    /* access modifiers changed from: protected */
    public void onActivityResumed(Activity activity) {
    }

    /* access modifiers changed from: protected */
    public void onActivityStarted(Activity activity) {
    }

    /* access modifiers changed from: protected */
    public void onActivityStopped(Activity activity) {
    }

    /* access modifiers changed from: protected */
    public void onApplicationCreated(Context context) {
    }

    /* access modifiers changed from: protected */
    public abstract boolean shouldShowAds();

    public boolean isAbstractBanner() {
        return this.mIsAbstractBanner;
    }

    public void setAbstractBanner(boolean z) {
        this.mIsAbstractBanner = z;
    }

    public boolean isAndromoAd() {
        return this.mIsAndromoAd;
    }

    public void setAndromoAd(boolean z) {
        this.mIsAndromoAd = z;
    }

    public void setAdFallbackHandler(AdFallbackHandler adFallbackHandler) {
        this.mAdFallbackHandler = adFallbackHandler;
    }

    public AdFallbackHandler getAdFallbackHandler() {
        Log.v(TAG, this.mAdFallbackHandler != null ? "mAdFallbackHandler != null" : "mAdFallbackHandler == null");
        return this.mAdFallbackHandler;
    }

    public boolean insertAd(Activity activity, ViewGroup viewGroup, AdHelper.SlideDirection slideDirection) {
        boolean z = false;
        if (activity == null || viewGroup == null) {
            return false;
        }
        View adViewHolder = getAdViewHolder(activity, activity.getLayoutInflater());
        if (adViewHolder != null) {
            z = true;
            viewGroup.addView(adViewHolder);
            switch (slideDirection) {
                case DOWN:
                    if (AdHelper.AdAnimation.shouldSlideAway(activity)) {
                        AdHelper.AdAnimation.postDelayedSlideAway(adViewHolder);
                        break;
                    }
                    break;
                case UP:
                    if (AdHelper.AdAnimation.shouldSlideAway(activity)) {
                        AdHelper.AdAnimation.postDelayedSlideAwayTop(adViewHolder);
                        break;
                    }
                    break;
            }
        }
        return z;
    }

    public View getAdViewHolder(Activity activity, LayoutInflater layoutInflater) {
        return getAdViewHolder(activity, layoutInflater, getAdResourceID());
    }

    public View getAdViewHolder(LayoutInflater layoutInflater) {
        Log.v(TAG, "Calling getAdViewHolder without activity reference.");
        return getAdViewHolder((Activity) null, layoutInflater, getAdResourceID());
    }

    private View getAdViewHolder(Activity activity, LayoutInflater layoutInflater, int i) {
        Log.v(TAG, "getAdViewHolder");
        View view = null;
        if (!(!shouldShowAds() || layoutInflater == null || (view = layoutInflater.inflate(i, (ViewGroup) null)) == null)) {
            initializeAdView(activity, view);
        }
        return view;
    }

    public boolean showAdInViewStub(Activity activity) {
        if (!shouldShowAds() || activity == null) {
            return false;
        }
        return showAdInViewStub(activity, (ViewStub) activity.findViewById(getAdStubID()));
    }

    public boolean showAdInViewStub(Activity activity, ViewStub viewStub) {
        boolean z = false;
        if (activity == null || viewStub == null) {
            return false;
        }
        if (AdHelper.Size.isScreenTallEnough(activity)) {
            int pixels = AdHelper.Size.getPixels(50.0f, AdHelper.Size.getDensity(activity));
            viewStub.setLayoutResource(getAdResourceID());
            viewStub.setInflatedId(-1);
            View inflate = viewStub.inflate();
            if (inflate != null) {
                if (pixels > 0) {
                    inflate.setMinimumHeight(pixels);
                }
                z = initializeAdView(activity, inflate);
            }
        }
        if (!z) {
            viewStub.setVisibility(8);
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public void resetAdFallbacks() {
        Log.v(TAG, "resetAdFallbacks()");
        if (this.mAdFallbackHandler != null) {
            this.mAdFallbackHandler.resetAdFallbacks();
        }
    }

    /* access modifiers changed from: protected */
    public void replaceAdWithFallback(Activity activity, View view) {
        Log.v(TAG, "replaceAdWithFallback()");
        Log.v(TAG, this.mAdFallbackHandler != null ? "mAdFallbackHandler != null" : "mAdFallbackHandler == null");
        if (this.mAdFallbackHandler != null) {
            this.mAdFallbackHandler.replaceAdWithFallback(activity, view);
        }
    }
}
