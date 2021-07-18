package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.os.Parcelable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.newandromo.dev849565.app936843.Item;

public abstract class ItemBindingCursorAdapter<T extends Item> extends RecyclerView.Adapter<ItemViewHolder> {
    private static final int ITEM_MODEL = 2131296260;
    private static final String TAG = "ItemBindingCursorAdapter";
    private ViewDataBinding defaultLayoutBinding;
    private LayoutInflater inflater;
    private final ItemBinder<T> itemBinder;
    protected Cursor mCursor;
    protected boolean mDataValid;
    protected int mRowIDColumn;
    private final PositionBinder positionBinder;
    protected RecyclerView recyclerView;
    protected final ObservableInt toolbarColor;

    public abstract T getItem(Cursor cursor);

    /* access modifiers changed from: protected */
    public abstract void onCreateItemBinding(ViewDataBinding viewDataBinding);

    public ItemBindingCursorAdapter(Cursor cursor, ItemBinder<T> itemBinder2) {
        this(cursor, itemBinder2, (PositionBinder) null);
    }

    public ItemBindingCursorAdapter(Cursor cursor, PositionBinder positionBinder2) {
        this(cursor, (ItemBinder) null, positionBinder2);
    }

    public ItemBindingCursorAdapter(Cursor cursor, ItemBinder<T> itemBinder2, PositionBinder positionBinder2) {
        this.toolbarColor = new ObservableInt();
        this.itemBinder = itemBinder2;
        this.positionBinder = positionBinder2;
        init(cursor);
    }

