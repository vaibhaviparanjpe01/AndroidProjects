package com.newandromo.dev849565.app936843;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.newandromo.dev849565.app936843.AdHelper;

public class AdManager {
    private static final int MAX_AD_NOT_READY_ALLOWED = 3;
    private static final String TAG = "AdManager";
    private static Activity mActivity = null;
    private static AerServAppHelper mAerServAppHelper = new AerServAppHelper();
    private static final boolean mAndromoAdsEnabled = true;
    private static AndromoBannerChooser mAndromoBannerChooser = new AndromoBannerChooser();
    private static final boolean mAndromoInterstitialAdsEnabled = false;
    private static AndromoInterstitialChooser mAndromoInterstitialChooser = new AndromoInterstitialChooser();
    private static boolean mDelayFlurryEvents = false;
    private static boolean mDelayedAdLoading = false;
    private static InterstitialHelperBase mInterstitialHelper = null;
    private static boolean mInterstitialInitialized = false;
    private static int mNotReadyCounter = 0;
    private static boolean mPersonalizedAdsEnabled = mAndromoAdsEnabled;
    private static final int mUserAdPercentage = 0;
    private static final boolean mUserAdsEnabled = false;
    private static UserBannerChooser mUserBannerChooser = new UserBannerChooser();
    private static final int mUserInterstitialAdPercentage = 0;
    private static final boolean mUserInterstitialAdsEnabled = false;
    private static UserInterstitialChooser mUserInterstitialChooser = new UserInterstitialChooser();

    static boolean areInterstitialAdsEnabled() {
        return false;
    }

    private static InterstitialHelperBase getInterstitialHelper() {
        return null;
    }

    private static BannerHelperBase getUserOrAndromoBannerHelper() {
        return null;
    }

    private static InterstitialHelperBase getUserOrAndromoInterstitialHelper() {
        return null;
    }

    public static boolean isUserAdsEnabled() {
        return false;
    }

    private static boolean shouldWeShowUserAds() {
        return false;
    }

