package pl.pas.model;

import org.junit.Test;
import pl.pas.model.user.Client;

import static org.junit.Assert.*;

public class ClientTest {
    @Test
    public void clientTest() {
        Client client = new Client("cool_jan++", "Jan", "Kowalski", 18);

        assertTrue(client.getUserId().toString().matches("[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}"));
        assertEquals(client.getLogin(), "cool_jan++");
        assertEquals(client.getName(), "Jan");
        assertEquals(client.getLastName(), "Kowalski");
        assertEquals(client.getAge(), 18);
        assertTrue(client.isActive());

        System.out.println(client);

        client.setActive(false);
        assertFalse(client.isActive());
    }
}
