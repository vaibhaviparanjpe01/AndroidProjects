package com.flurry.sdk;

import android.content.Context;

public final class s {
    public static r a(Context context, String str) {
        if (str.startsWith("http://") || str.startsWith("https://")) {
            return new o(str);
        }
        return new p(context, str);
    }
}
