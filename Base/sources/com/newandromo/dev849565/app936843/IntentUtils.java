package com.newandromo.dev849565.app936843;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.Build;
import android.widget.Toast;
import com.bumptech.glide.load.Key;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class IntentUtils {
    public static final String NAVIGATION_PAGE = "com.newandromo.dev849565.app936843.NAVIGATION_PAGE";
    private static final String TAG = "IntentUtils";
    public static final AndroidMarket appStoreTarget = AndroidMarket.fromInt(0);
    private static final int intAppStoreTarget = 0;

    public enum AndroidMarket {
        GOOGLE_PLAY(0),
        AMAZON_APP_STORE(1),
        SAMSUNG_APP_STORE(2);
        
        private final int value;

        private AndroidMarket(int i) {
            this.value = i;
        }

        public final int getValue() {
            return this.value;
        }

        public static AndroidMarket fromInt(int i) {
            if (GOOGLE_PLAY.getValue() == i) {
                return GOOGLE_PLAY;
            }
            if (AMAZON_APP_STORE.getValue() == i) {
                return AMAZON_APP_STORE;
            }
            if (SAMSUNG_APP_STORE.getValue() == i) {
                return SAMSUNG_APP_STORE;
            }
            return GOOGLE_PLAY;
        }
    }

    public static void invokeCall(Context context, String str) {
        Intent intent = new Intent("android.intent.action.DIAL");
        try {
            str = URLEncoder.encode(str, Key.STRING_CHARSET_NAME);
        } catch (UnsupportedEncodingException unused) {
        }
        intent.setData(Uri.parse("tel:" + str));
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused2) {
            Toast.makeText(context, R.string.dialer_not_available, 0).show();
        }
    }

    public static void invokeEmail(Context context, String str, String str2, String str3) {
        String[] strArr = {str};
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.EMAIL", strArr);
        intent.putExtra("android.intent.extra.SUBJECT", str2);
        intent.putExtra("android.intent.extra.TEXT", str3);
        intent.setType("message/rfc822");
        try {
            context.startActivity(Intent.createChooser(intent, context.getString(R.string.send_using_chooser_title)));
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(context, R.string.no_email_clients_configured, 0).show();
        }
    }

    public static boolean shoudTargetAmazon() {
        return appStoreTarget == AndroidMarket.AMAZON_APP_STORE || isDeviceAKindleFire();
    }

    public static boolean isDeviceAKindleFire() {
        return Build.MANUFACTURER.equals("Amazon") && (Build.MODEL.equals("Kindle Fire") || Build.MODEL.startsWith("KF"));
    }

    public static boolean shoudTargetSamsung() {
        return appStoreTarget == AndroidMarket.SAMSUNG_APP_STORE;
    }

    public static boolean isSamsungDevice() {
        return SamsungUtils.isSamsungDevice();
    }

    public static String getSamsungAppStoreLink(Context context) {
        return context != null ? getSamsungAppStoreLink(context.getPackageName()) : "";
    }

    public static String getSamsungAppStoreLink(String str) {
        if (str == null) {
            return "";
        }
        return "samsungapps://ProductDetail/" + str;
    }

    public static Intent createAppShareIntent(Context context) {
        if (context == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        if (shoudTargetAmazon()) {
            String string = context.getString(R.string.share_app_amazon);
            intent.putExtra("android.intent.extra.TEXT", String.format(string, new Object[]{context.getString(R.string.app_name), "http://www.amazon.com/gp/mas/dl/android?p=" + context.getPackageName()}));
            return intent;
        } else if (shoudTargetSamsung()) {
            String string2 = context.getString(R.string.share_app_samsung);
            intent.putExtra("android.intent.extra.TEXT", String.format(string2, new Object[]{context.getString(R.string.app_name), "http://www.samsungapps.com/appquery/appDetail.as?appId=" + context.getPackageName()}));
            return intent;
        } else {
            String string3 = context.getString(R.string.share_app_google);
            intent.putExtra("android.intent.extra.TEXT", String.format(string3, new Object[]{context.getString(R.string.app_name), "https://play.google.com/store/apps/details?id=" + context.getPackageName()}));
            return intent;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x000f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void invokeMarket(android.content.Context r2, java.lang.String r3, java.lang.String r4) {
        /*
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "android.intent.action.VIEW"
            android.net.Uri r3 = android.net.Uri.parse(r3)
            r0.<init>(r1, r3)
            r2.startActivity(r0)     // Catch:{ ActivityNotFoundException -> 0x000f }
            goto L_0x003d
        L_0x000f:
            openUrlInDefaultBrowserWithFallback(r2, r4)     // Catch:{ ActivityNotFoundException -> 0x0013 }
            goto L_0x003d
        L_0x0013:
            boolean r3 = shoudTargetAmazon()
            r4 = 2131558603(0x7f0d00cb, float:1.8742526E38)
            r0 = 0
            if (r3 == 0) goto L_0x0025
            android.widget.Toast r2 = android.widget.Toast.makeText(r2, r4, r0)
            r2.show()
            goto L_0x003d
        L_0x0025:
            boolean r3 = shoudTargetSamsung()
            if (r3 == 0) goto L_0x0033
            android.widget.Toast r2 = android.widget.Toast.makeText(r2, r4, r0)
            r2.show()
            goto L_0x003d
        L_0x0033:
            r3 = 2131558604(0x7f0d00cc, float:1.8742529E38)
            android.widget.Toast r2 = android.widget.Toast.makeText(r2, r3, r0)
            r2.show()
        L_0x003d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newandromo.dev849565.app936843.IntentUtils.invokeMarket(android.content.Context, java.lang.String, java.lang.String):void");
    }

    public static String convertGoogleMarketUriToAmazonSubLink(String str) {
        if (str == null) {
            return "";
        }
        int indexOf = str.indexOf("details?id=");
        if (indexOf > -1) {
            return "android?p=" + str.substring(indexOf + "details?id=".length());
        }
        int indexOf2 = str.indexOf("developer?id=");
        if (indexOf2 > -1) {
            return "android?s=" + str.substring(indexOf2 + "developer?id=".length());
        }
        int indexOf3 = str.indexOf("?q=pub:");
        if (indexOf3 > -1) {
            return "android?s=" + str.substring(indexOf3 + "?q=pub:".length());
        }
        int indexOf4 = str.indexOf("?q=");
        if (indexOf4 > -1) {
            return "android?s=" + str.substring(indexOf4 + "?q=".length());
        }
        int indexOf5 = str.indexOf("?asin=");
        if (indexOf5 > -1) {
            return "android" + str.substring(indexOf5);
        }
        int indexOf6 = str.indexOf("/gp/product/");
        if (indexOf6 <= -1) {
            return "";
        }
        return "android?asin=" + str.substring(indexOf6 + "/gp/product/".length());
    }

    public static String convertGoogleMarketUriToSamsungDeepLink(Context context, String str) {
        String samsungAppStoreLink = getSamsungAppStoreLink(context);
        if (str == null || str.equals("")) {
            return samsungAppStoreLink;
        }
        Uri parse = Uri.parse(str);
        if (parse != null) {
            String queryParameter = parse.getQueryParameter("id");
            if (queryParameter != null) {
                return getSamsungAppStoreLink(queryParameter);
            }
            return samsungAppStoreLink;
        }
        int indexOf = str.indexOf("details?id=");
        return indexOf > -1 ? getSamsungAppStoreLink(str.substring(indexOf + "details?id=".length())) : samsungAppStoreLink;
    }

    public static void openUrlInBrowser(Context context, String str) throws ActivityNotFoundException {
        Intent intent = new Intent("android.intent.action.VIEW");
        if (str != null && !str.equals("")) {
            intent.setData(Uri.parse(str));
            intent.addCategory("android.intent.category.BROWSABLE");
            context.startActivity(intent);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x000a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void openUrlInDefaultBrowserWithFallback(android.content.Context r3, java.lang.String r4) throws android.content.ActivityNotFoundException {
        /*
            java.lang.String r0 = "android.intent.category.BROWSABLE"
            java.lang.String r1 = "com.android.browser"
            java.lang.String r2 = "com.android.browser.BrowserActivity"
            invokeActionView(r3, r4, r0, r1, r2)     // Catch:{ ActivityNotFoundException -> 0x000e, SecurityException -> 0x000a }
            goto L_0x001f
        L_0x000a:
            openUrlInBrowser(r3, r4)     // Catch:{ ActivityNotFoundException | SecurityException -> 0x001f }
            goto L_0x001f
        L_0x000e:
            java.lang.String r0 = "android.intent.category.BROWSABLE"
            java.lang.String r1 = "com.google.android.browser"
            java.lang.String r2 = "com.google.android.browser.BrowserActivity"
            invokeActionView(r3, r4, r0, r1, r2)     // Catch:{ ActivityNotFoundException -> 0x001c, SecurityException -> 0x0018 }
            goto L_0x001f
        L_0x0018:
            openUrlInBrowser(r3, r4)
            goto L_0x001f
        L_0x001c:
            openUrlInBrowser(r3, r4)
        L_0x001f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newandromo.dev849565.app936843.IntentUtils.openUrlInDefaultBrowserWithFallback(android.content.Context, java.lang.String):void");
    }

    private static void invokeActionView(Context context, String str, String str2, String str3, String str4) throws ActivityNotFoundException {
        Intent intent = new Intent("android.intent.action.VIEW");
        if (str != null && !str.equals("")) {
            intent.setData(Uri.parse(str));
            intent.addCategory(str2);
            intent.setClassName(str3, str4);
            context.startActivity(intent);
        }
    }

    public static void invokeMapAddress(Context context, String str, String str2) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("geo:0,0?q=" + str + "(" + str2 + ")"));
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(context, R.string.no_app_found_to_open_map_location, 0).show();
        }
    }

    public static void invokeMapCoordinates(Context context, String str, String str2) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("geo:0,0?q=" + str + "(" + str2 + ")"));
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(context, R.string.no_app_found_to_open_map_location, 0).show();
        }
    }

    public static boolean invokeActivityAndromoProtocol(Context context, String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        return invokeActivityAndromoProtocol(context, Uri.parse(str));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00ac, code lost:
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        return false;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[ExcHandler: ClassNotFoundException | IllegalAccessException | InstantiationException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:12:0x0053] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean invokeActivityAndromoProtocol(android.content.Context r9, android.net.Uri r10) {
        /*
            r0 = 0
            if (r9 == 0) goto L_0x00b1
            boolean r1 = com.newandromo.dev849565.app936843.UriUtils.isAndromoProtocol(r10)
            if (r1 == 0) goto L_0x00b1
            java.lang.String r1 = r10.getHost()
            java.lang.String r10 = r10.getPath()
            java.lang.String r2 = ""
            java.lang.String r3 = "dashboard"
            boolean r3 = r3.equalsIgnoreCase(r1)
            if (r3 == 0) goto L_0x0047
            android.content.res.Resources r1 = r9.getResources()
            r3 = 2131558461(0x7f0d003d, float:1.8742238E38)
            java.lang.String r1 = r1.getString(r3)
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L_0x004b
            java.lang.String r2 = r9.getPackageName()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r2)
            java.lang.String r2 = "."
            r3.append(r2)
            r3.append(r1)
            java.lang.String r2 = r3.toString()
            goto L_0x004b
        L_0x0047:
            java.lang.String r2 = com.newandromo.dev849565.app936843.ActionBarUtils.searchForActivityClass(r9, r1)
        L_0x004b:
            java.lang.String r1 = ""
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00b1
            java.lang.Class r1 = java.lang.Class.forName(r2)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x00b1 }
            java.lang.Class<android.app.Activity> r2 = android.app.Activity.class
            boolean r2 = r2.isAssignableFrom(r1)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x00b1 }
            r3 = 1
            if (r2 == 0) goto L_0x0088
            android.content.Intent r2 = new android.content.Intent     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x00b1 }
            r2.<init>(r9, r1)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x00b1 }
            r1 = 67108864(0x4000000, float:1.5046328E-36)
            r2.addFlags(r1)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x00b1 }
            if (r10 == 0) goto L_0x0079
            java.lang.String r1 = ""
            boolean r1 = r10.equals(r1)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x00b1 }
            if (r1 != 0) goto L_0x0079
            java.lang.String r1 = "com.newandromo.dev849565.app936843.NAVIGATION_PAGE"
            r2.putExtra(r1, r10)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x00b1 }
        L_0x0079:
            boolean r10 = r9 instanceof android.app.Activity     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x00b1 }
            if (r10 == 0) goto L_0x0083
            android.app.Activity r9 = (android.app.Activity) r9     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x00b1 }
            com.newandromo.dev849565.app936843.AndromoUtils.startActivityWithInterstitial(r9, r2)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x00b1 }
            goto L_0x0086
        L_0x0083:
            r9.startActivity(r2)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x00b1 }
        L_0x0086:
            r0 = 1
            goto L_0x00b1
        L_0x0088:
            java.lang.Object r10 = r1.newInstance()     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x00b1 }
            java.lang.reflect.Method[] r1 = r1.getDeclaredMethods()     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x00b1 }
            int r2 = r1.length     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x00b1 }
            r4 = 0
            r5 = 0
        L_0x0093:
            if (r4 >= r2) goto L_0x00b0
            r6 = r1[r4]     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x00b1 }
            java.lang.String r7 = r6.getName()     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x00b1 }
            java.lang.String r8 = "runIntent"
            boolean r7 = r7.equals(r8)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x00b1 }
            if (r7 == 0) goto L_0x00ad
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ InvocationTargetException -> 0x00ac, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x00b1, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x00b1 }
            r5[r0] = r9     // Catch:{ InvocationTargetException -> 0x00ac, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x00b1, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x00b1 }
            r6.invoke(r10, r5)     // Catch:{ InvocationTargetException -> 0x00ac, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x00b1, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x00b1 }
            r5 = 1
            goto L_0x00ad
        L_0x00ac:
            r5 = 0
        L_0x00ad:
            int r4 = r4 + 1
            goto L_0x0093
        L_0x00b0:
            r0 = r5
        L_0x00b1:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newandromo.dev849565.app936843.IntentUtils.invokeActivityAndromoProtocol(android.content.Context, android.net.Uri):boolean");
    }

    public static boolean isAndromoProtocol(Context context, String str) {
        return isAndromoProtocol(str);
    }

    public static boolean isAndromoProtocol(String str) {
        return str != null && !str.equals("") && UriUtils.isAndromoProtocol(Uri.parse(str));
    }

    public static void invokeYouTube(Context context, String str) {
        Uri parse;
        Intent intent;
        if (str != null && !str.equals("") && (parse = Uri.parse(str)) != null) {
            String queryParameter = parse.getQueryParameter("v");
            if (queryParameter != null) {
                intent = new Intent("android.intent.action.VIEW", Uri.parse("vnd.youtube:" + queryParameter));
                intent.putExtra("VIDEO_ID", queryParameter);
                if (context.getPackageManager().queryIntentActivities(intent, 65536).size() == 0) {
                    intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                }
            } else {
                intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            }
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                try {
                    context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                } catch (Exception unused2) {
                }
            }
        }
    }

    public static Intent getSmsIntent(Uri uri, String str) {
        Intent intent = new Intent("android.intent.action.SENDTO");
        String uri2 = uri.toString();
        String str2 = uri2.split("[:?]")[1];
        if (!str.endsWith(":")) {
            str = str + ":";
        }
        UrlQuerySanitizer urlQuerySanitizer = new UrlQuerySanitizer();
        urlQuerySanitizer.registerParameter("body", UrlQuerySanitizzr.getAllButNulLegal());
        urlQuerySanitizer.parseUrl(uri2);
        String value = urlQuerySanitizer.getValue("body");
        if (!StringUtils.isNullOrEmpty(str2)) {
            intent.setData(Uri.parse(str + str2));
        } else {
            intent.setData(Uri.parse(str));
        }
        if (!StringUtils.isNullOrEmpty(value)) {
            intent.putExtra("sms_body", value);
        }
        return intent;
    }
}
