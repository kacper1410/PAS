package pl.pas.model;

import org.junit.Test;
import pl.pas.model.user.Client;

import static org.junit.Assert.*;

public class ClientTest {
    @Test
    public void clientTest() {
        Client client = new Client("cool_jan++", "Jan", "Kowalski", 18);

        assertTrue(client.getUserId().toString().matches("[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}"));
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
