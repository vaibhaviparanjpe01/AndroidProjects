package com.newandromo.dev849565.app936843;

import android.support.v4.util.Pair;
import android.view.View;
import java.lang.reflect.Array;
import java.util.Arrays;

public final class ArrayUtils {
    public static String[] removeElement(String[] strArr, int i) {
        if (i <= 0 || i >= strArr.length) {
            return strArr;
        }
        String[] strArr2 = new String[(strArr.length - 1)];
        System.arraycopy(strArr, 0, strArr2, 0, i);
        if (i < strArr2.length) {
            System.arraycopy(strArr, i + 1, strArr2, i, (strArr.length - i) - 1);
        }
        return strArr2;
    }

    public static int[] removeElement(int[] iArr, int i) {
        if (i <= 0 || i >= iArr.length) {
            return iArr;
        }
        int[] iArr2 = new int[(iArr.length - 1)];
        System.arraycopy(iArr, 0, iArr2, 0, i);
        if (i < iArr2.length) {
            System.arraycopy(iArr, i + 1, iArr2, i, (iArr.length - i) - 1);
        }
        return iArr2;
    }

    public static String[] insertElement(String[] strArr, int i, String str) {
        if (i < 0 || i > strArr.length) {
            return strArr;
        }
        String[] strArr2 = new String[(strArr.length + 1)];
        System.arraycopy(strArr, 0, strArr2, 0, i);
        if (i < strArr2.length) {
            strArr2[i] = str;
            System.arraycopy(strArr, i, strArr2, i + 1, strArr.length - i);
        }
        return strArr2;
    }

    public static int[] insertElement(int[] iArr, int i, int i2) {
        if (i < 0 || i > iArr.length) {
            return iArr;
        }
        int[] iArr2 = new int[(iArr.length + 1)];
        System.arraycopy(iArr, 0, iArr2, 0, i);
        if (i < iArr2.length) {
            iArr2[i] = i2;
            System.arraycopy(iArr, i, iArr2, i + 1, iArr.length - i);
        }
        return iArr2;
    }

    public static Pair<View, String>[] append(Pair<View, String>[] pairArr, Pair<View, String>... pairArr2) {
        Pair<View, String>[] pairArr3 = (Pair[]) Array.newInstance(pairArr.getClass().getComponentType(), pairArr.length + pairArr2.length);
        System.arraycopy(pairArr2, 0, pairArr3, pairArr.length, pairArr2.length);
        return pairArr3;
    }

    public static <T> T[] concat(T[] tArr, T[]... tArr2) {
        int length = tArr.length;
        for (T[] length2 : tArr2) {
            length += length2.length;
        }
        T[] copyOf = Arrays.copyOf(tArr, length);
        int length3 = tArr.length;
        int i = length3;
        for (T[] tArr3 : tArr2) {
            System.arraycopy(tArr3, 0, copyOf, i, tArr3.length);
            i += tArr3.length;
        }
        return copyOf;
    }
}
