package at.aau.risiko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import at.aau.risiko.core.CardList;
import at.aau.risiko.core.HandDeck;


public class CardActivity extends AppCompatActivity {

    CardList cardDeck = new CardList();
    HandDeck handDeck = new HandDeck();




    LinearLayout linearLayout_handDeck;
    

    ArrayList<Integer> imgIds;
    LayoutInflater inflater;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        cardDeck.fillUpCardlistForStart();


       inflater = LayoutInflater.from(this);
       initData();
       showCards();
       //initView();
       //addCardToSelection();


    }


    private void initData()
    {
        imgIds=new ArrayList<>();
        imgIds.add(R.drawable.ca_alaska);
        imgIds.add(R.drawable.ca_argentina);
        imgIds.add(R.drawable.ca_brazil);
        imgIds.add(R.drawable.ca_canada);
      }


    private void showCards(){
        for (int i = 0; i < imgIds.size(); i++)
        {
            if(i==0) {

                ImageView img = findViewById(R.id.id_handdeck_card_0);
                img.setImageResource(imgIds.get(i));
            }
            if(i==1) {

                ImageView img = findViewById(R.id.id_handdeck_card_1);
                img.setImageResource(imgIds.get(i));
            }
            if(i==2) {

                ImageView img = findViewById(R.id.id_handdeck_card_2);
                img.setImageResource(imgIds.get(i));
            }
            if(i==3) {

                ImageView img = findViewById(R.id.id_handdeck_card_3);
                img.setImageResource(imgIds.get(i));
            }
            if(i==4) {

                ImageView img = findViewById(R.id.id_handdeck_card_4);
                img.setImageResource(imgIds.get(i));
            }
            if(i==5) {

                ImageView img = findViewById(R.id.id_handdeck_card_5);
                img.setImageResource(imgIds.get(i));
            }
            if(i==6) {

                ImageView img = findViewById(R.id.id_handdeck_card_6);
                img.setImageResource(imgIds.get(i));
            }
            if(i==7) {

                ImageView img = findViewById(R.id.id_handdeck_card_7);
                img.setImageResource(imgIds.get(i));
            }
            if(i==8) {

                ImageView img = findViewById(R.id.id_handdeck_card_8);
                img.setImageResource(imgIds.get(i));
            }
            if(i==9) {

                ImageView img = findViewById(R.id.id_handdeck_card_9);
                img.setImageResource(imgIds.get(i));
            }

        }

    }


    private void initView()
    {




        linearLayout_handDeck = findViewById(R.id.id_handdeck);

        for (int i = 0; i < imgIds.size(); i++)
        {

            View view = inflater.inflate(R.layout.handdeck_item, linearLayout_handDeck, false);
            ImageView img = view.findViewById(R.id.id_handdeck_item);
            img.setImageResource(imgIds.get(i));

            linearLayout_handDeck.addView(view);
        }
    }


    public void addCardToSelection() {




    }
}