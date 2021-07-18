package com.newandromo.dev849565.app936843;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;

public abstract class CursorRecyclerAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private static final String TAG = "CursorRecyclerAdapter";
    protected Cursor mCursor;
    protected boolean mDataValid;
    protected int mRowIDColumn;

    public abstract void onBindViewHolder(VH vh, Cursor cursor);

    public CursorRecyclerAdapter(Cursor cursor) {
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

    public final void onBindViewHolder(VH vh, int i) {
        if (!this.mDataValid) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        } else if (this.mCursor.moveToPosition(i)) {
            onBindViewHolder(vh, this.mCursor);
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

    public Cursor getItem(int i) {
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
            notifyDataSetChanged();
        } else {
            this.mRowIDColumn = -1;
            this.mDataValid = false;
            notifyItemRangeRemoved(0, getItemCount());
        }
        return cursor2;
    }

    public CharSequence convertToString(Cursor cursor) {
        return cursor == null ? "" : cursor.toString();
    }
}
