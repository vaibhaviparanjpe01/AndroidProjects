package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.webkit.URLUtil;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public final class NetUtils {
    private static boolean HAS_ACCESS_NETWORK_STATE_PERMISSION = false;
    private static boolean PERMISSION_CHECKED = false;
    private static final String TAG = "NetUtils";

    public static boolean isValidUrl(String str) {
        return URLUtil.isValidUrl(str);
    }

    public static String getAbsolutePathIfExists(String str, String str2) {
        String absolutePath = getAbsolutePath(str, str2);
        return testExists(absolutePath) ? absolutePath : "";
    }

    public static boolean isRemoteUrl(String str) {
        if (str == null) {
            return false;
        }
        if ((str.length() <= 4 || !"http:".equalsIgnoreCase(str.substring(0, 5))) && ((str.length() <= 5 || !"https:".equalsIgnoreCase(str.substring(0, 6))) && (str.length() <= 4 || !"rtsp:".equalsIgnoreCase(str.substring(0, 5))))) {
            return false;
        }
        return true;
    }

    public static String getAbsolutePath(String str, String str2) {
        if (str != null) {
            boolean z = false;
            if ((str.length() > 4 && "http:".equalsIgnoreCase(str.substring(0, 5))) || ((str.length() > 5 && "https:".equalsIgnoreCase(str.substring(0, 6))) || (str.length() > 4 && "rtsp:".equalsIgnoreCase(str.substring(0, 5))))) {
                z = true;
            }
            if (z) {
                return str;
            }
            try {
                URI resolve = new URI(str2).resolve(new URI(str));
                if (resolve != null) {
                    String uri = resolve.toString();
                    if (!URLUtil.isValidUrl(uri)) {
                        return "";
                    }
                    return uri;
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return "";
            }
        }
        return "";
    }

    public static boolean testExists(String str) {
        HttpURLConnection httpURLConnection;
        boolean z = false;
        if (str != null && str.length() > 0) {
            try {
                httpURLConnection = (HttpURLConnection) new URI(str).toURL().openConnection();
                httpURLConnection.setRequestMethod("HEAD");
                if (httpURLConnection.getResponseCode() == 200) {
                    z = true;
                }
                httpURLConnection.disconnect();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (MalformedURLException e2) {
                e2.printStackTrace();
            } catch (IOException e3) {
                e3.printStackTrace();
            } catch (Throwable th) {
                httpURLConnection.disconnect();
                throw th;
            }
        }
        return z;
    }

    public static boolean pingTest(String str) {
        if (str == null || str.length() <= 0) {
            return false;
        }
        Runtime runtime = Runtime.getRuntime();
        try {
            if (runtime.exec("ping -c 1 " + str).waitFor() == 0) {
                return true;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean hasInternet(String str) {
        return testExists(str) || pingTest("8.8.8.8");
    }

    private static ConnectivityManager getConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService("connectivity");
    }

    private static NetworkInfo getCurrentNetwork(Context context) {
        return getConnectivityManager(context).getActiveNetworkInfo();
    }

    public static boolean canSyncData(Context context) {
        return isConnected(context) && getConnectivityManager(context).getBackgroundDataSetting();
    }

    public static boolean canSyncLargeData(Context context) {
        return isNetworkFast(context) && canSyncData(context);
    }

    public static boolean isNetworkConstrained(Context context) {
        return !isNetworkFast(context) && isConnected(context);
    }

    public static boolean isNetworkFast(Context context) {
        NetworkInfo currentNetwork = getCurrentNetwork(context);
        if (currentNetwork == null || currentNetwork.isRoaming()) {
            return false;
        }
        if (currentNetwork.getType() == 9 || currentNetwork.getType() == 1) {
            return true;
        }
        return false;
    }

    public static boolean isConnected(Context context) {
        NetworkInfo currentNetwork = getCurrentNetwork(context);
        if (currentNetwork != null) {
            return currentNetwork.isConnected();
        }
        return false;
    }

    public static boolean isConnectedIfPermitted(Context context, boolean z) {
        if (!PERMISSION_CHECKED && context != null) {
            HAS_ACCESS_NETWORK_STATE_PERMISSION = checkPermission(context.getApplicationContext(), "android.permission.ACCESS_NETWORK_STATE");
            PERMISSION_CHECKED = true;
        }
        return HAS_ACCESS_NETWORK_STATE_PERMISSION ? isConnected(context) : z;
    }

    public static boolean isConnectedIfPermitted(Context context) {
        return isConnectedIfPermitted(context, false);
    }

    public static boolean checkPermission(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }
}
