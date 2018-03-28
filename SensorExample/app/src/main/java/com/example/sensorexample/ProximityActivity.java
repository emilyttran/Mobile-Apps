package com.example.sensorexample;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by emily_000 on 3/28/2018.
 */

public class ProximityActivity extends AppCompatActivity {
    // when using sensor, you need sensoryManager
    // each sensor has a code

    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private SensorEventListener proximitySensorListener;

    @Override
    protected  void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        // initialize sensor manager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // get the proximity
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        // override the event listener to get data
        if(proximitySensor == null)
            finish();

        // override the event listener to get data
        proximitySensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                // when data changes, what will you do?
                if(sensorEvent.values[0] < proximitySensor.getMaximumRange())
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                else
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

    }

    // life cycle of sensors. Want to make sure sensors stop when you don't use them
    @Override
    protected void onResume(){
        super.onResume();
        // register sensor again
        sensorManager.registerListener(proximitySensorListener, proximitySensor, 2000);
    }

    protected  void onPause(){
        super.onPause();
        // unregister your sensor
        sensorManager.unregisterListener(proximitySensorListener);
    }
}
