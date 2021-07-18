package com.flurry.sdk;

import java.util.Timer;
import java.util.TimerTask;

public class dj {
    /* access modifiers changed from: private */
    public static final String a = "dj";
    private Timer b;
    private a c;
    /* access modifiers changed from: private */
    public dk d;

    public dj(dk dkVar) {
        this.d = dkVar;
    }

    public final synchronized void a() {
        if (this.b != null) {
            this.b.cancel();
            this.b = null;
            db.a(3, a, "HttpRequestTimeoutTimer stopped.");
        }
        this.c = null;
    }

    class a extends TimerTask {
        private a() {
        }

        /* synthetic */ a(dj djVar, byte b) {
            this();
        }

        public final void run() {
            db.a(3, dj.a, "HttpRequest timed out. Cancelling.");
            dk a2 = dj.this.d;
            long currentTimeMillis = System.currentTimeMillis() - a2.n;
            String str = dk.e;
            db.a(3, str, "Timeout (" + currentTimeMillis + "MS) for url: " + a2.g);
            a2.q = 629;
            a2.t = true;
            a2.e();
            a2.f();
        }
    }

    public final synchronized void a(long j) {
        if (this.b != null) {
            a();
        }
        this.b = new Timer("HttpRequestTimeoutTimer");
        this.c = new a(this, (byte) 0);
        this.b.schedule(this.c, j);
        String str = a;
        db.a(3, str, "HttpRequestTimeoutTimer started: " + j + "MS");
    }
}
