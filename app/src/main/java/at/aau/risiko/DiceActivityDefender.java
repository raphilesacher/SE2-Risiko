package at.aau.risiko;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class DiceActivityDefender extends AppCompatActivity implements SensorEventListener {
    /*these variables are needed to track the sensor event*/
    private SensorManager sensorManager;
    private Sensor accelerometer;
    /*UI Variables*/
    private ImageView diceOneDefense;
    private ImageView diceTwoDefense;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
