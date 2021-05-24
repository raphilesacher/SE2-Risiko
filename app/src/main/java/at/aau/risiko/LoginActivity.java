package at.aau.risiko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import at.aau.risiko.networking.Callback;
import at.aau.risiko.networking.dto.BaseMessage;
import at.aau.risiko.networking.dto.TextMessage;
import at.aau.risiko.networking.kryonet.GameClient;
import at.aau.risiko.networking.kryonet.GameServer;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        EditText txtNickname = (EditText)findViewById(R.id.txtNickname);
        Button btnConfirm = (Button)findViewById(R.id.btnConfirm);

        Log.i("loginActivity", "LoginActivity launched");

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to send on server
                String enteredNickname = txtNickname.getText().toString();

                if(txtNickname.getText().toString().isEmpty()){
                    showToast("Player's name has not been entered!");
                }
                else{
                    showToast("Player's name: " + enteredNickname);
                    // TODO: send nickname to server
                    // example: server.sendTCP(enteredNicknname);
                    startActivity(new Intent(getApplicationContext(),GameLobby.class));
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        GameServer server = GameServer.getInstance();
        GameClient client = GameClient.getInstance();

        server.registerClass(TextMessage.class);
        client.registerClass(TextMessage.class);

        TextMessage message = new TextMessage("Sending you a message!");
        TextMessage confirm = new TextMessage("Message was read.");

        server.registerCallback(new Callback<BaseMessage>() {
            @Override
            public void callback(BaseMessage argument) {
                Log.i("SERVER CALLBACK", ((TextMessage) argument).text);
                server.broadcastMessage(confirm);
            }
        });
        client.registerCallback(new Callback<BaseMessage>() {
            @Override
            public void callback(BaseMessage argument) {
                Log.i("CLIENT CALLBACK", ((TextMessage) argument).text);
            }
        });

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    server.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    client.connect("localhost");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                client.sendMessage(message);
            }
        };

        thread.start();

        // startActivity(new Intent(this, MapActivity.class));
    }

    public void showToast(String message){
        Log.i("BUTTON", "Showing toast!!");
        Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}