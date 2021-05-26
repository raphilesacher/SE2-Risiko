package at.aau.server.kryonet;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

import at.aau.server.dto.BaseMessage;

public class GameClient implements NetworkClient, KryoNetComponent {
    private static GameClient instance;
    private final Client client;
    private Callback<BaseMessage> callback;

    private GameClient() {
        client = new Client();
    }

    public static GameClient getInstance() {
        if (instance == null) {
            instance = new GameClient();
        }
        return instance;
    }

    /*
    public InetSocketAddress getRemoteAddress() {
        return this.client.getRemoteAddressTCP();
    }
    */

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
        Thread thread = new Thread() {
            @Override
            public void run() {
                client.sendTCP(message);
            }
        };
        thread.start();
    }

    public void sendNickName(String nickname) {
        client.sendTCP(nickname);
    }
}
