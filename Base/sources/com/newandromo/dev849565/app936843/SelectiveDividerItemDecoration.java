package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class SelectiveDividerItemDecoration extends RecyclerView.ItemDecoration {
    public static final int HORIZONTAL_LIST = 0;
    private static final String TAG = "SelectiveDividerItemDecoration";
    public static final int VERTICAL_LIST = 1;
    private boolean mDecorateByDefault;
    private Drawable mDivider;
    private int mExtraPaddingAtEnds;
    private int mExtraPaddingBeforeDividers;
    private boolean mHasHeader = true;
    private int mOrientation;

    public interface Decoratable {
        boolean isDecorationEnabled();
    }

    public SelectiveDividerItemDecoration(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{16843284});
        this.mDivider = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        setOrientation(i);
        int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R.dimen.material_padding);
        this.mExtraPaddingBeforeDividers = dimensionPixelOffset;
        this.mExtraPaddingAtEnds = dimensionPixelOffset;
    }

    public SelectiveDividerItemDecoration(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{16843284});
        this.mDivider = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        setOrientation(i);
        int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R.dimen.material_padding);
        this.mExtraPaddingBeforeDividers = dimensionPixelOffset;
        this.mExtraPaddingAtEnds = dimensionPixelOffset;
    }

    public void setDecorateByDefault(boolean z) {
        this.mDecorateByDefault = z;
    }

    public void setExtraPaddingAtEnds(int i) {
        this.mExtraPaddingAtEnds = i;
    }

    public void setExtraPaddingBeforeDividers(int i) {
        this.mExtraPaddingBeforeDividers = i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x005e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void getItemOffsets(android.graphics.Rect r5, android.view.View r6, android.support.v7.widget.RecyclerView r7, android.support.v7.widget.RecyclerView.State r8) {
        /*
            r4 = this;
            int r8 = r7.getChildLayoutPosition(r6)
            android.support.v7.widget.RecyclerView$Adapter r0 = r7.getAdapter()
            r0.getItemCount()
            boolean r0 = r4.mHasHeader
            r1 = 1
            r2 = 0
            if (r8 != r0) goto L_0x0015
            int r0 = r4.mExtraPaddingAtEnds
        L_0x0013:
            r3 = 0
            goto L_0x0027
        L_0x0015:
            android.support.v7.widget.RecyclerView$Adapter r0 = r7.getAdapter()
            int r0 = r0.getItemCount()
            int r0 = r0 - r1
            if (r8 != r0) goto L_0x0025
            int r0 = r4.mExtraPaddingAtEnds
            r3 = r0
            r0 = 0
            goto L_0x0027
        L_0x0025:
            r0 = 0
            goto L_0x0013
        L_0x0027:
            if (r8 < r1) goto L_0x005e
            boolean r6 = r4.isDecorationEnabled(r6, r7)
            if (r6 == 0) goto L_0x005e
            int r6 = r4.mExtraPaddingBeforeDividers
            int r0 = r0 + r6
            int r6 = r4.mOrientation
            if (r6 != r1) goto L_0x004a
            android.graphics.drawable.Drawable r6 = r4.mDivider
            int r6 = r6.getIntrinsicHeight()
            r5.set(r2, r6, r2, r2)
            android.graphics.drawable.Drawable r6 = r4.mDivider
            int r6 = r6.getIntrinsicHeight()
            int r6 = r6 + r0
            r5.set(r2, r6, r2, r3)
            goto L_0x0069
        L_0x004a:
            android.graphics.drawable.Drawable r6 = r4.mDivider
            int r6 = r6.getIntrinsicWidth()
            r5.set(r6, r2, r2, r2)
            android.graphics.drawable.Drawable r6 = r4.mDivider
            int r6 = r6.getIntrinsicWidth()
            int r6 = r6 + r0
            r5.set(r6, r2, r3, r2)
            goto L_0x0069
        L_0x005e:
            int r6 = r4.mOrientation
            if (r6 != r1) goto L_0x0066
            r5.set(r2, r0, r2, r3)
            goto L_0x0069
        L_0x0066:
            r5.set(r0, r2, r3, r2)
        L_0x0069:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newandromo.dev849565.app936843.SelectiveDividerItemDecoration.getItemOffsets(android.graphics.Rect, android.view.View, android.support.v7.widget.RecyclerView, android.support.v7.widget.RecyclerView$State):void");
    }

    public void setOrientation(int i) {
        if (i == 0 || i == 1) {
            this.mOrientation = i;
            return;
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        if (this.mOrientation == 1) {
            drawVertical(canvas, recyclerView);
        } else {
            drawHorizontal(canvas, recyclerView);
        }
    }

    private void drawVertical(Canvas canvas, RecyclerView recyclerView) {
        int paddingLeft = recyclerView.getPaddingLeft();
        int width = recyclerView.getWidth() - recyclerView.getPaddingRight();
        int childCount = recyclerView.getChildCount();
        int intrinsicHeight = this.mDivider.getIntrinsicHeight();
        for (int i = 1; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            if (isDecorationEnabled(childAt, recyclerView)) {
                int top = (childAt.getTop() - ((RecyclerView.LayoutParams) childAt.getLayoutParams()).topMargin) + ((int) (childAt.getTranslationY() + 0.5f));
                this.mDivider.setBounds(paddingLeft, top, width, top + intrinsicHeight);
                this.mDivider.draw(canvas);
            }
        }
    }

    private void drawHorizontal(Canvas canvas, RecyclerView recyclerView) {
        int paddingTop = recyclerView.getPaddingTop();
        int height = recyclerView.getHeight() - recyclerView.getPaddingBottom();
        int childCount = recyclerView.getChildCount();
        int intrinsicHeight = this.mDivider.getIntrinsicHeight();
        for (int i = 1; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            if (isDecorationEnabled(childAt, recyclerView)) {
                int left = (childAt.getLeft() - ((RecyclerView.LayoutParams) childAt.getLayoutParams()).leftMargin) + ((int) (childAt.getTranslationX() + 0.5f));
                this.mDivider.setBounds(left, paddingTop, left + intrinsicHeight, height);
                this.mDivider.draw(canvas);
            }
        }
    }

    private boolean isDecorationEnabled(View view, RecyclerView recyclerView) {
        RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(view);
        return childViewHolder instanceof Decoratable ? ((Decoratable) childViewHolder).isDecorationEnabled() : this.mDecorateByDefault;
    }

    private int getOrientation(RecyclerView recyclerView) {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) recyclerView.getLayoutManager()).getOrientation();
        }
        throw new IllegalStateException("SelectiveDividerItemDecoration can only be used with a LinearLayoutManager.");
    }
}
