package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

public class TintableImageView extends ImageView {
    private static final String TAG = "TintableImageView";
    private PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
    private ColorStateList tint;

    public TintableImageView(Context context) {
        super(context);
    }

    public TintableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0);
    }

    public TintableImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        if (context != null) {
            try {
                TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.TintableImageView, i, 0);
                int resourceId = obtainStyledAttributes.getResourceId(0, 0);
                if (resourceId != 0) {
                    this.tint = AppCompatResources.getColorStateList(context, resourceId);
                } else {
                    this.tint = obtainStyledAttributes.getColorStateList(0);
                }
                obtainStyledAttributes.recycle();
                updateTintColor();
            } catch (Resources.NotFoundException unused) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.tint != null && this.tint.isStateful()) {
            updateTintColor();
        }
    }

    public void setColorFilter(ColorStateList colorStateList, PorterDuff.Mode mode2) {
        this.tint = colorStateList;
        this.mode = mode2;
        if (colorStateList != null) {
            super.setColorFilter(colorStateList.getColorForState(getDrawableState(), 0), mode2);
            invalidate();
            return;
        }
        super.clearColorFilter();
    }

    private void updateTintColor() {
        if (this.tint != null) {
            setColorFilter(this.tint.getColorForState(getDrawableState(), 0), this.mode);
        }
    }

    @BindingAdapter({"tint"})
    public static void setColorTint(TintableImageView tintableImageView, ColorStateList colorStateList) {
        tintableImageView.setColorFilter(colorStateList, PorterDuff.Mode.SRC_IN);
    }

    @BindingAdapter({"tint"})
    public static void setColorTint(TintableImageView tintableImageView, int i) {
        tintableImageView.setColorFilter(ColorStateList.valueOf(i), PorterDuff.Mode.SRC_IN);
    }

    @BindingAdapter({"android:src", "tint"})
    public static void setImageResourceAndTint(TintableImageView tintableImageView, int i, ColorStateList colorStateList) {
        if (tintableImageView != null) {
            if (i != 0) {
                tintableImageView.setImageResource(i);
            } else {
                tintableImageView.setImageDrawable((Drawable) null);
            }
            tintableImageView.setColorFilter(colorStateList, PorterDuff.Mode.SRC_IN);
            return;
        }
        throw new IllegalArgumentException("target tintableImageView is null");
    }

    @BindingAdapter({"android:src", "android:tint"})
    public static void setImageResourceAndroidTint(TintableImageView tintableImageView, int i, ColorStateList colorStateList) {
        setImageResourceAndTint(tintableImageView, i, colorStateList);
    }
}
