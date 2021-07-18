package com.flurry.sdk;

import android.text.TextUtils;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

public final class er {
    private static final Pattern a = Pattern.compile("/");

    private static URI b(String str) {
        try {
            return new URI(str);
        } catch (URISyntaxException unused) {
            return null;
        }
    }

    public static boolean a(String str) {
        URI b;
        if (TextUtils.isEmpty(str) || (b = b(str)) == null) {
            return false;
        }
        if (b.getScheme() == null || "http".equalsIgnoreCase(b.getScheme()) || "https".equalsIgnoreCase(b.getScheme())) {
            return true;
        }
        return false;
    }

    public static String a(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            try {
                if (new URI(str).isAbsolute() || TextUtils.isEmpty(str2)) {
                    return str;
                }
                URI uri = new URI(str2);
                return uri.getScheme() + "://" + uri.getHost() + str;
            } catch (Exception unused) {
            }
        }
        return str;
    }
}
