package at.aau.server.dto;

public class NameMessage extends BaseMessage {

    public String playerName;

    public NameMessage() {

    }

    public NameMessage(String playerName) {
        this.playerName = playerName;
    }

}
