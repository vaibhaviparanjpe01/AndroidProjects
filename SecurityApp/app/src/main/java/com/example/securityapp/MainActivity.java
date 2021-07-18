package com.example.securityapp;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends Activity
        implements
        LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener

{


    private static final String TAG = "LocationActivity";
    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;

    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mCurrentLocation;
    String mLastUpdateTime;
    String name,mobile,bname,bmobile;

    TextView tv;

    protected static final int RESULT_SPEECH = 1;

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            File f = new File("/sdcard/userdata.txt");
            FileInputStream fin = new FileInputStream(f);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(fin));
            String data = br.readLine();
            String finalData[] = data.split(",");
            name = finalData[0];
            mobile = finalData[1];
            bname = finalData[2];
            bmobile = finalData[3];
            setContentView(R.layout.speak);
            tv = (TextView) findViewById(R.id.textView2);
            Toast.makeText(this,"Welcome :"+name,
                    Toast.LENGTH_SHORT).show();
        }
        catch(Exception ex){
            setContentView(R.layout.activity_main);
        }


        createLocationRequest();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

    }

    public void submitclick(View v)
    {
        EditText t1 = (EditText) findViewById(R.id.editText);
        EditText t2 = (EditText) findViewById(R.id.editText5);
        EditText t3 = (EditText) findViewById(R.id.editText3);
        EditText t4 = (EditText) findViewById(R.id.editText6);

        name = t1.getText().toString();
        mobile = t2.getText().toString();
        bname = t3.getText().toString();
        bmobile = t4.getText().toString();
        if(name.length()==0
                ||mobile.length()==0
                ||bname.length()==0
                ||bmobile.length()==0
        )
        {
            Toast.makeText(this,"Value cannot be empty",
                    Toast.LENGTH_SHORT);
            return;

        }

        try
        {
            String msg =name+","+mobile+","+bname+","+bmobile;
            File f = new File("/sdcard/userdata.txt");
            f.createNewFile();
            FileOutputStream fOut = new FileOutputStream(f);
            OutputStreamWriter myOutWriter =new OutputStreamWriter(fOut);
            myOutWriter.append(msg);
            myOutWriter.close();
            fOut.close();
            Toast.makeText(v.getContext(),
                    "Done writing SD 'userdata.txt'",
                    Toast.LENGTH_SHORT).show();


            String firstmsg = "Hi " + bname
                    + "\n , I have added you as a helping person for emergency";
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(bmobile,
                    null, firstmsg, null, null);

            Toast.makeText(getApplicationContext(), "SMS Sent!",
                    Toast.LENGTH_LONG).show();
            setContentView(R.layout.speak);
            tv = (TextView) findViewById(R.id.textView2);
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(), ex.toString(),
                    Toast.LENGTH_LONG).show();
        }

    }


    public void speakclick(View v)
    {
        Intent intent = new Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                "en-US");
        try {
            startActivityForResult(intent, RESULT_SPEECH);
            tv.setText("");
        } catch (ActivityNotFoundException a) {
            Toast t = Toast.makeText(this,
                    "Ops! Your device doesn't support Speech to " +
                            "Text", Toast.LENGTH_SHORT);
            t.show();
        }
    }
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> text = data
                            .getStringArrayListExtra(
                                    RecognizerIntent.EXTRA_RESULTS);

                    tv.setText(text.get(0));
                    if (tv.getText().equals("help")) {
                        updateUI();
                    }
                }
                break;
            }
        }
    }



    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart fired ..............");
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop fired ..............");
        mGoogleApiClient.disconnect();
        Log.d(TAG, "isConnected ...............: " + mGoogleApiClient.isConnected());
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "onConnected - isConnected ...............: " + mGoogleApiClient.isConnected());
        startLocationUpdates();
    }

    protected void startLocationUpdates() {

        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "ACCESS_FINE_LOCATION permission OK");
        } else {
            Log.d(TAG, "ACCESS_FINE_LOCATION permission NG");
            return;
        }
        PendingResult<Status> pendingResult =
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        Log.d(TAG, "Location update started ..............: ");


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "Connection failed: " + connectionResult.toString());
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "Firing onLocationChanged..............................................");
        mCurrentLocation = location;



    }

    private void updateUI() {
        Log.d(TAG, "UI update initiated .............");
        if (null != mCurrentLocation) {
            String lat = String.valueOf(mCurrentLocation.getLatitude());
            String lng = String.valueOf(mCurrentLocation.getLongitude());

            String url = "http://maps.google.com/maps?&daddr="+
                    lat+"," +lng;

            ////Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
            //         Uri.parse(url));
            // startActivity(intent);

            try {
                String firstmsg = "i need help \n my location is " +url ;

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(bmobile,
                        null, firstmsg, null, null);
                Toast.makeText(getApplicationContext(), "SMS Sent!",
                        Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),
                        "SMS faild, please try again later!",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }




        } else {
            Log.d(TAG, "location is null ...............");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
        Log.d(TAG, "Location update stopped .......................");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleApiClient.isConnected()) {
            startLocationUpdates();
            Log.d(TAG, "Location update resumed .....................");
        }
    }



}