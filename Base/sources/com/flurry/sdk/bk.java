package com.flurry.sdk;

import com.flurry.sdk.eh;

public class bk extends Cdo implements eh.a {
    /* access modifiers changed from: private */
    public static final String e = "bk";
    private String f;
    private boolean g;

    public bk() {
        this((byte) 0);
    }

    private bk(byte b) {
        super("Analytics", bk.class.getSimpleName());
        this.b = "AnalyticsData_";
        eg a = eg.a();
        this.g = ((Boolean) a.a("UseHttps")).booleanValue();
        a.a("UseHttps", (eh.a) this);
        String str = e;
        db.a(4, str, "initSettings, UseHttps = " + this.g);
        String str2 = (String) a.a("ReportUrl");
        a.a("ReportUrl", (eh.a) this);
        a(str2);
        db.a(4, e, "initSettings, ReportUrl = ".concat(String.valueOf(str2)));
        b();
    }

    private void a(String str) {
        if (str != null && !str.endsWith(".do")) {
            db.a(5, e, "overriding analytics agent report URL without an endpoint, are you sure?");
        }
        this.f = str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.String r3, java.lang.Object r4) {
        /*
            r2 = this;
            int r0 = r3.hashCode()
            r1 = -239660092(0xfffffffff1b713c4, float:-1.8131089E30)
            if (r0 == r1) goto L_0x0019
            r1 = 1650629499(0x62629b7b, float:1.0450419E21)
            if (r0 == r1) goto L_0x000f
            goto L_0x0023
        L_0x000f:
            java.lang.String r0 = "ReportUrl"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0023
            r3 = 1
            goto L_0x0024
        L_0x0019:
            java.lang.String r0 = "UseHttps"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0023
            r3 = 0
            goto L_0x0024
        L_0x0023:
            r3 = -1
        L_0x0024:
            r0 = 4
            switch(r3) {
                case 0: goto L_0x0046;
                case 1: goto L_0x0031;
                default: goto L_0x0028;
            }
        L_0x0028:
            r3 = 6
            java.lang.String r4 = e
            java.lang.String r0 = "onSettingUpdate internal error!"
            com.flurry.sdk.db.a((int) r3, (java.lang.String) r4, (java.lang.String) r0)
            return
        L_0x0031:
            java.lang.String r4 = (java.lang.String) r4
            r2.a((java.lang.String) r4)
            java.lang.String r3 = e
            java.lang.String r1 = "onSettingUpdate, ReportUrl = "
            java.lang.String r4 = java.lang.String.valueOf(r4)
            java.lang.String r4 = r1.concat(r4)
            com.flurry.sdk.db.a((int) r0, (java.lang.String) r3, (java.lang.String) r4)
            return
        L_0x0046:
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r3 = r4.booleanValue()
            r2.g = r3
            java.lang.String r3 = e
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r1 = "onSettingUpdate, UseHttps = "
            r4.<init>(r1)
            boolean r1 = r2.g
            r4.append(r1)
            java.lang.String r4 = r4.toString()
            com.flurry.sdk.db.a((int) r0, (java.lang.String) r3, (java.lang.String) r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.bk.a(java.lang.String, java.lang.Object):void");
    }

    public final void a(String str, String str2, final int i) {
        ck.a().b(new eo() {
            public final void a() {
                if (i == 200) {
                    x.a();
                    bo b2 = x.b();
                    if (b2 != null) {
                        b2.j = true;
                    }
                }
            }
        });
        super.a(str, str2, i);
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [byte[], RequestObjectType] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(byte[] r7, final java.lang.String r8, final java.lang.String r9) {
        /*
            r6 = this;
            java.lang.String r0 = r6.f
            if (r0 == 0) goto L_0x0007
            java.lang.String r0 = r6.f
            goto L_0x0010
        L_0x0007:
            boolean r0 = r6.g
            if (r0 == 0) goto L_0x000e
            java.lang.String r0 = "https://data.flurry.com/aap.do"
            goto L_0x0010
        L_0x000e:
            java.lang.String r0 = "http://data.flurry.com/aap.do"
        L_0x0010:
            long r1 = java.lang.System.currentTimeMillis()
            java.lang.String r1 = java.lang.Long.toString(r1)
            r2 = 4
            java.lang.String r3 = e
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "FlurryDataSender: id = "
            r4.<init>(r5)
            r4.append(r8)
            java.lang.String r5 = " to "
            r4.append(r5)
            r4.append(r0)
            java.lang.String r5 = " at "
            r4.append(r5)
            r4.append(r1)
            java.lang.String r4 = r4.toString()
            com.flurry.sdk.db.a((int) r2, (java.lang.String) r3, (java.lang.String) r4)
            com.flurry.sdk.di r2 = new com.flurry.sdk.di
            r2.<init>()
            r2.g = r0
            r0 = 100000(0x186a0, float:1.4013E-40)
            r2.u = r0
            com.flurry.sdk.dk$a r0 = com.flurry.sdk.dk.a.kPost
            r2.h = r0
            java.lang.String r0 = "Content-Type"
            java.lang.String r3 = "application/octet-stream"
            r2.a(r0, r3)
            java.lang.String r0 = "X-Flurry-Sdk-Clock"
            r2.a(r0, r1)
            com.flurry.sdk.ds r0 = new com.flurry.sdk.ds
            r0.<init>()
            r2.c = r0
            r2.b = r7
            com.flurry.sdk.bk$1 r7 = new com.flurry.sdk.bk$1
            r7.<init>(r8, r9)
            r2.a = r7
            com.flurry.sdk.cg r7 = com.flurry.sdk.cg.a()
            r7.a((java.lang.Object) r6, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.bk.a(byte[], java.lang.String, java.lang.String):void");
    }
}
