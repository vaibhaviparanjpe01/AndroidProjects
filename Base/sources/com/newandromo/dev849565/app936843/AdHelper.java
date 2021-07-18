package com.newandromo.dev849565.app936843;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.TranslateAnimation;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class AdHelper {
    private static final int AD_DISPLAY_MILLISECS = 20000;
    private static final int AD_SLIDE_DURATION_MILLISECS = 500;
    public static final int MIN_SCREEN_HEIGHT = 500;
    private static final String TAG = "AdHelper";

    public enum SlideDirection {
        NONE,
        DOWN,
        UP
    }

    public static View replaceView(View view, int i) {
        LayoutInflater layoutInflater;
        ViewGroup viewGroup;
        if (view == null || (layoutInflater = (LayoutInflater) view.getContext().getSystemService("layout_inflater")) == null || (viewGroup = (ViewGroup) view.getParent()) == null) {
            return null;
        }
        int indexOfChild = viewGroup.indexOfChild(view);
        viewGroup.removeView(view);
        View inflate = layoutInflater.inflate(i, viewGroup, false);
        viewGroup.addView(inflate, indexOfChild);
        return inflate;
    }

    public static void replaceView(View view, View view2) {
        ViewGroup viewGroup;
        if (view != null && (viewGroup = (ViewGroup) view.getParent()) != null) {
            int indexOfChild = viewGroup.indexOfChild(view);
            viewGroup.removeView(view);
            viewGroup.addView(view2, indexOfChild);
        }
    }

    public static class Device {
        public static String getMacAddress(Context context) {
            WifiManager wifiManager;
            WifiInfo connectionInfo;
            if (context == null || (wifiManager = (WifiManager) context.getSystemService("wifi")) == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) {
                return "";
            }
            return connectionInfo.getMacAddress();
        }

        public static String getAndroidID(ContentResolver contentResolver) {
            String string = Settings.Secure.getString(contentResolver, "android_id");
            return string == null ? "" : string;
        }

        public static String getLocalIpAddress() {
            try {
                Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                    while (true) {
                        if (inetAddresses.hasMoreElements()) {
                            InetAddress nextElement = inetAddresses.nextElement();
                            if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet4Address)) {
                                return nextElement.getHostAddress().toString();
                            }
                        }
                    }
                }
                return null;
            } catch (Exception unused) {
                return null;
            }
        }
    }

    public static class Size {
        public static float getDips(int i, float f) {
            return ((float) i) / f;
        }

        public static int getPixels(float f, float f2) {
            return (int) ((f * f2) + 0.5f);
        }

        public static boolean isScreenTallEnough(Context context) {
            return isScreenTallerThan(context, 500);
        }

        public static boolean isScreenTallerThan(Context context, int i) {
            if (i <= 0) {
                return true;
            }
            DisplayMetrics metrics = getMetrics(context);
            if (context == null || metrics == null || metrics.heightPixels > i) {
                return true;
            }
            return false;
        }

        public static boolean isScreenWiderThanDP(Context context, int i) {
            if (i <= 0) {
                return true;
            }
            DisplayMetrics metrics = getMetrics(context);
            if (context == null || metrics == null || metrics.widthPixels > getPixels((float) i, metrics)) {
                return true;
            }
            return false;
        }

        public static DisplayMetrics getMetrics(Resources resources) {
            return resources != null ? resources.getDisplayMetrics() : new DisplayMetrics();
        }

        public static DisplayMetrics getMetrics(Context context) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            if (context == null) {
                return displayMetrics;
            }
            if (!(context instanceof Activity)) {
                return getMetrics(context.getResources());
            }
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics;
        }

        public static int getDensityDpi(Context context) {
            DisplayMetrics metrics = getMetrics(context);
            if (metrics != null) {
                return metrics.densityDpi;
            }
            return 0;
        }

        public static int getDensityDpi(Resources resources) {
            DisplayMetrics metrics = getMetrics(resources);
            if (metrics != null) {
                return metrics.densityDpi;
            }
            return 0;
        }

        public static float getDensity(Context context) {
            if (context != null) {
                return context.getResources().getDisplayMetrics().density;
            }
            return 1.0f;
        }

        public static int getPixels(float f, DisplayMetrics displayMetrics) {
            return (int) ((f * displayMetrics.density) + 0.5f);
        }

        public static float getDips(Context context, int i) {
            return ((float) i) / getDensity(context);
        }
    }

    public static class AdAnimation {
        public static boolean shouldSlideAway(Context context) {
            return !Size.isScreenTallEnough(context);
        }

        static final void postDelayedSlideAway(View view) {
            postDelayedSlideAway(view, AdHelper.AD_DISPLAY_MILLISECS);
        }

        static final void postDelayedSlideAwayTop(View view) {
            postDelayedSlideAwayTop(view, AdHelper.AD_DISPLAY_MILLISECS);
        }

        static final void postDelayedSlideAway(View view, int i) {
            new Handler().postDelayed(getRunnableToSlideBottomAdAway(view), (long) i);
        }

        static final void postDelayedSlideAwayTop(View view, int i) {
            new Handler().postDelayed(getRunnableToSlideTopAdAway(view), (long) i);
        }

        public static final AnimRunnable getRunnableToSlideBottomAdAway(View view) {
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
            translateAnimation.setDuration(500);
            translateAnimation.setInterpolator(new AccelerateInterpolator(1.0f));
            translateAnimation.setAnimationListener(new SetVisibilityAnimationListener(view, 8));
            return new AnimRunnable(view, translateAnimation);
        }

        public static final AnimRunnable getRunnableToSlideTopAdAway(View view) {
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, -1.0f);
            translateAnimation.setDuration(500);
            translateAnimation.setInterpolator(new AccelerateInterpolator(1.0f));
            translateAnimation.setAnimationListener(new SetVisibilityAnimationListener(view, 8));
            return new AnimRunnable(view, translateAnimation);
        }
    }
}
