package pl.pas.model.entities;

import org.junit.Test;
import pl.pas.model.entities.user.Client;

import static org.junit.Assert.*;

public class ClientTest {
    @Test
    public void clientTest() {
        Client client = new Client("cool_jan++", "Jan", "Kowalski", 18);

        assertEquals("cool_jan++", client.getLogin());
        assertEquals("Jan", client.getName());
        assertEquals("Kowalski", client.getLastName());
        assertEquals(18, client.getAge());
        assertTrue(client.isActive());

        System.out.println(client);

        client.setActive(false);
        assertFalse(client.isActive());
    }
}
