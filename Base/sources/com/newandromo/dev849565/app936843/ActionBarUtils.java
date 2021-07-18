package com.newandromo.dev849565.app936843;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

final class ActionBarUtils {
    private static final String TAG = "ActionBarUtils";
    private static final boolean bHomeAsUp = true;
    private static final int mActionBarTextDisplayMode = 2;

    private static void deprecated(String str) {
    }

    static void setListNavigationMode(Activity activity, ActionBar actionBar, int i, int i2, int i3) {
    }

    static void setSelectedNavigationItem(ActionBar actionBar, int i) {
    }

    ActionBarUtils() {
    }

    static String getActivityClassNameFromTitle(Context context, String str, int i, int i2) {
        String str2 = "";
        String[] stringArray = context.getResources().getStringArray(i);
        String[] stringArray2 = context.getResources().getStringArray(i2);
        String str3 = context.getPackageName() + ".";
        int length = stringArray.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (str.equals(stringArray[i3])) {
                str2 = str3 + stringArray2[i3];
            }
        }
        return str2;
    }

    static String searchForActivityClass(Context context, String str) {
        for (int stringArray : new int[]{R.array.activity_000_classes}) {
            for (String str2 : context.getResources().getStringArray(stringArray)) {
                if (str2.equalsIgnoreCase(str)) {
                    return context.getPackageName() + "." + str2;
                }
            }
        }
        return "";
    }

    static Intent createLauncherActivityIntent(Context context) {
        Intent intent = new Intent();
        String packageName = context.getPackageName();
        context.getClass().getName();
        String string = context.getResources().getString(R.string.activity_launcher_name);
        if (string.equals("")) {
            return intent;
        }
        try {
            Class<?> cls = Class.forName(packageName + "." + string);
            if (Activity.class.isAssignableFrom(cls)) {
                return new Intent(context, cls);
            }
            return new Intent();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return new Intent();
        }
    }

    static int getActivityIndexFromClassNamesArray(Context context, int i) {
        String[] stringArray = context.getResources().getStringArray(i);
        String name = context.getClass().getName();
        String str = context.getPackageName() + ".";
        int length = stringArray.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (name.equals(str + stringArray[i2])) {
                return i2;
            }
        }
        return -1;
    }

    static int getActivityIndexFromClassNamesArray(Context context, String[] strArr) {
        String name = context.getClass().getName();
        String str = context.getPackageName() + ".";
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (name.equals(str + strArr[i])) {
                return i;
            }
        }
        return -1;
    }

    static int getIndexFromFullyQualifiedClassName(Context context, String str) {
        for (int stringArray : new int[]{R.array.activity_000_classes}) {
            String[] stringArray2 = context.getResources().getStringArray(stringArray);
            String str2 = context.getPackageName() + ".";
            int length = stringArray2.length;
            for (int i = 0; i < length; i++) {
                if (str.equals(str2 + stringArray2[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    static String getActivityTitleFromIndex(Context context, int i, int i2) {
        String[] stringArray = context.getResources().getStringArray(i2);
        if (stringArray == null || i < 0 || i >= stringArray.length) {
            return "";
        }
        return stringArray[i];
    }

    static Intent createHomeIntent(Context context) {
        String packageName = context.getPackageName();
        String name = context.getClass().getName();
        String string = context.getResources().getString(R.string.activity_launcher_name);
        Intent intent = null;
        if (!string.equals("")) {
            try {
                Class<?> cls = Class.forName(packageName + "." + string);
                if (Activity.class.isAssignableFrom(cls) && !name.equals(cls.getName())) {
                    Intent intent2 = new Intent(context, cls);
                    try {
                        intent2.addFlags(67108864);
                        return intent2;
                    } catch (ClassNotFoundException e) {
                        e = e;
                        intent = intent2;
                        e.printStackTrace();
                        return intent;
                    }
                }
            } catch (ClassNotFoundException e2) {
                e = e2;
                e.printStackTrace();
                return intent;
            }
        }
        return intent;
    }

    static void inflateDefaultMenuItems(MenuInflater menuInflater, Menu menu) {
        deprecated("inflateDefaultMenuItems");
        AndromoUtils.inflateDefaultMenuItems(menuInflater, menu);
    }

    static void inflateDefaultMenuItems(MenuInflater menuInflater, Menu menu, boolean z) {
        deprecated("inflateDefaultMenuItems");
        AndromoUtils.inflateDefaultMenuItems(menuInflater, menu, z);
    }

    static boolean dashboardClassExists() {
        deprecated("dashboardClassExists");
        return AndromoUtils.dashboardClassExists();
    }

    static String[] getClassNamesInCategory(Context context, String str) {
        deprecated("getClassNamesInCategory");
        return AndromoUtils.getClassNamesInCategory(context, str);
    }

    static String[] getClassNamesInCategory(Context context, Class<?> cls) {
        deprecated("getClassNamesInCategory");
        return AndromoUtils.getClassNamesInCategory(context, cls);
    }

    static Class<?> getFirstActivityClassInCategory(Context context, String str, boolean z) {
        deprecated("getFirstActivityClassInCategory");
        return AndromoUtils.getFirstActivityClassInCategory(context, str, z);
    }

    static Class<?> getFirstActivityClassInCategory(Context context, Class<?> cls, boolean z) {
        deprecated("getFirstActivityClassInCategory");
        return AndromoUtils.getFirstActivityClassInCategory(context, cls, z);
    }

    static Class<?> getFirstActivityClassInCategory(String[] strArr, boolean z) {
        deprecated("getFirstActivityClassInCategory");
        return AndromoUtils.getFirstActivityClassInCategory(strArr, z);
    }

    static Class<?> getUpClass(Activity activity) {
        deprecated("getUpClass");
        return AndromoUtils.getUpClass(activity);
    }

    static boolean isDashboardNone(String str) {
        deprecated("isDashboardNone");
        return AndromoUtils.isDashboardNone(str);
    }

    static Intent getUpIntentForContentActivity(Context context, Class<?> cls) {
        deprecated("getUpIntentForContentActivity");
        return AndromoUtils.getUpIntentForContentActivity(context, cls);
    }

    static boolean handleDefaultMenuItems(AppCompatActivity appCompatActivity, MenuItem menuItem) {
        deprecated("handleDefaultMenuItems");
        return AndromoUtils.handleDefaultMenuItems(appCompatActivity, menuItem);
    }

    static void setMenuItemVisible(Menu menu, int i, boolean z) {
        MenuItem findItem = menu.findItem(i);
        if (findItem != null) {
            findItem.setVisible(z);
        }
    }

    static void setDisplayOptions(ActionBar actionBar) {
        setDisplayOptions(actionBar, false, bHomeAsUp);
    }

    static void setDisplayOptions(ActionBar actionBar, boolean z) {
        setDisplayOptions(actionBar, z, bHomeAsUp);
    }

    static void setDisplayOptions(ActionBar actionBar, boolean z, boolean z2) {
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(bHomeAsUp);
            actionBar.setHomeButtonEnabled(bHomeAsUp);
            actionBar.setDisplayShowCustomEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(bHomeAsUp);
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(z2);
            switch (2) {
                case 0:
                    actionBar.setDisplayShowTitleEnabled(false);
                    return;
                case 1:
                    actionBar.setDisplayShowTitleEnabled(bHomeAsUp);
                    actionBar.setTitle((int) R.string.app_name);
                    return;
                case 2:
                    actionBar.setDisplayShowTitleEnabled(z);
                    return;
                default:
                    actionBar.setDisplayShowTitleEnabled(z);
                    return;
            }
        }
    }

    static void setTitle(ActionBar actionBar, String str) {
        if (actionBar != null) {
            switch (2) {
                case 0:
                    return;
                case 1:
                    actionBar.setTitle((int) R.string.app_name);
                    return;
                case 2:
                    actionBar.setTitle((CharSequence) str);
                    return;
                default:
                    actionBar.setTitle((CharSequence) str);
                    return;
            }
        }
    }

    static void setTitle(ActionBar actionBar, int i) {
        if (actionBar != null) {
            switch (2) {
                case 0:
                    return;
                case 1:
                    actionBar.setTitle((int) R.string.app_name);
                    return;
                case 2:
                    actionBar.setTitle(i);
                    return;
                default:
                    actionBar.setTitle(i);
                    return;
            }
        }
    }
}
