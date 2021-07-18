package com.newandromo.dev849565.app936843;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import java.util.ArrayList;

public final class ToolbarColorizer {
    private static final String TAG = "ToolbarColorizer";

    public static MenuItem getMenuItem(Toolbar toolbar, int i) {
        Menu menu;
        if (toolbar == null || (menu = toolbar.getMenu()) == null) {
            return null;
        }
        return menu.findItem(i);
    }

    public static void tintMenuItem(final Toolbar toolbar, final int i, final int i2) {
        if (toolbar != null) {
            toolbar.post(new Runnable() {
                public void run() {
                    MenuColorizer.tintMenuItem(toolbar.getMenu(), i, i2);
                }
            });
        }
    }

    public static void tintMenuItem(final Toolbar toolbar, final int i, final int i2, final int i3) {
        if (toolbar != null) {
            toolbar.post(new Runnable() {
                public void run() {
                    MenuColorizer.tintMenuItem(toolbar.getMenu(), i, i2, i3);
                }
            });
        }
    }

    public static void tintAllMenuItems(final Toolbar toolbar, final int i) {
        if (toolbar != null) {
            toolbar.post(new Runnable() {
                public void run() {
                    MenuColorizer.tintAllMenuItems(toolbar.getMenu(), i);
                }
            });
        }
    }

    public static void tintAllMenuItems(final Toolbar toolbar, final int i, final int i2) {
        if (toolbar != null) {
            toolbar.post(new Runnable() {
                public void run() {
                    MenuColorizer.tintAllMenuItems(toolbar.getMenu(), i, i2);
                }
            });
        }
    }

    public static void setNavigationIconColor(Toolbar toolbar, int i) {
        Drawable navigationIcon = toolbar.getNavigationIcon();
        if (navigationIcon != null) {
            Drawable wrap = DrawableCompat.wrap(navigationIcon);
            DrawableCompat.setTint(wrap.mutate(), i);
            toolbar.setNavigationIcon(wrap);
        }
    }

    public static void setOverflowButtonColor(Toolbar toolbar, int i) {
        Drawable overflowIcon = toolbar.getOverflowIcon();
        if (overflowIcon != null) {
            Drawable wrap = DrawableCompat.wrap(overflowIcon);
            DrawableCompat.setTint(wrap.mutate(), i);
            toolbar.setOverflowIcon(wrap);
        }
    }

    public static void setOverflowButtonColor(AppCompatActivity appCompatActivity, int i) {
        setOverflowButtonColor(appCompatActivity, new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_IN));
    }

    public static void setOverflowButtonColor(AppCompatActivity appCompatActivity, final PorterDuffColorFilter porterDuffColorFilter) {
        final String string = appCompatActivity.getString(R.string.abc_action_menu_overflow_description);
        final ViewGroup viewGroup = (ViewGroup) appCompatActivity.getWindow().getDecorView();
        viewGroup.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                ArrayList arrayList = new ArrayList();
                viewGroup.findViewsWithText(arrayList, string, 2);
                if (!arrayList.isEmpty()) {
                    View view = (View) arrayList.get(0);
                    if (view instanceof ImageView) {
                        ((ImageView) view).setColorFilter(porterDuffColorFilter);
                    } else if (view instanceof ActionMenuItemView) {
                        ((ActionMenuItemView) view).getCompoundDrawables()[0].setColorFilter(porterDuffColorFilter);
                    }
                    ToolbarColorizer.removeOnGlobalLayoutListener(viewGroup, this);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static void removeOnGlobalLayoutListener(View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (Build.VERSION.SDK_INT < 16) {
            view.getViewTreeObserver().removeGlobalOnLayoutListener(onGlobalLayoutListener);
        } else {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }
}
