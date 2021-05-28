package at.aau.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCore {

    // Card
    @Test
    public void testCard() {
        String name = "Alaska";
        String type = "infantry";
        Card card = new Card(name, type, false, false);

        assertEquals(name, card.getCardName());
        assertEquals(type, card.getCardType());
        assertFalse(card.isCardIsdrawn());
        assertFalse(card.isCardIsExchanged());

        card.setCardIsdrawn(true);
        card.setCardIsExchanged(true);

        assertTrue(card.isCardIsdrawn());
        assertTrue(card.isCardIsExchanged());
    }

    // CardList
    @Test
    public void testCardList_DrawCard_Empty() {
        CardList cardList = new CardList();

        assertFalse(cardList.checkIfCardsAvailable());
        assertEquals("No Cards available!", cardList.drawCardFromCardList());
    }

    @Test
    public void testCardList_DrawCard_Filled() {
        CardList cardList = new CardList();
        cardList.fillUpCardlistForStart();

        assertTrue(cardList.checkIfCardsAvailable());
        // assertEquals();
    }

    @Test
    public void testCardList_ExchangeCards_Failed() {
        CardList cardList = new CardList();
        cardList.fillUpCardlistForStart();

        String[] deck = new String[3];
        String[] type = {"infantry", "artillery"};

        int index = 0;

        while(cardList.checkIfCardsAvailable() && index < 3) {
            String card = cardList.drawCardFromCardList();
            if (cardList.returnCardTypeFoundByName(card) == type[index % type.length]) {
                deck[index] = card;
                ++index;
            }
        }

        // TODO: add call to exchangeCards()!
        assertFalse(cardList.checkIfCombinationOfCardsCanBeExchanged(deck[0], deck[1], deck[2]));
    }

    @Test
    public void testCardList_ExchangeCards_AllSame() {
        LinkedList<String> type = new LinkedList<>();
        type.add("infantry");
        type.add("infantry");
        type.add("infantry");
        exchangeCardsHelper(type);
    }

    @Test
    public void testCardList_ExchangeCards_AllDifferent() {
        LinkedList<String> type = new LinkedList<>();
        type.add("infantry");
        type.add("cavalry");
        type.add("artillery");
        exchangeCardsHelper(type);
    }

    @Test
    public void testCardList_ExchangeCards_TwoSameJoker() {
        LinkedList<String> type = new LinkedList<>();
        type.add("infantry");
        type.add("infantry");
        type.add("joker");
        exchangeCardsHelper(type);
    }

    /*
    // TODO: add missing check for two different + joker in CardList!
    @Test
    public void testCardList_ExchangeCards_TwoDifferentJoker() {
        LinkedList<String> type = new LinkedList<>();
        type.add("infantry");
        type.add("cavalry");
        type.add("joker");
        exchangeCardsHelper(type);
    }
    */

    public void exchangeCardsHelper(LinkedList<String> type) {
        CardList cardList = new CardList();
        cardList.fillUpCardlistForStart();

        String[] deck = new String[3];

        int index = 0;
        while(cardList.checkIfCardsAvailable() && index < 3) {
            String card = cardList.drawCardFromCardList();
            if (type.contains(cardList.returnCardTypeFoundByName(card))) {
                deck[index] = card;
                type.remove(cardList.returnCardTypeFoundByName(card));
                ++index;
            }
        }
        // TODO: check call to exchangeCards in some way!
        assertTrue(cardList.checkIfCombinationOfCardsCanBeExchanged(deck[0], deck[1], deck[2]));
        assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                cardList.exchangeCards(deck[0], deck[1], deck[2]);
            }
        });
    }


    // Continent
    @Test
    public void testContinent() {
        String name = "Antarctica";
        Continent continent = new Continent(name);

        assertEquals(name, continent.getName());
        assertEquals(0, continent.getCountries());
        assertEquals(0, continent.getBonus());
    }

    // Country
    @Test
    public void testCountry() {
        String nameCountry = "Alaska";
        String nameNeighbor = "Columbia";
        Country country = new Country(nameCountry);
        Country neighbor = new Country(nameNeighbor);

        assertEquals(nameCountry, country.getName());
        assertEquals(0, country.getArmies());

        country.addNeighbor(neighbor);
        country.getNeighbors().contains(neighbor);
    }

    // Dice
    @Test
    public void testDice() {

    }

    // HandDeck
    @Test
    public void testHandDeck() {
        HandDeck handDeck = new HandDeck();
        Card cardOne = new Card("Alaska", "infantry", false, false);
        Card cardTwo = new Card("Alaska", "infantry", false, false);

        assertEquals(0, handDeck.size());

        handDeck.addCardToHandDeck(cardOne.getCardName());
        handDeck.addCardToHandDeck(cardTwo.getCardName());
        assertEquals(2, handDeck.size());

        assertEquals(cardOne.getCardName(), handDeck.getCardFromHandDeck(0));

        handDeck.deleteCardFromHandDeck(cardOne.getCardName());
        assertEquals(1, handDeck.size());
    }

    @Test
    public void testHandDeck_Selection() {
        HandDeck handDeck = new HandDeck();
        Card cardOne = new Card("Alaska", "infantry", false, false);
        Card cardTwo = new Card("Alaska", "infantry", false, false);

        assertEquals(0, handDeck.sizeOfSelection());

        handDeck.addCardToSelection(cardOne.getCardName());
        handDeck.addCardToSelection(cardTwo.getCardName());
        assertEquals(2, handDeck.sizeOfSelection());

        assertEquals(cardOne.getCardName(), handDeck.getCardFromSelection(0));

        handDeck.deleteCardFromSelection(cardOne.getCardName());
        assertEquals(1, handDeck.sizeOfSelection());

        handDeck.deleteAllCardsFromSelection();
        assertEquals(0, handDeck.sizeOfSelection());
    }

    // Player
    @Test
    public void testPlayer() {
        String name = "Hans";
        Integer color = 0xFF000000;
        Player player = new Player(name, color);

        assertEquals(name, player.getName());
        assertEquals(color, player.getColor());
    }

    // Parser:
    @Test
    public void testParser() {
        DataParser.printCountries();
        DataParser.printCards();
    }

}
