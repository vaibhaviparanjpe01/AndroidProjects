package com.flurry.sdk;

import android.os.Build;
import android.text.TextUtils;
import java.util.HashMap;
import javax.net.ssl.HttpsURLConnection;

public class o extends r {
    private static final String i = "o";
    private static String j;
    private HttpsURLConnection k;
    private String l;
    private boolean m;

    o(String str) {
        this.a = str;
        j = "Flurry-Config/1.0 (Android " + Build.VERSION.RELEASE + "/" + Build.ID + ")";
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0117  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x011c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.io.InputStream a() throws java.io.IOException {
        /*
            r6 = this;
            java.net.URL r0 = new java.net.URL
            java.lang.String r1 = r6.a
            r0.<init>(r1)
            java.net.URLConnection r0 = r0.openConnection()
            javax.net.ssl.HttpsURLConnection r0 = (javax.net.ssl.HttpsURLConnection) r0
            r6.k = r0
            javax.net.ssl.HttpsURLConnection r0 = r6.k
            r1 = 10000(0x2710, float:1.4013E-41)
            r0.setReadTimeout(r1)
            javax.net.ssl.HttpsURLConnection r0 = r6.k
            r1 = 15000(0x3a98, float:2.102E-41)
            r0.setConnectTimeout(r1)
            javax.net.ssl.HttpsURLConnection r0 = r6.k
            java.lang.String r1 = "POST"
            r0.setRequestMethod(r1)
            javax.net.ssl.HttpsURLConnection r0 = r6.k
            java.lang.String r1 = "User-Agent"
            java.lang.String r2 = j
            r0.setRequestProperty(r1, r2)
            javax.net.ssl.HttpsURLConnection r0 = r6.k
            java.lang.String r1 = "Content-Type"
            java.lang.String r2 = "application/json"
            r0.setRequestProperty(r1, r2)
            javax.net.ssl.HttpsURLConnection r0 = r6.k
            r1 = 1
            r0.setDoInput(r1)
            javax.net.ssl.HttpsURLConnection r0 = r6.k
            r0.setDoOutput(r1)
            javax.net.ssl.HttpsURLConnection r0 = r6.k
            r0.connect()
            javax.net.ssl.HttpsURLConnection r0 = r6.k
            com.flurry.sdk.t.a((javax.net.ssl.HttpsURLConnection) r0)
            java.util.UUID r0 = java.util.UUID.randomUUID()
            java.lang.String r0 = r0.toString()
            java.util.Locale r1 = java.util.Locale.ENGLISH
            java.lang.String r0 = r0.toUpperCase(r1)
            r6.c = r0
            r0 = 0
            javax.net.ssl.HttpsURLConnection r1 = r6.k     // Catch:{ all -> 0x0112 }
            java.io.OutputStream r1 = r1.getOutputStream()     // Catch:{ all -> 0x0112 }
            java.io.BufferedWriter r2 = new java.io.BufferedWriter     // Catch:{ all -> 0x0110 }
            java.io.OutputStreamWriter r3 = new java.io.OutputStreamWriter     // Catch:{ all -> 0x0110 }
            java.lang.String r4 = "UTF-8"
            r3.<init>(r1, r4)     // Catch:{ all -> 0x0110 }
            r2.<init>(r3)     // Catch:{ all -> 0x0110 }
            java.lang.String r0 = r6.c     // Catch:{ all -> 0x010b }
            java.lang.String r0 = com.flurry.sdk.q.a(r0)     // Catch:{ all -> 0x010b }
            r2.write(r0)     // Catch:{ all -> 0x010b }
            r2.close()
            if (r1 == 0) goto L_0x007f
            r1.close()
        L_0x007f:
            javax.net.ssl.HttpsURLConnection r0 = r6.k
            int r0 = r0.getResponseCode()
            r1 = 400(0x190, float:5.6E-43)
            if (r0 >= r1) goto L_0x00fb
            javax.net.ssl.HttpsURLConnection r1 = r6.k
            java.lang.String r2 = "Content-Signature"
            java.lang.String r1 = r1.getHeaderField(r2)
            r6.l = r1
            javax.net.ssl.HttpsURLConnection r1 = r6.k
            java.lang.String r2 = "ETag"
            java.lang.String r1 = r1.getHeaderField(r2)
            r6.g = r1
            java.lang.String r1 = i
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Content-Signature: "
            r2.<init>(r3)
            java.lang.String r3 = r6.l
            r2.append(r3)
            java.lang.String r3 = ", ETag: "
            r2.append(r3)
            java.lang.String r3 = r6.g
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.flurry.sdk.db.a(r1, r2)
            r1 = 304(0x130, float:4.26E-43)
            if (r0 != r1) goto L_0x00f4
            java.lang.String r0 = r6.c
            boolean r0 = r6.a(r0)
            if (r0 == 0) goto L_0x00d4
            com.flurry.sdk.g r0 = com.flurry.sdk.g.b
            r6.b = r0
            java.lang.String r0 = i
            java.lang.String r1 = "Empty 304 payload; No Change."
            com.flurry.sdk.db.a(r0, r1)
            goto L_0x00f4
        L_0x00d4:
            com.flurry.sdk.g r0 = new com.flurry.sdk.g
            com.flurry.sdk.g$a r1 = com.flurry.sdk.g.a.AUTHENTICATE
            java.lang.String r2 = "GUID Signature Error."
            r0.<init>(r1, r2)
            r6.b = r0
            java.lang.String r0 = i
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Authentication error: "
            r1.<init>(r2)
            com.flurry.sdk.g r2 = r6.b
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.flurry.sdk.db.b(r0, r1)
        L_0x00f4:
            javax.net.ssl.HttpsURLConnection r0 = r6.k
            java.io.InputStream r0 = r0.getInputStream()
            return r0
        L_0x00fb:
            java.io.IOException r1 = new java.io.IOException
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r2 = "Server response code is "
            java.lang.String r0 = r2.concat(r0)
            r1.<init>(r0)
            throw r1
        L_0x010b:
            r0 = move-exception
            r5 = r2
            r2 = r0
            r0 = r5
            goto L_0x0115
        L_0x0110:
            r2 = move-exception
            goto L_0x0115
        L_0x0112:
            r1 = move-exception
            r2 = r1
            r1 = r0
        L_0x0115:
            if (r0 == 0) goto L_0x011a
            r0.close()
        L_0x011a:
            if (r1 == 0) goto L_0x011f
            r1.close()
        L_0x011f:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.o.a():java.io.InputStream");
    }

    /* access modifiers changed from: protected */
    public final void b() {
        if (this.k != null) {
            this.k.disconnect();
        }
    }

    public final boolean c() {
        return "https://cfg.flurry.com/sdk/v1/config".equals(this.a);
    }

    /* access modifiers changed from: protected */
    public final boolean a(String str) {
        boolean z;
        if (!b(this.l)) {
            return false;
        }
        if (this.m) {
            z = t.c(this.e, str, this.f);
        } else {
            z = t.b(this.e, str, this.f);
        }
        if (z) {
            return true;
        }
        db.b(i, "Incorrect signature for response.");
        return false;
    }

    private boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            db.b(i, "Content-Signature is empty.");
            return false;
        }
        HashMap hashMap = new HashMap();
        for (String str2 : str.split(";")) {
            int indexOf = str2.indexOf("=");
            if (indexOf > 0) {
                hashMap.put(str2.substring(0, indexOf), str2.substring(indexOf + 1));
            }
        }
        this.d = (String) hashMap.get("keyid");
        if (TextUtils.isEmpty(this.d)) {
            db.b(i, "Error to get keyid from Signature.");
            return false;
        }
        this.e = u.a.get(this.d);
        db.a(i, "Signature keyid: " + this.d + ", key: " + this.e);
        if (this.e == null) {
            db.b(i, "Unknown keyid from Signature.");
            return false;
        }
        this.m = hashMap.containsKey("sha256ecdsa");
        this.f = (String) hashMap.get(this.m ? "sha256ecdsa" : "sha256rsa");
        if (TextUtils.isEmpty(this.f)) {
            db.b(i, "Error to get rsa from Signature.");
            return false;
        }
        db.a(i, "Signature rsa: " + this.f);
        return true;
    }
}
