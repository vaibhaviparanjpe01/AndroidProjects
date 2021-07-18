package com.newandromo.dev849565.app936843;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class SingleItemInflatingAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private static final String TAG = "SingleItemInflatingAdapter";
    private final int itemLayoutResourceId;

    public final int getItemCount() {
        return 1;
    }

    public abstract int getItemViewType();

    public abstract VH onCreateViewHolder(View view);

    public SingleItemInflatingAdapter(int i) {
        this.itemLayoutResourceId = i;
    }

    public final int getItemViewType(int i) {
        return getItemViewType();
    }

    public final VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == getItemViewType()) {
            return onCreateViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(this.itemLayoutResourceId, viewGroup, false));
        }
        throw new IllegalArgumentException("Unknown view type: " + i);
    }
}
