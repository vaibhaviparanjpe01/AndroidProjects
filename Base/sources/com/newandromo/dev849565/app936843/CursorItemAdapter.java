package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.database.Cursor;
import android.os.Parcelable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import com.newandromo.dev849565.app936843.Item;

public abstract class CursorItemAdapter<T extends Item> extends ItemAdapter<T> {
    private static final String TAG = "CursorItemAdapter";
    protected Cursor mCursor;
    protected boolean mDataValid;
    protected int mRowIDColumn;

    public abstract T getItemToBind(Context context, Cursor cursor, int i);

    public CursorItemAdapter(Cursor cursor, ItemBinder<T> itemBinder, int i, boolean z, String str) {
        this(cursor, itemBinder, (PositionBinder) null, i, z, str);
    }

    public CursorItemAdapter(Cursor cursor, PositionBinder positionBinder, int i, boolean z, String str) {
        this(cursor, (ItemBinder) null, positionBinder, i, z, str);
    }

    public CursorItemAdapter(Cursor cursor, ItemBinder<T> itemBinder, PositionBinder positionBinder, int i, boolean z, String str) {
        super(itemBinder, positionBinder, i, z, str);
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

    public final T getItem(int i) {
        return getItem(getCursor(i));
    }

    public T getItem(Cursor cursor) {
        throw new IllegalStateException("getItem(Cursor cursor) needs to be implemented if using an ItemBinder instead of a PositionBinder.");
    }

    public final T getItemToBind(Context context, int i, int i2) {
        if (!this.mDataValid) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        } else if (this.mCursor.moveToPosition(i)) {
            return getItemToBind(context, this.mCursor, i2);
        } else {
            throw new IllegalStateException("couldn't move cursor to position " + i);
        }
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
