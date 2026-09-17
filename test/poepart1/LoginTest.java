package poepart1;

import poepart1.Login;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {

    private Login login = new Login();

    // --- USERNAME TESTS ---
    @Test
    public void testCheckUserNameCorrect() {
        // Test Data: "kyl_1"
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserNameIncorrect() {
        // Test Data: "kyle!!!!!!"
        assertFalse(login.checkUserName("kyle!!!!!!"));
    }

    // --- PASSWORD TESTS ---
    @Test
    public void testCheckPasswordComplexitySuccess() {
        // Test Data: "Ch&&sec@ke99!"
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testCheckPasswordComplexityFailure() {
        // Test Data: "password"
        assertFalse(login.checkPasswordComplexity("password"));
    }

    // --- CELL PHONE TESTS ---
    @Test
    public void testCheckCellPhoneNumberCorrect() {
        // Test Data: "+27838968976"
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCheckCellPhoneNumberIncorrect() {
        // Test Data: "08966553"
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    // --- LOGIN TESTS ---
    @Test
    public void testLoginUserSuccess() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "LastName");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginUserFailed() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "LastName");
        assertFalse(login.loginUser("wrong_user", "WrongPass123!"));
    }
}