package at.aau.server.dto;

public class UpdateMessage extends BaseMessage{

    public String playerName;
    public String countryName;
    public Integer color;
    public Integer armies;

    public UpdateMessage() {

    }

    public UpdateMessage(String playerName, String countryName, Integer color, Integer armies) {
        this.playerName = playerName;
        this.countryName = countryName;
        this.color = color;
        this.armies = armies;
    }

}
