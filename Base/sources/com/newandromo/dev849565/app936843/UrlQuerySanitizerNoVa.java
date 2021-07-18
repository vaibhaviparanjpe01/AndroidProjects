package com.newandromo.dev849565.app936843;

import android.net.Uri;
import android.net.UrlQuerySanitizer;
import java.util.StringTokenizer;

public class UrlQuerySanitizerNoVa extends UrlQuerySanitizer {
    private static final int FLAGS_MASK = 4123;
    public static final String NO_VALUE_MARKER = "EmPtYeMpTyEmPtY";
    public static final String TAG = "UrlQuerySanitizer";
    private int flags;

    public UrlQuerySanitizerNoVa() {
    }

    public UrlQuerySanitizerNoVa(int i) {
        this.flags = i & FLAGS_MASK;
    }

    public UrlQuerySanitizerNoVa(int i, String str) {
        super(str);
        this.flags = i & FLAGS_MASK;
    }

    private boolean isSet(int i) {
        return (i & this.flags) != 0;
    }

    public void parseQuery(String str) {
        clear();
        StringTokenizer stringTokenizer = new StringTokenizer(str, "&");
        while (stringTokenizer.hasMoreElements()) {
            String nextToken = stringTokenizer.nextToken();
            if (nextToken.length() > 0) {
                int indexOf = nextToken.indexOf(61);
                if (indexOf < 0) {
                    parseEntry(nextToken, NO_VALUE_MARKER);
                } else {
                    parseEntry(nextToken.substring(0, indexOf), nextToken.substring(indexOf + 1));
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void parseEntry(java.lang.String r8, java.lang.String r9) {
        /*
            r7 = this;
            r0 = 1
            boolean r1 = r7.isSet(r0)
            r2 = 8
            r3 = 0
            if (r1 == 0) goto L_0x0022
            boolean r1 = r7.isSet(r2)
            if (r1 != 0) goto L_0x0022
            int r1 = r8.length()
            java.lang.String r8 = com.newandromo.dev849565.app936843.UriSanitizer.removeScript(r8)
            int r4 = r8.length()
            if (r4 != 0) goto L_0x0022
            if (r1 == 0) goto L_0x0022
            r1 = 1
            goto L_0x0023
        L_0x0022:
            r1 = 0
        L_0x0023:
            if (r1 != 0) goto L_0x0088
            r1 = 2
            boolean r1 = r7.isSet(r1)
            r4 = 16
            if (r1 == 0) goto L_0x0038
            boolean r1 = r7.isSet(r4)
            if (r1 != 0) goto L_0x0038
            java.lang.String r9 = com.newandromo.dev849565.app936843.UriSanitizer.removeScript(r9)
        L_0x0038:
            int r1 = r8.length()
            java.lang.String r8 = r7.preserveEncoded(r8)
            java.lang.String r8 = r7.unescape(r8)
            int r5 = r8.length()
            if (r5 != 0) goto L_0x004e
            if (r1 == 0) goto L_0x004e
            r5 = 1
            goto L_0x004f
        L_0x004e:
            r5 = 0
        L_0x004f:
            if (r5 != 0) goto L_0x0088
            android.net.UrlQuerySanitizer$ValueSanitizer r6 = r7.getEffectiveValueSanitizer(r8)
            if (r6 != 0) goto L_0x0058
            return
        L_0x0058:
            java.lang.String r9 = r7.preserveEncoded(r9)
            java.lang.String r9 = r7.unescape(r9)
            boolean r2 = r7.isSet(r2)
            if (r2 == 0) goto L_0x0075
            java.lang.String r8 = com.newandromo.dev849565.app936843.UriSanitizer.removeScriptEnc(r8)
            int r2 = r8.length()
            if (r2 != 0) goto L_0x0074
            if (r1 == 0) goto L_0x0074
            r5 = 1
            goto L_0x0075
        L_0x0074:
            r5 = 0
        L_0x0075:
            if (r5 != 0) goto L_0x0088
            boolean r0 = r7.isSet(r4)
            if (r0 == 0) goto L_0x0081
            java.lang.String r9 = com.newandromo.dev849565.app936843.UriSanitizer.removeScriptEnc(r9)
        L_0x0081:
            java.lang.String r9 = r6.sanitize(r9)
            r7.addSanitizedEntry(r8, r9)
        L_0x0088:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newandromo.dev849565.app936843.UrlQuerySanitizerNoVa.parseEntry(java.lang.String, java.lang.String):void");
    }

    public String unescape(String str) {
        return UriSanitizer.unescape(str);
    }

    private String preserveEncoded(String str) {
        return isSet(4096) ? UriSanitizer.preserveEncoded(str) : str;
    }

    private String restoreEncoded(String str) {
        return isSet(4096) ? UriSanitizer.restoreEncoded(str) : str;
    }

    public String getQuery(UrlQuerySanitizer urlQuerySanitizer, int i) {
        StringBuilder sb = new StringBuilder(i);
        for (UrlQuerySanitizer.ParameterValuePair appendQueryParameter : urlQuerySanitizer.getParameterList()) {
            appendQueryParameter(sb, appendQueryParameter, this.flags);
        }
        return sb.toString();
    }

    public static String getQuery(UrlQuerySanitizer urlQuerySanitizer, int i, int i2) {
        StringBuilder sb = new StringBuilder(i);
        for (UrlQuerySanitizer.ParameterValuePair appendQueryParameter : urlQuerySanitizer.getParameterList()) {
            appendQueryParameter(sb, appendQueryParameter, i2);
        }
        return sb.toString();
    }

    private static void appendQueryParameter(StringBuilder sb, UrlQuerySanitizer.ParameterValuePair parameterValuePair, int i) {
        if (sb.length() > 0) {
            sb.append("&");
        }
        sb.append(UriSanitizer.restoreEncoded(Uri.encode(parameterValuePair.mParameter, (String) null), i));
        if (!NO_VALUE_MARKER.equals(parameterValuePair.mValue)) {
            sb.append("=" + UriSanitizer.restoreEncoded(Uri.encode(parameterValuePair.mValue, ","), i));
        }
    }
}
