package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class AspectRatioImageView extends ImageView {
    private static final String TAG = "AspectRatioImageView";
    private final boolean mFitHeight;
    private final boolean mFitWidth;
    private final double mMaxHeightFactor;
    private final double mMaxWidthFactor;
    private final int mTargetId;

    public AspectRatioImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    public AspectRatioImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AspectRatioImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        boolean z = false;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AspectRatioImageView, i, 0);
        this.mFitWidth = (obtainStyledAttributes.getInt(0, 0) & 1) == 1;
        this.mFitHeight = (obtainStyledAttributes.getInt(0, 0) & 2) == 2 ? true : z;
        this.mTargetId = obtainStyledAttributes.getResourceId(2, -1);
        this.mMaxWidthFactor = (double) obtainStyledAttributes.getFloat(3, 1.0f);
        this.mMaxHeightFactor = (double) obtainStyledAttributes.getFloat(1, 1.0f);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        double d;
        double d2;
        View.MeasureSpec.getMode(i);
        View.MeasureSpec.getMode(i2);
        View.MeasureSpec.getSize(i);
        View.MeasureSpec.getSize(i2);
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        double size = (double) (View.MeasureSpec.getSize(i) - paddingLeft);
        double size2 = (double) (View.MeasureSpec.getSize(i2) - paddingTop);
        if (this.mTargetId != -1 && (this.mMaxWidthFactor != 1.0d || this.mMaxHeightFactor != 1.0d)) {
            View view = (View) getParent();
            while (true) {
                if (view == null) {
                    break;
                } else if (view.getId() == this.mTargetId) {
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    if (layoutParams != null) {
                        if (layoutParams.width > 0) {
                            double d3 = (double) layoutParams.width;
                            double d4 = this.mMaxWidthFactor;
                            Double.isNaN(d3);
                            double d5 = d3 * d4;
                            double d6 = (double) paddingLeft;
                            Double.isNaN(d6);
                            d = d5 - d6;
                        } else {
                            double measuredWidth = (double) view.getMeasuredWidth();
                            double d7 = this.mMaxWidthFactor;
                            Double.isNaN(measuredWidth);
                            double d8 = measuredWidth * d7;
                            double d9 = (double) paddingLeft;
                            Double.isNaN(d9);
                            d = d8 - d9;
                        }
                        if (layoutParams.height > 0) {
                            double d10 = (double) layoutParams.height;
                            double d11 = this.mMaxHeightFactor;
                            Double.isNaN(d10);
                            double d12 = (double) paddingTop;
                            Double.isNaN(d12);
                            d2 = (d10 * d11) - d12;
                        } else {
                            double measuredHeight = (double) view.getMeasuredHeight();
                            double d13 = this.mMaxHeightFactor;
                            Double.isNaN(measuredHeight);
                            double d14 = measuredHeight * d13;
                            double d15 = (double) paddingTop;
                            Double.isNaN(d15);
                            d2 = d14 - d15;
                        }
                        if (d > 0.0d && size > d) {
                            size = d;
                        }
                        if (d2 > 0.0d && size2 > d2) {
                            size2 = d2;
                        }
                    }
                } else {
                    view = (View) view.getParent();
                }
            }
        }
        Drawable drawable = getDrawable();
        if (drawable == null) {
            int i3 = i;
            int i4 = i2;
            super.onMeasure(i, i2);
        } else if (this.mFitWidth && this.mFitHeight) {
            double intrinsicWidth = (double) drawable.getIntrinsicWidth();
            double intrinsicHeight = (double) drawable.getIntrinsicHeight();
            if (intrinsicWidth <= 0.0d || intrinsicHeight <= 0.0d) {
                super.onMeasure(i, i2);
                return;
            }
            Double.isNaN(intrinsicWidth);
            double abs = Math.abs(size / intrinsicWidth);
            Double.isNaN(intrinsicHeight);
            double min = Math.min(abs, Math.abs(size2 / intrinsicHeight));
            Double.isNaN(intrinsicWidth);
            Double.isNaN(intrinsicHeight);
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(((int) ((intrinsicWidth * min) + 0.5d)) + paddingLeft, 1073741824), View.MeasureSpec.makeMeasureSpec(((int) ((intrinsicHeight * min) + 0.5d)) + paddingTop, 1073741824));
        } else if (this.mFitWidth) {
            double intrinsicWidth2 = (double) drawable.getIntrinsicWidth();
            if (intrinsicWidth2 > 0.0d) {
                double intrinsicHeight2 = (double) drawable.getIntrinsicHeight();
                Double.isNaN(intrinsicHeight2);
                Double.isNaN(intrinsicWidth2);
                super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(((int) (((size * intrinsicHeight2) / intrinsicWidth2) + 0.5d)) + paddingTop, 1073741824));
                return;
            }
            int i5 = i;
            super.onMeasure(i, i2);
        } else {
            int i6 = i;
            if (this.mFitHeight) {
                double intrinsicHeight3 = (double) drawable.getIntrinsicHeight();
                if (intrinsicHeight3 > 0.0d) {
                    double intrinsicWidth3 = (double) drawable.getIntrinsicWidth();
                    Double.isNaN(intrinsicWidth3);
                    Double.isNaN(intrinsicHeight3);
                    super.onMeasure(View.MeasureSpec.makeMeasureSpec(((int) (((size2 * intrinsicWidth3) / intrinsicHeight3) + 0.5d)) + paddingLeft, 1073741824), i2);
                    return;
                }
                int i7 = i2;
                super.onMeasure(i, i2);
                return;
            }
            int i8 = i2;
            super.onMeasure(i, i2);
        }
    }
}
