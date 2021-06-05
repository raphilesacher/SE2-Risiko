package at.aau.server.dto;
/**
 * this message type is used to send a boolean.
 */
public class BooleanMessage extends BaseMessage{
    boolean bool;
    public BooleanMessage(boolean bool) {
        this.bool = bool;
    }
}
