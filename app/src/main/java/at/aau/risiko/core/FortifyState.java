package at.aau.risiko.core;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

public class FortifyState extends State {

    private Country donor;
    private Country recipient;
    Player p = game.getPlayers()[game.getIndex()];

    public FortifyState(Game game) {
        super(game);
        donor = null;
        recipient = null;
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

        HashMap<Integer, Country> occupiedCountries = p.getOccupied();

        if (occupiedCountries.containsKey(view.getId())) {
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

        } else {
            Context context = game.getContext();
            CharSequence text = "You can move armys only between your own countries!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    @Override
    public void changeState() {
        game.setState(new ObserveState(game));
    }

}
