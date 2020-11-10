package pl.pas.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {
    @Test
    public void clientTest() {
        Client client = new Client(1, "cool_jan++", "Jan", 18);

        assertEquals(client.getUserId(), 1);
        assertEquals(client.getLogin(), "cool_jan++");
        assertEquals(client.getName(), "Jan");
        assertEquals(client.getAge(), 18);
        assertTrue(client.isActive());

        System.out.println(client);

        client.setActive(false);
        assertFalse(client.isActive());
    }
}
