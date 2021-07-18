package com.flurry.sdk;

import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class et {
    private static final String b = et.class.getCanonicalName();
    private static final char[] c = {'F', 'C', 'B', 'M'};
    private static final String d = new String(c);
    private static final int e = ((((c.length * 2) + 2) + 1) + 105984);
    private static final int f;
    private static final int g;
    private static final int h;
    public ByteBuffer a;
    private short i;
    private boolean j;

    public static int b() {
        return 1;
    }

    static {
        int length = c.length * 2;
        f = length;
        int i2 = length + 2;
        g = i2;
        h = i2 + 1;
    }

    public et() {
        this.a = ByteBuffer.allocateDirect(e);
        this.a.asCharBuffer().put(c);
    }

    public et(File file) {
        int i2;
        boolean z = true;
        db.a(3, b, String.format(Locale.getDefault(), "Loading crash breadcrumbs from %s", new Object[]{file.getAbsolutePath()}));
        this.a = ByteBuffer.allocate(e);
        if (file.length() != ((long) this.a.capacity())) {
            db.a(6, b, String.format(Locale.getDefault(), "Crash breadcrumbs invalid file length %s != %s", new Object[]{Long.valueOf(file.length()), Integer.valueOf(this.a.capacity())}));
            this.a = null;
            return;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            FileChannel channel = fileInputStream.getChannel();
            try {
                i2 = channel.read(this.a);
            } catch (IOException unused) {
                db.a(6, b, "Issue reading breadcrumbs from file.");
                i2 = 0;
            }
            em.a((Closeable) channel);
            em.a((Closeable) fileInputStream);
            if (i2 != this.a.capacity()) {
                db.a(6, b, String.format(Locale.getDefault(), "YCrashBreadcrumbs unexpected read size %s != %s", new Object[]{Integer.valueOf(i2), Integer.valueOf(this.a.capacity())}));
                this.a = null;
                return;
            }
            this.a.position(0);
            String obj = this.a.asCharBuffer().limit(c.length).toString();
            if (!obj.equals(d)) {
                db.a(6, b, String.format(Locale.getDefault(), "YCrashBreadcrumbs invalid magic string: '%s'", new Object[]{obj}));
                this.a = null;
                return;
            }
            this.i = this.a.getShort(f);
            if (this.i < 0 || this.i >= 207) {
                db.a(6, b, String.format(Locale.getDefault(), "YCrashBreadcrumbs invalid index: '%s'", new Object[]{Short.valueOf(this.i)}));
                this.a = null;
                return;
            }
            this.j = this.a.get(g) != 1 ? false : z;
        } catch (FileNotFoundException unused2) {
            db.a(6, b, "Issue reading breadcrumbs file.");
            this.a = null;
        }
    }

    private es a(int i2) {
        this.a.position(h + (i2 * 512));
        return new es(this.a.asCharBuffer().limit(this.a.getInt()).toString(), this.a.getLong());
    }

    public final List<es> a() {
        ArrayList arrayList = new ArrayList();
        if (this.a == null) {
            return arrayList;
        }
        if (this.j) {
            for (int i2 = this.i; i2 < 207; i2++) {
                arrayList.add(a(i2));
            }
        }
        for (int i3 = 0; i3 < this.i; i3++) {
            arrayList.add(a(i3));
        }
        return arrayList;
    }

    public final synchronized void a(es esVar) {
        String str = esVar.a;
        if (TextUtils.isEmpty(str)) {
            db.b(b, "Breadcrumb may not be null or empty.");
            return;
        }
        long j2 = esVar.b;
        int min = Math.min(str.length(), ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        this.a.position((this.i * 512) + h);
        this.a.putLong(j2);
        this.a.putInt(min);
        this.a.asCharBuffer().put(str, 0, min);
        this.i = (short) (this.i + 1);
        if (this.i >= 207) {
            this.i = 0;
            this.j = true;
        }
        this.a.putShort(f, this.i);
        this.a.put(g, this.j ? (byte) 1 : 0);
    }

    public synchronized String toString() {
        short s;
        StringBuilder sb;
        if (this.a == null) {
            s = 0;
        } else {
            s = this.j ? 207 : this.i;
        }
        sb = new StringBuilder();
        sb.append("Total number of breadcrumbs: " + s + "\n");
        for (es esVar : a()) {
            sb.append(esVar.toString());
        }
        return sb.toString();
    }
}
