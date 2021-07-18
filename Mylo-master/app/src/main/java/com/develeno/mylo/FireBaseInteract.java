package com.develeno.mylo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.widget.Toast;

import com.aquery.AQuery;
import com.aquery.listener.QueryNetworkListener;
import com.develeno.mylo.activities.SplashActivity;
import com.develeno.mylo.others.Data;
import com.develeno.mylo.pojo.Deal;
import com.develeno.mylo.pojo.Reservation;
import com.develeno.mylo.pojo.Review;
import com.develeno.mylo.pojo.UserObject;
import com.develeno.mylo.pojo.Vendor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by devel_000 on 05-Dec-17.
 */
public class FireBaseInteract {

    public static UserObject userObject;
    //public static UserProfile user;
    private final OnFailureListener failureListener;
    private final FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private Activity activity;

    public FireBaseInteract(final Activity activity) {
        this.activity = activity;
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        failureListener = e -> {
            Toast.makeText(activity, "Something went wrong\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        };
    }

    public static void sendSMS(String msg, String num, Context context) {
        //String num = "7566427991";
        String authKey = "198556A3OQp9DxHFf5b02ca25";
        String sender = "MYLOAP";
        // String msg = "Hello! This is a test message";
        msg = msg + "\n\nOpen Mylo App for full details";
        String url = "http://api.msg91.com/api/sendhttp.php?country=91&sender=" + sender +
                "&route=4&mobiles=" + num + "&authkey=" + authKey + "&message=" + msg;
        new AQuery(context).ajax(url).get().response(new QueryNetworkListener() {
            @Override
            public void response(String s, Throwable throwable) {
                // Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void postReview(Review review, Pair<String, Vendor> vendor, OnSuccessListener<Void> listener) {
        // Get a new write batch
        WriteBatch batch = db.batch();
        DocumentReference reference = db.collection("reviews").document(review.getCreatedOn().getTime() + "");
        batch.set(reference, review);

        final Vendor second = vendor.second;
        second.setRatingTotal((int) (second.getRatingTotal() + review.getRating()));
        second.setRatingCount(second.getRatingCount() + 1);
        DocumentReference reference2 = db.collection("vendors").document(vendor.first);
        batch.set(reference2, second);
        batch.commit().addOnSuccessListener(listener).addOnFailureListener(failureListener);
//        reference.set(review).addOnSuccessListener(listener).addOnFailureListener(failureListener);
//        db.collection("vendors").document(vendor.first).set(vendor.second);
    }

    public void modifyReview(String id, Review review, float oldrating, Pair<String, Vendor> vendor, OnSuccessListener<Void> listener) {
        WriteBatch batch = db.batch();
        DocumentReference reference = db.collection("reviews").document(id);
        batch.set(reference, review);
        final Vendor second = vendor.second;
        final float rate = second.getRatingTotal() + review.getRating();
        second.setRatingTotal(rate - oldrating);
//        second.setRatingCount(second.getRatingCount() + 1);
        DocumentReference reference2 = db.collection("vendors").document(vendor.first);
        batch.set(reference2, second);
        batch.commit().addOnSuccessListener(listener).addOnFailureListener(failureListener);
    }

    private void createUserProfileInDatabase(UserObject user) {
        db.collection("users").document(user.getNumber()).set(user).addOnFailureListener(failureListener);
    }

    private void openMainActivity(String referralCode) {
        Intent intent = new Intent(activity, SplashActivity.class);
        intent.putExtra("code", referralCode);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    public void openMainActivity() {
        Intent intent = new Intent(activity, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    public void logout() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signOut();
        Intent intent = new Intent(activity, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
    }

    public void fetchUserDetails(String number, final OnSuccessListener<DocumentSnapshot> listener) {
        if (number.contains("@mylo.in")) {
            number = number.replace("@mylo.in", "");
        }
        db.collection("users").document(number).get().addOnSuccessListener(listener).addOnFailureListener(failureListener);
    }

    public void signInWithPassword(final String number, final String password) {
        final ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setMessage("Please wait");
        dialog.setTitle("Signing in");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        String email = number + "@mylo.in";
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, "123456").addOnCompleteListener(task -> {
            boolean successful = task.isSuccessful();
//            successful = true;
            if (successful) {
                dialog.setMessage("Verifying");
                new FireBaseInteract(activity).fetchUserDetails(number, documentSnapshot -> {
                    dialog.dismiss();
                    UserObject userObject = documentSnapshot.toObject(UserObject.class);
                    if (true/*userObject.getPassword().equalsIgnoreCase(password)*/) {
                        Toast.makeText(activity, "Logged In", Toast.LENGTH_SHORT).show();
                        openMainActivity();
                    } else {
                        Toast.makeText(activity, "Wrong Password", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(activity, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                task.getException().printStackTrace();
                dialog.dismiss();
            }
        });
    }

    public void signUp(final UserObject user, String referralCode) {
        final ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setMessage("Please wait");
        dialog.setTitle("Signing up");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        String email = user.getNumber() + "@mylo.in";
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                dialog.dismiss();
                if (task.isSuccessful()) {
                    createUserProfileInDatabase(user);
                    openMainActivity(referralCode);
                } else {
                    Toast.makeText(activity, task.getException().getMessage() + "Try again.", Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }

    public void fetchVendorsAround(String category, OnCompleteListener<QuerySnapshot> listener) {
        if (category != null) {
            db.collection("vendors").whereEqualTo("category", category).orderBy("points", Query.Direction.DESCENDING).get().addOnCompleteListener(listener);
        } else {
            db.collection("vendors").orderBy("points", Query.Direction.DESCENDING).get().addOnCompleteListener(listener);
        }
    }

    public void fetchVendorsNearby(String category, Location location, OnCompleteListener<QuerySnapshot> listener) {
        double lat = 0.0144927536231884;
        double lon = 0.0181818181818182;

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        double distance = 1.0;
        double lowerLat = latitude - (lat * distance);
        double lowerLon = longitude - (lon * distance);

        double greaterLat = latitude + (lat * distance);
        double greaterLon = longitude + (lon * distance);

        GeoPoint lesserGeopoint = new GeoPoint(lowerLat, lowerLon);
        GeoPoint greaterGeopoint = new GeoPoint(greaterLat, greaterLon);

        CollectionReference docRef = FirebaseFirestore.getInstance().collection("vendors");
        Query query = docRef.whereGreaterThan("coordinates", lesserGeopoint)
                .whereLessThan("coordinates", greaterGeopoint);
        if (category != null && !category.isEmpty()) {
            Query query1 = query.whereEqualTo("category", category);
            query1.get().addOnCompleteListener(listener);
        } else {
            query.get().addOnCompleteListener(listener);
        }
    }

    public void fetchDealsForVendor(String vendorId, OnCompleteListener<QuerySnapshot> onCompleteListener) {
        db.collection("deals").whereEqualTo("vendorId", vendorId).get().addOnCompleteListener(onCompleteListener);
    }

    public void searchVendors(String query, OnSuccessListener<QuerySnapshot> onSuccessListener) {
        db.collection("vendors").orderBy("name", Query.Direction.ASCENDING)
                .startAt(query)
                .endAt(query + "\uf8ff").get().addOnSuccessListener(onSuccessListener).addOnFailureListener(failureListener);
    }

    public void fetchReviewsForVendor(String vendorId, OnCompleteListener<QuerySnapshot> onCompleteListener) {
        db.collection("reviews").whereEqualTo("vendorId", vendorId).get().addOnCompleteListener(onCompleteListener);
    }

    public void removeFromFav(String id) {
        Data.wishList.remove(id);
        db.collection("users").document(userObject.getNumber()).collection("data").document("fav").set(Data.wishList);
    }

    public void addToFav(String id) {
        Data.wishList.add(id);
        db.collection("users").document(userObject.getNumber()).collection("data").document("fav").set(Data.wishList);
    }

    public void fetchVendorById(String id, OnSuccessListener<DocumentSnapshot> onSuccessListener) {
        db.collection("vendors").document(id).get().addOnSuccessListener(onSuccessListener).addOnFailureListener(failureListener);
    }

    public void fetchVendorUserById(String id, OnSuccessListener<? super QuerySnapshot> onSuccessListener) {
        db.collection("vendorUsers").whereEqualTo("vendorId", id).get().addOnSuccessListener(onSuccessListener).addOnFailureListener(failureListener);
    }

    public void saveUser(OnSuccessListener<Void> listener) {
        String phoneNumber = userObject.getNumber();
        db.collection("users").document(phoneNumber).set(userObject).addOnSuccessListener(listener).addOnFailureListener(failureListener);
    }

    public void saveUser(UserObject userObject, OnSuccessListener<Void> listener) {
        String phoneNumber = userObject.getNumber();
        db.collection("users").document(phoneNumber).set(userObject).addOnSuccessListener(listener).addOnFailureListener(failureListener);
    }

    public void buyDeal(Pair<String, Deal> dealPair, OnSuccessListener<? super Void> listener) {
        WriteBatch batch = db.batch();
        String phoneNumber = userObject.getNumber();

        Map<String, Object> mData = new HashMap<>();
        mData.put("name", userObject.getName());
        mData.put("createdOn", Calendar.getInstance().getTime());

        final DocumentReference reference = db.collection("deals").document(dealPair.first).collection("buyers").document(phoneNumber);
        batch.set(reference, mData);

        final DocumentReference reference1 = db.collection("users").document(phoneNumber).collection("mydeals").document(dealPair.first);
        batch.set(reference1, mData);

        batch.commit().addOnSuccessListener(listener).addOnFailureListener(failureListener);
    }

    public void fetchMyDeals(OnSuccessListener<QuerySnapshot> listener) {
        String phoneNumber = userObject.getNumber();
        db.collection("users").document(phoneNumber).collection("mydeals").orderBy("createdOn", Query.Direction.DESCENDING).get().addOnSuccessListener(listener).addOnFailureListener(failureListener);
    }

    public void fetchDealById(String id, OnSuccessListener<DocumentSnapshot> listener) {
        String phoneNumber = userObject.getNumber();
        db.collection("deals").document(id).get().addOnSuccessListener(listener).addOnFailureListener(failureListener);
    }

    public void checkForMyReview(String vendorId, OnCompleteListener<QuerySnapshot> onCompleteListener) {
        String phoneNumber = userObject.getNumber();
        final Query query = db.collection("reviews").whereEqualTo("vendorId", vendorId).whereEqualTo("userId", phoneNumber);
        query.get().addOnCompleteListener(onCompleteListener).addOnFailureListener(failureListener);
    }

    public void fetchTimeSlotsForVendor(String vendorId, OnCompleteListener<QuerySnapshot> onCompleteListener) {
        db.collection("vendors").document(vendorId).collection("timeslots").get().addOnCompleteListener(onCompleteListener);
    }

    public void makeAReservation(Reservation reservation, OnSuccessListener<Void> successListener) {
        db.collection("reservations").document(Calendar.getInstance().getTimeInMillis() + "")
                .set(reservation).addOnSuccessListener(successListener).addOnFailureListener(failureListener);
    }

    public void fetchMyBookings(OnSuccessListener<QuerySnapshot> listener) {
        String phoneNumber = userObject.getNumber();
        db.collection("reservations").whereEqualTo("userId", phoneNumber).orderBy("reservationDate", Query.Direction.DESCENDING).get().addOnSuccessListener(listener).addOnFailureListener(failureListener);
    }

    public void updateToken(String token, OnSuccessListener<? super Void> listener) {
        String phoneNumber = userObject.getNumber();
        db.collection("users").document(phoneNumber).update("fcm", token).addOnSuccessListener(listener).addOnFailureListener(failureListener);
    }

    public void changeStatus(String bookingId, int status, OnSuccessListener<? super Void> listener) {
        db.collection("reservations").document(bookingId).update("status", status).addOnSuccessListener(listener).addOnFailureListener(failureListener);
    }

    public void addCreditsToVendor(long credits, String vendorId, OnSuccessListener<? super Void> listener) {
        db.collection("vendors").document(vendorId).update("credits", credits).addOnSuccessListener(listener).addOnFailureListener(failureListener);
    }

    public void fetchUserByReferralCode(String code, OnSuccessListener<? super QuerySnapshot> listener) {
        db.collection("users").whereEqualTo("referralCode", code).get().addOnSuccessListener(listener).addOnFailureListener(failureListener);
    }

    public void checkForUpdate(OnSuccessListener<? super DocumentSnapshot> listener) {
        db.collection("remoteConfig").document("latest_version")
                .get().addOnSuccessListener(listener).addOnFailureListener(failureListener);
    }

    public void sendNotif(String title, String desc, String fcm) {
        Map<String, String> params = new HashMap<>();
        params.put("title", title);
        params.put("desc", desc);
        params.put("fcm", fcm);
        new AQuery(activity).ajax("https://mylocalpay.com/fcm.php").postUrlEncoded(params).response(new QueryNetworkListener() {
            @Override
            public void response(String s, Throwable throwable) {
                //Toast.makeText(MainActivity.mainActivity, s+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

  /*  public void sendNotif(String title, String desc, String fcm) {
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> params2 = new HashMap<>();
        params.put("title", title);
        params2.put("to", fcm);
        params2.put("body", desc);
        params.put("notification", params2);
        new AQuery(activity).ajax("https://fcm.googleapis.com/fcm/send").post(params).response(new QueryNetworkListener() {
            @Override
            public void response(String s, Throwable throwable) {
                Toast.makeText(MainActivity.mainActivity, s + "", Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}
