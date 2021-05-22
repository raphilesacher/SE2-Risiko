package at.aau.risiko.core;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

public class DraftState extends State {

    private Context con;

    int availableStrenght;
    Country clicked;
    Player p = game.getPlayers()[game.getIndex()];

    /* The constructor must calculate the armies available to
     the player.*/

    public DraftState(Game game) {
        super(game);
        this.availableStrenght = CalculateStrenght();
        Context context = game.getContext();
        CharSequence text = "You have" + availableStrenght + " Armys to reinforce";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

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

        boolean isOccupied;
        HashMap<Integer, Country> occupiedCountries = p.getOccupied();


        if(occupiedCountries.containsKey(clicked))
        {
            isOccupied = true;
        }
        if (isOccupied = true) {
            Context context = view.getContext();
            CharSequence text = "You have" + availableStrenght + " Armys to reinforce";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            int oldArmys = clicked.getArmies();
            int newArmys = oldArmys + availableStrenght;
            clicked.setArmies(newArmys);

            Button button = (Button)  view;
            button.setText(Integer.toString(newArmys));
            p.setAvailable(0);
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
