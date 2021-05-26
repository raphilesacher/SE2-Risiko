package at.aau.server.networking.dto;

public class CountryUpdateMessage extends BaseMessage {

    public CountryUpdateMessage() {
    }

    public CountryUpdateMessage(Country c, int buttonId) {
        this.c = c;
        ButtonId = buttonId;
    }

    public Country c;
    public int ButtonId;
}
