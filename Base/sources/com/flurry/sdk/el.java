package com.flurry.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public final class el {
    private static String a = "el";

    @TargetApi(21)
    public static File a() {
        File file;
        Context context = ck.a().a;
        if (em.a(21)) {
            file = context.getNoBackupFilesDir();
        } else {
            file = new File(context.getFilesDir().getPath() + File.separator + "no_backup");
        }
        return new File(file.getPath() + File.separator + ".flurryNoBackup");
    }

    public static boolean a(File file) {
        if (file == null || file.getAbsoluteFile() == null) {
            return false;
        }
        File parentFile = file.getParentFile();
        if (parentFile == null || parentFile.mkdirs() || parentFile.isDirectory()) {
            return true;
        }
        db.a(6, a, "Unable to create persistent dir: ".concat(String.valueOf(parentFile)));
        return false;
    }

    public static boolean b(File file) {
        if (file == null || !file.isDirectory()) {
            return false;
        }
        for (String file2 : file.list()) {
            if (!b(new File(file, file2))) {
                return false;
            }
        }
        return file.delete();
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String c(java.io.File r6) {
        /*
            r0 = 4
            r1 = 0
            if (r6 == 0) goto L_0x0064
            boolean r2 = r6.exists()
            if (r2 != 0) goto L_0x000b
            goto L_0x0064
        L_0x000b:
            java.lang.String r2 = a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Loading persistent data: "
            r3.<init>(r4)
            java.lang.String r4 = r6.getAbsolutePath()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.flurry.sdk.db.a((int) r0, (java.lang.String) r2, (java.lang.String) r3)
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0049, all -> 0x0046 }
            r0.<init>(r6)     // Catch:{ Throwable -> 0x0049, all -> 0x0046 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0044 }
            r6.<init>()     // Catch:{ Throwable -> 0x0044 }
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch:{ Throwable -> 0x0044 }
        L_0x0030:
            int r3 = r0.read(r2)     // Catch:{ Throwable -> 0x0044 }
            if (r3 <= 0) goto L_0x0040
            java.lang.String r4 = new java.lang.String     // Catch:{ Throwable -> 0x0044 }
            r5 = 0
            r4.<init>(r2, r5, r3)     // Catch:{ Throwable -> 0x0044 }
            r6.append(r4)     // Catch:{ Throwable -> 0x0044 }
            goto L_0x0030
        L_0x0040:
            com.flurry.sdk.em.a((java.io.Closeable) r0)
            goto L_0x0057
        L_0x0044:
            r6 = move-exception
            goto L_0x004b
        L_0x0046:
            r6 = move-exception
            r0 = r1
            goto L_0x0060
        L_0x0049:
            r6 = move-exception
            r0 = r1
        L_0x004b:
            r2 = 6
            java.lang.String r3 = a     // Catch:{ all -> 0x005f }
            java.lang.String r4 = "Error when loading persistent file"
            com.flurry.sdk.db.a(r2, r3, r4, r6)     // Catch:{ all -> 0x005f }
            com.flurry.sdk.em.a((java.io.Closeable) r0)
            r6 = r1
        L_0x0057:
            if (r6 == 0) goto L_0x005e
            java.lang.String r6 = r6.toString()
            return r6
        L_0x005e:
            return r1
        L_0x005f:
            r6 = move-exception
        L_0x0060:
            com.flurry.sdk.em.a((java.io.Closeable) r0)
            throw r6
        L_0x0064:
            java.lang.String r6 = a
            java.lang.String r2 = "Persistent file doesn't exist."
            com.flurry.sdk.db.a((int) r0, (java.lang.String) r6, (java.lang.String) r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.el.c(java.io.File):java.lang.String");
    }

    public static void a(File file, String str) {
        if (file == null) {
            db.a(4, a, "No persistent file specified.");
        } else if (str == null) {
            String str2 = a;
            db.a(4, str2, "No data specified; deleting persistent file: " + file.getAbsolutePath());
            file.delete();
        } else {
            String str3 = a;
            db.a(4, str3, "Writing persistent data: " + file.getAbsolutePath());
            FileOutputStream fileOutputStream = null;
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    fileOutputStream2.write(str.getBytes());
                    em.a((Closeable) fileOutputStream2);
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    em.a((Closeable) fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                db.a(6, a, "Error writing persistent file", th);
                em.a((Closeable) fileOutputStream);
            }
        }
    }

    public static String[] a(File file, final Pattern pattern) {
        if (!file.exists()) {
            return new String[0];
        }
        String[] list = file.list(new FilenameFilter() {
            public final boolean accept(File file, String str) {
                return pattern.matcher(str).matches();
            }
        });
        return list == null ? new String[0] : list;
    }
}
