package at.aau.risiko;

import java.io.IOException;

import at.aau.risiko.networking.NetworkClient;
import at.aau.risiko.networking.dto.BaseMessage;
import at.aau.risiko.networking.dto.TextMessage;
import at.aau.risiko.networking.kryonet.NetworkClientKryo;

public class NetworkThread extends Thread{

    String host;

    NetworkThread(String host){this.host=host;};

    public void run()
    {
        try {
            NetworkClient client = new NetworkClientKryo();
            client.connect("127.0.0.1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
