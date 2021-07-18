package com.newandromo.dev849565.app936843;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.ContentObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class SQLiteContentProvider extends ContentProvider {
    private static final int MAX_OPERATIONS_PER_YIELD_POINT = 500;
    private static final int SLEEP_AFTER_YIELD_DELAY = 4000;
    private static final String TAG = "SQLiteContentProvider";
    private final ThreadLocal<Boolean> mApplyingBatch = new ThreadLocal<>();
    private Set<Uri> mChangedUris;
    protected SQLiteDatabase mDb;
    private SQLiteOpenHelper mOpenHelper;

    public abstract int deleteInTransaction(Uri uri, String str, String[] strArr, boolean z);

    public abstract SQLiteOpenHelper getDatabaseHelper(Context context);

    public abstract Uri insertInTransaction(Uri uri, ContentValues contentValues, boolean z);

    public boolean isCallerSyncAdapter(Uri uri) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean syncToNetwork(Uri uri) {
        return false;
    }

    public abstract int updateInTransaction(Uri uri, ContentValues contentValues, String str, String[] strArr, boolean z);

    public boolean onCreate() {
        this.mOpenHelper = getDatabaseHelper(getContext());
        this.mChangedUris = new HashSet();
        return true;
    }

    /* access modifiers changed from: protected */
    public void postNotifyUri(Uri uri) {
        synchronized (this.mChangedUris) {
            this.mChangedUris.add(uri);
        }
    }

    public SQLiteOpenHelper getDatabaseHelper() {
        return this.mOpenHelper;
    }

    private boolean applyingBatch() {
        return this.mApplyingBatch.get() != null && this.mApplyingBatch.get().booleanValue();
    }

    /* JADX INFO: finally extract failed */
    public Uri insert(Uri uri, ContentValues contentValues) {
        boolean isCallerSyncAdapter = isCallerSyncAdapter(uri);
        if (applyingBatch()) {
            return insertInTransaction(uri, contentValues, isCallerSyncAdapter);
        }
        this.mDb = this.mOpenHelper.getWritableDatabase();
        this.mDb.beginTransaction();
        try {
            Uri insertInTransaction = insertInTransaction(uri, contentValues, isCallerSyncAdapter);
            this.mDb.setTransactionSuccessful();
            this.mDb.endTransaction();
            onEndTransaction(isCallerSyncAdapter);
            return insertInTransaction;
        } catch (Throwable th) {
            this.mDb.endTransaction();
            throw th;
        }
    }

    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        int length = contentValuesArr.length;
        boolean isCallerSyncAdapter = isCallerSyncAdapter(uri);
        this.mDb = this.mOpenHelper.getWritableDatabase();
        this.mDb.beginTransaction();
        int i = 0;
        while (i < length) {
            try {
                insertInTransaction(uri, contentValuesArr[i], isCallerSyncAdapter);
                this.mDb.yieldIfContendedSafely();
                i++;
            } catch (Throwable th) {
                this.mDb.endTransaction();
                throw th;
            }
        }
        this.mDb.setTransactionSuccessful();
        this.mDb.endTransaction();
        onEndTransaction(isCallerSyncAdapter);
        return length;
    }

    /* JADX INFO: finally extract failed */
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        boolean isCallerSyncAdapter = isCallerSyncAdapter(uri);
        if (applyingBatch()) {
            return updateInTransaction(uri, contentValues, str, strArr, isCallerSyncAdapter);
        }
        this.mDb = this.mOpenHelper.getWritableDatabase();
        this.mDb.beginTransaction();
        try {
            int updateInTransaction = updateInTransaction(uri, contentValues, str, strArr, isCallerSyncAdapter);
            this.mDb.setTransactionSuccessful();
            this.mDb.endTransaction();
            onEndTransaction(isCallerSyncAdapter);
            return updateInTransaction;
        } catch (Throwable th) {
            this.mDb.endTransaction();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public int delete(Uri uri, String str, String[] strArr) {
        boolean isCallerSyncAdapter = isCallerSyncAdapter(uri);
        if (applyingBatch()) {
            return deleteInTransaction(uri, str, strArr, isCallerSyncAdapter);
        }
        this.mDb = this.mOpenHelper.getWritableDatabase();
        this.mDb.beginTransaction();
        try {
            int deleteInTransaction = deleteInTransaction(uri, str, strArr, isCallerSyncAdapter);
            this.mDb.setTransactionSuccessful();
            this.mDb.endTransaction();
            onEndTransaction(isCallerSyncAdapter);
            return deleteInTransaction;
        } catch (Throwable th) {
            this.mDb.endTransaction();
            throw th;
        }
    }

    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        boolean z;
        this.mDb = this.mOpenHelper.getWritableDatabase();
        this.mDb.beginTransaction();
        try {
            this.mApplyingBatch.set(true);
            int size = arrayList.size();
            ContentProviderResult[] contentProviderResultArr = new ContentProviderResult[size];
            int i = 0;
            int i2 = 0;
            z = false;
            int i3 = 0;
            while (i < size) {
                i2++;
                if (i2 < 500) {
                    try {
                        ContentProviderOperation contentProviderOperation = arrayList.get(i);
                        if (!z && isCallerSyncAdapter(contentProviderOperation.getUri())) {
                            z = true;
                        }
                        if (i > 0 && contentProviderOperation.isYieldAllowed()) {
                            if (this.mDb.yieldIfContendedSafely(4000)) {
                                i3++;
                            }
                            i2 = 0;
                        }
                        contentProviderResultArr[i] = contentProviderOperation.apply(this, contentProviderResultArr, i);
                        i++;
                    } catch (Throwable th) {
                        th = th;
                        this.mApplyingBatch.set(false);
                        this.mDb.endTransaction();
                        onEndTransaction(z);
                        throw th;
                    }
                } else {
                    throw new OperationApplicationException("Too many content provider operations between yield points. The maximum number of operations per yield point is 500", i3);
                }
            }
            this.mDb.setTransactionSuccessful();
            this.mApplyingBatch.set(false);
            this.mDb.endTransaction();
            onEndTransaction(z);
            return contentProviderResultArr;
        } catch (Throwable th2) {
            th = th2;
            z = false;
            this.mApplyingBatch.set(false);
            this.mDb.endTransaction();
            onEndTransaction(z);
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public void onEndTransaction(boolean z) {
        HashSet<Uri> hashSet;
        synchronized (this.mChangedUris) {
            hashSet = new HashSet<>(this.mChangedUris);
            this.mChangedUris.clear();
        }
        ContentResolver contentResolver = getContext().getContentResolver();
        for (Uri uri : hashSet) {
            contentResolver.notifyChange(uri, (ContentObserver) null, !z && syncToNetwork(uri));
        }
    }
}
