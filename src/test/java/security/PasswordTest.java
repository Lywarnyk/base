package security;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Testing Password hashing
 *
 * @author D. Kuliha
 */
public class PasswordTest {
    @Test
    public void hash() throws Exception {
        String password = "test";
        String expected = "a94a8fe5ccb19ba61c4c0873d391e987982fbbd3";
        String actual = Password.hash(password);
        assertEquals(expected.toUpperCase(), actual);
    }

}