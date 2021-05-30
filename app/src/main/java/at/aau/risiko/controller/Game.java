package at.aau.risiko.controller;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    private Activity activity;
    HashMap<Integer, Country> buttonMap;
    HashMap<Integer, Player> avatarMap;


    public Game(Player[] players, HashMap<Integer, Country> buttonMap, HashMap<Integer, Player> avatarMap, Activity activity) {
        this.state = new SetupState(this);
        this.players = players;
        this.availableCountries = new LinkedList<>();
        this.cardDeck = new CardList();
        this.index = 0;

        this.activity = activity;
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
        ProgressBar bar = ((MapActivity) activity).findViewById(R.id.progressBar);
        bar.setProgress(progress);
    }

    public void showToast(String message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this.activity, message, duration);
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
            TurnMessage update = (TurnMessage) message;

            // Set current player:
            for (Map.Entry<Integer, Player> e : avatarMap.entrySet()) {
                if (e.getValue().getName().equals(update.playerName)) {
                    // TODO: FIX THREAD ACCESS PROBLEMS!
                    // ((ImageView)activity.findViewById(e.getKey())).setMinimumWidth(120);
                    // ((ImageView)activity.findViewById(e.getKey())).setMinimumHeight(160);
                }
            }

            // Start new turn:
            if (this.getAvailableCountries().size() > 0) {
                this.setState(new SetupState(this));
            } else {
                this.setState(new DraftState(this));
            }
        } else if (message instanceof UpdateMessage) {
            // game.sendMessage(new UpdateMessage(null, ?.getName(), ?.getArmies()));
            UpdateMessage update = (UpdateMessage) message;

            // Find country by name:
            Button button = null;
            Country country = null;
            for (Map.Entry<Integer, Country> e : buttonMap.entrySet()) {
                if (e.getValue().getName().equals(update.countryName)) {
                    button = (Button) activity.findViewById(e.getKey());
                    country = e.getValue();
                    break;
                }
            }

            // Find player by name:
            Player player;
            for (Map.Entry<Integer, Player> e : avatarMap.entrySet()) {
                if (e.getValue().getOccupied().contains(country)) {
                    e.getValue().getOccupied().remove(country);
                }
                if (e.getValue().getName().equals(update.playerName)) {
                    player = e.getValue();
                    player.getOccupied().add(country);
                    availableCountries.remove(country);
                    country.setColor(player.getColor());
                }
            }

            Log.i("UPDATE MESSAGE BEFORE", String.valueOf(country.getArmies()));

            country.setArmies(update.armies);
            button.setBackgroundTintList(ColorStateList.valueOf(country.getColor()));
            button.setText(String.valueOf(country.getArmies()));

            Log.i("UPDATE MESSAGE AFTER", String.valueOf(country.getArmies()));

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
        return activity;
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
