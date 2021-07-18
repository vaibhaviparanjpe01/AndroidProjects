package com.flurry.android;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.flurry.android.FlurryPrivacySession;
import com.flurry.sdk.bn;
import com.flurry.sdk.bo;
import com.flurry.sdk.bq;
import com.flurry.sdk.ch;
import com.flurry.sdk.ci;
import com.flurry.sdk.cj;
import com.flurry.sdk.ck;
import com.flurry.sdk.cl;
import com.flurry.sdk.cm;
import com.flurry.sdk.cu;
import com.flurry.sdk.cv;
import com.flurry.sdk.cw;
import com.flurry.sdk.db;
import com.flurry.sdk.de;
import com.flurry.sdk.df;
import com.flurry.sdk.ec;
import com.flurry.sdk.ed;
import com.flurry.sdk.eg;
import com.flurry.sdk.em;
import com.flurry.sdk.es;
import com.flurry.sdk.fa;
import com.flurry.sdk.fd;
import com.flurry.sdk.v;
import com.flurry.sdk.w;
import com.flurry.sdk.x;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

public final class FlurryAgent {
    public static String VERSION_STRING = "!SDK-VERSION-STRING!:com.flurry.android:analytics:11.6.0";
    /* access modifiers changed from: private */
    public static final String a = "FlurryAgent";
    /* access modifiers changed from: private */
    public static FlurryAgentListener b;
    private static final cv<ec> c = new cv<ec>() {
        public final /* synthetic */ void a(cu cuVar) {
            final ec ecVar = (ec) cuVar;
            ck.a().a((Runnable) new Runnable() {
                public final void run() {
                    if (AnonymousClass3.a[ecVar.d - 1] == 1 && FlurryAgent.b != null) {
                        FlurryAgent.b.onSessionStarted();
                    }
                }
            });
        }
    };
    private static boolean d = false;
    private static int e = 5;
    private static long f = 10000;
    private static boolean g = true;
    private static boolean h = true;
    private static List<FlurryModule> i = new ArrayList();
    private static String j = null;
    private static String k = null;
    private static Consent l = null;

    @Deprecated
    public static void setLocation(float f2, float f3) {
    }

    @Deprecated
    public static void setLocationCriteria(@NonNull Criteria criteria) {
    }

    @Deprecated
    public static void setLogEvents(boolean z) {
    }

