package com.newandromo.dev849565.app936843;

import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

public final class MenuColorizer {
    private static final String TAG = "MenuColorizer";

    public static void tintAllMenuItems(Menu menu, int i) {
        if (menu != null) {
            int size = menu.size();
            for (int i2 = 0; i2 < size; i2++) {
                MenuItem item = menu.getItem(i2);
                tintMenuItem(item, i);
                if (item.hasSubMenu()) {
                    SubMenu subMenu = item.getSubMenu();
                    int size2 = subMenu.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        tintMenuItem(subMenu.getItem(i3), i);
                    }
                }
            }
        }
    }

    public static void tintAllMenuItems(Menu menu, int i, int i2) {
        if (menu != null) {
            int size = menu.size();
            for (int i3 = 0; i3 < size; i3++) {
                MenuItem item = menu.getItem(i3);
                tintMenuItem(item, i, i2);
                if (item.hasSubMenu()) {
                    SubMenu subMenu = item.getSubMenu();
                    int size2 = subMenu.size();
                    for (int i4 = 0; i4 < size2; i4++) {
                        tintMenuItem(subMenu.getItem(i4), i, i2);
                    }
                }
            }
        }
    }

    public static void tintMenuItem(Menu menu, int i, int i2) {
        if (menu != null) {
            tintMenuItem(menu.findItem(i), i2);
        }
    }

    public static void tintMenuItem(Menu menu, int i, int i2, int i3) {
        if (menu != null) {
            tintMenuItem(menu.findItem(i), i2, i3);
        }
    }

    public static void tintMenuItem(MenuItem menuItem, int i) {
        Drawable icon;
        if (menuItem != null && (icon = menuItem.getIcon()) != null) {
            Drawable wrap = DrawableCompat.wrap(icon);
            DrawableCompat.setTint(wrap.mutate(), i);
            menuItem.setIcon(wrap);
        }
    }

    public static void tintMenuItem(MenuItem menuItem, int i, int i2) {
        Drawable icon;
        if (menuItem != null && (icon = menuItem.getIcon()) != null) {
            Drawable wrap = DrawableCompat.wrap(icon);
            DrawableCompat.setTint(wrap.mutate(), i);
            wrap.setAlpha(i2);
            menuItem.setIcon(wrap);
        }
    }
}
