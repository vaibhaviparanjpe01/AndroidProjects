package com.newandromo.dev849565.app936843;

import java.lang.reflect.Array;

public final class HashCodeUtils {
    private static final int ODD_PRIME_NUMBER = 37;
    public static final int SEED = 23;

    private static int firstTerm(int i) {
        return i * 37;
    }

    public static int hash(int i, boolean z) {
        return firstTerm(i) + (z ? 1 : 0);
    }

    public static int hash(int i, char c) {
        return firstTerm(i) + c;
    }

    public static int hash(int i, int i2) {
        return firstTerm(i) + i2;
    }

    public static int hash(int i, long j) {
        return firstTerm(i) + ((int) (j ^ (j >>> 32)));
    }

    public static int hash(int i, float f) {
        return hash(i, Float.floatToIntBits(f));
    }

    public static int hash(int i, double d) {
        return hash(i, Double.doubleToLongBits(d));
    }

    public static int hash(int i, Object obj) {
        if (obj == null) {
            return hash(i, 0);
        }
        if (!isArray(obj)) {
            return hash(i, obj.hashCode());
        }
        int length = Array.getLength(obj);
        for (int i2 = 0; i2 < length; i2++) {
            i = hash(i, Array.get(obj, i2));
        }
        return i;
    }

    private static boolean isArray(Object obj) {
        return obj.getClass().isArray();
    }
}
