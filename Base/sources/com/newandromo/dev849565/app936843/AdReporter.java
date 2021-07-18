package com.newandromo.dev849565.app936843;

import android.os.Handler;
import android.util.Base64;
import com.bumptech.glide.load.Key;
import com.newandromo.dev849565.app936843.AndromoApplication;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;

public class AdReporter implements AndromoApplication.AppState {
    private static AdReporter instance;
    private final String PATH_PING = "/v3/ping/";
    private final String PATH_REPORT = "/v3/event/";
    private final long PING_INTERVAL = 1800000;
    private final String SECRET = "${andromo.ad.reporter.secret}";
    private final String SERVER_URL = "${andromo.ad.reporter.url}";
    private final String TAG = "AdReporter";
    private final String UUID = "${andromo.ad.reporter.uuid}";
    /* access modifiers changed from: private */
    public Map<String, Boolean> allowedNetworks = new HashMap();
    /* access modifiers changed from: private */
    public boolean canShowAds;
    /* access modifiers changed from: private */
    public boolean foreground;
    /* access modifiers changed from: private */
    public long interstitialDelay;
    private boolean launched;
    private Runnable pingRoutine = new Runnable() {
        public void run() {
            try {
                StringBuilder sb = new StringBuilder();
                new Formatter(sb).format("%s%s?_v=3&a=%s&l=%s&f=%s&_cb=%s", new Object[]{"${andromo.ad.reporter.url}", "/v3/ping/", "${andromo.ad.reporter.uuid}", Integer.valueOf(AdReporter.this.getLaunched()), Integer.valueOf(AdReporter.this.getForeground()), Long.valueOf(System.currentTimeMillis())});
                JSONObject access$300 = AdReporter.this.httpGet(sb.toString());
                boolean unused = AdReporter.this.canShowAds = access$300.getBoolean("isEnabled");
                JSONObject jSONObject = access$300.getJSONObject("networks");
                Iterator<String> keys = jSONObject.keys();
                AdReporter.this.allowedNetworks.clear();
                while (keys.hasNext()) {
                    String obj = keys.next().toString();
                    AdReporter.this.allowedNetworks.put(obj, Boolean.valueOf(jSONObject.getBoolean(obj)));
                }
                long unused2 = AdReporter.this.interstitialDelay = access$300.getLong("interstitialDelay");
                SplashScreenActivity.setNewTime(access$300.getLong("splashDelayMin"), access$300.getLong("splashDelayMax"));
            } catch (Exception unused3) {
            } catch (Throwable th) {
                AdReporter.this.workerHandler.postDelayed(this, 1800000);
                throw th;
            }
            AdReporter.this.workerHandler.postDelayed(this, 1800000);
        }
    };
    /* access modifiers changed from: private */
    public Handler workerHandler;
    private Thread workerThread;

