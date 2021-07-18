package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = {16843284};
    public static final int HORIZONTAL = 0;
    private static final String TAG = "GridDividerItemDecoration";
    public static final int VERTICAL = 1;
    private boolean alwaysDrawFirstDivider;
    private boolean dividerInsideItem;
    private GridInfo gridInfo;
    private final GridItemData item = new GridItemData();
    private final Rect mBounds = new Rect();
    private Drawable mDivider;

    private boolean isFirstRow(int i, int i2) {
        return i < i2;
    }

    private boolean isRightmostColumn(int i, int i2, int i3) {
        return i + i2 == i3;
    }

    public GridDividerItemDecoration(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(ATTRS);
        this.mDivider = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
    }

    public GridDividerItemDecoration(Drawable drawable) {
        this.mDivider = drawable;
        if (drawable == null) {
            throw new IllegalArgumentException("Drawable cannot be null.");
        }
    }

    public void setDrawable(Drawable drawable) {
        if (drawable != null) {
            this.mDivider = drawable;
            return;
        }
        throw new IllegalArgumentException("Drawable cannot be null.");
    }

    public void setOrientation(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        } else if (this.gridInfo != null) {
            this.gridInfo.orientation = i;
        }
    }

    public void setReverseLayout(boolean z) {
        if (this.gridInfo != null) {
            this.gridInfo.reverseLayout = z;
        }
    }

    public void resetGridInfo() {
        this.gridInfo = null;
    }

    public void setAlwaysDrawFirstDivider(boolean z) {
        this.alwaysDrawFirstDivider = z;
    }

    public void setDrawDividerInsideItem(boolean z) {
        this.dividerInsideItem = z;
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        if (recyclerView.getLayoutManager() != null) {
            if (this.gridInfo == null) {
                this.gridInfo = new GridInfo(recyclerView);
            } else {
                getGridInfo(recyclerView, this.gridInfo);
            }
            int i = this.gridInfo.spanCount;
            int i2 = this.gridInfo.orientation;
            boolean z = this.gridInfo.reverseLayout;
            if (i2 == 1) {
                if (z) {
                    drawForVerticalReverse(canvas, recyclerView, state, i);
                } else {
                    drawForVertical(canvas, recyclerView, state, i);
                }
            } else if (z) {
                drawForHorizontalReverse(canvas, recyclerView, state, i);
            } else {
                drawForHorizontal(canvas, recyclerView, state, i);
            }
        }
    }

    private void drawForVerticalReverse(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state, int i) {
        canvas.save();
        int childCount = recyclerView.getChildCount();
        int itemCount = state.getItemCount();
        boolean z = childCount >= state.getItemCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = recyclerView.getChildAt(i2);
            int childAdapterPosition = recyclerView.getChildAdapterPosition(childAt);
            recyclerView.getDecoratedBoundsWithMargins(childAt, this.mBounds);
            int round = this.mBounds.left + Math.round(ViewCompat.getTranslationX(childAt));
            int round2 = this.mBounds.right + Math.round(ViewCompat.getTranslationX(childAt));
            int round3 = this.mBounds.top + Math.round(ViewCompat.getTranslationY(childAt));
            int intrinsicHeight = this.mDivider.getIntrinsicHeight() + round3;
            if (z || !isLastRow(childAdapterPosition, i, itemCount)) {
                this.mDivider.setBounds(round, round3, round2, intrinsicHeight);
                this.mDivider.draw(canvas);
            }
            if ((z || this.alwaysDrawFirstDivider) && isFirstRow(childAdapterPosition, i)) {
                int round4 = this.mBounds.bottom + Math.round(ViewCompat.getTranslationY(childAt));
                this.mDivider.setBounds(round, round4 - this.mDivider.getIntrinsicHeight(), round2, round4);
                this.mDivider.draw(canvas);
            }
            getGridItemData(recyclerView, childAt, this.item);
            if (!isRightmostColumn(this.item.spanIndex, this.item.spanSize, i)) {
                this.mDivider.setBounds(round2 - this.mDivider.getIntrinsicWidth(), this.mBounds.top + Math.round(ViewCompat.getTranslationY(childAt)), round2, this.mBounds.bottom + Math.round(ViewCompat.getTranslationY(childAt)));
                this.mDivider.draw(canvas);
            }
        }
        canvas.restore();
    }

    private void drawForVertical(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state, int i) {
        canvas.save();
        int childCount = recyclerView.getChildCount();
        int itemCount = state.getItemCount();
        boolean z = childCount >= state.getItemCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = recyclerView.getChildAt(i2);
            int childAdapterPosition = recyclerView.getChildAdapterPosition(childAt);
            recyclerView.getDecoratedBoundsWithMargins(childAt, this.mBounds);
            int round = this.mBounds.left + Math.round(ViewCompat.getTranslationX(childAt));
            int round2 = this.mBounds.right + Math.round(ViewCompat.getTranslationX(childAt));
            int round3 = this.mBounds.bottom + Math.round(ViewCompat.getTranslationY(childAt));
            int intrinsicHeight = round3 - this.mDivider.getIntrinsicHeight();
            if (z || !isLastRow(childAdapterPosition, i, itemCount)) {
                this.mDivider.setBounds(round, intrinsicHeight, round2, round3);
                this.mDivider.draw(canvas);
            }
            if ((z || this.alwaysDrawFirstDivider) && isFirstRow(childAdapterPosition, i)) {
                int round4 = this.mBounds.top + Math.round(ViewCompat.getTranslationY(childAt));
                this.mDivider.setBounds(round, round4, round2, this.mDivider.getIntrinsicHeight() + round4);
                this.mDivider.draw(canvas);
            }
            getGridItemData(recyclerView, childAt, this.item);
            if (!isRightmostColumn(this.item.spanIndex, this.item.spanSize, i)) {
                this.mDivider.setBounds(round2 - this.mDivider.getIntrinsicWidth(), this.mBounds.top + Math.round(ViewCompat.getTranslationY(childAt)), round2, this.mBounds.bottom + Math.round(ViewCompat.getTranslationY(childAt)));
                this.mDivider.draw(canvas);
            }
        }
        canvas.restore();
    }

    private void drawForHorizontalReverse(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state, int i) {
        canvas.save();
        int childCount = recyclerView.getChildCount();
        int itemCount = state.getItemCount();
        int itemCount2 = state.getItemCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = recyclerView.getChildAt(i2);
            recyclerView.getDecoratedBoundsWithMargins(childAt, this.mBounds);
            int round = this.mBounds.left + Math.round(ViewCompat.getTranslationX(childAt));
            int round2 = this.mBounds.right + Math.round(ViewCompat.getTranslationX(childAt));
            int round3 = this.mBounds.bottom + Math.round(ViewCompat.getTranslationY(childAt));
            this.mDivider.setBounds(round, round3 - this.mDivider.getIntrinsicHeight(), round2, round3);
            this.mDivider.draw(canvas);
            if (!isLastRow(recyclerView.getChildAdapterPosition(childAt), i, itemCount)) {
                int round4 = this.mBounds.top + Math.round(ViewCompat.getTranslationY(childAt));
                int round5 = this.mBounds.bottom + Math.round(ViewCompat.getTranslationY(childAt));
                this.mDivider.setBounds(round, round4, this.mDivider.getIntrinsicWidth() + round, round5);
                this.mDivider.draw(canvas);
            }
        }
        canvas.restore();
    }

    private void drawForHorizontal(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state, int i) {
        canvas.save();
        int childCount = recyclerView.getChildCount();
        int itemCount = state.getItemCount();
        int itemCount2 = state.getItemCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = recyclerView.getChildAt(i2);
            int childAdapterPosition = recyclerView.getChildAdapterPosition(childAt);
            recyclerView.getDecoratedBoundsWithMargins(childAt, this.mBounds);
            int round = this.mBounds.left + Math.round(ViewCompat.getTranslationX(childAt));
            int round2 = this.mBounds.right + Math.round(ViewCompat.getTranslationX(childAt));
            int round3 = this.mBounds.bottom + Math.round(ViewCompat.getTranslationY(childAt));
            this.mDivider.setBounds(round, round3 - this.mDivider.getIntrinsicHeight(), round2, round3);
            this.mDivider.draw(canvas);
            if (!isLastRow(childAdapterPosition, i, itemCount)) {
                int round4 = this.mBounds.top + Math.round(ViewCompat.getTranslationY(childAt));
                int round5 = this.mBounds.bottom + Math.round(ViewCompat.getTranslationY(childAt));
                this.mDivider.setBounds(round2 - this.mDivider.getIntrinsicWidth(), round4, round2, round5);
                this.mDivider.draw(canvas);
            }
        }
        canvas.restore();
    }

    private boolean isLeftmostColumn(int i, int i2) {
        return i % i2 == 0;
    }

    private boolean isLastRow(int i, int i2, int i3) {
        return i >= ((i3 - 1) / i2) * i2;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int i = 0;
        if (this.dividerInsideItem) {
            rect.set(0, 0, 0, 0);
            return;
        }
        recyclerView.getLayoutManager();
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int itemCount = state.getItemCount();
        if (this.gridInfo == null) {
            this.gridInfo = new GridInfo(recyclerView);
        } else {
            getGridInfo(recyclerView, this.gridInfo);
        }
        int i2 = this.gridInfo.spanCount;
        int i3 = this.gridInfo.orientation;
        boolean z = this.gridInfo.reverseLayout;
        if (i3 == 1) {
            getGridItemData(recyclerView, view, this.item);
            if (z) {
                rect.left = 0;
                rect.top = this.mDivider.getIntrinsicHeight();
                rect.right = this.mDivider.getIntrinsicWidth();
                rect.bottom = 0;
                return;
            }
            rect.left = 0;
            rect.top = 0;
            rect.right = this.mDivider.getIntrinsicWidth();
            rect.bottom = this.mDivider.getIntrinsicHeight();
        } else if (z) {
            rect.left = isLastRow(childAdapterPosition, i2, itemCount) ? 0 : this.mDivider.getIntrinsicWidth();
            rect.right = 0;
            rect.top = 0;
            rect.bottom = this.mDivider.getIntrinsicHeight();
        } else {
            rect.left = 0;
            rect.top = 0;
            if (!isLastRow(childAdapterPosition, i2, itemCount)) {
                i = this.mDivider.getIntrinsicWidth();
            }
            rect.right = i;
            rect.bottom = this.mDivider.getIntrinsicHeight();
        }
    }

    private static final class GridInfo {
        public int orientation;
        public boolean reverseLayout;
        public int spanCount;

        public GridInfo(int i, int i2, boolean z) {
            this.spanCount = i;
            this.orientation = i2;
            this.reverseLayout = z;
        }

        public GridInfo(RecyclerView recyclerView) {
            GridDividerItemDecoration.getGridInfo(recyclerView, this);
        }
    }

    /* access modifiers changed from: private */
    public static void getGridInfo(RecyclerView recyclerView, GridInfo gridInfo2) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridInfo2.spanCount = gridLayoutManager.getSpanCount();
            gridInfo2.orientation = gridLayoutManager.getOrientation();
            gridInfo2.reverseLayout = gridLayoutManager.getReverseLayout();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            gridInfo2.spanCount = staggeredGridLayoutManager.getSpanCount();
            gridInfo2.orientation = staggeredGridLayoutManager.getOrientation();
            gridInfo2.reverseLayout = staggeredGridLayoutManager.getReverseLayout();
        } else if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            gridInfo2.spanCount = 1;
            gridInfo2.orientation = linearLayoutManager.getOrientation();
            gridInfo2.reverseLayout = linearLayoutManager.getReverseLayout();
        } else {
            throw new UnsupportedOperationException("Incompatible layout manager.");
        }
    }

    private static final class GridItemData {
        public int spanIndex = -1;
        public int spanSize = -1;

        public GridItemData() {
        }

        public GridItemData(int i, int i2) {
            this.spanIndex = i;
            this.spanSize = i2;
        }

        public GridItemData(RecyclerView recyclerView, View view) {
            GridDividerItemDecoration.getGridItemData(recyclerView, view, this);
        }
    }

    /* access modifiers changed from: private */
    public static void getGridItemData(RecyclerView recyclerView, View view, GridItemData gridItemData) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
            gridItemData.spanIndex = layoutParams.getSpanIndex();
            gridItemData.spanSize = layoutParams.getSpanSize();
            return;
        }
        int i = 1;
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            StaggeredGridLayoutManager.LayoutParams layoutParams2 = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            gridItemData.spanIndex = layoutParams2.getSpanIndex();
            if (layoutParams2.isFullSpan()) {
                i = staggeredGridLayoutManager.getSpanCount();
            }
            gridItemData.spanSize = i;
        } else if (layoutManager instanceof LinearLayoutManager) {
            gridItemData.spanIndex = 0;
            gridItemData.spanSize = 1;
        } else {
            throw new UnsupportedOperationException("Incompatible layout manager.");
        }
    }
}
