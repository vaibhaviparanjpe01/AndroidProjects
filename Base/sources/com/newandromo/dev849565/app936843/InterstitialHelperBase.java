package com.newandromo.dev849565.app936843;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.newandromo.dev849565.app936843.AudioService;
import java.util.Random;

public abstract class InterstitialHelperBase {
    protected static final boolean AUDIO_INCLUDED = false;
    static final String INTERSTITIAL_PREFERENCES = "com.newandromo.dev849565.app936843.interstitial";
    private static final int MAX_EVENTS = 5;
    private static final int MIN_EVENTS = 2;
    private static final long PRELOAD_WINDOW_MILLISECS = 300000;
    private static final String PROPERTY_AD_EVENT_COUNT = "interstitialAdEventCount";
    private static final String PROPERTY_AD_EXPIRATION_TIME = "interstitialAdExpiration";
    private static final String TAG = "InterstitialHelperBase";
    private static final boolean USE_RANDOM_SPACE = false;
    private static LocalBroadcastManager localBroadcastManager = null;
    private static boolean mAudioServiceExists = false;
    private static AudioServiceReceiver mAudioServiceReceiver = null;
    /* access modifiers changed from: private */
    public static AudioItem mPausedItem = null;
    private static Context mReceiverContext = null;
    private static final long mRefreshRateMillis = 300000;
    /* access modifiers changed from: private */
    public static boolean mWaitingForPaused;
    private Runnable mRunnable;
    private boolean mbShowUseAds = true;

    static boolean haveEnoughEventsPassed(Context context) {
        return true;
    }

    protected static void pauseAudioIfPlaying(Context context) {
    }

    protected static void playAudioIfPaused(Context context) {
    }

    /* access modifiers changed from: protected */
    public abstract void destroyInterstitial();

    public String getNameForReport() {
        return "developer_forgot_about_this_one";
    }

    /* access modifiers changed from: protected */
    public abstract RunnableTiming getRunnableTiming();

    /* access modifiers changed from: protected */
    public abstract void hideInterstitial();

    /* access modifiers changed from: protected */
    public abstract boolean initializeInterstitial(Activity activity);

