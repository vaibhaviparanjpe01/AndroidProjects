package com.newandromo.dev849565.app936843;

import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

public class CursorIdDiffCallback extends DiffUtil.Callback {
    private final long[] newIds;
    private final long[] oldIds;

    public CursorIdDiffCallback(Cursor cursor, Cursor cursor2, int i, int i2) {
        this.oldIds = new long[cursor.getCount()];
        this.newIds = new long[cursor2.getCount()];
        cursor.moveToFirst();
        int i3 = 0;
        int i4 = 0;
        while (!cursor.isAfterLast()) {
            this.oldIds[i4] = cursor.getLong(i);
            cursor.moveToNext();
            i4++;
        }
        cursor2.moveToFirst();
        while (!cursor2.isAfterLast()) {
            this.newIds[i3] = cursor2.getLong(i2);
            cursor2.moveToNext();
            i3++;
        }
    }

    public int getOldListSize() {
        if (this.oldIds != null) {
            return this.oldIds.length;
        }
        return 0;
    }

    public int getNewListSize() {
        if (this.newIds != null) {
            return this.newIds.length;
        }
        return 0;
    }

    public boolean areItemsTheSame(int i, int i2) {
        return this.newIds[i2] == this.oldIds[i];
    }

    public boolean areContentsTheSame(int i, int i2) {
        return this.newIds[i2] == this.oldIds[i];
    }

    @Nullable
    public Object getChangePayload(int i, int i2) {
        return super.getChangePayload(i, i2);
    }
}
