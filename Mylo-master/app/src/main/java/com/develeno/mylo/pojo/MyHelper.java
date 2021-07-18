package com.develeno.mylo.pojo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.develeno.mylo.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;


/**
 * Created by devel_000 on 08-Oct-17.
 */
public class MyHelper {

    private static final int PICK_IMAGE_REQUEST = 101;
    private final Activity activity;

    public MyHelper(Activity activity) {
        this.activity = activity;
    }

    public static void vibrate(Context activity) {
        Vibrator v = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(30);
    }

    public static int getIndexByString(Spinner spinner, String string) {
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(string)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static String createRandomCode(int codeLength) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < codeLength; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        System.out.println(output);
        return output;
    }

   /* public void loadImage(ImageView imageView, String link) {
        ///AppController.getInstance().getImageLoader().
    }*/

    public void selectPicture() {
        Intent intent = new Intent();
        // Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        // Always show the chooser (if there are multiple options available)
        activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    public void loadImage(ImageView imageView, String link) {
        try {
            Picasso.with(activity)
                    .load(link)
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.not_available)
                    .into(imageView);
        } catch (Exception e) {
            // Toast.makeText(activity, "", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void loadImage(ImageView imageView, String link, int loading) {
        try {
            Picasso.with(activity)
                    .load(link)
                    .placeholder(loading)
                    .error(R.drawable.not_available)
                    .into(imageView);
        } catch (Exception e) {
            // Toast.makeText(activity, "", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void loadImage(Target target, String link) {
        try {
            Picasso.with(activity)
                    .load(link)
                    .placeholder(R.drawable.loading_mylo)
                    .error(R.drawable.logo_mylo)
                    .into(target);
        } catch (Exception e) {
            // Toast.makeText(activity, "", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public boolean hasAllNeededPermissions() {
        int permissionCamera = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.CAMERA);
        int permissionAudio = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.RECORD_AUDIO);
        int permissionExternal = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        return permissionCamera == PackageManager.PERMISSION_GRANTED &&
                permissionExternal == PackageManager.PERMISSION_GRANTED;
    }

    public void askPermissions() {
        ArrayList<String> arrayList = new ArrayList<>();

        int permissionCamera = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.CAMERA);
        int permissionAudio = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.RECORD_AUDIO);
        int permissionExternal = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCamera != PackageManager.PERMISSION_GRANTED) {
            arrayList.add(Manifest.permission.CAMERA);
        }

        if (permissionAudio != PackageManager.PERMISSION_GRANTED) {
            arrayList.add(Manifest.permission.RECORD_AUDIO);
        }

        if (permissionExternal != PackageManager.PERMISSION_GRANTED) {
            arrayList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        String[] permissions = arrayList.toArray(new String[arrayList.size()]);
        showPermissionDialog(permissions);
    }

    private void showPermissionDialog(String[] permissions) {
        if (permissions.length > 0) {
            ActivityCompat.requestPermissions(activity, permissions, 0);
        }
    }

    public void updateSpinnerAreas(Spinner spinnerArea, ArrayList<String> areas) {
        ArrayAdapter<String> areaAdapter = new ArrayAdapter<String>(
                activity, android.R.layout.simple_spinner_dropdown_item, areas);
        spinnerArea.setAdapter(areaAdapter);
    }

    public boolean isPicAttached(Bitmap[] bitmaps) {
        for (Bitmap bitmap : bitmaps) {
            if (bitmap != null) {
                return true;
            }
        }
        return false;
    }

   /* public ArrayList<String> getAreasForCity(String city) {
        if (FirebaseInteract.cities != null) {
            for (DocumentSnapshot snapshot : FirebaseInteract.cities) {
                if (snapshot.getId().equals(city) && snapshot.getData().containsKey("areas")) {
                    return (ArrayList<String>) snapshot.getData().get("areas");
                }
            }
        }
        return new ArrayList<String>();
    }*/

    public void updateCitiesSpinner(Spinner spinnerCity, ArrayList<String> cities) {
        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<String>(
                activity, android.R.layout.simple_spinner_dropdown_item, cities);
        spinnerCity.setAdapter(citiesAdapter);
    }

    public void showPermissionRequiredSnackBar() {
        Snackbar snackbar = Snackbar
                .make(activity.findViewById(android.R.id.content), "Permissions required", Snackbar.LENGTH_LONG)
                .setAction("Open Settings", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openSettings(activity);
                    }
                });
        snackbar.show();
    }

    private void openSettings(Activity activity) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivity(intent);
    }

    public void showSignUpDialog(Activity activity) {
        Toast.makeText(activity, "Login Required", Toast.LENGTH_SHORT).show();
        //activity.startActivity(new Intent(activity, EnterNumberActivity.class));
    }

    public void uploadThisPic(Bitmap bitmap, String folderName, OnFailureListener onFailureListener, OnSuccessListener<UploadTask.TaskSnapshot> onSuccessListener) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 30, baos);
        byte[] data = baos.toByteArray();

        FirebaseStorage storage = FirebaseStorage.getInstance();

        // Create a storage reference from our app
        StorageReference storageRef = storage.getReference().child(folderName);

        // Create a reference to "mountains.jpg"
        String imgName = UUID.randomUUID().toString();
        StorageReference imageRef = storageRef.child(imgName + ".png");

        UploadTask uploadTask = imageRef.putBytes(data);
        uploadTask.addOnFailureListener(onFailureListener).addOnSuccessListener(onSuccessListener);
    }

    /*public void callInstamojoPay(String email, String phone, String amount, String purpose, String buyername, InstapayListener listener) {
        InstamojoPay instamojoPay = new InstamojoPay();
        IntentFilter filter = new IntentFilter("ai.devsupport.instamojo");
        activity.registerReceiver(instamojoPay, filter);
        JSONObject pay = new JSONObject();
        try {
            pay.put("email", email);
            pay.put("phone", phone);
            pay.put("purpose", purpose);
            pay.put("amount", amount);
            pay.put("name", buyername);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        instamojoPay.start(activity, pay, listener);
    }*/
}
