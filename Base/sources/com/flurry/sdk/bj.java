package com.flurry.sdk;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.flurry.android.Consent;
import com.flurry.android.FlurryAgent;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.DigestOutputStream;
import java.util.List;
import java.util.Map;

public class bj {
    private static final String b = "bj";
    public byte[] a = null;

    public bj(String str, String str2, boolean z, boolean z2, long j, long j2, List<bl> list, Map<ca, byte[]> map, Map<String, List<String>> map2, Map<String, List<String>> map3, Map<String, Map<String, String>> map4, long j3, eg egVar, boolean z3) throws IOException {
        DataOutputStream dataOutputStream;
        Throwable th;
        byte[] bArr;
        DataOutputStream dataOutputStream2;
        Throwable th2;
        int i;
        Map<String, String> map5;
        int identifier;
        eg egVar2 = egVar;
        try {
            cr crVar = new cr();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DigestOutputStream digestOutputStream = new DigestOutputStream(byteArrayOutputStream, crVar);
            dataOutputStream = new DataOutputStream(digestOutputStream);
            try {
                dataOutputStream.writeShort(35);
                dataOutputStream.writeShort(0);
                dataOutputStream.writeLong(0);
                dataOutputStream.writeShort(0);
                dataOutputStream.writeShort(3);
                dataOutputStream.writeShort(cl.b());
                dataOutputStream.writeLong(j3);
                dataOutputStream.writeUTF(str);
                dataOutputStream.writeUTF(str2);
                String str3 = (String) egVar2.a("VersionName");
                if (TextUtils.isEmpty(str3)) {
                    dataOutputStream.writeUTF("");
                } else {
                    dataOutputStream.writeUTF(str3);
                }
                Context context = ck.a().a;
                dataOutputStream.writeUTF(String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode));
                dataOutputStream.writeByte(3);
                if (da.a().b) {
                    dataOutputStream.writeByte(3);
                    db.c(b, "Agent report type: instant app");
                } else {
                    dataOutputStream.writeByte(1);
                    db.c(b, "Agent report type: main device");
                }
                dataOutputStream.writeShort(map.size());
                for (Map.Entry next : map.entrySet()) {
                    dataOutputStream.writeShort(((ca) next.getKey()).d);
                    byte[] bArr2 = (byte[]) next.getValue();
                    dataOutputStream.writeShort(bArr2.length);
                    dataOutputStream.write(bArr2);
                }
                dataOutputStream.writeByte(0);
                dataOutputStream.writeBoolean(z);
                dataOutputStream.writeBoolean(z2);
                dataOutputStream.writeLong(j);
                dataOutputStream.writeLong(j2);
                if (z3) {
                    dataOutputStream.writeByte(2);
                } else {
                    dataOutputStream.writeByte(0);
                }
                dataOutputStream.writeBoolean(((Boolean) egVar2.a("IncludeBackgroundSessionsInMetrics")).booleanValue());
                String property = System.getProperty("os.arch");
                property = TextUtils.isEmpty(property) ? "" : property;
                dataOutputStream.writeShort(8);
                dataOutputStream.writeUTF("device.model");
                dataOutputStream.writeUTF(Build.MODEL);
                dataOutputStream.writeByte(0);
                dataOutputStream.writeUTF("build.brand");
                dataOutputStream.writeUTF(Build.BRAND);
                dataOutputStream.writeByte(0);
                dataOutputStream.writeUTF("build.id");
                dataOutputStream.writeUTF(Build.ID);
                dataOutputStream.writeByte(0);
                dataOutputStream.writeUTF("version.release");
                dataOutputStream.writeUTF(Build.VERSION.RELEASE);
                dataOutputStream.writeByte(0);
                dataOutputStream.writeUTF("build.device");
                dataOutputStream.writeUTF(Build.DEVICE);
                dataOutputStream.writeByte(0);
                dataOutputStream.writeUTF("build.product");
                dataOutputStream.writeUTF(Build.PRODUCT);
                dataOutputStream.writeByte(0);
                dataOutputStream.writeUTF("proguard.build.uuid");
                br.a();
                Context context2 = ck.a().a;
                dataOutputStream.writeUTF((context2 == null || (identifier = context2.getResources().getIdentifier("com.flurry.crash.map_id", "string", context2.getPackageName())) == 0) ? "" : context2.getResources().getString(identifier));
                dataOutputStream.writeByte(0);
                dataOutputStream.writeUTF("device.arch");
                dataOutputStream.writeUTF(property);
                dataOutputStream.writeByte(0);
                dataOutputStream.writeShort(map2 != null ? map2.keySet().size() : 0);
                if (map2 != null) {
                    db.a(3, b, "sending referrer values because it exists");
                    for (Map.Entry next2 : map2.entrySet()) {
                        String str4 = b;
                        db.a(3, str4, "Referrer Entry:  " + ((String) next2.getKey()) + "=" + next2.getValue());
                        dataOutputStream.writeUTF((String) next2.getKey());
                        String str5 = b;
                        db.a(3, str5, "referrer key is :" + ((String) next2.getKey()));
                        dataOutputStream.writeShort(((List) next2.getValue()).size());
                        for (String str6 : (List) next2.getValue()) {
                            dataOutputStream.writeUTF(str6);
                            db.a(3, b, "referrer value is :".concat(String.valueOf(str6)));
                        }
                    }
                }
                String str7 = (String) egVar2.a("notificationToken");
                if (!TextUtils.isEmpty(str7)) {
                    i = 1;
                    dataOutputStream.writeBoolean(true);
                    dataOutputStream.writeUTF(str7);
                } else {
                    i = 1;
                    dataOutputStream.writeBoolean(false);
                }
                dataOutputStream.writeBoolean(((Boolean) egVar2.a("notificationsEnabled")).booleanValue());
                int size = map3 != null ? map3.keySet().size() : 0;
                db.a(3, b, "optionsMapSize is:  ".concat(String.valueOf(size)));
                dataOutputStream.writeShort(size);
                if (map3 != null) {
                    db.a(3, b, "sending launch options");
                    for (Map.Entry next3 : map3.entrySet()) {
                        String str8 = b;
                        db.a(3, str8, "Launch Options Key:  " + ((String) next3.getKey()));
                        dataOutputStream.writeUTF((String) next3.getKey());
                        dataOutputStream.writeShort(((List) next3.getValue()).size());
                        for (String str9 : (List) next3.getValue()) {
                            dataOutputStream.writeUTF(str9);
                            db.a(3, b, "Launch Options value is :".concat(String.valueOf(str9)));
                        }
                    }
                }
                int size2 = map4 != null ? map4.keySet().size() : 0;
                db.a(3, b, "numOriginAttributions is:  ".concat(String.valueOf(size)));
                dataOutputStream.writeShort(size2);
                if (map4 != null) {
                    for (Map.Entry next4 : map4.entrySet()) {
                        String str10 = b;
                        db.a(3, str10, "Origin Atttribute Key:  " + ((String) next4.getKey()));
                        dataOutputStream.writeUTF((String) next4.getKey());
                        dataOutputStream.writeShort(((Map) next4.getValue()).size());
                        String str11 = b;
                        db.a(3, str11, "Origin Attribute Map Size for " + ((String) next4.getKey()) + ":  " + ((Map) next4.getValue()).size());
                        for (Map.Entry entry : ((Map) next4.getValue()).entrySet()) {
                            String str12 = b;
                            db.a(3, str12, "Origin Atttribute for " + ((String) next4.getKey()) + ":  " + ((String) entry.getKey()) + ":" + ((String) entry.getValue()));
                            dataOutputStream.writeUTF(entry.getKey() != null ? (String) entry.getKey() : "");
                            dataOutputStream.writeUTF(entry.getValue() != null ? (String) entry.getValue() : "");
                        }
                    }
                }
                dataOutputStream.writeUTF(ej.a(ck.a().a));
                Consent flurryConsent = FlurryAgent.getFlurryConsent();
                if (flurryConsent == null) {
                    map5 = null;
                } else {
                    map5 = flurryConsent.getConsentStrings();
                }
                if (flurryConsent == null) {
                    i = 0;
                } else if (!flurryConsent.isGdprScope()) {
                    i = 2;
                }
                dataOutputStream.writeByte(i);
                if (map5 == null) {
                    dataOutputStream.writeShort(0);
                } else {
                    dataOutputStream.writeShort(map5.size());
                }
                if (map5 != null) {
                    for (Map.Entry next5 : map5.entrySet()) {
                        String str13 = b;
                        db.a(3, str13, "Consent string for " + ((String) next5.getKey()) + ": " + ((String) next5.getValue()));
                        dataOutputStream.writeUTF((String) next5.getKey());
                        dataOutputStream.writeUTF((String) next5.getValue());
                    }
                }
                dataOutputStream.writeShort(list.size());
                for (bl blVar : list) {
                    dataOutputStream.write(blVar.a);
                }
                dataOutputStream.writeShort(0);
                dataOutputStream.writeShort(0);
                digestOutputStream.on(false);
                dataOutputStream.write(crVar.a());
                dataOutputStream.close();
                bArr = byteArrayOutputStream.toByteArray();
                em.a((Closeable) dataOutputStream);
            } catch (Throwable th3) {
                th = th3;
                em.a((Closeable) dataOutputStream);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            dataOutputStream = null;
            em.a((Closeable) dataOutputStream);
            throw th;
        }
        this.a = bArr;
    }
}
