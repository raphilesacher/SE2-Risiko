package at.aau.server.networking.dto;

public class DraftMessage extends BaseMessage {

    public DraftMessage(){}

    public DraftMessage(int availableStrenght) {
        this.availableStrenght = availableStrenght;
    }

    int availableStrenght;
}
