package com.newandromo.dev849565.app936843;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.Support7Widget;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class AdapterListAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private static final String TAG = "AdapterListAdapter";
    /* access modifiers changed from: private */
    public List<RecyclerView.Adapter<VH>> mAdapters;
    private List<AdapterListAdapter<VH>.AdapterObserver> mObservers;
    private final int viewTypesPerAdapter;
    private final int viewTypesStart;

    public AdapterListAdapter() {
        this.mAdapters = new ArrayList();
        this.mObservers = new ArrayList();
        this.viewTypesStart = 0;
        this.viewTypesPerAdapter = 8;
    }

    public AdapterListAdapter(int i, int i2) {
        this.mAdapters = new ArrayList();
        this.mObservers = new ArrayList();
        this.viewTypesPerAdapter = i;
        this.viewTypesStart = i2;
    }

    private int getViewTypeOffset(int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < this.mAdapters.size(); i3++) {
            i2 += this.mAdapters.get(i3).getItemCount();
            if (i < i2) {
                return this.viewTypesStart + (this.viewTypesPerAdapter * i3);
            }
        }
        throw new IllegalArgumentException("No adapter for position " + i);
    }

    private int getViewTypeOffset(RecyclerView.Adapter<VH> adapter) {
        for (int i = 0; i < this.mAdapters.size(); i++) {
            if (this.mAdapters.get(i).equals(adapter)) {
                return this.viewTypesStart + (this.viewTypesPerAdapter * i);
            }
        }
        throw new IllegalArgumentException("Adapter not found.");
    }

    private int getAdapterIndex(RecyclerView.Adapter<VH> adapter) {
        for (int i = 0; i < this.mAdapters.size(); i++) {
            if (this.mAdapters.get(i).equals(adapter)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Adapter not found.");
    }

    private int getAdapterIndexForPosition(int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < this.mAdapters.size(); i3++) {
            i2 += this.mAdapters.get(i3).getItemCount();
            if (i < i2) {
                return i3;
            }
        }
        throw new IllegalArgumentException("No adapter for position " + i);
    }

    public int getItemCount() {
        int i = 0;
        for (RecyclerView.Adapter<VH> itemCount : this.mAdapters) {
            i += itemCount.getItemCount();
        }
        return i;
    }

    public int getItemViewType(int i) {
        int itemViewType;
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.mAdapters.size()) {
            RecyclerView.Adapter adapter = this.mAdapters.get(i2);
            int itemCount = adapter.getItemCount() + i3;
            if (i < itemCount && (itemViewType = adapter.getItemViewType(i - i3)) >= 0) {
                return itemViewType + this.viewTypesStart + (i2 * this.viewTypesPerAdapter);
            }
            i2++;
            i3 = itemCount;
        }
        throw new IllegalArgumentException("No adapter for position " + i);
    }

    private RecyclerView.Adapter<VH> getAdapterForViewType(int i) {
        int i2 = (i - this.viewTypesStart) / this.viewTypesPerAdapter;
        if (i2 >= 0 && i2 < this.mAdapters.size()) {
            return this.mAdapters.get(i2);
        }
        throw new IllegalArgumentException("Invalid view type " + i + ", viewType out of range.");
    }

    private int getAdapterIndexForViewType(int i) {
        int i2 = (i - this.viewTypesStart) / this.viewTypesPerAdapter;
        if (i2 >= 0 && i2 < this.mAdapters.size()) {
            return i2;
        }
        throw new IllegalArgumentException("Invalid view type " + i + ", viewType out of range.");
    }

    public RecyclerView.Adapter<VH> getAdapterForPosition(int i) {
        int i2 = 0;
        for (RecyclerView.Adapter<VH> next : this.mAdapters) {
            i2 += next.getItemCount();
            if (i < i2) {
                return next;
            }
        }
        throw new IllegalArgumentException("No adapter for position " + i);
    }

    private int getAdapterOffset(RecyclerView.Adapter<VH> adapter) {
        int i = 0;
        for (RecyclerView.Adapter next : this.mAdapters) {
            if (next.equals(adapter)) {
                return i;
            }
            i += next.getItemCount();
        }
        throw new IllegalArgumentException("Adapter not found.");
    }

    private int getAdapterOffsetForPosition(int i) {
        int i2 = 0;
        for (RecyclerView.Adapter<VH> itemCount : this.mAdapters) {
            int itemCount2 = itemCount.getItemCount() + i2;
            if (i < itemCount2) {
                return i2;
            }
            i2 = itemCount2;
        }
        throw new IllegalArgumentException("No adapter for position " + i);
    }

    public int getPosition(RecyclerView.Adapter<VH> adapter, int i) {
        return getAdapterOffset(adapter) + i;
    }

    public int getAdapterPosition(int i) {
        int i2 = 0;
        for (RecyclerView.Adapter<VH> itemCount : this.mAdapters) {
            int itemCount2 = itemCount.getItemCount() + i2;
            if (i < itemCount2) {
                return i - i2;
            }
            i2 = itemCount2;
        }
        throw new IllegalArgumentException("No adapter for position " + i);
    }

    private int getAdapterViewType(int i) {
        if (i > this.viewTypesStart && i < this.viewTypesStart + (this.mAdapters.size() * this.viewTypesPerAdapter)) {
            return (i - this.viewTypesStart) % this.viewTypesPerAdapter;
        }
        throw new IllegalArgumentException("Invalid view type " + i + ", viewType out of range.");
    }

    public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        int adapterIndexForViewType = getAdapterIndexForViewType(i);
        return this.mAdapters.get(adapterIndexForViewType).onCreateViewHolder(viewGroup, i - (this.viewTypesStart + (this.viewTypesPerAdapter * adapterIndexForViewType)));
    }

    public void onBindViewHolder(VH vh, int i) {
        int itemViewType = vh.getItemViewType();
        AdapterAndPosition adapterAndPosition = getAdapterAndPosition(i);
        Support7Widget.setItemViewType(vh, itemViewType - getViewTypeOffset(i));
        adapterAndPosition.adapter.onBindViewHolder(vh, adapterAndPosition.position);
        Support7Widget.setItemViewType(vh, itemViewType);
    }

    private static class AdapterAndPosition<T extends RecyclerView.ViewHolder> {
        RecyclerView.Adapter<T> adapter;
        int position;

        public AdapterAndPosition(RecyclerView.Adapter<T> adapter2, int i) {
            this.adapter = adapter2;
            this.position = i;
        }
    }

    public AdapterAndPosition<VH> getAdapterAndPosition(int i) {
        int i2 = 0;
        for (RecyclerView.Adapter next : this.mAdapters) {
            int itemCount = next.getItemCount() + i2;
            if (i < itemCount) {
                return new AdapterAndPosition<>(next, i - i2);
            }
            i2 = itemCount;
        }
        throw new IllegalArgumentException("No adapter for position " + i);
    }

    public void addAdapter(RecyclerView.Adapter<VH> adapter) {
        this.mAdapters.add(adapter);
        AdapterObserver adapterObserver = new AdapterObserver(adapter, this.mObservers.size());
        this.mObservers.add(adapterObserver);
        adapter.registerAdapterDataObserver(adapterObserver);
    }

    public void setAdapter(int i, RecyclerView.Adapter<VH> adapter) {
        RecyclerView.Adapter adapter2 = this.mAdapters.get(i);
        AdapterObserver adapterObserver = this.mObservers.get(i);
        if (!(adapter2 == null || adapterObserver == null)) {
            adapter2.unregisterAdapterDataObserver(adapterObserver);
        }
        this.mAdapters.set(i, adapter);
        AdapterObserver adapterObserver2 = new AdapterObserver(adapter, i);
        this.mObservers.set(i, adapterObserver2);
        adapter.registerAdapterDataObserver(adapterObserver2);
    }

    public void removeAdapter(int i) {
        this.mAdapters.get(i).unregisterAdapterDataObserver(this.mObservers.get(i));
        this.mObservers.remove(i);
        this.mAdapters.remove(i);
        updateObserversFrom(i);
    }

    private void updateObserversFrom(int i) {
        while (i < this.mObservers.size()) {
            this.mObservers.set(i, new AdapterObserver(this.mObservers.get(i).adapter, i));
            i++;
        }
    }

    public void removeAdapter(RecyclerView.Adapter<VH> adapter) {
        removeAdapter(this.mAdapters.indexOf(adapter));
    }

    public void clearAdapterList() {
        for (int size = this.mAdapters.size() - 1; size >= 0; size--) {
            this.mAdapters.get(size).unregisterAdapterDataObserver(this.mObservers.get(size));
        }
        this.mAdapters.clear();
        this.mObservers.clear();
    }

    private class AdapterObserver extends RecyclerView.AdapterDataObserver {
        private static final String TAG = "AdapterObserver";
        RecyclerView.Adapter<VH> adapter;
        int first;
        int index;

        public AdapterObserver(RecyclerView.Adapter<VH> adapter2, int i) {
            this.adapter = adapter2;
            this.index = i;
            this.first = getFirstPosition(i);
        }

        private int getFirstPosition(int i) {
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                i2 += ((RecyclerView.Adapter) AdapterListAdapter.this.mAdapters.get(i3)).getItemCount();
            }
            return i2;
        }

        private void updateFirstPosition() {
            int i = 0;
            for (int i2 = 0; i2 < this.index; i2++) {
                i += ((RecyclerView.Adapter) AdapterListAdapter.this.mAdapters.get(i2)).getItemCount();
            }
            this.first = i;
        }

        public void onChanged() {
            AdapterListAdapter.this.notifyDataSetChanged();
            updateFirstPosition();
        }

        public void onItemRangeChanged(int i, int i2) {
            AdapterListAdapter.this.notifyItemRangeChanged(this.first + i, i2);
            updateFirstPosition();
        }

        public void onItemRangeInserted(int i, int i2) {
            AdapterListAdapter.this.notifyItemRangeInserted(this.first + i, i2);
            updateFirstPosition();
        }

        public void onItemRangeRemoved(int i, int i2) {
            AdapterListAdapter.this.notifyItemRangeRemoved(this.first + i, i2);
            updateFirstPosition();
        }

        public void onItemRangeMoved(int i, int i2, int i3) {
            if (i3 == 1) {
                AdapterListAdapter.this.notifyItemMoved(this.first + i, this.first + i2);
                updateFirstPosition();
                return;
            }
            int i4 = this.first + i;
            int i5 = this.first + i2;
            for (int i6 = 0; i6 < i3; i6++) {
                AdapterListAdapter.this.notifyItemMoved(i4 + i6, i5 + i6);
            }
            updateFirstPosition();
        }
    }
}
