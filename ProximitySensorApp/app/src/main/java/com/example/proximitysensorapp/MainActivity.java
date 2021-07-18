package com.example.proximitysensorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.content.*;
import android.hardware.*;

import java.security.Policy;

public class MainActivity extends AppCompatActivity {

    TextView L1, L2;
    SensorManager mySensorManager;
    Sensor myProximitySenor;

    private Camera camera;
    private Parameters parameter;

    private void getCamera() {
        if (camera == null) {
            try {

                camera = Camera.open();
                parameter = camera.getParameters();
            } catch (RuntimeException e) {
                System.out.println("Error: Failed to open" + e.getMessage());
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu)
    {
        switch(item.getItemId())
        {
            case R.id.newfile:
                Toast.makeText(getApplicationContext(),"New Clicked",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.exit:
                Toast.makeText(getApplicationContext(),"Exit",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        L1 = (TextView) findViewById(R.id.textView);
        L1.setText("Proximity Sensor Example");
        L2 = (TextView) findViewById(R.id.textView2);

        getCamera();
        mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        myProximitySenor = mySensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (myProximitySenor == null) {
            L1.setText("No proximity sensor");
        } else {
            mySensorManager.registerListener(proximitySensorEventListener, myProximitySenor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    SensorEventListener proximitySensorEventListener = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            //TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            //TODO Auto-generated method stub
            if(event.sensor.getType()==Sensor.TYPE_PROXIMITY)
            {
                if(event.values[0]==0)
                {
                    L2.setText("Near");
                    turnOnTheFlash();
                }
                else
                {
                 L2.setText("Away");
                 turnOffTheFlash();
                }
            }
        }
    };
    private void turnOffTheFlash()
    {
       // parameter.setFlashMode(Policy.Parameters.FLASH_MODE_OFF);
        this.camera.setParameters(parameter);
        this.camera.stopPreview();
    }
    private void turnOnTheFlash()
    {
        if(this.camera !=null)
        {
            parameter=this.camera.getParameters();
            parameter.setFlashMode(Parameters.FLASH_MODE_TORCH);
            this.camera.setParameters(parameter);
            this.camera.startPreview();
        }
    }

}