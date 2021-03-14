package pl.pas.data.model;

import org.junit.Test;
import pl.pas.domain.model.user.Client;

import static org.junit.Assert.*;

public class ClientTest {
    @Test
    public void clientTest() {
        Client client = new Client("cool_jan++", "cool_jan--", "Jan", "Kowalski", 18);

        assertEquals(0, client.getUserId());
        assertEquals("cool_jan++", client.getLogin());
        assertEquals("cool_jan--", client.getPassword());
        assertEquals("Jan", client.getName());
        assertEquals("Kowalski", client.getLastName());
        assertEquals(18, client.getAge());
        assertTrue(client.isActive());

        System.out.println(client);

        client.setActive(false);
        assertFalse(client.isActive());
    }
}
