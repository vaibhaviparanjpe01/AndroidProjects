//package com.develeno.mylo.others;
//
//import android.content.Context;
//import android.support.multidex.MultiDex;
//import android.text.TextUtils;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.toolbox.ImageLoader;
//import com.android.volley.toolbox.Volley;
//
//public class AppController extends android.support.multidex.MultiDexApplication {
//
//    public static final String TAG = AppController.class
//            .getSimpleName();
//    private static AppController mInstance;
//    private RequestQueue mRequestQueue;
//    private ImageLoader mImageLoader;
//
//    public static synchronized AppController getInstance() {
//        return mInstance;
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        mInstance = this;
//       /* CaocConfig.Builder.create()
//                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //default: CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM
//                .enabled(false) //default: true
//                .showErrorDetails(false) //default: true
//                .showRestartButton(false) //default: true
//                .logErrorOnRestart(false) //default: true
//                .trackActivities(true) //default: false
//                .minTimeBetweenCrashesMs(2000) //default: 3000
//                .errorDrawable(R.drawable.minnat_logo) //default: bug image
//                .restartActivity(SplashActivity.class) //default: null (your app's launch activity)
//                .errorActivity(AppCrashActivity.class) //default: null (default error activity)
//                .eventListener(null) //default: null
//                .apply();*/
//    }
//
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(this);
//    }
//
//    public RequestQueue getRequestQueue() {
//        if (mRequestQueue == null) {
//            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
//        }
//
//        return mRequestQueue;
//    }
//
//    public <T> void addToRequestQueue(Request<T> req, String tag) {
//        // set the default tag if tag is empty
//        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
//        getRequestQueue().add(req);
//    }
//
//    public <T> void addToRequestQueue(Request<T> req) {
//        req.setTag(TAG);
//        getRequestQueue().add(req);
//    }
//
//    public void cancelPendingRequests(Object tag) {
//        if (mRequestQueue != null) {
//            mRequestQueue.cancelAll(tag);
//        }
//    }
//
//    public ImageLoader getImageLoader() {
//        getRequestQueue();
//        if (mImageLoader == null) {
//            mImageLoader = new ImageLoader(this.mRequestQueue,
//                    new LruBitmapCache());
//        }
//        return this.mImageLoader;
//    }
//}