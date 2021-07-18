package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.os.Build;
import com.bumptech.glide.load.Key;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;

final class DownloadUtils {
    private static final String TAG = "DownloadUtils";

    public interface ProgressListener {
        int getStep();

        boolean onProgress(long j);

        void onTotal(long j);
    }

    DownloadUtils() {
    }

    private static void disableConnectionReuseIfNecessary() {
        if (Build.VERSION.SDK_INT < 8) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    static void enableHttpResponseCache(Context context) {
        try {
            File file = new File(context.getCacheDir(), "http");
            Class.forName("android.net.http.HttpResponseCache").getMethod("install", new Class[]{File.class, Long.TYPE}).invoke((Object) null, new Object[]{file, 10485760L});
        } catch (Exception unused) {
        }
    }

    static URLConnection establishURLConnection(String str) throws MalformedURLException, IOException {
        return establishURLConnection(str, "*/*", (String) null, 10000, 30000);
    }

    static HttpURLConnection establishHttpURLConnection(String str) throws MalformedURLException, IOException {
        return establishHttpURLConnection(str, "*/*", (String) null, 10000, 30000);
    }

    static URLConnection establishURLConnection(String str, String str2, String str3, int i, int i2) throws MalformedURLException, IOException {
        disableConnectionReuseIfNecessary();
        URLConnection openConnection = new URL(str).openConnection();
        openConnection.setRequestProperty("Connection", "close");
        if (str3 != null) {
            openConnection.setRequestProperty("User-Agent", str3);
        }
        if (str2 != null) {
            openConnection.setRequestProperty("Accept", str2);
        }
        openConnection.setUseCaches(false);
        openConnection.setDoInput(true);
        openConnection.setConnectTimeout(i);
        openConnection.setReadTimeout(i2);
        openConnection.connect();
        return openConnection;
    }

    static HttpURLConnection establishHttpURLConnection(String str, String str2, String str3, int i, int i2) throws MalformedURLException, IOException {
        disableConnectionReuseIfNecessary();
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestProperty("Connection", "close");
        if (str3 != null) {
            httpURLConnection.setRequestProperty("User-Agent", str3);
        }
        if (str2 != null) {
            httpURLConnection.setRequestProperty("Accept", str2);
        }
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setConnectTimeout(i);
        httpURLConnection.setReadTimeout(i2);
        httpURLConnection.connect();
        return httpURLConnection;
    }

    static boolean hasMimeType(URLConnection uRLConnection, Collection<String> collection) throws IOException {
        String contentType;
        if (uRLConnection == null || collection == null || collection.size() <= 0 || (contentType = uRLConnection.getContentType()) == null) {
            return false;
        }
        String lowerCase = contentType.toLowerCase();
        int indexOf = lowerCase.indexOf(59);
        if (indexOf != -1) {
            lowerCase = lowerCase.substring(0, indexOf);
            lowerCase.trim();
        }
        for (String equals : collection) {
            if (lowerCase.equals(equals)) {
                return true;
            }
        }
        return false;
    }

    static String getEncoding(URLConnection uRLConnection, String str) throws IOException {
        String str2;
        if (uRLConnection != null) {
            String contentEncoding = uRLConnection.getContentEncoding();
            if (contentEncoding != null) {
                return contentEncoding;
            }
            String contentType = uRLConnection.getContentType();
            if (contentType != null) {
                String[] split = contentType.split(";");
                int length = split.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    String trim = split[i].trim();
                    if (trim.toLowerCase().startsWith("charset=")) {
                        str2 = trim.substring("charset=".length());
                        break;
                    }
                    i++;
                }
                if (str2 != null && !"".equals(str2)) {
                    return str2;
                }
            }
            str2 = contentEncoding;
            return str2;
        }
        return str;
    }

