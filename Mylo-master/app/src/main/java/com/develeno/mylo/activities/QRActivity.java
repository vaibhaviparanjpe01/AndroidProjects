package com.develeno.mylo.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.develeno.mylo.FireBaseInteract;
import com.develeno.mylo.R;
import com.develeno.mylo.pojo.Reservation;
import com.develeno.mylo.pojo.Vendor;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class QRActivity extends AppCompatActivity {

    private static Pair<String, Reservation> reservationPair;
    private ProgressBar progressBar;
    private ImageView image;
    private Bitmap bmp;
    private Timer timer;

    public static void setReservation(Pair<String, Reservation> reservationPair) {
        QRActivity.reservationPair = reservationPair;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        Toolbar toolbar = findViewById(R.id.MyToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(reservationPair.second.getVendorName());
        getSupportActionBar().setSubtitle("ID: " + reservationPair.first);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        image = findViewById(R.id.profilepic);
        progressBar = findViewById(R.id.progressBar);

        Async async = new Async(); // can add params for a constructor if needed
        async.execute();

        final TextView title = findViewById(R.id.title);
        final TextView tnc = findViewById(R.id.tnc);

        title.setText(reservationPair.second.getDeal());
        String secondTnc = reservationPair.second.getTnc() + "";
        String[] tc = secondTnc.split(";");
        tnc.setText("");
        for (int i = 0; i < tc.length; i++) {
            tnc.setText(tnc.getText() + "\n" + tc[i]);
        }
        findViewById(R.id.card).setVisibility(View.VISIBLE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_qr, menu);
        return true;
    }


    private void startTimer() {
        final int[] n = {30};
        timer = new Timer();
        TimerTask timerTask;
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        n[0]--;
                        updateTimerText(n[0]);

                        if (n[0] <= 0) {
                            timer.cancel();
                            updateTimerText(true);
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    private void updateTimerText(int n) {
        TextView timerField = findViewById(R.id.timer);
        timerField.setVisibility(View.VISIBLE);
        timerField.setTypeface(null, Typeface.NORMAL);
        timerField.setTextColor(Color.parseColor("#75000000"));
//        timerField.setText("Wait for 00:" + (n < 10 ? ("0" + n) : n) + " seconds\nbefore retrying if you don't receive any code");
        timerField.setText("This QR code is valid only for " + (n < 10 ? ("0" + n) : n) + " seconds");
    }

    private void updateTimerText(boolean b) {
        TextView timerField = findViewById(R.id.timer);
        timerField.setVisibility(View.VISIBLE);
        timerField.setText("Regenerating QR Code");
        Async async = new Async(); // can add params for a constructor if needed
        async.execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.cancel_action:
                cancelBooking();
                break;
        }
        return true;
    }

    private void changeStatus(String bookingId, int status) {
        final ProgressDialog dialog = new ProgressDialog(QRActivity.this);
        dialog.setMessage("Please wait");
        dialog.show();
        FireBaseInteract fireBaseInteract = new FireBaseInteract(QRActivity.this);
        fireBaseInteract.changeStatus(bookingId, status, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dialog.dismiss();
                setResult(RESULT_OK);
                finish();
            }
        });
        if (reservationPair.second != null) {
            dialog.show();
            fireBaseInteract.fetchVendorById(reservationPair.second.getVendorId(), new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    Vendor vendor = documentSnapshot.toObject(Vendor.class);
                    int cost = reservationPair.second.getCost();
                    if (vendor != null) {
                        fireBaseInteract.addCreditsToVendor(vendor.getCredits() + cost, reservationPair.second.getVendorId(), aVoid -> {
                            dialog.dismiss();
                        });
                    } else {
                        dialog.dismiss();
                        Toast.makeText(QRActivity.this, "No Vendor found for this booking", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void cancelBooking() {
        AlertDialog.Builder builder = new AlertDialog.Builder(QRActivity.this);
        builder.setTitle("Cancel Booking?");
        builder.setMessage("Are you sure you want to cancel Booking?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            changeStatus(reservationPair.first, 3);
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

    public class Async extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            image.setVisibility(View.INVISIBLE);
            /*
             *    do things before doInBackground() code runs
             *    such as preparing and showing a Dialog or ProgressBar
             */
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            /*
             *    updating data
             *    such a Dialog or ProgressBar
             */

        }

        @Override
        protected Void doInBackground(Void... params) {
            //do your work here
            QRCodeWriter writer = new QRCodeWriter();
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("user", FireBaseInteract.userObject.getNumber());
                jsonObject.put("userName", FireBaseInteract.userObject.getName());
                jsonObject.put("deal", reservationPair.second.getDeal() + "");
                jsonObject.put("vendor", reservationPair.second.getVendorId());
                jsonObject.put("time", Calendar.getInstance().getTime().getTime());
                jsonObject.put("bookindId", reservationPair.first);
                jsonObject.put("status", reservationPair.second.getStatus());
                String contents;
                contents = jsonObject.toString();
                BitMatrix bitMatrix = writer.encode(contents, BarcodeFormat.QR_CODE, 512, 512);
                int width = bitMatrix.getWidth();
                int height = bitMatrix.getHeight();
                bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                    }
                }
            } catch (WriterException | JSONException e) {
                e.printStackTrace();
                Toast.makeText(QRActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            progressBar.setVisibility(View.GONE);
            image.setVisibility(View.VISIBLE);
            image.setImageBitmap(bmp);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageViewerActivity.setBitmap(bmp);
                    startActivity(new Intent(QRActivity.this, ImageViewerActivity.class));
                }
            });

            startTimer();
            /*
             *    do something with data here
             *    display it or send to mainactivity
             *    close any dialogs/ProgressBars/etc...
             */
        }
    }
}
