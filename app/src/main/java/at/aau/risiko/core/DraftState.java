package at.aau.risiko.core;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.HashMap;

import at.aau.risiko.MapActivity;
import at.aau.risiko.R;

public class DraftState extends State {


    int availableStrenght;
    Country clicked;
    Player p = game.getPlayers()[game.getIndex()];

    /* The constructor must calculate the armies available to
     the player.*/

    public DraftState(Game game) {
        super(game);
        Log.i("GAME STATE", "Transitioned into DraftState.");
        this.availableStrenght = CalculateStrenght();
        Context context = game.getContext();
        CharSequence text = availableStrenght + " armys available to reinforce your countries";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        game.setProgress(1);
    }

    private int CalculateStrenght() {
        int occupiedCountries = p.getOccupied().size();
        int strenght = occupiedCountries / 3;
        if (strenght < 3) {
            strenght = 3;
        }
        return strenght;
    }

    /**
     * handleInput():
     * Deploy armies in country if owned by player.
     * Change state if all armies have been used.
     * changeState():
     * Transition to AttackState.
     */

    // @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void handleInput(View view) {

        clicked = game.buttonMap.get(view.getId());

        boolean isOccupied = false;
        HashMap<Integer, Country> occupiedCountries = p.getOccupied();

        if (occupiedCountries.containsKey(view.getId())) {
            isOccupied = true;
        }

        if (isOccupied = true) {

            int oldArmys = clicked.getArmies();
            int newArmys = oldArmys + 1;
            clicked.setArmies(newArmys);

            Button button = (Button) view;
            button.setText(Integer.toString(newArmys));
            p.setAvailable(availableStrenght--);

            Context context = game.getContext();
            CharSequence text = availableStrenght + " armys still available to reinforce your countries";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();


            if (availableStrenght == 0) {
                changeState();
            }

        } else {
            Context context = view.getContext();
            CharSequence text = "Choose one of your occupied Countries";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    private int calculateRemainingArmys(int available) {
        int remaining = available;
        remaining = available - 1;
        return remaining;
    }

    @Override
    public void changeState() {
        game.setState(new AttackState(game));
        // game.sendMessage(null);
    }
}
