package android.support.v7.preference;

import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.InflateException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

class PreferenceInflater {
    private static final HashMap<String, Constructor> CONSTRUCTOR_MAP = new HashMap<>();
    private static final Class<?>[] CONSTRUCTOR_SIGNATURE = {Context.class, AttributeSet.class};
    private static final String EXTRA_TAG_NAME = "extra";
    private static final String INTENT_TAG_NAME = "intent";
    private static final String TAG = "PreferenceInflater";
    private final Object[] mConstructorArgs = new Object[2];
    private final Context mContext;
    private String[] mDefaultPackages;
    private PreferenceManager mPreferenceManager;

    public PreferenceInflater(Context context, PreferenceManager preferenceManager) {
        this.mContext = context;
        init(preferenceManager);
    }

    private void init(PreferenceManager preferenceManager) {
        this.mPreferenceManager = preferenceManager;
        setDefaultPackages(new String[]{"android.support.v14.preference.", "android.support.v7.preference."});
    }

    public void setDefaultPackages(String[] strArr) {
        this.mDefaultPackages = strArr;
    }

    public String[] getDefaultPackages() {
        return this.mDefaultPackages;
    }

    public Context getContext() {
        return this.mContext;
    }

    public Preference inflate(int i, @Nullable PreferenceGroup preferenceGroup) {
        XmlResourceParser xml = getContext().getResources().getXml(i);
        try {
            return inflate((XmlPullParser) xml, preferenceGroup);
        } finally {
            xml.close();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001a A[Catch:{ InflateException -> 0x007c, XmlPullParserException -> 0x006e, IOException -> 0x0048 }] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x002d A[SYNTHETIC, Splitter:B:14:0x002d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.support.v7.preference.Preference inflate(org.xmlpull.v1.XmlPullParser r6, @android.support.annotation.Nullable android.support.v7.preference.PreferenceGroup r7) {
        /*
            r5 = this;
            java.lang.Object[] r0 = r5.mConstructorArgs
            monitor-enter(r0)
            android.util.AttributeSet r1 = android.util.Xml.asAttributeSet(r6)     // Catch:{ all -> 0x007e }
            java.lang.Object[] r2 = r5.mConstructorArgs     // Catch:{ all -> 0x007e }
            r3 = 0
            android.content.Context r4 = r5.mContext     // Catch:{ all -> 0x007e }
            r2[r3] = r4     // Catch:{ all -> 0x007e }
        L_0x000e:
            int r2 = r6.next()     // Catch:{ InflateException -> 0x007c, XmlPullParserException -> 0x006e, IOException -> 0x0048 }
            r3 = 2
            if (r2 == r3) goto L_0x0018
            r4 = 1
            if (r2 != r4) goto L_0x000e
        L_0x0018:
            if (r2 != r3) goto L_0x002d
            java.lang.String r2 = r6.getName()     // Catch:{ InflateException -> 0x007c, XmlPullParserException -> 0x006e, IOException -> 0x0048 }
            android.support.v7.preference.Preference r2 = r5.createItemFromTag(r2, r1)     // Catch:{ InflateException -> 0x007c, XmlPullParserException -> 0x006e, IOException -> 0x0048 }
            android.support.v7.preference.PreferenceGroup r2 = (android.support.v7.preference.PreferenceGroup) r2     // Catch:{ InflateException -> 0x007c, XmlPullParserException -> 0x006e, IOException -> 0x0048 }
            android.support.v7.preference.PreferenceGroup r7 = r5.onMergeRoots(r7, r2)     // Catch:{ InflateException -> 0x007c, XmlPullParserException -> 0x006e, IOException -> 0x0048 }
            r5.rInflate(r6, r7, r1)     // Catch:{ InflateException -> 0x007c, XmlPullParserException -> 0x006e, IOException -> 0x0048 }
            monitor-exit(r0)     // Catch:{ all -> 0x007e }
            return r7
        L_0x002d:
            android.view.InflateException r7 = new android.view.InflateException     // Catch:{ InflateException -> 0x007c, XmlPullParserException -> 0x006e, IOException -> 0x0048 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ InflateException -> 0x007c, XmlPullParserException -> 0x006e, IOException -> 0x0048 }
            r1.<init>()     // Catch:{ InflateException -> 0x007c, XmlPullParserException -> 0x006e, IOException -> 0x0048 }
            java.lang.String r2 = r6.getPositionDescription()     // Catch:{ InflateException -> 0x007c, XmlPullParserException -> 0x006e, IOException -> 0x0048 }
            r1.append(r2)     // Catch:{ InflateException -> 0x007c, XmlPullParserException -> 0x006e, IOException -> 0x0048 }
            java.lang.String r2 = ": No start tag found!"
            r1.append(r2)     // Catch:{ InflateException -> 0x007c, XmlPullParserException -> 0x006e, IOException -> 0x0048 }
            java.lang.String r1 = r1.toString()     // Catch:{ InflateException -> 0x007c, XmlPullParserException -> 0x006e, IOException -> 0x0048 }
            r7.<init>(r1)     // Catch:{ InflateException -> 0x007c, XmlPullParserException -> 0x006e, IOException -> 0x0048 }
            throw r7     // Catch:{ InflateException -> 0x007c, XmlPullParserException -> 0x006e, IOException -> 0x0048 }
        L_0x0048:
            r7 = move-exception
            android.view.InflateException r1 = new android.view.InflateException     // Catch:{ all -> 0x007e }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x007e }
            r2.<init>()     // Catch:{ all -> 0x007e }
            java.lang.String r6 = r6.getPositionDescription()     // Catch:{ all -> 0x007e }
            r2.append(r6)     // Catch:{ all -> 0x007e }
            java.lang.String r6 = ": "
            r2.append(r6)     // Catch:{ all -> 0x007e }
            java.lang.String r6 = r7.getMessage()     // Catch:{ all -> 0x007e }
            r2.append(r6)     // Catch:{ all -> 0x007e }
            java.lang.String r6 = r2.toString()     // Catch:{ all -> 0x007e }
            r1.<init>(r6)     // Catch:{ all -> 0x007e }
            r1.initCause(r7)     // Catch:{ all -> 0x007e }
            throw r1     // Catch:{ all -> 0x007e }
        L_0x006e:
            r6 = move-exception
            android.view.InflateException r7 = new android.view.InflateException     // Catch:{ all -> 0x007e }
            java.lang.String r1 = r6.getMessage()     // Catch:{ all -> 0x007e }
            r7.<init>(r1)     // Catch:{ all -> 0x007e }
            r7.initCause(r6)     // Catch:{ all -> 0x007e }
            throw r7     // Catch:{ all -> 0x007e }
        L_0x007c:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x007e }
        L_0x007e:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x007e }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.preference.PreferenceInflater.inflate(org.xmlpull.v1.XmlPullParser, android.support.v7.preference.PreferenceGroup):android.support.v7.preference.Preference");
    }

    @NonNull
    private PreferenceGroup onMergeRoots(PreferenceGroup preferenceGroup, @NonNull PreferenceGroup preferenceGroup2) {
        if (preferenceGroup != null) {
            return preferenceGroup;
        }
        preferenceGroup2.onAttachedToHierarchy(this.mPreferenceManager);
        return preferenceGroup2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0034, code lost:
        r5 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0035, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006e, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007d, code lost:
        r0 = new android.view.InflateException(r11.getPositionDescription() + ": Error inflating class " + r9);
        r0.initCause(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x009d, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006e A[Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }, ExcHandler: Exception (r10v4 'e' java.lang.Exception A[CUSTOM_DECLARE, Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }]), Splitter:B:2:0x000b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.support.v7.preference.Preference createItem(@android.support.annotation.NonNull java.lang.String r9, @android.support.annotation.Nullable java.lang.String[] r10, android.util.AttributeSet r11) throws java.lang.ClassNotFoundException, android.view.InflateException {
        /*
            r8 = this;
            java.util.HashMap<java.lang.String, java.lang.reflect.Constructor> r0 = CONSTRUCTOR_MAP
            java.lang.Object r0 = r0.get(r9)
            java.lang.reflect.Constructor r0 = (java.lang.reflect.Constructor) r0
            r1 = 1
            if (r0 != 0) goto L_0x0072
            android.content.Context r0 = r8.mContext     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
            java.lang.ClassLoader r0 = r0.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
            if (r10 == 0) goto L_0x005b
            int r2 = r10.length     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
            if (r2 != 0) goto L_0x0017
            goto L_0x005b
        L_0x0017:
            int r2 = r10.length     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
            r3 = 0
            r4 = 0
            r5 = r4
        L_0x001b:
            if (r3 >= r2) goto L_0x0038
            r6 = r10[r3]     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x0034, Exception -> 0x006e }
            r7.<init>()     // Catch:{ ClassNotFoundException -> 0x0034, Exception -> 0x006e }
            r7.append(r6)     // Catch:{ ClassNotFoundException -> 0x0034, Exception -> 0x006e }
            r7.append(r9)     // Catch:{ ClassNotFoundException -> 0x0034, Exception -> 0x006e }
            java.lang.String r6 = r7.toString()     // Catch:{ ClassNotFoundException -> 0x0034, Exception -> 0x006e }
            java.lang.Class r6 = r0.loadClass(r6)     // Catch:{ ClassNotFoundException -> 0x0034, Exception -> 0x006e }
            r4 = r6
            goto L_0x0038
        L_0x0034:
            r5 = move-exception
            int r3 = r3 + 1
            goto L_0x001b
        L_0x0038:
            if (r4 != 0) goto L_0x005f
            if (r5 != 0) goto L_0x005a
            android.view.InflateException r10 = new android.view.InflateException     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
            r0.<init>()     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
            java.lang.String r1 = r11.getPositionDescription()     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
            r0.append(r1)     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
            java.lang.String r1 = ": Error inflating class "
            r0.append(r1)     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
            r0.append(r9)     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
            java.lang.String r0 = r0.toString()     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
            r10.<init>(r0)     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
            throw r10     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
        L_0x005a:
            throw r5     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
        L_0x005b:
            java.lang.Class r4 = r0.loadClass(r9)     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
        L_0x005f:
            java.lang.Class<?>[] r10 = CONSTRUCTOR_SIGNATURE     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
            java.lang.reflect.Constructor r0 = r4.getConstructor(r10)     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
            r0.setAccessible(r1)     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
            java.util.HashMap<java.lang.String, java.lang.reflect.Constructor> r10 = CONSTRUCTOR_MAP     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
            r10.put(r9, r0)     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
            goto L_0x0072
        L_0x006e:
            r10 = move-exception
            goto L_0x007d
        L_0x0070:
            r9 = move-exception
            goto L_0x009e
        L_0x0072:
            java.lang.Object[] r10 = r8.mConstructorArgs     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
            r10[r1] = r11     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
            java.lang.Object r10 = r0.newInstance(r10)     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
            android.support.v7.preference.Preference r10 = (android.support.v7.preference.Preference) r10     // Catch:{ ClassNotFoundException -> 0x0070, Exception -> 0x006e }
            return r10
        L_0x007d:
            android.view.InflateException r0 = new android.view.InflateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r11 = r11.getPositionDescription()
            r1.append(r11)
            java.lang.String r11 = ": Error inflating class "
            r1.append(r11)
            r1.append(r9)
            java.lang.String r9 = r1.toString()
            r0.<init>(r9)
            r0.initCause(r10)
            throw r0
        L_0x009e:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.preference.PreferenceInflater.createItem(java.lang.String, java.lang.String[], android.util.AttributeSet):android.support.v7.preference.Preference");
    }

    /* access modifiers changed from: protected */
    public Preference onCreateItem(String str, AttributeSet attributeSet) throws ClassNotFoundException {
        return createItem(str, this.mDefaultPackages, attributeSet);
    }

    private Preference createItemFromTag(String str, AttributeSet attributeSet) {
        try {
            if (-1 == str.indexOf(46)) {
                return onCreateItem(str, attributeSet);
            }
            return createItem(str, (String[]) null, attributeSet);
        } catch (InflateException e) {
            throw e;
        } catch (ClassNotFoundException e2) {
            InflateException inflateException = new InflateException(attributeSet.getPositionDescription() + ": Error inflating class (not found)" + str);
            inflateException.initCause(e2);
            throw inflateException;
        } catch (Exception e3) {
            InflateException inflateException2 = new InflateException(attributeSet.getPositionDescription() + ": Error inflating class " + str);
            inflateException2.initCause(e3);
            throw inflateException2;
        }
    }

    private void rInflate(XmlPullParser xmlPullParser, Preference preference, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if ((next == 3 && xmlPullParser.getDepth() <= depth) || next == 1) {
                return;
            }
            if (next == 2) {
                String name = xmlPullParser.getName();
                if (INTENT_TAG_NAME.equals(name)) {
                    try {
                        preference.setIntent(Intent.parseIntent(getContext().getResources(), xmlPullParser, attributeSet));
                    } catch (IOException e) {
                        XmlPullParserException xmlPullParserException = new XmlPullParserException("Error parsing preference");
                        xmlPullParserException.initCause(e);
                        throw xmlPullParserException;
                    }
                } else if (EXTRA_TAG_NAME.equals(name)) {
                    getContext().getResources().parseBundleExtra(EXTRA_TAG_NAME, attributeSet, preference.getExtras());
                    try {
                        skipCurrentTag(xmlPullParser);
                    } catch (IOException e2) {
                        XmlPullParserException xmlPullParserException2 = new XmlPullParserException("Error parsing preference");
                        xmlPullParserException2.initCause(e2);
                        throw xmlPullParserException2;
                    }
                } else {
                    Preference createItemFromTag = createItemFromTag(name, attributeSet);
                    ((PreferenceGroup) preference).addItemFromInflater(createItemFromTag);
                    rInflate(xmlPullParser, createItemFromTag, attributeSet);
                }
            }
        }
    }

    private static void skipCurrentTag(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return;
            }
            if (next == 3 && xmlPullParser.getDepth() <= depth) {
                return;
            }
        }
    }
}
