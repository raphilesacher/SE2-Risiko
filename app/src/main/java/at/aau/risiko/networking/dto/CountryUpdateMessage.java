package at.aau.risiko.networking.dto;

import android.widget.Button;

import at.aau.risiko.core.Country;

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
