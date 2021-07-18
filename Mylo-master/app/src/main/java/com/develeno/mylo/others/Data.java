package com.develeno.mylo.others;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.develeno.mylo.pojo.WishList;

import java.util.ArrayList;

/**
 * Created by devel_000 on 22-Sep-15.
 */
public class Data {

    //    public static Banners banners;
    // public static ArrayList<Pair<String, Category>> categories = new ArrayList<>();
    public static WishList wishList = new WishList();
    public static ArrayList<String> myDeals = new ArrayList<>();
//    public static ArrayList<Pair<String, ProductListing>> recents = new ArrayList<>(15);
//    private static UserObject serviceProvider;
//    public static Cart cart = new CartObject();

    public static boolean isFirstTime(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean first = preferences.getBoolean("first", true);
        if (first) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("first", false).apply();
        }
        return first;
    }

    //    public static void saveLastUserType(int userType, Context context) {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putInt("usertype", userType).apply();
//    }
//
//    public static int getLastUserType(Context context) {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
//        return preferences.getInt("usertype", 0);
//    }
//
//    public static void setServiceProvider(UserObject serviceProvider, Context context) {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor editor = preferences.edit();
//        if (serviceProvider != null) {
//            String json = new Gson().toJson(serviceProvider);
//            editor.putString("provider", json).apply();
//            editor.putBoolean("isProviderSelected", true).apply();
//        } else {
//            boolean result = editor.putString("provider", "").commit();
//            editor.putBoolean("isProviderSelected", false).apply();
//            //Toast.makeText(context, "re"+result, Toast.LENGTH_SHORT).show();
//        }
//        Data.serviceProvider = serviceProvider;
//    }
//
//    public static UserObject getServiceProvider(Context context) {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
//        String string = preferences.getString("provider", null);
//        boolean isProviderSelected = preferences.getBoolean("isProviderSelected", false);
//        UserObject serviceProvider;
//        if (isProviderSelected) {
//            serviceProvider = new Gson().fromJson(string, UserObject.class);
//            Data.serviceProvider = serviceProvider;
//        } else {
//            serviceProvider = null;
//            Data.serviceProvider = null;
//        }
//        return serviceProvider;
//    }
//
//    public static void saveBanners(Context context) {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("banner", new Gson().toJson(banners)).apply();
//    }
//
//    public static Banners getSavedBanners(Context context) {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
//        String string = preferences.getString("banner", null);
//        return new Gson().fromJson(string, Banners.class);
//    }
//
    public static boolean getSpeakSettings(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean("speak", true);
    }

    public static void saveSpeakSettings(boolean checked, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("speak", checked);
        editor.commit();
    }
//
//    public static void saveServiceProviderDp(Bitmap bitmap, Context context) {
//        SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor edit = shre.edit();
//        if (bitmap != null) {
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//            byte[] b = baos.toByteArray();
//            String temp = Base64.encodeToString(b, Base64.DEFAULT);
//
//            edit.putString("image_data", temp);
//            edit.apply();
//        } else {
//            edit.remove("image_data").apply();
//        }
//    }
//
//    public static Bitmap getServiceProviderDP(Context context) {
//        SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(context);
//        String previouslyEncodedImage = shre.getString("image_data", "");
//
//        if (!previouslyEncodedImage.equalsIgnoreCase("")) {
//            byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
//            return BitmapFactory.decodeByteArray(b, 0, b.length);
//        } else {
//            return null;
//        }
//    }
//
//    public static void setServiceProviderID(String first, Context context) {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("spid", first);
//        editor.apply();
//    }
//
//    public static String getServiceProviderId(Context context) {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
//        return preferences.getString("spid", null);
//    }
//
//    public static void saveImageOffline(UserObject userObject, final Activity activity) {
//        Data.saveServiceProviderDp(null, activity);
//        String dpLink = userObject.getServiceProviderDetails().getDpLink();
//        if (dpLink != null) {
//            ImageLoader imageLoader = AppController.getInstance().getImageLoader();
//
//            // If you are using normal ImageView
//            imageLoader.get(dpLink, new ImageLoader.ImageListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(activity, "Failed to save dp", Toast.LENGTH_SHORT).show();
//                    // Log.e(TAG, "Image Load Error: " + error.getMessage());
//                }
//
//                @Override
//                public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
//                    if (response.getBitmap() != null) {
//                        // load image into imageview
//                        //imageView.setImageBitmap(response.getBitmap());
//                        Data.saveServiceProviderDp(response.getBitmap(), activity);
//                        //Toast.makeText(activity, "Saved dp", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//    }
}
