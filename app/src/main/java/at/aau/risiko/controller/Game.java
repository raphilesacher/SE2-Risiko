package at.aau.risiko.controller;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import at.aau.core.CardList;
import at.aau.core.Country;
import at.aau.core.Player;
import at.aau.risiko.MapActivity;
import at.aau.risiko.R;
import at.aau.server.dto.BaseMessage;
import at.aau.server.kryonet.GameClient;

public class Game {

    // Game is the local instance of the controller.
    private State state;
    private final List<Player> players;
    private final List<Country> availableCountries;
    private final CardList cardDeck;
    private int index;

    private final Context context;
    HashMap<Integer, Country> buttonMap;
    HashMap<Integer, Player> avatarMap;


    public Game(List<Player> players, List<Country> countries, HashMap<Integer, Country> buttonMap, HashMap<Integer, Player> avatarMap, Context context) {
        this.state = new SetupState(this);
        this.players = players;
        this.availableCountries = countries;
        this.cardDeck = new CardList();
        this.index = 0;

        this.context = context;
        this.buttonMap = buttonMap;
        this.avatarMap = avatarMap;
    }


    // Methods:

    public void handleInput(View view) {
        state.handleInput(view);
    }

    public void changeState() {
        state.changeState();
    }


    // UI updates:

    public void setProgress(int progress) {
        ProgressBar bar = ((MapActivity) context).findViewById(R.id.progressBar);
        bar.setProgress(progress);
    }

    public void showToast(String message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this.context, message, duration);
        toast.show();
    }


    // Update server:

    public void sendMessage(BaseMessage message) {
        // Send request to server.
        GameClient.getInstance().sendMessage(message);
    }


    // Update client:

    public void handleMessage(BaseMessage message) {
        // Handle response from server.
        // This method should be called by the GameClient.
    }


    // Getters & Setters:

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Context getContext() {
        return context;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Country> getAvailableCountries() {
        return availableCountries;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public CardList getCardDeck() {
        return cardDeck;
    }

    public void setCardDeck(){
        this.cardDeck.fillUpCardlistForStart();
    }
}
