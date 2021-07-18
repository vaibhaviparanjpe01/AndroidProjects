package com.flurry.sdk;

import com.flurry.sdk.eq;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class cz<T extends eq> {
    private static final String a = "cz";
    private final cq<Object, T> b = new cq<>();
    private final HashMap<T, Object> c = new HashMap<>();
    /* access modifiers changed from: private */
    public final HashMap<T, Future<?>> d = new HashMap<>();
    private final ThreadPoolExecutor e;

    public cz(String str, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue) {
        this.e = new ThreadPoolExecutor(timeUnit, blockingQueue) {
            /* access modifiers changed from: protected */
            public final void beforeExecute(Thread thread, Runnable runnable) {
                super.beforeExecute(thread, runnable);
                final eq a2 = cz.a(runnable);
                if (a2 != null) {
                    new eo() {
                        public final void a() {
                        }
                    }.run();
                }
            }

            /* access modifiers changed from: protected */
            public final void afterExecute(Runnable runnable, Throwable th) {
                super.afterExecute(runnable, th);
                final eq a2 = cz.a(runnable);
                if (a2 != null) {
                    synchronized (cz.this.d) {
                        cz.this.d.remove(a2);
                    }
                    cz.this.a(a2);
                    new eo() {
                        public final void a() {
                        }
                    }.run();
                }
            }

            /* access modifiers changed from: protected */
            public final <V> RunnableFuture<V> newTaskFor(Runnable runnable, V v) {
                cy cyVar = new cy(runnable, v);
                synchronized (cz.this.d) {
                    cz.this.d.put((eq) runnable, cyVar);
                }
                return cyVar;
            }

            /* access modifiers changed from: protected */
            public final <V> RunnableFuture<V> newTaskFor(Callable<V> callable) {
                throw new UnsupportedOperationException("Callable not supported");
            }
        };
        this.e.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy() {
            public final void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                super.rejectedExecution(runnable, threadPoolExecutor);
                final eq a2 = cz.a(runnable);
                if (a2 != null) {
                    synchronized (cz.this.d) {
                        cz.this.d.remove(a2);
                    }
                    cz.this.a(a2);
                    new eo() {
                        public final void a() {
                        }
                    }.run();
                }
            }
        });
        this.e.setThreadFactory(new ei(str));
    }

    /* access modifiers changed from: private */
    public synchronized void a(T t) {
        b(this.c.get(t), t);
    }

    private synchronized void b(Object obj, T t) {
        this.b.b(obj, t);
        this.c.remove(t);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0014, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a(java.lang.Object r1, T r2) {
        /*
            r0 = this;
            monitor-enter(r0)
            if (r1 == 0) goto L_0x0013
            if (r2 != 0) goto L_0x0006
            goto L_0x0013
        L_0x0006:
            r0.c(r1, r2)     // Catch:{ all -> 0x0010 }
            java.util.concurrent.ThreadPoolExecutor r1 = r0.e     // Catch:{ all -> 0x0010 }
            r1.submit(r2)     // Catch:{ all -> 0x0010 }
            monitor-exit(r0)
            return
        L_0x0010:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        L_0x0013:
            monitor-exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.cz.a(java.lang.Object, com.flurry.sdk.eq):void");
    }

    private synchronized void c(Object obj, T t) {
        this.b.a(obj, t);
        this.c.put(t, obj);
    }

    public final synchronized void c() {
        HashSet<Object> hashSet = new HashSet<>();
        hashSet.addAll(this.b.a.keySet());
        for (Object a2 : hashSet) {
            a(a2);
        }
    }

    public final synchronized void a(Object obj) {
        if (obj != null) {
            HashSet<eq> hashSet = new HashSet<>();
            hashSet.addAll(this.b.a(obj));
            for (eq b2 : hashSet) {
                b(b2);
            }
        }
    }

    private synchronized void b(final T t) {
        Future remove;
        if (t != null) {
            synchronized (this.d) {
                remove = this.d.remove(t);
            }
            a(t);
            if (remove != null) {
                remove.cancel(true);
            }
            new eo() {
                public final void a() {
                    t.h();
                }
            }.run();
        }
    }

    static /* synthetic */ eq a(Runnable runnable) {
        if (runnable instanceof cy) {
            return (eq) ((cy) runnable).a();
        }
        if (runnable instanceof eq) {
            return (eq) runnable;
        }
        String str = a;
        db.a(6, str, "Unknown runnable class: " + runnable.getClass().getName());
        return null;
    }
}
