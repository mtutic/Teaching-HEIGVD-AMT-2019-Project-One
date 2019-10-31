package ch.heigvd.amt.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserTest {

    @Test
    public void shouldCreateAnUser() {
        User user = new User(1, "Doe", "John", "j.doe@gmail.com", "qwertz");

        assertNotNull(user);

        assertEquals(1, user.getId());
        assertEquals("Doe", user.getLastName());
        assertEquals("John", user.getFirstName());
        assertEquals("j.doe@gmail.com", user.getEmail());
        assertEquals("qwertz", user.getPassword());
    }
}
