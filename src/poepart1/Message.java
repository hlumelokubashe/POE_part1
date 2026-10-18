package poepart1;

import java.util.ArrayList;
import java.util.Random;

public class Message {

    private String messageID;
    private String recipientCell;
    private String messageText;
    private String messageHash;
    private static int totalMessagesSent = 0;

    // Part 3: Dynamic Arrays for Storage & Tracking
    public static ArrayList<Message> sentMessagesList = new ArrayList<>();
    public static ArrayList<Message> disregardedMessagesList = new ArrayList<>();
    public static ArrayList<Message> storedMessagesList = new ArrayList<>();
    public static ArrayList<String> messageIDsList = new ArrayList<>();
    public static ArrayList<String> messageHashesList = new ArrayList<>();

    public Message() {}

    public Message(String recipientCell, String messageText) {
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.messageID = generateMessageID();
    }

    public String generateMessageID() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public boolean checkMessageID() {
        return this.messageID != null && this.messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if (recipientCell != null && recipientCell.startsWith("+") && recipientCell.length() <= 12) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String validateMessageLength() {
        if (messageText == null || messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = messageText.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

    public String createMessageHash(int messageNum) {
        String idPrefix = (messageID != null && messageID.length() >= 2) ? messageID.substring(0, 2) : "00";
        
        String firstWord = "";
        String lastWord = "";
        
        if (messageText != null && !messageText.trim().isEmpty()) {
            String[] words = messageText.trim().split("\\s+");
            firstWord = words[0].replaceAll("[^a-zA-Z]", "");
            lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", "");
        }

        this.messageHash = (idPrefix + ":" + messageNum + ":" + firstWord + lastWord).toUpperCase();
        return this.messageHash;
    }

    public String SentMessage(int choice) {
        if (!messageIDsList.contains(this.messageID)) {
            messageIDsList.add(this.messageID);
        }
        if (!messageHashesList.contains(this.messageHash)) {
            messageHashesList.add(this.messageHash);
        }

        switch (choice) {
            case 1:
                totalMessagesSent++;
                sentMessagesList.add(this);
                return "Message successfully sent.";
            case 2:
                disregardedMessagesList.add(this);
                return "Press 0 to delete the message.";
            case 3:
                storedMessagesList.add(this);
                storeMessage();
                return "Message successfully stored.";
            default:
                return "Invalid option selected.";
        }
    }

    public void storeMessage() {
        // Placeholder for storing messages
    }

    public String printMessages() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipientCell + "\n" +
               "Message: " + messageText;
    }

    public static int returnTotalMessagess() {
        return totalMessagesSent;
    }

    public String getMessageID() { return messageID; }
    public void setMessageID(String messageID) { this.messageID = messageID; }
    
    public String getRecipientCell() { return recipientCell; }
    public void setRecipientCell(String recipientCell) { this.recipientCell = recipientCell; }
    
    public String getMessageText() { return messageText; }
    public void setMessageText(String messageText) { this.messageText = messageText; }
    
    public String getMessageHash() { return messageHash; }
}