package com.newandromo.dev849565.app936843;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

public class AndromoBannerChooser implements AdFallbackHandler {
    private static final int MAX_FALLBACK_INDEX = Math.max(countAdServices(), 9);
    private static final String TAG = "AndromoBannerChooser";
    private static int mAdMobCutOff = -1;
    private static boolean mAdMobEnabled = false;
    private static AdMobHelper mAdMobHelper = null;
    private static int mAdMobPercentage = -1;
    private static final boolean mAdsEnabled = ((mAdMobEnabled || mAerServEnabled || mFacebookEnabled || mMillennialMediaEnabled || mJavascriptAdEnabled || mLeadBoltEnabled || mAmazonEnabled || mStartAppEnabled || mAirpushEnabled || mAirpush360Enabled || mStaticAdEnabled) ? mIsAndromoAd : false);
    private static int mAerServCutOff = -1;
    private static boolean mAerServEnabled = false;
    private static AerServHelper mAerServHelper = null;
    private static int mAerServPercentage = -1;
    private static int mAirpush360CutOff = -1;
    private static boolean mAirpush360Enabled = false;
    private static Airpush360Helper mAirpush360Helper = null;
    private static int mAirpush360Percentage = -1;
    private static int mAirpushCutOff = -1;
    private static boolean mAirpushEnabled = false;
    private static AirpushHelper mAirpushHelper = null;
    private static int mAirpushPercentage = -1;
    private static int mAmazonCutOff = -1;
    private static boolean mAmazonEnabled = false;
    private static AmazonHelper mAmazonHelper = null;
    private static int mAmazonPercentage = -1;
    private static int mCurrentFallbackIndex = 0;
    private static boolean mCutoffsInitialized = false;
    private static FacebookBannerHelper mFacebookBannerHelper = null;
    private static int mFacebookCutOff = -1;
    private static boolean mFacebookEnabled = false;
    private static int mFacebookPercentage = -1;
    private static final boolean mIsAndromoAd = true;
    private static int mJavascriptAdCutOff = -1;
    private static boolean mJavascriptAdEnabled = false;
    private static JavascriptAdHelper mJavascriptAdHelper = null;
    private static int mJavascriptAdPercentage = -1;
    private static int mLeadBoltCutOff = -1;
    private static boolean mLeadBoltEnabled = false;
    private static LeadBoltBannerHelper mLeadBoltHelper = null;
    private static int mLeadBoltPercentage = -1;
    private static int mMillennialMediaCutOff = -1;
    private static boolean mMillennialMediaEnabled = false;
    private static MillennialMediaHelper mMillennialMediaHelper = null;
    private static int mMillennialMediaPercentage = -1;
    private static int mStartAppCutOff = -1;
    private static boolean mStartAppEnabled = false;
    private static StartAppHelper mStartAppHelper = null;
    private static int mStartAppPercentage = -1;
    private static int mStaticAdCutOff = -1;
    private static boolean mStaticAdEnabled = true;
    private static StaticAdHelper mStaticAdHelper;
    private static int mStaticAdPercentage = 100;
    private static boolean mSuppressAdMob;

    static void setSuppressAdMob(boolean z) {
        if (z != mSuppressAdMob) {
            resetCutOffs();
        }
        mSuppressAdMob = z;
    }

    private static void resetCutOffs() {
        mCutoffsInitialized = false;
        mAdMobCutOff = -1;
        mAerServCutOff = -1;
        mFacebookCutOff = -1;
        mMillennialMediaCutOff = -1;
        mJavascriptAdCutOff = -1;
        mLeadBoltCutOff = -1;
        mAmazonCutOff = -1;
        mStartAppCutOff = -1;
        mAirpushCutOff = -1;
        mAirpush360CutOff = -1;
        mStaticAdCutOff = -1;
    }

