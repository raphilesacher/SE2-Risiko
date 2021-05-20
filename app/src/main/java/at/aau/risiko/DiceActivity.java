package at.aau.risiko;

import android.annotation.SuppressLint;
import android.app.AlertDialog;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;


import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

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
    /**
     * numAttackers and numDefenders should be set via .getNumAttacker and .getNumDefenders
     * as soon as attack feature is implemented
     */
    private int numAttackers = 3;
    private int numDefenders = 2;

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

        //variables to track sensor activity
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //check how many dices are needed on each side and set them to some images
        switch(numAttackers) {
            case 1:
                diceOneAttack.setImageResource(R.drawable.diceredtwo);
                break;
            case 2:
                diceOneAttack.setImageResource(R.drawable.diceredthree);
                diceTwoAttack.setImageResource(R.drawable.diceredfive);
                break;
            case 3:
                diceOneAttack.setImageResource(R.drawable.diceredfour);
                diceTwoAttack.setImageResource(R.drawable.diceredfive);
                diceThreeAttack.setImageResource(R.drawable.diceredone);
                break;
        }
        switch(numDefenders) {
            case 1:
                diceOneDefense.setImageResource(R.drawable.dicebluefive);
                break;
            case 2:
                diceOneDefense.setImageResource(R.drawable.dicebluefive);
                diceTwoDefense.setImageResource(R.drawable.dicebluesix);
                break;
        }




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



            for (int i = 0; i < numAttackers; i++) {
                int num = dice.diceRoll();
                //diceNum.setText("dice have been rolled:" + num);
                //accel.setText("Acceleration: " + (int)accelerationValueCurrent);
                dice.setEyeNumber(num);

                setImageView(num, i+1);

            }
            Log.i("DiceActivity", "Device was shaken");
        }
        //cheat function
        if(accelerationValueCurrent > 30) {
            dice.setEyeNumber(6);
            for(int index = 0; index < numAttackers; index++) {
                setImageView(6, index+1);
            }


            //diceNum.setText("dice have been rolled" + dice.getEyeNumber());
            //accel.setText("Acceleration: " + (int)accelerationValueCurrent);
        }

    }

    private void setImageView(int num, int index) {
        if(index == 1) {
            switch (num) {
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
        }else if(index == 2) {
            switch (num) {
                case 1:
                    diceTwoAttack.setImageResource(R.drawable.diceredone);
                    rotateDice();
                    break;
                case 2:
                    diceTwoAttack.setImageResource(R.drawable.diceredtwo);
                    rotateDice();
                    break;
                case 3:
                    diceTwoAttack.setImageResource(R.drawable.diceredthree);
                    rotateDice();
                    break;
                case 4:
                    diceTwoAttack.setImageResource(R.drawable.diceredfour);
                    rotateDice();
                    break;
                case 5:
                    diceTwoAttack.setImageResource(R.drawable.diceredfive);
                    rotateDice();
                    break;
                case 6:
                    diceTwoAttack.setImageResource(R.drawable.diceredsix);
                    rotateDice();
                    break;

            }
        }else if(index == 3) {
            switch (num) {
                case 1:
                    diceThreeAttack.setImageResource(R.drawable.diceredone);
                    rotateDice();
                    break;
                case 2:
                    diceThreeAttack.setImageResource(R.drawable.diceredtwo);
                    rotateDice();
                    break;
                case 3:
                    diceThreeAttack.setImageResource(R.drawable.diceredthree);
                    rotateDice();
                    break;
                case 4:
                    diceThreeAttack.setImageResource(R.drawable.diceredfour);
                    rotateDice();
                    break;
                case 5:
                    diceThreeAttack.setImageResource(R.drawable.diceredfive);
                    rotateDice();
                    break;
                case 6:
                    diceOneAttack.setImageResource(R.drawable.diceredsix);
                    rotateDice();
                    break;

            }
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
