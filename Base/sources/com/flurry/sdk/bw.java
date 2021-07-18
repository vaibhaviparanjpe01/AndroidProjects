package com.flurry.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import com.flurry.sdk.eh;
import java.util.Timer;
import java.util.TimerTask;

@SuppressLint({"MissingPermission"})
public class bw implements eh.a {
    /* access modifiers changed from: private */
    public static final String a = "bw";
    private static int b = -1;
    private static int c = -1;
    private static int d = -1;
    private static bw e;
    private boolean f;
    private Location g;
    /* access modifiers changed from: private */
    public long h = 0;
    private LocationManager i = ((LocationManager) ck.a().a.getSystemService("location"));
    private a j = new a();
    /* access modifiers changed from: private */
    public Location k;
    private boolean l = false;
    private int m = 0;
    private Timer n = null;

    static /* synthetic */ int c(bw bwVar) {
        int i2 = bwVar.m + 1;
        bwVar.m = i2;
        return i2;
    }

    private bw() {
        eg a2 = eg.a();
        this.f = ((Boolean) a2.a("ReportLocation")).booleanValue();
        a2.a("ReportLocation", (eh.a) this);
        String str = a;
        db.a(4, str, "initSettings, ReportLocation = " + this.f);
        this.g = (Location) a2.a("ExplicitLocation");
        a2.a("ExplicitLocation", (eh.a) this);
        String str2 = a;
        db.a(4, str2, "initSettings, ExplicitLocation = " + this.g);
    }

    public static synchronized bw a() {
        bw bwVar;
        synchronized (bw.class) {
            if (e == null) {
                e = new bw();
            }
            bwVar = e;
        }
        return bwVar;
    }

    public static void b() {
        if (e != null) {
            eg.a().b("ReportLocation", e);
            eg.a().b("ExplicitLocation", e);
        }
        e = null;
    }

    public static int c() {
        return b;
    }

    public static int d() {
        return d;
    }

    public final synchronized void e() {
        db.a(4, a, "Location update requested");
        if (this.m < 3 && !this.l && this.f && this.g == null) {
            Context context = ck.a().a;
            if (context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 || context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) {
                this.m = 0;
                String str = a(context) ? "passive" : null;
                if (!TextUtils.isEmpty(str)) {
                    this.i.requestLocationUpdates(str, 10000, 0.0f, this.j, Looper.getMainLooper());
                }
                this.k = a(str);
                this.h = System.currentTimeMillis() + 90000;
                if (this.n != null) {
                    this.n.cancel();
                    this.n = null;
                }
                db.a(4, a, "Register location timer");
                this.n = new Timer();
                this.n.schedule(new TimerTask() {
                    public final void run() {
                        if (bw.this.h > 0 && bw.this.h < System.currentTimeMillis()) {
                            db.a(4, bw.a, "No location received in 90 seconds , stopping LocationManager");
                            bw.this.i();
                        }
                    }
                }, 90000);
                this.l = true;
                db.a(4, a, "LocationProvider started");
            }
        }
    }

    private static boolean a(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    private static boolean b(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0;
    }

    private Location a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return this.i.getLastKnownLocation(str);
        }
        return null;
    }

    public final synchronized void f() {
        db.a(4, a, "Stop update location requested");
        i();
    }

    /* access modifiers changed from: private */
    public void i() {
        if (this.l) {
            this.i.removeUpdates(this.j);
            this.m = 0;
            this.h = 0;
            j();
            this.l = false;
            db.a(4, a, "LocationProvider stopped");
        }
    }

    private void j() {
        db.a(4, a, "Unregister location timer");
        if (this.n != null) {
            this.n.cancel();
            this.n = null;
        }
    }

    public final Location g() {
        if (this.g != null) {
            return this.g;
        }
        Location location = null;
        if (this.f) {
            Context context = ck.a().a;
            if (!a(context) && !b(context)) {
                return null;
            }
            String str = a(context) ? "passive" : null;
            if (str != null) {
                Location a2 = a(str);
                if (a2 != null) {
                    this.k = a2;
                }
                location = this.k;
            }
        }
        db.a(4, a, "getLocation() = ".concat(String.valueOf(location)));
        return location;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.String r3, java.lang.Object r4) {
        /*
            r2 = this;
            int r0 = r3.hashCode()
            r1 = -864112343(0xffffffffcc7eb129, float:-6.6765988E7)
            if (r0 == r1) goto L_0x0019
            r1 = -300729815(0xffffffffee133a29, float:-1.1391152E28)
            if (r0 == r1) goto L_0x000f
            goto L_0x0023
        L_0x000f:
            java.lang.String r0 = "ExplicitLocation"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0023
            r3 = 1
            goto L_0x0024
        L_0x0019:
            java.lang.String r0 = "ReportLocation"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0023
            r3 = 0
            goto L_0x0024
        L_0x0023:
            r3 = -1
        L_0x0024:
            r0 = 4
            switch(r3) {
                case 0: goto L_0x004b;
                case 1: goto L_0x0031;
                default: goto L_0x0028;
            }
        L_0x0028:
            r3 = 6
            java.lang.String r4 = a
            java.lang.String r0 = "LocationProvider internal error! Had to be LocationCriteria, ReportLocation or ExplicitLocation key."
            com.flurry.sdk.db.a((int) r3, (java.lang.String) r4, (java.lang.String) r0)
            return
        L_0x0031:
            android.location.Location r4 = (android.location.Location) r4
            r2.g = r4
            java.lang.String r3 = a
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r1 = "onSettingUpdate, ExplicitLocation = "
            r4.<init>(r1)
            android.location.Location r1 = r2.g
            r4.append(r1)
            java.lang.String r4 = r4.toString()
            com.flurry.sdk.db.a((int) r0, (java.lang.String) r3, (java.lang.String) r4)
            return
        L_0x004b:
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r3 = r4.booleanValue()
            r2.f = r3
            java.lang.String r3 = a
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r1 = "onSettingUpdate, ReportLocation = "
            r4.<init>(r1)
            boolean r1 = r2.f
            r4.append(r1)
            java.lang.String r4 = r4.toString()
            com.flurry.sdk.db.a((int) r0, (java.lang.String) r3, (java.lang.String) r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.bw.a(java.lang.String, java.lang.Object):void");
    }

    class a implements LocationListener {
        public final void onProviderDisabled(String str) {
        }

        public final void onProviderEnabled(String str) {
        }

        public final void onStatusChanged(String str, int i, Bundle bundle) {
        }

        public a() {
        }

        public final void onLocationChanged(Location location) {
            if (location != null) {
                Location unused = bw.this.k = location;
            }
            if (bw.c(bw.this) >= 3) {
                db.a(4, bw.a, "Max location reports reached, stopping");
                bw.this.i();
            }
        }
    }
}
