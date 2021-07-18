package com.flurry.sdk;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class cg extends cz<dk> {
    private static cg a;

    protected cg() {
        super(cg.class.getName(), TimeUnit.MILLISECONDS, new PriorityBlockingQueue(11, new cx()));
    }

    public static synchronized cg a() {
        cg cgVar;
        synchronized (cg.class) {
            if (a == null) {
                a = new cg();
            }
            cgVar = a;
        }
        return cgVar;
    }

    public static synchronized void b() {
        synchronized (cg.class) {
            if (a != null) {
                a.c();
            }
            a = null;
        }
    }
}
