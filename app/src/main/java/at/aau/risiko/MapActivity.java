package at.aau.risiko;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import at.aau.core.Country;
import at.aau.core.DataParser;
import at.aau.core.Player;
import at.aau.risiko.controller.DraftState;
import at.aau.risiko.controller.Game;
import at.aau.risiko.controller.SetupState;
import at.aau.server.dto.BaseMessage;
import at.aau.server.dto.ReadyMessage;
import at.aau.server.dto.StartMessage;
import at.aau.server.dto.TextMessage;
import at.aau.server.dto.TurnMessage;
import at.aau.server.kryonet.Callback;
import at.aau.server.kryonet.GameClient;

public class MapActivity extends AppCompatActivity {

    Game game;

    HashMap<Integer, Country> countryMapping;
    HashMap<Integer, Player> playerMapping;
    HashMap<Integer, Integer[]> neighborMapping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        List<Player> players = new LinkedList<Player>();
        players.add(new Player("Uno", 0xFFFFCC00));
        players.add(new Player("Due", 0xFFFF00CC));
        List<Country> countries = new LinkedList<Country>(); // DataParser.getCountries();
        countries.add(null);

        // Start game:
        // TODO: CHANGE PLAYER ARRAY TO REFLECT PLAYERS CONNECTED TO SERVER
        game = new Game(players, countries, countryMapping, playerMapping, this);

        GameClient.getInstance().registerCallback(new Callback<BaseMessage>() {
            @Override
            public void callback(BaseMessage argument) {
                if (argument instanceof TextMessage) {
                    Log.i("SERVER MESSAGE", ((TextMessage) argument).text);
                } else if (argument instanceof StartMessage) {
                    // Do nothing.
                } else if (argument instanceof TurnMessage) {
                    // TODO: CHANGE TO SETUP STATE?
                    if (game.getAvailableCountries().size() > 0) {
                        game.setState(new SetupState(game));
                    } else {
                        game.setState(new DraftState(game));
                    }
                }
            }
        });


        // Add buttons from JSON country data:
        for (Country c : game.getAvailableCountries()) {
            //
            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT);
            params.topToTop = 0;
            params.bottomToBottom = 0;
            params.startToStart = 0;
            params.endToEnd = 0;

            //
            params.width = (int)(36 * this.getResources().getDisplayMetrics().density);
            params.height = (int)(36 * this.getResources().getDisplayMetrics().density);
            params.horizontalBias = 0.5f;
            params.verticalBias = 0.5f;

            //
            Button countryButton = new Button(this);
            countryButton.setLayoutParams(params);
            countryButton.setTypeface(ResourcesCompat.getFont(this, R.font.erica_one));
            countryButton.setTextColor(0xFFFFFFFF);
            countryButton.setText("0");
            countryButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            countryButton.setBackgroundTintList(ColorStateList.valueOf(0xFFDDDDDD));
            countryButton.setBackgroundTintMode(PorterDuff.Mode.MULTIPLY);
            countryButton.setBackgroundResource(R.drawable.ic_army_counter);
            countryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Button)v).setText("YEEE");
                }
            });
            ((ConstraintLayout) findViewById(R.id.constraintLayout)).addView(countryButton);
        }



        // TODO: PUT THIS LOGIC DIRECTLY INTO BUTTON GENERATION!
        // Find all buttons in view and link to countries:
        countryMapping = new HashMap<Integer, Country>();

        // Put all countries bordering a country into a hashtable:
        neighborMapping = new HashMap<Integer, Integer[]>();

        // Convert name mapping to neighbour mapping:
        for (int i : neighborMapping.keySet()) {
            for (int j : neighborMapping.get(i)) {
                countryMapping.get(i).addNeighbor(countryMapping.get(j));
            }
        }

        // Add players to side layout
        playerMapping = new HashMap<Integer, Player>();

        LinearLayout layout = findViewById(R.id.linearLayout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 8, 32, 8);

        for (Player player : game.getPlayers()) {
            ImageView avatar = new ImageView(this);

            avatar.setId(View.generateViewId());
            avatar.setLayoutParams(params);
            avatar.setImageResource(R.drawable.ic_army_counter);
            avatar.setImageTintList(ColorStateList.valueOf(player.getColor()));
            avatar.setImageTintMode(PorterDuff.Mode.MULTIPLY);

            layout.addView(avatar, LinearLayout.LayoutParams.WRAP_CONTENT);

            playerMapping.put(avatar.getId(), player);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        GameClient.getInstance().sendMessage(new ReadyMessage());
    }

    // Country button:
    public void onClick(View view) {
        game.handleInput(view);
    }

    // Cards button:
    public void openCardActivity(View view) {
        startActivity(new Intent(this, CardActivity.class));
    }

}