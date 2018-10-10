package ru.yandex.dunaev.mick.mysensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textVal1) TextView val1;
    @BindView(R.id.textVal2) TextView val2;
    @BindView(R.id.textVal3) TextView val3;

    @BindView(R.id.textMagneticVal1) TextView valM1;
    @BindView(R.id.textMagneticVal2) TextView valM2;
    @BindView(R.id.textMagneticVal3) TextView valM3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        SensorManager sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> list = sm.getSensorList(Sensor.TYPE_ALL);

        Sensor sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor sensorMagnetic = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        SensorEventListener listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                switch (event.sensor.getType()){
                    case Sensor.TYPE_ACCELEROMETER:
                        val1.setText(String.format("%.3f",event.values[0]));
                        val2.setText(String.format("%.3f",event.values[1]));
                        val3.setText(String.format("%.3f",event.values[2]));
                        break;
                    case Sensor.TYPE_MAGNETIC_FIELD:
                        valM1.setText(String.format("%.3f",event.values[0]));
                        valM2.setText(String.format("%.3f",event.values[1]));
                        valM3.setText(String.format("%.3f",event.values[2]));
                        break;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };


        sm.registerListener(listener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(listener,sensorMagnetic,SensorManager.SENSOR_DELAY_NORMAL);
    }
}
