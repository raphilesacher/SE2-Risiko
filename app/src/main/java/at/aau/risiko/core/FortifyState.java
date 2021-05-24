package at.aau.risiko.core;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

public class FortifyState extends State {

    private Country donor;
    private Country recipient;
    Player p = game.getPlayers()[game.getIndex()];

    public FortifyState(Game game) {
        super(game);
        donor = null;
        recipient = null;
        //TODO Auto-generated constructor stub
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
        // TODO Auto-generated method stub


        Country clicked = game.buttonMap.get(view.getId());

        if (donor == null) {
            donor = clicked;
        } else if (recipient == null) {
            boolean valid = false;
            for (Country c : clicked.getNeighbors()) {
                if (c == donor) {
                    recipient = clicked;
                    valid = true;
                    break;
                }
            }
            if (valid) {

                //move one Army from donor to recipient
                
                int donorArmys = donor.getArmies() - 1;
                int recipientArmys = recipient.getArmies() + 1;

                donor.setArmies(donorArmys);
                recipient.setArmies(recipientArmys);

                Button button = (Button) view;
                button.setText(Integer.toString(donorArmys));

                changeState();
            }
        } else {
            donor = null;
            recipient = null;
        }

    }

    @Override
    public void changeState() {
        // TODO Auto-generated method stub
        game.setState(new ObserveState(game));
    }

}
