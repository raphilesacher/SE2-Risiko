package at.aau.risiko.core;

import android.content.Context;
import android.view.View;

import java.util.HashMap;

public class Game {

    // Game is the local instance of the controller.
    State state;
    Player[] players;
    int index;

    HashMap<Integer, Country> buttons;


    public Game(Player[] players, HashMap<Integer, Country> buttonMapping) {
        this.state = new ObserveState(this);
        this.players = players;
        this.index = 0;
        this.buttons = buttonMapping;
    }


    // Methods:
    
    public void handleInput(View view) {
        state.handleInput(view);
    }

    public void changeState() {
        state.changeState();
    }

}
