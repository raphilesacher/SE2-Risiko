package at.aau.risiko.core;

import android.util.Log;
import android.view.View;

public class AttackState extends State {

    private Country attacking;
    private Country defending;

    public AttackState(Game game) {
        super(game);
        //TODO Auto-generated constructor stub
        attacking = null;
        defending = null;
    }

    /**
     * 
     * handleInput():
     * Select attacking country.
     * Select defending country.
     * Send message to server if selection is valid, otherwise reset.
     * [Roll dice ...]
     * Change state if player presses skip button.
     * 
     * changeState():
     * Transition to FortifyState.
     * 
     */
    
    // Methods:

    @Override
    public void handleInput(View view) {
        // TODO Auto-generated method stub
        Country clicked = game.buttons.get(view.getId());

        if (attacking == null) {
            attacking = clicked;
        } else if (defending == null) {
            boolean valid = false;
            for (Country c : clicked.getNeighbors()) {
                if (c == attacking) {
                    defending = clicked;
                    valid = true;
                    break;
                }
            }
            if (valid) {
                // TODO: SEND MESSAGE TO SERVER AND START DICE STATE
                Log.i("ATTACK PHASE", "It's " + attacking.getName() + " against " + defending.getName());
                changeState();
            }
        } else {
            attacking = null;
            defending = null;
        }

    }

    @Override
    public void changeState() {
        // TODO Auto-generated method stub
        game.state = new FortifyState(game);
    }
    
}
