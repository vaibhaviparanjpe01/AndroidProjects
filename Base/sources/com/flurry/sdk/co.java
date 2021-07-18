package com.flurry.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import com.flurry.sdk.cn;
import com.flurry.sdk.cp;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class co {
    /* access modifiers changed from: private */
    public static final String a = "co";
    /* access modifiers changed from: private */
    public static final List<String> b = new ArrayList(Arrays.asList(new String[]{"FlurryFullscreenTakeoverActivity", "FlurryBrowserActivity"}));
    private static co c;
    /* access modifiers changed from: private */
    public static boolean e;
    /* access modifiers changed from: private */
    public static String g;
    private Application.ActivityLifecycleCallbacks d;
    private ComponentCallbacks2 f;

    private co() {
        Context context = ck.a().a;
        if (this.d == null) {
            this.d = new Application.ActivityLifecycleCallbacks() {
                public final void onActivityCreated(Activity activity, Bundle bundle) {
                    db.a(3, co.a, "onActivityCreated for activity:".concat(String.valueOf(activity)));
                    a(activity, cn.a.a);
                    synchronized (co.this) {
                        if (co.g == null) {
                            String unused = co.g = activity.getClass().getName();
                        }
                    }
                }

                public final void onActivityStarted(Activity activity) {
                    db.a(3, co.a, "onActivityStarted for activity:".concat(String.valueOf(activity)));
                    if (a(activity)) {
                        a(activity, cn.a.e);
                    }
                }

                public final void onActivityResumed(Activity activity) {
                    db.a(3, co.a, "onActivityResumed for activity:".concat(String.valueOf(activity)));
                    if (!co.e) {
                        co.a(true);
                    }
                    a(activity, cn.a.d);
                }

                public final void onActivityPaused(Activity activity) {
                    db.a(3, co.a, "onActivityPaused for activity:".concat(String.valueOf(activity)));
                    a(activity, cn.a.c);
                }

                public final void onActivityStopped(Activity activity) {
                    db.a(3, co.a, "onActivityStopped for activity:".concat(String.valueOf(activity)));
                    if (a(activity)) {
                        a(activity, cn.a.f);
                    }
                }

                public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                    db.a(3, co.a, "onActivitySaveInstanceState for activity:".concat(String.valueOf(activity)));
                    a(activity, cn.a.g);
                }

                public final void onActivityDestroyed(Activity activity) {
                    db.a(3, co.a, "onActivityDestroyed for activity:".concat(String.valueOf(activity)));
                    a(activity, cn.a.b);
                }

                private static void a(Activity activity, int i) {
                    cn cnVar = new cn();
                    cnVar.a = new WeakReference<>(activity);
                    cnVar.b = i;
                    cnVar.b();
                }

                private static boolean a(Activity activity) {
                    return !co.b.contains(activity.getClass().getSimpleName());
                }
            };
            ((Application) context).registerActivityLifecycleCallbacks(this.d);
        }
        if (this.f == null) {
            this.f = new ComponentCallbacks2() {
                public final void onConfigurationChanged(Configuration configuration) {
                }

                public final void onLowMemory() {
                }

                public final void onTrimMemory(int i) {
                    if (i == 20) {
                        co.a(false);
                    }
                }
            };
            context.registerComponentCallbacks(this.f);
        }
    }

    public static synchronized co a() {
        co coVar;
        synchronized (co.class) {
            if (c == null) {
                c = new co();
            }
            coVar = c;
        }
        return coVar;
    }

    public static synchronized void b() {
        synchronized (co.class) {
            if (c != null) {
                co coVar = c;
                Context context = ck.a().a;
                if (coVar.d != null) {
                    ((Application) context).unregisterActivityLifecycleCallbacks(coVar.d);
                    coVar.d = null;
                }
                if (coVar.f != null) {
                    context.unregisterComponentCallbacks(coVar.f);
                    coVar.f = null;
                }
            }
            c = null;
        }
    }

    public final boolean c() {
        return this.d != null;
    }

    public final synchronized String d() {
        return g;
    }

    static /* synthetic */ void a(boolean z) {
        int i;
        e = z;
        ck.a(z);
        if (e) {
            i = cp.a.a;
        } else {
            i = cp.a.b;
        }
        cw.a().a((cu) new cp(i));
    }
}
