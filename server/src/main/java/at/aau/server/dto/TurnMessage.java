package at.aau.server.dto;

public class TurnMessage extends BaseMessage {

    public String playerName;
    public Integer playerIndex;

    public TurnMessage() {

    }

    public TurnMessage(String playerName, Integer playerIndex) {
        this.playerName = playerName;
        this.playerIndex = playerIndex;
    }

}
