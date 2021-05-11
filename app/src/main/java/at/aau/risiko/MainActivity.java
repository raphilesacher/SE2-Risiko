package at.aau.risiko;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import java.io.IOException;

import at.aau.risiko.networking.Callback;
import at.aau.risiko.networking.NetworkClient;
import at.aau.risiko.networking.NetworkServer;
import at.aau.risiko.networking.dto.TextMessage;
import at.aau.risiko.networking.kryonet.NetworkClientKryo;
import at.aau.risiko.networking.kryonet.NetworkServerKryo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button send = findViewById(R.id.sendToServer);
        send.setOnClickListener(v -> {
            sendMessage();
        });


        try {
            startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startServer() throws IOException {
        NetworkServer server = new NetworkServerKryo();
        server.start();

        String host = "127.0.0.1";

        NetworkThread thread = new NetworkThread(host);

        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            System.out.println("An error occured");
        }
    }


    private void sendMessage()
    {
        TextMessage m = new TextMessage();
        m.text = "Some text";

        NetworkClientKryo c = new NetworkClientKryo();
        c.sendMessage(m);
    }
}