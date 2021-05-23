package at.aau.risiko.core;

import android.content.res.ColorStateList;
import android.os.Build;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;

public class SetupState extends State {

    public SetupState(Game game) {
        super(game);
        //TODO Auto-generated constructor stub
    }

    /**
     * handleInput():
     * Claim country if free.
     * Change state when successful, otherwise do nothing.
     *
     * changeState():
     * Transition to ObserveState.
     */

    // Methods:
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void handleInput(View view) {
        // TODO GET RID OF API DEPENDENCY!
        Button button = (Button) view;
        button.setBackgroundTintList(ColorStateList.valueOf(game.getPlayers()[game.getIndex()].getColor().toArgb()));
        button.setText("0");
        changeState();
    }

    @Override
    public void changeState() {
        // TODO Auto-generated method stub
        game.setState(new ObserveState(game));
    }


}
