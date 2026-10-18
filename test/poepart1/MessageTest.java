package poepart1;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testMessageLengthValid() {
        Message msg = new Message("+27718693002", "Hello World");
        assertEquals("Message ready to send.", msg.validateMessageLength());
    }

    @Test
    public void testRecipientCellValid() {
        Message msg = new Message("+27718693002", "Hello World");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientCellInvalid() {
        Message msg = new Message("0718693002", "Hello World");
        assertTrue(msg.checkRecipientCell().contains("incorrectly formatted"));
    }

    @Test
    public void testSentMessagesArrayPopulation() {
        Message.sentMessagesList.clear();
        Message msg = new Message("+27834557896", "Did you get the cake?");
        msg.createMessageHash(1);
        msg.SentMessage(1); // Action 1 = Sent
        
        assertFalse(Message.sentMessagesList.isEmpty());
        assertEquals("Did you get the cake?", Message.sentMessagesList.get(0).getMessageText());
    }

    @Test
    public void testLongestStoredMessage() {
        Message.storedMessagesList.clear();
        Message msg1 = new Message("+27838884567", "Short message");
        Message msg2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.");
        
        Message.storedMessagesList.add(msg1);
        Message.storedMessagesList.add(msg2);

        Message longest = Message.storedMessagesList.get(0);
        for (Message m : Message.storedMessagesList) {
            if (m.getMessageText().length() > longest.getMessageText().length()) {
                longest = m;
            }
        }
        
        assertEquals("Where are you? You are late! I have asked you to be on time.", longest.getMessageText());
    }

    @Test
    public void testSearchMessageByID() {
        Message.storedMessagesList.clear();
        Message msg = new Message("+27838884567", "It is dinner time !");
        msg.setMessageID("1234567890");
        Message.storedMessagesList.add(msg);

        boolean found = false;
        for (Message m : Message.storedMessagesList) {
            if (m.getMessageID().equals("1234567890")) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testDeleteMessageByHash() {
        Message.storedMessagesList.clear();
        Message msg = new Message("+27838884567", "Test Message");
        msg.createMessageHash(1);
        Message.storedMessagesList.add(msg);

        String targetHash = msg.getMessageHash();
        boolean removed = Message.storedMessagesList.removeIf(m -> m.getMessageHash().equals(targetHash));

        assertTrue(removed);
        assertTrue(Message.storedMessagesList.isEmpty());
    }
}