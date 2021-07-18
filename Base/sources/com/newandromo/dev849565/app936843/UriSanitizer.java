package com.newandromo.dev849565.app936843;

import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.util.Log;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class UriSanitizer {
    private static final String AMP_MARKER = "aMpAmPaMpAmPaMp";
    private static final String AT_MARKER = "aTAtaTAtaTAt";
    private static final Pattern BRACKET_PATTERN = Pattern.compile("[<>]");
    private static final String COLON_MARKER = "cOlOnCoLoNcOlOn";
    private static final String COMMA_MARKER = "cOmMaCoMmAcOmMa";
    private static String[] CONTROL_REPL = {"", "%01", "%02", "%03", "%04", "%05", "%06", "%07", "%08", "%09", "%0a", "%0b", "%0c", "%0d", "%0e", "%0f", "%10", "%11", "%12", "%13", "%14", "%15", "%16", "%17", "%18", "%19", "%1a", "%1b", "%1c", "%1d", "%1e", "%1f", "%20"};
    static int DEFAULT_FLAGS = 5383;
    private static final String DOLLAR_MARKER = "dOlLaRDoLlArdOlLaR";
    private static final String DQUOTE_MARKER = "dOuBlEqUoTeDoUbLeQuOtE";
    private static final Pattern ENCODED_AMP_PATTERN = Pattern.compile(ENCODED_AMP_STRING);
    private static final String ENCODED_AMP_STRING = "%26";
    private static final Pattern ENCODED_AT_PATTERN = Pattern.compile(ENCODED_AT_STRING);
    private static final String ENCODED_AT_STRING = "%40";
    private static final Pattern ENCODED_COLON_PATTERN = Pattern.compile("%3[aA]");
    private static final String ENCODED_COLON_STRING = "%3A";
    private static final Pattern ENCODED_COMMA_PATTERN = Pattern.compile("%2[cC]");
    private static final String ENCODED_COMMA_STRING = "%2C";
    private static final Pattern ENCODED_DOLLAR_PATTERN = Pattern.compile(ENCODED_DOLLAR_STRING);
    private static final String ENCODED_DOLLAR_STRING = "%24";
    private static final Pattern ENCODED_DQUOTE_PATTERN = Pattern.compile(ENCODED_DQUOTE_STRING);
    private static final String ENCODED_DQUOTE_STRING = "%22";
    private static final Pattern ENCODED_EQUALS_PATTERN = Pattern.compile("%3[dD]");
    private static final String ENCODED_EQUALS_STRING = "%3D";
    private static final Pattern ENCODED_PLUS_PATTERN = Pattern.compile("%2[bB]");
    private static final String ENCODED_PLUS_STRING = "%2B";
    private static final Pattern ENCODED_QUESTION_PATTERN = Pattern.compile("%3[fF]");
    private static final String ENCODED_QUESTION_STRING = "%3F";
    private static final Pattern ENCODED_SEMICOLON_PATTERN = Pattern.compile("%3[bB]");
    private static final String ENCODED_SEMICOLON_STRING = "%3B";
    private static final Pattern ENCODED_SLASH_PATTERN = Pattern.compile("%2[fF]");
    private static final String ENCODED_SLASH_STRING = "%2F";
    private static final Pattern ENCODED_SQUOTE_PATTERN = Pattern.compile(ENCODED_SQUOTE_STRING);
    private static final String ENCODED_SQUOTE_STRING = "%27";
    public static final int ENCODE_SPACES = 2048;
    private static final String EQUALS_MARKER = "eQuAlSEqUaLseQuAlS";
    private static final long HTML_SPACE_CHAR_BITMASK = 4294981120L;
    private static final String JAVASCRIPT_PREFIX = "javascript:";
    private static final Pattern JAVASCRIPT_PREFIX_PATTERN = Pattern.compile("(?i)(?:j(?:[^\\S\\ ]|\\x0)*a(?:[^\\S\\ ]|\\x0)*v(?:[^\\S\\ ]|\\x0)*a(?:[^\\S\\ ]|\\x0)*|v(?:[^\\S\\ ]|\\x0)*b(?:[^\\S\\ ]|\\x0)*)s(?:[^\\S\\ ]|\\x0)*c(?:[^\\S\\ ]|\\x0)*r(?:[^\\S\\ ]|\\x0)*i(?:[^\\S\\ ]|\\x0)*p(?:[^\\S\\ ]|\\x0)*t(?:[^\\S\\ ]|\\x0)*:");
    private static final Pattern JAVASCRIPT_PREFIX_PATTERN_ENC = Pattern.compile("(?:[jJ](?:[^\\S\\ ]|\\x0)*[aA](?:[^\\S\\ ]|\\x0)*[vV](?:[^\\S\\ ]|\\x0)*[aA](?:[^\\S\\ ]|\\x0)*|[vV](?:[^\\S\\ ]|\\x0)*[bB](?:[^\\S\\ ]|\\x0)*)[sS](?:[^\\S\\ ]|\\x0)*[cC](?:[^\\S\\ ]|\\x0)*[rR](?:[^\\S\\ ]|\\x0)*[iI](?:[^\\S\\ ]|\\x0)*[pP](?:[^\\S\\ ]|\\x0)*[tT](?:[^\\S\\ ]|\\x0)*(:|cOlOnCoLoNcOlOn)");
    private static String[] JS_EVENTS = {"onAbort", "onActivate", "onAfterPrint", "onAfterUpdate", "onBeforeActivate", "onBeforeCopy", "onBeforeCut", "onBeforeDeactivate", "onBeforeEditFocus", "onBeforePaste", "onBeforePrint", "onBeforeUnload", "onBeforeUpdate", "onBegin", "onBlur", "onBounce", "onCellChange", "onChange", "onClick", "onContextMenu", "onControlSelect", "onCopy", "onCut", "onDataAvailable", "onDataSetChanged", "onDataSetComplete", "onDblClick", "onDeactivate", "onDrag", "onDragEnd", "onDragLeave", "onDragEnter", "onDragOver", "onDragDrop", "onDragStart", "onDrop", "onEnd", "onError", "onErrorUpdate", "onFilterChange", "onFinish", "onFocus", "onFocusIn", "onFocusOut", "onHashChange", "onHelp", "onInput", "onKeyDown", "onKeyPress", "onKeyUp", "onLayoutComplete", "onLoad", "onLoseCapture", "onMediaComplete", "onMediaError", "onMessage", "onMouseDown", "onMouseEnter", "onMouseLeave", "onMouseMove", "onMouseOut", "onMouseOver", "onMouseUp", "onMouseWheel", "onMove", "onMoveEnd", "onMoveStart", "onOffline", "onOnline", "onOutOfSync", "onPaste", "onPause", "onPopState", "onProgress", "onPropertyChange", "onReadyStateChange()", "onRedo", "onRepeat", "onReset", "onResize", "onResizeEnd", "onResizeStart", "onResume", "onReverse", "onRowsEnter", "onRowExit", "onRowDelete", "onRowInserted", "onScroll", "onSeek", "onSelect", "onSelectionChange", "onSelectStart", "onStart", "onStop", "onStorage", "onSyncRestored", "onSubmit", "onTimeError", "onTrackChange", "onUndo", "onUnload", "onURLFlip"};
    private static final Pattern JS_EVENTS_PATTERN = Pattern.compile("(?i)on\\S*?[=(]");
    private static final Pattern JS_EVENTS_PATTERN_ENC = Pattern.compile("[oO][nN]\\S*?([=(]|eQuAlSEqUaLseQuAlS)");
    private static final int MAX_LENGTH_JS_EVENTS = StringUtils.getMaxLength(JS_EVENTS);
    private static final int MIN_LENGTH_JS_EVENTS = StringUtils.getMinLength(JS_EVENTS);
    private static final int MIN_SCRIPT_PREFIX_LENGTH = Math.min(JAVASCRIPT_PREFIX.length(), VBSCRIPT_PREFIX.length());
    private static final int MIN_SCRIPT_TEST_LENGTH = "script".length();
    private static final String PLUS_MARKER = "pLuSPlUspLuSPlUs";
    public static final int PRESERVE_ENCODED = 4096;
    private static final String QUESTION_MARKER = "qUeStIoNQuEsTiOn";
    public static final int REMOVE_ENCODED_SCRIPT = 56;
    public static final int REMOVE_ENCODED_SCRIPT_FROM_KEYS = 8;
    public static final int REMOVE_ENCODED_SCRIPT_FROM_QUERY = 24;
    public static final int REMOVE_ENCODED_SCRIPT_FROM_VALUES = 16;
    public static final int REMOVE_ENCODED_SCRIPT_OUTSIDE_QUERY = 32;
    public static final int REMOVE_SCRIPT_MASK = 63;
    public static final int REMOVE_UNENCODED_SCRIPT = 7;
    public static final int REMOVE_UNENCODED_SCRIPT_FROM_KEYS = 1;
    public static final int REMOVE_UNENCODED_SCRIPT_FROM_QUERY = 3;
    public static final int REMOVE_UNENCODED_SCRIPT_FROM_VALUES = 2;
    public static final int REMOVE_UNENCODED_SCRIPT_OUTSIDE_QUERY = 4;
    public static final int SANITIZE = 64;
    public static final int SANITIZE_IF_ENCODED_BRACKET = 512;
    public static final int SANITIZE_IF_ENCODED_SCRIPT = 128;
    public static final int SANITIZE_IF_ENCODED_SCRIPT_OR_BRACKET = 640;
    public static final int SANITIZE_IF_UNENCODED_BRACKET = 1024;
    public static final int SANITIZE_IF_UNENCODED_SCRIPT = 256;
    public static final int SANITIZE_IF_UNENCODED_SCRIPT_OR_BRACKET = 1280;
    public static final int SANITIZE_MASK = 1984;
    private static final Pattern SCRIPT_PATTERN = Pattern.compile("(?i)(?:j(?:[^\\S\\ ]|\\x0)*a(?:[^\\S\\ ]|\\x0)*v(?:[^\\S\\ ]|\\x0)*a(?:[^\\S\\ ]|\\x0)*|v(?:[^\\S\\ ]|\\x0)*b(?:[^\\S\\ ]|\\x0)*)s(?:[^\\S\\ ]|\\x0)*c(?:[^\\S\\ ]|\\x0)*r(?:[^\\S\\ ]|\\x0)*i(?:[^\\S\\ ]|\\x0)*p(?:[^\\S\\ ]|\\x0)*t(?:[^\\S\\ ]|\\x0)*:|<\\s*\\/*s(?:[^\\S\\ ]|\\x0)*c(?:[^\\S\\ ]|\\x0)*r(?:[^\\S\\ ]|\\x0)*i(?:[^\\S\\ ]|\\x0)*p(?:[^\\S\\ ]|\\x0)*t");
    private static final Pattern SCRIPT_PATTERN_ENC = Pattern.compile("(?:[jJ](?:[^\\S\\ ]|\\x0)*[aA](?:[^\\S\\ ]|\\x0)*[vV](?:[^\\S\\ ]|\\x0)*[aA](?:[^\\S\\ ]|\\x0)*|[vV](?:[^\\S\\ ]|\\x0)*[bB](?:[^\\S\\ ]|\\x0)*)[sS](?:[^\\S\\ ]|\\x0)*[cC](?:[^\\S\\ ]|\\x0)*[rR](?:[^\\S\\ ]|\\x0)*[iI](?:[^\\S\\ ]|\\x0)*[pP](?:[^\\S\\ ]|\\x0)*[tT](?:[^\\S\\ ]|\\x0)*(:|cOlOnCoLoNcOlOn)|<\\s*(\\/|sLaShSlAsHsLaSh)*[sS](?:[^\\S\\ ]|\\x0)*[cC](?:[^\\S\\ ]|\\x0)*[rR](?:[^\\S\\ ]|\\x0)*[iI](?:[^\\S\\ ]|\\x0)*[pP](?:[^\\S\\ ]|\\x0)*[tT]");
    private static final Pattern SCRIPT_TAG_PATTERN = Pattern.compile("(?i)<\\s*\\/*s(?:[^\\S\\ ]|\\x0)*c(?:[^\\S\\ ]|\\x0)*r(?:[^\\S\\ ]|\\x0)*i(?:[^\\S\\ ]|\\x0)*p(?:[^\\S\\ ]|\\x0)*t");
    private static final Pattern SCRIPT_TAG_PATTERN_ENC = Pattern.compile("<\\s*(\\/|sLaShSlAsHsLaSh)*[sS](?:[^\\S\\ ]|\\x0)*[cC](?:[^\\S\\ ]|\\x0)*[rR](?:[^\\S\\ ]|\\x0)*[iI](?:[^\\S\\ ]|\\x0)*[pP](?:[^\\S\\ ]|\\x0)*[tT]");
    private static final String SEMICOLON_MARKER = "sEmIcOlOnSeMiCoLon";
    private static final String SLASH_MARKER = "sLaShSlAsHsLaSh";
    private static final String SQUOTE_MARKER = "sInGlEqUoTeSiNgLeQuOtE";
    private static final long TAB_CR_LF_FF_CHAR_BITMASK = 13824;
    public static final String TAG = "UriSanitizer";
    private static final String VBSCRIPT_PREFIX = "vbscript:";
    private static final UrlQuerySanitizer.ValueSanitizer sUrlAndSpaceAndQuotesAndScriptAndBracketsLegal = new UrlQuerySanitizer.IllegalCharacterValueSanitizer(UrlQuerySanitizzr.fixFlags(1533));
    private static final UrlQuerySanitizer.ValueSanitizer sUrlAndSpaceAndQuotesAndScriptLegal = new UrlQuerySanitizer.IllegalCharacterValueSanitizer(UrlQuerySanitizzr.fixFlags(1437));
    private static final UrlQuerySanitizer.ValueSanitizer sUrlAndSpaceAndQuotesLegal = new UrlQuerySanitizer.IllegalCharacterValueSanitizer(UrlQuerySanitizzr.fixFlags(413));
    private static final String ws = "(?:[^\\S\\ ]|\\x0)*";

    public static class Allow {
        public static String AUTHORITY = (SUBDELIMITER + ":@");
        public static String FRAGMENT = (PCHAR + "/?");
        public static String HOST_IPV4 = SUBDELIMITER;
        public static String HOST_IPV6 = (SUBDELIMITER + "[]:");
        public static String OPAQUE = PCHAR;
        public static String PATH = (PCHAR + "/");
        public static String PATH_SEGMENT = PCHAR;
        private static String PCHAR = (SUBDELIMITER + ":@");
        public static String PORT = null;
        public static String QUERY = (PCHAR + "/?");
        public static String QUERY_PARAM = "$+,;:@/?";
        public static String SCHEME = "+";
        private static String SUBDELIMITER = "$&+,;=";
        public static String USER_INFO = (SUBDELIMITER + ":");
    }

    private static int decodeHexDigit(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return (c - 'A') + 10;
        }
        if (c < 'a' || c > 'f') {
            return -1;
        }
        return (c - 'a') + 10;
    }

    static boolean isEmbeddedExploitChar(int i) {
        return (i <= 32 && (TAB_CR_LF_FF_CHAR_BITMASK & (1 << i)) != 0) || i == 0;
    }

    static boolean isExactly(int i, int i2) {
        return (i & i2) == i2;
    }

    static boolean isHtmlSpace(int i) {
        return i <= 32 && (HTML_SPACE_CHAR_BITMASK & (1 << i)) != 0;
    }

    static boolean isHtmlSpaceOrNul(int i) {
        return (i <= 32 && (HTML_SPACE_CHAR_BITMASK & (1 << i)) != 0) || i == 0;
    }

    static boolean isSet(int i, int i2) {
        return (i & i2) != 0;
    }

    private UriSanitizer() {
    }

    public static Uri sanitize(Uri uri) {
        if (uri == null) {
            return null;
        }
        return sanitize(uri, DEFAULT_FLAGS);
    }

    public static Uri sanitize(Uri uri, int i) {
        return sanitize(uri, getUrlAndSpaceLegal(), getUrlAndSpaceLegal(), getUrlAndSpaceLegal(), getUrlAndSpaceLegal(), getUrlAndSpaceLegal(), i);
    }

    public static Uri sanitize(Uri uri, UrlQuerySanitizer.ValueSanitizer valueSanitizer, UrlQuerySanitizer.ValueSanitizer valueSanitizer2, UrlQuerySanitizer.ValueSanitizer valueSanitizer3, UrlQuerySanitizer.ValueSanitizer valueSanitizer4, UrlQuerySanitizer.ValueSanitizer valueSanitizer5, int i) {
        Uri uri2;
        int i2 = i;
        if (uri == null) {
            return null;
        }
        boolean z = (i2 & 64) != 0 || ((i2 & 256) != 0 && containsScript(uri.toString())) || ((i2 & 1024) != 0 && containsBracket(uri.toString()));
        if (!z) {
            int i3 = i2 & 512;
            if ((i3 | 128) != 0) {
                String unescape = unescape(uri.toString());
                z = ((i2 & 128) != 0 && containsScript(unescape)) || (i3 != 0 && containsBracket(unescape));
            }
        }
        boolean isSet = isSet(i2, 32);
        boolean isSet2 = isSet(i2, 4);
        boolean isSet3 = isSet(i2, 4096);
        if (z) {
            uri2 = sanitizeUriScheme(uri, getUrlAndSpaceLegal(), isSet2, isSet, isSet3);
        } else {
            uri2 = removeScriptFromUriScheme(uri, isSet2, isSet, isSet3);
        }
        if ("".equals(uri2.toString())) {
            return uri2;
        }
        String encodedQuery = getEncodedQuery(uri2, valueSanitizer, z, i2);
        if (uri2.isHierarchical()) {
            UriBuilder uriBuilder = new UriBuilder();
            uriBuilder.scheme(uri2.getScheme());
            boolean z2 = isSet2;
            boolean z3 = isSet;
            boolean z4 = z;
            boolean z5 = isSet3;
            uriBuilder.encodedAuthority(getEncodedAuthority(uri2, valueSanitizer2, z2, z3, z4, z5));
            uriBuilder.encodedPath(getEncodedPath(uri2, valueSanitizer3, z2, z3, z4, z5));
            uriBuilder.encodedFragment(getEncodedFragment(uri2, valueSanitizer5, z2, z3, z4, z5));
            if (encodedQuery != null) {
                uriBuilder.encodedQuery(encodedQuery);
            }
            uri2 = parse(uriBuilder);
        } else if (uri2.isOpaque()) {
            String encodedSchemeSpecificPart = uri2.getEncodedSchemeSpecificPart();
            int indexOf = encodedSchemeSpecificPart.indexOf(63);
            if (indexOf >= 0) {
                encodedSchemeSpecificPart = encodedSchemeSpecificPart.substring(0, indexOf);
            }
            Uri.Builder builder = new Uri.Builder();
            builder.scheme(uri2.getScheme());
            builder.encodedOpaquePart(handleNonQueryValue(encodedSchemeSpecificPart, valueSanitizer5, isSet2, isSet, z, isSet3, Allow.OPAQUE));
            UriBuilder uriBuilder2 = new UriBuilder();
            if (encodedQuery != null) {
                uriBuilder2.encodedQuery(encodedQuery);
            }
            uriBuilder2.encodedFragment(getEncodedFragment(uri2, valueSanitizer5, isSet2, isSet, z, isSet3));
            uri2 = Uri.parse(builder.toString() + uriBuilder2.toString());
        }
        return (!isSet(i2, 2048) || z) ? uri2 : Uri.parse(encodeSpaces(uri2.toString()));
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void appendQueryParameter(java.lang.StringBuilder r5, java.lang.String r6, int r7) {
        /*
            r0 = 1
            boolean r1 = isSet(r7, r0)
            r2 = 8
            r3 = 0
            if (r1 == 0) goto L_0x0022
            boolean r1 = isSet(r7, r2)
            if (r1 != 0) goto L_0x0022
            int r1 = r6.length()
            java.lang.String r6 = removeScript(r6)
            int r4 = r6.length()
            if (r4 != 0) goto L_0x0022
            if (r1 == 0) goto L_0x0022
            r1 = 1
            goto L_0x0023
        L_0x0022:
            r1 = 0
        L_0x0023:
            if (r1 != 0) goto L_0x0064
            boolean r2 = isSet(r7, r2)
            if (r2 == 0) goto L_0x0053
            java.lang.String r1 = preserveEncoded((java.lang.String) r6, (int) r7)
            java.lang.String r1 = unescape(r1)
            int r2 = indexOfScriptEnc(r1)
            if (r2 != 0) goto L_0x0040
            int r4 = r1.length()
            if (r4 == 0) goto L_0x0040
            goto L_0x0041
        L_0x0040:
            r0 = 0
        L_0x0041:
            if (r0 != 0) goto L_0x0054
            if (r2 < 0) goto L_0x0054
            java.lang.String r6 = r1.substring(r3, r2)
            r1 = 0
            java.lang.String r6 = android.net.Uri.encode(r6, r1)
            java.lang.String r6 = restoreEncoded((java.lang.String) r6, (int) r7)
            goto L_0x0054
        L_0x0053:
            r0 = r1
        L_0x0054:
            if (r0 != 0) goto L_0x0064
            int r7 = r5.length()
            if (r7 <= 0) goto L_0x0061
            java.lang.String r7 = "&"
            r5.append(r7)
        L_0x0061:
            r5.append(r6)
        L_0x0064:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newandromo.dev849565.app936843.UriSanitizer.appendQueryParameter(java.lang.StringBuilder, java.lang.String, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0073, code lost:
        r0 = unescape(preserveEncoded(r8, r9));
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void appendQueryParameter(java.lang.StringBuilder r6, java.lang.String r7, java.lang.String r8, int r9) {
        /*
            r0 = 1
            boolean r1 = isSet(r9, r0)
            r2 = 8
            r3 = 0
            if (r1 == 0) goto L_0x0022
            boolean r1 = isSet(r9, r2)
            if (r1 != 0) goto L_0x0022
            int r1 = r7.length()
            java.lang.String r7 = removeScript(r7)
            int r4 = r7.length()
            if (r4 != 0) goto L_0x0022
            if (r1 == 0) goto L_0x0022
            r1 = 1
            goto L_0x0023
        L_0x0022:
            r1 = 0
        L_0x0023:
            if (r1 != 0) goto L_0x00b1
            r4 = 16
            if (r8 == 0) goto L_0x003a
            r5 = 2
            boolean r5 = isSet(r9, r5)
            if (r5 == 0) goto L_0x003a
            boolean r5 = isSet(r9, r4)
            if (r5 != 0) goto L_0x003a
            java.lang.String r8 = removeScript(r8)
        L_0x003a:
            boolean r2 = isSet(r9, r2)
            if (r2 == 0) goto L_0x0068
            java.lang.String r1 = preserveEncoded((java.lang.String) r7, (int) r9)
            java.lang.String r1 = unescape(r1)
            int r2 = indexOfScriptEnc(r1)
            if (r2 != 0) goto L_0x0055
            int r5 = r1.length()
            if (r5 == 0) goto L_0x0055
            goto L_0x0056
        L_0x0055:
            r0 = 0
        L_0x0056:
            if (r0 != 0) goto L_0x0069
            if (r2 < 0) goto L_0x0069
            java.lang.String r7 = r1.substring(r3, r2)
            r1 = 0
            java.lang.String r7 = android.net.Uri.encode(r7, r1)
            java.lang.String r7 = restoreEncoded((java.lang.String) r7, (int) r9)
            goto L_0x0069
        L_0x0068:
            r0 = r1
        L_0x0069:
            if (r0 != 0) goto L_0x00b1
            if (r8 == 0) goto L_0x008f
            boolean r0 = isSet(r9, r4)
            if (r0 == 0) goto L_0x008f
            java.lang.String r0 = preserveEncoded((java.lang.String) r8, (int) r9)
            java.lang.String r0 = unescape(r0)
            int r1 = indexOfScriptEnc(r0)
            if (r1 < 0) goto L_0x008f
            java.lang.String r8 = r0.substring(r3, r1)
            java.lang.String r0 = ","
            java.lang.String r8 = android.net.Uri.encode(r8, r0)
            java.lang.String r8 = restoreEncoded((java.lang.String) r8, (int) r9)
        L_0x008f:
            int r9 = r6.length()
            if (r9 <= 0) goto L_0x009a
            java.lang.String r9 = "&"
            r6.append(r9)
        L_0x009a:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r7)
            java.lang.String r7 = "="
            r9.append(r7)
            r9.append(r8)
            java.lang.String r7 = r9.toString()
            r6.append(r7)
        L_0x00b1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newandromo.dev849565.app936843.UriSanitizer.appendQueryParameter(java.lang.StringBuilder, java.lang.String, java.lang.String, int):void");
    }

    private static String removeScriptFromQuery(String str, int i) {
        StringBuilder sb = new StringBuilder(str.length());
        StringTokenizer stringTokenizer = new StringTokenizer(str, "&");
        while (stringTokenizer.hasMoreElements()) {
            String nextToken = stringTokenizer.nextToken();
            if (nextToken.length() > 0) {
                int indexOf = nextToken.indexOf(61);
                if (indexOf < 0) {
                    appendQueryParameter(sb, nextToken, i);
                } else {
                    appendQueryParameter(sb, nextToken.substring(0, indexOf), nextToken.substring(indexOf + 1), i);
                }
            }
        }
        return sb.toString();
    }

    private static String getEncodedPath(Uri uri, UrlQuerySanitizer.ValueSanitizer valueSanitizer, boolean z, boolean z2, boolean z3, boolean z4) {
        return restoreEncodedPluses(handleNonQueryValue(preserveEncodedPluses(uri.getEncodedPath()), valueSanitizer, z, z2, z3, z4, Allow.PATH));
    }

    private static String getEncodedFragment(Uri uri, UrlQuerySanitizer.ValueSanitizer valueSanitizer, boolean z, boolean z2, boolean z3, boolean z4) {
        return restoreEncodedPluses(handleNonQueryValue(preserveEncodedPluses(uri.getEncodedFragment()), valueSanitizer, z, z2, z3, z4, Allow.FRAGMENT));
    }

    private static String handleNonQueryValue(String str, UrlQuerySanitizer.ValueSanitizer valueSanitizer, boolean z, boolean z2, boolean z3, boolean z4, String str2) {
        if (!z3 || valueSanitizer == null) {
            return (z || z2) ? removeScriptFromNonQueryValue(str, z, z2, z4, str2) : str;
        }
        return sanitizeAndEncodeNonQueryValue(valueSanitizer, str, z, z2, z4, str2);
    }

    private static String sanitizeAndEncodeNonQueryValue(UrlQuerySanitizer.ValueSanitizer valueSanitizer, String str, boolean z, boolean z2, boolean z3, String str2) {
        int indexOfScript;
        int indexOfScriptEnc;
        if (valueSanitizer == null || str == null) {
            return null;
        }
        if (z && !z2) {
            str = removeScript(str);
        }
        if (z3) {
            String decode = Uri.decode(preserveEncoded(str));
            if (z2 && (indexOfScriptEnc = indexOfScriptEnc(decode)) >= 0) {
                decode = decode.substring(0, indexOfScriptEnc);
            }
            return restoreEncoded(Uri.encode(valueSanitizer.sanitize(decode), str2));
        }
        String decode2 = Uri.decode(str);
        if (z2 && (indexOfScript = indexOfScript(decode2)) >= 0) {
            decode2 = decode2.substring(0, indexOfScript);
        }
        return Uri.encode(valueSanitizer.sanitize(decode2), str2);
    }

    private static String removeScriptFromNonQueryValue(String str, boolean z, boolean z2, boolean z3, String str2) {
        if (str == null) {
            return str;
        }
        if (z && !z2) {
            return removeScript(str);
        }
        if (!z2) {
            return str;
        }
        if (z3) {
            String decode = Uri.decode(preserveEncoded(str));
            int indexOfScriptEnc = indexOfScriptEnc(decode);
            if (indexOfScriptEnc >= 0) {
                return restoreEncoded(Uri.encode(decode.substring(0, indexOfScriptEnc), str2));
            }
            return str;
        }
        String decode2 = Uri.decode(str);
        int indexOfScript = indexOfScript(decode2);
        return indexOfScript >= 0 ? Uri.encode(decode2.substring(0, indexOfScript), str2) : str;
    }

    private static Uri removeScriptFromUriScheme(Uri uri, boolean z, boolean z2, boolean z3) {
        String scheme;
        int i;
        if (uri == null) {
            return uri;
        }
        if ((!z2 && !z) || (scheme = uri.getScheme()) == null) {
            return uri;
        }
        if (z3 && z2) {
            scheme = preserveEncoded(scheme);
        }
        String decode = z2 ? Uri.decode(scheme) : scheme;
        if (indexOfScriptPrefix(scheme + ":") >= 0) {
            Log.w(TAG, "Script detected; sanitizing URI to empty string.");
            return Uri.parse("");
        }
        if (!z3 || !z2) {
            i = indexOfScriptTagOrEvent(decode);
        } else {
            i = indexOfScriptTagOrEventEnc(decode);
        }
        if (i < 0) {
            return uri;
        }
        String str = TAG;
        Log.w(str, "Script detected in scheme; removing: " + decode.substring(i));
        String substring = decode.substring(0, i);
        if (z2) {
            substring = Uri.encode(substring.substring(0, i), Allow.SCHEME);
        }
        if (z3 && z2) {
            substring = restoreEncoded(substring);
        }
        UriBuilder fromUri = UriBuilder.fromUri(uri);
        fromUri.scheme(substring);
        return parse(fromUri);
    }

    private static Uri sanitizeUriScheme(Uri uri, UrlQuerySanitizer.ValueSanitizer valueSanitizer, boolean z, boolean z2, boolean z3) {
        String scheme;
        int i;
        if (uri == null || (scheme = uri.getScheme()) == null) {
            return uri;
        }
        if (z && !z2) {
            if (indexOfScriptPrefix(scheme + ":") >= 0) {
                Log.w(TAG, "Script detected; sanitizing URI to empty string.");
                return Uri.parse("");
            }
            int indexOfScriptTagOrEvent = indexOfScriptTagOrEvent(scheme);
            if (indexOfScriptTagOrEvent >= 0) {
                String str = TAG;
                Log.w(str, "Script detected in scheme; removing: " + scheme.substring(indexOfScriptTagOrEvent));
                scheme = scheme.substring(0, indexOfScriptTagOrEvent);
            }
        }
        if (z3) {
            scheme = preserveEncoded(scheme);
        }
        String decode = Uri.decode(scheme);
        if (z2) {
            if (indexOfScriptPrefix(decode + ":") >= 0) {
                Log.w(TAG, "Script detected; sanitizing URI to empty string.");
                return Uri.parse("");
            }
            if (z3) {
                i = indexOfScriptTagOrEventEnc(decode);
            } else {
                i = indexOfScriptTagOrEvent(decode);
            }
            if (i >= 0) {
                String str2 = TAG;
                Log.w(str2, "Script detected in scheme; removing: " + decode.substring(i));
                decode = decode.substring(0, i);
            }
        }
        String encode = Uri.encode(valueSanitizer.sanitize(decode), Allow.SCHEME);
        if (z3) {
            encode = restoreEncoded(encode);
        }
        UriBuilder fromUri = UriBuilder.fromUri(uri);
        fromUri.scheme(encode);
        return parse(fromUri);
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

    private static String preserveEncodedPluses(String str) {
        return str != null ? ENCODED_PLUS_PATTERN.matcher(str).replaceAll(PLUS_MARKER) : str;
    }

    private static String restoreEncodedPluses(String str) {
        return str != null ? str.replace(PLUS_MARKER, ENCODED_PLUS_STRING) : str;
    }

    private static String preserveEncoded(String str, boolean z) {
        return z ? preserveEncoded(str) : str;
    }

    private static String restoreEncoded(String str, boolean z) {
        return z ? restoreEncoded(str) : str;
    }

    static String preserveEncoded(String str, int i) {
        return isSet(i, 4096) ? preserveEncoded(str) : str;
    }

    static String restoreEncoded(String str, int i) {
        return isSet(i, 4096) ? restoreEncoded(str) : str;
    }

    static String preserveEncoded(String str) {
        if (str == null) {
            return str;
        }
        return ENCODED_SQUOTE_PATTERN.matcher(ENCODED_DQUOTE_PATTERN.matcher(ENCODED_AT_PATTERN.matcher(ENCODED_COLON_PATTERN.matcher(ENCODED_EQUALS_PATTERN.matcher(ENCODED_SEMICOLON_PATTERN.matcher(ENCODED_COMMA_PATTERN.matcher(ENCODED_DOLLAR_PATTERN.matcher(ENCODED_QUESTION_PATTERN.matcher(ENCODED_SLASH_PATTERN.matcher(ENCODED_AMP_PATTERN.matcher(ENCODED_PLUS_PATTERN.matcher(str).replaceAll(PLUS_MARKER)).replaceAll(AMP_MARKER)).replaceAll(SLASH_MARKER)).replaceAll(QUESTION_MARKER)).replaceAll(DOLLAR_MARKER)).replaceAll(COMMA_MARKER)).replaceAll(SEMICOLON_MARKER)).replaceAll(EQUALS_MARKER)).replaceAll(COLON_MARKER)).replaceAll(AT_MARKER)).replaceAll(DQUOTE_MARKER)).replaceAll(SQUOTE_MARKER);
    }

    static String restoreEncoded(String str) {
        return str != null ? str.replace(PLUS_MARKER, ENCODED_PLUS_STRING).replace(AMP_MARKER, ENCODED_AMP_STRING).replace(SLASH_MARKER, ENCODED_SLASH_STRING).replace(QUESTION_MARKER, ENCODED_QUESTION_STRING).replace(DOLLAR_MARKER, ENCODED_DOLLAR_STRING).replace(COMMA_MARKER, ENCODED_COMMA_STRING).replace(SEMICOLON_MARKER, ENCODED_SEMICOLON_STRING).replace(EQUALS_MARKER, ENCODED_EQUALS_STRING).replace(COLON_MARKER, ENCODED_COLON_STRING).replace(AT_MARKER, ENCODED_AT_STRING).replace(DQUOTE_MARKER, ENCODED_DQUOTE_STRING).replace(SQUOTE_MARKER, ENCODED_SQUOTE_STRING) : str;
    }

    static String encodeSpaces(String str) {
        return str != null ? str.replace(" ", "%20") : str;
    }

    public static String rejectScriptPrefixNoRegex(String str) {
        if (str == null) {
            return null;
        }
        String removeEmbeddedExploitChars = removeEmbeddedExploitChars(trimHtmlSpaces(str));
        if (removeEmbeddedExploitChars.length() >= MIN_SCRIPT_PREFIX_LENGTH) {
            String lowerCase = removeEmbeddedExploitChars.toLowerCase(Locale.ROOT);
            if (lowerCase.startsWith(JAVASCRIPT_PREFIX) || lowerCase.startsWith(VBSCRIPT_PREFIX)) {
                return "";
            }
        }
        return str;
    }

    public static String rejectScriptPrefix(String str) {
        if (str == null) {
            return null;
        }
        return (str.length() < MIN_SCRIPT_PREFIX_LENGTH || !JAVASCRIPT_PREFIX_PATTERN.matcher(str).find()) ? str : "";
    }

    public static String removeScript(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() > "script".length()) {
            Matcher matcher = SCRIPT_PATTERN.matcher(str);
            if (matcher.find()) {
                return str.substring(0, matcher.start());
            }
        }
        if (str.length() > MIN_LENGTH_JS_EVENTS) {
            Matcher matcher2 = JS_EVENTS_PATTERN.matcher(str);
            while (matcher2.find()) {
                String[] strArr = JS_EVENTS;
                int length = strArr.length;
                int i = 0;
                while (true) {
                    if (i < length) {
                        if (strArr[i].equalsIgnoreCase(str.substring(matcher2.start(), matcher2.end() - 1))) {
                            return str.substring(0, matcher2.start());
                        }
                        i++;
                    }
                }
            }
        }
        return str;
    }

    public static String removeScriptEnc(String str) {
        String str2;
        if (str == null) {
            return null;
        }
        if (str.length() > "script".length()) {
            Matcher matcher = SCRIPT_PATTERN_ENC.matcher(str);
            if (matcher.find()) {
                return str.substring(0, matcher.start());
            }
        }
        if (str.length() > MIN_LENGTH_JS_EVENTS) {
            Matcher matcher2 = JS_EVENTS_PATTERN_ENC.matcher(str);
            while (matcher2.find()) {
                if (str.substring(matcher2.start(), matcher2.end()).endsWith(EQUALS_MARKER)) {
                    str2 = str.substring(matcher2.start(), matcher2.end() - EQUALS_MARKER.length());
                } else {
                    str2 = str.substring(matcher2.start(), matcher2.end() - 1);
                }
                String[] strArr = JS_EVENTS;
                int length = strArr.length;
                int i = 0;
                while (true) {
                    if (i < length) {
                        if (strArr[i].equalsIgnoreCase(str2)) {
                            return str.substring(0, matcher2.start());
                        }
                        i++;
                    }
                }
            }
        }
        return str;
    }

    public static String rejectScript(String str) {
        return containsScript(str) ? "" : str;
    }

    static boolean containsHtmlSpace(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (isHtmlSpace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    static String trimHtmlSpaces(String str) {
        int length = str.length();
        while (length > 0 && isHtmlSpace(str.charAt(length - 1))) {
            length--;
        }
        int i = 0;
        while (i < length && isHtmlSpace(str.charAt(i))) {
            i++;
        }
        if (i == 0 && length == str.length()) {
            return str;
        }
        return str.substring(i, length);
    }

    static String trimHtmlSpacesOrNuls(String str) {
        int length = str.length();
        while (length > 0 && isHtmlSpaceOrNul(str.charAt(length - 1))) {
            length--;
        }
        int i = 0;
        while (i < length && isHtmlSpaceOrNul(str.charAt(i))) {
            i++;
        }
        if (i == 0 && length == str.length()) {
            return str;
        }
        return str.substring(i, length);
    }

    static String removeAllHtmlSpaces(String str) {
        String trimHtmlSpaces = trimHtmlSpaces(str);
        int length = trimHtmlSpaces.length();
        StringBuilder sb = null;
        for (int i = 0; i < length; i++) {
            char charAt = trimHtmlSpaces.charAt(i);
            if (isHtmlSpace(charAt)) {
                if (sb == null) {
                    sb = new StringBuilder(length - 1);
                    if (i > 0) {
                        sb.append(trimHtmlSpaces, 0, i);
                    }
                }
            } else if (sb != null) {
                sb.append(charAt);
            }
        }
        return sb != null ? sb.toString() : trimHtmlSpaces;
    }

    static String removeAllHtmlSpacesAndNuls(String str) {
        String trimHtmlSpaces = trimHtmlSpaces(str);
        int length = trimHtmlSpaces.length();
        StringBuilder sb = null;
        for (int i = 0; i < length; i++) {
            char charAt = trimHtmlSpaces.charAt(i);
            if (isHtmlSpaceOrNul(charAt)) {
                if (sb == null) {
                    sb = new StringBuilder(length - 1);
                    if (i > 0) {
                        sb.append(trimHtmlSpaces, 0, i);
                    }
                }
            } else if (sb != null) {
                sb.append(charAt);
            }
        }
        return sb != null ? sb.toString() : trimHtmlSpaces;
    }

    static String removeEmbeddedExploitChars(String str) {
        StringBuilder sb = null;
        if (str != null) {
            int length = str.length();
            StringBuilder sb2 = null;
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (isEmbeddedExploitChar(charAt)) {
                    if (sb2 == null) {
                        sb2 = new StringBuilder(length - 1);
                        if (i > 0) {
                            sb2.append(str, 0, i);
                        }
                    }
                } else if (sb2 != null) {
                    sb2.append(charAt);
                }
            }
            sb = sb2;
        }
        return sb != null ? sb.toString() : str;
    }

    private static boolean containsBracket(String str) {
        return str != null && str.length() > 0 && BRACKET_PATTERN.matcher(str).find();
    }

    private static boolean containsScript(String str) {
        if (str != null) {
            if (str.length() > "script".length() && SCRIPT_PATTERN.matcher(str).find()) {
                return true;
            }
            if (str.length() > MIN_LENGTH_JS_EVENTS) {
                Matcher matcher = JS_EVENTS_PATTERN.matcher(str);
                while (matcher.find()) {
                    String[] strArr = JS_EVENTS;
                    int length = strArr.length;
                    int i = 0;
                    while (true) {
                        if (i < length) {
                            if (strArr[i].equalsIgnoreCase(str.substring(matcher.start(), matcher.end() - 1))) {
                                return true;
                            }
                            i++;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean containsScriptPrefix(String str) {
        return str != null && str.length() >= "vbscript".length() && JAVASCRIPT_PREFIX_PATTERN.matcher(str).find();
    }

    private static boolean containsScriptTagOrEvent(String str) {
        if (str != null) {
            if (str.length() > "script".length() && SCRIPT_TAG_PATTERN.matcher(str).find()) {
                return true;
            }
            if (str.length() > MIN_LENGTH_JS_EVENTS) {
                Matcher matcher = JS_EVENTS_PATTERN.matcher(str);
                while (matcher.find()) {
                    String[] strArr = JS_EVENTS;
                    int length = strArr.length;
                    int i = 0;
                    while (true) {
                        if (i < length) {
                            if (strArr[i].equalsIgnoreCase(str.substring(matcher.start(), matcher.end() - 1))) {
                                return true;
                            }
                            i++;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static int indexOfScript(String str, boolean z) {
        if (z) {
            return indexOfScriptEnc(str);
        }
        return indexOfScript(str);
    }

    private static int indexOfScript(String str) {
        if (str == null) {
            return -1;
        }
        if (str.length() > "script".length()) {
            Matcher matcher = SCRIPT_PATTERN.matcher(str);
            if (matcher.find()) {
                return matcher.start();
            }
        }
        if (str.length() <= MIN_LENGTH_JS_EVENTS) {
            return -1;
        }
        Matcher matcher2 = JS_EVENTS_PATTERN.matcher(str);
        while (matcher2.find()) {
            String[] strArr = JS_EVENTS;
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    if (strArr[i].equalsIgnoreCase(str.substring(matcher2.start(), matcher2.end() - 1))) {
                        return matcher2.start();
                    }
                    i++;
                }
            }
        }
        return -1;
    }

    private static int indexOfScriptEnc(String str) {
        String str2;
        if (str == null) {
            return -1;
        }
        if (str.length() > "script".length()) {
            Matcher matcher = SCRIPT_PATTERN_ENC.matcher(str);
            if (matcher.find()) {
                return matcher.start();
            }
        }
        if (str.length() <= MIN_LENGTH_JS_EVENTS) {
            return -1;
        }
        Matcher matcher2 = JS_EVENTS_PATTERN_ENC.matcher(str);
        while (matcher2.find()) {
            if (str.substring(matcher2.start(), matcher2.end()).endsWith(EQUALS_MARKER)) {
                str2 = str.substring(matcher2.start(), matcher2.end() - EQUALS_MARKER.length());
            } else {
                str2 = str.substring(matcher2.start(), matcher2.end() - 1);
            }
            String[] strArr = JS_EVENTS;
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    if (strArr[i].equalsIgnoreCase(str2)) {
                        return matcher2.start();
                    }
                    i++;
                }
            }
        }
        return -1;
    }

    private static int indexOfScriptPrefix(String str) {
        if (str == null || str.length() <= "vbscript".length()) {
            return -1;
        }
        Matcher matcher = JAVASCRIPT_PREFIX_PATTERN.matcher(str);
        if (matcher.find()) {
            return matcher.start();
        }
        return -1;
    }

    private static int indexOfScriptPrefixEnc(String str) {
        if (str == null || str.length() <= "vbscript".length()) {
            return -1;
        }
        Matcher matcher = JAVASCRIPT_PREFIX_PATTERN_ENC.matcher(str);
        if (matcher.find()) {
            return matcher.start();
        }
        return -1;
    }

    private static int indexOfScriptTagOrEvent(String str) {
        if (str == null) {
            return -1;
        }
        if (str.length() > "script".length()) {
            Matcher matcher = SCRIPT_PATTERN.matcher(str);
            if (matcher.find()) {
                return matcher.start();
            }
        }
        if (str.length() <= MIN_LENGTH_JS_EVENTS) {
            return -1;
        }
        Matcher matcher2 = JS_EVENTS_PATTERN.matcher(str);
        while (matcher2.find()) {
            String[] strArr = JS_EVENTS;
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    if (strArr[i].equalsIgnoreCase(str.substring(matcher2.start(), matcher2.end() - 1))) {
                        return matcher2.start();
                    }
                    i++;
                }
            }
        }
        return -1;
    }

    private static int indexOfScriptTagOrEventEnc(String str) {
        String str2;
        if (str == null) {
            return -1;
        }
        if (str.length() > "script".length()) {
            Matcher matcher = SCRIPT_PATTERN_ENC.matcher(str);
            if (matcher.find()) {
                return matcher.start();
            }
        }
        if (str.length() <= MIN_LENGTH_JS_EVENTS) {
            return -1;
        }
        Matcher matcher2 = JS_EVENTS_PATTERN_ENC.matcher(str);
        while (matcher2.find()) {
            if (str.substring(matcher2.start(), matcher2.end()).endsWith(EQUALS_MARKER)) {
                str2 = str.substring(matcher2.start(), matcher2.end() - EQUALS_MARKER.length());
            } else {
                str2 = str.substring(matcher2.start(), matcher2.end() - 1);
            }
            String[] strArr = JS_EVENTS;
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    if (strArr[i].equalsIgnoreCase(str2)) {
                        return matcher2.start();
                    }
                    i++;
                }
            }
        }
        return -1;
    }

    private static boolean canRebuildAuthority(Uri uri) {
        String encodedAuthority;
        if (uri == null || (encodedAuthority = uri.getEncodedAuthority()) == null) {
            return false;
        }
        return canRebuildAuthority(encodedAuthority, uri.getEncodedUserInfo(), uri.getHost(), uri.getPort());
    }

    private static boolean canRebuildAuthority(String str, String str2, String str3, int i) {
        if (str == null) {
            return false;
        }
        StringBuilder sb = new StringBuilder(str.length());
        if (str2 != null) {
            sb.append(str2 + "@");
        }
        if (str3 != null) {
            sb.append(str3);
        }
        if (i != -1) {
            sb.append(":" + i);
        }
        return str.equals(sb.toString());
    }

    private static String sanitizeAndEncodeNonQueryValue(UrlQuerySanitizer.ValueSanitizer valueSanitizer, String str, String str2) {
        if (valueSanitizer == null || str == null) {
            return null;
        }
        return Uri.encode(valueSanitizer.sanitize(Uri.decode(str)), str2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000e, code lost:
        r2 = r2.getEncodedSchemeSpecificPart();
        r1 = r2.indexOf(63);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getEncodedQuery(android.net.Uri r2) {
        /*
            if (r2 == 0) goto L_0x0021
            java.lang.String r0 = r2.getEncodedQuery()
            if (r0 != 0) goto L_0x0022
            boolean r1 = r2.isOpaque()
            if (r1 == 0) goto L_0x0022
            java.lang.String r2 = r2.getEncodedSchemeSpecificPart()
            r1 = 63
            int r1 = r2.indexOf(r1)
            if (r1 < 0) goto L_0x0022
            int r1 = r1 + 1
            java.lang.String r0 = r2.substring(r1)
            goto L_0x0022
        L_0x0021:
            r0 = 0
        L_0x0022:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newandromo.dev849565.app936843.UriSanitizer.getEncodedQuery(android.net.Uri):java.lang.String");
    }

    private static String getEncodedQuery(Uri uri, UrlQuerySanitizer.ValueSanitizer valueSanitizer, boolean z, int i) {
        String encodedQuery = getEncodedQuery(uri);
        if (encodedQuery == null) {
            return null;
        }
        if (!z || valueSanitizer == null) {
            return isSet(i, 27) ? removeScriptFromQuery(encodedQuery, i) : encodedQuery;
        }
        UrlQuerySanitizerNoVa urlQuerySanitizerNoVa = new UrlQuerySanitizerNoVa(i);
        urlQuerySanitizerNoVa.setAllowUnregisteredParamaters(true);
        urlQuerySanitizerNoVa.setUnregisteredParameterValueSanitizer(valueSanitizer);
        urlQuerySanitizerNoVa.parseQuery(encodedQuery);
        return UrlQuerySanitizerNoVa.getQuery(urlQuerySanitizerNoVa, encodedQuery.length(), i);
    }

    private static String getEncodedAuthority(Uri uri, UrlQuerySanitizer.ValueSanitizer valueSanitizer, boolean z, boolean z2, boolean z3, boolean z4) {
        String encodedAuthority;
        String handleNonQueryValue;
        String handleNonQueryValue2;
        if (valueSanitizer == null || uri == null || (encodedAuthority = uri.getEncodedAuthority()) == null) {
            return null;
        }
        String encodedUserInfo = uri.getEncodedUserInfo();
        String host = uri.getHost();
        int port = uri.getPort();
        if (!canRebuildAuthority(encodedAuthority, encodedUserInfo, host, port)) {
            return encodedAuthority;
        }
        StringBuilder sb = new StringBuilder(encodedAuthority.length());
        if (!(encodedUserInfo == null || (handleNonQueryValue2 = handleNonQueryValue(encodedUserInfo, valueSanitizer, z, z2, z3, z4, Allow.USER_INFO)) == null || handleNonQueryValue2.isEmpty())) {
            sb.append(handleNonQueryValue2 + "@");
        }
        if (!(host == null || (handleNonQueryValue = handleNonQueryValue(host, valueSanitizer, z, z2, z3, z4, Allow.HOST_IPV6)) == null || handleNonQueryValue.isEmpty())) {
            sb.append(handleNonQueryValue);
        }
        if (port != -1) {
            sb.append(":" + port);
        }
        return sb.toString();
    }

    public static String unescape(String str) {
        int i;
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(37);
        if (indexOf < 0) {
            indexOf = str.indexOf(43);
            if (indexOf < 0) {
                return str;
            }
        } else {
            int indexOf2 = str.substring(0, indexOf).indexOf(43);
            if (indexOf2 >= 0 && indexOf2 < indexOf) {
                indexOf = indexOf2;
            }
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        sb.append(str.substring(0, indexOf));
        while (indexOf < length) {
            char charAt = str.charAt(indexOf);
            if (charAt == '+') {
                charAt = ' ';
            } else if (charAt == '%' && (i = indexOf + 2) < length) {
                char charAt2 = str.charAt(indexOf + 1);
                char charAt3 = str.charAt(i);
                if (isHexDigit(charAt2) && isHexDigit(charAt3)) {
                    charAt = (char) ((decodeHexDigit(charAt2) * 16) + decodeHexDigit(charAt3));
                    indexOf = i;
                }
            }
            sb.append(charAt);
            indexOf++;
        }
        return sb.toString();
    }

    private static String unescapePreservingPlus(String str) {
        int i;
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(37);
        if (indexOf < 0) {
            return str;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        sb.append(str.substring(0, indexOf));
        while (indexOf < length) {
            char charAt = str.charAt(indexOf);
            if (charAt == '%' && (i = indexOf + 2) < length) {
                char charAt2 = str.charAt(indexOf + 1);
                char charAt3 = str.charAt(i);
                if (isHexDigit(charAt2) && isHexDigit(charAt3)) {
                    charAt = (char) ((decodeHexDigit(charAt2) * 16) + decodeHexDigit(charAt3));
                    indexOf = i;
                }
            }
            sb.append(charAt);
            indexOf++;
        }
        return sb.toString();
    }

    public static String unescape(String str, char c) {
        int i;
        char c2;
        int indexOf;
        if (str == null) {
            return null;
        }
        if (c == '+') {
            return unescapePreservingPlus(str);
        }
        int indexOf2 = str.indexOf(37);
        if (indexOf2 < 0) {
            if (c != '+') {
                indexOf2 = str.indexOf(43);
            }
            if (indexOf2 < 0) {
                return str;
            }
        } else if (c != '+' && (indexOf = str.substring(0, indexOf2).indexOf(43)) >= 0 && indexOf < indexOf2) {
            indexOf2 = indexOf;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        sb.append(str.substring(0, indexOf2));
        while (indexOf2 < length) {
            char charAt = str.charAt(indexOf2);
            if (charAt == '+') {
                i = indexOf2;
                c2 = c;
            } else {
                if (charAt == '%' && (i = indexOf2 + 2) < length) {
                    char charAt2 = str.charAt(indexOf2 + 1);
                    char charAt3 = str.charAt(i);
                    if (isHexDigit(charAt2) && isHexDigit(charAt3)) {
                        c2 = (char) ((decodeHexDigit(charAt2) * 16) + decodeHexDigit(charAt3));
                    }
                }
                i = indexOf2;
                c2 = charAt;
            }
            sb.append(c2);
            indexOf2 = i + 1;
        }
        return sb.toString();
    }

    private static boolean isHexDigit(char c) {
        return decodeHexDigit(c) >= 0;
    }

    static String normalizeUri(String str) {
        int length = str.length();
        boolean z = false;
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case '#':
                case '/':
                case ':':
                case '?':
                    z = true;
                    break;
                case '(':
                case ')':
                case '{':
                case '}':
                    return normalizeUriFrom(str, i, z);
                case 1417:
                case 1475:
                case 8758:
                case 65306:
                    if (z) {
                        break;
                    } else {
                        return normalizeUriFrom(str, i, false);
                    }
                default:
                    if (charAt > ' ') {
                        break;
                    } else {
                        return normalizeUriFrom(str, i, false);
                    }
            }
        }
        return str;
    }

    private static String normalizeUriFrom(String str, int i, boolean z) {
        String str2;
        int length = str.length();
        StringBuilder sb = new StringBuilder(length + 16);
        int i2 = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            String str3 = null;
            if (charAt == '{') {
                str3 = "%7b";
            } else if (charAt != '}') {
                switch (charAt) {
                    case '(':
                        str3 = "%28";
                        break;
                    case ')':
                        str3 = "%29";
                        break;
                    default:
                        if (charAt < 256 || z) {
                            if (charAt <= ' ') {
                                str3 = CONTROL_REPL[charAt];
                                break;
                            }
                        } else {
                            if (charAt == 1417) {
                                str2 = "%d6%89";
                            } else if (charAt == 1475) {
                                str2 = "%d7%83";
                            } else if (charAt == 8758) {
                                str2 = "%e2%88%b6";
                            } else if (charAt == 65306) {
                                str2 = "%ef%bc%9a";
                            }
                            str3 = str2;
                            break;
                        }
                        break;
                }
            } else {
                str3 = "%7d";
            }
            if (str3 != null) {
                sb.append(str, i2, i);
                sb.append(str3);
                i2 = i + 1;
            }
            i++;
        }
        sb.append(str, i2, length);
        return sb.toString();
    }

    public static final UrlQuerySanitizer.ValueSanitizer getUrlAndSpaceAndQuotesLegal() {
        return sUrlAndSpaceAndQuotesLegal;
    }

    public static final UrlQuerySanitizer.ValueSanitizer getUrlAndSpaceAndQuotesAndScriptLegal() {
        return sUrlAndSpaceAndQuotesAndScriptLegal;
    }

    public static final UrlQuerySanitizer.ValueSanitizer getUrlAndSpaceAndQuotesAndScriptAndBracketsLegal() {
        return sUrlAndSpaceAndQuotesAndScriptLegal;
    }

    public static final UrlQuerySanitizer.ValueSanitizer getAllIllegal() {
        return UrlQuerySanitizzr.getAllIllegal();
    }

    public static final UrlQuerySanitizer.ValueSanitizer getAllButNulLegal() {
        return UrlQuerySanitizzr.getAllButNulLegal();
    }

    public static final UrlQuerySanitizer.ValueSanitizer getAllButWhitespaceLegal() {
        return UrlQuerySanitizzr.getAllButWhitespaceLegal();
    }

    public static final UrlQuerySanitizer.ValueSanitizer getUrlLegal() {
        return UrlQuerySanitizzr.getUrlLegal();
    }

    public static final UrlQuerySanitizer.ValueSanitizer getUrlAndSpaceLegal() {
        return UrlQuerySanitizzr.getUrlAndSpaceLegal();
    }

    public static final UrlQuerySanitizer.ValueSanitizer getAmpLegal() {
        return UrlQuerySanitizzr.getAmpLegal();
    }

    public static final UrlQuerySanitizer.ValueSanitizer getAmpAndSpaceLegal() {
        return UrlQuerySanitizzr.getAmpAndSpaceLegal();
    }

    public static final UrlQuerySanitizer.ValueSanitizer getSpaceLegal() {
        return UrlQuerySanitizzr.getSpaceLegal();
    }

    public static final UrlQuerySanitizer.ValueSanitizer getAllButNulAndAngleBracketsLegal() {
        return UrlQuerySanitizzr.getAllButNulAndAngleBracketsLegal();
    }
}
