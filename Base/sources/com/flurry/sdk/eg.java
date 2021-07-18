package com.flurry.sdk;

import android.location.Criteria;
import android.location.Location;

public final class eg extends eh {
    public static final Integer a = 284;
    public static final Integer b = 11;
    public static final Integer c = 6;
    public static final Integer d = 0;
    public static final String e = null;
    public static final Boolean f = Boolean.TRUE;
    public static final Boolean g = Boolean.TRUE;
    public static final String h = null;
    public static final Boolean i = Boolean.TRUE;
    public static final Criteria j = null;
    public static final Location k = null;
    public static final Long l = 10000L;
    public static final Boolean m = Boolean.TRUE;
    public static final Long n = null;
    public static final Byte o = (byte) -1;
    public static final Boolean p = Boolean.FALSE;
    public static final String q = null;
    public static final Boolean r = Boolean.TRUE;
    public static final Boolean s = Boolean.TRUE;
    private static eg t;

    private eg() {
        a("AgentVersion", (Object) a);
        a("ReleaseMajorVersion", (Object) b);
        a("ReleaseMinorVersion", (Object) c);
        a("ReleasePatchVersion", (Object) d);
        a("ReleaseBetaVersion", (Object) "");
        a("VersionName", (Object) e);
        a("CaptureUncaughtExceptions", (Object) f);
        a("UseHttps", (Object) g);
        a("ReportUrl", (Object) h);
        a("ReportLocation", (Object) i);
        a("ExplicitLocation", (Object) k);
        a("ContinueSessionMillis", (Object) l);
        a("LogEvents", (Object) m);
        a("Age", (Object) n);
        a("Gender", (Object) o);
        a("UserId", (Object) "");
        a("ProtonEnabled", (Object) p);
        a("ProtonConfigUrl", (Object) q);
        a("analyticsEnabled", (Object) r);
        a("IncludeBackgroundSessionsInMetrics", (Object) s);
        a("notificationsEnabled", (Object) Boolean.FALSE);
    }

    public static synchronized eg a() {
        eg egVar;
        synchronized (eg.class) {
            if (t == null) {
                t = new eg();
            }
            egVar = t;
        }
        return egVar;
    }

    public static synchronized void b() {
        synchronized (eg.class) {
            if (t != null) {
                t.c();
            }
            t = null;
        }
    }
}
