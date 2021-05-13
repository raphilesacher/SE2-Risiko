package at.aau.risiko;

import android.app.AlertDialog;
import android.app.Dialog;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Objects;

import static android.content.Context.SENSOR_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class DiceActivity extends AppCompatActivity implements SensorEventListener {
    private AlertDialog dialog;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private TextView diceNum;
    //dice should only be rolled if acceleration is > SHAKE_THRESHOLD
    final static int SHAKE_THRESHOLD = 3;
    @NonNull
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

         diceNum = findViewById(R.id.diceNumber);

        //variables for building the dialog
        /*AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dice, null);*/
        //variables to track sensor activity
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        //builder.setView(view);



        //return builder.create();
    }
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //variables for tracking the motion of the device on x-, y- and z-axis
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];
        //calculate the movement value
        double accelerationValueCurrent = Math.sqrt((x*x + y*y + z*z)) - SensorManager.GRAVITY_EARTH;
        //if accelerationValueCurrent > SHAKE_THRESHOLD call rollDice
        if(accelerationValueCurrent > SHAKE_THRESHOLD) {
            Dice dice = new Dice("attacker");
            int num = dice.diceRoll();
            diceNum.setText(num);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
