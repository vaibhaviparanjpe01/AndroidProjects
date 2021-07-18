package com.flurry.sdk;

import android.os.Build;
import android.text.TextUtils;
import com.flurry.sdk.cd;
import com.flurry.sdk.cs;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Locale;
import java.util.UUID;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class ce {
    private static final String a = "ce";
    private cf b;
    private int c;
    private byte[] d;
    private final cs<byte[]> e;
    private ct<cd> f;

    public ce() {
        this.b = null;
        this.c = 0;
        this.d = null;
        this.f = null;
        this.f = new ct<>(b(), "installationNum", 1, new dz<cd>() {
            public final dw<cd> a(int i) {
                return new cd.a();
            }
        });
        this.e = new cs<>(new ds());
        this.b = new cf();
        byte[] a2 = a(c());
        if (a2 != null && Build.VERSION.SDK_INT >= 23) {
            el.b(b());
            a(a2, cs.a.CRYPTO_ALGO_PADDING_7);
        }
        a();
    }

    public final synchronized byte[] a() {
        byte[] bArr;
        cs.a aVar;
        bArr = this.d;
        if (bArr == null) {
            if (Build.VERSION.SDK_INT < 23) {
                aVar = cs.a.CRYPTO_ALGO_PADDING_5;
            } else {
                aVar = cs.a.CRYPTO_ALGO_PADDING_7;
            }
            byte[] a2 = a(e());
            if (a2 == null) {
                String lowerCase = UUID.randomUUID().toString().toLowerCase(Locale.ENGLISH);
                byte[] bArr2 = null;
                if (!TextUtils.isEmpty(lowerCase)) {
                    String replaceAll = lowerCase.replaceAll("[^a-f0-9]+", "");
                    if (replaceAll.length() % 2 != 0) {
                        db.a(4, a, "Input string must contain an even number of characters ".concat(String.valueOf(replaceAll)));
                    } else {
                        bArr2 = em.e(replaceAll);
                    }
                }
                a(bArr2, aVar);
                bArr = bArr2;
            } else {
                bArr = a2;
            }
            this.d = bArr;
        }
        return bArr;
    }

    private boolean a(byte[] bArr, cs.a aVar) {
        cd cdVar;
        try {
            el.b(b());
            byte[] d2 = d();
            byte[] a2 = this.e.a(bArr, e(), new IvParameterSpec(d2), aVar);
            if (a2 != null) {
                cdVar = new cd(a2, d2, true, aVar.ordinal());
            } else {
                cdVar = new cd(bArr, new byte[0], false, aVar.ordinal());
            }
            this.f.a(cdVar);
            return true;
        } catch (IOException e2) {
            String str = a;
            db.a(5, str, "Error while generating UUID" + e2.getMessage(), e2);
            return false;
        }
    }

    private byte[] a(Key key) {
        byte[] bArr;
        try {
            cd a2 = this.f.a();
            if (a2 == null) {
                return null;
            }
            if (a2.a) {
                byte[] bArr2 = a2.b;
                byte[] bArr3 = a2.c;
                cs.a a3 = cs.a.a(a2.d);
                if (bArr2 == null || bArr3 == null) {
                    return null;
                }
                bArr = this.e.a(bArr3, key, new IvParameterSpec(bArr2), a3);
            } else {
                bArr = a2.c;
            }
            return bArr;
        } catch (IOException unused) {
            db.a(5, a, "Error while reading Android Install Id. Deleting file.");
            return null;
        }
    }

    private static File b() {
        return new File(el.a().getPath() + File.separator + "installationNum");
    }

    private static SecretKey c() {
        String str = ck.a().b;
        String a2 = ej.a(ck.a().a);
        try {
            return new SecretKeySpec(SecretKeyFactory.getInstance("PBEWithSHA256And256BitAES-CBC-BC").generateSecret(new PBEKeySpec(str.toCharArray(), ByteBuffer.allocate(8).putLong(!TextUtils.isEmpty(a2) ? em.g(a2) : Long.MIN_VALUE).array(), 1000, 256)).getEncoded(), "AES");
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e2) {
            db.a(4, a, "Error in generate secret key", e2);
            return null;
        }
    }

    private Key e() {
        if (Build.VERSION.SDK_INT < 23) {
            return c();
        }
        return this.b.a();
    }

    private static byte[] d() {
        try {
            byte[] bArr = new byte[16];
            SecureRandom.getInstance("SHA1PRNG").nextBytes(bArr);
            return bArr;
        } catch (NoSuchAlgorithmException e2) {
            db.a(4, a, "Error in generating iv", e2);
            return null;
        }
    }
}