    /* renamed from: com.flurry.android.FlurryAgent$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[ec.a.a().length];

        static {
            try {
                a[ec.a.e - 1] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private FlurryAgent() {
    }

    @Deprecated
    public static void setFlurryAgentListener(@NonNull FlurryAgentListener flurryAgentListener) {
        if (d()) {
            b = flurryAgentListener;
            cw.a().a("com.flurry.android.sdk.FlurrySessionEvent", c);
        }
    }

    @Deprecated
    public static void setLogEnabled(boolean z) {
        if (d()) {
            if (z) {
                db.b();
            } else {
                db.a();
            }
        }
    }

    @Deprecated
    public static void setLogLevel(int i2) {
        if (d()) {
            db.a(i2);
        }
    }

    @Deprecated
    public static void setContinueSessionMillis(long j2) {
        if (d()) {
            if (j2 < 5000) {
                db.b(a, "Invalid time set for session resumption: ".concat(String.valueOf(j2)));
            } else {
                eg.a().a("ContinueSessionMillis", (Object) Long.valueOf(j2));
            }
        }
    }

    @Deprecated
    public static void setCaptureUncaughtExceptions(boolean z) {
        if (d()) {
            eg.a().a("CaptureUncaughtExceptions", (Object) Boolean.valueOf(z));
        }
    }

    @Deprecated
    public static void setPulseEnabled(boolean z) {
        if (z) {
            db.e(a, "Flurry Pulse is not available anymore and the API will be removed in an upcoming release");
        }
    }

    @Deprecated
    public static synchronized void init(@NonNull Context context, @NonNull String str) {
        synchronized (FlurryAgent.class) {
            if (d()) {
                if (!TextUtils.isEmpty(str)) {
                    if (ck.a() != null) {
                        db.e(a, "Flurry is already initialized");
                    }
                    try {
                        fd.a();
                        ck.a(context, str);
                    } catch (Throwable th) {
                        db.a(a, "", th);
                    }
                } else {
                    throw new IllegalArgumentException("API key not specified");
                }
            }
        }
    }

    public static int getAgentVersion() {
        return cl.b();
    }

    public static String getReleaseVersion() {
        return cl.a();
    }

    @NonNull
    public static List<FlurryModule> getAddOnModules() {
        return i;
    }

    public static void setVersionName(@NonNull String str) {
        if (d()) {
            eg.a().a("VersionName", (Object) str);
        }
    }

    public static void setReportLocation(boolean z) {
        if (d()) {
            eg.a().a("ReportLocation", (Object) Boolean.valueOf(z));
        }
    }

    @Deprecated
    public static void clearLocation() {
        if (d()) {
            eg.a().a("ExplicitLocation", (Object) null);
        }
    }

    public static void addOrigin(@NonNull String str, @NonNull String str2) {
        addOrigin(str, str2, (Map<String, String>) null);
    }

    public static void addOrigin(@NonNull String str, @NonNull String str2, Map<String, String> map) {
        if (d()) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("originName not specified");
            } else if (!TextUtils.isEmpty(str2)) {
                try {
                    cm.a().a(str, str2, map);
                } catch (Throwable th) {
                    db.a(a, "", th);
                }
            } else {
                throw new IllegalArgumentException("originVersion not specified");
            }
        }
    }

    public static void setInstantAppName(@NonNull String str) {
        k = str;
    }

    public static String getInstantAppName() {
        return k;
    }

    public static synchronized Consent getFlurryConsent() {
        Consent consent;
        synchronized (FlurryAgent.class) {
            consent = l;
        }
        return consent;
    }

    public static synchronized boolean updateFlurryConsent(@NonNull Consent consent) {
        synchronized (FlurryAgent.class) {
            if (!d()) {
                return false;
            }
            if (!cj.a(consent)) {
                db.b(a, "Consent is null or illegal");
                return false;
            }
            l = consent;
            if (ci.a != null) {
                ci.a.a();
            }
            return true;
        }
    }

    @Deprecated
    public static void onStartSession(@NonNull Context context, @NonNull String str) {
        onStartSession(context);
    }

    public static void onStartSession(@NonNull Context context) {
        if (d()) {
            e();
            try {
                ed.a().b(context);
            } catch (Throwable th) {
                db.a(a, "", th);
            }
        }
    }

    public static void onEndSession(@NonNull Context context) {
        if (d()) {
            e();
            try {
                ed.a().c(context);
            } catch (Throwable th) {
                db.a(a, "", th);
            }
        }
    }

    public static boolean isSessionActive() {
        if (!d()) {
            return false;
        }
        try {
            return ed.a().d();
        } catch (Throwable th) {
            db.a(a, "", th);
            return false;
        }
    }

    public static String getSessionId() {
        if (!d()) {
            return null;
        }
        e();
        try {
            bq.a();
            return bq.b();
        } catch (Throwable th) {
            db.a(a, "", th);
            return null;
        }
    }

    @NonNull
    public static FlurryEventRecordStatus logEvent(@NonNull String str) {
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (!d()) {
            return flurryEventRecordStatus;
        }
        try {
            return x.a().a(str, (Map<String, String>) null, false, 0);
        } catch (Throwable th) {
            db.a(a, "Failed to log event: ".concat(String.valueOf(str)), th);
            return flurryEventRecordStatus;
        }
    }

    @NonNull
    public static FlurryEventRecordStatus logEvent(@NonNull String str, @NonNull Map<String, String> map) {
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (!d()) {
            return flurryEventRecordStatus;
        }
        try {
            return x.a().a(str, map, 0);
        } catch (Throwable th) {
            db.a(a, "Failed to log event: ".concat(String.valueOf(str)), th);
            return flurryEventRecordStatus;
        }
    }

    @NonNull
    public static FlurryEventRecordStatus logEvent(@NonNull String str, boolean z) {
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (!d()) {
            return flurryEventRecordStatus;
        }
        try {
            return x.a().a(str, (Map<String, String>) null, z, 0);
        } catch (Throwable th) {
            db.a(a, "Failed to log event: ".concat(String.valueOf(str)), th);
            return flurryEventRecordStatus;
        }
    }

    @NonNull
    public static FlurryEventRecordStatus logEvent(@NonNull String str, @NonNull Map<String, String> map, boolean z) {
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (!d()) {
            return flurryEventRecordStatus;
        }
        try {
            return x.a().a(str, map, z);
        } catch (Throwable th) {
            db.a(a, "Failed to log event: ".concat(String.valueOf(str)), th);
            return flurryEventRecordStatus;
        }
    }

    public static void logPayment(int i2, Intent intent, @Nullable final Map<String, String> map) {
        Object obj;
        if (d()) {
            Bundle extras = intent.getExtras();
            if (extras == null) {
                obj = null;
            } else {
                obj = extras.get("RESPONSE_CODE");
            }
            int i3 = 0;
            if (obj == null) {
                db.b(a, "Intent with no response code, assuming OK (known issue)");
            } else if (obj instanceof Integer) {
                i3 = ((Integer) obj).intValue();
            } else if (obj instanceof Long) {
                i3 = (int) ((Long) obj).longValue();
            }
            final String stringExtra = intent.getStringExtra("INAPP_PURCHASE_DATA");
            final String stringExtra2 = intent.getStringExtra("INAPP_DATA_SIGNATURE");
            if (stringExtra == null || stringExtra2 == null) {
                String str = a;
                db.b(str, "Invalid logPayment call. resultCode:" + i2 + ", responseCode:" + i3 + ", purchaseData:" + stringExtra + ", dataSignature:" + stringExtra2);
                return;
            }
            try {
                final JSONObject jSONObject = new JSONObject(stringExtra);
                if (i2 == -1 && i3 == 0) {
                    bn.a(ck.a().a, jSONObject.optString("productId"), new bn.a() {
                        public final void a(int i, bn.c cVar) {
                            if (cVar != null) {
                                double d2 = (double) cVar.b;
                                Double.isNaN(d2);
                                HashMap hashMap = new HashMap();
                                hashMap.put("fl.Quantity", "1");
                                hashMap.put("fl.ProductId", jSONObject.optString("productId"));
                                hashMap.put("fl.Price", String.format(Locale.ENGLISH, "%1$.2f", new Object[]{Double.valueOf(d2 / 1000000.0d)}));
                                hashMap.put("fl.Currency", cVar.c);
                                hashMap.put("fl.ProductName", cVar.d);
                                hashMap.put("fl.ProductType", cVar.a);
                                hashMap.put("fl.TransactionIdentifier", jSONObject.optString("orderId"));
                                hashMap.put("fl.OrderJSON", stringExtra);
                                hashMap.put("fl.OrderJSONSignature", stringExtra2);
                                int size = hashMap.size();
                                if (map != null) {
                                    for (Map.Entry entry : map.entrySet()) {
                                        if (!hashMap.containsKey(entry.getKey())) {
                                            hashMap.put(entry.getKey(), entry.getValue());
                                        }
                                    }
                                }
                                x.a().a("Flurry.purchase", (Map<String, String>) hashMap, size);
                                return;
                            }
                            String b2 = FlurryAgent.a;
                            db.b(b2, "Failed to load SKU Details from Google for '" + jSONObject.optString("productId") + "'. Result: " + i);
                        }
                    });
                    return;
                }
                String str2 = a;
                db.b(str2, "Invalid logPayment call. resultCode:" + i2 + ", responseCode:" + i3 + ", purchaseData:" + stringExtra + ", dataSignature:" + stringExtra2);
            } catch (Throwable th) {
                db.a(a, "Failed to log event: Flurry.purchase", th);
            }
        }
    }

    @NonNull
    public static FlurryEventRecordStatus logPayment(@NonNull String str, @NonNull String str2, int i2, double d2, @NonNull String str3, @NonNull String str4, @Nullable Map<String, String> map) {
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (!d()) {
            return flurryEventRecordStatus;
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("fl.ProductName", str);
            hashMap.put("fl.ProductID", str2);
            hashMap.put("fl.Quantity", String.valueOf(i2));
            hashMap.put("fl.Price", String.format(Locale.ENGLISH, "%1$.2f", new Object[]{Double.valueOf(d2)}));
            hashMap.put("fl.Currency", str3);
            hashMap.put("fl.TransactionIdentifier", str4);
            int size = hashMap.size();
            if (map != null) {
                for (Map.Entry next : map.entrySet()) {
                    if (!hashMap.containsKey(next.getKey())) {
                        hashMap.put(next.getKey(), next.getValue());
                    }
                }
            }
            return x.a().a("Flurry.purchase", (Map<String, String>) hashMap, size);
        } catch (Throwable th) {
            db.a(a, "Failed to log event: Flurry.purchase", th);
            return flurryEventRecordStatus;
        }
    }

    public static void endTimedEvent(@NonNull String str) {
        if (d()) {
            try {
                x.a().a(new w(str, (Map<String, String>) null));
            } catch (Throwable th) {
                db.a(a, "Failed to signify the end of event: ".concat(String.valueOf(str)), th);
            }
        }
    }

    public static void endTimedEvent(@NonNull String str, @NonNull Map<String, String> map) {
        if (d()) {
            try {
                x.a().a(new w(str, map));
            } catch (Throwable th) {
                db.a(a, "Failed to signify the end of event: ".concat(String.valueOf(str)), th);
            }
        }
    }

    public static void onError(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        onError(str, str2, str3, (Map<String, String>) null);
    }

    public static void onError(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable Map<String, String> map) {
        if (d()) {
            if (TextUtils.isEmpty(str)) {
                db.b(a, "String errorId passed to onError was empty.");
            } else if (TextUtils.isEmpty(str2)) {
                db.b(a, "String message passed to onError was empty.");
            } else if (TextUtils.isEmpty(str3)) {
                db.b(a, "String errorClass passed to onError was empty.");
            } else {
                try {
                    x a2 = x.a();
                    StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                    if (stackTrace != null && stackTrace.length > 2) {
                        StackTraceElement[] stackTraceElementArr = new StackTraceElement[(stackTrace.length - 2)];
                        System.arraycopy(stackTrace, 2, stackTraceElementArr, 0, stackTraceElementArr.length);
                        stackTrace = stackTraceElementArr;
                    }
                    Throwable th = new Throwable(str2);
                    th.setStackTrace(stackTrace);
                    a2.a(new v(str, str2, str3, th, (Map<String, String>) null, map));
                } catch (Throwable th2) {
                    db.a(a, "", th2);
                }
            }
        }
    }

    public static void onError(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        onError(str, str2, th, (Map<String, String>) null);
    }

    public static void onError(@NonNull String str, @NonNull String str2, @NonNull Throwable th, @Nullable Map<String, String> map) {
        if (d()) {
            try {
                x.a().a(str, str2, th, map);
            } catch (Throwable th2) {
                db.a(a, "", th2);
            }
        }
    }

    public static void logBreadcrumb(@NonNull String str) {
        if (d()) {
            if (TextUtils.isEmpty(str)) {
                db.b(a, "Crash breadcrumb cannot be empty.");
                return;
            }
            e();
            try {
                x a2 = x.a();
                es esVar = new es(str, System.currentTimeMillis());
                if (a2.d != null) {
                    a2.d.a(esVar);
                }
            } catch (Throwable th) {
                db.a(a, "", th);
            }
        }
    }

    public static void onPageView() {
        if (d()) {
            try {
                x.a();
                bo b2 = x.b();
                if (b2 != null) {
                    b2.c();
                }
            } catch (Throwable th) {
                db.a(a, "", th);
            }
        }
    }

    public static void setAge(int i2) {
        if (d() && i2 > 0 && i2 < 110) {
            eg.a().a("Age", (Object) Long.valueOf(new Date(new Date(System.currentTimeMillis() - (((long) i2) * 31449600000L)).getYear(), 1, 1).getTime()));
        }
    }

    public static void setGender(byte b2) {
        if (d()) {
            switch (b2) {
                case 0:
                case 1:
                    eg.a().a("Gender", (Object) Byte.valueOf(b2));
                    return;
                default:
                    eg.a().a("Gender", (Object) (byte) -1);
                    return;
            }
        }
    }

    public static void setUserId(@NonNull String str) {
        if (d()) {
            eg.a().a("UserId", (Object) em.b(str));
        }
    }

    public static void setSessionOrigin(@NonNull String str, @Nullable String str2) {
        if (d()) {
            if (TextUtils.isEmpty(str)) {
                db.b(a, "String originName passed to setSessionOrigin was empty.");
                return;
            }
            e();
            bq.a();
            bq.a(str);
            bq.a();
            ch c2 = bq.c();
            if (c2 != null) {
                c2.b(str2);
            }
        }
    }

    public static void addSessionProperty(@NonNull String str, @Nullable String str2) {
        if (d()) {
            if (TextUtils.isEmpty(str)) {
                db.b(a, "Session property name was empty");
            } else if (TextUtils.isEmpty(str2)) {
                db.b(a, "Session property value was empty");
            } else {
                e();
                bq.a();
                ch c2 = bq.c();
                if (c2 != null) {
                    c2.a(str, str2);
                }
            }
        }
    }

    public static void openPrivacyDashboard(@NonNull FlurryPrivacySession.Request request) {
        if (d()) {
            e();
            fa.a(request);
        }
    }

    /* access modifiers changed from: private */
    public static boolean d() {
        if (em.a(16)) {
            return true;
        }
        db.b(a, String.format(Locale.getDefault(), "Device SDK Version older than %d", new Object[]{16}));
        return false;
    }

