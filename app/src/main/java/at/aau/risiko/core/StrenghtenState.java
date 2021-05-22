package at.aau.risiko.core;

import android.view.View;
import android.widget.NumberPicker;

public class StrenghtenState extends State {

    public StrenghtenState(Game game) {
        super(game);
    }

    Game g = super.game;
    Player p = game.players[0];

    int strenght;

    public int getStrenght() {
        return strenght;
    }


    private int calculateStrenght()
    {
        int occupiedCountries = p.getArmies();
        int strenght = occupiedCountries / 3;
        if(strenght < 3)
        {
            strenght = 3;
        }
        return strenght;
    }

    private int calculateNewStrenght(int choosen)
    {
        strenght = strenght - choosen;
        if (strenght == 0)
        {
            changeState();
        }
        return strenght;
    }

    @Override
    public void handleInput(View view) {

        int strenght = calculateStrenght();
        // open Dialog
        // get Value from Dialog
        int choosen = 0;
        strenght = calculateNewStrenght(choosen);

        //actualize view

        // TODO Implement method Calls for Frontend
    }

    @Override
    public void changeState() {
        // TODO Implement swith to Attack

    }

}
