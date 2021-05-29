package at.aau.risiko.controller;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

import at.aau.core.Country;
import at.aau.core.Player;
import at.aau.server.dto.TurnMessage;

public class FortifyState extends State {

    private final Player player = game.getPlayers().get(game.getIndex());
    private Country donor;
    private Button donorButton;
    private Country recipient;
    private Button recipientButton;

    public FortifyState(Game game) {
        super(game);
        Log.i("GAME STATE", "Transitioned into FortifyState.");

        donor = null;
        recipient = null;

        game.setProgress(3);
    }

    /**
     * handleInput():
     * Select donor country.
     * Select recipient country.
     * Change state if selection is valid, otherwise reset.
     * <p>
     * changeState():
     * Send message to server, then transition to ObserverState.
     */

    // Methods:
    @Override
    public void handleInput(View view) {

        Country clicked = game.buttonMap.get(view.getId());

        HashMap<Integer, Country> occupiedCountries = player.getOccupied();

        if (occupiedCountries.containsKey(view.getId())) {
            if (donor == null) {
                donor = clicked;
                donorButton = (Button) view;
            } else if (recipient == null) {
                if (clicked.getNeighbors().contains(donor)) {
                    recipient = clicked;
                    recipientButton = (Button) view;

                    //move one Army from donor to recipient
                    int donorArmys = donor.getArmies() - 1;
                    int recipientArmys = recipient.getArmies() + 1;

                    donor.setArmies(donorArmys);
                    recipient.setArmies(recipientArmys);

                    donorButton.setText(Integer.toString(donorArmys));
                    recipientButton.setText(Integer.toString(recipientArmys));

                    changeState();
                } else {
                    game.showToast("You can only move armies between neighbouring countries!");
                }
            } else {
                donor = null;
                donorButton = null;
                recipient = null;
                recipientButton = null;
            }

        } else {
            game.showToast("You can move armys only between your own countries!");
        }
    }

    @Override
    public void changeState() {
        game.setState(new ObserveState(game));
        game.sendMessage(new TurnMessage());
    }

}
