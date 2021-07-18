package com.flurry.sdk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import com.flurry.android.FlurryPrivacySession;
import com.flurry.sdk.di;
import com.flurry.sdk.dk;
import com.flurry.sdk.ex;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class fa {
    /* access modifiers changed from: private */
    public static final String a = "fa";
    private static fa b = new fa();

    private fa() {
    }

    /* access modifiers changed from: private */
    public static void b(Context context, FlurryPrivacySession.a aVar) {
        Intent intent = new Intent("android.intent.action.VIEW", aVar.a);
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public static void a(final FlurryPrivacySession.Request request) {
        ck.a().b(new eo() {
            public final void a() {
                Map b2 = fa.b(request);
                di diVar = new di();
                diVar.g = "https://api.login.yahoo.com/oauth2/device_session";
                diVar.h = dk.a.kPost;
                diVar.a("Content-Type", "application/json");
                diVar.b = new JSONObject(b2).toString();
                diVar.d = new dx();
                diVar.c = new dx();
                diVar.a = new di.a<String, String>() {
                    public final /* synthetic */ void a(di diVar, Object obj) {
                        String str = (String) obj;
                        try {
                            int i = diVar.q;
                            if (i == 200) {
                                JSONObject jSONObject = new JSONObject(str);
                                fa.a(fa.this, new FlurryPrivacySession.a(jSONObject.getString("device_session_id"), jSONObject.getLong("expires_in"), request));
                                if (request.callback != null) {
                                    request.callback.success();
                                    return;
                                }
                                return;
                            }
                            db.e(fa.a, "Error in getting privacy dashboard url. Error code = ".concat(String.valueOf(i)));
                            if (request.callback != null) {
                                request.callback.failure();
                            }
                        } catch (JSONException e) {
                            db.b(fa.a, "Error in getting privacy dashboard url. ", (Throwable) e);
                            if (request.callback != null) {
                                request.callback.failure();
                            }
                        }
                    }
                };
                cg.a().a((Object) fa.this, diVar);
            }
        });
    }

    static /* synthetic */ Map b(FlurryPrivacySession.Request request) {
        HashMap hashMap = new HashMap();
        hashMap.put("device_verifier", request.verifier);
        HashMap hashMap2 = new HashMap();
        String d = bs.a().d();
        if (d != null) {
            hashMap2.put("gpaid", d);
        }
        String str = bs.a().b;
        if (str != null) {
            hashMap2.put("andid", str);
        }
        hashMap.putAll(hashMap2);
        HashMap hashMap3 = new HashMap();
        byte[] e = bs.a().e();
        if (e != null) {
            hashMap3.put("flurry_guid", em.a(e));
        }
        hashMap3.put("flurry_project_api_key", ck.a().b);
        hashMap.putAll(hashMap3);
        Context context = request.context;
        HashMap hashMap4 = new HashMap();
        hashMap4.put("src", "flurryandroidsdk");
        hashMap4.put("srcv", "11.6.0");
        hashMap4.put("appsrc", context.getPackageName());
        cb.a();
        hashMap4.put("appsrcv", cb.a(context));
        hashMap.putAll(hashMap4);
        return hashMap;
    }

    static /* synthetic */ void a(fa faVar, final FlurryPrivacySession.a aVar) {
        Context context = ck.a().a;
        if (ex.a(context)) {
            ex.a(context, new CustomTabsIntent.Builder().setShowTitle(true).build(), Uri.parse(aVar.a.toString()), new ex.a() {
                public final void a(Context context) {
                    fa.b(context, aVar);
                }
            });
        } else {
            b(context, aVar);
        }
    }
}
