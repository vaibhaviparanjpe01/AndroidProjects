package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.util.Log;
import com.flurry.android.FlurryAgent;
import com.flurry.android.FlurryAgentListener;
import java.util.HashMap;
import java.util.Map;

public final class AndromoFlurryAnalytics {
    private static final String ANDROMO_FLURRY_API_KEY = "88NR3STM8VVDY3N7MPK5";
    private static final String EVENT_NAME_APP_LAUNCH = "app_launch";
    private static final String EVENT_NAME_BANNER = "banner_ad";
    private static final String EVENT_NAME_INTERSTITIAL = "interstitial_ad";
    private static final String EVENT_NAME_PAGE_VIEW = "page_view";
    private static final String PARAM_NAME_ACTIVITY_TYPE = "activity_type";
    private static final String PARAM_NAME_PACKAGE = "package_name";
    private static final String TAG = "AndromoFlurryAnalytics";
    private static final String VERSION_NAME = "5.2.2";
    private static boolean mFlurryAnalyticsEnabled = true;
    private static final boolean mFlurryLoggingEnabled = false;

    public static boolean isFlurryAnalyticsIncluded() {
        return true;
    }

    public static boolean isFlurryAnalyticsEnabled() {
        boolean z = isFlurryAnalyticsIncluded() && mFlurryAnalyticsEnabled;
        Log.v(TAG, "isFlurryAnalyticsEnabled: " + z);
        return z;
    }

    public static void setFlurryAnalyticsEnabled(boolean z) {
        Log.v(TAG, "setFlurryAnalyticsEnabled: Setting to " + z);
        mFlurryAnalyticsEnabled = z;
    }

    public static boolean isFlurryAnalyticsSessionActive() {
        return FlurryAgent.isSessionActive();
    }

    public static void initialize(Context context) {
        if (mFlurryAnalyticsEnabled) {
            FlurryAgent.setVersionName(VERSION_NAME);
            Log.v(TAG, "initialize: Flurry Analytics version name set to - 5.2.2");
            Log.v(TAG, "initialize: Initializing Flurry Analytics.");
            new FlurryAgent.Builder().withLogEnabled(false).withLogLevel(2).withListener(new FlurryAgentListener() {
                public void onSessionStarted() {
                    Log.v(AndromoFlurryAnalytics.TAG, "Flurry Analytics - onSessionStarted.");
                    FlurryAgent.addSessionProperty(AndromoFlurryAnalytics.PARAM_NAME_PACKAGE, BuildConfig.APPLICATION_ID);
                    AndromoFlurryAnalytics.trackAppLaunchEvent();
                }
            }).build(context, ANDROMO_FLURRY_API_KEY);
            return;
        }
        Log.v(TAG, "initialize: Flurry Analytics is disabled, not initializing.");
    }

    public static void trackAppLaunchEvent() {
        if (mFlurryAnalyticsEnabled) {
            Log.v(TAG, "Flurry Analytics: trackAppLaunchEvent.");
            HashMap hashMap = new HashMap();
            hashMap.put(PARAM_NAME_PACKAGE, BuildConfig.APPLICATION_ID);
            FlurryAgent.logEvent(EVENT_NAME_APP_LAUNCH, (Map<String, String>) hashMap);
            return;
        }
        Log.v(TAG, "trackAppLaunchEvent: Flurry Analytics is disabled.");
    }

    public static void trackPageViewCount() {
        if (!mFlurryAnalyticsEnabled) {
            Log.v(TAG, "trackPageViewCount: Flurry Analytics is disabled.");
        } else if (FlurryAgent.isSessionActive()) {
            Log.v(TAG, "Flurry Analytics: trackPageViewCount.");
            FlurryAgent.onPageView();
        } else {
            Log.v(TAG, "Flurry Analytics: trackPageViewCount - session not active.");
        }
    }

    public static void trackPageViewEvent(String str) {
        if (!mFlurryAnalyticsEnabled) {
            Log.v(TAG, "trackPageViewEvent: Flurry Analytics is disabled.");
        } else if (FlurryAgent.isSessionActive()) {
            HashMap hashMap = new HashMap();
            hashMap.put(PARAM_NAME_PACKAGE, BuildConfig.APPLICATION_ID);
            if (str != null && !str.equals("")) {
                hashMap.put(PARAM_NAME_ACTIVITY_TYPE, str);
            }
            Log.v(TAG, "Flurry Analytics: trackPageViewEvent - activityType: " + str);
            FlurryAgent.logEvent(EVENT_NAME_PAGE_VIEW, (Map<String, String>) hashMap);
        } else {
            Log.v(TAG, "Flurry Analytics: trackPageViewEvent - session not active.");
        }
    }

    public static void trackBannerAdEvent() {
        if (!mFlurryAnalyticsEnabled) {
            Log.v(TAG, "trackBannerAdEvent: Flurry Analytics is disabled.");
        } else if (FlurryAgent.isSessionActive()) {
            HashMap hashMap = new HashMap();
            hashMap.put(PARAM_NAME_PACKAGE, BuildConfig.APPLICATION_ID);
            Log.v(TAG, "Flurry Analytics: trackBannerAdEvent.");
            FlurryAgent.logEvent(EVENT_NAME_BANNER, (Map<String, String>) hashMap);
        } else {
            Log.v(TAG, "Flurry Analytics: trackBannerAdEvent - session not active.");
        }
    }

    public static void trackInterstitialAdEvent() {
        if (!mFlurryAnalyticsEnabled) {
            Log.v(TAG, "trackInterstitialAdEvent: Flurry Analytics is disabled.");
        } else if (FlurryAgent.isSessionActive()) {
            HashMap hashMap = new HashMap();
            hashMap.put(PARAM_NAME_PACKAGE, BuildConfig.APPLICATION_ID);
            Log.v(TAG, "Flurry Analytics: trackInterstitialAdEvent.");
            FlurryAgent.logEvent(EVENT_NAME_INTERSTITIAL, (Map<String, String>) hashMap);
        } else {
            Log.v(TAG, "Flurry Analytics: trackInterstitialAdEvent - session not active.");
        }
    }
}