    static ByteArrayInputStream downloadToByteArrayInputStreamFixingXmlEntities(URLConnection uRLConnection, int i) throws IOException {
        return downloadToByteArrayInputStreamFixingXmlEntities(uRLConnection, i, getEncoding(uRLConnection, Key.STRING_CHARSET_NAME));
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x002e A[SYNTHETIC, Splitter:B:15:0x002e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.io.ByteArrayInputStream downloadToByteArrayInputStreamFixingXmlEntities(java.net.URLConnection r4, int r5, java.lang.String r6) throws java.io.IOException {
        /*
            r0 = 0
            if (r4 == 0) goto L_0x0037
            com.newandromo.dev849565.app936843.EntityFixingReader r1 = new com.newandromo.dev849565.app936843.EntityFixingReader     // Catch:{ all -> 0x002b }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ all -> 0x002b }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ all -> 0x002b }
            java.io.InputStream r4 = r4.getInputStream()     // Catch:{ all -> 0x002b }
            r3.<init>(r4, r6)     // Catch:{ all -> 0x002b }
            r2.<init>(r3)     // Catch:{ all -> 0x002b }
            r1.<init>(r2)     // Catch:{ all -> 0x002b }
            java.io.ByteArrayInputStream r4 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x0028 }
            byte[] r5 = readBytes(r1, r5, r6)     // Catch:{ all -> 0x0028 }
            r4.<init>(r5)     // Catch:{ all -> 0x0028 }
            r1.close()     // Catch:{ IOException -> 0x0023 }
            goto L_0x0027
        L_0x0023:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0027:
            return r4
        L_0x0028:
            r4 = move-exception
            r0 = r1
            goto L_0x002c
        L_0x002b:
            r4 = move-exception
        L_0x002c:
            if (r0 == 0) goto L_0x0036
            r0.close()     // Catch:{ IOException -> 0x0032 }
            goto L_0x0036
        L_0x0032:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0036:
            throw r4
        L_0x0037:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newandromo.dev849565.app936843.DownloadUtils.downloadToByteArrayInputStreamFixingXmlEntities(java.net.URLConnection, int, java.lang.String):java.io.ByteArrayInputStream");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x001f A[SYNTHETIC, Splitter:B:16:0x001f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.io.ByteArrayInputStream downloadToByteArrayInputStream(java.net.URLConnection r1, int r2) throws java.io.IOException {
        /*
            r0 = 0
            if (r1 == 0) goto L_0x0026
            java.io.InputStream r1 = r1.getInputStream()     // Catch:{ all -> 0x001b }
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x0019 }
            byte[] r2 = readBytes(r1, r2)     // Catch:{ all -> 0x0019 }
            r0.<init>(r2)     // Catch:{ all -> 0x0019 }
            if (r1 == 0) goto L_0x0018
            r1.close()     // Catch:{ IOException -> 0x0016 }
            goto L_0x0018
        L_0x0016:
            r1 = move-exception
            throw r1
        L_0x0018:
            return r0
        L_0x0019:
            r2 = move-exception
            goto L_0x001d
        L_0x001b:
            r2 = move-exception
            r1 = r0
        L_0x001d:
            if (r1 == 0) goto L_0x0025
            r1.close()     // Catch:{ IOException -> 0x0023 }
            goto L_0x0025
        L_0x0023:
            r1 = move-exception
            throw r1
        L_0x0025:
            throw r2
        L_0x0026:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newandromo.dev849565.app936843.DownloadUtils.downloadToByteArrayInputStream(java.net.URLConnection, int):java.io.ByteArrayInputStream");
    }

    static byte[] readBytes(Reader reader, int i, String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (reader != null) {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream, str);
            char[] cArr = new char[8192];
            while (true) {
                int read = reader.read(cArr);
                if (read == -1) {
                    break;
                }
                outputStreamWriter.write(cArr, 0, read);
                if (i > 0 && byteArrayOutputStream.size() >= i) {
                    break;
                }
            }
            outputStreamWriter.flush();
        }
        return byteArrayOutputStream.toByteArray();
    }

    static byte[] readBytes(InputStream inputStream, int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, read);
            if (i > 0 && byteArrayOutputStream.size() >= i) {
                break;
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    static void downloadToFile(String str, File file, ProgressListener progressListener) throws IOException {
        downloadToFile(str, file, "*/*", (Collection<String>) null, progressListener);
    }

    static void downloadToFile(String str, File file, String str2, Collection<String> collection, ProgressListener progressListener) throws IOException {
        if (str == null) {
            throw new IllegalArgumentException("spec cannot be null");
        } else if (file != null) {
            HttpURLConnection establishHttpURLConnection = establishHttpURLConnection(str, str2, (String) null, 30000, 60000);
            if (establishHttpURLConnection == null) {
                return;
            }
            if (collection == null || !hasMimeType(establishHttpURLConnection, collection)) {
                downloadToFile(establishHttpURLConnection, file, progressListener);
                return;
            }
            throw new FileNotFoundException("Unacceptable content-type.");
        } else {
            throw new IllegalArgumentException("toFile cannot be null");
        }
    }

    static void downloadToFile(String str, File file, String str2, String str3, int i, int i2, Collection<String> collection, Collection<String> collection2, ProgressListener progressListener) throws IOException {
        if (str == null) {
            throw new IllegalArgumentException("spec cannot be null");
        } else if (file != null) {
            HttpURLConnection establishHttpURLConnection = establishHttpURLConnection(str, str2, str3, i, i2);
            if (establishHttpURLConnection == null) {
                return;
            }
            if (collection != null && !hasMimeType(establishHttpURLConnection, collection)) {
                throw new FileNotFoundException("Unacceptable content-type.");
            } else if (collection2 == null || !hasMimeType(establishHttpURLConnection, collection2)) {
                downloadToFile(establishHttpURLConnection, file, progressListener);
            } else {
                throw new FileNotFoundException("Unacceptable content-type.");
            }
        } else {
            throw new IllegalArgumentException("toFile cannot be null");
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.io.BufferedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.io.BufferedOutputStream} */
    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v3 */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0092 A[SYNTHETIC, Splitter:B:47:0x0092] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0097 A[SYNTHETIC, Splitter:B:51:0x0097] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void downloadToFile(java.net.HttpURLConnection r13, java.io.File r14, com.newandromo.dev849565.app936843.DownloadUtils.ProgressListener r15) throws java.io.IOException {
        /*
            if (r13 == 0) goto L_0x00af
            if (r14 == 0) goto L_0x00a7
            r0 = 0
            if (r13 == 0) goto L_0x009b
            int r1 = r13.getContentLength()     // Catch:{ all -> 0x008e }
            int r2 = r13.getResponseCode()     // Catch:{ all -> 0x008e }
            if (r1 <= 0) goto L_0x0086
            if (r15 == 0) goto L_0x0017
            long r3 = (long) r1     // Catch:{ all -> 0x008e }
            r15.onTotal(r3)     // Catch:{ all -> 0x008e }
        L_0x0017:
            r1 = 400(0x190, float:5.6E-43)
            if (r2 >= r1) goto L_0x007e
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ all -> 0x008e }
            java.io.InputStream r13 = r13.getInputStream()     // Catch:{ all -> 0x008e }
            r1.<init>(r13)     // Catch:{ all -> 0x008e }
            java.io.BufferedOutputStream r13 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x007c }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ all -> 0x007c }
            r2.<init>(r14)     // Catch:{ all -> 0x007c }
            r13.<init>(r2)     // Catch:{ all -> 0x007c }
            r14 = 8192(0x2000, float:1.14794E-41)
            byte[] r14 = new byte[r14]     // Catch:{ all -> 0x0078 }
            r0 = 0
            r2 = -1
            r3 = 0
            if (r15 == 0) goto L_0x005d
            int r5 = r15.getStep()     // Catch:{ all -> 0x0078 }
            long r5 = (long) r5     // Catch:{ all -> 0x0078 }
            r7 = r3
            r9 = r5
        L_0x003f:
            int r11 = r1.read(r14)     // Catch:{ all -> 0x0078 }
            if (r11 == r2) goto L_0x0055
            r13.write(r14, r0, r11)     // Catch:{ all -> 0x0078 }
            long r11 = (long) r11     // Catch:{ all -> 0x0078 }
            long r7 = r7 + r11
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 < 0) goto L_0x003f
            r15.onProgress(r7)     // Catch:{ all -> 0x0078 }
            r9 = 0
            long r9 = r7 + r5
            goto L_0x003f
        L_0x0055:
            int r14 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r14 <= 0) goto L_0x006a
            r15.onProgress(r7)     // Catch:{ all -> 0x0078 }
            goto L_0x006a
        L_0x005d:
            r7 = r3
        L_0x005e:
            int r15 = r1.read(r14)     // Catch:{ all -> 0x0078 }
            if (r15 == r2) goto L_0x006a
            r13.write(r14, r0, r15)     // Catch:{ all -> 0x0078 }
            long r5 = (long) r15     // Catch:{ all -> 0x0078 }
            long r7 = r7 + r5
            goto L_0x005e
        L_0x006a:
            int r14 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r14 <= 0) goto L_0x0070
            r0 = r1
            goto L_0x009c
        L_0x0070:
            java.io.FileNotFoundException r14 = new java.io.FileNotFoundException     // Catch:{ all -> 0x0078 }
            java.lang.String r15 = "Empty file (no data retrieved)."
            r14.<init>(r15)     // Catch:{ all -> 0x0078 }
            throw r14     // Catch:{ all -> 0x0078 }
        L_0x0078:
            r14 = move-exception
            r0 = r13
            r13 = r14
            goto L_0x0090
        L_0x007c:
            r13 = move-exception
            goto L_0x0090
        L_0x007e:
            java.io.FileNotFoundException r13 = new java.io.FileNotFoundException     // Catch:{ all -> 0x008e }
            java.lang.String r14 = "Invalid response code."
            r13.<init>(r14)     // Catch:{ all -> 0x008e }
            throw r13     // Catch:{ all -> 0x008e }
        L_0x0086:
            java.io.FileNotFoundException r13 = new java.io.FileNotFoundException     // Catch:{ all -> 0x008e }
            java.lang.String r14 = "The content-length indicates an empty file or a stream."
            r13.<init>(r14)     // Catch:{ all -> 0x008e }
            throw r13     // Catch:{ all -> 0x008e }
        L_0x008e:
            r13 = move-exception
            r1 = r0
        L_0x0090:
            if (r1 == 0) goto L_0x0095
            r1.close()     // Catch:{ IOException -> 0x0095 }
        L_0x0095:
            if (r0 == 0) goto L_0x009a
            r0.close()     // Catch:{ IOException -> 0x009a }
        L_0x009a:
            throw r13
        L_0x009b:
            r13 = r0
        L_0x009c:
            if (r0 == 0) goto L_0x00a1
            r0.close()     // Catch:{ IOException -> 0x00a1 }
        L_0x00a1:
            if (r13 == 0) goto L_0x00a6
            r13.close()     // Catch:{ IOException -> 0x00a6 }
        L_0x00a6:
            return
        L_0x00a7:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "toFile cannot be null"
            r13.<init>(r14)
            throw r13
        L_0x00af:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "connection cannot be null"
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newandromo.dev849565.app936843.DownloadUtils.downloadToFile(java.net.HttpURLConnection, java.io.File, com.newandromo.dev849565.app936843.DownloadUtils$ProgressListener):void");
    }
}