    /* access modifiers changed from: protected */
    public boolean isReadyToInitializeInterstitial(Context context) {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract boolean isReadyToShowInterstitial(Context context);

    public void onActivityBackPressed(Activity activity) {
    }

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
    public void onApplicationCreated(Context context) {
    }

    /* access modifiers changed from: protected */
    public abstract boolean shouldShowAds();

    /* access modifiers changed from: protected */
    public abstract boolean showInterstitial(Activity activity);

    public void setShowuserAds(boolean z) {
        this.mbShowUseAds = z;
    }

    public final boolean showInterstitialAndRun(Activity activity, Runnable runnable) {
        this.mRunnable = runnable;
        if (getRunnableTiming() == RunnableTiming.RUN_BEFORE_SHOWING) {
            runRunnable();
            AdManager.selectNextInterstitialNetwork();
        }
        boolean showInterstitial = showInterstitial(activity);
        if (!showInterstitial || getRunnableTiming() == RunnableTiming.RUN_AFTER_SHOWING) {
            runRunnable();
            AdManager.selectNextInterstitialNetwork();
        }
        return showInterstitial;
    }

    /* access modifiers changed from: protected */
    public final void runRunnable() {
        Log.v(TAG, "runRunnable()");
        if (this.mRunnable != null) {
            Log.v(TAG, "mRunnable not null, calling mRunnable.run()");
            this.mRunnable.run();
            this.mRunnable = null;
        }
    }

    /* access modifiers changed from: protected */
    public final void onInterstitialClosed() {
        Log.v(TAG, "onInterstitialClosed");
        if (getRunnableTiming() == RunnableTiming.RUN_ON_CLOSED || getRunnableTiming() == RunnableTiming.RUN_ON_CLOSED_OR_FAILED) {
            runRunnable();
        }
        AdManager.selectNextInterstitialNetwork();
    }

    /* access modifiers changed from: protected */
    public final void onInterstitialFailed() {
        Log.v(TAG, "onInterstitialFailed");
        if (getRunnableTiming() == RunnableTiming.RUN_ON_CLOSED_OR_FAILED) {
            runRunnable();
        }
        AdManager.selectNextInterstitialNetwork();
    }

    protected static void playAudioIfPaused() {
        playAudioIfPaused(mReceiverContext);
        mReceiverContext = null;
    }

    public static void resetForNextInterstitialDelay(Context context) {
        updateTimestamp(context);
        resetEventCount(context);
    }

    public static void setReadyToShowInterstitialRightNow(Context context) {
        Log.v(TAG, "Overriding timestamp and events counter to allow next interstitial ad to show immediately.");
        setEventCount(context, 0);
        setTimestamp(context, -1);
    }

    static boolean hasEnoughTimePassed(Context context) {
        boolean z = System.currentTimeMillis() > getPreferences(context).getLong(PROPERTY_AD_EXPIRATION_TIME, -1);
        Log.v(TAG, "hasEnoughTimePassed enoughTime: " + z);
        return z;
    }

    static boolean isTimeToInitialize(Context context) {
        boolean z = getPreferences(context).getLong(PROPERTY_AD_EXPIRATION_TIME, -1) - System.currentTimeMillis() < 300000;
        Log.v(TAG, "isTimeToInitialize based on time: " + z);
        return z;
    }

    static void updateTimestamp(Context context) {
        SharedPreferences.Editor edit = getPreferences(context).edit();
        long interstitialDelay = AdReporter.getInstance().getInterstitialDelay();
        if (interstitialDelay == 0) {
            interstitialDelay = 300000;
        }
        long currentTimeMillis = System.currentTimeMillis() + interstitialDelay;
        edit.putLong(PROPERTY_AD_EXPIRATION_TIME, currentTimeMillis);
        edit.apply();
        Log.v(TAG, "updateTimestamp expirationTime: " + currentTimeMillis);
    }

    static void setTimestamp(Context context, long j) {
        Log.v(TAG, "setting timestamp=" + j);
        SharedPreferences.Editor edit = getPreferences(context).edit();
        edit.putLong(PROPERTY_AD_EXPIRATION_TIME, j);
        edit.apply();
    }

    static int getEventCount(Context context) {
        SharedPreferences preferences = getPreferences(context);
        if (preferences == null) {
            return 0;
        }
        int i = preferences.getInt(PROPERTY_AD_EVENT_COUNT, -1);
        return i == -1 ? resetEventCount(context) : i;
    }

    static int resetEventCount(Context context) {
        int nextInt = new Random().nextInt(3) + 2;
        Log.v(TAG, "resetEventCount eventCount: " + nextInt);
        setEventCount(context, nextInt);
        return nextInt;
    }

    static void setEventCount(Context context, int i) {
        SharedPreferences.Editor edit = getPreferences(context).edit();
        edit.putInt(PROPERTY_AD_EVENT_COUNT, i);
        edit.apply();
    }

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(INTERSTITIAL_PREFERENCES, 0);
    }

    /* access modifiers changed from: protected */
    public void onActivityStarted(Activity activity) {
        if (mWaitingForPaused) {
            registerAudioServiceReceiver(activity);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityStopped(Activity activity) {
        unregisterAudioServiceReceiver(activity);
    }

    private static void registerAudioServiceReceiver(Context context) {
        if (mAudioServiceReceiver == null) {
            Log.v(TAG, "registerAudioServiceReceiver");
            IntentFilter intentFilter = new IntentFilter("");
            intentFilter.addCategory("android.intent.category.DEFAULT");
            mAudioServiceReceiver = new AudioServiceReceiver();
            if (mAudioServiceReceiver != null) {
                localBroadcastManager.registerReceiver(mAudioServiceReceiver, intentFilter);
            }
        }
    }

    private static void unregisterAudioServiceReceiver(Context context) {
        if (mAudioServiceReceiver != null) {
            Log.v(TAG, "unregisterAudioServiceReceiver");
            try {
                localBroadcastManager.unregisterReceiver(mAudioServiceReceiver);
            } catch (IllegalArgumentException e) {
                Log.e(TAG, "IllegalArgumentException calling unRegisterReceiver: " + e.getMessage());
                e.printStackTrace();
            }
            mAudioServiceReceiver = null;
        }
    }

    public static class AudioServiceReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                Log.v(InterstitialHelperBase.TAG, "AudioServiceReceiver onReceive(): " + action);
                if ("".equals(action)) {
                    String stringExtra = intent.getStringExtra("");
                    Log.v(InterstitialHelperBase.TAG, "AudioServiceReceiver onReceive(): " + stringExtra);
                    switch (AudioService.State.valueOf(stringExtra)) {
                        case Paused:
                            if (InterstitialHelperBase.mWaitingForPaused) {
                                AudioItem unused = InterstitialHelperBase.mPausedItem = (AudioItem) intent.getParcelableExtra("");
                                boolean unused2 = InterstitialHelperBase.mWaitingForPaused = false;
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }
            }
        }
    }
}
