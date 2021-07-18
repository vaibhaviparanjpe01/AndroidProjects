package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.support.annotation.Nullable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    private static final String DEF_TYPE_STRING = "string";
    private static final String REGEX_RESOURCE_STRING = "@string/([A-Za-z0-9-_]*)";
    private static final String TAG = "StringUtils";

    public static String nullToEmpty(@Nullable String str) {
        return str == null ? "" : str;
    }

    public static String trimCharFromFront(String str, char c) {
        if (str == null || str.equals("")) {
            return str;
        }
        String trim = str.trim();
        if (trim == null) {
            return trim;
        }
        int i = 0;
        while (trim.charAt(i) == c) {
            i++;
        }
        return trim.substring(i).trim();
    }

    public static String trimCharFromEnd(String str, char c) {
        if (str == null || str.equals("")) {
            return str;
        }
        String trim = str.trim();
        if (trim == null) {
            return trim;
        }
        int length = trim.length() - 1;
        while (trim.charAt(length) == c) {
            length--;
            if (length < 0) {
                return "";
            }
        }
        return trim.substring(0, length + 1).trim();
    }

    public static String replaceResourceStrings(Context context, String str) {
        Matcher matcher = Pattern.compile(REGEX_RESOURCE_STRING).matcher(str);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            String stringByName = getStringByName(context, matcher.group(1));
            if (stringByName == null) {
                stringByName = matcher.group(1);
            }
            matcher.appendReplacement(stringBuffer, replaceResourceStrings(context, stringByName));
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    public static String getStringByName(Context context, String str) {
        int resourceId = getResourceId(context, DEF_TYPE_STRING, str);
        if (resourceId != 0) {
            return context.getString(resourceId);
        }
        return null;
    }

    private static int getResourceId(Context context, String str, String str2) {
        return context.getResources().getIdentifier(str2, str, context.getPackageName());
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    @Nullable
    public static String emptyToNull(@Nullable String str) {
        if (isNullOrEmpty(str)) {
            return null;
        }
        return str;
    }

    public static boolean regionMatchesIgnoreCase(CharSequence charSequence, int i, CharSequence charSequence2, int i2, int i3) {
        if (i + i3 > charSequence.length() || i2 + i3 > charSequence2.length()) {
            return false;
        }
        while (true) {
            i3--;
            if (i3 >= 0) {
                char charAt = charSequence.charAt(i + i3);
                char charAt2 = charSequence2.charAt(i2 + i3);
                if (charAt != charAt2) {
                    if (charAt > 'z' || charAt < 'A') {
                        break;
                    }
                    if (charAt <= 'Z') {
                        charAt = (char) (charAt | ' ');
                    }
                    if (charAt2 <= 'Z' && charAt2 >= 'A') {
                        charAt2 = (char) (charAt2 | ' ');
                    }
                    if (charAt != charAt2) {
                        break;
                    }
                }
            } else {
                return true;
            }
        }
        return false;
    }

    public static boolean regionMatchesIgnoreCase(String str, int i, String str2, int i2, int i3) {
        return str.regionMatches(true, i, str2, i2, i3);
    }

    public static boolean containsIgnoreCase(CharSequence charSequence, CharSequence charSequence2) {
        if (!(charSequence == null || charSequence2 == null)) {
            int length = charSequence2.length();
            int length2 = charSequence.length() - length;
            for (int i = 0; i <= length2; i++) {
                if (regionMatchesIgnoreCase(charSequence, i, charSequence2, 0, length)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsIgnoreCase(String str, String str2) {
        if (!(str == null || str2 == null)) {
            int length = str2.length();
            int length2 = str.length() - length;
            for (int i = 0; i <= length2; i++) {
                if (str.regionMatches(true, i, str2, 0, length)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int indexOfIgnoreCase(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null || charSequence2 == null) {
            return -1;
        }
        int length = charSequence2.length();
        int length2 = charSequence.length() - length;
        for (int i = 0; i <= length2; i++) {
            if (regionMatchesIgnoreCase(charSequence, i, charSequence2, 0, length)) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOfIgnoreCase(String str, String str2) {
        if (str == null || str2 == null) {
            return -1;
        }
        int length = str2.length();
        int length2 = str.length() - length;
        for (int i = 0; i <= length2; i++) {
            if (str.regionMatches(true, i, str2, 0, length)) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOfIgnoreCase(String str, int i, String str2) {
        if (str == null || str2 == null) {
            return -1;
        }
        int length = str2.length();
        int length2 = str.length() - length;
        while (i <= length2) {
            if (str.regionMatches(true, i, str2, 0, length)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static String getLongestString(String[] strArr) {
        String str = null;
        int i = 0;
        for (String str2 : strArr) {
            if (str2.length() > i) {
                i = str2.length();
                str = str2;
            }
        }
        return str;
    }

    public static int getMaxLength(String[] strArr) {
        int i = 0;
        for (String str : strArr) {
            if (str.length() > i) {
                i = str.length();
            }
        }
        return i;
    }

    public static int getMinLength(String[] strArr) {
        int i = Integer.MAX_VALUE;
        for (String str : strArr) {
            if (str.length() < i) {
                i = str.length();
            }
        }
        return i;
    }
}
