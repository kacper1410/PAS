package pl.pas.model;

import org.junit.Test;
import pl.pas.model.user.Administrator;

import static org.junit.Assert.*;

public class AdministratorTest {
    @Test
    public void administratorTest() {
        Administrator administrator = new Administrator("nice_administrator", "Anastazjusz", "Meczyziomek");

        assertTrue(administrator.getUserId().toString().matches("[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}"));
        assertEquals(administrator.getLogin(), "nice_administrator");
        assertEquals(administrator.getName(), "Anastazjusz");
        assertEquals(administrator.getLastName(), "Meczyziomek");
        assertTrue(administrator.isActive());

        administrator.setActive(false);
        assertFalse(administrator.isActive());

        System.out.println(administrator);
    }
}
