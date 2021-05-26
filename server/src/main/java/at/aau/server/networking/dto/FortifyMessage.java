package at.aau.server.networking.dto;

public class FortifyMessage extends BaseMessage {
    public FortifyMessage(int donorArmies, int recipientArmies) {
        this.donorArmies = donorArmies;
        this.recipientArmies = recipientArmies;
    }

    int donorArmies;
    int recipientArmies;

}
