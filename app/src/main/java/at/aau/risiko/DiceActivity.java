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

import at.aau.risiko.core.Dice;

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

    boolean hasRolledDiceDefender;
    boolean hasRolledDiceAttacker;
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
        /**
         * set booleans to false everytime the activity is created
         */
        hasRolledDiceDefender = false;
        hasRolledDiceAttacker = false;



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

        /**
         * let the defender roll the dice first
         */
        if (!hasRolledDiceDefender && !hasRolledDiceAttacker) {
            if(accelerationValueCurrent > SHAKE_THRESHOLD) {



                for (int i = 0; i < numDefenders; i++) {
                    int num = dice.diceRoll();
                    //diceNum.setText("dice have been rolled:" + num);
                    //accel.setText("Acceleration: " + (int)accelerationValueCurrent);
                    dice.setEyeNumber(num);

                    setImageViewDefender(num, i + 1);

                }
                Log.i("DiceActivity", "Device was shaken");
            }
            //cheat function
            if (accelerationValueCurrent > 30) {
                dice.setEyeNumber(6);
                for (int index = 0; index < numDefenders; index++) {
                    setImageViewDefender(6, index + 1);
                }
            }
            hasRolledDiceDefender = true;
        }
        /**
         * when defender has rolled the dice, the attacker can roll the dice
         */
        if (hasRolledDiceDefender && !hasRolledDiceAttacker) {
            if(accelerationValueCurrent > SHAKE_THRESHOLD) {



                for (int i = 0; i < numAttackers; i++) {
                    int num = dice.diceRoll();
                    //diceNum.setText("dice have been rolled:" + num);
                    //accel.setText("Acceleration: " + (int)accelerationValueCurrent);
                    dice.setEyeNumber(num);

                    setImageViewAttacker(num, i + 1);

                }
                Log.i("DiceActivity", "Device was shaken");
            }
            //cheat function
            if (accelerationValueCurrent > 30) {
                dice.setEyeNumber(6);
                for (int index = 0; index < numAttackers; index++) {
                    setImageViewAttacker(6, index + 1);
                }
            }
            hasRolledDiceAttacker = true;
        }


            //diceNum.setText("dice have been rolled" + dice.getEyeNumber());
            //accel.setText("Acceleration: " + (int)accelerationValueCurrent);


    }

    private void setImageViewDefender(int num, int index) {
        if (index == 1) {
            switch (num) {
                case 1:
                    diceOneDefense.setImageResource(R.drawable.diceredone);
                    break;
                case 2:
                    diceOneDefense.setImageResource(R.drawable.diceredtwo);
                    break;
                case 3:
                    diceOneDefense.setImageResource(R.drawable.diceredthree);
                    break;
                case 4:
                    diceOneDefense.setImageResource(R.drawable.diceredfour);
                    break;
                case 5:
                    diceOneDefense.setImageResource(R.drawable.diceredfive);
                    break;
                case 6:
                    diceOneDefense.setImageResource(R.drawable.diceredsix);
                    break;

            }
            rotateDice(index);
        } else if (index == 2) {
            switch (num) {
                case 1:
                    diceTwoDefense.setImageResource(R.drawable.diceredone);
                    break;
                case 2:
                    diceTwoDefense.setImageResource(R.drawable.diceredtwo);
                    break;
                case 3:
                    diceTwoDefense.setImageResource(R.drawable.diceredthree);
                    break;
                case 4:
                    diceTwoDefense.setImageResource(R.drawable.diceredfour);
                    ;
                    break;
                case 5:
                    diceTwoDefense.setImageResource(R.drawable.diceredfive);
                    break;
                case 6:
                    diceTwoDefense.setImageResource(R.drawable.diceredsix);
                    break;

            }
            rotateDice(index);
        }
    }

    private void setImageViewAttacker(int num, int index) {
        if(index == 1) {
            switch (num) {
                case 1:
                    diceOneAttack.setImageResource(R.drawable.diceredone);
                    break;
                case 2:
                    diceOneAttack.setImageResource(R.drawable.diceredtwo);
                    break;
                case 3:
                    diceOneAttack.setImageResource(R.drawable.diceredthree);
                    break;
                case 4:
                    diceOneAttack.setImageResource(R.drawable.diceredfour);
                    break;
                case 5:
                    diceOneAttack.setImageResource(R.drawable.diceredfive);
                    break;
                case 6:
                    diceOneAttack.setImageResource(R.drawable.diceredsix);
                    break;

            }
            rotateDice(index);
        }else if(index == 2) {
            switch (num) {
                case 1:
                    diceTwoAttack.setImageResource(R.drawable.diceredone);
                    break;
                case 2:
                    diceTwoAttack.setImageResource(R.drawable.diceredtwo);
                    break;
                case 3:
                    diceTwoAttack.setImageResource(R.drawable.diceredthree);
                    break;
                case 4:
                    diceTwoAttack.setImageResource(R.drawable.diceredfour);;
                    break;
                case 5:
                    diceTwoAttack.setImageResource(R.drawable.diceredfive);
                    break;
                case 6:
                    diceTwoAttack.setImageResource(R.drawable.diceredsix);
                    break;

            }
            rotateDice(index);
        }else if(index == 3) {
            switch (num) {
                case 1:
                    diceThreeAttack.setImageResource(R.drawable.diceredone);
                    break;
                case 2:
                    diceThreeAttack.setImageResource(R.drawable.diceredtwo);
                    break;
                case 3:
                    diceThreeAttack.setImageResource(R.drawable.diceredthree);
                    break;
                case 4:
                    diceThreeAttack.setImageResource(R.drawable.diceredfour);
                    break;
                case 5:
                    diceThreeAttack.setImageResource(R.drawable.diceredfive);
                    break;
                case 6:
                    diceThreeAttack.setImageResource(R.drawable.diceredsix);
                    break;
            }
            rotateDice(index);
        }
    }

    private void rotateDice(int index) {

        Animation rollAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        if(index == 1) {
            diceOneAttack.setAnimation(rollAnimation);
        }else if(index == 2) {
            diceTwoAttack.setAnimation(rollAnimation);
        }else if(index == 3) {
            diceThreeAttack.setAnimation(rollAnimation);
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
