package com.newandromo.dev849565.app936843;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.databinding.BindingAdapter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.security.InvalidParameterException;

public final class BindingAdapters {
    private static final String TAG = "BindingAdapters";

    @BindingAdapter({"android:layout_width"})
    public static void setLayoutWidth(View view, int i) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = i;
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter({"android:layout_height"})
    public static void setLayoutHeight(View view, int i) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = i;
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter({"android:layout_below"})
    public static void setLayoutBelow(View view, int i, int i2) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        if (i != 0) {
            layoutParams.removeRule(3);
        }
        if (i2 != 0) {
            layoutParams.addRule(3, i2);
        }
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter({"android:layout_above"})
    public static void setLayoutAbove(View view, int i, int i2) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        if (i != 0) {
            layoutParams.removeRule(2);
        }
        if (i2 != 0) {
            layoutParams.addRule(2, i2);
        }
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter({"android:layout_alignBottom"})
    public static void setLayoutAlignBottom(View view, int i, int i2) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        if (i != 0) {
            layoutParams.removeRule(8);
        }
        if (i2 != 0) {
            layoutParams.addRule(8, i2);
        }
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter({"paddingBelowBaseline"})
    public static void setPaddingBelowBaseline(TextView textView, int i) {
        setPaddingBelowBaseline(textView, i, false);
    }

    @BindingAdapter({"paddingBelowBaseline"})
    public static void setPaddingBelowBaseline(TextView textView, float f) {
        double d = (double) f;
        Double.isNaN(d);
        setPaddingBelowBaseline(textView, (int) (d + 0.5d), false);
    }

    @BindingAdapter({"paddingBelowBaseline", "allowFontClipping"})
    public static void setPaddingBelowBaseline(TextView textView, float f, boolean z) {
        double d = (double) f;
        Double.isNaN(d);
        setPaddingBelowBaseline(textView, (int) (d + 0.5d), z);
    }

    @BindingAdapter({"paddingBelowBaseline", "allowFontClipping"})
    public static void setPaddingBelowBaseline(TextView textView, int i, boolean z) {
        textView.setIncludeFontPadding(false);
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i2 = i - fontMetricsInt.descent;
        if (!z && i2 < fontMetricsInt.bottom) {
            i2 = fontMetricsInt.bottom;
        }
        textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), i2);
    }

    @BindingAdapter({"firstLineLeading"})
    public static void setFirstLineLeading(TextView textView, float f) {
        textView.setIncludeFontPadding(false);
        textView.setPadding(textView.getPaddingLeft(), ((int) f) + textView.getPaint().getFontMetricsInt().ascent, textView.getPaddingRight(), textView.getPaddingBottom());
    }

    @BindingAdapter({"windowBackgroundColor"})
    public static void setWindowBackgroundColor(View view, int i) {
        getWindow(view.getContext()).getDecorView().setBackgroundColor(i);
    }

    @BindingAdapter({"windowBackground"})
    public static void setWindowBackgroundColor(View view, Drawable drawable) {
        setBackgroundCompat(getWindow(view.getContext()).getDecorView(), drawable);
    }

    private static Window getWindow(Context context) {
        if (context instanceof Activity) {
            return ((Activity) context).getWindow();
        }
        if (context instanceof ContextWrapper) {
            return getWindow(((ContextWrapper) context).getBaseContext());
        }
        throw new InvalidParameterException("Cannot find activity context");
    }

    private static void setBackgroundCompat(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    @BindingAdapter({"android:textColor"})
    public static void setTextColor(TextView textView, ColorStateList colorStateList) {
        if (textView != null && colorStateList != null) {
            textView.setTextColor(colorStateList);
        }
    }

    @BindingAdapter({"android:tint"})
    public static void setColorTint(ImageView imageView, int i) {
        imageView.setColorFilter(i, PorterDuff.Mode.SRC_IN);
    }

    @BindingAdapter({"backgroundTint"})
    public static void setBackgroundTint(View view, int i) {
        if (i != 0) {
            ViewCompat.setBackgroundTintList(view, ColorStateList.valueOf(i));
        } else {
            ViewCompat.setBackgroundTintList(view, (ColorStateList) null);
        }
    }

    @BindingAdapter({"backgroundShapeColor"})
    public static void setBackgroundShapeColor(View view, int i) {
        setBackgroundShapeColor(view.getBackground(), i);
    }

    public static void setBackgroundShapeColor(Drawable drawable, int i) {
        if (drawable instanceof ShapeDrawable) {
            ((ShapeDrawable) drawable.mutate()).getPaint().setColor(i);
        } else if (drawable instanceof GradientDrawable) {
            ((GradientDrawable) drawable.mutate()).setColor(i);
        } else if (drawable instanceof ColorDrawable) {
            ((ColorDrawable) drawable.mutate()).setColor(i);
        }
    }

    @BindingAdapter({"backgroundStrokeColor", "backgroundStrokeWidth"})
    public static void setBackgroundStrokeColor(View view, int i, int i2) {
        Drawable background = view.getBackground();
        if (background instanceof GradientDrawable) {
            ((GradientDrawable) background.mutate()).setStroke(i2, i);
        }
    }

    @BindingAdapter({"android:background"})
    public static void setBackgroundColor(View view, AutoColor autoColor) {
        if (autoColor == null || autoColor.getBackgroundColor() == 0) {
            setBackgroundCompat(view, (Drawable) null);
        } else {
            setBackgroundCompat(view, new ColorDrawable(autoColor.getBackgroundColor()));
        }
    }

    @BindingAdapter({"layout_constraintDimensionRatio"})
    public static void setConstraintDimensionRatio(View view, String str) {
        if (str != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ConstraintLayout) {
                ConstraintLayout constraintLayout = (ConstraintLayout) parent;
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.setDimensionRatio(view.getId(), str);
                constraintSet.applyTo(constraintLayout);
            }
        }
    }
}
