package com.flurry.sdk;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;

public class cs<ObjectType> {
    private static final String a = "cs";
    private dw<ObjectType> b;

    public cs(dw<ObjectType> dwVar) {
        this.b = dwVar;
    }

    public enum a {
        NONE(""),
        CRYPTO_ALGO_PADDING_7("AES/CBC/PKCS7Padding"),
        CRYPTO_ALGO_PADDING_5("AES/CBC/PKCS5Padding");
        
        String d;

        private a(String str) {
            this.d = str;
        }

        public static a a(int i) {
            for (a aVar : values()) {
                if (aVar.ordinal() == i) {
                    return aVar;
                }
            }
            return NONE;
        }
    }

    public final byte[] a(ObjectType objecttype, Key key, IvParameterSpec ivParameterSpec, a aVar) throws IOException {
        if (objecttype == null || key == null || aVar == null) {
            db.a(5, a, "Cannot encrypt, invalid params.");
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.b.a(byteArrayOutputStream, objecttype);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            Cipher instance = Cipher.getInstance(aVar.d);
            instance.init(1, key, ivParameterSpec);
            return instance.doFinal(byteArray);
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            String str = a;
            db.a(5, str, "Error in encrypt " + e.getMessage());
            return null;
        }
    }

    public final ObjectType a(byte[] bArr, Key key, IvParameterSpec ivParameterSpec, a aVar) throws IOException {
        if (bArr == null || key == null || aVar == null) {
            db.a(5, a, "Cannot decrypt, invalid params.");
            return null;
        }
        try {
            Cipher instance = Cipher.getInstance(aVar.d);
            instance.init(2, key, ivParameterSpec);
            return this.b.a(new ByteArrayInputStream(instance.doFinal(bArr)));
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            String str = a;
            db.a(5, str, "Error in decrypt " + e.getMessage());
            return null;
        }
    }
}
