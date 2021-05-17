package at.aau.risiko;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dice);

    }

    public void showDialog()
    {
        final Dialog d = new Dialog(MainActivity.this);
        d.setTitle("Truppen verstärken");
        d.setContentView(R.layout.strenghten);
    }
}