package at.aau.risiko.controller;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

    private Context context;
    HashMap<Integer, Country> buttonMap;
    HashMap<Player, Integer> avatarMap;


    public Game(Player[] players, HashMap<Integer, Country> buttonMap, HashMap<Player, Integer> avatarMap, Context context) {
        this.state = new SetupState(this);
        this.players = players;
        this.availableCountries = new LinkedList<>();
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
        if (message instanceof TextMessage) {
            Log.i("SERVER MESSAGE", ((TextMessage) message).text);
        } else if (message instanceof StartMessage) {
            // Do nothing.
        } else if (message instanceof TurnMessage) {
            TurnMessage update = (TurnMessage) message;

            // Set current player:
            for (Player p : avatarMap.keySet()) {
                if (p.getName() == update.playerName) {
                    ((ImageView)((MapActivity)context).findViewById(avatarMap.get(p))).setScaleX(1.2f);
                    ((ImageView)((MapActivity)context).findViewById(avatarMap.get(p))).setScaleY(1.2f);
                }
            }

            // Start new turn:
            if (this.getAvailableCountries().size() > 0) {
                this.setState(new SetupState(this));
            } else {
                this.setState(new DraftState(this));
            }
        } else if (message instanceof UpdateMessage) {
            Log.i("UPDATE MESSAGE", "Yeah I got it.");
            UpdateMessage update = (UpdateMessage) message;

            // Find country by name:
            // Update country button:
            Button button = null;
            Country country = null;
            for (Map.Entry<Integer, Country> e : buttonMap.entrySet()) {
                if (e.getValue().getName() == update.countryName) {
                    button = (Button)((MapActivity)context).findViewById(e.getKey());
                    button.setText(country.getArmies());
                    country = e.getValue();
                    country.setColor(update.color);
                    country.setArmies(update.armies);
                    break;
                }
            }

            // Find player by name:
            Player player;
            for (Player p : avatarMap.keySet()) {
                if (p.getOccupied().contains(country)) {
                    p.getOccupied().remove(country);
                }
                if (p.getName() == update.playerName) {
                    player = p;
                    player.getOccupied().add(country);
                    button.setBackgroundTintList(ColorStateList.valueOf(player.getColor()));
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