    private static boolean shouldWeShowUserInterstitialAds() {
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0015  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void showBannerAdStaticContainer(android.app.Activity r7) {
        /*
            if (r7 == 0) goto L_0x00b3
            com.newandromo.dev849565.app936843.AndromoBannerChooser r0 = mAndromoBannerChooser
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0012
            com.newandromo.dev849565.app936843.AndromoBannerChooser r0 = mAndromoBannerChooser
            int r0 = com.newandromo.dev849565.app936843.AndromoBannerChooser.countAdServices()
            if (r0 <= 0) goto L_0x0012
            r0 = 1
            goto L_0x0013
        L_0x0012:
            r0 = 0
        L_0x0013:
            if (r0 == 0) goto L_0x00b3
            boolean r0 = com.newandromo.dev849565.app936843.AndromoFlurryAnalytics.isFlurryAnalyticsEnabled()
            if (r0 == 0) goto L_0x0030
            boolean r0 = com.newandromo.dev849565.app936843.AndromoFlurryAnalytics.isFlurryAnalyticsSessionActive()
            if (r0 == 0) goto L_0x0027
            com.newandromo.dev849565.app936843.AndromoFlurryAnalytics.trackBannerAdEvent()
            mDelayFlurryEvents = r2
            goto L_0x0030
        L_0x0027:
            mDelayFlurryEvents = r1
            java.lang.String r0 = "AdManager"
            java.lang.String r3 = "Flurry Analytics - showBannerAdStaticContainer: Session not active yet - delaying event."
            android.util.Log.v(r0, r3)
        L_0x0030:
            setBannerContentAdLayoutHeight(r7)
            r0 = 2131296315(0x7f09003b, float:1.8210543E38)
            android.view.View r0 = r7.findViewById(r0)
            android.widget.FrameLayout r0 = (android.widget.FrameLayout) r0
            if (r0 == 0) goto L_0x0041
            r0.setVisibility(r2)
        L_0x0041:
            r0 = 2131296316(0x7f09003c, float:1.8210545E38)
            android.view.View r0 = r7.findViewById(r0)
            if (r0 == 0) goto L_0x004d
            r0.setVisibility(r2)
        L_0x004d:
            r0 = 2130968613(0x7f040025, float:1.7545885E38)
            java.lang.String r0 = com.newandromo.dev849565.app936843.ThemeUtils.resolveString(r7, r0)
            java.lang.String r3 = "AdManager"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "activity_background_image_url: '"
            r4.append(r5)
            r4.append(r0)
            java.lang.String r5 = "'"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.v(r3, r4)
            r3 = 2130968610(0x7f040022, float:1.7545879E38)
            int r3 = com.newandromo.dev849565.app936843.ThemeUtils.getResourceId(r7, r3, r2)
            java.lang.String r4 = "AdManager"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "activity_background_image_drawable: "
            r5.append(r6)
            r5.append(r3)
            java.lang.String r5 = r5.toString()
            android.util.Log.v(r4, r5)
            if (r0 == 0) goto L_0x0094
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0098
        L_0x0094:
            if (r3 == 0) goto L_0x0097
            goto L_0x0098
        L_0x0097:
            r1 = 0
        L_0x0098:
            if (r1 == 0) goto L_0x00b3
            r0 = 2131296317(0x7f09003d, float:1.8210547E38)
            android.view.View r7 = r7.findViewById(r0)
            if (r7 == 0) goto L_0x00b3
            r0 = -452984832(0xffffffffe5000000, float:-3.7778932E22)
            r1 = 12
            r3 = 80
            android.graphics.drawable.Drawable r0 = com.newandromo.dev849565.app936843.ScrimUtil.makeCubicGradientScrimDrawable(r0, r1, r3)
            r7.setBackground(r0)
            r7.setVisibility(r2)
        L_0x00b3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newandromo.dev849565.app936843.AdManager.showBannerAdStaticContainer(android.app.Activity):void");
    }

    public static LinearLayout getStaticContentAdLayout(Activity activity) {
        if (activity != null) {
            return (LinearLayout) activity.findViewById(R.id.contentAdLayout);
        }
        return null;
    }

    public static int getStaticContentAdLayoutWidthDP(LinearLayout linearLayout) {
        DisplayMetrics displayMetrics;
        if (linearLayout == null || (displayMetrics = linearLayout.getResources().getDisplayMetrics()) == null) {
            return 0;
        }
        return Math.round(((float) displayMetrics.widthPixels) / displayMetrics.density);
    }

    public static int getStaticContentAdLayoutWidthPixels(LinearLayout linearLayout) {
        DisplayMetrics displayMetrics;
        if (linearLayout == null || (displayMetrics = linearLayout.getResources().getDisplayMetrics()) == null) {
            return 0;
        }
        return displayMetrics.widthPixels;
    }

    public static int getStaticContentAdLayoutHeightDP(LinearLayout linearLayout) {
        DisplayMetrics displayMetrics;
        if (linearLayout == null || (displayMetrics = linearLayout.getResources().getDisplayMetrics()) == null) {
            return 0;
        }
        return Math.round(((float) linearLayout.getLayoutParams().height) / displayMetrics.density);
    }

    public static int getStaticContentAdLayoutHeightPixels(LinearLayout linearLayout) {
        if (linearLayout == null || linearLayout.getResources().getDisplayMetrics() == null) {
            return 0;
        }
        return linearLayout.getLayoutParams().height;
    }

    public static void setBannerContentAdLayoutHeight(Activity activity) {
        LinearLayout linearLayout = (LinearLayout) activity.findViewById(R.id.contentAdLayout);
        if (linearLayout != null) {
            Log.v(TAG, "setBannerContentAdLayoutHeight - Setting height for contentAdLayout inside bannerAdStaticContainer.");
            DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
            if (displayMetrics != null) {
                int round = Math.round(((float) displayMetrics.heightPixels) / displayMetrics.density);
                Log.v(TAG, "AdManager - screenHeightDP is: " + round);
                int round2 = Math.round(((float) displayMetrics.widthPixels) / displayMetrics.density);
                Log.v(TAG, "AdManager - screenWidthDP is: " + round2);
                if (round > 720) {
                    Log.v(TAG, "AdManager - screenHeightDP is greater than 720, setting ad container height to 90dp.");
                    ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
                    layoutParams.height = Math.round(displayMetrics.density * 90.0f);
                    Log.v(TAG, "AdManager - 90dp height in pixels is " + layoutParams.height);
                    linearLayout.setLayoutParams(layoutParams);
                    FrameLayout frameLayout = (FrameLayout) activity.findViewById(R.id.bannerAdStaticContainer);
                    return;
                }
                Log.v(TAG, "AdManager - screenHeightDP is less than 720, use existing 50dp ad container height.");
                return;
            }
            Log.v(TAG, "AdManager - Display metrics returned null.");
        }
    }

    public static boolean insertBannerAd(Activity activity, ViewGroup viewGroup) {
        UserBannerChooser userBannerChooser = mUserBannerChooser;
        UserBannerChooser.setSuppressAdMob(false);
        return insertBannerAd(activity, viewGroup, AdHelper.SlideDirection.NONE);
    }

    public static boolean insertBannerAd(Activity activity, ViewGroup viewGroup, boolean z) {
        UserBannerChooser userBannerChooser = mUserBannerChooser;
        UserBannerChooser.setSuppressAdMob(false);
        return insertBannerAd(activity, viewGroup, z ? AdHelper.SlideDirection.DOWN : AdHelper.SlideDirection.NONE);
    }

    public static boolean insertBannerAd(Activity activity, ViewGroup viewGroup, AdHelper.SlideDirection slideDirection) {
        if (!mDelayedAdLoading) {
            UserBannerChooser userBannerChooser = mUserBannerChooser;
            UserBannerChooser.setSuppressAdMob(false);
            BannerHelperBase bannerHelper = getBannerHelper();
            if (bannerHelper != null) {
                showBannerAdStaticContainer(activity);
                return bannerHelper.insertAd(activity, viewGroup, slideDirection);
            }
        } else {
            showBannerAdStaticContainer(activity);
        }
        return false;
    }

    public static boolean insertBannerAd(Activity activity, ViewGroup viewGroup, AdHelper.SlideDirection slideDirection, boolean z) {
        if (!mDelayedAdLoading) {
            UserBannerChooser userBannerChooser = mUserBannerChooser;
            UserBannerChooser.setSuppressAdMob(z);
            BannerHelperBase bannerHelper = getBannerHelper();
            if (bannerHelper == null) {
                return false;
            }
            showBannerAdStaticContainer(activity);
            return bannerHelper.insertAd(activity, viewGroup, slideDirection);
        }
        showBannerAdStaticContainer(activity);
        return false;
    }

    public static View getBannerAdViewHolder(LayoutInflater layoutInflater) {
        BannerHelperBase bannerHelper = getBannerHelper();
        if (bannerHelper != null) {
            return bannerHelper.getAdViewHolder(layoutInflater);
        }
        return null;
    }

    public static boolean showBannerAdInViewStub(Activity activity) {
        BannerHelperBase bannerHelper = getBannerHelper();
        if (bannerHelper != null) {
            return bannerHelper.showAdInViewStub(activity);
        }
        return false;
    }

    static void onApplicationCreated(Context context) {
        Log.v(TAG, "onApplicationCreated()");
        UserInterstitialChooser userInterstitialChooser = mUserInterstitialChooser;
        UserInterstitialChooser.onApplicationCreated(context);
        AndromoInterstitialChooser andromoInterstitialChooser = mAndromoInterstitialChooser;
        AndromoInterstitialChooser.onApplicationCreated(context);
        UserBannerChooser userBannerChooser = mUserBannerChooser;
        UserBannerChooser.onApplicationCreated(context);
        AndromoBannerChooser andromoBannerChooser = mAndromoBannerChooser;
        AndromoBannerChooser.onApplicationCreated(context);
        AdMobAppHelper.initializeApp(context);
        TapcoreHelper.initializeTapcore(context);
    }

    static void onActivityCreated(Activity activity) {
        Log.v(TAG, "onActivityCreated: " + activity);
        if (!mDelayedAdLoading) {
            AerServAppHelper aerServAppHelper = mAerServAppHelper;
            AerServAppHelper.initializeApp(activity);
            Log.v(TAG, "Setting mActivity: " + activity + ", was: " + mActivity);
            mActivity = activity;
            initializeInterstitial(activity);
            return;
        }
        Log.v(TAG, "onActivityCreated: Delayed ad loading is enabled, do nothing");
    }

    static void onActivityStarted(Activity activity) {
        Log.v(TAG, "onActivityStarted: " + activity);
        if (!mDelayedAdLoading) {
            AndromoBannerChooser andromoBannerChooser = mAndromoBannerChooser;
            AndromoBannerChooser.onActivityStarted(activity);
            UserBannerChooser userBannerChooser = mUserBannerChooser;
            UserBannerChooser.onActivityStarted(activity);
            StringBuilder sb = new StringBuilder();
            sb.append("activity ");
            sb.append(activity == mActivity ? "matches mActivity" : "does not match mActivity");
            Log.v(TAG, sb.toString());
            Log.v(TAG, "mInterstitialInitialized: " + mInterstitialInitialized);
            if (activity != mActivity || !mInterstitialInitialized) {
                Log.v(TAG, "Setting mActivity: " + activity + ", was: " + mActivity);
                mActivity = activity;
                initializeInterstitial(activity);
            }
            if (mActivity == activity && mInterstitialHelper != null) {
                mInterstitialHelper.onActivityStarted(activity);
                return;
            }
            return;
        }
        Log.v(TAG, "onActivityStarted: Delayed ad loading is enabled, do nothing");
    }

    static void onActivityStopped(Activity activity) {
        Log.v(TAG, "onActivityStopped: " + activity);
        AndromoBannerChooser andromoBannerChooser = mAndromoBannerChooser;
        AndromoBannerChooser.onActivityStopped(activity);
        UserBannerChooser userBannerChooser = mUserBannerChooser;
        UserBannerChooser.onActivityStopped(activity);
        if (mActivity == activity && mInterstitialHelper != null) {
            mInterstitialHelper.onActivityStopped(activity);
        }
    }

    static void onActivityPaused(Activity activity) {
        Log.v(TAG, "onActivityPaused: " + activity);
        if (mDelayFlurryEvents && AndromoFlurryAnalytics.isFlurryAnalyticsEnabled()) {
            if (AndromoFlurryAnalytics.isFlurryAnalyticsSessionActive()) {
                AndromoFlurryAnalytics.trackBannerAdEvent();
            } else {
                Log.v(TAG, "Flurry Analytics - onActivityPaused: Session still not active, ignoring event.");
            }
            mDelayFlurryEvents = false;
        }
        AndromoBannerChooser andromoBannerChooser = mAndromoBannerChooser;
        AndromoBannerChooser.onActivityPaused(activity);
        UserBannerChooser userBannerChooser = mUserBannerChooser;
        UserBannerChooser.onActivityPaused(activity);
        if (mActivity == activity && mInterstitialHelper != null) {
            mInterstitialHelper.onActivityPaused(activity);
        }
    }

    static void onActivityResumed(Activity activity) {
        Log.v(TAG, "onActivityResumed: " + activity);
        AndromoBannerChooser andromoBannerChooser = mAndromoBannerChooser;
        AndromoBannerChooser.onActivityResumed(activity);
        UserBannerChooser userBannerChooser = mUserBannerChooser;
        UserBannerChooser.onActivityResumed(activity);
        if (mActivity == activity && mInterstitialHelper != null) {
            mInterstitialHelper.onActivityResumed(activity);
        }
    }

    static void onActivityDestroyed(Activity activity) {
        Log.v(TAG, "onActivityDestroyed: " + activity);
        AndromoBannerChooser andromoBannerChooser = mAndromoBannerChooser;
        AndromoBannerChooser.onActivityDestroyed(activity);
        UserBannerChooser userBannerChooser = mUserBannerChooser;
        UserBannerChooser.onActivityDestroyed(activity);
        if (mActivity == activity) {
            if (mInterstitialHelper != null) {
                mInterstitialHelper.onActivityDestroyed(activity);
            }
            Log.v(TAG, "Setting mActivity: null, was: " + mActivity);
            mActivity = null;
        }
    }

    static void onActivityBackPressed(Activity activity) {
        Log.v(TAG, "onActivityBackPressed: " + activity);
        if (mActivity == activity && mInterstitialHelper != null) {
            mInterstitialHelper.onActivityBackPressed(activity);
        }
        if (mInterstitialHelper != null) {
            Log.v(TAG, "onActivityBackPressed - mInterstitialHelper not null, calling destroyInterstitial.");
            mInterstitialHelper.destroyInterstitial();
            Log.v(TAG, "onActivityBackPressed - Setting mInterstitialHelper to null.");
            mInterstitialHelper = null;
            mInterstitialInitialized = false;
        }
    }

    private static BannerHelperBase getUserBannerHelper() {
        return mUserBannerChooser.getBannerHelper();
    }

    private static BannerHelperBase getAndromoBannerHelper() {
        return mAndromoBannerChooser.getBannerHelper();
    }

    private static BannerHelperBase getBannerHelper() {
        BannerHelperBase andromoBannerHelper = getAndromoBannerHelper();
        AndromoAnalytics.getInstance().sendEvent("Banner Ad Opportunity", BuildConfig.APPLICATION_ID, "Andromo", (Long) null);
        return andromoBannerHelper;
    }

    static void selectNextInterstitialNetwork() {
        Log.v(TAG, "selectNextInterstitialNetwork()");
        if (mInterstitialHelper != null) {
            Log.v(TAG, "selectNextInterstitialNetwork - mInterstitialHelper not null, calling destroyInterstitial.");
            mInterstitialHelper.destroyInterstitial();
            mInterstitialHelper = null;
        }
        mNotReadyCounter = 0;
        mInterstitialInitialized = false;
        mInterstitialHelper = getInterstitialHelper();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECTED: ");
        sb.append(mInterstitialHelper != null ? mInterstitialHelper.getClass().getSimpleName() : "(null)");
        Log.v(TAG, sb.toString());
        if (mInterstitialHelper != null && !mInterstitialHelper.shouldShowAds()) {
            Log.e(TAG, "Um, interstitial helper's shouldShowAds() returned false. WTF?");
            mInterstitialInitialized = false;
            mInterstitialHelper = null;
        }
    }

    public static boolean isTimeToInitializeInterstitial(Context context) {
        return InterstitialHelperBase.isTimeToInitialize(context);
    }

    public static boolean isTimeToShowInterstitial(Context context) {
        if (!InterstitialHelperBase.hasEnoughTimePassed(context) || !InterstitialHelperBase.haveEnoughEventsPassed(context)) {
            return false;
        }
        return mAndromoAdsEnabled;
    }

    public static void resetForNextInterstitialDelay(Context context) {
        InterstitialHelperBase.updateTimestamp(context);
        InterstitialHelperBase.resetEventCount(context);
    }

    public static void initializeInterstitial(Activity activity) {
        Log.v(TAG, "initializeInterstitial()");
        mInterstitialInitialized = false;
        if (mInterstitialHelper == null) {
            Log.w(TAG, "initializeInterstitial called without any ad network selected, calling selectNextInterstitialNetwork()...");
            selectNextInterstitialNetwork();
        }
        if (mInterstitialHelper == null) {
            Log.e(TAG, "No ad network to initialize");
        } else if (!isTimeToInitializeInterstitial(activity)) {
            Log.v(TAG, "Not time to initialize yet.");
        } else if (mInterstitialHelper.isReadyToInitializeInterstitial(activity)) {
            Log.v(TAG, "Ready to initialize, calling " + mInterstitialHelper.getClass().getSimpleName() + ".initializeInterstitial()");
            boolean initializeInterstitial = mInterstitialHelper.initializeInterstitial(activity);
            if (!initializeInterstitial) {
                Log.w(TAG, "Initialize failed, calling selectNextInterstitialNetwork");
                selectNextInterstitialNetwork();
                if (mInterstitialHelper != null) {
                    Log.v(TAG, "Second time's the charm, calling " + mInterstitialHelper.getClass().getSimpleName() + ".initializeInterstitial()");
                    initializeInterstitial = mInterstitialHelper.initializeInterstitial(activity);
                    if (!initializeInterstitial) {
                        Log.e(TAG, "Second initialize failed, giving up.");
                    }
                }
            }
            mInterstitialInitialized = initializeInterstitial;
        } else {
            Log.v(TAG, "Not ready to initialize yet.");
        }
    }

    public static boolean showInterstitialAd(Activity activity) {
        if (mInterstitialHelper == null || !mInterstitialInitialized) {
            Log.w(TAG, "showInterstitialAd called without any ad network selected, calling initializeInterstitial() to select one and initialize it...");
            initializeInterstitial(activity);
        }
        boolean z = false;
        if (!AdReporter.getInstance().canShowAds()) {
            Log.d(TAG, "Can not show ads AdReporter");
            return false;
        }
        if (mInterstitialHelper == null || !mInterstitialInitialized) {
            Log.e(TAG, "Can't show interstitial ad, mInterstitialHelper is null!");
        } else if (isTimeToShowInterstitial(activity)) {
            AndromoFlurryAnalytics.trackInterstitialAdEvent();
            if (mInterstitialHelper.isReadyToShowInterstitial(activity)) {
                Log.v(TAG, "Ready to show, calling " + mInterstitialHelper.getClass().getSimpleName() + ".showInterstitial()");
                InterstitialHelperBase.resetForNextInterstitialDelay(activity);
                mInterstitialHelper.getNameForReport();
                z = mInterstitialHelper.showInterstitial(activity);
            } else {
                Log.v(TAG, "Not ready to show an ad yet. mNotReadyCounter = " + mNotReadyCounter);
                int i = mNotReadyCounter + 1;
                mNotReadyCounter = i;
                if (i > 3) {
                    Log.w(TAG, "Too many not readies in a row, switching to another ad network.");
                    selectNextInterstitialNetwork();
                }
            }
        } else {
            Log.v(TAG, "Not time to show an ad yet.");
        }
        if (z) {
            AdReporter.getInstance().reportImpression("admob");
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00bb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean showInterstitialAndRun(android.app.Activity r3, java.lang.Runnable r4) {
        /*
            java.lang.String r0 = ""
            com.newandromo.dev849565.app936843.InterstitialHelperBase r1 = mInterstitialHelper
            if (r1 == 0) goto L_0x000a
            boolean r1 = mInterstitialInitialized
            if (r1 != 0) goto L_0x0014
        L_0x000a:
            java.lang.String r1 = "AdManager"
            java.lang.String r2 = "showInterstitialAd called without any ad network selected, calling initializeInterstitial() to select one and initialize it..."
            android.util.Log.w(r1, r2)
            initializeInterstitial(r3)
        L_0x0014:
            com.newandromo.dev849565.app936843.InterstitialHelperBase r1 = mInterstitialHelper
            if (r1 == 0) goto L_0x00aa
            boolean r1 = mInterstitialInitialized
            if (r1 == 0) goto L_0x00aa
            boolean r1 = isTimeToShowInterstitial(r3)
            if (r1 == 0) goto L_0x00a2
            com.newandromo.dev849565.app936843.AndromoFlurryAnalytics.trackInterstitialAdEvent()
            com.newandromo.dev849565.app936843.AdReporter r1 = com.newandromo.dev849565.app936843.AdReporter.getInstance()
            boolean r1 = r1.canShowAds()
            if (r1 != 0) goto L_0x0038
            java.lang.String r3 = "AdManager"
            java.lang.String r1 = "Can not show ads AdReporter"
            android.util.Log.d(r3, r1)
            goto L_0x00b1
        L_0x0038:
            com.newandromo.dev849565.app936843.InterstitialHelperBase r1 = mInterstitialHelper
            boolean r1 = r1.isReadyToShowInterstitial(r3)
            if (r1 == 0) goto L_0x0076
            java.lang.String r0 = "AdManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Ready to show, calling "
            r1.append(r2)
            com.newandromo.dev849565.app936843.InterstitialHelperBase r2 = mInterstitialHelper
            java.lang.Class r2 = r2.getClass()
            java.lang.String r2 = r2.getSimpleName()
            r1.append(r2)
            java.lang.String r2 = ".showInterstitial()"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.v(r0, r1)
            com.newandromo.dev849565.app936843.InterstitialHelperBase.resetForNextInterstitialDelay(r3)
            com.newandromo.dev849565.app936843.InterstitialHelperBase r0 = mInterstitialHelper
            java.lang.String r0 = r0.getNameForReport()
            com.newandromo.dev849565.app936843.InterstitialHelperBase r1 = mInterstitialHelper
            boolean r3 = r1.showInterstitialAndRun(r3, r4)
            r4 = 0
            goto L_0x00b2
        L_0x0076:
            java.lang.String r3 = "AdManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Not ready to show an ad yet. mNotReadyCounter = "
            r1.append(r2)
            int r2 = mNotReadyCounter
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.v(r3, r1)
            int r3 = mNotReadyCounter
            int r3 = r3 + 1
            mNotReadyCounter = r3
            r1 = 3
            if (r3 <= r1) goto L_0x00b1
            java.lang.String r3 = "AdManager"
            java.lang.String r1 = "Too many not readies in a row, switching to another ad network."
            android.util.Log.w(r3, r1)
            selectNextInterstitialNetwork()
            goto L_0x00b1
        L_0x00a2:
            java.lang.String r3 = "AdManager"
            java.lang.String r1 = "Not time to show an ad yet."
            android.util.Log.v(r3, r1)
            goto L_0x00b1
        L_0x00aa:
            java.lang.String r3 = "AdManager"
            java.lang.String r1 = "Can't show interstitial ad, mInterstitialHelper is null!"
            android.util.Log.e(r3, r1)
        L_0x00b1:
            r3 = 0
        L_0x00b2:
            if (r3 != 0) goto L_0x00b9
            if (r4 == 0) goto L_0x00b9
            r4.run()
        L_0x00b9:
            if (r3 == 0) goto L_0x00c2
            com.newandromo.dev849565.app936843.AdReporter r4 = com.newandromo.dev849565.app936843.AdReporter.getInstance()
            r4.reportImpression(r0)
        L_0x00c2:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newandromo.dev849565.app936843.AdManager.showInterstitialAndRun(android.app.Activity, java.lang.Runnable):boolean");
    }

    public static void hideInterstitialAd() {
        if (mInterstitialHelper != null) {
            Log.v(TAG, "Calling " + mInterstitialHelper.getClass().getSimpleName() + ".hideInterstitial()");
            mInterstitialHelper.hideInterstitial();
            return;
        }
        Log.v(TAG, "Can't call hideInterstitial(), mInterstitialHelper is null.");
    }

    private static InterstitialHelperBase getUserInterstitialHelper() {
        UserInterstitialChooser userInterstitialChooser = mUserInterstitialChooser;
        return UserInterstitialChooser.getInterstitialHelper();
    }

    private static InterstitialHelperBase getAndromoInterstitialHelper() {
        AndromoInterstitialChooser andromoInterstitialChooser = mAndromoInterstitialChooser;
        return AndromoInterstitialChooser.getInterstitialHelper();
    }

    static void setPersonalizedAdsConsent(Context context, boolean z) {
        Log.v(TAG, "setPersonalizedAdsConsent: consentProvided - " + z);
        setPersonalizedAdsEnabled(z);
        Context appContext = AndromoApplication.getAppContext();
        if (mUserInterstitialChooser.isStartAppEnabled()) {
            StartAppInterstitialHelper.setPersonalizedAdsConsent(appContext, z);
        } else if (mUserBannerChooser.isStartAppEnabled()) {
            StartAppHelper.setPersonalizedAdsConsent(appContext, z);
        }
        if (mUserInterstitialChooser.isAppLovinEnabled()) {
            AppLovinInterstitialHelper.setPersonalizedAdsConsent(appContext, z);
        }
    }

    static void setDisabledNetworksForGDPR(boolean z) {
        if (z) {
            Log.v(TAG, "setDisabledNetworksForGDPR: Is an EU country, disabling networks.");
            mUserBannerChooser.disableNetworksForGDPR();
            mUserInterstitialChooser.disableNetworksForGDPR();
            return;
        }
        Log.v(TAG, "setDisabledNetworksForGDPR: Not an EU country, don't need to disable.");
    }

    public static void setPersonalizedAdsEnabled(boolean z) {
        Log.v(TAG, "setPersonalizedAdsEnabled: Setting to " + z);
        mPersonalizedAdsEnabled = z;
    }

    public static boolean isPersonalizedAdsEnabled() {
        Log.v(TAG, "isPersonalizedAdsEnabled is: " + mPersonalizedAdsEnabled);
        return mPersonalizedAdsEnabled;
    }

    public static void setDelayedAdLoading(boolean z) {
        Log.v(TAG, "setDelayedAdLoading: Setting to " + z);
        mDelayedAdLoading = z;
    }

    public static boolean isDelayedAdLoadingEnabled() {
        Log.v(TAG, "isDelayedAdLoadingEnabled is: " + mDelayedAdLoading);
        return mDelayedAdLoading;
    }
}
