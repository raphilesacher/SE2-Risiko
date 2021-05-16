package at.aau.risiko.cards;

import java.util.ArrayList;
import java.util.Random;

public class CardList {

    ArrayList<Card> cardList = new ArrayList<>();

    public CardList() {
        this.cardList = cardList;
    }

    public void fillUpCardlistForStart(){
        Card alaska = new Card("Alaska","infantry",false, false);
        Card greenland = new Card("Greenland","cavalry",false, false);
        Card canada = new Card("Canada","artillary",false, false);
        Card centralAmerika = new Card("Central America","infantry",false, false);
        Card venezuela = new Card("Venezuela","cavalry",false, false);
        Card peru = new Card("Peru","artillary",false, false);
        Card brazil = new Card("Brazil","infantry",false, false);
        Card argentina = new Card("Argentina","cavalry",false, false);
        Card northAfrica = new Card("North Africa","artillary",false, false);
        Card eastAfrika = new Card("East Africa","infantry",false, false);
        Card congo = new Card("Congo","cavalry",false, false);
        Card southAfrica = new Card("South Africa","artillary",false, false);
        Card scandinavia = new Card("Scandinavia","infantry",false, false);
        Card ukraine = new Card("Ukraine","cavalry",false, false);
        Card southerEurope = new Card("Southern Europe","artillary",false, false);
        Card westernEurope = new Card("Western Europe","infantry",false, false);
        Card indonesia = new Card("Indonesia","cavalry",false, false);
        Card westernAustralia = new Card("Western Australia","artillary",false, false);
        Card easternAustralia = new Card("Eastern Australia","infantry",false, false);
        Card siam = new Card("Siamn","cavalry",false, false);
        Card india = new Card("India","artillary",false, false);
        Card china = new Card("China","infantry",false, false);
        Card mongolia = new Card("Mongolia","cavalry",false, false);
        Card siberia = new Card("Siberia","artillary",false, false);
        Card ural = new Card("Ural","infantry",false, false);
        Card middleEast = new Card("Middle East","cavalry",false, false);


        Card joker1 = new Card("Joker","joker",false, false);
        Card joker2 = new Card("Joker","joker",false, false);


        this.cardList.add(alaska);
        this.cardList.add(greenland);
        this.cardList.add(canada);
        this.cardList.add(centralAmerika);
        this.cardList.add(venezuela);
        this.cardList.add(peru);
        this.cardList.add(brazil);
        this.cardList.add(argentina);
        this.cardList.add(northAfrica);
        this.cardList.add(eastAfrika);
        this.cardList.add(congo);
        this.cardList.add(southAfrica);
        this.cardList.add(scandinavia);
        this.cardList.add(ukraine);
        this.cardList.add(southerEurope);
        this.cardList.add(westernEurope);
        this.cardList.add(indonesia);
        this.cardList.add(westernAustralia);
        this.cardList.add(easternAustralia);
        this.cardList.add(siam);
        this.cardList.add(india);
        this.cardList.add(china);
        this.cardList.add(mongolia);
        this.cardList.add(siberia);
        this.cardList.add(ural);
        this.cardList.add(middleEast);
        this.cardList.add(joker1);
        this.cardList.add(joker2);

    }

    public int size() {
        return this.cardList.size();
    }

    public Card get(int i) {
        return this.cardList.get(i);
    }

    public  String drawCardFromCardList() {

        String drawnCard = "";
        boolean succes = false;

        if (checkIfCardsAvailable()) {

            while (!succes) {

                int bound = this.cardList.size();
                Random rand = new Random();
                int randomInt = rand.nextInt(bound);
                if (!this.cardList.get(randomInt).isCardIsdrawn()) {
                    drawnCard = this.cardList.get(randomInt).cardName;
                    this.cardList.get(randomInt).setCardIsdrawn(true);
                    succes = true;
                }

            }
        }
        else {
            drawnCard="No Cards available!";

        }

        return drawnCard;
    }

    public boolean checkIfCardsAvailable() {

        boolean cardsAvailable = true;
        int availableCards = 0;

        for (int i = 0; i < this.cardList.size(); i++) {
            if (!this.cardList.get(i).isCardIsdrawn()) {
                availableCards++;
            }
        }

        if (availableCards == 0) {
            cardsAvailable = false;
        }

        return cardsAvailable;
    }

    public boolean checkIfCardsCanBeExchanged(Card a, Card b, Card c){

        boolean allowed = false;
        String typeA=a.getCardType();
        String typeB=b.getCardType();
        String typeC=c.getCardType();

        System.out.println(typeA);
        System.out.println(typeB);
        System.out.println(typeC);

        //dreimal das gleiche Symbol
        if(typeA.equals(typeB)&&typeA.equals(typeC)&&typeB.equals(typeC)){
            allowed=true;
        }
        //drei unterschiedliche Symbole, aber kein Joker
        if(!typeA.equals(typeB)&&!typeA.equals(typeC)&&!typeB.equals(typeC)&&!typeA.equals("joker")&&!typeB.equals("joker")&&!typeC.equals("joker")){
            allowed=true;
        }

        //Erste Karte Joker, die anderen beiden gleich
        if(typeA.equals("joker")&&typeB.equals(typeC)){
            allowed=true;
        }

        //Zweite Karte Joker, die anderen beiden gleich
        if(typeB.equals("joker")&&typeA.equals(typeC)){
            allowed=true;
        }

        //Dritte Karte Joker, die anderen beiden gleich
        if(typeC.equals("joker")&&typeA.equals(typeB)){
            allowed=true;
        }

        return allowed;

    }



}
