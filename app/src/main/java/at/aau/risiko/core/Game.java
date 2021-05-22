package at.aau.risiko.core;

import android.content.Context;
import android.view.View;

import java.util.HashMap;

public class Game {

    public Context getContext() {
        return context;
    }

    // Game is the local instance of the controller.
    private Context context;
    private State state;
    private Player[] players;
    private int index;

    HashMap<Integer, Country> buttonMap;
    HashMap<Integer, Player> avatarMap;


    public Game(Player[] players, HashMap<Integer, Country> buttonMapping, Context context) {
        this.state = new SetupState(this);
        this.players = players;
        this.index = 0;
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


    // Getters & Setters:

    public State getState() {
        return state;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getIndex() {
        return index;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
