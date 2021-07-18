package com.flurry.sdk;

import java.lang.Thread;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public final class ew {
    private static ew c;
    final Thread.UncaughtExceptionHandler a = Thread.getDefaultUncaughtExceptionHandler();
    final Map<Thread.UncaughtExceptionHandler, Void> b = new WeakHashMap();

    private ew() {
        Thread.setDefaultUncaughtExceptionHandler(new a(this, (byte) 0));
    }

    public static synchronized ew a() {
        ew ewVar;
        synchronized (ew.class) {
            if (c == null) {
                c = new ew();
            }
            ewVar = c;
        }
        return ewVar;
    }

    public static synchronized void b() {
        synchronized (ew.class) {
            if (c != null) {
                Thread.setDefaultUncaughtExceptionHandler(c.a);
            }
            c = null;
        }
    }

    /* access modifiers changed from: package-private */
    public final Set<Thread.UncaughtExceptionHandler> c() {
        Set<Thread.UncaughtExceptionHandler> keySet;
        synchronized (this.b) {
            keySet = this.b.keySet();
        }
        return keySet;
    }

    final class a implements Thread.UncaughtExceptionHandler {
        private a() {
        }

        /* synthetic */ a(ew ewVar, byte b) {
            this();
        }

        public final void uncaughtException(Thread thread, Throwable th) {
            for (Thread.UncaughtExceptionHandler uncaughtException : ew.this.c()) {
                try {
                    uncaughtException.uncaughtException(thread, th);
                } catch (Throwable unused) {
                }
            }
            ew ewVar = ew.this;
            if (ewVar.a != null) {
                try {
                    ewVar.a.uncaughtException(thread, th);
                } catch (Throwable unused2) {
                }
            }
        }
    }
}
