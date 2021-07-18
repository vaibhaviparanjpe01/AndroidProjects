package com.newandromo.dev849565.app936843;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import java.util.Collection;

public class ItemBindingAdapter<T> extends RecyclerView.Adapter<ItemViewHolder> {
    private static final int ITEM_MODEL = 2131296412;
    private LayoutInflater inflater;
    private final ItemBinder<T> itemBinder;
    private ObservableList<T> items;
    private final WeakReferenceOnListChangedCallback<T> onListChangedCallback;
    private final PositionBinder positionBinder;

    public ItemBindingAdapter(ItemBinder<T> itemBinder2, @Nullable Collection<T> collection) {
        this(itemBinder2, (PositionBinder) null, collection);
    }

    public ItemBindingAdapter(PositionBinder positionBinder2, @Nullable Collection<T> collection) {
        this((ItemBinder) null, positionBinder2, collection);
    }

    public ItemBindingAdapter(ItemBinder<T> itemBinder2, PositionBinder positionBinder2, @Nullable Collection<T> collection) {
        this.itemBinder = itemBinder2;
        this.positionBinder = positionBinder2;
        this.onListChangedCallback = new WeakReferenceOnListChangedCallback<>(this);
        setItems(collection);
    }

    public ObservableList<T> getItems() {
        return this.items;
    }

    public void setItems(@Nullable Collection<T> collection) {
        if (this.items != collection) {
            if (this.items != null) {
                this.items.removeOnListChangedCallback(this.onListChangedCallback);
                notifyItemRangeRemoved(0, this.items.size());
            }
            if (collection instanceof ObservableList) {
                this.items = (ObservableList) collection;
                notifyItemRangeInserted(0, this.items.size());
                this.items.addOnListChangedCallback(this.onListChangedCallback);
            } else if (collection != null) {
                this.items = new ObservableArrayList();
                this.items.addOnListChangedCallback(this.onListChangedCallback);
                this.items.addAll(collection);
            } else {
                this.items = null;
            }
        }
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        if (this.items != null) {
            this.items.removeOnListChangedCallback(this.onListChangedCallback);
        }
    }

    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (this.inflater == null) {
            this.inflater = LayoutInflater.from(viewGroup.getContext());
        }
        return new ItemViewHolder(DataBindingUtil.inflate(this.inflater, i, viewGroup, false));
    }

    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        Object obj = this.items.get(i);
        if (this.positionBinder != null) {
            itemViewHolder.binding.setVariable(this.positionBinder.getBindingVariableId(i), obj);
        } else if (this.itemBinder != null) {
            itemViewHolder.binding.setVariable(this.itemBinder.getBindingVariableId(obj), obj);
        } else {
            throw new IllegalStateException("itemBinder and positionBinder are both null");
        }
        itemViewHolder.binding.getRoot().setTag(R.id.item_binding_adapter_item_model, obj);
        itemViewHolder.binding.executePendingBindings();
    }

    public int getItemViewType(int i) {
        if (this.positionBinder != null) {
            return this.positionBinder.getLayoutResId(i);
        }
        if (this.itemBinder != null) {
            return this.itemBinder.getLayoutResId(this.items.get(i));
        }
        throw new IllegalStateException("itemBinder and positionBinder are both null");
    }

    public int getItemCount() {
        if (this.items == null) {
            return 0;
        }
        return this.items.size();
    }

    private static class WeakReferenceOnListChangedCallback<T> extends ObservableList.OnListChangedCallback {
        private final WeakReference<ItemBindingAdapter<T>> adapterReference;

        public WeakReferenceOnListChangedCallback(ItemBindingAdapter<T> itemBindingAdapter) {
            this.adapterReference = new WeakReference<>(itemBindingAdapter);
        }

        public void onChanged(ObservableList observableList) {
            RecyclerView.Adapter adapter = (RecyclerView.Adapter) this.adapterReference.get();
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }

        public void onItemRangeChanged(ObservableList observableList, int i, int i2) {
            RecyclerView.Adapter adapter = (RecyclerView.Adapter) this.adapterReference.get();
            if (adapter != null) {
                adapter.notifyItemRangeChanged(i, i2);
            }
        }

        public void onItemRangeInserted(ObservableList observableList, int i, int i2) {
            RecyclerView.Adapter adapter = (RecyclerView.Adapter) this.adapterReference.get();
            if (adapter != null) {
                adapter.notifyItemRangeInserted(i, i2);
            }
        }

        public void onItemRangeMoved(ObservableList observableList, int i, int i2, int i3) {
            RecyclerView.Adapter adapter = (RecyclerView.Adapter) this.adapterReference.get();
            if (adapter == null) {
                return;
            }
            if (i3 == 1) {
                adapter.notifyItemMoved(i, i2);
                return;
            }
            for (int i4 = 0; i4 < i3; i4++) {
                adapter.notifyItemMoved(i + i4, i2 + i4);
            }
        }

        public void onItemRangeRemoved(ObservableList observableList, int i, int i2) {
            RecyclerView.Adapter adapter = (RecyclerView.Adapter) this.adapterReference.get();
            if (adapter != null) {
                adapter.notifyItemRangeRemoved(i, i2);
            }
        }
    }
}
