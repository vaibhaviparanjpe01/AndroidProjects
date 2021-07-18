package com.flurry.sdk;

import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public final class cy<V> extends FutureTask<V> {
    private final WeakReference<Callable<V>> a = new WeakReference<>((Object) null);
    private final WeakReference<Runnable> b;

    public cy(Runnable runnable, V v) {
        super(runnable, v);
        this.b = new WeakReference<>(runnable);
    }

    public final Runnable a() {
        return (Runnable) this.b.get();
    }
}
