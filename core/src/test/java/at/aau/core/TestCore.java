package at.aau.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

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

        assertTrue(card.getCardName() == name);
        assertTrue(card.getCardType() == type);
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
    public void testCardList_ExchangeCards_Succeeded() {
        CardList cardList = new CardList();
        cardList.fillUpCardlistForStart();

        String[] deck = new String[3];

        int index = 0;
        while(cardList.checkIfCardsAvailable() && index < 3) {
            String card = cardList.drawCardFromCardList();
            if (cardList.returnCardTypeFoundByName(card) == "infantry") {
                deck[index] = card;
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

    }

    // Country
    @Test
    public void testCountry() {

    }

    // Dice
    @Test
    public void testDice() {

    }

    // HandDeck
    @Test
    public void testHandDeck() {

    }

    // Player
    @Test
    public void testPlayer() {

    }

}
