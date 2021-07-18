package com.flurry.sdk;

import android.content.Context;
import com.flurry.sdk.eh;
import java.lang.Thread;
import java.util.Map;

public class ev implements df, eh.a, Thread.UncaughtExceptionHandler {
    private static final String a = "ev";
    private boolean b;

    public void init(Context context) {
        eg a2 = eg.a();
        this.b = ((Boolean) a2.a("CaptureUncaughtExceptions")).booleanValue();
        a2.a("CaptureUncaughtExceptions", (eh.a) this);
        String str = a;
        db.a(4, str, "initSettings, CrashReportingEnabled = " + this.b);
        ew a3 = ew.a();
        synchronized (a3.b) {
            a3.b.put(this, (Object) null);
        }
    }

    public void destroy() {
        ew.b();
        eg.a().b("CaptureUncaughtExceptions", this);
    }

    public final void a(String str, Object obj) {
        if (str.equals("CaptureUncaughtExceptions")) {
            this.b = ((Boolean) obj).booleanValue();
            String str2 = a;
            db.a(4, str2, "onSettingUpdate, CrashReportingEnabled = " + this.b);
            return;
        }
        db.a(6, a, "onSettingUpdate internal error!");
    }

    public void uncaughtException(Thread thread, Throwable th) {
        th.printStackTrace();
        if (this.b) {
            String str = "";
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null && stackTrace.length > 0) {
                StringBuilder sb = new StringBuilder();
                if (th.getMessage() != null) {
                    sb.append(" (");
                    sb.append(th.getMessage());
                    sb.append(")\n");
                }
                str = sb.toString();
            } else if (th.getMessage() != null) {
                str = th.getMessage();
            }
            x.a().a("uncaught", str, th, (Map<String, String>) null);
        }
        ed.a().c();
        bw.a().f();
    }
}
