package at.aau.risiko.cards;

import java.util.ArrayList;

public class CardList {

    public static ArrayList<Card> createCardList(){
        Card afghanistan = new Card("Afghanistan","Kavallerie",false);
        Card agypten = new Card("Ägypten","Infanterie",false);
        Card alaska = new Card("Alaska","Infanterie",false);
        Card alberta = new Card("Alberta","Kavallerie",false);
        Card argentinien = new Card("Argentinien","Infanterie",false);
        Card brasilien = new Card("Brasilien","Artillerie",false);
        Card china = new Card("China","Infanterie",false);
        Card groenland = new Card("Grönland","Kavallerie",false);
        Card grossbritannien = new Card("Großbritannien","Artillerie",false);
        Card indien = new Card("Indien","Kavallerie",false);
        Card indonesien = new Card("Indonesien","Artillerie",false);
        Card irkutsk = new Card("Irkutsk","Kavallerie",false);
        Card island = new Card("Island","Infanterie",false);
        Card jakutien = new Card("Jakutien","Kavallerie",false);
        Card japan = new Card("Japan","Artillerie",false);
        Card kamtschatka = new Card("Kamtschatka","Infanterie",false);
        Card madagaskar = new Card("Madagaskar","Kavallerie",false);
        Card mittelamerika = new Card("Mittelamerika","Artillerie",false);
        Card mongolei = new Card("Mongolei","Infanterie",false);
        Card naherOsten = new Card("Naher Osten","Infanterie",false);
        Card neuguinea = new Card("Neuguinea","Infanterie",false);


        Card joker1 = new Card("Joker","Joker",false);
        Card joker2 = new Card("Joker","Joker",false);


        ArrayList<Card> cardList = new ArrayList<>();


        cardList.add(afghanistan);
        cardList.add(agypten);
        cardList.add(alaska);
        cardList.add(alberta);
        cardList.add(argentinien);
        cardList.add(brasilien);
        cardList.add(china);
        cardList.add(groenland);
        cardList.add(grossbritannien);
        cardList.add(indien);
        cardList.add(indonesien);
        cardList.add(irkutsk);
        cardList.add(island);
        cardList.add(jakutien);
        cardList.add(japan);
        cardList.add(kamtschatka);
        cardList.add(madagaskar);
        cardList.add(mittelamerika);
        cardList.add(mongolei);
        cardList.add(naherOsten);
        cardList.add(neuguinea);
        cardList.add(joker1);
        cardList.add(joker2);



      return cardList;

    }



}
