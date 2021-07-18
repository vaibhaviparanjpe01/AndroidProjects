package com.newandromo.dev849565.app936843;

import android.net.UrlQuerySanitizer;

public final class UrlQuerySanitizzr {
    static final boolean SANITIZER_BUGGED = (UrlQuerySanitizer.getAllIllegal().sanitize("javascript:").length() > 0);
    static final boolean SANITIZER_OK = (!SANITIZER_BUGGED);
    private static final UrlQuerySanitizer.ValueSanitizer sAllButNulAndAngleBracketsLegal;
    private static final UrlQuerySanitizer.ValueSanitizer sAllButNulLegal;
    private static final UrlQuerySanitizer.ValueSanitizer sAllButWhitespaceLegal;
    private static final UrlQuerySanitizer.ValueSanitizer sAllIllegal;
    private static final UrlQuerySanitizer.ValueSanitizer sAmpAndSpaceLegal;
    private static final UrlQuerySanitizer.ValueSanitizer sAmpLegal;
    private static final UrlQuerySanitizer.ValueSanitizer sSpaceLegal;
    private static final UrlQuerySanitizer.ValueSanitizer sURLLegal;
    private static final UrlQuerySanitizer.ValueSanitizer sUrlAndSpaceLegal;

    private UrlQuerySanitizzr() {
    }

    static {
        UrlQuerySanitizer.ValueSanitizer valueSanitizer;
        UrlQuerySanitizer.ValueSanitizer valueSanitizer2;
        UrlQuerySanitizer.ValueSanitizer valueSanitizer3;
        UrlQuerySanitizer.ValueSanitizer valueSanitizer4;
        UrlQuerySanitizer.ValueSanitizer valueSanitizer5;
        UrlQuerySanitizer.ValueSanitizer valueSanitizer6;
        UrlQuerySanitizer.ValueSanitizer valueSanitizer7;
        UrlQuerySanitizer.ValueSanitizer valueSanitizer8;
        UrlQuerySanitizer.ValueSanitizer valueSanitizer9;
        if (SANITIZER_OK) {
            valueSanitizer = UrlQuerySanitizer.getAllIllegal();
        } else {
            valueSanitizer = fix(0);
        }
        sAllIllegal = valueSanitizer;
        if (SANITIZER_OK) {
            valueSanitizer2 = UrlQuerySanitizer.getAllButNulLegal();
        } else {
            valueSanitizer2 = fix(1535);
        }
        sAllButNulLegal = valueSanitizer2;
        if (SANITIZER_OK) {
            valueSanitizer3 = UrlQuerySanitizer.getAllButWhitespaceLegal();
        } else {
            valueSanitizer3 = fix(1532);
        }
        sAllButWhitespaceLegal = valueSanitizer3;
        if (SANITIZER_OK) {
            valueSanitizer4 = UrlQuerySanitizer.getUrlLegal();
        } else {
            valueSanitizer4 = fix(404);
        }
        sURLLegal = valueSanitizer4;
        if (SANITIZER_OK) {
            valueSanitizer5 = UrlQuerySanitizer.getUrlAndSpaceLegal();
        } else {
            valueSanitizer5 = fix(405);
        }
        sUrlAndSpaceLegal = valueSanitizer5;
        if (SANITIZER_OK) {
            valueSanitizer6 = UrlQuerySanitizer.getAmpLegal();
        } else {
            valueSanitizer6 = fix(128);
        }
        sAmpLegal = valueSanitizer6;
        if (SANITIZER_OK) {
            valueSanitizer7 = UrlQuerySanitizer.getAmpAndSpaceLegal();
        } else {
            valueSanitizer7 = fix(129);
        }
        sAmpAndSpaceLegal = valueSanitizer7;
        if (SANITIZER_OK) {
            valueSanitizer8 = UrlQuerySanitizer.getSpaceLegal();
        } else {
            valueSanitizer8 = fix(1);
        }
        sSpaceLegal = valueSanitizer8;
        if (SANITIZER_OK) {
            valueSanitizer9 = UrlQuerySanitizer.getAllButNulAndAngleBracketsLegal();
        } else {
            valueSanitizer9 = fix(1439);
        }
        sAllButNulAndAngleBracketsLegal = valueSanitizer9;
    }

    public static int fixFlags(int i) {
        return SANITIZER_BUGGED ? i ^ 1024 : i;
    }

    private static UrlQuerySanitizer.ValueSanitizer fix(int i) {
        return new UrlQuerySanitizer.IllegalCharacterValueSanitizer(fixFlags(i));
    }

    public static final UrlQuerySanitizer.ValueSanitizer getAllIllegal() {
        return sAllIllegal;
    }

    public static final UrlQuerySanitizer.ValueSanitizer getAllButNulLegal() {
        return sAllButNulLegal;
    }

    public static final UrlQuerySanitizer.ValueSanitizer getAllButWhitespaceLegal() {
        return sAllButWhitespaceLegal;
    }

    public static final UrlQuerySanitizer.ValueSanitizer getUrlLegal() {
        return sURLLegal;
    }

    public static final UrlQuerySanitizer.ValueSanitizer getUrlAndSpaceLegal() {
        return sUrlAndSpaceLegal;
    }

    public static final UrlQuerySanitizer.ValueSanitizer getAmpLegal() {
        return sAmpLegal;
    }

    public static final UrlQuerySanitizer.ValueSanitizer getAmpAndSpaceLegal() {
        return sAmpAndSpaceLegal;
    }

    public static final UrlQuerySanitizer.ValueSanitizer getSpaceLegal() {
        return sSpaceLegal;
    }

    public static final UrlQuerySanitizer.ValueSanitizer getAllButNulAndAngleBracketsLegal() {
        return sAllButNulAndAngleBracketsLegal;
    }
}
