package com.newandromo.dev849565.app936843;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class StaticAdHelper extends BannerHelperBase {
    private static final String TAG = "StaticAdHelper";
    private static final boolean mEnabled = true;
    private Activity mActivity = null;
    /* access modifiers changed from: private */
    public final String mAdClickUrl;
    private final int mAdLayoutID;

    /* access modifiers changed from: protected */
    public boolean shouldShowAds() {
        return mEnabled;
    }

    public StaticAdHelper(int i, String str) {
        this.mAdLayoutID = i;
        this.mAdClickUrl = "https://go.nordvpn.net/aff_c?offer_id=15&aff_id=22925&random_url=1&source=androm";
    }

    /* access modifiers changed from: protected */
    public int getAdResourceID() {
        Log.v(TAG, "getAdResourceID() for STATIC AD");
        return this.mAdLayoutID;
    }

    /* access modifiers changed from: protected */
    public boolean initializeAdView(Activity activity, View view) {
        Log.v(TAG, "initializeAdView() for STATIC AD");
        this.mActivity = activity;
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.adHeader);
        if (linearLayout != null) {
            View inflate = activity.getLayoutInflater().inflate(R.layout.static_ad_50, (ViewGroup) null);
            if (inflate == null) {
                return false;
            }
            linearLayout.addView(inflate);
            View findViewById = view.findViewById(R.id.adView);
            if (findViewById == null) {
                return false;
            }
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Context context = view.getContext();
                    if (context != null && StaticAdHelper.this.mAdClickUrl != null) {
                        try {
                            Log.v(StaticAdHelper.TAG, "Static Ad Clicked");
                            IntentUtils.openUrlInDefaultBrowserWithFallback(context, StaticAdHelper.this.mAdClickUrl);
                            AnalyticsUtils.trackEvent("Static Ad", "Clicked", (String) null, (Long) null);
                        } catch (ActivityNotFoundException unused) {
                            Toast.makeText(context, "Error loading URL", 0).show();
                            AnalyticsUtils.trackEvent("Static Ad", "Click Failed (Browser Not Found)", (String) null, (Long) null);
                        }
                    }
                }
            });
            findViewById.setVisibility(0);
            return mEnabled;
        }
        Log.v(TAG, "Static Ad adHeaderLayout was null");
        return false;
    }

    private int getBestAdLayout(Activity activity) {
        if (activity != null) {
            LinearLayout staticContentAdLayout = AdManager.getStaticContentAdLayout(activity);
            if (staticContentAdLayout != null) {
                int staticContentAdLayoutHeightDP = AdManager.getStaticContentAdLayoutHeightDP(staticContentAdLayout);
                Log.v(TAG, "StaticAd getBestAdLayout staticContentAdLayoutHeightDP: " + staticContentAdLayoutHeightDP);
                if (staticContentAdLayoutHeightDP <= 0) {
                    return R.layout.static_ad_50;
                }
                if (staticContentAdLayoutHeightDP <= 50) {
                    Log.v(TAG, "StaticAd getBestAdLayout - using R.layout.static_ad_50.");
                    return R.layout.static_ad_50;
                }
                Log.v(TAG, "StaticAd getBestAdLayout - using R.layout.static_ad_90.");
                return R.layout.static_ad_90;
            }
            Log.v(TAG, "StaticAd getBestAdLayout - staticContentAdLayout was null.");
            return R.layout.static_ad_50;
        }
        Log.v(TAG, "StaticAd getBestAdLayout - activity was null.");
        return R.layout.static_ad_50;
    }

    /* access modifiers changed from: protected */
    public int getAdStubID() {
        Log.v(TAG, "getAdStubID() for STATIC AD");
        return R.id.ad_stub;
    }

    /* access modifiers changed from: protected */
    public void onActivityDestroyed(Activity activity) {
        Log.v(TAG, "StaticAd onActivityDestroyed");
        if (activity != null && this.mActivity != null && activity == this.mActivity) {
            this.mActivity = null;
        }
    }
}
