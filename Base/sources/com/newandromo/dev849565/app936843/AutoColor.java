package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.view.ViewCompat;

public class AutoColor {
    private static final String TAG = "AutoColor";
    private int backgroundColor;
    private int bodyTextColor;
    private boolean isCustom;
    private int primaryTextColor;
    private int secondaryTextColor;
    private int titleTextColor;

    public AutoColor(int i) {
        this(i, 0, 0, 0, 0);
    }

    public AutoColor(int i, int i2, int i3, int i4, int i5) {
        this.backgroundColor = i;
        this.titleTextColor = i2;
        this.bodyTextColor = i3;
        this.primaryTextColor = i4;
        this.secondaryTextColor = i5;
        this.isCustom = ((i2 + i3) + i4) + i5 != 0;
    }

    public void setBackgroundColor(int i) {
        if (i != this.backgroundColor) {
            this.backgroundColor = i;
            this.secondaryTextColor = 0;
            this.primaryTextColor = 0;
            this.bodyTextColor = 0;
            this.titleTextColor = 0;
        }
    }

    public void setColors(int i, int i2, int i3, int i4, int i5) {
        this.backgroundColor = i;
        this.titleTextColor = i2;
        this.bodyTextColor = i3;
        this.primaryTextColor = i4;
        this.secondaryTextColor = i5;
        this.isCustom = ((i2 + i3) + i4) + i5 != 0;
    }

