package com.flurry.sdk;

import java.io.PrintStream;
import java.io.PrintWriter;

public abstract class eo implements Runnable {
    private static final String a = "eo";
    private PrintStream b;
    private PrintWriter c;

    public abstract void a() throws Exception;

    public final void run() {
        try {
            a();
        } catch (Throwable th) {
            if (this.b != null) {
                th.printStackTrace(this.b);
            } else if (this.c != null) {
                th.printStackTrace(this.c);
            } else {
                th.printStackTrace();
            }
            db.a(6, a, "", th);
        }
    }
}
