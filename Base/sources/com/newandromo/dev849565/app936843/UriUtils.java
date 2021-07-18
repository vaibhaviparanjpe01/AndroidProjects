package com.newandromo.dev849565.app936843;

import android.net.Uri;
import android.webkit.URLUtil;

public final class UriUtils {
    public static final String TAG = "UriUtils";

    public static Uri logDecodedParts(String str, Uri uri) {
        return uri;
    }

    public static Uri logEncodedParts(String str, Uri uri) {
        return uri;
    }

    public static Uri logParts(String str, Uri uri) {
        return uri;
    }

    private UriUtils() {
    }

    public static boolean isAssetUri(Uri uri) {
        return uri != null && URLUtil.isAssetUrl(uri.toString());
    }

    public static boolean isAndromoProtocol(Uri uri) {
        return uri != null && "andromo".equals(uri.getScheme());
    }

    public static Uri parse(Uri.Builder builder) {
        if (builder != null) {
            return Uri.parse(builder.toString());
        }
        return null;
    }

    public static Uri parse(UriBuilder uriBuilder) {
        if (uriBuilder != null) {
            return Uri.parse(uriBuilder.toString());
        }
        return null;
    }

    public static String trimAssetPath(String str) {
        int length = "file:///android_asset/".length();
        return (str == null || str.length() < length) ? str : str.substring(length);
    }

    public static Uri escapeForTel(Uri uri) {
        return Uri.parse(escapeForTel(uri.toString()));
    }

    public static String escapeForTel(String str) {
        if (str != null) {
            return str.replace("#", "%23");
        }
        return null;
    }

    public static String toSafeString(Uri uri) {
        String str;
        if (uri == null) {
            return "";
        }
        String scheme = uri.getScheme();
        String schemeSpecificPart = uri.getSchemeSpecificPart();
        if (scheme != null) {
            if (scheme.equalsIgnoreCase("tel") || scheme.equalsIgnoreCase("sip") || scheme.equalsIgnoreCase("sms") || scheme.equalsIgnoreCase("smsto") || scheme.equalsIgnoreCase("mailto")) {
                StringBuilder sb = new StringBuilder(64);
                sb.append(scheme);
                sb.append(':');
                if (schemeSpecificPart != null) {
                    for (int i = 0; i < schemeSpecificPart.length(); i++) {
                        char charAt = schemeSpecificPart.charAt(i);
                        if (charAt == '-' || charAt == '@' || charAt == '.') {
                            sb.append(charAt);
                        } else {
                            sb.append('x');
                        }
                    }
                }
                return sb.toString();
            } else if (scheme.equalsIgnoreCase("http") || scheme.equalsIgnoreCase("https") || scheme.equalsIgnoreCase("ftp")) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("//");
                sb2.append(uri.getHost() != null ? uri.getHost() : "");
                if (uri.getPort() != -1) {
                    str = ":" + uri.getPort();
                } else {
                    str = "";
                }
                sb2.append(str);
                sb2.append("/...");
                schemeSpecificPart = sb2.toString();
            }
        }
        StringBuilder sb3 = new StringBuilder(64);
        if (scheme != null) {
            sb3.append(scheme);
            sb3.append(':');
        }
        if (schemeSpecificPart != null) {
            sb3.append(schemeSpecificPart);
        }
        return sb3.toString();
    }

    public static String logParts(String str) {
        logParts(Uri.parse(str));
        return str;
    }

    public static Uri logParts(Uri uri) {
        return logParts(TAG, uri);
    }

    public static Uri logDecodedParts(Uri uri) {
        return logDecodedParts(TAG, uri);
    }

    public static Uri logEncodedParts(Uri uri) {
        return logEncodedParts(TAG, uri);
    }
}
