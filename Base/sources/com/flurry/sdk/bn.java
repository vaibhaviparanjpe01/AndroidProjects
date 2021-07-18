package com.flurry.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.android.vending.billing.IInAppBillingService;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class bn {
    private static final String a = "bn";
    /* access modifiers changed from: private */
    public static Object b = new Object();
    /* access modifiers changed from: private */
    public static List<b> c = new ArrayList();
    /* access modifiers changed from: private */
    public static IInAppBillingService d;
    /* access modifiers changed from: private */
    public static ServiceConnection e;

    public static abstract class a {
        public abstract void a(int i, c cVar);
    }

    public static void a(final Context context, final String str, final a aVar) {
        try {
            Class.forName("com.android.vending.billing.IInAppBillingService");
            db.a(3, a, "Google play billing library is available");
            AnonymousClass1 r0 = new b() {
                public final void a(int i, IInAppBillingService iInAppBillingService) {
                    if (i == 0) {
                        c a2 = bn.b(iInAppBillingService, context, "inapp", str);
                        if (a2 == null) {
                            a2 = bn.b(iInAppBillingService, context, "subs", str);
                        }
                        aVar.a(i, a2);
                        return;
                    }
                    aVar.a(i, (c) null);
                }
            };
            Boolean bool = Boolean.FALSE;
            synchronized (b) {
                if (e == null) {
                    e = new ServiceConnection() {
                        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                            synchronized (bn.b) {
                                IInAppBillingService unused = bn.d = IInAppBillingService.Stub.asInterface(iBinder);
                                for (b b : bn.c) {
                                    b.b(0, bn.d);
                                }
                                bn.c.clear();
                            }
                        }

                        public final void onServiceDisconnected(ComponentName componentName) {
                            synchronized (bn.b) {
                                ServiceConnection unused = bn.e = null;
                                IInAppBillingService unused2 = bn.d = null;
                                for (b b : bn.c) {
                                    b.b(1, (IInAppBillingService) null);
                                }
                                bn.c.clear();
                            }
                        }
                    };
                    bool = Boolean.TRUE;
                }
                if (d == null) {
                    c.add(r0);
                } else {
                    r0.b(0, d);
                }
                if (bool.booleanValue()) {
                    Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
                    intent.setPackage("com.android.vending");
                    List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
                    if (queryIntentServices == null || queryIntentServices.isEmpty()) {
                        r0.b(1, (IInAppBillingService) null);
                        e = null;
                    } else {
                        context.bindService(intent, e, 1);
                    }
                }
            }
        } catch (ClassNotFoundException e2) {
            db.b(a, "Could not find google play billing library");
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static c b(IInAppBillingService iInAppBillingService, Context context, String str, String str2) {
        Bundle bundle = new Bundle();
        ArrayList arrayList = new ArrayList();
        arrayList.add(str2);
        bundle.putStringArrayList("ITEM_ID_LIST", arrayList);
        try {
            Bundle skuDetails = iInAppBillingService.getSkuDetails(3, context.getPackageName(), str, bundle);
            if (skuDetails.containsKey("DETAILS_LIST")) {
                ArrayList<String> stringArrayList = skuDetails.getStringArrayList("DETAILS_LIST");
                if (stringArrayList.size() > 0) {
                    return new c(str, stringArrayList.get(0));
                }
            }
            return null;
        } catch (RemoteException e2) {
            db.a(a, "RemoteException getting SKU Details", (Throwable) e2);
            return null;
        } catch (JSONException e3) {
            db.a(a, "JSONException parsing SKU Details", (Throwable) e3);
            return null;
        }
    }

    static abstract class b {
        public abstract void a(int i, IInAppBillingService iInAppBillingService);

        private b() {
        }

        /* synthetic */ b(byte b) {
            this();
        }

        public final void b(final int i, final IInAppBillingService iInAppBillingService) {
            new Thread(new Runnable() {
                public final void run() {
                    b.this.a(i, iInAppBillingService);
                }
            }).start();
        }
    }

    public static class c {
        public final String a;
        public final long b;
        public final String c;
        public final String d;
        private final String e;
        private final String f;
        private final String g;
        private final String h;
        private final String i;

        public c(String str, String str2) throws JSONException {
            this.e = str;
            this.i = str2;
            JSONObject jSONObject = new JSONObject(this.i);
            this.f = jSONObject.optString("productId");
            this.a = jSONObject.optString("type");
            this.g = jSONObject.optString("price");
            this.b = jSONObject.optLong("price_amount_micros");
            this.c = jSONObject.optString("price_currency_code");
            this.d = jSONObject.optString("title");
            this.h = jSONObject.optString("description");
        }

        public final String toString() {
            return "SkuDetails:" + this.i;
        }
    }
}
