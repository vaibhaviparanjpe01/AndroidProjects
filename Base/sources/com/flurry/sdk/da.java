package com.flurry.sdk;

import com.flurry.android.FlurryAgent;

public final class da {
    public static final String a = "da";
    private static da c;
    public boolean b = false;

    private da() {
    }

    public static synchronized da a() {
        da daVar;
        synchronized (da.class) {
            if (c == null) {
                c = new da();
            }
            daVar = c;
        }
        return daVar;
    }

    public final synchronized String b() {
        if (!this.b) {
            return null;
        }
        if (FlurryAgent.getInstantAppName() != null) {
            return FlurryAgent.getInstantAppName();
        }
        return co.a().d();
    }
}
