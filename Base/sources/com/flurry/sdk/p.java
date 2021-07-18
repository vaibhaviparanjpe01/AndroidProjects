package com.flurry.sdk;

import android.content.Context;
import android.text.TextUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class p extends r {
    private static final String i = "p";
    private final Context j;

    /* access modifiers changed from: protected */
    public final void b() {
    }

    p(Context context, String str) {
        this.j = context;
        this.a = str;
    }

    /* access modifiers changed from: protected */
    public final InputStream a() throws IOException {
        if (this.j == null || TextUtils.isEmpty(this.a)) {
            return null;
        }
        try {
            return this.j.getAssets().open(this.a);
        } catch (FileNotFoundException unused) {
            String str = i;
            db.b(str, "File Not Found when opening " + this.a);
            return null;
        } catch (IOException unused2) {
            String str2 = i;
            db.b(str2, "IO Exception when opening " + this.a);
            return null;
        }
    }
}
