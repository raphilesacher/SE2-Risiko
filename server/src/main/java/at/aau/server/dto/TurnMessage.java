package at.aau.server.dto;

public class TurnMessage extends BaseMessage {

    public String playerName;

    public TurnMessage() {

    }

    public TurnMessage(String playerName) {
        this.playerName = playerName;
    }

}
