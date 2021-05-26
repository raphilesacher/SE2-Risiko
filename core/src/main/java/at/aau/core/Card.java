package at.aau.core;
/*Diese Klasse repräsentiert die Spielkarten. In der Variable cardName wird der Name des Landes gespeichert, zu dem die
jeweilige Karte zugeordnet ist. In der Variable cardType wird entweder "artillary", "cavalry", "infantry" oder
"joker" gespeichert.
Dies dient dazu um die Karten eintauschen zu können. Im boolean cardIsDrawn wird hinterlegt, ob die jeweilige Karte
im Spiel bereits gezogen wurde.*/

public class Card {

    String cardName;
    String cardType;
    boolean cardIsdrawn;
    boolean cardIsExchanged;


    //Constructor für den Datentyp Card
    public Card(String cardName, String cardType, boolean cardIsdrawn, boolean cardIsexchanged) {
        this.cardName = cardName;
        this.cardType = cardType;
        this.cardIsdrawn = cardIsdrawn;
        this.cardIsExchanged = cardIsexchanged;
    }


    //Getter und Setter für die Klasse Card
    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public boolean isCardIsdrawn() {
        return cardIsdrawn;
    }

    public void setCardIsdrawn(boolean cardIsdrawn) {
        this.cardIsdrawn = cardIsdrawn;
    }

    public boolean isCardIsExchanged() {
        return cardIsExchanged;
    }

    public void setCardIsExchanged(boolean cardIsExchanged) {
        this.cardIsExchanged = cardIsExchanged;
    }
}
