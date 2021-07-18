package com.newandromo.dev849565.app936843;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.GeolocationPermissions;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.newandromo.dev849565.app936843.AdHelper;
import com.newandromo.dev849565.app936843.AndromoActivity;
import java.lang.reflect.InvocationTargetException;

public class Custompage_1601126045_8a479573a2 extends AndromoActivity {
    /* access modifiers changed from: private */
    public static String[] PERMISSIONS_LOCATION = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    private static final String TAG = "WebView";
    private static final int WEBVIEW_REQUEST_LOCATION = 1;
    private static AndromoActivity.ParentCheck mParentCheck = new AndromoActivity.ParentCheck();
    /* access modifiers changed from: private */
    public boolean bGeolocationEnabled = false;
    /* access modifiers changed from: private */
    public boolean bOpenLinksExternally = true;
    private boolean bZoomEnabled = true;
    /* access modifiers changed from: private */
    public String failUrl;
    int mActivityIndex = -1;
    /* access modifiers changed from: private */
    public GeolocationPermissions.Callback mCallback = null;
    private long mChannelID;
    /* access modifiers changed from: private */
    public boolean mLoadingPage = false;
    /* access modifiers changed from: private */
    public String mOrigin = null;
    private MenuItem mRefresh;
    private int position = -1;
    /* access modifiers changed from: private */
    public boolean visible;
    WebView webView;

    /* access modifiers changed from: protected */
    public String getActivityTypeForAnalytics() {
        switch (2) {
            case 1:
                return "HTML Archive";
            case 2:
                return "Custom Page";
            default:
                return "Website";
        }
    }

    /* access modifiers changed from: protected */
    public boolean getHandleCustomWindowColor() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isDetailActivity() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isToolbarEnabled() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isParentReachable() {
        return mParentCheck.isParentReachable(this, "material");
    }

    public String[] getParentClassNamesArray() {
        return getResources().getStringArray(R.array.activity_000_classes);
    }

    /* access modifiers changed from: protected */
    public String getActivityTitleForAnalytics() {
        return getString(R.string.Custompage_1601126045_8a479573a2_activity_title);
    }

