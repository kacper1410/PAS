package pl.pas.model;

import org.junit.Test;
import pl.pas.model.user.Administrator;

import static org.junit.Assert.*;

public class AdministratorTest {
    @Test
    public void administratorTest() {
        Administrator administrator = new Administrator("nice_administrator", "Anastazjusz", "Meczyziomek");

        assertEquals("nice_administrator", administrator.getLogin());
        assertEquals("Anastazjusz", administrator.getName());
        assertEquals("Meczyziomek", administrator.getLastName());
        assertTrue(administrator.isActive());

        administrator.setActive(false);
        assertFalse(administrator.isActive());

        System.out.println(administrator);
    }
}
