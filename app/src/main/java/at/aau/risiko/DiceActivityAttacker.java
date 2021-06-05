package at.aau.risiko;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import at.aau.core.Dice;

public class DiceActivityAttacker extends AppCompatActivity implements SensorEventListener {
    /*these variables are needed to track the sensor event*/
    private SensorManager sensorManager;
    private Sensor accelerometer;

    /*UI Variables*/
    private ImageView diceOneAttack;
    private ImageView diceTwoAttack;
    private ImageView diceThreeAttack;

    /**
     *ToDo: replace this Variables with getNumAttackers() from Daniel's feature to set exactly the amount of dices needed
     */
    /*This variables are needed to set the exact amount of dices needed*/
    int numAttackers = 3;
    int numDefenders = 2;
    /*This boolean needs to be set to true after the dices have been rolled to send to the Defender so the UI can be updated*/
    boolean isShaken = false;
    /*if hasRolledDefender == true the Attacker is allowed to roll the dices*/
    boolean hasRolledDefender = false;
    //dice should only be rolled if acceleration is > SHAKE_THRESHOLD
    final static int SHAKE_THRESHOLD = 3;
    int diceOneNum;
    int diceTwoNum;
    int diceThreeNum;
    /*count variable for insurance that dices can only be rolled once*/
    int count = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        diceOneAttack = findViewById(R.id.diceOneAttack);
        diceTwoAttack = findViewById(R.id.diceTwoAttack);
        diceThreeAttack = findViewById(R.id.diceThreeAttack);
        /*these variables are only for updating the UI with the according to the server message*/
        ImageView diceOneDefense = findViewById(R.id.diceOneDefense);
        ImageView diceTwoDefense = findViewById(R.id.diceTwoDefense);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        Button closeBtn = (Button)findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //close the activity
                finish();
            }
        });

        /**
         * ToDo: read server message from DiceActivityDefender and set hasShakenDefender and update the UI
         */
        if(isShaken) {
            /**
             * ToDo: send server message to DiceActivityDefender if isShaken is true so that the UI can be updated and change state
             */
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

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        /*only allow to roll dice if the defender has rolled his*/
        if(hasRolledDefender && count < 1) {
            //variables for tracking the motion of the device on x-, y- and z-axis
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            //calculate the movement value
            double accelerationValue = Math.sqrt((x * x + y * y + z * z)) - SensorManager.GRAVITY_EARTH;
            //if accelerationValue > SHAKE_THRESHOLD call rollDice
            Dice dice = new Dice("attacker");
            if (accelerationValue > SHAKE_THRESHOLD && accelerationValue < 30) {


                for (int i = 0; i < numAttackers; i++) {
                    int num = dice.diceRoll();
                    //diceNum.setText("dice have been rolled:" + num);
                    //accel.setText("Acceleration: " + (int)accelerationValueDefender);
                    dice.setEyeNumber(num);

                    setImageViewAttacker(num, i + 1);

                }
                count++;
                isShaken = true;
                Log.i("DiceActivity", "Device was shaken");
            }else {
                return;
            }
            //cheat function
            if (accelerationValue > 30) {
                dice.setEyeNumber(6);
                for (int index = 0; index < numAttackers; index++) {
                    setImageViewAttacker(6, index + 1);
                }
                count++;
                isShaken = true;
            }

            System.out.println("Attacker rolled dice");

            return;

        }else {
            return;
        }


    }




    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

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
                default:
                    break;

            }

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
                default:
                    break;

            }

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
                default:
                    break;
            }

        }
        rotateDice(index);
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


}
