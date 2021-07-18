package com.flurry.sdk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public final class fc {

    public static class a {
        public static MessageDigest a(String str) {
            try {
                return MessageDigest.getInstance(str);
            } catch (NoSuchAlgorithmException unused) {
                return null;
            }
        }
    }

    public static class b {
        private static final Random a = new SecureRandom();
        private static final char[] b = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

        public static String a() {
            char[] cArr = new char[32];
            for (int i = 0; i < 32; i++) {
                cArr[i] = b[a.nextInt(b.length)];
            }
            return new String(cArr);
        }
    }
}
