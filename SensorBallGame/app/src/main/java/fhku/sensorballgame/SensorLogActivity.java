package fhku.sensorballgame;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.math.RoundingMode;

public class SensorLogActivity extends AppCompatActivity implements SensorEventListener {

    public static final String ACCELEROMETER = "ACCELEROMETER";
    public static final String GYROSCOPE = "GYROSCOPE";

    String TAG = "SENSOR_LOG";
    float roundingDecimals = 1000f;
    SensorManager sensorManager;
    String sensor = ACCELEROMETER;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_log);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        textView = (TextView) findViewById(R.id.text_view);
    }

    public void switchSensor (View view) {
        if (sensor.equals(ACCELEROMETER)) {
            sensor = GYROSCOPE;
        } else {
            sensor = ACCELEROMETER;
        }

        textView.setText(sensor);
        unregisterSensor();
        registerSensor(sensor);
    }

    public String formantValue(float value) {
        value = (float) Math.round(value * roundingDecimals) / roundingDecimals;

        if (value < 0) {
            return Float.toString(value);
        } else if (value > 0) {
            return "+" + Float.toString(value);
        } else {
            return " 0.000";
        }
    }

    public void registerSensor(String sensorName) {
        Sensor sensor;

        if (sensorName.equals(ACCELEROMETER)) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        } else {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        }

        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unregisterSensor() {
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerSensor(sensor);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterSensor();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.i(TAG, "x: " + formantValue(sensorEvent.values[0]) + ", y: " + formantValue(sensorEvent.values[1]) + ", z: " + formantValue(sensorEvent.values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
