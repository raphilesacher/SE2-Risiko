package at.aau.risiko;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Objects;

import static android.content.Context.SENSOR_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class DiceActivity extends AppCompatActivity implements SensorEventListener {
    //global variables
    private AlertDialog dialog;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private TextView diceNum;
    private TextView accel;

    private ImageView diceOneAttack;
    private ImageView diceTwoAttack;
    private ImageView diceThreeAttack;
    private ImageView diceOneDefense;
    private ImageView diceTwoDefense;
    //dice should only be rolled if acceleration is > SHAKE_THRESHOLD
    final static int SHAKE_THRESHOLD = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

         diceNum = (TextView)findViewById(R.id.diceNumber);
         accel = (TextView)findViewById(R.id.acceleration);

        diceOneAttack = findViewById(R.id.diceOneAttack);
        diceTwoAttack = findViewById(R.id.diceTwoAttack);
        diceThreeAttack = findViewById(R.id.diceThreeAttack);
        diceOneDefense = findViewById(R.id.diceOneDefense);
        diceTwoDefense = findViewById(R.id.diceTwoDefense);

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

    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //variables for tracking the motion of the device on x-, y- and z-axis
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];
        //calculate the movement value
        double accelerationValueCurrent = Math.sqrt((x*x + y*y + z*z)) - SensorManager.GRAVITY_EARTH;
        //if accelerationValueCurrent > SHAKE_THRESHOLD call rollDice
        Dice dice = new Dice("attacker");
        System.out.println("Shakyshaky");
        if(accelerationValueCurrent > SHAKE_THRESHOLD) {

            int num = dice.diceRoll();
            diceNum.setText("dice have been rolled:" + num);
            accel.setText("Acceleration: " + (int)accelerationValueCurrent);
            switch(num) {
                case 1:
                    diceOneAttack.setImageResource(R.drawable.diceredone);
                    rotateDice();
                    break;
                case 2:
                    diceOneAttack.setImageResource(R.drawable.diceredtwo);
                    rotateDice();
                    break;
                case 3:
                    diceOneAttack.setImageResource(R.drawable.diceredthree);
                    rotateDice();
                    break;
                case 4:
                    diceOneAttack.setImageResource(R.drawable.diceredfour);
                    rotateDice();
                    break;
                case 5:
                    diceOneAttack.setImageResource(R.drawable.diceredfive);
                    rotateDice();
                    break;
                case 6:
                    diceOneAttack.setImageResource(R.drawable.diceredsix);
                    rotateDice();
                    break;

            }
            Log.i("DiceActivity", "Device was shaken");
        }
        //cheat function
        if(accelerationValueCurrent > 30) {
            dice.setEyeNumber(6);
            diceNum.setText("dice have been rolled" + dice.getEyeNumber());
            accel.setText("Acceleration: " + (int)accelerationValueCurrent);
        }

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    private void rotateDice() {
        Animation rollAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        diceOneAttack.setAnimation(rollAnimation);
    }

}