    private static void e() {
        if (ck.a() == null) {
            throw new IllegalStateException("Flurry SDK must be initialized before starting a session");
        }
    }

    public static class Builder {
        private static FlurryAgentListener c;
        List<FlurryModule> a = new ArrayList();
        Consent b;
        private boolean d = false;
        private int e = 5;
        private long f = 10000;
        private boolean g = true;
        private boolean h = false;
        private boolean i = true;

        public Builder withListener(@NonNull FlurryAgentListener flurryAgentListener) {
            c = flurryAgentListener;
            return this;
        }

        public Builder withLogEnabled(boolean z) {
            this.d = z;
            return this;
        }

        public Builder withLogLevel(int i2) {
            this.e = i2;
            return this;
        }

        public Builder withContinueSessionMillis(long j) {
            this.f = j;
            return this;
        }

        public Builder withCaptureUncaughtExceptions(boolean z) {
            this.g = z;
            return this;
        }

        @Deprecated
        public Builder withPulseEnabled(boolean z) {
            this.h = z;
            return this;
        }

        public Builder withIncludeBackgroundSessionsInMetrics(boolean z) {
            this.i = z;
            return this;
        }

        public Builder withModule(@NonNull FlurryModule flurryModule) throws IllegalArgumentException {
            if (de.a(flurryModule.getClass().getCanonicalName())) {
                this.a.add(flurryModule);
                return this;
            }
            throw new IllegalArgumentException("The Flurry module you have registered is invalid: " + flurryModule.getClass().getCanonicalName());
        }

