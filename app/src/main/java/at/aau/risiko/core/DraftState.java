package at.aau.risiko.core;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;

public class DraftState extends State {

    public DraftState(Game game) {
        super(game);
        //TODO Auto-generated constructor stub
    }

    /**
     * 
     * The constructor must calculate the armies available to
     * the player.
     * 
     * handleInput():
     * Deploy armies in country if owned by player.
     * Change state if all armies have been used.
     * 
     * changeState():
     * Transition to AttackState.
     * 
     */

    // Methods:

    @Override
    public void handleInput(View view) {
        // TODO Auto-generated method stub
        Button button = (Button) view;
        button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFBB33")));
        button.setText(Integer.toString(Integer.parseInt(button.getText().toString()) + 1));
        changeState();
    }

    @Override
    public void changeState() {
        // TODO Auto-generated method stub
        game.setState(new AttackState(game));
    }

    
    
}
