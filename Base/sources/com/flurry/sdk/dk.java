package com.flurry.sdk;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class dk extends eq {
    static final String e = "dk";
    private final cq<String, String> a = new cq<>();
    private final Object b = new Object();
    private int c;
    private int d;
    public final cq<String, String> f = new cq<>();
    public String g;
    public a h;
    public int i = 10000;
    public int j = 15000;
    public boolean k = true;
    b l;
    public boolean m;
    long n = -1;
    public long o = -1;
    public Exception p;
    public int q = -1;
    public boolean r;
    public int s = 25000;
    public boolean t;
    /* access modifiers changed from: private */
    public HttpURLConnection v;
    private boolean w;
    private boolean x;
    private dj y = new dj(this);

    public interface b {
        void a();

        void a(dk dkVar, InputStream inputStream) throws Exception;

        void a(OutputStream outputStream) throws Exception;
    }

    public final void a(String str, String str2) {
        this.a.a(str, str2);
    }

    public final boolean b() {
        return !c() && d();
    }

    public final boolean c() {
        return this.p != null;
    }

    public final boolean d() {
        return this.q >= 200 && this.q < 400 && !this.t;
    }

    public final List<String> a(String str) {
        return this.f.a(str);
    }

    /* access modifiers changed from: package-private */
    public final void e() {
        if (this.l != null && !g()) {
            this.l.a();
        }
    }

    public final void f() {
        String str = e;
        db.a(3, str, "Cancelling http request: " + this.g);
        synchronized (this.b) {
            this.x = true;
        }
        i();
    }

    public final boolean g() {
        boolean z;
        synchronized (this.b) {
            z = this.x;
        }
        return z;
    }

    private void i() {
        if (!this.w) {
            this.w = true;
            if (this.v != null) {
                new Thread() {
                    public final void run() {
                        try {
                            if (dk.this.v != null) {
                                dk.this.v.disconnect();
                            }
                        } catch (Throwable unused) {
                        }
                    }
                }.start();
            }
        }
    }

    public void a() {
        try {
            if (this.g == null) {
                this.y.a();
                e();
            } else if (!by.a().c) {
                String str = e;
                db.a(3, str, "Network not available, aborting http request: " + this.g);
                this.y.a();
                e();
            } else {
                if (this.h == null || a.kUnknown.equals(this.h)) {
                    this.h = a.kGet;
                }
                j();
                String str2 = e;
                db.a(4, str2, "HTTP status: " + this.q + " for url: " + this.g);
                this.y.a();
                e();
            }
        } catch (Exception e2) {
            String str3 = e;
            db.a(4, str3, "HTTP status: " + this.q + " for url: " + this.g);
            String str4 = e;
            StringBuilder sb = new StringBuilder("Exception during http request: ");
            sb.append(this.g);
            db.a(3, str4, sb.toString(), e2);
            if (this.v != null) {
                this.d = this.v.getReadTimeout();
                this.c = this.v.getConnectTimeout();
            }
            this.p = e2;
        } catch (Throwable th) {
            this.y.a();
            e();
            throw th;
        }
    }

    private void j() throws Exception {
        BufferedInputStream bufferedInputStream;
        Throwable th;
        InputStream inputStream;
        BufferedOutputStream bufferedOutputStream;
        Throwable th2;
        OutputStream outputStream;
        if (!this.x) {
            this.g = em.a(this.g);
            this.v = (HttpURLConnection) new URL(this.g).openConnection();
            this.v.setConnectTimeout(this.i);
            this.v.setReadTimeout(this.j);
            this.v.setRequestMethod(this.h.toString());
            this.v.setInstanceFollowRedirects(this.k);
            this.v.setDoOutput(a.kPost.equals(this.h));
            this.v.setDoInput(true);
            for (Map.Entry next : this.a.b()) {
                this.v.addRequestProperty((String) next.getKey(), (String) next.getValue());
            }
            if (!a.kGet.equals(this.h) && !a.kPost.equals(this.h)) {
                this.v.setRequestProperty("Accept-Encoding", "");
            }
            if (this.x) {
                k();
                return;
            }
            if (a.kPost.equals(this.h)) {
                try {
                    outputStream = this.v.getOutputStream();
                    try {
                        bufferedOutputStream = new BufferedOutputStream(outputStream);
                    } catch (Throwable th3) {
                        Throwable th4 = th3;
                        bufferedOutputStream = null;
                        th2 = th4;
                        em.a((Closeable) bufferedOutputStream);
                        em.a((Closeable) outputStream);
                        throw th2;
                    }
                } catch (Throwable th5) {
                    bufferedOutputStream = null;
                    th2 = th5;
                    outputStream = null;
                    em.a((Closeable) bufferedOutputStream);
                    em.a((Closeable) outputStream);
                    throw th2;
                }
                try {
                    if (this.l != null && !g()) {
                        this.l.a(bufferedOutputStream);
                    }
                    em.a((Closeable) bufferedOutputStream);
                    em.a((Closeable) outputStream);
                } catch (Throwable th6) {
                    k();
                    throw th6;
                }
            }
            if (this.m) {
                this.n = System.currentTimeMillis();
            }
            if (this.r) {
                this.y.a((long) this.s);
            }
            this.q = this.v.getResponseCode();
            if (this.m && this.n != -1) {
                this.o = System.currentTimeMillis() - this.n;
            }
            this.y.a();
            for (Map.Entry entry : this.v.getHeaderFields().entrySet()) {
                for (String a2 : (List) entry.getValue()) {
                    this.f.a(entry.getKey(), a2);
                }
            }
            if (!a.kGet.equals(this.h) && !a.kPost.equals(this.h)) {
                k();
            } else if (this.x) {
                k();
            } else {
                try {
                    inputStream = this.v.getInputStream();
                    try {
                        bufferedInputStream = new BufferedInputStream(inputStream);
                    } catch (Throwable th7) {
                        Throwable th8 = th7;
                        bufferedInputStream = null;
                        th = th8;
                        em.a((Closeable) bufferedInputStream);
                        em.a((Closeable) inputStream);
                        throw th;
                    }
                    try {
                        if (this.l != null && !g()) {
                            this.l.a(this, bufferedInputStream);
                        }
                        em.a((Closeable) bufferedInputStream);
                        em.a((Closeable) inputStream);
                        k();
                    } catch (Throwable th9) {
                        th = th9;
                        em.a((Closeable) bufferedInputStream);
                        em.a((Closeable) inputStream);
                        throw th;
                    }
                } catch (Throwable th10) {
                    bufferedInputStream = null;
                    th = th10;
                    inputStream = null;
                    em.a((Closeable) bufferedInputStream);
                    em.a((Closeable) inputStream);
                    throw th;
                }
            }
        }
    }

    private void k() {
        if (!this.w) {
            this.w = true;
            if (this.v != null) {
                this.v.disconnect();
            }
        }
    }

    public final void h() {
        f();
    }

    public enum a {
        kUnknown,
        kGet,
        kPost,
        kPut,
        kDelete,
        kHead;

        public final String toString() {
            switch (this) {
                case kPost:
                    return "POST";
                case kPut:
                    return "PUT";
                case kDelete:
                    return "DELETE";
                case kHead:
                    return "HEAD";
                case kGet:
                    return "GET";
                default:
                    return null;
            }
        }
    }
}
