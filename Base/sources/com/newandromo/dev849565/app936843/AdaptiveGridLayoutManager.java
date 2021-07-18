package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Support7Widget;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

public class AdaptiveGridLayoutManager extends GridLayoutManager {
    private static final boolean DEBUG = false;
    private static final int MAX_SPAN_COUNT = 64;
    private static final String TAG = "AdaptiveGridLayoutManager";
    private int calculatedForSize;
    private boolean increaseInitialSpanCountIfRoomInParent;
    private boolean isAtMost;
    private int[] mMeasuredDimension;
    private boolean matchNaturalSpanSize;
    private int maxSpanCountLandscape;
    private int maxSpanCountPortrait;
    private int maxSpanSize;
    private int minItemSize;
    private int minSpanSize;
    private int parentSize;
    private int requestedSpanCount;
    private boolean rowBalancingEnabled;

    private final class ChildSizeInfo {
        public int decoratedChildSize;
        public int minItemSize;
        public int undecoratedChildSize;

        private ChildSizeInfo() {
        }
    }

    public AdaptiveGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.maxSpanSize = Integer.MAX_VALUE;
        this.maxSpanCountPortrait = 64;
        this.maxSpanCountLandscape = 64;
        this.mMeasuredDimension = new int[2];
        this.increaseInitialSpanCountIfRoomInParent = true;
        this.matchNaturalSpanSize = true;
        this.rowBalancingEnabled = false;
        this.requestedSpanCount = getProperties(context, attributeSet, i, i2).spanCount;
    }

    public AdaptiveGridLayoutManager(Context context, int i) {
        this(context, i, 64, 64);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AdaptiveGridLayoutManager(Context context, int i, int i2, boolean z) {
        super(context, i > 0 ? i : 1, i2, z);
        this.maxSpanSize = Integer.MAX_VALUE;
        this.maxSpanCountPortrait = 64;
        this.maxSpanCountLandscape = 64;
        this.mMeasuredDimension = new int[2];
        this.increaseInitialSpanCountIfRoomInParent = true;
        this.matchNaturalSpanSize = true;
        this.rowBalancingEnabled = false;
        this.requestedSpanCount = i;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AdaptiveGridLayoutManager(Context context, int i, int i2, int i3) {
        super(context, i > 0 ? i : 1);
        this.maxSpanSize = Integer.MAX_VALUE;
        int i4 = 64;
        this.maxSpanCountPortrait = 64;
        this.maxSpanCountLandscape = 64;
        this.mMeasuredDimension = new int[2];
        this.increaseInitialSpanCountIfRoomInParent = true;
        this.matchNaturalSpanSize = true;
        this.rowBalancingEnabled = false;
        this.requestedSpanCount = i;
        this.maxSpanCountPortrait = i2 <= 0 ? 64 : i2;
        this.maxSpanCountLandscape = i3 > 0 ? i3 : i4;
    }

    public AdaptiveGridLayoutManager(Context context, int i, int i2, int i3, float f) {
        this(context, i, i2, i3, f, 0.0f);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AdaptiveGridLayoutManager(Context context, int i, int i2, int i3, float f, float f2) {
        super(context, i > 0 ? i : 1);
        int i4 = Integer.MAX_VALUE;
        this.maxSpanSize = Integer.MAX_VALUE;
        this.maxSpanCountPortrait = 64;
        this.maxSpanCountLandscape = 64;
        this.mMeasuredDimension = new int[2];
        this.increaseInitialSpanCountIfRoomInParent = true;
        this.matchNaturalSpanSize = true;
        int i5 = 0;
        this.rowBalancingEnabled = false;
        this.requestedSpanCount = i;
        this.maxSpanCountPortrait = i2 <= 0 ? 64 : i2;
        this.maxSpanCountLandscape = i3 <= 0 ? 64 : i3;
        this.minSpanSize = f != 0.0f ? DimenUtils.dp(context, f) : i5;
        this.maxSpanSize = f2 != 0.0f ? DimenUtils.dp(context, f2) : i4;
    }

    public AdaptiveGridLayoutManager(Context context, int i, int i2, int i3, float f, float f2, boolean z) {
        this(context, i, i2, i3, f, f2);
        this.matchNaturalSpanSize = z;
    }

    public void setSpanCount(int i) {
        if (this.maxSpanCountPortrait > 0) {
            if (i != this.requestedSpanCount) {
                this.calculatedForSize = 0;
            }
            this.requestedSpanCount = i;
            updateSpanCount();
            return;
        }
        super.setSpanCount(i);
    }

    public void setMaxSpanCount(int i, int i2) {
        if (i <= 0) {
            i = 64;
        }
        if (i2 <= 0) {
            i2 = 64;
        }
        boolean z = (this.maxSpanCountPortrait == i && this.maxSpanCountLandscape == i2) ? false : true;
        this.maxSpanCountPortrait = i;
        this.maxSpanCountLandscape = i2;
        if (z && this.calculatedForSize != 0) {
            updateSpanCount();
        }
    }

    private int getMaxSpanCountForScreenOrientation(Context context) {
        return ThemeUtils.isLandscape(context) ? this.maxSpanCountLandscape : this.maxSpanCountPortrait;
    }

    public void setRowBalancingEnabled(boolean z) {
        boolean z2 = this.rowBalancingEnabled != z;
        this.rowBalancingEnabled = z;
        if (z2 && this.calculatedForSize != 0) {
            updateSpanCount();
        }
    }

    public void setMatchNaturalSpanSize(boolean z) {
        boolean z2 = this.matchNaturalSpanSize != z;
        this.matchNaturalSpanSize = z;
        if (z2 && this.calculatedForSize != 0) {
            updateSpanCount();
        }
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        ChildSizeInfo childSizeInfo;
        int maxSpanCountForScreenOrientation = getMaxSpanCountForScreenOrientation(Support7Widget.getContext(this));
        if (this.increaseInitialSpanCountIfRoomInParent && this.isAtMost && getChildCount() == 0 && getSpanCount() < maxSpanCountForScreenOrientation && (childSizeInfo = getChildSizeInfo(0, recycler, state)) != null) {
            this.minItemSize = childSizeInfo.minItemSize;
            int initialSpanCount = getInitialSpanCount(childSizeInfo.decoratedChildSize, childSizeInfo.minItemSize, this.requestedSpanCount, maxSpanCountForScreenOrientation);
            if (initialSpanCount > this.requestedSpanCount && initialSpanCount != getSpanCount()) {
                setSpanCount(initialSpanCount);
            }
        }
        super.onLayoutChildren(recycler, state);
    }

    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        if (this.calculatedForSize != getOrientedSize()) {
            updateSpanCount();
        }
    }

    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        if (getChildCount() == 0) {
            int size = View.MeasureSpec.getSize(i);
            int size2 = View.MeasureSpec.getSize(i2);
            boolean z = false;
            if (getOrientation() == 1) {
                this.parentSize = size;
                if (View.MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
                    z = true;
                }
                this.isAtMost = z;
            } else {
                this.parentSize = size2;
                if (View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
                    z = true;
                }
                this.isAtMost = z;
            }
        }
        super.onMeasure(recycler, state, i, i2);
    }

    private ChildSizeInfo getChildSizeInfo(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        RecyclerView.Recycler recycler2 = recycler;
        if (recycler2 == null || state.getItemCount() <= 0) {
            return null;
        }
        ChildSizeInfo childSizeInfo = new ChildSizeInfo();
        View viewForPosition = recycler2.getViewForPosition(i);
        addView(viewForPosition);
        measureChildWithMargins(viewForPosition, 0, 0);
        int decoratedMeasuredWidth = getDecoratedMeasuredWidth(viewForPosition);
        int decoratedMeasuredHeight = getDecoratedMeasuredHeight(viewForPosition);
        int measuredWidth = viewForPosition.getMeasuredWidth();
        int measuredHeight = viewForPosition.getMeasuredHeight();
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) viewForPosition.getLayoutParams();
        if (getOrientation() == 1) {
            childSizeInfo.decoratedChildSize = decoratedMeasuredWidth;
            childSizeInfo.undecoratedChildSize = measuredWidth;
            if (layoutParams.width > 0) {
                childSizeInfo.minItemSize = layoutParams.width;
            } else if (layoutParams.width == -2) {
                measureScrapChild(recycler, i, View.MeasureSpec.makeMeasureSpec(measuredWidth, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(0, 0), this.mMeasuredDimension);
                if (this.mMeasuredDimension[0] < measuredWidth) {
                    childSizeInfo.minItemSize = 0;
                } else {
                    childSizeInfo.minItemSize = measuredWidth;
                }
            } else {
                childSizeInfo.minItemSize = 0;
            }
        } else {
            childSizeInfo.decoratedChildSize = decoratedMeasuredHeight;
            childSizeInfo.undecoratedChildSize = measuredHeight;
            if (layoutParams.height > 0) {
                childSizeInfo.minItemSize = layoutParams.height;
            } else if (layoutParams.height == -2) {
                measureScrapChild(recycler, i, View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(measuredHeight, Integer.MIN_VALUE), this.mMeasuredDimension);
                if (this.mMeasuredDimension[1] < measuredHeight) {
                    childSizeInfo.minItemSize = 0;
                } else {
                    childSizeInfo.minItemSize = measuredHeight;
                }
            } else {
                childSizeInfo.minItemSize = 0;
            }
        }
        detachAndScrapView(viewForPosition, recycler2);
        return childSizeInfo;
    }

    private int getInitialSpanCount(int i, int i2, int i3, int i4) {
        if (i == 0) {
            return 0;
        }
        if (i2 > i) {
            i = i2;
        }
        int i5 = this.parentSize / i;
        if (i5 > i4) {
            i5 = i4;
        }
        while (i2 > this.parentSize / i5 && i5 > i3) {
            i5--;
        }
        return i5;
    }

    private void measureScrapChild(RecyclerView.Recycler recycler, int i, int i2, int i3, int[] iArr) {
        try {
            View viewForPosition = recycler.getViewForPosition(i);
            if (viewForPosition != null) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) viewForPosition.getLayoutParams();
                viewForPosition.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight(), layoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom(), layoutParams.height));
                iArr[0] = viewForPosition.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                iArr[1] = viewForPosition.getMeasuredHeight() + layoutParams.bottomMargin + layoutParams.topMargin;
                recycler.recycleView(viewForPosition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
    }

    private int getOrientedSize() {
        return getOrientation() == 1 ? getWidth() : getHeight();
    }

    private int getAdjustedSpanCount(int i, int i2) {
        int i3;
        int i4 = this.minItemSize > this.minSpanSize ? this.minItemSize : this.minSpanSize;
        int i5 = 1;
        if (this.requestedSpanCount > 0) {
            i3 = this.requestedSpanCount;
        } else if (this.minSpanSize <= 0 || i < i4) {
            i3 = 1;
        } else {
            i3 = i / i4;
            if (i3 > i2) {
                i3 = i2;
            }
        }
        while (i / i3 > i4 && i3 < i2) {
            i3++;
        }
        if (this.requestedSpanCount > 0) {
            i5 = this.requestedSpanCount;
        }
        while (i / i3 < i4 && i3 > i5) {
            i3--;
        }
        while (i / i3 > this.maxSpanSize && i3 < i2) {
            i3++;
        }
        while (i / i3 < this.minItemSize && i3 > i5) {
            i3--;
        }
        if (this.rowBalancingEnabled) {
            int balancedSpanCount = getBalancedSpanCount(i3, i5);
            if (checkBalancedSpanCount(balancedSpanCount, i3, i, this.maxSpanSize, i4)) {
                return balancedSpanCount;
            }
        }
        return i3;
    }

    private int getAdjustedSpanCount(int i, int i2, int i3, int i4) {
        int i5 = Integer.MAX_VALUE;
        int i6 = i2;
        int i7 = Integer.MAX_VALUE;
        while (i2 <= i4) {
            int abs = Math.abs((i / i2) - i3);
            if (abs < i5) {
                i6 = i2;
                i5 = abs;
            } else if (abs > i7) {
                break;
            }
            i2++;
            i7 = abs;
        }
        int i8 = this.requestedSpanCount > 0 ? this.requestedSpanCount : 1;
        while (i / i6 < this.minItemSize && i6 > i8) {
            i6--;
        }
        if (this.rowBalancingEnabled) {
            int balancedSpanCount = getBalancedSpanCount(i6, i8);
            if (checkBalancedSpanCount(balancedSpanCount, i6, i, this.maxSpanSize, this.minItemSize)) {
                return balancedSpanCount;
            }
        }
        return i6;
    }

    private static boolean checkBalancedSpanCount(int i, int i2, int i3, int i4, int i5) {
        if (i == i2) {
            return false;
        }
        int i6 = i3 / i;
        return !(i6 > i4) && !(i6 < i5);
    }

    private int getBalancedSpanCount(int i, int i2) {
        int itemCount = getItemCount();
        if ((itemCount != getChildCount() && itemCount / i > 3) || itemCount % i == 0) {
            return i;
        }
        int i3 = i;
        int i4 = i - 1;
        while (true) {
            if (i4 < i2) {
                break;
            }
            int i5 = itemCount % i4;
            if (i5 != 0) {
                if (i4 - i5 > i5 && i5 > itemCount % i3 && itemCount / i4 <= 3) {
                    i3 = i4;
                }
                i4--;
            } else if (itemCount / i4 <= 3) {
                return i4;
            }
        }
        return i3;
    }

    /* access modifiers changed from: private */
    public void updateSpanCount() {
        int adjustedSpanCount;
        Context context = Support7Widget.getContext(this);
        int i = 1;
        if (context != null) {
            int orientedSize = getOrientedSize();
            this.calculatedForSize = orientedSize;
            if (orientedSize == 0) {
                super.setSpanCount(this.requestedSpanCount);
                return;
            }
            if (getOrientation() == 1) {
                if (!ThemeUtils.isLandscape(context)) {
                    adjustedSpanCount = getAdjustedSpanCount(orientedSize, this.maxSpanCountPortrait);
                } else if (this.matchNaturalSpanSize) {
                    int screenHeight = ThemeUtils.getScreenHeight(context);
                    int adjustedSpanCount2 = getAdjustedSpanCount(screenHeight, this.maxSpanCountPortrait);
                    int i2 = screenHeight / adjustedSpanCount2;
                    if (adjustedSpanCount2 > this.maxSpanCountLandscape) {
                        adjustedSpanCount2 = this.maxSpanCountLandscape;
                    }
                    adjustedSpanCount = getAdjustedSpanCount(orientedSize, adjustedSpanCount2, i2, this.maxSpanCountLandscape);
                } else {
                    adjustedSpanCount = getAdjustedSpanCount(orientedSize, this.maxSpanCountLandscape);
                }
            } else if (ThemeUtils.isLandscape(context)) {
                adjustedSpanCount = getAdjustedSpanCount(orientedSize, this.maxSpanCountLandscape);
            } else if (this.matchNaturalSpanSize) {
                int width = getWidth();
                int adjustedSpanCount3 = getAdjustedSpanCount(width, this.maxSpanCountLandscape);
                int i3 = width / adjustedSpanCount3;
                if (adjustedSpanCount3 > this.maxSpanCountPortrait) {
                    adjustedSpanCount3 = this.maxSpanCountPortrait;
                }
                adjustedSpanCount = getAdjustedSpanCount(orientedSize, adjustedSpanCount3, i3, this.maxSpanCountPortrait);
            } else {
                adjustedSpanCount = getAdjustedSpanCount(orientedSize, this.maxSpanCountPortrait);
            }
            i = adjustedSpanCount;
        } else if (this.requestedSpanCount > 0) {
            i = this.requestedSpanCount;
        }
        int itemCount = getItemCount();
        if (i <= itemCount || itemCount <= 0 || itemCount < this.requestedSpanCount) {
            itemCount = i;
        }
        super.setSpanCount(itemCount);
    }

    protected class LayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        private RecyclerView recyclerView;

        public LayoutListener(@NonNull RecyclerView recyclerView2) {
            this.recyclerView = recyclerView2;
        }

        public void onGlobalLayout() {
            removeOnGlobalLayoutListener(this.recyclerView, this);
            AdaptiveGridLayoutManager.this.updateSpanCount();
        }

        public void removeOnGlobalLayoutListener(View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
            if (Build.VERSION.SDK_INT < 16) {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(onGlobalLayoutListener);
            } else {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
            }
        }
    }
}
