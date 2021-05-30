package at.aau.risiko.controller;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import at.aau.core.Country;
import at.aau.risiko.DiceActivity;
import at.aau.server.dto.TextMessage;
import at.aau.server.dto.UpdateMessage;

public class AttackState extends State {

    private Country attacking;
    private Country defending;

    public AttackState(Game game) {
        super(game);
        Log.i("GAME STATE", "Transitioned into AttackState.");
        //TODO Auto-generated constructor stub
        attacking = null;
        defending = null;

        game.setProgress(2);
    }

    /**
     * handleInput():
     * Select attacking country.
     * Select defending country.
     * Send message to server if selection is valid, otherwise reset.
     * [Roll dice ...]
     * Change state if player presses skip button.
     * <p>
     * changeState():
     * Transition to FortifyState.
     */

    // Methods:
    @Override
    public void handleInput(View view) {
        // TODO Auto-generated method stub
        Country clicked = game.buttonMap.get(view.getId());

        if (attacking == null) {
            attacking = clicked;
        } else if (defending == null) {
            if (clicked.getNeighbors().contains(attacking)) {
                // TODO: SEND MESSAGE TO SERVER AND START DICE STATE
                defending = clicked;
                game.sendMessage(new TextMessage("It's " + attacking.getName() + " against " + defending.getName()));
                game.getContext().startActivity(new Intent(game.getContext(), DiceActivity.class));
                changeState();
            } else {
                game.showToast("You can only attack neighbouring countries!");
            }
        } else {
            attacking = null;
            defending = null;
        }

        // TODO: CHANGE HARDCODED NAME AND COLOR!
        game.sendMessage(new UpdateMessage("DUE", game.buttonMap.get(view.getId()).getName(), game.buttonMap.get(view.getId()).getArmies()));
    }

    @Override
    public void changeState() {
        game.setState(new FortifyState(game));
    }

}
