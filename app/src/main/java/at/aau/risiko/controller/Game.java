package at.aau.risiko.controller;

import android.content.Context;
import android.util.Log;
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
import at.aau.server.dto.StartMessage;
import at.aau.server.dto.TextMessage;
import at.aau.server.dto.TurnMessage;
import at.aau.server.dto.UpdateMessage;
import at.aau.server.kryonet.GameClient;

public class Game {

    // Game is the local instance of the controller.
    private State state;
    private Player[] players;
    private List<Country> availableCountries;
    private CardList cardDeck;
    private int index;

    private Context context;
    HashMap<Integer, Country> buttonMap;
    HashMap<Integer, Player> avatarMap;


    public Game(Player[] players, HashMap<Integer, Country> buttonMapping, Context context) {
        this.state = new SetupState(this);
        this.players = players;
        this.index = 0;
        this.availableCountries = new LinkedList<>();
        this.cardDeck = new CardList();
        this.buttonMap = buttonMapping;
        this.context = context;
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
        if (message instanceof TextMessage) {
            Log.i("SERVER MESSAGE", ((TextMessage) message).text);
        } else if (message instanceof StartMessage) {
            // Do nothing.
        } else if (message instanceof TurnMessage) {
            if (this.getAvailableCountries().size() > 0) {
                this.setState(new SetupState(this));
            } else {
                this.setState(new DraftState(this));
            }
        } else if (message instanceof UpdateMessage) {
            UpdateMessage update = (UpdateMessage) message;

            // Find country by name
            Country country = null;
            for (Country c : buttonMap.values()) {
                if (c.getName() == update.country) {
                    c.setColor(update.color);
                    c.setArmies(update.armies);
                    country = c;
                    break;
                }
            }

            // Find player by name
            for (Player p : avatarMap.values()) {
                if (p.getOccupied().contains(country)) {
                    p.getOccupied().remove(country);
                }
                if (p.getName() == update.player) {
                    p.getOccupied().add(country);
                }
            }

        }
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

    public Player[] getPlayers() {
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
