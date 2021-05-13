package at.aau.risiko;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //just for testing the dice feature
        Button diceBtn = findViewById(R.id.diceBtn);
        diceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

    }

    private void openDialog() {
        DiceActivity dialog = new DiceActivity();
        dialog.show(getSupportFragmentManager(), "dialog");
    }
}