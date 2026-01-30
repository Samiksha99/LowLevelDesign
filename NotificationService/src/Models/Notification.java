package Models;

import Enums.ChannelType;

import java.util.List;

public class Notification {
    private String messageType;
    private String messageId;
    private String message;
    private String sender;
    private String recipient;

    public String getmessageId() {
        return messageId;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public List<ChannelType> getPreferredChannel() {
        return preferredChannel;
    }
    public String getDedupKey() {
        return messageType + "-" + messageId + "-" + recipient;
    }

    private List<ChannelType> preferredChannel;
    public Notification(String message, String messageType, String sender, String recipient, List<ChannelType> channels) {
        this.messageId = java.util.UUID.randomUUID().toString();
        this.messageType = messageType;
        this.message = message;
        this.sender = sender;
        this.recipient = recipient;
        this.preferredChannel = channels;
    }

}