    /* access modifiers changed from: package-private */
    public void init(Cursor cursor) {
        boolean z = cursor != null;
        this.mCursor = cursor;
        this.mDataValid = z;
        this.mRowIDColumn = z ? cursor.getColumnIndexOrThrow("_id") : -1;
        setHasStableIds(true);
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView2) {
        super.onAttachedToRecyclerView(recyclerView2);
        this.recyclerView = recyclerView2;
        this.inflater = null;
    }

    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        if (this.inflater == null) {
            this.inflater = LayoutInflater.from(context);
        }
        ViewDataBinding inflate = DataBindingUtil.inflate(this.inflater, i, viewGroup, false);
        if (this.defaultLayoutBinding == null && !ThemeUtils.isDefaultLayoutBindingWorkaroundNeeded()) {
            this.defaultLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(context), i, viewGroup, false);
        }
        inflate.setVariable(35, this.defaultLayoutBinding);
        inflate.setVariable(4, this.toolbarColor);
        onCreateItemBinding(inflate);
        return new ItemViewHolder(inflate);
    }

    public void onToolbarColorsReady(int i, int i2) {
        this.toolbarColor.set(i);
    }

    public final void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        if (!this.mDataValid) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        } else if (!this.mCursor.moveToPosition(i)) {
            throw new IllegalStateException("couldn't move cursor to position " + i);
        } else if (itemViewHolder != null) {
            if (ThemeUtils.isDefaultLayoutBindingWorkaroundNeeded()) {
                Context context = itemViewHolder.binding.getRoot().getContext();
                itemViewHolder.binding.setVariable(35, DataBindingUtil.inflate(LayoutInflater.from(context), itemViewHolder.getItemViewType(), (ViewGroup) null, false));
            }
            Item item = getItem(this.mCursor);
            if (item != null) {
                item.setContext(itemViewHolder.binding.getRoot().getContext());
                item.paletteTargets = itemViewHolder.paletteTargets;
                if (this.positionBinder != null) {
                    itemViewHolder.binding.setVariable(this.positionBinder.getBindingVariableId(i), item);
                } else if (this.itemBinder != null) {
                    itemViewHolder.binding.setVariable(this.itemBinder.getBindingVariableId(item), item);
                } else {
                    throw new IllegalStateException("itemBinder and positionBinder are both null");
                }
                itemViewHolder.binding.setVariable(14, Integer.valueOf(itemViewHolder.getAdapterPosition()));
                itemViewHolder.binding.getRoot().setTag(R.id.ItemBindingCursorAdapter_item_model, item);
                itemViewHolder.binding.executePendingBindings();
                return;
            }
            throw new IllegalStateException("onBindViewHolder item is null, position: " + i);
        } else {
            throw new IllegalStateException("ItemViewHolder for position " + i + " is null");
        }
    }

    public void onViewRecycled(ItemViewHolder itemViewHolder) {
        Glide.clear((Target<?>) new ClearingTarget(itemViewHolder));
        for (PaletteViewTarget clear : itemViewHolder.paletteTargets) {
            Glide.clear((Target<?>) clear);
        }
        itemViewHolder.paletteTargets.clear();
        if (itemViewHolder.binding != null) {
            Context context = itemViewHolder.itemView.getContext();
            if (context instanceof ItemBindingInfo) {
                ImageView imageView = (ImageView) ((ItemBindingInfo) context).getItemImageViewFromBinding(itemViewHolder.binding);
                if (imageView != null) {
                    Glide.clear((View) imageView);
                }
            } else {
                ImageView imageView2 = (ImageView) itemViewHolder.itemView.findViewById(R.id.itemImage);
                if (imageView2 != null) {
                    Glide.clear((View) imageView2);
                }
            }
        }
        super.onViewRecycled(itemViewHolder);
    }

    public final int getItemViewType(int i) {
        if (this.positionBinder != null) {
            return this.positionBinder.getLayoutResId(i);
        }
        if (this.itemBinder != null) {
            return this.itemBinder.getLayoutResId(getItem(i));
        }
        throw new IllegalStateException("itemBinder and positionBinder are both null");
    }

    public final T getItem(int i) {
        return getItem(getCursor(i));
    }

    public Cursor getCursor() {
        return this.mCursor;
    }

    public int getItemCount() {
        if (!this.mDataValid || this.mCursor == null) {
            return 0;
        }
        return this.mCursor.getCount();
    }

    public long getItemId(int i) {
        if (!hasStableIds() || !this.mDataValid || this.mCursor == null || !this.mCursor.moveToPosition(i)) {
            return -1;
        }
        return this.mCursor.getLong(this.mRowIDColumn);
    }

    public Cursor getCursor(int i) {
        if (this.mCursor != null && !this.mCursor.isClosed()) {
            this.mCursor.moveToPosition(i);
        }
        return this.mCursor;
    }

    public void changeCursor(Cursor cursor) {
        Cursor swapCursor = swapCursor(cursor);
        if (swapCursor != null) {
            swapCursor.close();
        }
    }

    public Cursor swapCursor(Cursor cursor) {
        if (cursor == this.mCursor) {
            return null;
        }
        Cursor cursor2 = this.mCursor;
        this.mCursor = cursor;
        if (cursor != null) {
            this.mRowIDColumn = cursor.getColumnIndexOrThrow("_id");
            this.mDataValid = true;
            if (cursor2 != null) {
                DiffUtil.DiffResult calculateDiff = calculateDiff(cursor2, cursor, cursor2.getColumnIndexOrThrow("_id"), this.mRowIDColumn);
                if (calculateDiff != null) {
                    Parcelable onSaveInstanceState = this.recyclerView.getLayoutManager().onSaveInstanceState();
                    calculateDiff.dispatchUpdatesTo((RecyclerView.Adapter) this);
                    this.recyclerView.getLayoutManager().onRestoreInstanceState(onSaveInstanceState);
                } else {
                    notifyDataSetChanged();
                }
            } else {
                notifyItemRangeInserted(0, cursor.getCount());
            }
        } else {
            this.mRowIDColumn = -1;
            this.mDataValid = false;
            notifyItemRangeRemoved(0, getItemCount());
        }
        return cursor2;
    }

    /* access modifiers changed from: protected */
    public DiffUtil.DiffResult calculateDiff(Cursor cursor, Cursor cursor2, int i, int i2) {
        return DiffUtil.calculateDiff(new CursorIdDiffCallback(cursor, cursor2, i, i2), true);
    }

    public CharSequence convertToString(Cursor cursor) {
        return cursor == null ? "" : cursor.toString();
    }
}
