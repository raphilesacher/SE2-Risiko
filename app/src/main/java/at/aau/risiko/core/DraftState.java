package at.aau.risiko.core;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;

import at.aau.risiko.MapActivity;

public class DraftState extends State {

    private Context con;

    int availableStrenght;
    Country clicked;
    Player p = game.getPlayers()[0];

    /* The constructor must calculate the armies available to
     the player.*/

    public DraftState(Game game) {
        super(game);
        this.availableStrenght = CalculateStrenght();
    }


    private int CalculateStrenght() {
        int occupiedCountries = p.getOccupied().length;
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

        boolean isOccupied;
        Country[] occupiedCountries = p.getOccupied();

        for (int i = 0; 1 < occupiedCountries.length; i++) {
            if (occupiedCountries[i] == clicked) {
                isOccupied = true;
                break;
            }
        }
        if (isOccupied = true) {
            Context context = view.getContext();
            CharSequence text = "You have" + availableStrenght + " Armys to reinforce";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            int oldArmys = clicked.getArmies();
            int newArmys = oldArmys + availableStrenght;
            String buttonOutput = Integer.toString(newArmys);
            clicked.setName(buttonOutput);
            availableStrenght = 0;
            changeState();
        } else {
            Context context = view.getContext();
            CharSequence text = "Choose one of your occupied Countries";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }


    @Override
    public void changeState() {
        game.setState(new AttackState(game));
    }
}
