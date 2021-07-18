package com.google.ads.consent;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.HashMap;

public class ConsentForm {
    private final boolean adFreeOption;
    private final URL appPrivacyPolicyURL;
    private final Context context;
    private final Dialog dialog;
    /* access modifiers changed from: private */
    public final ConsentFormListener listener;
    /* access modifiers changed from: private */
    public LoadState loadState;
    private final boolean nonPersonalizedAdsOption;
    private final boolean personalizedAdsOption;
    private final WebView webView;

    private enum LoadState {
        NOT_READY,
        LOADING,
        LOADED
    }

    private ConsentForm(Builder builder) {
        this.context = builder.context;
        if (builder.listener == null) {
            this.listener = new ConsentFormListener(this) {
            };
        } else {
            this.listener = builder.listener;
        }
        this.personalizedAdsOption = builder.personalizedAdsOption;
        this.nonPersonalizedAdsOption = builder.nonPersonalizedAdsOption;
        this.adFreeOption = builder.adFreeOption;
        this.appPrivacyPolicyURL = builder.appPrivacyPolicyURL;
        this.dialog = new Dialog(this.context, 16973840);
        this.loadState = LoadState.NOT_READY;
        this.webView = new WebView(this.context);
        this.webView.setBackgroundColor(0);
        this.dialog.setContentView(this.webView);
        this.dialog.setCancelable(false);
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.setWebViewClient(new WebViewClient() {
            boolean isInternalRedirect;

            private boolean isConsentFormUrl(String str) {
                return !TextUtils.isEmpty(str) && str.startsWith("consent://");
            }

            /* JADX WARNING: Code restructure failed: missing block: B:10:0x003c, code lost:
                if (r1.equals("dismiss") == false) goto L_0x0053;
             */
            /* JADX WARNING: Removed duplicated region for block: B:19:0x0058  */
            /* JADX WARNING: Removed duplicated region for block: B:20:0x005e  */
            /* JADX WARNING: Removed duplicated region for block: B:21:0x0066  */
            /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            private void handleUrl(java.lang.String r8) {
                /*
                    r7 = this;
                    boolean r0 = r7.isConsentFormUrl(r8)
                    if (r0 != 0) goto L_0x0007
                    return
                L_0x0007:
                    r0 = 1
                    r7.isInternalRedirect = r0
                    android.net.Uri r8 = android.net.Uri.parse(r8)
                    java.lang.String r1 = "action"
                    java.lang.String r1 = r8.getQueryParameter(r1)
                    java.lang.String r2 = "status"
                    java.lang.String r2 = r8.getQueryParameter(r2)
                    java.lang.String r3 = "url"
                    java.lang.String r8 = r8.getQueryParameter(r3)
                    r3 = -1
                    int r4 = r1.hashCode()
                    r5 = -1370505102(0xffffffffae4fc072, float:-4.7237277E-11)
                    r6 = 0
                    if (r4 == r5) goto L_0x0049
                    r5 = 150940456(0x8ff2b28, float:1.53574E-33)
                    if (r4 == r5) goto L_0x003f
                    r5 = 1671672458(0x63a3b28a, float:6.039369E21)
                    if (r4 == r5) goto L_0x0036
                    goto L_0x0053
                L_0x0036:
                    java.lang.String r4 = "dismiss"
                    boolean r1 = r1.equals(r4)
                    if (r1 == 0) goto L_0x0053
                    goto L_0x0054
                L_0x003f:
                    java.lang.String r0 = "browser"
                    boolean r0 = r1.equals(r0)
                    if (r0 == 0) goto L_0x0053
                    r0 = 2
                    goto L_0x0054
                L_0x0049:
                    java.lang.String r0 = "load_complete"
                    boolean r0 = r1.equals(r0)
                    if (r0 == 0) goto L_0x0053
                    r0 = 0
                    goto L_0x0054
                L_0x0053:
                    r0 = -1
                L_0x0054:
                    switch(r0) {
                        case 0: goto L_0x0066;
                        case 1: goto L_0x005e;
                        case 2: goto L_0x0058;
                        default: goto L_0x0057;
                    }
                L_0x0057:
                    goto L_0x006b
                L_0x0058:
                    com.google.ads.consent.ConsentForm r0 = com.google.ads.consent.ConsentForm.this
                    r0.handleOpenBrowser(r8)
                    goto L_0x006b
                L_0x005e:
                    r7.isInternalRedirect = r6
                    com.google.ads.consent.ConsentForm r8 = com.google.ads.consent.ConsentForm.this
                    r8.handleDismiss(r2)
                    goto L_0x006b
                L_0x0066:
                    com.google.ads.consent.ConsentForm r8 = com.google.ads.consent.ConsentForm.this
                    r8.handleLoadComplete(r2)
                L_0x006b:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.ads.consent.ConsentForm.AnonymousClass2.handleUrl(java.lang.String):void");
            }

            public void onLoadResource(WebView webView, String str) {
                handleUrl(str);
            }

            @TargetApi(24)
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                String uri = webResourceRequest.getUrl().toString();
                if (!isConsentFormUrl(uri)) {
                    return false;
                }
                handleUrl(uri);
                return true;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (!isConsentFormUrl(str)) {
                    return false;
                }
                handleUrl(str);
                return true;
            }

            public void onPageFinished(WebView webView, String str) {
                if (!this.isInternalRedirect) {
                    ConsentForm.this.updateDialogContent(webView);
                }
                super.onPageFinished(webView, str);
            }

            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                LoadState unused = ConsentForm.this.loadState = LoadState.NOT_READY;
                ConsentForm.this.listener.onConsentFormError(webResourceError.toString());
            }
        });
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public boolean adFreeOption = false;
        /* access modifiers changed from: private */
        public final URL appPrivacyPolicyURL;
        /* access modifiers changed from: private */
        public final Context context;
        /* access modifiers changed from: private */
        public ConsentFormListener listener;
        /* access modifiers changed from: private */
        public boolean nonPersonalizedAdsOption = false;
        /* access modifiers changed from: private */
        public boolean personalizedAdsOption = false;

        public Builder(Context context2, URL url) {
            this.context = context2;
            this.appPrivacyPolicyURL = url;
            if (this.appPrivacyPolicyURL == null) {
                throw new IllegalArgumentException("Must provide valid app privacy policy url to create a ConsentForm");
            }
        }

        public Builder withListener(ConsentFormListener consentFormListener) {
            this.listener = consentFormListener;
            return this;
        }

        public Builder withPersonalizedAdsOption() {
            this.personalizedAdsOption = true;
            return this;
        }

        public Builder withNonPersonalizedAdsOption() {
            this.nonPersonalizedAdsOption = true;
            return this;
        }

        public Builder withAdFreeOption() {
            this.adFreeOption = true;
            return this;
        }

        public ConsentForm build() {
            return new ConsentForm(this);
        }
    }

    private static String getApplicationName(Context context2) {
        return context2.getApplicationInfo().loadLabel(context2.getPackageManager()).toString();
    }

    private static String getAppIconURIString(Context context2) {
        Drawable applicationIcon = context2.getPackageManager().getApplicationIcon(context2.getApplicationInfo());
        Bitmap createBitmap = Bitmap.createBitmap(applicationIcon.getIntrinsicWidth(), applicationIcon.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        applicationIcon.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        applicationIcon.draw(canvas);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        String valueOf = String.valueOf(Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0));
        return valueOf.length() != 0 ? "data:image/png;base64,".concat(valueOf) : new String("data:image/png;base64,");
    }

    private static String createJavascriptCommand(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("info", str2);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("args", hashMap);
        return String.format("javascript:%s(%s)", new Object[]{str, new Gson().toJson((Object) hashMap2)});
    }

    /* access modifiers changed from: private */
    public void updateDialogContent(WebView webView2) {
        HashMap hashMap = new HashMap();
        hashMap.put("app_name", getApplicationName(this.context));
        hashMap.put("app_icon", getAppIconURIString(this.context));
        hashMap.put("offer_personalized", Boolean.valueOf(this.personalizedAdsOption));
        hashMap.put("offer_non_personalized", Boolean.valueOf(this.nonPersonalizedAdsOption));
        hashMap.put("offer_ad_free", Boolean.valueOf(this.adFreeOption));
        hashMap.put("is_request_in_eea_or_unknown", Boolean.valueOf(ConsentInformation.getInstance(this.context).isRequestLocationInEeaOrUnknown()));
        hashMap.put("app_privacy_url", this.appPrivacyPolicyURL);
        ConsentData loadConsentData = ConsentInformation.getInstance(this.context).loadConsentData();
        hashMap.put("plat", loadConsentData.getSDKPlatformString());
        hashMap.put("consent_info", loadConsentData);
        webView2.loadUrl(createJavascriptCommand("setUpConsentDialog", new Gson().toJson((Object) hashMap)));
    }

    public void load() {
        if (this.loadState == LoadState.LOADING) {
            this.listener.onConsentFormError("Cannot simultaneously load multiple consent forms.");
        } else if (this.loadState == LoadState.LOADED) {
            this.listener.onConsentFormLoaded();
        } else {
            this.loadState = LoadState.LOADING;
            this.webView.loadUrl("file:///android_asset/consentform.html");
        }
    }

    /* access modifiers changed from: private */
    public void handleLoadComplete(String str) {
        if (TextUtils.isEmpty(str)) {
            this.loadState = LoadState.NOT_READY;
            this.listener.onConsentFormError("No information");
        } else if (str.contains("Error")) {
            this.loadState = LoadState.NOT_READY;
            this.listener.onConsentFormError(str);
        } else {
            this.loadState = LoadState.LOADED;
            this.listener.onConsentFormLoaded();
        }
    }

    /* access modifiers changed from: private */
    public void handleOpenBrowser(String str) {
        if (TextUtils.isEmpty(str)) {
            this.listener.onConsentFormError("No valid URL for browser navigation.");
            return;
        }
        try {
            this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (ActivityNotFoundException unused) {
            this.listener.onConsentFormError("No Activity found to handle browser intent.");
        }
    }

    /* access modifiers changed from: private */
    public void handleDismiss(String str) {
        ConsentStatus consentStatus;
        this.loadState = LoadState.NOT_READY;
        this.dialog.dismiss();
        if (TextUtils.isEmpty(str)) {
            this.listener.onConsentFormError("No information provided.");
        } else if (str.contains("Error")) {
            this.listener.onConsentFormError(str);
        } else {
            char c = 65535;
            int hashCode = str.hashCode();
            boolean z = false;
            if (hashCode != -1152655096) {
                if (hashCode != -258041904) {
                    if (hashCode == 1666911234 && str.equals("non_personalized")) {
                        c = 1;
                    }
                } else if (str.equals("personalized")) {
                    c = 0;
                }
            } else if (str.equals("ad_free")) {
                c = 2;
            }
            switch (c) {
                case 0:
                    consentStatus = ConsentStatus.PERSONALIZED;
                    break;
                case 1:
                    consentStatus = ConsentStatus.NON_PERSONALIZED;
                    break;
                case 2:
                    consentStatus = ConsentStatus.UNKNOWN;
                    z = true;
                    break;
                default:
                    consentStatus = ConsentStatus.UNKNOWN;
                    break;
            }
            ConsentInformation.getInstance(this.context).setConsentStatus(consentStatus, "form");
            this.listener.onConsentFormClosed(consentStatus, Boolean.valueOf(z));
        }
    }

    public void show() {
        if (this.loadState != LoadState.LOADED) {
            this.listener.onConsentFormError("Consent form is not ready to be displayed.");
        } else if (ConsentInformation.getInstance(this.context).isTaggedForUnderAgeOfConsent()) {
            this.listener.onConsentFormError("Error: tagged for under age of consent");
        } else {
            this.dialog.getWindow().setLayout(-1, -1);
            this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            this.dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                public void onShow(DialogInterface dialogInterface) {
                    ConsentForm.this.listener.onConsentFormOpened();
                }
            });
            this.dialog.show();
            if (!this.dialog.isShowing()) {
                this.listener.onConsentFormError("Consent form could not be displayed.");
            }
        }
    }

    public boolean isShowing() {
        return this.dialog.isShowing();
    }
}
