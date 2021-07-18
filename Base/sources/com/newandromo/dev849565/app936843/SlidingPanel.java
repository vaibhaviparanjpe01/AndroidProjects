package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SlidingPanel extends LinearLayout {
    private static final String TAG = "SlidingPanel";
    Animation.AnimationListener collapseListener = new Animation.AnimationListener() {
        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            SlidingPanel.this.setVisibility(8);
        }
    };
    private Animation fadeOut = null;
    private boolean isOpen = false;
    private int speed = 300;

    public SlidingPanel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SlidingPanel, 0, 0);
        this.speed = obtainStyledAttributes.getInt(0, 300);
        obtainStyledAttributes.recycle();
        this.fadeOut = AnimationUtils.loadAnimation(context, R.anim.fade);
    }

    public void display(boolean z, boolean z2) {
        if (z != this.isOpen) {
            boolean localVisibleRect = getLocalVisibleRect(new Rect());
            TranslateAnimation translateAnimation = null;
            AnimationSet animationSet = new AnimationSet(true);
            this.isOpen = z;
            if (this.isOpen) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams.addRule(12);
                setLayoutParams(layoutParams);
                setVisibility(0);
                if (z2) {
                    translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) getHeight(), 0.0f);
                }
            } else if (!z2) {
                setVisibility(8);
            } else if (!localVisibleRect) {
                setVisibility(8);
            } else {
                translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) getHeight());
                translateAnimation.setAnimationListener(this.collapseListener);
                animationSet.addAnimation(this.fadeOut);
            }
            if (z2 && translateAnimation != null) {
                animationSet.addAnimation(translateAnimation);
                animationSet.setDuration((long) this.speed);
                animationSet.setInterpolator(new AccelerateInterpolator(1.0f));
                startAnimation(animationSet);
            }
        }
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int mIsOpen;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.mIsOpen = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.mIsOpen);
        }
    }
}
