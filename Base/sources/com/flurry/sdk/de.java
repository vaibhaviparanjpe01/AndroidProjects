package com.flurry.sdk;

import android.content.Context;
import android.os.Build;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class de {
    private static final String a = "de";
    private static final Map<Class<? extends df>, dc> b = new LinkedHashMap();
    private static List<df> d = new ArrayList();
    private static List<String> e;
    private final Map<Class<? extends df>, df> c = new LinkedHashMap();

    static {
        ArrayList arrayList = new ArrayList();
        e = arrayList;
        arrayList.add("com.flurry.android.marketing.core.FlurryMarketingCoreModule");
        e.add("com.flurry.android.marketing.FlurryMarketingModule");
        e.add("com.flurry.android.config.killswitch.FlurryKillSwitchModule");
        e.add("com.flurry.android.nativecrash.FlurryNativeCrashModule");
        e.add("com.flurry.android.FlurryAdModule");
        e.add("com.flurry.android.ymadlite.YahooAdModule");
    }

    public static void a(Class<? extends df> cls) {
        if (cls != null) {
            synchronized (b) {
                b.put(cls, new dc(cls));
            }
        }
    }

    public static void a(df dfVar) {
        if (dfVar != null) {
            boolean z = false;
            Iterator<df> it = d.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().getClass().getSimpleName().equals(dfVar.getClass().getSimpleName())) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (!z) {
                d.add(dfVar);
                return;
            }
            String str = a;
            db.a(3, str, dfVar + " has been register already as addOn module");
            return;
        }
        db.e(a, "Module is null, cannot register it");
    }

    public static void a() {
        synchronized (b) {
            b.clear();
        }
    }

    public final synchronized void a(Context context) {
        ArrayList<dc> arrayList;
        if (context == null) {
            db.a(5, a, "Null context.");
            return;
        }
        synchronized (b) {
            arrayList = new ArrayList<>(b.values());
        }
        for (dc dcVar : arrayList) {
            try {
                if (dcVar.a != null && Build.VERSION.SDK_INT >= dcVar.b) {
                    df dfVar = (df) dcVar.a.newInstance();
                    dfVar.init(context);
                    this.c.put(dcVar.a, dfVar);
                }
            } catch (Exception e2) {
                String str = a;
                db.a(5, str, "Flurry Module for class " + dcVar.a + " is not available:", e2);
            }
        }
        for (df next : d) {
            try {
                next.init(context);
                this.c.put(next.getClass(), next);
            } catch (dd e3) {
                db.b(a, e3.getMessage());
            }
        }
        ed.a().a(context);
        co.a();
    }

    public final synchronized void b() {
        co.b();
        ed.b();
        List<df> c2 = c();
        for (int size = c2.size() - 1; size >= 0; size--) {
            try {
                this.c.remove(c2.get(size).getClass()).destroy();
            } catch (Exception e2) {
                db.a(5, a, "Error destroying module:", e2);
            }
        }
    }

    private List<df> c() {
        ArrayList arrayList;
        synchronized (this.c) {
            arrayList = new ArrayList(this.c.values());
        }
        return arrayList;
    }

    public final df b(Class<? extends df> cls) {
        df dfVar;
        if (cls == null) {
            return null;
        }
        synchronized (this.c) {
            dfVar = this.c.get(cls);
        }
        if (dfVar != null) {
            return dfVar;
        }
        throw new IllegalStateException("Module was not registered/initialized. ".concat(String.valueOf(cls)));
    }

    public static boolean a(String str) {
        return e.contains(str);
    }
}