    public boolean isNetworkAllowed(String str) {
        Boolean bool = this.allowedNetworks.get(str);
        if (bool == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public void onChange(boolean z) {
        if (z) {
            this.workerHandler.removeCallbacksAndMessages((Object) null);
            this.workerHandler.post(this.pingRoutine);
            return;
        }
        this.workerHandler.removeCallbacksAndMessages((Object) null);
        this.workerHandler.post(new Runnable() {
            public void run() {
                boolean unused = AdReporter.this.foreground = true;
            }
        });
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(9:2|3|4|5|6|7|8|9|10) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0051 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private AdReporter() {
        /*
            r2 = this;
            r2.<init>()
            java.lang.String r0 = "AdReporter"
            r2.TAG = r0
            java.lang.String r0 = "${andromo.ad.reporter.url}"
            r2.SERVER_URL = r0
            java.lang.String r0 = "/v3/ping/"
            r2.PATH_PING = r0
            java.lang.String r0 = "/v3/event/"
            r2.PATH_REPORT = r0
            java.lang.String r0 = "${andromo.ad.reporter.uuid}"
            r2.UUID = r0
            java.lang.String r0 = "${andromo.ad.reporter.secret}"
            r2.SECRET = r0
            r0 = 1800000(0x1b7740, double:8.89318E-318)
            r2.PING_INTERVAL = r0
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r2.allowedNetworks = r0
            com.newandromo.dev849565.app936843.AdReporter$2 r0 = new com.newandromo.dev849565.app936843.AdReporter$2
            r0.<init>()
            r2.pingRoutine = r0
            monitor-enter(r2)
            r0 = 0
            r2.foreground = r0     // Catch:{ all -> 0x005c }
            r0 = 1
            r2.launched = r0     // Catch:{ all -> 0x005c }
            r2.canShowAds = r0     // Catch:{ all -> 0x005c }
            r0 = 0
            r2.interstitialDelay = r0     // Catch:{ all -> 0x005c }
            java.lang.Thread r0 = new java.lang.Thread     // Catch:{ all -> 0x005c }
            com.newandromo.dev849565.app936843.AdReporter$3 r1 = new com.newandromo.dev849565.app936843.AdReporter$3     // Catch:{ all -> 0x005c }
            r1.<init>()     // Catch:{ all -> 0x005c }
            r0.<init>(r1)     // Catch:{ all -> 0x005c }
            r2.workerThread = r0     // Catch:{ all -> 0x005c }
            java.lang.Thread r0 = r2.workerThread     // Catch:{ all -> 0x005c }
            r0.start()     // Catch:{ all -> 0x005c }
            r0 = 1000(0x3e8, double:4.94E-321)
            r2.wait(r0)     // Catch:{ Exception -> 0x0051 }
        L_0x0051:
            android.os.Handler r0 = r2.workerHandler     // Catch:{ all -> 0x005c }
            java.lang.Runnable r1 = r2.pingRoutine     // Catch:{ all -> 0x005c }
            r0.post(r1)     // Catch:{ all -> 0x005c }
            com.newandromo.dev849565.app936843.AndromoApplication.appState = r2     // Catch:{ all -> 0x005c }
            monitor-exit(r2)     // Catch:{ all -> 0x005c }
            return
        L_0x005c:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x005c }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newandromo.dev849565.app936843.AdReporter.<init>():void");
    }

    public void reportImpression(final String str) {
        this.workerHandler.post(new Runnable() {
            public void run() {
                try {
                    long currentTimeMillis = System.currentTimeMillis();
                    String str = str;
                    String encode = URLEncoder.encode(AdReporter.this.hmacDigest("${andromo.ad.reporter.uuid}" + ":" + currentTimeMillis + ":" + str + ":" + "i", "${andromo.ad.reporter.secret}"), Key.STRING_CHARSET_NAME);
                    StringBuilder sb = new StringBuilder();
                    new Formatter(sb).format("%s%s?_v=3&a=%s&n=%s&e=%s&t=%s&_s=%s", new Object[]{"${andromo.ad.reporter.url}", "/v3/event/", "${andromo.ad.reporter.uuid}", str, "i", Long.valueOf(currentTimeMillis), encode});
                    JSONObject unused = AdReporter.this.httpGet(sb.toString());
                } catch (Exception unused2) {
                }
            }
        });
    }

    public static AdReporter getInstance() {
        AdReporter adReporter;
        synchronized (AdReporter.class) {
            if (instance == null) {
                instance = new AdReporter();
            }
            adReporter = instance;
        }
        return adReporter;
    }

    public boolean canShowAds() {
        return this.canShowAds;
    }

    public long getInterstitialDelay() {
        return this.interstitialDelay;
    }

    /* access modifiers changed from: private */
    public int getLaunched() {
        if (!this.launched) {
            return 0;
        }
        this.launched = false;
        return 1;
    }

    /* access modifiers changed from: private */
    public int getForeground() {
        if (!this.foreground) {
            return 0;
        }
        this.foreground = false;
        return 1;
    }

    /* access modifiers changed from: private */
    public JSONObject httpGet(String str) throws Exception {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestMethod("GET");
        InputStream inputStream = httpURLConnection.getInputStream();
        byte[] bArr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = inputStream.read(bArr, 0, 1024);
            if (read <= 0) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
        inputStream.close();
        httpURLConnection.disconnect();
        String str2 = new String(byteArrayOutputStream.toByteArray(), "utf-8");
        if (str2.isEmpty()) {
            str2 = "{}";
        }
        return new JSONObject(str2);
    }

    /* access modifiers changed from: private */
    public String hmacDigest(String str, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("utf-8"), "HmacSHA1");
            Mac instance2 = Mac.getInstance("HmacSHA1");
            instance2.init(secretKeySpec);
            return Base64.encodeToString(instance2.doFinal(str.getBytes("utf-8")), 1).toLowerCase();
        } catch (Exception unused) {
            return null;
        }
    }
}
