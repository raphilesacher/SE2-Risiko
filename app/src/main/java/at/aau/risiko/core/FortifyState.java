package at.aau.risiko.core;

import android.util.Log;
import android.view.View;

import at.aau.risiko.networking.dto.TextMessage;
import at.aau.risiko.networking.dto.TurnMessage;

public class FortifyState extends State {

    Country donor;
    Country recipient;

    public FortifyState(Game game) {
        super(game);
        Log.i("GAME STATE", "Transitioned into FortifyState.");
        //TODO Auto-generated constructor stub

        game.setProgress(3);
    }

    /**
     * handleInput():
     * Select donor country.
     * Select recipient country.
     * Change state if selection is valid, otherwise reset.
     *
     * changeState():
     * Send message to server, then transition to ObserverState.
     */

    // Methods:
    @Override
    public void handleInput(View view) {
        // TODO Auto-generated method stub
        changeState();
    }

    @Override
    public void changeState() {
        // TODO Auto-generated method stub
        game.setState(new ObserveState(game));
        game.sendMessage(new TurnMessage());
    }

}