    public void setColors(int i, int i2, int i3) {
        boolean z = false;
        if (this.backgroundColor != i) {
            this.primaryTextColor = 0;
            this.secondaryTextColor = 0;
        }
        this.backgroundColor = i;
        this.titleTextColor = i2;
        this.bodyTextColor = i3;
        if (i2 + i3 + this.primaryTextColor + this.secondaryTextColor != 0) {
            z = true;
        }
        this.isCustom = z;
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public int getTitleTextColor() {
        generateBodyAndTitleIfNeeded();
        return this.titleTextColor;
    }

    public int getBodyTextColor() {
        generateBodyAndTitleIfNeeded();
        return this.bodyTextColor;
    }

    public int getPrimaryTextColor() {
        generatePrimaryAndSecondaryIfNeeded();
        return this.primaryTextColor;
    }

    public int getSecondaryTextColor() {
        generatePrimaryAndSecondaryIfNeeded();
        return this.secondaryTextColor;
    }

    private void generatePrimaryAndSecondaryIfNeeded() {
        if (this.backgroundColor != 0 && this.primaryTextColor == 0 && this.secondaryTextColor == 0) {
            boolean isDark = ColorUtils.isDark(this.backgroundColor);
            this.primaryTextColor = ColorUtils.getPrimaryTextColor(isDark);
            this.secondaryTextColor = ColorUtils.getSecondaryTextColor(isDark);
        }
    }

    private void generateBodyAndTitleIfNeeded() {
        int i;
        int i2;
        if (this.backgroundColor != 0 && this.bodyTextColor == 0 && this.titleTextColor == 0) {
            int alpha = ColorUtils.setAlpha(this.backgroundColor, 255);
            int calculateMinimumAlpha = ColorUtils.calculateMinimumAlpha(-1, alpha, 4.5f);
            int calculateMinimumAlpha2 = ColorUtils.calculateMinimumAlpha(-1, alpha, 3.0f);
            if (calculateMinimumAlpha == -1 || calculateMinimumAlpha2 == -1) {
                int calculateMinimumAlpha3 = ColorUtils.calculateMinimumAlpha(ViewCompat.MEASURED_STATE_MASK, alpha, 4.5f);
                int calculateMinimumAlpha4 = ColorUtils.calculateMinimumAlpha(ViewCompat.MEASURED_STATE_MASK, alpha, 3.0f);
                if (calculateMinimumAlpha3 == -1 || calculateMinimumAlpha3 == -1) {
                    if (calculateMinimumAlpha != -1) {
                        i = ColorUtils.setAlpha(-1, calculateMinimumAlpha);
                    } else {
                        i = ColorUtils.setAlpha((int) ViewCompat.MEASURED_STATE_MASK, calculateMinimumAlpha3);
                    }
                    this.bodyTextColor = i;
                    if (calculateMinimumAlpha2 != -1) {
                        i2 = ColorUtils.setAlpha(-1, calculateMinimumAlpha2);
                    } else {
                        i2 = ColorUtils.setAlpha((int) ViewCompat.MEASURED_STATE_MASK, calculateMinimumAlpha4);
                    }
                    this.titleTextColor = i2;
                    return;
                }
                this.bodyTextColor = ColorUtils.setAlpha((int) ViewCompat.MEASURED_STATE_MASK, calculateMinimumAlpha3);
                this.titleTextColor = ColorUtils.setAlpha((int) ViewCompat.MEASURED_STATE_MASK, calculateMinimumAlpha4);
                return;
            }
            this.bodyTextColor = ColorUtils.setAlpha(-1, calculateMinimumAlpha);
            this.titleTextColor = ColorUtils.setAlpha(-1, calculateMinimumAlpha2);
        }
    }

    public boolean canEqual(Object obj) {
        return obj instanceof AutoColor;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AutoColor)) {
            return false;
        }
        AutoColor autoColor = (AutoColor) obj;
        boolean z = autoColor.canEqual(this) && EqualityUtils.areEqual((long) this.backgroundColor, (long) autoColor.backgroundColor);
        if (!z) {
            return z;
        }
        if (!this.isCustom && !autoColor.isCustom) {
            return z;
        }
        boolean z2 = (this.bodyTextColor == 0 && this.titleTextColor == 0 && autoColor.bodyTextColor == 0 && autoColor.titleTextColor == 0) ? false : true;
        if ((this.primaryTextColor == 0 && this.secondaryTextColor == 0 && autoColor.primaryTextColor == 0 && autoColor.secondaryTextColor == 0) ? false : true) {
            generatePrimaryAndSecondaryIfNeeded();
            autoColor.generatePrimaryAndSecondaryIfNeeded();
            z = z && EqualityUtils.areEqual((long) this.primaryTextColor, (long) autoColor.primaryTextColor) && EqualityUtils.areEqual((long) this.secondaryTextColor, (long) autoColor.secondaryTextColor);
        }
        if (!z2) {
            return z;
        }
        generateBodyAndTitleIfNeeded();
        autoColor.generateBodyAndTitleIfNeeded();
        return z && EqualityUtils.areEqual((long) this.titleTextColor, (long) autoColor.titleTextColor) && EqualityUtils.areEqual((long) this.bodyTextColor, (long) autoColor.bodyTextColor);
    }

    public int hashCode() {
        return HashCodeUtils.hash(23, this.backgroundColor);
    }

    public String toString() {
        return getClass().getName() + "[ backgroundColor: '" + ColorUtils.toHexStringArgb(this.backgroundColor) + "', titleTextColor: '" + ColorUtils.toHexStringArgb(this.titleTextColor) + "', bodyTextColor: '" + ColorUtils.toHexStringArgb(this.bodyTextColor) + "', primaryTextColor: '" + ColorUtils.toHexStringArgb(this.primaryTextColor) + "', secondaryTextColor: '" + ColorUtils.toHexStringArgb(this.secondaryTextColor) + "', isCustom: " + this.isCustom + "]";
    }

    public static int background(AutoColor autoColor) {
        if (autoColor != null) {
            return autoColor.getBackgroundColor();
        }
        return 0;
    }

    public static int bodyText(AutoColor autoColor) {
        if (autoColor != null) {
            return autoColor.getBodyTextColor();
        }
        return 0;
    }

    public static int titleText(AutoColor autoColor) {
        if (autoColor != null) {
            return autoColor.getTitleTextColor();
        }
        return 0;
    }

    public static int primaryText(AutoColor autoColor) {
        if (autoColor != null) {
            return autoColor.getPrimaryTextColor();
        }
        return 0;
    }

    public static int secondaryText(AutoColor autoColor) {
        if (autoColor != null) {
            return autoColor.getSecondaryTextColor();
        }
        return 0;
    }

    public static boolean isDark(AutoColor autoColor) {
        if (autoColor != null) {
            return ColorUtils.isDark(autoColor.getBackgroundColor());
        }
        return false;
    }

    public static int foreground(AutoColor autoColor, int i) {
        if (autoColor == null) {
            return 0;
        }
        if (isDark(autoColor)) {
            return ColorUtils.setAlpha(-1, i);
        }
        return ColorUtils.setAlpha((int) ViewCompat.MEASURED_STATE_MASK, i);
    }

    public static int foreground(AutoColor autoColor, float f) {
        if (autoColor == null) {
            return 0;
        }
        if (isDark(autoColor)) {
            return ColorUtils.setAlpha(-1, f);
        }
        return ColorUtils.setAlpha((int) ViewCompat.MEASURED_STATE_MASK, f);
    }

    public static float defaultDisabledAlpha(AutoColor autoColor) {
        return (autoColor == null || !ColorUtils.isDark(autoColor.backgroundColor)) ? 0.26f : 0.3f;
    }

    public static int bodyTextDisabled(Context context, AutoColor autoColor) {
        return ThemeUtils.getDisabledColor(context, bodyText(autoColor), defaultDisabledAlpha(autoColor));
    }

    public static int titleTextDisabled(Context context, AutoColor autoColor) {
        return ThemeUtils.getDisabledColor(context, titleText(autoColor), defaultDisabledAlpha(autoColor));
    }

    public static int primaryTextDisabled(Context context, AutoColor autoColor) {
        return ThemeUtils.getDisabledColor(context, primaryText(autoColor), defaultDisabledAlpha(autoColor));
    }

    public static int secondaryTextDisabled(Context context, AutoColor autoColor) {
        return ThemeUtils.getDisabledColor(context, secondaryText(autoColor), defaultDisabledAlpha(autoColor));
    }

    public static AutoColor from(int i) {
        return new AutoColor(i);
    }
}
