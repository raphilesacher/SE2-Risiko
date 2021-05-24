package at.aau.risiko.networking.kryonet;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

import at.aau.risiko.networking.NetworkClient;
import at.aau.risiko.networking.dto.BaseMessage;
import at.aau.risiko.networking.kryonet.KryoNetComponent;
import at.aau.risiko.networking.Callback;
import at.aau.risiko.networking.NetworkClient;
import at.aau.risiko.networking.NetworkServer;

public class NetworkClientKryo implements NetworkClient, KryoNetComponent {
    private Client client;
    private Callback<BaseMessage> callback;

    public NetworkClientKryo() {
        client = new Client();
    }
    //TODO: MAKE A NEW SERVER PROJECT(FOR SERVER)
    //TODO: COMMUNICATION WITH SERVER

    public void registerClass(Class c) {
        client.getKryo().register(c);
    }

    public void connect(String host) throws IOException {
        client.start();
        client.connect(5000, host, NetworkConstants.TCP_PORT);

        client.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (callback != null && object instanceof BaseMessage)
                    callback.callback((BaseMessage) object);
            }
        });
    }

    public void registerCallback(Callback<BaseMessage> callback) {
        this.callback = callback;
    }

    public void sendMessage(BaseMessage message) {
        client.sendTCP(message);
    }
}
