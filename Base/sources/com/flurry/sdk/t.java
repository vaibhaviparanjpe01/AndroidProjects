package com.flurry.sdk;

import android.content.Context;
import android.net.http.X509TrustManagerExtensions;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.bumptech.glide.load.Key;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLPeerUnverifiedException;

public class t {
    public static final Charset a = Charset.forName(Key.STRING_CHARSET_NAME);
    private static final String b = "t";
    private static X509TrustManagerExtensions c = new X509TrustManagerExtensions(b());

    static {
        if (Build.VERSION.SDK_INT >= 17) {
        }
    }

    private static String d(Context context) {
        return context.getPackageName() + ".variants";
    }

    public static synchronized boolean a(Context context) {
        boolean b2;
        synchronized (t.class) {
            b2 = b(context, d(context));
        }
        return b2;
    }

    private static synchronized boolean b(Context context, String str) {
        boolean exists;
        synchronized (t.class) {
            exists = context.getFileStreamPath(str).exists();
        }
        return exists;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:40|(2:42|43)|45|46) */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x002a */
    /* JADX WARNING: Missing exception handler attribute for start block: B:32:0x0048 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x005f */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0042 A[SYNTHETIC, Splitter:B:25:0x0042] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0059 A[SYNTHETIC, Splitter:B:42:0x0059] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:13:0x002a=Splitter:B:13:0x002a, B:45:0x005f=Splitter:B:45:0x005f} */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:32:0x0048=Splitter:B:32:0x0048, B:22:0x0039=Splitter:B:22:0x0039} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized java.lang.String b(android.content.Context r6) {
        /*
            java.lang.Class<com.flurry.sdk.t> r0 = com.flurry.sdk.t.class
            monitor-enter(r0)
            r1 = 0
            java.lang.String r2 = d(r6)     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x0037, all -> 0x0032 }
            java.io.FileInputStream r6 = r6.openFileInput(r2)     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x0037, all -> 0x0032 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ FileNotFoundException -> 0x0048, IOException -> 0x0030 }
            r2.<init>(r6)     // Catch:{ FileNotFoundException -> 0x0048, IOException -> 0x0030 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x0048, IOException -> 0x0030 }
            r3.<init>(r2)     // Catch:{ FileNotFoundException -> 0x0048, IOException -> 0x0030 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0048, IOException -> 0x0030 }
            r2.<init>()     // Catch:{ FileNotFoundException -> 0x0048, IOException -> 0x0030 }
        L_0x001b:
            java.lang.String r4 = r3.readLine()     // Catch:{ FileNotFoundException -> 0x0048, IOException -> 0x0030 }
            if (r4 == 0) goto L_0x0025
            r2.append(r4)     // Catch:{ FileNotFoundException -> 0x0048, IOException -> 0x0030 }
            goto L_0x001b
        L_0x0025:
            if (r6 == 0) goto L_0x002a
            r6.close()     // Catch:{ IOException -> 0x002a }
        L_0x002a:
            java.lang.String r6 = r2.toString()     // Catch:{ all -> 0x005d }
            monitor-exit(r0)
            return r6
        L_0x0030:
            r2 = move-exception
            goto L_0x0039
        L_0x0032:
            r6 = move-exception
            r5 = r1
            r1 = r6
            r6 = r5
            goto L_0x0057
        L_0x0037:
            r2 = move-exception
            r6 = r1
        L_0x0039:
            java.lang.String r3 = b     // Catch:{ all -> 0x0056 }
            java.lang.String r4 = "Error in reading file!"
            com.flurry.sdk.db.a((java.lang.String) r3, (java.lang.String) r4, (java.lang.Throwable) r2)     // Catch:{ all -> 0x0056 }
            if (r6 == 0) goto L_0x0045
            r6.close()     // Catch:{ IOException -> 0x0045 }
        L_0x0045:
            monitor-exit(r0)
            return r1
        L_0x0047:
            r6 = r1
        L_0x0048:
            java.lang.String r2 = b     // Catch:{ all -> 0x0056 }
            java.lang.String r3 = "File not found!"
            com.flurry.sdk.db.e(r2, r3)     // Catch:{ all -> 0x0056 }
            if (r6 == 0) goto L_0x0054
            r6.close()     // Catch:{ IOException -> 0x0054 }
        L_0x0054:
            monitor-exit(r0)
            return r1
        L_0x0056:
            r1 = move-exception
        L_0x0057:
            if (r6 == 0) goto L_0x005f
            r6.close()     // Catch:{ IOException -> 0x005f }
            goto L_0x005f
        L_0x005d:
            r6 = move-exception
            goto L_0x0060
        L_0x005f:
            throw r1     // Catch:{ all -> 0x005d }
        L_0x0060:
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.t.b(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:40:0x0044 */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0033 A[SYNTHETIC, Splitter:B:27:0x0033] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x003e A[SYNTHETIC, Splitter:B:37:0x003e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void a(android.content.Context r4, java.lang.String r5) {
        /*
            java.lang.Class<com.flurry.sdk.t> r0 = com.flurry.sdk.t.class
            monitor-enter(r0)
            if (r5 != 0) goto L_0x0007
            monitor-exit(r0)
            return
        L_0x0007:
            r1 = 0
            java.lang.String r2 = d(r4)     // Catch:{ IOException -> 0x0029 }
            r3 = 0
            java.io.FileOutputStream r4 = r4.openFileOutput(r2, r3)     // Catch:{ IOException -> 0x0029 }
            byte[] r5 = r5.getBytes()     // Catch:{ IOException -> 0x0024, all -> 0x0021 }
            r4.write(r5)     // Catch:{ IOException -> 0x0024, all -> 0x0021 }
            if (r4 == 0) goto L_0x003a
            r4.close()     // Catch:{ IOException -> 0x001f }
            monitor-exit(r0)
            return
        L_0x001f:
            monitor-exit(r0)
            return
        L_0x0021:
            r5 = move-exception
            r1 = r4
            goto L_0x003c
        L_0x0024:
            r5 = move-exception
            r1 = r4
            goto L_0x002a
        L_0x0027:
            r5 = move-exception
            goto L_0x003c
        L_0x0029:
            r5 = move-exception
        L_0x002a:
            java.lang.String r4 = b     // Catch:{ all -> 0x0027 }
            java.lang.String r2 = "Error in writing data to file"
            com.flurry.sdk.db.a((java.lang.String) r4, (java.lang.String) r2, (java.lang.Throwable) r5)     // Catch:{ all -> 0x0027 }
            if (r1 == 0) goto L_0x003a
            r1.close()     // Catch:{ IOException -> 0x0038 }
            monitor-exit(r0)
            return
        L_0x0038:
            monitor-exit(r0)
            return
        L_0x003a:
            monitor-exit(r0)
            return
        L_0x003c:
            if (r1 == 0) goto L_0x0044
            r1.close()     // Catch:{ IOException -> 0x0044 }
            goto L_0x0044
        L_0x0042:
            r4 = move-exception
            goto L_0x0045
        L_0x0044:
            throw r5     // Catch:{ all -> 0x0042 }
        L_0x0045:
            monitor-exit(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.t.a(android.content.Context, java.lang.String):void");
    }

    public static synchronized boolean c(Context context) {
        boolean deleteFile;
        synchronized (t.class) {
            deleteFile = context.deleteFile(d(context));
            if (deleteFile) {
                db.e(b, "File removed from memory");
            } else {
                db.e(b, "Error in clearing data from memory");
            }
        }
        return deleteFile;
    }

    public static void a(HttpsURLConnection httpsURLConnection) throws SSLException {
        if (Build.VERSION.SDK_INT >= 17 && c != null) {
            String str = "";
            try {
                Certificate[] serverCertificates = httpsURLConnection.getServerCertificates();
                List<X509Certificate> checkServerTrusted = c.checkServerTrusted((X509Certificate[]) Arrays.copyOf(serverCertificates, serverCertificates.length, X509Certificate[].class), "RSA", httpsURLConnection.getURL().getHost());
                if (checkServerTrusted != null) {
                    MessageDigest instance = MessageDigest.getInstance("SHA-256");
                    for (X509Certificate next : checkServerTrusted) {
                        byte[] encoded = next.getPublicKey().getEncoded();
                        instance.update(encoded, 0, encoded.length);
                        String encodeToString = Base64.encodeToString(instance.digest(), 2);
                        if (u.b.contains(encodeToString)) {
                            db.a(b, "Found matched pin: ".concat(String.valueOf(encodeToString)));
                            return;
                        }
                        str = str + "    sha256/" + encodeToString + ": " + next.getSubjectDN().toString() + "\n";
                    }
                    throw new SSLPeerUnverifiedException("Certificate pinning failure!\n  Peer certificate chain:\n".concat(String.valueOf(str)));
                }
                throw new SSLPeerUnverifiedException("Empty trusted chain Certificate.");
            } catch (NoSuchAlgorithmException e) {
                db.a(b, "Error in validating pinning: ", (Throwable) e);
            } catch (CertificateException e2) {
                db.a(b, "Error in getting certificate: ", (Throwable) e2);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0026 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static javax.net.ssl.X509TrustManager b() {
        /*
            r0 = 0
            java.lang.String r1 = javax.net.ssl.TrustManagerFactory.getDefaultAlgorithm()     // Catch:{ NoSuchAlgorithmException -> 0x001b, KeyStoreException -> 0x0011 }
            javax.net.ssl.TrustManagerFactory r1 = javax.net.ssl.TrustManagerFactory.getInstance(r1)     // Catch:{ NoSuchAlgorithmException -> 0x001b, KeyStoreException -> 0x0011 }
            r1.init(r0)     // Catch:{ NoSuchAlgorithmException -> 0x000f, KeyStoreException -> 0x000d }
            goto L_0x0024
        L_0x000d:
            r2 = move-exception
            goto L_0x0013
        L_0x000f:
            r2 = move-exception
            goto L_0x001d
        L_0x0011:
            r2 = move-exception
            r1 = r0
        L_0x0013:
            java.lang.String r3 = b
            java.lang.String r4 = "Error in getting trust manager: "
            com.flurry.sdk.db.a((java.lang.String) r3, (java.lang.String) r4, (java.lang.Throwable) r2)
            goto L_0x0024
        L_0x001b:
            r2 = move-exception
            r1 = r0
        L_0x001d:
            java.lang.String r3 = b
            java.lang.String r4 = "Error in getting trust manager: "
            com.flurry.sdk.db.a((java.lang.String) r3, (java.lang.String) r4, (java.lang.Throwable) r2)
        L_0x0024:
            if (r1 != 0) goto L_0x0027
            return r0
        L_0x0027:
            javax.net.ssl.TrustManager[] r1 = r1.getTrustManagers()
            int r2 = r1.length
            r3 = 0
        L_0x002d:
            if (r3 >= r2) goto L_0x003c
            r4 = r1[r3]
            boolean r5 = r4 instanceof javax.net.ssl.X509TrustManager
            if (r5 == 0) goto L_0x0039
            r0 = r4
            javax.net.ssl.X509TrustManager r0 = (javax.net.ssl.X509TrustManager) r0
            goto L_0x003c
        L_0x0039:
            int r3 = r3 + 1
            goto L_0x002d
        L_0x003c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.t.b():javax.net.ssl.X509TrustManager");
    }

    public static boolean a(String str, String str2, String str3) {
        String str4;
        if (TextUtils.isEmpty(str) || (str4 = u.a.get(str)) == null) {
            return false;
        }
        if (str.indexOf("com.flurry.configkey.prod.ec.") != -1) {
            return c(str4, str2, str3);
        }
        return b(str4, str2, str3);
    }

    public static boolean b(String str, String str2, String str3) {
        return a(str, str2, str3, "RSA", "SHA256withRSA");
    }

    public static boolean c(String str, String str2, String str3) {
        return a(str, str2, str3, "EC", "SHA256withECDSA");
    }

    private static boolean a(String str, String str2, String str3, String str4, String str5) {
        try {
            PublicKey generatePublic = KeyFactory.getInstance(str4).generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
            Signature instance = Signature.getInstance(str5);
            instance.initVerify(generatePublic);
            instance.update(str2.getBytes(a));
            return instance.verify(Base64.decode(str3, 0));
        } catch (GeneralSecurityException e) {
            db.b(b, "GeneralSecurityException for Signature: ".concat(String.valueOf(e)));
            return false;
        }
    }

    public static boolean a() {
        try {
            KeyFactory.getInstance("EC");
            Signature.getInstance("SHA256withECDSA");
            return true;
        } catch (NoSuchAlgorithmException e) {
            db.a(b, "ECDSA encryption is not available: ".concat(String.valueOf(e)));
            return false;
        }
    }

    public static boolean a(String str) {
        return "com.flurry.configkey.prod.ec.1".equals(str) || "com.flurry.configkey.prod.rot.6".equals(str) || "com.flurry.configkey.prod.fs.0".equals(str);
    }
}
