package com.flurry.sdk;

import android.app.Activity;
import java.lang.ref.WeakReference;

public final class cn extends cu {
    public WeakReference<Activity> a = new WeakReference<>((Object) null);
    public int b;

    cn() {
        super("com.flurry.android.sdk.ActivityLifecycleEvent");
    }

    public enum a {
        ;
        
        public static final int a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        public static final int f = 6;
        public static final int g = 7;

        static {
            h = new int[]{a, b, c, d, e, f, g};
        }

        public static int[] a() {
            return (int[]) h.clone();
        }
    }
}
