package at.aau.server;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import at.aau.server.dto.BaseMessage;
import at.aau.server.dto.TextMessage;
import at.aau.server.kryonet.Callback;
import at.aau.server.kryonet.GameClient;
import at.aau.server.kryonet.GameServer;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestServer {

    GameServer server = GameServer.getInstance();
    GameClient client = GameClient.getInstance();

    // TODO: SPLIT INTO MULTIPLE TESTS!
    @Test
    public void testConnection() {
        TextMessage request = new TextMessage("Marco!");
        TextMessage response = new TextMessage("Polo!");

        server.registerClass(TextMessage.class);
        client.registerClass(TextMessage.class);

        server.registerCallback(new Callback<BaseMessage>() {
            @Override
            public void callback(BaseMessage argument) {
                assertEquals("Marco!", ((TextMessage) argument).text);
                server.broadcastMessage(response);
            }
        });
        client.registerCallback(new Callback<BaseMessage>() {
            @Override
            public void callback(BaseMessage argument) {
                assertEquals("Polo!", ((TextMessage) argument).text);
            }
        });

        Thread serverThread = new Thread() {
            @Override
            public void run() {
                try {
                    server.start();
                } catch (IOException e) {
                    // e.printStackTrace();
                }
            }
        };
        serverThread.start();

        Thread clientThread = new Thread() {
            @Override
            public void run() {
                try {
                    client.connect("localhost");
                    client.sendMessage(request);
                } catch (IOException e) {
                    // e.printStackTrace();
                }
            }
        };
        clientThread.start();
    }

}
