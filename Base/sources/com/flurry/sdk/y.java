package com.flurry.sdk;

import android.content.Context;
import java.io.File;
import java.util.List;
import java.util.Map;

public class y {
    private static final String b = "y";
    boolean a;
    private final z c;
    private final File d;
    private String e;

    public y() {
        this(ck.a().a);
    }

    public y(Context context) {
        this.c = new z();
        this.d = context.getFileStreamPath(".flurryinstallreceiver.");
        String str = b;
        db.a(3, str, "Referrer file name if it exists:  " + this.d);
    }

    public final synchronized Map<String, List<String>> a() {
        c();
        return z.a(this.e);
    }

    private void c() {
        if (!this.a) {
            this.a = true;
            String str = b;
            db.a(4, str, "Loading referrer info from file: " + this.d.getAbsolutePath());
            String c2 = el.c(this.d);
            db.a(b, "Referrer file contents: ".concat(String.valueOf(c2)));
            b(c2);
        }
    }

    private void b(String str) {
        if (str != null) {
            this.e = str;
        }
    }

    public final synchronized String b() {
        c();
        return this.e;
    }

    public final synchronized void a(String str) {
        this.a = true;
        b(str);
        el.a(this.d, this.e);
    }
}
