package android.support.v7.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

public class Support7Widget {
    public static void setItemViewType(RecyclerView.ViewHolder viewHolder, int i) {
        viewHolder.mItemViewType = i;
    }

    public static RecyclerView getRecyclerView(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager != null) {
            return layoutManager.mRecyclerView;
        }
        return null;
    }

    public static Context getContext(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager == null || layoutManager.mRecyclerView == null) {
            return null;
        }
        return layoutManager.mRecyclerView.getContext();
    }
}