    private BannerHelperBase getNextFallbackNetwork() {
        BannerHelperBase bannerHelperBase;
        Log.v(TAG, "getNextFallbackNetwork - mCurrentFallbackIndex is: " + mCurrentFallbackIndex);
        Log.v(TAG, "getNextFallbackNetwork starting from: " + (mCurrentFallbackIndex + 1));
        int i = mCurrentFallbackIndex + 1;
        mCurrentFallbackIndex = i;
        while (true) {
            if (i <= MAX_FALLBACK_INDEX) {
                if (!mAdMobEnabled || mSuppressAdMob || i != -1) {
                    if (!mAerServEnabled || i != 1) {
                        if (!mFacebookEnabled || i != -1) {
                            if (!mMillennialMediaEnabled || i != -1) {
                                if (!mJavascriptAdEnabled || i != -1) {
                                    if (!mLeadBoltEnabled || i != -1) {
                                        if (!mAmazonEnabled || i != -1) {
                                            if (!mStartAppEnabled || i != -1) {
                                                if (!mAirpushEnabled || i != -1) {
                                                    if (!mAirpush360Enabled || i != -1) {
                                                        if (mStaticAdEnabled && i == 5) {
                                                            mCurrentFallbackIndex = i;
                                                            bannerHelperBase = getStaticAdHelper();
                                                            Log.v(TAG, "chose Static Ad, index: " + i);
                                                            break;
                                                        }
                                                        i++;
                                                    } else {
                                                        mCurrentFallbackIndex = i;
                                                        bannerHelperBase = getAirpush360Helper();
                                                        Log.v(TAG, "chose Airpush 360, index: " + i);
                                                        break;
                                                    }
                                                } else {
                                                    mCurrentFallbackIndex = i;
                                                    bannerHelperBase = getAirpushHelper();
                                                    Log.v(TAG, "chose Airpush, index: " + i);
                                                    break;
                                                }
                                            } else {
                                                mCurrentFallbackIndex = i;
                                                bannerHelperBase = getStartAppHelper();
                                                Log.v(TAG, "chose StartApp, index: " + i);
                                                break;
                                            }
                                        } else {
                                            mCurrentFallbackIndex = i;
                                            bannerHelperBase = getAmazonHelper();
                                            Log.v(TAG, "chose amazon, index: " + i);
                                            break;
                                        }
                                    } else {
                                        mCurrentFallbackIndex = i;
                                        bannerHelperBase = getLeadBoltHelper();
                                        Log.v(TAG, "chose LeadBolt, index: " + i);
                                        break;
                                    }
                                } else {
                                    mCurrentFallbackIndex = i;
                                    bannerHelperBase = getJavascriptAdHelper();
                                    Log.v(TAG, "chose JavascriptAd, index: " + i);
                                    break;
                                }
                            } else {
                                mCurrentFallbackIndex = i;
                                bannerHelperBase = getMillennialMediaHelper();
                                Log.v(TAG, "chose Millennial Media, index: " + i);
                                break;
                            }
                        } else {
                            mCurrentFallbackIndex = i;
                            bannerHelperBase = getFacebookBannerHelper();
                            Log.v(TAG, "chose Facebook Audience Network, index: " + i);
                            break;
                        }
                    } else {
                        mCurrentFallbackIndex = i;
                        bannerHelperBase = getAerServHelper();
                        Log.v(TAG, "chose AerServ, index: " + i);
                        break;
                    }
                } else {
                    mCurrentFallbackIndex = i;
                    bannerHelperBase = getAdMobHelper();
                    Log.v(TAG, "chose AdMob, index: " + i);
                    break;
                }
            } else {
                bannerHelperBase = null;
                break;
            }
        }
        if (bannerHelperBase != null) {
            bannerHelperBase.setAdFallbackHandler(this);
            bannerHelperBase.setAndromoAd(mIsAndromoAd);
        }
        return bannerHelperBase;
    }

    public void resetAdFallbacks() {
        Log.v(TAG, "resetting mCurrentFallbackIndex from " + mCurrentFallbackIndex + " to 0");
        mCurrentFallbackIndex = 0;
    }

