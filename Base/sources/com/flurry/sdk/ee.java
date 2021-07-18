package com.flurry.sdk;

import java.util.Timer;
import java.util.TimerTask;

final class ee {
    private Timer a;
    private a b;

    ee() {
    }

    public final synchronized void a(long j) {
        if (a()) {
            b();
        }
        this.a = new Timer("FlurrySessionTimer");
        this.b = new a();
        this.a.schedule(this.b, j);
    }

    public final boolean a() {
        return this.a != null;
    }

    public final synchronized void b() {
        if (this.a != null) {
            this.a.cancel();
            this.a = null;
        }
        this.b = null;
    }

    class a extends TimerTask {
        a() {
        }

        public final void run() {
            ee.this.b();
            cw.a().a((cu) new ef());
        }
    }
}
