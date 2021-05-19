package at.aau.risiko;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.io.IOException;



public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< Updated upstream
        setContentView(R.layout.activity_dice);
=======
        setContentView(R.layout.activity_main);

        Button diceBtn = findViewById(R.id.diceBtn);

        diceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DiceActivity.class);
                startActivity(intent);
            }
        });
>>>>>>> Stashed changes


    }


}