    public void replaceAdWithFallback(Activity activity, View view) {
        Log.v(TAG, "replaceAdWithFallback()");
        if (view != null) {
            BannerHelperBase nextFallbackNetwork = getNextFallbackNetwork();
            if (nextFallbackNetwork != null) {
                Log.v(TAG, "got next fallback network");
                View replaceView = AdHelper.replaceView(view, nextFallbackNetwork.getAdResourceID());
                if (replaceView != null) {
                    Log.v(TAG, "got new ad view, initializing it...");
                    nextFallbackNetwork.initializeAdView(activity, replaceView);
                    return;
                }
                Log.v(TAG, "no new ad view, hey dude null's not cool ok?");
                return;
            }
            Log.v(TAG, "no more ad networks to fallback to");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:61:0x0102  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.newandromo.dev849565.app936843.BannerHelperBase getBannerHelper() {
        /*
            r3 = this;
            boolean r0 = mAdsEnabled
            r1 = 0
            if (r0 == 0) goto L_0x010d
            java.lang.String r0 = "AndromoBannerChooser"
            java.lang.String r2 = "getBannerHelper"
            android.util.Log.v(r0, r2)
            java.util.Random r0 = new java.util.Random
            r0.<init>()
            r2 = 100
            int r0 = r0.nextInt(r2)
            initAdPercentages()
            boolean r2 = mAdMobEnabled
            if (r2 == 0) goto L_0x0034
            boolean r2 = mSuppressAdMob
            if (r2 != 0) goto L_0x0034
            int r2 = mAdMobCutOff
            if (r0 >= r2) goto L_0x0034
            java.lang.String r0 = "AndromoBannerChooser"
            java.lang.String r1 = "ADMOB chosen"
            android.util.Log.v(r0, r1)
            com.newandromo.dev849565.app936843.BannerHelperBase r0 = getAdMobHelper()
        L_0x0031:
            r1 = r0
            goto L_0x0100
        L_0x0034:
            boolean r2 = mAerServEnabled
            if (r2 == 0) goto L_0x0048
            int r2 = mAerServCutOff
            if (r0 >= r2) goto L_0x0048
            java.lang.String r0 = "AndromoBannerChooser"
            java.lang.String r1 = "AERSERV chosen"
            android.util.Log.v(r0, r1)
            com.newandromo.dev849565.app936843.BannerHelperBase r0 = getAerServHelper()
            goto L_0x0031
        L_0x0048:
            boolean r2 = mFacebookEnabled
            if (r2 == 0) goto L_0x005c
            int r2 = mFacebookCutOff
            if (r0 >= r2) goto L_0x005c
            java.lang.String r0 = "AndromoBannerChooser"
            java.lang.String r1 = "FACEBOOK AUDIENCE NETWORK chosen"
            android.util.Log.v(r0, r1)
            com.newandromo.dev849565.app936843.BannerHelperBase r0 = getFacebookBannerHelper()
            goto L_0x0031
        L_0x005c:
            boolean r2 = mMillennialMediaEnabled
            if (r2 == 0) goto L_0x0070
            int r2 = mMillennialMediaCutOff
            if (r0 >= r2) goto L_0x0070
            java.lang.String r0 = "AndromoBannerChooser"
            java.lang.String r1 = "MILLENNIAL MEDIA chosen"
            android.util.Log.v(r0, r1)
            com.newandromo.dev849565.app936843.BannerHelperBase r0 = getMillennialMediaHelper()
            goto L_0x0031
        L_0x0070:
            boolean r2 = mJavascriptAdEnabled
            if (r2 == 0) goto L_0x0084
            int r2 = mJavascriptAdCutOff
            if (r0 >= r2) goto L_0x0084
            java.lang.String r0 = "AndromoBannerChooser"
            java.lang.String r1 = "JAVASCRIPT AD chosen"
            android.util.Log.v(r0, r1)
            com.newandromo.dev849565.app936843.BannerHelperBase r0 = getJavascriptAdHelper()
            goto L_0x0031
        L_0x0084:
            boolean r2 = mLeadBoltEnabled
            if (r2 == 0) goto L_0x0098
            int r2 = mLeadBoltCutOff
            if (r0 >= r2) goto L_0x0098
            java.lang.String r0 = "AndromoBannerChooser"
            java.lang.String r1 = "LEADBOLT chosen"
            android.util.Log.v(r0, r1)
            com.newandromo.dev849565.app936843.BannerHelperBase r0 = getLeadBoltHelper()
            goto L_0x0031
        L_0x0098:
            boolean r2 = mAmazonEnabled
            if (r2 == 0) goto L_0x00ac
            int r2 = mAmazonCutOff
            if (r0 >= r2) goto L_0x00ac
            java.lang.String r0 = "AndromoBannerChooser"
            java.lang.String r1 = "AMAZON chosen"
            android.util.Log.v(r0, r1)
            com.newandromo.dev849565.app936843.BannerHelperBase r0 = getAmazonHelper()
            goto L_0x0031
        L_0x00ac:
            boolean r2 = mStartAppEnabled
            if (r2 == 0) goto L_0x00c1
            int r2 = mStartAppCutOff
            if (r0 >= r2) goto L_0x00c1
            java.lang.String r0 = "AndromoBannerChooser"
            java.lang.String r1 = "StartApp chosen"
            android.util.Log.v(r0, r1)
            com.newandromo.dev849565.app936843.BannerHelperBase r0 = getStartAppHelper()
            goto L_0x0031
        L_0x00c1:
            boolean r2 = mAirpushEnabled
            if (r2 == 0) goto L_0x00d6
            int r2 = mAirpushCutOff
            if (r0 >= r2) goto L_0x00d6
            java.lang.String r0 = "AndromoBannerChooser"
            java.lang.String r1 = "Airpush chosen"
            android.util.Log.v(r0, r1)
            com.newandromo.dev849565.app936843.BannerHelperBase r0 = getAirpushHelper()
            goto L_0x0031
        L_0x00d6:
            boolean r2 = mAirpush360Enabled
            if (r2 == 0) goto L_0x00eb
            int r2 = mAirpush360CutOff
            if (r0 >= r2) goto L_0x00eb
            java.lang.String r0 = "AndromoBannerChooser"
            java.lang.String r1 = "Airpush 360 chosen"
            android.util.Log.v(r0, r1)
            com.newandromo.dev849565.app936843.BannerHelperBase r0 = getAirpush360Helper()
            goto L_0x0031
        L_0x00eb:
            boolean r2 = mStaticAdEnabled
            if (r2 == 0) goto L_0x0100
            int r2 = mStaticAdCutOff
            if (r0 >= r2) goto L_0x0100
            java.lang.String r0 = "AndromoBannerChooser"
            java.lang.String r1 = "STATIC chosen"
            android.util.Log.v(r0, r1)
            com.newandromo.dev849565.app936843.BannerHelperBase r0 = getStaticAdHelper()
            goto L_0x0031
        L_0x0100:
            if (r1 == 0) goto L_0x0114
            r1.setAdFallbackHandler(r3)
            r0 = 1
            r1.setAndromoAd(r0)
            r0 = 0
            mCurrentFallbackIndex = r0
            goto L_0x0114
        L_0x010d:
            java.lang.String r0 = "AndromoBannerChooser"
            java.lang.String r2 = "Banner Ads not enabled"
            android.util.Log.v(r0, r2)
        L_0x0114:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newandromo.dev849565.app936843.AndromoBannerChooser.getBannerHelper():com.newandromo.dev849565.app936843.BannerHelperBase");
    }

    public static int countAdServices() {
        int i = (!mAdMobEnabled || mSuppressAdMob) ? 0 : 1;
        if (mAerServEnabled) {
            i++;
        }
        if (mFacebookEnabled) {
            i++;
        }
        if (mMillennialMediaEnabled) {
            i++;
        }
        if (mJavascriptAdEnabled) {
            i++;
        }
        if (mLeadBoltEnabled) {
            i++;
        }
        if (mAmazonEnabled) {
            i++;
        }
        if (mStartAppEnabled) {
            i++;
        }
        if (mAirpushEnabled) {
            i++;
        }
        if (mAirpush360Enabled) {
            i++;
        }
        return mStaticAdEnabled ? i + 1 : i;
    }

    private static void initAdPercentages() {
        int countAdServices = countAdServices();
        int i = 0;
        int i2 = (!mAdMobEnabled || mSuppressAdMob) ? 0 : mAdMobPercentage + 0;
        if (mAerServEnabled) {
            i2 += mAerServPercentage;
        }
        if (mFacebookEnabled) {
            i2 += mFacebookPercentage;
        }
        if (mMillennialMediaEnabled) {
            i2 += mMillennialMediaPercentage;
        }
        if (mJavascriptAdEnabled) {
            i2 += mJavascriptAdPercentage;
        }
        if (mLeadBoltEnabled) {
            i2 += mLeadBoltPercentage;
        }
        if (mAmazonEnabled) {
            i2 += mAmazonPercentage;
        }
        if (mStartAppEnabled) {
            i2 += mStartAppPercentage;
        }
        if (mAirpushEnabled) {
            i2 += mAirpushPercentage;
        }
        if (mAirpush360Enabled) {
            i2 += mAirpush360Percentage;
        }
        if (mStaticAdEnabled) {
            i2 += mStaticAdPercentage;
        }
        int i3 = 100;
        if (i2 != 100 && countAdServices > 0) {
            int i4 = 100 % countAdServices;
            if (countAdServices != 1) {
                i3 = 100 / countAdServices;
            }
            if (mAdMobEnabled && !mSuppressAdMob) {
                mAdMobPercentage = i4 + i3;
                i4 = 0;
            }
            if (mAerServEnabled) {
                mAerServPercentage = i4 + i3;
                i4 = 0;
            }
            if (mFacebookEnabled) {
                mFacebookPercentage = i4 + i3;
                i4 = 0;
            }
            if (mMillennialMediaEnabled) {
                mMillennialMediaPercentage = i4 + i3;
                i4 = 0;
            }
            if (mJavascriptAdEnabled) {
                mJavascriptAdPercentage = i4 + i3;
                i4 = 0;
            }
            if (mLeadBoltEnabled) {
                mLeadBoltPercentage = i4 + i3;
                i4 = 0;
            }
            if (mAmazonEnabled) {
                mAmazonPercentage = i4 + i3;
                i4 = 0;
            }
            if (mStartAppEnabled) {
                mStartAppPercentage = i4 + i3;
                i4 = 0;
            }
            if (mAirpushEnabled) {
                mAirpushPercentage = i4 + i3;
                i4 = 0;
            }
            if (mAirpush360Enabled) {
                mAirpush360Percentage = i4 + i3;
                i4 = 0;
            }
            if (mStaticAdEnabled) {
                mStaticAdPercentage = i3 + i4;
            }
        }
        if (!mCutoffsInitialized) {
            if (mAdMobEnabled && !mSuppressAdMob) {
                mAdMobCutOff = mAdMobPercentage + 0;
                i = 0 + mAdMobPercentage;
            }
            if (mAerServEnabled) {
                mAerServCutOff = mAerServPercentage + i;
                i += mAerServPercentage;
            }
            if (mFacebookEnabled) {
                mFacebookCutOff = mFacebookPercentage + i;
                i += mFacebookPercentage;
            }
            if (mMillennialMediaEnabled) {
                mMillennialMediaCutOff = mMillennialMediaPercentage + i;
                i += mMillennialMediaPercentage;
            }
            if (mJavascriptAdEnabled) {
                mJavascriptAdCutOff = mJavascriptAdPercentage + i;
                i += mJavascriptAdPercentage;
            }
            if (mLeadBoltEnabled) {
                mLeadBoltCutOff = mLeadBoltPercentage + i;
                i += mLeadBoltPercentage;
            }
            if (mAmazonEnabled) {
                mAmazonCutOff = mAmazonPercentage + i;
                i += mAmazonPercentage;
            }
            if (mStartAppEnabled) {
                mStartAppCutOff = mStartAppPercentage + i;
                i += mStartAppPercentage;
            }
            if (mAirpushEnabled) {
                mAirpushCutOff = mAirpushPercentage + i;
                i += mAirpushPercentage;
            }
            if (mAirpush360Enabled) {
                mAirpush360CutOff = mAirpush360Percentage + i;
                i += mAirpush360Percentage;
            }
            if (mStaticAdEnabled) {
                mStaticAdCutOff = i + mStaticAdPercentage;
                int i5 = mStaticAdPercentage;
            }
            Log.v(TAG, "initAdPercentages mAdMobPercentage: " + mAdMobPercentage);
            Log.v(TAG, "initAdPercentages mAerServPercentage: " + mAerServPercentage);
            Log.v(TAG, "initAdPercentages mFacebookPercentage: " + mFacebookPercentage);
            Log.v(TAG, "initAdPercentages mMillennialMediaPercentage: " + mMillennialMediaPercentage);
            Log.v(TAG, "initAdPercentages mJavascriptAdPercentage: " + mJavascriptAdPercentage);
            Log.v(TAG, "initAdPercentages mLeadBoltPercentage: " + mLeadBoltPercentage);
            Log.v(TAG, "initAdPercentages mAmazonPercentage: " + mAmazonPercentage);
            Log.v(TAG, "initAdPercentages mStartAppPercentage: " + mStartAppPercentage);
            Log.v(TAG, "initAdPercentages mAirpushPercentage: " + mAirpushPercentage);
            Log.v(TAG, "initAdPercentages mAirpush360Percentage: " + mAirpush360Percentage);
            Log.v(TAG, "initAdPercentages mStaticAdPercentage: " + mStaticAdPercentage);
            Log.v(TAG, "initAdPercentages mAdMobCutOff: " + mAdMobCutOff);
            Log.v(TAG, "initAdPercentages mAerServCutOff: " + mAerServCutOff);
            Log.v(TAG, "initAdPercentages mFacebookCutOff: " + mFacebookCutOff);
            Log.v(TAG, "initAdPercentages mMillennialMediaCutOff: " + mMillennialMediaCutOff);
            Log.v(TAG, "initAdPercentages mJavascriptAdCutOff: " + mJavascriptAdCutOff);
            Log.v(TAG, "initAdPercentages mLeadBoltCutOff: " + mLeadBoltCutOff);
            Log.v(TAG, "initAdPercentages mAmazonCutOff: " + mAmazonCutOff);
            Log.v(TAG, "initAdPercentages mStartAppCutOff: " + mStartAppCutOff);
            Log.v(TAG, "initAdPercentages mAirpushCutOff: " + mAirpushCutOff);
            Log.v(TAG, "initAdPercentages mAirpush360CutOff: " + mAirpush360CutOff);
            Log.v(TAG, "initAdPercentages mStaticAdCutOff: " + mStaticAdCutOff);
        }
    }

    static void onActivityStarted(Activity activity) {
        if (mAdMobHelper != null) {
            mAdMobHelper.onActivityStarted(activity);
        }
        if (mAerServHelper != null) {
            mAerServHelper.onActivityStarted(activity);
        }
        if (mFacebookBannerHelper != null) {
            mFacebookBannerHelper.onActivityStarted(activity);
        }
        if (mMillennialMediaHelper != null) {
            mMillennialMediaHelper.onActivityStarted(activity);
        }
        if (mJavascriptAdHelper != null) {
            mJavascriptAdHelper.onActivityStarted(activity);
        }
        if (mLeadBoltHelper != null) {
            mLeadBoltHelper.onActivityStarted(activity);
        }
        if (mAmazonHelper != null) {
            mAmazonHelper.onActivityStarted(activity);
        }
        if (mStartAppHelper != null) {
            mStartAppHelper.onActivityStarted(activity);
        }
        if (mAirpushHelper != null) {
            mAirpushHelper.onActivityStarted(activity);
        }
        if (mAirpush360Helper != null) {
            mAirpush360Helper.onActivityStarted(activity);
        }
        if (mStaticAdHelper != null) {
            mStaticAdHelper.onActivityStarted(activity);
        }
    }

    static void onActivityStopped(Activity activity) {
        if (mAdMobHelper != null) {
            mAdMobHelper.onActivityStopped(activity);
        }
        if (mAerServHelper != null) {
            mAerServHelper.onActivityStopped(activity);
        }
        if (mFacebookBannerHelper != null) {
            mFacebookBannerHelper.onActivityStopped(activity);
        }
        if (mMillennialMediaHelper != null) {
            mMillennialMediaHelper.onActivityStopped(activity);
        }
        if (mJavascriptAdHelper != null) {
            mJavascriptAdHelper.onActivityStopped(activity);
        }
        if (mLeadBoltHelper != null) {
            mLeadBoltHelper.onActivityStopped(activity);
        }
        if (mAmazonHelper != null) {
            mAmazonHelper.onActivityStopped(activity);
        }
        if (mStartAppHelper != null) {
            mStartAppHelper.onActivityStopped(activity);
        }
        if (mAirpushHelper != null) {
            mAirpushHelper.onActivityStopped(activity);
        }
        if (mAirpush360Helper != null) {
            mAirpush360Helper.onActivityStopped(activity);
        }
        if (mStaticAdHelper != null) {
            mStaticAdHelper.onActivityStopped(activity);
        }
    }

    static void onActivityPaused(Activity activity) {
        if (mAdMobHelper != null) {
            mAdMobHelper.onActivityPaused(activity);
        }
        if (mAerServHelper != null) {
            mAerServHelper.onActivityPaused(activity);
        }
        if (mFacebookBannerHelper != null) {
            mFacebookBannerHelper.onActivityPaused(activity);
        }
        if (mMillennialMediaHelper != null) {
            mMillennialMediaHelper.onActivityPaused(activity);
        }
        if (mJavascriptAdHelper != null) {
            mJavascriptAdHelper.onActivityPaused(activity);
        }
        if (mLeadBoltHelper != null) {
            mLeadBoltHelper.onActivityPaused(activity);
        }
        if (mAmazonHelper != null) {
            mAmazonHelper.onActivityPaused(activity);
        }
        if (mStartAppHelper != null) {
            mStartAppHelper.onActivityPaused(activity);
        }
        if (mAirpushHelper != null) {
            mAirpushHelper.onActivityPaused(activity);
        }
        if (mAirpush360Helper != null) {
            mAirpush360Helper.onActivityPaused(activity);
        }
        if (mStaticAdHelper != null) {
            mStaticAdHelper.onActivityPaused(activity);
        }
    }

    static void onActivityResumed(Activity activity) {
        if (mAdMobHelper != null) {
            mAdMobHelper.onActivityResumed(activity);
        }
        if (mAerServHelper != null) {
            mAerServHelper.onActivityResumed(activity);
        }
        if (mFacebookBannerHelper != null) {
            mFacebookBannerHelper.onActivityResumed(activity);
        }
        if (mMillennialMediaHelper != null) {
            mMillennialMediaHelper.onActivityResumed(activity);
        }
        if (mJavascriptAdHelper != null) {
            mJavascriptAdHelper.onActivityResumed(activity);
        }
        if (mLeadBoltHelper != null) {
            mLeadBoltHelper.onActivityResumed(activity);
        }
        if (mAmazonHelper != null) {
            mAmazonHelper.onActivityResumed(activity);
        }
        if (mStartAppHelper != null) {
            mStartAppHelper.onActivityResumed(activity);
        }
        if (mAirpushHelper != null) {
            mAirpushHelper.onActivityResumed(activity);
        }
        if (mAirpush360Helper != null) {
            mAirpush360Helper.onActivityResumed(activity);
        }
        if (mStaticAdHelper != null) {
            mStaticAdHelper.onActivityResumed(activity);
        }
    }

    static void onActivityDestroyed(Activity activity) {
        if (mAdMobHelper != null) {
            mAdMobHelper.onActivityDestroyed(activity);
        }
        if (mAerServHelper != null) {
            mAerServHelper.onActivityDestroyed(activity);
        }
        if (mFacebookBannerHelper != null) {
            mFacebookBannerHelper.onActivityDestroyed(activity);
        }
        if (mMillennialMediaHelper != null) {
            mMillennialMediaHelper.onActivityDestroyed(activity);
        }
        if (mJavascriptAdHelper != null) {
            mJavascriptAdHelper.onActivityDestroyed(activity);
        }
        if (mLeadBoltHelper != null) {
            mLeadBoltHelper.onActivityDestroyed(activity);
        }
        if (mAmazonHelper != null) {
            mAmazonHelper.onActivityDestroyed(activity);
        }
        if (mStartAppHelper != null) {
            mStartAppHelper.onActivityDestroyed(activity);
        }
        if (mAirpushHelper != null) {
            mAirpushHelper.onActivityDestroyed(activity);
        }
        if (mAirpush360Helper != null) {
            mAirpush360Helper.onActivityDestroyed(activity);
        }
        if (mStaticAdHelper != null) {
            mStaticAdHelper.onActivityDestroyed(activity);
        }
    }

    static void onApplicationCreated(Context context) {
        if (mStartAppEnabled) {
            StartAppHelper.initializeSDK(context, "");
        }
    }

    private static BannerHelperBase getAdMobHelper() {
        if (mAdMobHelper == null) {
            mAdMobHelper = new AdMobHelper(R.layout.ad_blank);
        }
        return mAdMobHelper;
    }

    private static BannerHelperBase getAerServHelper() {
        if (mAerServHelper == null) {
            mAerServHelper = new AerServHelper("", "");
        }
        return mAerServHelper;
    }

    private static BannerHelperBase getFacebookBannerHelper() {
        if (mFacebookBannerHelper == null) {
            mFacebookBannerHelper = new FacebookBannerHelper("");
        }
        return mFacebookBannerHelper;
    }

    private static BannerHelperBase getMillennialMediaHelper() {
        if (mMillennialMediaHelper == null) {
            mMillennialMediaHelper = new MillennialMediaHelper(R.layout.ad_blank, "");
        }
        return mMillennialMediaHelper;
    }

    private static BannerHelperBase getJavascriptAdHelper() {
        if (mJavascriptAdHelper == null) {
            mJavascriptAdHelper = new JavascriptAdHelper(R.layout.javascript_ad_layout);
        }
        return mJavascriptAdHelper;
    }

    private static BannerHelperBase getLeadBoltHelper() {
        if (mLeadBoltHelper == null) {
            mLeadBoltHelper = new LeadBoltBannerHelper(R.layout.ad_blank, "0");
        }
        return mLeadBoltHelper;
    }

    private static BannerHelperBase getAmazonHelper() {
        if (mAmazonHelper == null) {
            mAmazonHelper = new AmazonHelper("");
        }
        return mAmazonHelper;
    }

    private static BannerHelperBase getStartAppHelper() {
        if (mStartAppHelper == null) {
            mStartAppHelper = new StartAppHelper("");
        }
        return mStartAppHelper;
    }

    private static BannerHelperBase getAirpushHelper() {
        if (mAirpushHelper == null) {
            mAirpushHelper = new AirpushHelper("", 0);
        }
        return mAirpushHelper;
    }

    private static BannerHelperBase getAirpush360Helper() {
        if (mAirpush360Helper == null) {
            mAirpush360Helper = new Airpush360Helper("", 0);
            mAirpush360Helper.setAbstractBanner(mIsAndromoAd);
        }
        return mAirpush360Helper;
    }

    private static BannerHelperBase getStaticAdHelper() {
        if (mStaticAdHelper == null) {
            mStaticAdHelper = new StaticAdHelper(R.layout.static_ad_andromo, "http://www.andromo.com/?utm_source=static_banner&utm_medium=app&utm_campaign=andromo_app");
        }
        return mStaticAdHelper;
    }

    public boolean isStartAppEnabled() {
        return mStartAppEnabled;
    }

    public void disableNetworksForGDPR() {
        Log.v(TAG, "disableNetworksForGDPR: Disabling Amazon and Facebook banners.");
        mAmazonEnabled = false;
        mFacebookEnabled = false;
    }
}
