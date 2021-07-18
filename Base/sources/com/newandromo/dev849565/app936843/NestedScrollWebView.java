package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

public class NestedScrollWebView extends WebView implements NestedScrollingChild {
    public static final String TAG = "NestedScrollWebView";
    private NestedScrollingChildHelper mChildHelper;
    private int mLastMotionY;
    private int mNestedYOffset;
    private final int[] mScrollConsumed = new int[2];
    private final int[] mScrollOffset = new int[2];

    public NestedScrollWebView(Context context) {
        super(context);
        init();
    }

    public NestedScrollWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public NestedScrollWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.mChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked == 0) {
            this.mNestedYOffset = 0;
        }
        int y = (int) motionEvent.getY();
        motionEvent.offsetLocation(0.0f, (float) this.mNestedYOffset);
        switch (actionMasked) {
            case 0:
                this.mLastMotionY = y;
                startNestedScroll(2);
                return super.onTouchEvent(motionEvent);
            case 1:
            case 3:
            case 5:
            case 6:
                stopNestedScroll();
                return super.onTouchEvent(motionEvent);
            case 2:
                int i = this.mLastMotionY - y;
                if (dispatchNestedPreScroll(0, i, this.mScrollConsumed, this.mScrollOffset)) {
                    i -= this.mScrollConsumed[1];
                    obtain.offsetLocation(0.0f, (float) this.mScrollOffset[1]);
                    this.mNestedYOffset += this.mScrollOffset[1];
                }
                int scrollY = getScrollY();
                this.mLastMotionY = y - this.mScrollOffset[1];
                int max = Math.max(0, scrollY + i);
                int i2 = i - (max - scrollY);
                if (dispatchNestedScroll(0, max - i2, 0, i2, this.mScrollOffset)) {
                    this.mLastMotionY -= this.mScrollOffset[1];
                    obtain.offsetLocation(0.0f, (float) this.mScrollOffset[1]);
                    this.mNestedYOffset += this.mScrollOffset[1];
                }
                obtain.recycle();
                return super.onTouchEvent(obtain);
            default:
                return false;
        }
    }

    public void setNestedScrollingEnabled(boolean z) {
        this.mChildHelper.setNestedScrollingEnabled(z);
    }

    public boolean isNestedScrollingEnabled() {
        return this.mChildHelper.isNestedScrollingEnabled();
    }

    public boolean startNestedScroll(int i) {
        return this.mChildHelper.startNestedScroll(i);
    }

    public void stopNestedScroll() {
        this.mChildHelper.stopNestedScroll();
    }

    public boolean hasNestedScrollingParent() {
        return this.mChildHelper.hasNestedScrollingParent();
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.mChildHelper.dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.mChildHelper.dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.mChildHelper.dispatchNestedFling(f, f2, z);
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.mChildHelper.dispatchNestedPreFling(f, f2);
    }
}
