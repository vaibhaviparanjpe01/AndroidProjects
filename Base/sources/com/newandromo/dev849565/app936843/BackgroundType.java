package com.newandromo.dev849565.app936843;

public final class BackgroundType {
    public static final int AUTOCOLOR = 2;
    public static final int AUTOCOLOR_SOLID = 3;
    public static final int AUTOCOLOR_TRANSLUCENT = 4;
    public static final int DEFAULT = 0;
    public static final int TRANSLUCENT = 1;

    public static boolean isAutoColorType(int i) {
        return i >= 2 && i <= 4;
    }

    public static String toString(int i) {
        switch (i) {
            case 0:
                return "default";
            case 1:
                return "translucent";
            case 2:
                return "autocolor";
            case 3:
                return "autocolor-solid";
            case 4:
                return "autocolor-translucent";
            default:
                return "unknown {" + i + ")";
        }
    }
}
