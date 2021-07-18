package com.newandromo.dev849565.app936843;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public final class AndromoUtils {
    private static final String TAG = "AndromoUtils";
    private static boolean bDashboardClassChecked;
    private static boolean bDashboardClassExists;

    static boolean handleDrawerSettingsItem(AppCompatActivity appCompatActivity) {
        return false;
    }

    static boolean isPreferencesEnabled() {
        return false;
    }

    public static String getActivityClassName(Context context, int i, int i2) {
        return context.getResources().getStringArray(i)[i2];
    }

    public static boolean navigateUpFrom(AppCompatActivity appCompatActivity) {
        Intent parentActivityIntent = NavUtils.getParentActivityIntent(appCompatActivity);
        if (parentActivityIntent == null) {
            return false;
        }
        if (isDashboardNone(parentActivityIntent.getComponent().getClassName())) {
            Class<?> upClass = getUpClass(appCompatActivity);
            if (upClass == null) {
                return false;
            }
            parentActivityIntent = new Intent("android.intent.action.MAIN").setClassName(appCompatActivity, upClass.getName());
        }
        parentActivityIntent.putExtra("HomeAsUp", true);
        appCompatActivity.supportFinishAfterTransition();
        if (NavUtils.shouldUpRecreateTask(appCompatActivity, parentActivityIntent)) {
            TaskStackBuilder.create(appCompatActivity).addNextIntentWithParentStack(parentActivityIntent).startActivities();
        } else if (!"com.newandromo.dev849565.app936843.Dashboard_000".equals(parentActivityIntent.getComponent().getClassName()) || dashboardClassExists()) {
            parentActivityIntent.addFlags(603979776);
            startActivityWithInterstitial(appCompatActivity, parentActivityIntent);
        }
        return true;
    }

    public static void navigateTo(Context context, String str, String str2) {
        navigateTo(context, str, str2, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:16:? A[ExcHandler: ClassNotFoundException | IllegalAccessException | InstantiationException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:1:0x0018] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void navigateTo(android.content.Context r7, java.lang.String r8, java.lang.String r9, boolean r10) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r7.getPackageName()
            r0.append(r1)
            java.lang.String r1 = "."
            r0.append(r1)
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            java.lang.Class r8 = java.lang.Class.forName(r8)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0062 }
            java.lang.Class<android.app.Activity> r0 = android.app.Activity.class
            boolean r0 = r0.isAssignableFrom(r8)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0062 }
            r1 = 1
            if (r0 == 0) goto L_0x003a
            android.content.Intent r9 = new android.content.Intent     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0062 }
            r9.<init>(r7, r8)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0062 }
            r8 = 67108864(0x4000000, float:1.5046328E-36)
            r9.addFlags(r8)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0062 }
            if (r10 == 0) goto L_0x0036
            java.lang.String r8 = "CLOSE_NAV_DRAWER"
            r9.putExtra(r8, r1)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0062 }
        L_0x0036:
            r7.startActivity(r9)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0062 }
            goto L_0x0062
        L_0x003a:
            java.lang.Object r10 = r8.newInstance()     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0062 }
            java.lang.reflect.Method[] r8 = r8.getDeclaredMethods()     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0062 }
            int r0 = r8.length     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0062 }
            r2 = 0
            r3 = 0
        L_0x0045:
            if (r3 >= r0) goto L_0x0062
            r4 = r8[r3]     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0062 }
            java.lang.String r5 = r4.getName()     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0062 }
            java.lang.String r6 = "runIntent"
            boolean r5 = r5.equals(r6)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0062 }
            if (r5 == 0) goto L_0x005f
            java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch:{ InvocationTargetException -> 0x005f, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0062, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0062 }
            r5[r2] = r7     // Catch:{ InvocationTargetException -> 0x005f, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0062, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0062 }
            r4.invoke(r10, r5)     // Catch:{ InvocationTargetException -> 0x005f, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0062, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0062 }
            com.newandromo.dev849565.app936843.AnalyticsUtils.trackUserView(r9)     // Catch:{ InvocationTargetException -> 0x005f, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0062, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0062 }
        L_0x005f:
            int r3 = r3 + 1
            goto L_0x0045
        L_0x0062:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newandromo.dev849565.app936843.AndromoUtils.navigateTo(android.content.Context, java.lang.String, java.lang.String, boolean):void");
    }

    public static void navigateWithInterstitial(Activity activity, String str, String str2, Pair<View, String>... pairArr) {
        navigateWithInterstitial(activity, str, str2, false, pairArr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[ExcHandler: ClassNotFoundException | IllegalAccessException | InstantiationException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:1:0x0018] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void navigateWithInterstitial(android.app.Activity r6, java.lang.String r7, java.lang.String r8, boolean r9, android.support.v4.util.Pair<android.view.View, java.lang.String>... r10) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r6.getPackageName()
            r0.append(r1)
            java.lang.String r1 = "."
            r0.append(r1)
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            java.lang.Class r7 = java.lang.Class.forName(r7)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085 }
            java.lang.Class<android.app.Activity> r0 = android.app.Activity.class
            boolean r0 = r0.isAssignableFrom(r7)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085 }
            r1 = 1
            if (r0 == 0) goto L_0x005d
            android.content.Intent r8 = new android.content.Intent     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085 }
            r8.<init>(r6, r7)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085 }
            r7 = 67108864(0x4000000, float:1.5046328E-36)
            r8.addFlags(r7)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085 }
            if (r9 == 0) goto L_0x0036
            java.lang.String r7 = "CLOSE_NAV_DRAWER"
            r8.putExtra(r7, r1)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085 }
        L_0x0036:
            boolean r7 = com.newandromo.dev849565.app936843.AdManager.areInterstitialAdsEnabled()     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085 }
            if (r7 == 0) goto L_0x0045
            com.newandromo.dev849565.app936843.ActivityLauncher r7 = new com.newandromo.dev849565.app936843.ActivityLauncher     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085 }
            r7.<init>(r6, r8)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085 }
            com.newandromo.dev849565.app936843.AdManager.showInterstitialAndRun(r6, r7)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085 }
            goto L_0x0085
        L_0x0045:
            if (r10 == 0) goto L_0x0059
            int r7 = android.os.Build.VERSION.SDK_INT     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085 }
            r9 = 21
            if (r7 < r9) goto L_0x0059
            android.support.v4.app.ActivityOptionsCompat r7 = android.support.v4.app.ActivityOptionsCompat.makeSceneTransitionAnimation(r6, r10)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085 }
            android.os.Bundle r7 = r7.toBundle()     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085 }
            r6.startActivity(r8, r7)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085 }
            goto L_0x0085
        L_0x0059:
            r6.startActivity(r8)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085 }
            goto L_0x0085
        L_0x005d:
            java.lang.Object r9 = r7.newInstance()     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085 }
            java.lang.reflect.Method[] r7 = r7.getDeclaredMethods()     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085 }
            int r10 = r7.length     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085 }
            r0 = 0
            r2 = 0
        L_0x0068:
            if (r2 >= r10) goto L_0x0085
            r3 = r7[r2]     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085 }
            java.lang.String r4 = r3.getName()     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085 }
            java.lang.String r5 = "runIntent"
            boolean r4 = r4.equals(r5)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085 }
            if (r4 == 0) goto L_0x0082
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ InvocationTargetException -> 0x0082, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085 }
            r4[r0] = r6     // Catch:{ InvocationTargetException -> 0x0082, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085 }
            r3.invoke(r9, r4)     // Catch:{ InvocationTargetException -> 0x0082, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085 }
            com.newandromo.dev849565.app936843.AnalyticsUtils.trackUserView(r8)     // Catch:{ InvocationTargetException -> 0x0082, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085, ClassNotFoundException | IllegalAccessException | InstantiationException -> 0x0085 }
        L_0x0082:
            int r2 = r2 + 1
            goto L_0x0068
        L_0x0085:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newandromo.dev849565.app936843.AndromoUtils.navigateWithInterstitial(android.app.Activity, java.lang.String, java.lang.String, boolean, android.support.v4.util.Pair[]):void");
    }

    public static void startActivityWithInterstitial(Activity activity, Intent intent) {
        if (AdManager.areInterstitialAdsEnabled()) {
            AdManager.showInterstitialAndRun(activity, new ActivityLauncher(activity, intent));
        } else {
            activity.startActivity(intent);
        }
    }

    static boolean isParentReachable(Activity activity) {
        return getUpClass(activity) != null;
    }

    static boolean isParentReachable(Activity activity, String str) {
        return !"none".equals(str) || isParentReachable(activity);
    }

    static Class<?> getUpClass(Activity activity) {
        String parentActivityName = NavUtils.getParentActivityName(activity);
        while (parentActivityName != null) {
            try {
                Class<?> cls = Class.forName(parentActivityName);
                if (!NoneDashboard.class.isAssignableFrom(cls)) {
                    return cls;
                }
                Class<?> firstActivityClassInCategory = getFirstActivityClassInCategory((Context) activity, parentActivityName, true);
                if (firstActivityClassInCategory != null && !firstActivityClassInCategory.equals(activity.getClass())) {
                    return firstActivityClassInCategory;
                }
                try {
                    parentActivityName = NavUtils.getParentActivityName(activity, new ComponentName(activity.getPackageName(), parentActivityName));
                } catch (PackageManager.NameNotFoundException unused) {
                    return null;
                }
            } catch (ClassNotFoundException unused2) {
                return null;
            }
        }
        return null;
    }

    static boolean dashboardClassExists() {
        if (!bDashboardClassChecked) {
            try {
                Class.forName("com.newandromo.dev849565.app936843.Dashboard_000");
                bDashboardClassExists = true;
            } catch (ClassNotFoundException unused) {
            }
            bDashboardClassChecked = true;
        }
        return bDashboardClassExists;
    }

    static boolean isDashboardNone(String str) {
        try {
            return NoneDashboard.class.isAssignableFrom(Class.forName(str));
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    static String[] getClassNamesInCategory(Context context, String str) {
        if (str == null) {
            return null;
        }
        try {
            Class<?> cls = Class.forName(str);
            if (Dashboard.class.isAssignableFrom(cls)) {
                return ((Dashboard) cls.newInstance()).getClassNamesArray(context);
            }
            return null;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
            return null;
        }
    }

    static String[] getClassNamesInCategory(Context context, Class<?> cls) {
        try {
            return ((Dashboard) cls.newInstance()).getClassNamesArray(context);
        } catch (IllegalAccessException | InstantiationException unused) {
            return null;
        }
    }

    static Class<?> getFirstActivityClassInCategory(Context context, String str, boolean z) {
        return getFirstActivityClassInCategory(getClassNamesInCategory(context, str), z);
    }

    static Class<?> getFirstActivityClassInCategory(Context context, Class<?> cls, boolean z) {
        return getFirstActivityClassInCategory(getClassNamesInCategory(context, cls), z);
    }

    static Class<?> getFirstActivityClassInCategory(String[] strArr, boolean z) {
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            String str = strArr[i];
            try {
                Class<?> cls = Class.forName("com.newandromo.dev849565.app936843." + str);
                if (z && NoneDashboard.class.isAssignableFrom(cls)) {
                    i++;
                } else if (Activity.class.isAssignableFrom(cls)) {
                    return cls;
                } else {
                    i++;
                }
            } catch (ClassNotFoundException unused) {
            }
        }
        return null;
    }

    static Intent getUpIntentForContentActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        intent.addFlags(335544320);
        intent.putExtra("HomeAsUp", true);
        return intent;
    }

    static void inflateDefaultMenuItems(MenuInflater menuInflater, Menu menu) {
        inflateDefaultMenuItems(menuInflater, menu, false);
    }

    static void inflateDefaultMenuItems(MenuInflater menuInflater, Menu menu, boolean z) {
        if (z) {
            menuInflater.inflate(R.menu.andromo_preferences_options_menu, menu);
        }
        menuInflater.inflate(R.menu.default_options_menu, menu);
        if (IntercomHelper.showInMenu()) {
            menuInflater.inflate(R.menu.intercom_menu, menu);
        }
    }

    static boolean handleDefaultMenuItems(AppCompatActivity appCompatActivity, MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            return navigateUpFrom(appCompatActivity);
        }
        if (itemId == R.id.about) {
            return showAboutActivity(appCompatActivity);
        }
        if (itemId != R.id.intercom) {
            return false;
        }
        IntercomHelper.launch();
        return false;
    }

    static boolean showAboutActivity(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(appCompatActivity, AndromoAboutActivity.class);
        intent.addFlags(67108864);
        appCompatActivity.startActivity(intent);
        return true;
    }

    static String getVersionNameFromPackage(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 128).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }
}
