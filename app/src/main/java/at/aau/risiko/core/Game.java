package at.aau.risiko.core;

import android.view.View;

import java.util.HashMap;

public class Game {

    // Game is the local instance of the controller.
    private State state;
    private Player[] players;
    private int index;

    HashMap<Integer, Country> buttonMap;
    HashMap<Integer, Player> avatarMap;


    public Game(Player[] players, HashMap<Integer, Country> buttonMapping) {
        this.state = new SetupState(this);
        this.players = players;
        this.index = 0;
        this.buttonMap = buttonMapping;
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
