package at.aau.risiko.core;

import android.view.View;

public class DraftState extends State {

    public DraftState(Game game) {
        super(game);
        //TODO Auto-generated constructor stub
    }

    /**
     * The constructor must calculate the armies available to
     * the player.
     *
     * handleInput():
     * Deploy armies in country if owned by player.
     * Change state if all armies have been used.
     *
     * changeState():
     * Transition to AttackState.
     */

    // Methods:

    // @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void handleInput(View view) {
        // TODO: REMOVE!
        /*
        Button button = (Button) view;
        button.setBackgroundTintList(ColorStateList.valueOf(game.getPlayers()[game.getIndex()].getColor().toArgb()));
        button.setText(Integer.toString(Integer.parseInt(button.getText().toString()) + 1));
        */
        changeState();
    }

    @Override
    public void changeState() {
        // TODO Auto-generated method stub
        game.setState(new AttackState(game));
    }


}
