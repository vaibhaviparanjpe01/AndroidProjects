package com.flurry.android;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import com.flurry.sdk.fb;
import com.flurry.sdk.fc;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Locale;

public interface FlurryPrivacySession {

    public interface Callback {
        void failure();

        void success();
    }

    public static class Request implements fb.a {
        final String a;
        final String b;
        final String c;
        public final Callback callback;
        public final Context context;
        public final String verifier = fc.b.a();

        public Request(@NonNull Context context2, @Nullable Callback callback2) {
            String str;
            this.context = context2;
            this.callback = callback2;
            this.b = context2.getPackageName();
            String str2 = this.verifier;
            MessageDigest a2 = fc.a.a("SHA-256");
            if (a2 != null) {
                a2.update(str2.getBytes(Charset.defaultCharset()));
                str = Base64.encodeToString(a2.digest(), 11);
            } else {
                str = "";
            }
            this.a = str;
            Locale locale = Locale.getDefault();
            String language = locale.getLanguage();
            language = TextUtils.isEmpty(language) ? e : language;
            String country = locale.getCountry();
            country = TextUtils.isEmpty(country) ? d : country;
            this.c = language + "-" + country;
        }
    }

    public static class a {
        public final Uri a;

        public a(String str, long j, Request request) {
            this.a = new Uri.Builder().scheme("https").authority("flurry.mydashboard.oath.com").appendQueryParameter("device_session_id", str).appendQueryParameter("expires_in", String.valueOf(j)).appendQueryParameter("device_verifier", request.a).appendQueryParameter("lang", request.c).build();
        }
    }
}
