package com.flurry.sdk;

import android.annotation.TargetApi;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.crypto.KeyGenerator;

public class cf {
    private static final String a = "cf";
    private KeyStore b;

    @TargetApi(23)
    public cf() {
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                this.b = KeyStore.getInstance("AndroidKeyStore");
                this.b.load((KeyStore.LoadStoreParameter) null);
                if (!this.b.containsAlias("fl.install.id.sec.key")) {
                    KeyGenerator instance = KeyGenerator.getInstance("AES", "AndroidKeyStore");
                    instance.init(new KeyGenParameterSpec.Builder("fl.install.id.sec.key", 3).setBlockModes(new String[]{"CBC"}).setEncryptionPaddings(new String[]{"PKCS7Padding"}).setRandomizedEncryptionRequired(false).setDigests(new String[]{"SHA-256", "SHA-512"}).build());
                    instance.generateKey();
                }
            } catch (IOException | InvalidAlgorithmParameterException | KeyStoreException | NoSuchAlgorithmException | NoSuchProviderException | CertificateException e) {
                String str = a;
                db.a(5, str, "Error while generating Key" + e.getMessage(), e);
            }
        }
    }

    public final Key a() {
        if (Build.VERSION.SDK_INT < 23 || this.b == null) {
            return null;
        }
        try {
            return this.b.getKey("fl.install.id.sec.key", (char[]) null);
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException unused) {
            db.a(6, a, "Error in getting key.");
            return null;
        }
    }
}
