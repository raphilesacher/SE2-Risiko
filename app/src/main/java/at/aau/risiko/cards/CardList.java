package at.aau.risiko.cards;

import java.util.ArrayList;

public class CardList {

    public static ArrayList<Card> createCardList(){
        Card alaska = new Card("Alaska","infantry",false);
        Card greenland = new Card("Greenland","cavalry",false);
        Card canada = new Card("Canada","artillary",false);
        Card centralAmerika = new Card("Central America","infantry",false);
        Card venezuela = new Card("Venezuela","cavalry",false);
        Card peru = new Card("Perus","artillary",false);
        Card brazil = new Card("Brazil","infantry",false);
        Card argentina = new Card("Argentina","cavalry",false);
        Card northAfrica = new Card("North Africa","artillary",false);
        Card eastAfrika = new Card("East Africa","infantry",false);
        Card congo = new Card("Congo","cavalry",false);
        Card southAfrica = new Card("South Africa","artillary",false);
        Card scandinavia = new Card("Scandinavia","infantry",false);
        Card ukraine = new Card("Ukraine","cavalry",false);
        Card southerEurope = new Card("Southern Europe","artillary",false);
        Card westernEurope = new Card("Western Europe","infantry",false);
        Card indonesia = new Card("Indonesia","cavalry",false);
        Card westernAustralia = new Card("Western Australia","artillary",false);
        Card easternAustralia = new Card("Eastern Australia","infantry",false);
        Card siam = new Card("Siamn","cavalry",false);
        Card india = new Card("India","artillary",false);
        Card china = new Card("China","infantry",false);
        Card mongolia = new Card("Mongolia","cavalry",false);
        Card siberia = new Card("Siberia","artillary",false);
        Card ural = new Card("Ural","infantry",false);
        Card middleEast = new Card("Middle East","cavalry",false);


        Card joker1 = new Card("Joker","joker",false);
        Card joker2 = new Card("Joker","joker",false);


        ArrayList<Card> cardList = new ArrayList<>();


        cardList.add(alaska);
        cardList.add(greenland);
        cardList.add(canada);
        cardList.add(alaska);
        cardList.add(centralAmerika);
        cardList.add(venezuela);
        cardList.add(peru);
        cardList.add(brazil);
        cardList.add(argentina);
        cardList.add(northAfrica);
        cardList.add(eastAfrika);
        cardList.add(congo);
        cardList.add(southAfrica);
        cardList.add(scandinavia);
        cardList.add(ukraine);
        cardList.add(southerEurope);
        cardList.add(westernEurope);
        cardList.add(indonesia);
        cardList.add(westernAustralia);
        cardList.add(easternAustralia);
        cardList.add(siam);
        cardList.add(india);
        cardList.add(china);
        cardList.add(mongolia);
        cardList.add(siberia);
        cardList.add(ural);
        cardList.add(middleEast);
        cardList.add(joker1);
        cardList.add(joker2);



      return cardList;

    }



}