        public Builder withConsent(@NonNull Consent consent) throws IllegalArgumentException {
            if (cj.a(consent)) {
                this.b = consent;
                return this;
            }
            throw new IllegalArgumentException("flurryConsent is not valid");
        }

        public void build(@NonNull Context context, @NonNull String str) {
            if (FlurryAgent.d()) {
                FlurryAgent.a(c, this.d, this.e, this.f, this.g, this.h, this.i, this.a, this.b, context, str);
            }
        }
    }

    static /* synthetic */ void a(FlurryAgentListener flurryAgentListener, boolean z, int i2, long j2, boolean z2, boolean z3, boolean z4, List list, Consent consent, Context context, String str) {
        boolean z5;
        b = flurryAgentListener;
        setFlurryAgentListener(flurryAgentListener);
        d = z;
        setLogEnabled(z);
        e = i2;
        setLogLevel(i2);
        f = j2;
        setContinueSessionMillis(j2);
        g = z2;
        setCaptureUncaughtExceptions(z2);
        int identifier = context.getResources().getIdentifier("FLURRY_IS_YAHOO_APP", "bool", context.getPackageName());
        if (identifier != 0) {
            z5 = context.getResources().getBoolean(identifier);
            db.c(a, "Found FLURRY_IS_YAHOO_APP resource id. Value: ".concat(String.valueOf(z5)));
        } else {
            z5 = false;
        }
        if (z5) {
            eg.a().a("ProtonEnabled", (Object) Boolean.valueOf(z3));
            if (!z3) {
                eg.a().a("analyticsEnabled", (Object) Boolean.TRUE);
            }
        } else if (z3) {
            db.e(a, "Flurry Pulse is not available anymore and the API will be removed in an upcoming release");
        }
        h = z4;
        if (d()) {
            eg.a().a("IncludeBackgroundSessionsInMetrics", (Object) Boolean.valueOf(z4));
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            de.a((df) ((FlurryModule) it.next()));
        }
        l = consent;
        j = str;
        init(context, j);
    }
}
