package com.flurry.sdk;

import android.text.TextUtils;

public abstract class cu {
    protected String c = "com.flurry.android.sdk.ReplaceMeWithAProperEventName";

    public cu(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.c = str;
            return;
        }
        throw new IllegalArgumentException("Event must have a name!");
    }

    public final String a() {
        return this.c;
    }

    public final void b() {
        cw.a().a(this);
    }
}
