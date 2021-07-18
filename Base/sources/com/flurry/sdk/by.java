package com.flurry.sdk;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.flurry.sdk.cn;

public class by extends BroadcastReceiver {
    public static final String a = "by";
    private static by d;
    boolean b;
    public boolean c;
    private boolean e = false;
    private final cv<cn> f = new cv<cn>() {
        public final /* synthetic */ void a(cu cuVar) {
            cn cnVar = (cn) cuVar;
            Activity activity = (Activity) cnVar.a.get();
            if (activity == null) {
                db.a(3, by.a, "Activity has been destroyed, don't update network state.");
            } else if (AnonymousClass2.a[cnVar.b - 1] == 1) {
                by.this.c = by.this.a(activity);
            }
        }
    };

    /* renamed from: com.flurry.sdk.by$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[cn.a.a().length];

        static {
            try {
                a[cn.a.d - 1] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private by() {
        boolean z = false;
        Context context = ck.a().a;
        this.e = context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0 ? true : z;
        this.c = a(context);
        if (this.e) {
            d();
        }
    }

    /* access modifiers changed from: private */
    public boolean a(Context context) {
        if (!this.e || context == null) {
            return true;
        }
        NetworkInfo activeNetworkInfo = e().getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return false;
        }
        return true;
    }

    private synchronized void d() {
        if (!this.b) {
            Context context = ck.a().a;
            this.c = a(context);
            context.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            cw.a().a("com.flurry.android.sdk.ActivityLifecycleEvent", this.f);
            this.b = true;
        }
    }

    private static ConnectivityManager e() {
        return (ConnectivityManager) ck.a().a.getSystemService("connectivity");
    }

    public static synchronized by a() {
        by byVar;
        synchronized (by.class) {
            if (d == null) {
                d = new by();
            }
            byVar = d;
        }
        return byVar;
    }

    public static synchronized void b() {
        synchronized (by.class) {
            if (d != null) {
                d.f();
            }
            d = null;
        }
    }

    private synchronized void f() {
        if (this.b) {
            ck.a().a.unregisterReceiver(this);
            cw.a().a((cv<?>) this.f);
            this.b = false;
        }
    }

    public void onReceive(Context context, Intent intent) {
        boolean a2 = a(context);
        if (this.c != a2) {
            this.c = a2;
            bx bxVar = new bx();
            bxVar.a = a2;
            bxVar.b = c();
            cw.a().a((cu) bxVar);
        }
    }

    public final int c() {
        if (!this.e) {
            return a.a;
        }
        NetworkInfo activeNetworkInfo = e().getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return a.a;
        }
        int type = activeNetworkInfo.getType();
        if (type == 8) {
            return a.a;
        }
        switch (type) {
            case 0:
            case 2:
            case 3:
            case 4:
            case 5:
                return a.d;
            case 1:
                return a.c;
            default:
                if (activeNetworkInfo.isConnected()) {
                    return a.b;
                }
                return a.a;
        }
    }

    public enum a {
        ;
        
        public static final int a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;

        static {
            e = new int[]{a, b, c, d};
        }
    }
}
