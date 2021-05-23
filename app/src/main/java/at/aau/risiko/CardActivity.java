package at.aau.risiko;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.zip.Inflater;


public class CardActivity extends AppCompatActivity {

    LinearLayout handDeck;
    int[] ImgIds;
    LayoutInflater inflater;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);


       inflater = LayoutInflater.from(this);
       initData();
       initView();

    }


    private void initData()
    {
        ImgIds = new int[] { R.drawable.ca_alaska, R.drawable.ca_argentina, R.drawable.ca_brazil,R.drawable.ca_canada, R.drawable.ca_central_amerika, R.drawable.ca_china};
    }

    private void initView()
    {
        handDeck = findViewById(R.id.id_handdeck);

        for (int i = 0; i < ImgIds.length; i++)
        {

            View view = inflater.inflate(R.layout.handdeck_item, handDeck, false);
            ImageView img = view.findViewById(R.id.handdeck_item);
            img.setImageResource(ImgIds[i]);

            handDeck.addView(view);
        }
    }


}