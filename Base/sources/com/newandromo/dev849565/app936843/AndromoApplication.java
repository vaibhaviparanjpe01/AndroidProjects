package com.newandromo.dev849565.app936843;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import com.google.ads.consent.ConsentForm;
import com.google.ads.consent.ConsentFormListener;
import com.google.ads.consent.ConsentInfoUpdateListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;
import java.net.URL;

public class AndromoApplication extends AirBopApplication {
    private static final String TAG = "AndromoApplication (5.3.14)";
    public static AppState appState;
    private static Context mContext;
    private static Resources mResources;
    ConsentForm _form;
    /* access modifiers changed from: private */
    public boolean _isPersonalizedAds;
    private final String admobPrivacyUrl = "null";
    public int numRunningActivities = 0;

    public interface AppState {
        void onChange(boolean z);
    }

    public static Resources getAppResources() {
        return mResources;
    }

    public static Context getAppContext() {
        return mContext;
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    public boolean isPersonalizedAds() {
        return this._isPersonalizedAds;
    }

    public void launchConsentFrom(Activity activity) {
        try {
            this._form = new ConsentForm.Builder(activity, new URL("null")).withListener(new ConsentFormListener() {
                public void onConsentFormOpened() {
                }

                public void onConsentFormLoaded() {
                    AndromoApplication.this._form.show();
                }

                public void onConsentFormClosed(ConsentStatus consentStatus, Boolean bool) {
                    if (consentStatus.equals(ConsentStatus.PERSONALIZED)) {
                        boolean unused = AndromoApplication.this._isPersonalizedAds = true;
                    } else {
                        boolean unused2 = AndromoApplication.this._isPersonalizedAds = false;
                    }
                }

                public void onConsentFormError(String str) {
                    boolean unused = AndromoApplication.this._isPersonalizedAds = false;
                }
            }).withPersonalizedAdsOption().withNonPersonalizedAdsOption().build();
            this._form.load();
        } catch (Exception unused) {
        }
    }

    public void onCreate() {
        super.onCreate();
        try {
            if (!"null".equals("null")) {
                ConsentInformation instance = ConsentInformation.getInstance(this);
                String string = getPackageManager().getApplicationInfo(getPackageName(), 128).metaData.getString("com.google.android.gms.ads.APPLICATION_ID");
                instance.requestConsentInfoUpdate(new String[]{"pub-" + string.split("~")[0].split("-")[3]}, new ConsentInfoUpdateListener() {
                    public void onFailedToUpdateConsentInfo(String str) {
                    }

                    public void onConsentInfoUpdated(ConsentStatus consentStatus) {
                        if (ConsentInformation.getInstance(AndromoApplication.this.getBaseContext()).isRequestLocationInEeaOrUnknown()) {
                            switch (AnonymousClass4.$SwitchMap$com$google$ads$consent$ConsentStatus[consentStatus.ordinal()]) {
                                case 1:
                                    boolean unused = AndromoApplication.this._isPersonalizedAds = true;
                                    return;
                                case 2:
                                    boolean unused2 = AndromoApplication.this._isPersonalizedAds = false;
                                    return;
                                case 3:
                                    AndromoActivity.showConsent = true;
                                    return;
                                default:
                                    return;
                            }
                        } else {
                            boolean unused3 = AndromoApplication.this._isPersonalizedAds = true;
                        }
                    }
                });
            }
        } catch (Exception unused) {
        }
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            public void onActivityDestroyed(Activity activity) {
            }

            public void onActivityPaused(Activity activity) {
            }

            public void onActivityResumed(Activity activity) {
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            public void onActivityStarted(Activity activity) {
                AndromoApplication.this.numRunningActivities++;
                if (AndromoApplication.this.numRunningActivities == 1 && AndromoApplication.appState != null) {
                    AndromoApplication.appState.onChange(true);
                }
            }

            public void onActivityStopped(Activity activity) {
                AndromoApplication andromoApplication = AndromoApplication.this;
                andromoApplication.numRunningActivities--;
                if (AndromoApplication.this.numRunningActivities == 0 && AndromoApplication.appState != null) {
                    AndromoApplication.appState.onChange(false);
                }
            }
        });
        mResources = getResources();
        mContext = getApplicationContext();
        AnalyticsUtils.setInitialAnalyticsAppOptOut(this);
        AdReporter.getInstance();
        AdManager.onApplicationCreated(this);
        RatePopupHelper.init(this);
        OneSignalHelper.init(this);
        IntercomHelper.init(this);
    }

    /* renamed from: com.newandromo.dev849565.app936843.AndromoApplication$4  reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$google$ads$consent$ConsentStatus = new int[ConsentStatus.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.google.ads.consent.ConsentStatus[] r0 = com.google.ads.consent.ConsentStatus.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$ads$consent$ConsentStatus = r0
                int[] r0 = $SwitchMap$com$google$ads$consent$ConsentStatus     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.ads.consent.ConsentStatus r1 = com.google.ads.consent.ConsentStatus.PERSONALIZED     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$google$ads$consent$ConsentStatus     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.ads.consent.ConsentStatus r1 = com.google.ads.consent.ConsentStatus.NON_PERSONALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$google$ads$consent$ConsentStatus     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.ads.consent.ConsentStatus r1 = com.google.ads.consent.ConsentStatus.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.newandromo.dev849565.app936843.AndromoApplication.AnonymousClass4.<clinit>():void");
        }
    }

    static void onEUCountryKnown(Context context, boolean z) {
        if (z) {
            AndromoFlurryAnalytics.setFlurryAnalyticsEnabled(false);
            AnalyticsUtils.setEnableAnalyticsInPrefsApply(getAppContext(), false);
            AndromoFirebaseAnalytics.disableFirebaseAnalyticsInPrefs(getAppContext());
            return;
        }
        AndromoFlurryAnalytics.initialize(getAppContext());
        AnalyticsUtils.initializeAnalytics(getAppContext());
        AnalyticsUtils.setContext(context);
        AndromoFirebaseAnalytics.initialize(getAppContext());
    }
}
