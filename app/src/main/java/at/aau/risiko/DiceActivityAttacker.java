package at.aau.risiko;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

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
    /*This variable is needed to set the exact amount of dices needed*/
    int numAttackers = 3;
    /*This boolean needs to be set to true after the dices have been rolled to send to the Defender so the UI can be updated*/
    boolean isShaken = false;
    /*if hasShakenDefender == true the Attacker is allowed to roll the dices*/
    boolean hasShakenDefender = false;
    //dice should only be rolled if acceleration is > SHAKE_THRESHOLD
    final static int SHAKE_THRESHOLD = 3;




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

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