    /* access modifiers changed from: protected */
    public void setContentView() {
        setContentView((int) R.layout.webview_main);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.mActivityIndex == -1) {
            this.mActivityIndex = ActionBarUtils.getActivityIndexFromClassNamesArray((Context) this, (int) R.array.activity_000_classes);
        }
        setToolbarTitle((int) R.string.Custompage_1601126045_8a479573a2_activity_title);
        CookieSyncManager.createInstance(this);
        this.webView = (WebView) findViewById(R.id.webView1);
        this.webView.setWebChromeClient(new MyWebChromeClient());
        if (this.bGeolocationEnabled) {
            this.webView.getSettings().setGeolocationEnabled(true);
        }
        this.webView.setWebViewClient(new LinkWebViewClient());
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.webView.getSettings().setLightTouchEnabled(true);
        this.webView.getSettings().setDatabaseEnabled(true);
        WebSettings settings = this.webView.getSettings();
        settings.setDatabasePath("/data/data/" + getPackageName() + "/databases/");
        this.webView.getSettings().setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= 21) {
            this.webView.getSettings().setMixedContentMode(2);
            CookieManager.getInstance().setAcceptThirdPartyCookies(this.webView, true);
        }
        if (Build.VERSION.SDK_INT > 7) {
            this.webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        }
        this.webView.getSettings().setBuiltInZoomControls(this.bZoomEnabled);
        this.webView.getSettings().setDisplayZoomControls(false);
        this.webView.getSettings().setSupportZoom(this.bZoomEnabled);
        this.webView.getSettings().setLoadWithOverviewMode(this.bZoomEnabled);
        this.webView.getSettings().setUseWideViewPort(this.bZoomEnabled);
        this.webView.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setDataAndType(Uri.parse(str), str4);
                try {
                    Custompage_1601126045_8a479573a2.this.startActivity(intent);
                } catch (ActivityNotFoundException unused) {
                    IntentUtils.openUrlInDefaultBrowserWithFallback(Custompage_1601126045_8a479573a2.this.webView.getContext(), str);
                }
            }
        });
        String str = "";
        Intent intent = getIntent();
        if (intent != null) {
            str = intent.getStringExtra(IntentUtils.NAVIGATION_PAGE);
        }
        if (str != null && !str.equals("")) {
            this.webView.loadUrl("file:///android_asset/Custompage_1601126045_8a479573a2" + str);
        } else if (bundle != null) {
            this.webView.restoreState(bundle);
        } else {
            this.webView.loadUrl(getString(R.string.Custompage_1601126045_8a479573a2_webview_content));
        }
        AdManager.insertBannerAd(this, (LinearLayout) findViewById(R.id.contentAdLayout), AdHelper.SlideDirection.NONE, false);
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.webView != null) {
            this.webView.saveState(bundle);
        }
    }

    private class MyWebChromeClient extends WebChromeClient {
        private MyWebChromeClient() {
        }

        public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(j2 * 2);
        }

        public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
            if (!Custompage_1601126045_8a479573a2.this.bGeolocationEnabled) {
                callback.invoke(str, false, false);
            } else if (ContextCompat.checkSelfPermission(Custompage_1601126045_8a479573a2.this, "android.permission.ACCESS_FINE_LOCATION") == 0 && ContextCompat.checkSelfPermission(Custompage_1601126045_8a479573a2.this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                callback.invoke(str, true, false);
            } else {
                GeolocationPermissions.Callback unused = Custompage_1601126045_8a479573a2.this.mCallback = callback;
                String unused2 = Custompage_1601126045_8a479573a2.this.mOrigin = str;
                if (ActivityCompat.shouldShowRequestPermissionRationale(Custompage_1601126045_8a479573a2.this, "android.permission.ACCESS_FINE_LOCATION") || ActivityCompat.shouldShowRequestPermissionRationale(Custompage_1601126045_8a479573a2.this, "android.permission.ACCESS_COARSE_LOCATION")) {
                    showMessageOKCancel(Custompage_1601126045_8a479573a2.this.getString(R.string.location_permission_request), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            switch (i) {
                                case -2:
                                    Custompage_1601126045_8a479573a2.this.mCallback.invoke(Custompage_1601126045_8a479573a2.this.mOrigin, false, false);
                                    return;
                                case -1:
                                    ActivityCompat.requestPermissions(Custompage_1601126045_8a479573a2.this, Custompage_1601126045_8a479573a2.PERMISSIONS_LOCATION, 1);
                                    return;
                                default:
                                    return;
                            }
                        }
                    }, new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialogInterface) {
                            Custompage_1601126045_8a479573a2.this.mCallback.invoke(Custompage_1601126045_8a479573a2.this.mOrigin, false, false);
                        }
                    });
                } else {
                    ActivityCompat.requestPermissions(Custompage_1601126045_8a479573a2.this, Custompage_1601126045_8a479573a2.PERMISSIONS_LOCATION, 1);
                }
            }
        }

        private void showMessageOKCancel(String str, DialogInterface.OnClickListener onClickListener, DialogInterface.OnCancelListener onCancelListener) {
            new AlertDialog.Builder(Custompage_1601126045_8a479573a2.this).setMessage((CharSequence) str).setPositiveButton(17039370, onClickListener).setNegativeButton(17039360, onClickListener).setOnCancelListener(onCancelListener).create().show();
        }
    }

    private class LinkWebViewClient extends WebViewClient {
        String mLastURL;

        public void onLoadResource(WebView webView, String str) {
        }

        private LinkWebViewClient() {
            this.mLastURL = "";
        }

        public boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
            if (Custompage_1601126045_8a479573a2.this.webView == null) {
                return true;
            }
            Custompage_1601126045_8a479573a2.this.webView.destroy();
            Custompage_1601126045_8a479573a2.this.webView = null;
            return true;
        }

        private void handleError(final WebView webView, int i, String str) {
            if (Custompage_1601126045_8a479573a2.this.failUrl != null && Custompage_1601126045_8a479573a2.this.failUrl.equals(str)) {
                webView.loadUrl("about:blank");
                if (Custompage_1601126045_8a479573a2.this.visible && !Custompage_1601126045_8a479573a2.this.isFinishing()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Custompage_1601126045_8a479573a2.this);
                    builder.setTitle((CharSequence) "No Internet connection");
                    builder.setMessage((CharSequence) "Please check your connection and try again.");
                    builder.setPositiveButton((CharSequence) "Retry", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (Custompage_1601126045_8a479573a2.this.failUrl != null) {
                                webView.loadUrl(Custompage_1601126045_8a479573a2.this.failUrl);
                            }
                        }
                    });
                    builder.setNegativeButton((CharSequence) "Cancel", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.create().show();
                }
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (!str.equals("about:blank")) {
                String unused = Custompage_1601126045_8a479573a2.this.failUrl = str;
            }
            boolean unused2 = Custompage_1601126045_8a479573a2.this.mLoadingPage = true;
            Custompage_1601126045_8a479573a2.this.startProgress();
        }

        public void onPageFinished(WebView webView, String str) {
            boolean unused = Custompage_1601126045_8a479573a2.this.mLoadingPage = false;
            Custompage_1601126045_8a479573a2.this.stopProgress();
            CookieSyncManager.getInstance().sync();
            if (str.equals("about:blank")) {
                webView.clearHistory();
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            handleError(webView, i, str2);
        }

        @TargetApi(24)
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            handleError(webView, webResourceError.getErrorCode(), webResourceRequest.getUrl().toString());
        }

        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            return super.shouldInterceptRequest(webView, str);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str != null && !str.equals("")) {
                return handleUri(webView, Uri.parse(str));
            }
            this.mLastURL = str;
            return true;
        }

        @TargetApi(24)
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            return handleUri(webView, webResourceRequest.getUrl());
        }

        private boolean tryIntent(String str, Uri uri, int i) {
            this.mLastURL = uri.toString();
            try {
                Custompage_1601126045_8a479573a2.this.startActivity(new Intent(str, uri));
                return true;
            } catch (Exception unused) {
                if (i != 0) {
                    Toast.makeText(Custompage_1601126045_8a479573a2.this.getApplicationContext(), i, 0).show();
                }
                return false;
            }
        }

        private boolean tryIntent(String str, Uri uri, CharSequence charSequence) {
            this.mLastURL = uri.toString();
            try {
                Custompage_1601126045_8a479573a2.this.startActivity(new Intent(str, uri));
                return true;
            } catch (Exception unused) {
                if (charSequence != null && charSequence.length() > 0) {
                    Toast.makeText(Custompage_1601126045_8a479573a2.this.getApplicationContext(), charSequence, 0).show();
                }
                return false;
            }
        }

        private boolean trySmsIntent(Uri uri, String str) {
            this.mLastURL = uri.toString();
            try {
                Custompage_1601126045_8a479573a2.this.startActivity(IntentUtils.getSmsIntent(uri, str));
                return true;
            } catch (Exception unused) {
                Toast.makeText(Custompage_1601126045_8a479573a2.this.getApplicationContext(), R.string.no_app_found_to_open_url, 0).show();
                return false;
            }
        }

        private boolean isHandledScheme(Uri uri) {
            return uri != null && isHandledScheme(uri.getScheme());
        }

        private boolean isHandledScheme(String str) {
            return "andromo".equals(str) || "tel".equals(str) || "mailto".equals(str) || "geo".equals(str) || "market".equals(str) || "vnd.youtube".equals(str) || "sms".equals(str) || "smsto".equals(str);
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(9:41|42|43|44|45|47|48|49|50) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00f1 */
        /* JADX WARNING: Removed duplicated region for block: B:60:0x010e  */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x0130  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean handleUri(android.webkit.WebView r9, android.net.Uri r10) {
            /*
                r8 = this;
                java.lang.String r0 = r10.getScheme()
                java.lang.String r1 = r10.toString()
                boolean r2 = com.newandromo.dev849565.app936843.UriUtils.isAndromoProtocol(r10)
                r3 = 2131558581(0x7f0d00b5, float:1.8742482E38)
                r4 = 0
                r5 = 1
                if (r2 == 0) goto L_0x003d
                com.newandromo.dev849565.app936843.Custompage_1601126045_8a479573a2 r9 = com.newandromo.dev849565.app936843.Custompage_1601126045_8a479573a2.this
                boolean r9 = com.newandromo.dev849565.app936843.IntentUtils.invokeActivityAndromoProtocol((android.content.Context) r9, (android.net.Uri) r10)
                if (r9 != 0) goto L_0x0036
                com.newandromo.dev849565.app936843.Custompage_1601126045_8a479573a2 r9 = com.newandromo.dev849565.app936843.Custompage_1601126045_8a479573a2.this
                android.content.Context r9 = r9.getApplicationContext()
                com.newandromo.dev849565.app936843.Custompage_1601126045_8a479573a2 r0 = com.newandromo.dev849565.app936843.Custompage_1601126045_8a479573a2.this
                java.lang.Object[] r1 = new java.lang.Object[r5]
                java.lang.String r2 = com.newandromo.dev849565.app936843.UriUtils.toSafeString(r10)
                r1[r4] = r2
                java.lang.String r0 = r0.getString(r3, r1)
                android.widget.Toast r9 = android.widget.Toast.makeText(r9, r0, r4)
                r9.show()
            L_0x0036:
                java.lang.String r9 = r10.toString()
                r8.mLastURL = r9
                return r5
            L_0x003d:
                java.lang.String r2 = "tel"
                boolean r2 = r2.equals(r0)
                r6 = 5432(0x1538, float:7.612E-42)
                if (r2 == 0) goto L_0x0058
                android.net.Uri r9 = com.newandromo.dev849565.app936843.UriUtils.escapeForTel((android.net.Uri) r10)
                android.net.Uri r9 = com.newandromo.dev849565.app936843.UriSanitizer.sanitize(r9, r6)
                java.lang.String r10 = "android.intent.action.DIAL"
                r0 = 2131558551(0x7f0d0097, float:1.874242E38)
                r8.tryIntent((java.lang.String) r10, (android.net.Uri) r9, (int) r0)
                return r5
            L_0x0058:
                com.newandromo.dev849565.app936843.Custompage_1601126045_8a479573a2 r2 = com.newandromo.dev849565.app936843.Custompage_1601126045_8a479573a2.this
                boolean r2 = r2.bOpenLinksExternally
                if (r2 != 0) goto L_0x0078
                java.lang.String r2 = "sms"
                boolean r2 = r2.equals(r0)
                if (r2 != 0) goto L_0x0070
                java.lang.String r2 = "smsto"
                boolean r2 = r2.equals(r0)
                if (r2 == 0) goto L_0x0078
            L_0x0070:
                android.net.Uri r9 = com.newandromo.dev849565.app936843.UriSanitizer.sanitize(r10, r6)
                r8.trySmsIntent(r9, r0)
                return r5
            L_0x0078:
                java.lang.String r2 = "mailto"
                boolean r2 = r2.equals(r0)
                r7 = 5418(0x152a, float:7.592E-42)
                if (r2 == 0) goto L_0x008f
                android.net.Uri r9 = com.newandromo.dev849565.app936843.UriSanitizer.sanitize(r10, r7)
                java.lang.String r10 = "android.intent.action.VIEW"
                r0 = 2131558609(0x7f0d00d1, float:1.8742539E38)
                r8.tryIntent((java.lang.String) r10, (android.net.Uri) r9, (int) r0)
                return r5
            L_0x008f:
                java.lang.String r2 = "geo"
                boolean r2 = r2.equals(r0)
                if (r2 == 0) goto L_0x00a4
                android.net.Uri r9 = com.newandromo.dev849565.app936843.UriSanitizer.sanitize(r10, r7)
                java.lang.String r10 = "android.intent.action.VIEW"
                r0 = 2131558602(0x7f0d00ca, float:1.8742524E38)
                r8.tryIntent((java.lang.String) r10, (android.net.Uri) r9, (int) r0)
                return r5
            L_0x00a4:
                java.lang.String r2 = "market"
                boolean r2 = r2.equals(r0)
                if (r2 == 0) goto L_0x00b9
                android.net.Uri r9 = com.newandromo.dev849565.app936843.UriSanitizer.sanitize(r10, r6)
                java.lang.String r10 = "android.intent.action.VIEW"
                r0 = 2131558603(0x7f0d00cb, float:1.8742526E38)
                r8.tryIntent((java.lang.String) r10, (android.net.Uri) r9, (int) r0)
                return r5
            L_0x00b9:
                java.lang.String r2 = "vnd.youtube"
                boolean r0 = r2.equals(r0)
                r2 = 2131558605(0x7f0d00cd, float:1.874253E38)
                if (r0 == 0) goto L_0x00d0
                r9 = 5383(0x1507, float:7.543E-42)
                android.net.Uri r9 = com.newandromo.dev849565.app936843.UriSanitizer.sanitize(r10, r9)
                java.lang.String r10 = "android.intent.action.VIEW"
                r8.tryIntent((java.lang.String) r10, (android.net.Uri) r9, (int) r2)
                return r5
            L_0x00d0:
                com.newandromo.dev849565.app936843.Custompage_1601126045_8a479573a2 r0 = com.newandromo.dev849565.app936843.Custompage_1601126045_8a479573a2.this
                boolean r0 = r0.bOpenLinksExternally
                if (r0 == 0) goto L_0x016e
                r0 = 0
                boolean r6 = com.newandromo.dev849565.app936843.UriUtils.isAssetUri(r10)     // Catch:{ Exception -> 0x0106 }
                if (r6 != 0) goto L_0x0103
                android.net.Uri r6 = com.newandromo.dev849565.app936843.UriSanitizer.sanitize(r10)     // Catch:{ Exception -> 0x0106 }
                java.lang.String r7 = r6.toString()     // Catch:{ Exception -> 0x0101 }
                android.content.Context r9 = r9.getContext()     // Catch:{ ActivityNotFoundException -> 0x00f1 }
                com.newandromo.dev849565.app936843.IntentUtils.openUrlInDefaultBrowserWithFallback(r9, r7)     // Catch:{ ActivityNotFoundException -> 0x00f1 }
                goto L_0x00fe
            L_0x00ef:
                goto L_0x0108
            L_0x00f1:
                com.newandromo.dev849565.app936843.Custompage_1601126045_8a479573a2 r9 = com.newandromo.dev849565.app936843.Custompage_1601126045_8a479573a2.this     // Catch:{ Exception -> 0x00ef }
                android.content.Context r9 = r9.getApplicationContext()     // Catch:{ Exception -> 0x00ef }
                android.widget.Toast r9 = android.widget.Toast.makeText(r9, r2, r4)     // Catch:{ Exception -> 0x00ef }
                r9.show()     // Catch:{ Exception -> 0x00ef }
            L_0x00fe:
                r8.mLastURL = r7     // Catch:{ Exception -> 0x00ef }
                return r5
            L_0x0101:
                r7 = r0
                goto L_0x0108
            L_0x0103:
                r8.mLastURL = r1     // Catch:{ Exception -> 0x0106 }
                return r4
            L_0x0106:
                r6 = r0
                r7 = r6
            L_0x0108:
                boolean r9 = com.newandromo.dev849565.app936843.UriUtils.isAssetUri(r10)
                if (r9 == 0) goto L_0x0130
                com.newandromo.dev849565.app936843.Custompage_1601126045_8a479573a2 r9 = com.newandromo.dev849565.app936843.Custompage_1601126045_8a479573a2.this
                android.content.Context r9 = r9.getApplicationContext()
                com.newandromo.dev849565.app936843.Custompage_1601126045_8a479573a2 r0 = com.newandromo.dev849565.app936843.Custompage_1601126045_8a479573a2.this
                java.lang.Object[] r2 = new java.lang.Object[r5]
                java.lang.String r10 = com.newandromo.dev849565.app936843.UriUtils.toSafeString(r10)
                java.lang.String r10 = com.newandromo.dev849565.app936843.UriUtils.trimAssetPath(r10)
                r2[r4] = r10
                java.lang.String r10 = r0.getString(r3, r2)
                android.widget.Toast r9 = android.widget.Toast.makeText(r9, r10, r4)
                r9.show()
                r8.mLastURL = r1
                goto L_0x016d
            L_0x0130:
                if (r6 == 0) goto L_0x0150
                com.newandromo.dev849565.app936843.Custompage_1601126045_8a479573a2 r9 = com.newandromo.dev849565.app936843.Custompage_1601126045_8a479573a2.this
                android.content.Context r9 = r9.getApplicationContext()
                com.newandromo.dev849565.app936843.Custompage_1601126045_8a479573a2 r10 = com.newandromo.dev849565.app936843.Custompage_1601126045_8a479573a2.this
                java.lang.Object[] r0 = new java.lang.Object[r5]
                java.lang.String r1 = com.newandromo.dev849565.app936843.UriUtils.toSafeString(r6)
                r0[r4] = r1
                java.lang.String r10 = r10.getString(r3, r0)
                android.widget.Toast r9 = android.widget.Toast.makeText(r9, r10, r4)
                r9.show()
                r8.mLastURL = r7
                goto L_0x016d
            L_0x0150:
                com.newandromo.dev849565.app936843.Custompage_1601126045_8a479573a2 r9 = com.newandromo.dev849565.app936843.Custompage_1601126045_8a479573a2.this
                android.content.Context r9 = r9.getApplicationContext()
                com.newandromo.dev849565.app936843.Custompage_1601126045_8a479573a2 r0 = com.newandromo.dev849565.app936843.Custompage_1601126045_8a479573a2.this
                java.lang.Object[] r2 = new java.lang.Object[r5]
                java.lang.String r10 = com.newandromo.dev849565.app936843.UriUtils.toSafeString(r10)
                r2[r4] = r10
                java.lang.String r10 = r0.getString(r3, r2)
                android.widget.Toast r9 = android.widget.Toast.makeText(r9, r10, r4)
                r9.show()
                r8.mLastURL = r1
            L_0x016d:
                return r5
            L_0x016e:
                java.lang.String r10 = "file:///android_asset/webkit/"
                boolean r10 = r10.equals(r1)
                if (r10 == 0) goto L_0x0177
                return r4
            L_0x0177:
                java.lang.String r10 = "about:blank"
                boolean r10 = r10.equalsIgnoreCase(r1)
                if (r10 == 0) goto L_0x0195
                java.lang.String r10 = r8.mLastURL
                android.net.Uri r10 = android.net.Uri.parse(r10)
                boolean r0 = r8.isHandledScheme((android.net.Uri) r10)
                if (r0 == 0) goto L_0x018f
                r8.handleUri(r9, r10)
                goto L_0x0194
            L_0x018f:
                java.lang.String r10 = r8.mLastURL
                r9.loadUrl(r10)
            L_0x0194:
                return r5
            L_0x0195:
                r8.mLastURL = r1
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.newandromo.dev849565.app936843.Custompage_1601126045_8a479573a2.LinkWebViewClient.handleUri(android.webkit.WebView, android.net.Uri):boolean");
        }
    }

    private boolean verifyPermissions(int[] iArr) {
        if (iArr.length < 1) {
            return false;
        }
        for (int i : iArr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i != 1 || this.mCallback == null || this.mOrigin == null) {
            super.onRequestPermissionsResult(i, strArr, iArr);
            return;
        }
        this.mCallback.invoke(this.mOrigin, verifyPermissions(iArr), false);
        this.mCallback = null;
        this.mOrigin = null;
    }

    /* access modifiers changed from: protected */
    public void startProgress() {
        if (this.mRefresh != null) {
            MenuItemCompat.setActionView(this.mRefresh, (int) R.layout.actionbar_indeterminate_progress);
        }
    }

    /* access modifiers changed from: protected */
    public void stopProgress() {
        if (this.mRefresh != null) {
            MenuItemCompat.setActionView(this.mRefresh, (View) null);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !this.webView.canGoBack()) {
            return super.onKeyDown(i, keyEvent);
        }
        this.webView.goBack();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.webview_options_menu, menu);
        this.mRefresh = menu.findItem(R.id.refresh);
        if (this.mLoadingPage) {
            startProgress();
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.refresh) {
            return super.onOptionsItemSelected(menuItem);
        }
        if (this.webView == null) {
            return true;
        }
        if (this.failUrl != null) {
            this.webView.loadUrl(this.failUrl);
            return true;
        }
        this.webView.reload();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        callHiddenWebViewMethod("onPause");
        CookieSyncManager.getInstance().stopSync();
        this.visible = false;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        callHiddenWebViewMethod("onResume");
        CookieSyncManager.getInstance().startSync();
        this.visible = true;
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onDelayedAdLoad() {
        super.onDelayedAdLoad();
        AdManager.insertBannerAd(this, (LinearLayout) findViewById(R.id.contentAdLayout), AdHelper.SlideDirection.NONE, false);
    }

    private void callHiddenWebViewMethod(String str) {
        if (this.webView != null) {
            try {
                WebView.class.getMethod(str, new Class[0]).invoke(this.webView, new Object[0]);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            }
        }
    }
}
