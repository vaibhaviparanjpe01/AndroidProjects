package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.os.AsyncTask;
import com.bumptech.glide.load.Key;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

class CountryTask extends AsyncTask<Void, Void, String> {
    private static final boolean DEBUG = true;
    private static final String TAG = "CountryTask";
    private Context context;
    private OnCompletedListener onCompletedListener;

    public interface OnCompletedListener {
        void onCompleted(String str);
    }

    public CountryTask(Context context2, OnCompletedListener onCompletedListener2) {
        this.context = context2.getApplicationContext();
        this.onCompletedListener = onCompletedListener2;
    }

    public void setOnCompletedListener(OnCompletedListener onCompletedListener2) {
        this.onCompletedListener = onCompletedListener2;
    }

    /* access modifiers changed from: protected */
    public String doInBackground(Void... voidArr) {
        String countryFromCellNetwork = getCountryFromCellNetwork(this.context);
        if (countryFromCellNetwork == null || countryFromCellNetwork.length() < 2) {
            countryFromCellNetwork = getCountryFromIp();
        }
        return (countryFromCellNetwork == null || countryFromCellNetwork.length() < 2) ? getCountryFromLocale(this.context) : countryFromCellNetwork;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0026, code lost:
        if (r0 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0028, code lost:
        r3 = r0.length();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0022, code lost:
        r0 = r3.getSimCountryIso();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getCountryFromCellNetwork(android.content.Context r3) {
        /*
            if (r3 == 0) goto L_0x002d
            java.lang.String r0 = "phone"
            java.lang.Object r3 = r3.getSystemService(r0)
            android.telephony.TelephonyManager r3 = (android.telephony.TelephonyManager) r3
            if (r3 == 0) goto L_0x002d
            int r0 = r3.getPhoneType()
            r1 = 2
            switch(r0) {
                case 0: goto L_0x002d;
                case 1: goto L_0x0015;
                case 2: goto L_0x0022;
                case 3: goto L_0x002d;
                default: goto L_0x0014;
            }
        L_0x0014:
            goto L_0x002d
        L_0x0015:
            java.lang.String r0 = r3.getNetworkCountryIso()
            if (r0 == 0) goto L_0x0022
            int r2 = r0.length()
            if (r2 < r1) goto L_0x0022
            goto L_0x002e
        L_0x0022:
            java.lang.String r0 = r3.getSimCountryIso()
            if (r0 == 0) goto L_0x002e
            int r3 = r0.length()
            goto L_0x002e
        L_0x002d:
            r0 = 0
        L_0x002e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newandromo.dev849565.app936843.CountryTask.getCountryFromCellNetwork(android.content.Context):java.lang.String");
    }

    private static String getCountryFromIp() {
        InputStreamReader inputStreamReader;
        try {
            HttpURLConnection establishHttpURLConnection = DownloadUtils.establishHttpURLConnection("http://pro.ip-api.com/csv/?key=3PjN9TMSUIC5qFL&fields=countryCode", (String) null, "CountryFromIpTask (Mozilla Compatible)", 10000, 20000);
            String encoding = DownloadUtils.getEncoding(establishHttpURLConnection, Key.STRING_CHARSET_NAME);
            StringBuilder sb = new StringBuilder();
            inputStreamReader = new InputStreamReader(establishHttpURLConnection.getInputStream(), encoding);
            char[] cArr = new char[64];
            do {
                int read = inputStreamReader.read(cArr, 0, cArr.length);
                if (read != -1) {
                    sb.append(cArr, 0, read);
                } else {
                    inputStreamReader.close();
                    if (sb.length() == 2) {
                        return sb.toString();
                    }
                    return null;
                }
            } while (sb.length() < 256);
            inputStreamReader.close();
            return null;
        } catch (IOException | MalformedURLException unused) {
            return null;
        } catch (Throwable th) {
            inputStreamReader.close();
            throw th;
        }
    }

    private static String getCountryFromLocale(Context context2) {
        if (context2 == null) {
            return null;
        }
        String country = context2.getResources().getConfiguration().locale.getCountry();
        if (country == null) {
            return country;
        }
        country.length();
        return country;
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        this.onCompletedListener = null;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(String str) {
        if (!isCancelled() && this.onCompletedListener != null) {
            this.onCompletedListener.onCompleted(str);
        }
    }
}
