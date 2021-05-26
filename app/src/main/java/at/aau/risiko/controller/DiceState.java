package at.aau.risiko.controller;

import android.util.Log;
import android.view.View;

public class DiceState extends State {

    public DiceState(Game game) {
        super(game);
        Log.i("GAME STATE", "Transitioned into DiceState.");
        //TODO Auto-generated constructor stub

    }

    /**
     * handleInput():
     * [Roll dice ...]
     * <p>
     * changeState():
     * Transition to ObserveState.
     */

    // Methods:
    @Override
    public void handleInput(View view) {
        // TODO Auto-generated method stub

    }

    @Override
    public void changeState() {
        // TODO Auto-generated method stub

    }

}
