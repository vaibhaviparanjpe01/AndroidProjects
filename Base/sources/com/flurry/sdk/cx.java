package com.flurry.sdk;

import java.util.Comparator;

public class cx implements Comparator<Runnable> {
    private static final String a = "cx";

    public /* synthetic */ int compare(Object obj, Object obj2) {
        int a2 = a((Runnable) obj);
        int a3 = a((Runnable) obj2);
        if (a2 < a3) {
            return -1;
        }
        return a2 > a3 ? 1 : 0;
    }

    private static int a(Runnable runnable) {
        if (runnable == null) {
            return Integer.MAX_VALUE;
        }
        if (runnable instanceof cy) {
            eq eqVar = (eq) ((cy) runnable).a();
            if (eqVar != null) {
                return eqVar.u;
            }
            return Integer.MAX_VALUE;
        } else if (runnable instanceof eq) {
            return ((eq) runnable).u;
        } else {
            String str = a;
            db.a(6, str, "Unknown runnable class: " + runnable.getClass().getName());
            return Integer.MAX_VALUE;
        }
    }
}
