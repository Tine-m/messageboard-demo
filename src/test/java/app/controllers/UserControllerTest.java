package app.controllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Test
    void shouldRejectBlankUsername() {
        String error = UserController.validateUser("", "1234");
        assertEquals("Brugernavn skal udfyldes", error);
    }

    @Test
    void shouldRejectBlankPassword() {
        String error = UserController.validateUser("tim", "");
        assertEquals("Password skal udfyldes", error);
    }

    @Test
    void shouldRejectTooShortUsername() {
        String error = UserController.validateUser("bo", "1234");
        assertEquals("Brugernavn skal være mindst 3 tegn", error);
    }

    @Test
    void shouldAcceptValidUsernameAndPassword() {
        String error = UserController.validateUser("test", "test");
        assertTrue(error.isEmpty());

    }
}