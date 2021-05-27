package at.aau.risiko;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import at.aau.server.dto.StartMessage;
import at.aau.server.dto.TextMessage;
import at.aau.server.kryonet.GameClient;
import at.aau.server.kryonet.GameServer;

public class LobbyActivity extends AppCompatActivity {

    ListView playersInLobby;

    // TODO replace this list with data from server
    ArrayList<String> userNames = new ArrayList<>();
    ArrayAdapter<String> playerNamesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        Button btnExit = findViewById(R.id.btnExit);
        playersInLobby = findViewById(R.id.listOfPlayers);


        for(String e : userNames){
            GameServer.getInstance().broadcastMessage(new TextMessage(e));
            userNames.add(e);
        }


        playerNamesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, userNames);
        playersInLobby.setAdapter(playerNamesAdapter);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                for(String e : userNames){
                    GameServer.getInstance().broadcastMessage(new TextMessage(e));
                    userNames.remove(e);
                }
                startActivity(intent);
            }
        });

        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);

                GameClient.getInstance().sendMessage(new StartMessage());
            }
        });

        // playersInLobby.findViewById(R.id.listOfPlayers);

    }

    // called by the network client to update the list of users
    public void setUserNames(ArrayList<String> userNames) {
        this.userNames = userNames;
        this.playerNamesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.userNames);
    }


}