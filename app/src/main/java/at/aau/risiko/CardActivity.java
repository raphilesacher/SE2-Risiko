package at.aau.risiko;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import at.aau.risiko.core.CardList;
import at.aau.risiko.core.HandDeck;


public class CardActivity extends AppCompatActivity {

    CardList cardDeck;
    HandDeck handDeck;

    LinearLayout linearLayout_handDeck;

    ArrayList<Integer> imgIds;
    LayoutInflater inflater;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        cardDeck = new CardList();
        handDeck = new HandDeck();
        cardDeck.fillUpCardlistForStart();

        drawCards();


       inflater = LayoutInflater.from(this);
       initDataForShowingHandDeck();
       showCards();

       //initView();
       //addCardToSelection();


    }

    private void drawCards(){

        handDeck.addCardToHandDeck(cardDeck.drawCardFromCardList());
        handDeck.addCardToHandDeck(cardDeck.drawCardFromCardList());
        handDeck.addCardToHandDeck(cardDeck.drawCardFromCardList());
        handDeck.addCardToHandDeck(cardDeck.drawCardFromCardList());
        handDeck.addCardToHandDeck(cardDeck.drawCardFromCardList());
      

    }


    private void initDataForShowingHandDeck()
    {
        imgIds=new ArrayList<>();

        for (int i = 0; i < handDeck.size(); i++)
        {
            String card = handDeck.getCardFromHandDeck(i);

            if(card.equals("Alaska")){
                imgIds.add(R.drawable.ca_alaska);
            }
            if(card.equals("Greenland")){
                imgIds.add(R.drawable.ca_greenland);
            }
            if(card.equals("Canada")){
                imgIds.add(R.drawable.ca_canada);
            }
            if(card.equals("Central America")){
                imgIds.add(R.drawable.ca_central_amerika);
            }
            if(card.equals("Venezuela")){
                imgIds.add(R.drawable.ca_venezuela);
            }
            if(card.equals("Peru")){
                imgIds.add(R.drawable.ca_peru);
            }
            if(card.equals("Brazil")){
                imgIds.add(R.drawable.ca_brazil);
            }
            if(card.equals("Argentina")){
                imgIds.add(R.drawable.ca_argentina);
            }
            if(card.equals("North Africa")){
                imgIds.add(R.drawable.ca_north_africa);
            }
            if(card.equals("East Africa")){
                imgIds.add(R.drawable.ca_east_africa);
            }
            if(card.equals("Congo")){
                imgIds.add(R.drawable.ca_congo);
            }
            if(card.equals("South Africa")) {
                imgIds.add(R.drawable.ca_south_africa);
            }
            if (card.equals("Scandinavia")) {
                    imgIds.add(R.drawable.ca_scandinavia);
            }
            if (card.equals("Ukraine")) {
                imgIds.add(R.drawable.ca_ukraine);
            }
            if (card.equals("Western Europe")) {
                imgIds.add(R.drawable.ca_western_europe);
            }
            if (card.equals("Indonesia")) {
                imgIds.add(R.drawable.ca_indonesia);
            }
            if (card.equals("Western Australia")) {
                imgIds.add(R.drawable.ca_western_australia);
            }
            if (card.equals("Eastern Australia")) {
                imgIds.add(R.drawable.ca_eastern_australia);
            }
            if (card.equals("Siam")) {
                imgIds.add(R.drawable.ca_siam);
            }
            if (card.equals("India")) {
                imgIds.add(R.drawable.ca_india);
            }
            if (card.equals("China")) {
                imgIds.add(R.drawable.ca_china);
            }
            if (card.equals("Mongolia")) {
                imgIds.add(R.drawable.ca_mongolia);
            }
            if (card.equals("Siberia")) {
                imgIds.add(R.drawable.ca_siberia);
            }
            if (card.equals("Ural")) {
                imgIds.add(R.drawable.ca_ural);
            }
            if (card.equals("Middle East")) {
                imgIds.add(R.drawable.ca_middle_east);
            }
            if (card.equals("USA")) {
                imgIds.add(R.drawable.ca_usa);
            }
            if (card.equals("Joker1")) {
                imgIds.add(R.drawable.ca_joker1);
            }
            if (card.equals("Joker2")) {
                imgIds.add(R.drawable.ca_joker2);
            }
        }


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