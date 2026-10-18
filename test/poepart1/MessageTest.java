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
}