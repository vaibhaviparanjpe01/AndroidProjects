package com.example.sensorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.content.*;
import android.os.Bundle;
import android.widget.*;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;


public class MainActivity extends AppCompatActivity {
    TextView ProximitySensor, data;
    SensorManager mySensorManager;
    Sensor myProximitySensor;
    private Camera camera;
    private Parameters parameter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = findViewById(R.id.textView2);
        getCamera();
        mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        myProximitySensor = mySensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (myProximitySensor == null) {
            ProximitySensor.setText("No Proximity Sensor");
        } else {
            mySensorManager.registerListener(proximitySensorEventListener, myProximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
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
            if (event.sensor.getType() == myProximitySensor.TYPE_PROXIMITY) {
                if (event.values[0] == 0) {
                    data.setText("Near");
                    turnOnTheFlash();

                } else {
                    data.setText("Away");
                    turnOffTheFlash();
                }

            }
        }
    };
private void turnOffTheFlash()
{
parameter.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
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
private void getCamera()
{
    if(camera==null)
    {
        try
        {
            camera=Camera.open();
            parameter=camera.getParameters();
        }
        catch(RuntimeException e)
        {
            System.out.println("Error: Failed to Open:"+e.getMessage());
        }
    }
}
}



