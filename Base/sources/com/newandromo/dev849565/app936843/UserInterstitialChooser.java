package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class UserInterstitialChooser {
    private static final String TAG = "UserInterstitialChooser";
    private static boolean mAdMobEnabled = false;
    private static int mAdMobPercentage = -1;
    private static final boolean mAdsEnabled = (mAppBrainEnabled || mAdMobEnabled || mAerServEnabled || mFacebookEnabled || mAmazonEnabled || mAppLovinEnabled || mStartAppEnabled || mAirpushEnabled || mMillennialMediaEnabled);
    private static boolean mAerServEnabled = false;
    private static int mAerServPercentage = -1;
    private static boolean mAirpushEnabled = false;
    private static int mAirpushPercentage = -1;
    private static boolean mAmazonEnabled = false;
    private static int mAmazonPercentage = -1;
    private static boolean mAppBrainEnabled = false;
    private static int mAppBrainPercentage = -1;
    private static boolean mAppLovinEnabled = false;
    private static int mAppLovinPercentage = -1;
    private static boolean mFacebookEnabled = false;
    private static int mFacebookPercentage = -1;
    private static boolean mMillennialMediaEnabled = false;
    private static int mMillennialMediaPercentage = -1;
    private static boolean mStartAppEnabled = false;
    private static int mStartAppPercentage = -1;
    private static Random random = new Random();

    private static abstract class InterstitialNetwork {
        final String name;
        final int percentage;

        /* access modifiers changed from: package-private */
        public abstract InterstitialHelperBase getHelper();

        public InterstitialNetwork(int i, String str) {
            this.percentage = i;
            this.name = str;
        }
    }

    private static void addEnabledNetworks(List<InterstitialNetwork> list) {
        if (list != null) {
            if (mAppBrainEnabled) {
                list.add(new InterstitialNetwork(mAppBrainPercentage, "AppBrain") {
                    /* access modifiers changed from: package-private */
                    public InterstitialHelperBase getHelper() {
                        return new AppBrainInterstitialHelper();
                    }
                });
                Log.v(TAG, "AppBrain enabled, percentage: " + mAppBrainPercentage);
            }
            if (mAdMobEnabled) {
                list.add(new InterstitialNetwork(mAdMobPercentage, "AdMob") {
                    /* access modifiers changed from: package-private */
                    public InterstitialHelperBase getHelper() {
                        return new AdMobInterstitialHelper("");
                    }
                });
                Log.v(TAG, "AdMob enabled, percentage: " + mAdMobPercentage);
            }
            if (mAerServEnabled) {
                list.add(new InterstitialNetwork(mAerServPercentage, "AerServ") {
                    /* access modifiers changed from: package-private */
                    public InterstitialHelperBase getHelper() {
                        return new AerServInterstitialHelper("", "");
                    }
                });
                Log.v(TAG, "AerServ enabled, percentage: " + mAerServPercentage);
            }
            if (mFacebookEnabled) {
                list.add(new InterstitialNetwork(mFacebookPercentage, "Facebook") {
                    /* access modifiers changed from: package-private */
                    public InterstitialHelperBase getHelper() {
                        return new FacebookInterstitialHelper("");
                    }
                });
                Log.v(TAG, "Facebook Audience Network enabled, percentage: " + mFacebookPercentage);
            }
            if (mAmazonEnabled) {
                list.add(new InterstitialNetwork(mAmazonPercentage, "Amazon") {
                    /* access modifiers changed from: package-private */
                    public InterstitialHelperBase getHelper() {
                        return new AmazonInterstitialHelper("");
                    }
                });
                Log.v(TAG, "Amazon enabled, percentage: " + mAmazonPercentage);
            }
            if (mAppLovinEnabled) {
                list.add(new InterstitialNetwork(mAppLovinPercentage, "AppLovin") {
                    /* access modifiers changed from: package-private */
                    public InterstitialHelperBase getHelper() {
                        return new AppLovinInterstitialHelper("");
                    }
                });
                Log.v(TAG, "AppLovin enabled, percentage: " + mAppLovinPercentage);
            }
            if (mStartAppEnabled) {
                list.add(new InterstitialNetwork(mStartAppPercentage, "StartApp") {
                    /* access modifiers changed from: package-private */
                    public InterstitialHelperBase getHelper() {
                        return new StartAppInterstitialHelper("");
                    }
                });
                Log.v(TAG, "StartApp enabled, percentage: " + mStartAppPercentage);
            }
            if (mAirpushEnabled) {
                list.add(new InterstitialNetwork(mAirpushPercentage, "AirPush") {
                    /* access modifiers changed from: package-private */
                    public InterstitialHelperBase getHelper() {
                        return new AirpushInterstitialHelper("", 0);
                    }
                });
                Log.v(TAG, "AirPush enabled, percentage: " + mAirpushPercentage);
            }
            if (mMillennialMediaEnabled) {
                list.add(new InterstitialNetwork(mMillennialMediaPercentage, "MillennialMedia") {
                    /* access modifiers changed from: package-private */
                    public InterstitialHelperBase getHelper() {
                        return new MillennialMediaInterstitialHelper("");
                    }
                });
                Log.v(TAG, "MillennialMedia enabled, percentage: " + mMillennialMediaPercentage);
            }
        }
    }

    private static int calculateTotalWeight(List<InterstitialNetwork> list, int i) {
        int i2 = 0;
        if (list != null) {
            for (InterstitialNetwork next : list) {
                if (next.percentage < 0) {
                    i2 += i;
                    StringBuilder sb = new StringBuilder();
                    sb.append(next.name);
                    sb.append(" range: ");
                    sb.append(i2 - i);
                    sb.append(" - ");
                    sb.append(i2 - 1);
                    sb.append(", percentage: ");
                    sb.append(i);
                    sb.append(" (");
                    sb.append(next.percentage);
                    sb.append(")");
                    Log.v(TAG, sb.toString());
                } else {
                    i2 += next.percentage;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(next.name);
                    sb2.append(" range: ");
                    sb2.append(i2 - next.percentage);
                    sb2.append(" - ");
                    sb2.append(i2 - 1);
                    sb2.append(", percentage: ");
                    sb2.append(next.percentage);
                    sb2.append(" (");
                    sb2.append(next.percentage);
                    sb2.append(")");
                    Log.v(TAG, sb2.toString());
                }
            }
        }
        return i2;
    }

    public static InterstitialHelperBase getInterstitialHelper() {
        Log.v(TAG, "getInterstitialHelper");
        InterstitialHelperBase interstitialHelperBase = null;
        if (mAdsEnabled) {
            Log.v(TAG, "Ads are enabled");
            ArrayList arrayList = new ArrayList();
            addEnabledNetworks(arrayList);
            int i = 0;
            if (arrayList.size() > 1) {
                int size = 100 / arrayList.size();
                Log.v(TAG, "automaticWeight: " + size);
                int nextInt = random.nextInt(calculateTotalWeight(arrayList, size));
                Log.v(TAG, "random value: " + nextInt);
                Iterator it = arrayList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    InterstitialNetwork interstitialNetwork = (InterstitialNetwork) it.next();
                    if (interstitialNetwork.percentage < 0) {
                        i += size;
                        continue;
                    } else {
                        i += interstitialNetwork.percentage;
                        continue;
                    }
                    if (nextInt < i) {
                        interstitialHelperBase = interstitialNetwork.getHelper();
                        break;
                    }
                }
            } else if (arrayList.size() == 1) {
                InterstitialNetwork interstitialNetwork2 = (InterstitialNetwork) arrayList.get(0);
                interstitialHelperBase = interstitialNetwork2.getHelper();
                Log.v(TAG, "Only one network enabled: " + interstitialNetwork2.name);
            } else {
                Log.v(TAG, "No networks enabled!");
            }
            if (interstitialHelperBase != null) {
                interstitialHelperBase.setShowuserAds(true);
            }
        }
        return interstitialHelperBase;
    }

    static void onApplicationCreated(Context context) {
        if (mAppBrainEnabled) {
            AppBrainInterstitialHelper.initializeAppBrain(context);
        }
        if (mStartAppEnabled) {
            StartAppInterstitialHelper.initializeSDK(context, "");
        }
        if (mAppLovinEnabled) {
            AppLovinInterstitialHelper.initializeSDK(context);
        }
    }

    public boolean isStartAppEnabled() {
        return mStartAppEnabled;
    }

    public boolean isAppLovinEnabled() {
        return mAppLovinEnabled;
    }

    public void disableNetworksForGDPR() {
        Log.v(TAG, "disableNetworksForGDPR: Disabling Amazon, Facebook, AppBrain interstitials.");
        mAmazonEnabled = false;
        mFacebookEnabled = false;
        mAppBrainEnabled = false;
    }
}
