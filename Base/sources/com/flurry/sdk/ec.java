package com.flurry.sdk;

import android.content.Context;
import java.lang.ref.WeakReference;

public final class ec extends cu {
    public WeakReference<Context> a;
    public eb b;
    public int d;
    public long e;

    public ec() {
        super("com.flurry.android.sdk.FlurrySessionEvent");
    }

    public enum a {
        ;
        
        public static final int a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;

        static {
            f = new int[]{a, b, c, d, e};
        }

        public static int[] a() {
            return (int[]) f.clone();
        }
    }
}
