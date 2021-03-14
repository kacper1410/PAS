package pl.pas.data.model;

import org.junit.Test;
import pl.pas.data.model.user.Administrator;

import static org.junit.Assert.*;

public class AdministratorTest {
    @Test
    public void administratorTest() {
        Administrator administrator = new Administrator("nice_administrator", "koko", "Anastazjusz", "Meczyziomek");

        assertEquals(0, administrator.getUserId());
        assertEquals("nice_administrator", administrator.getLogin());
        assertEquals("koko", administrator.getPassword());
        assertEquals("Anastazjusz", administrator.getName());
        assertEquals("Meczyziomek", administrator.getLastName());
        assertTrue(administrator.isActive());

        administrator.setActive(false);
        assertFalse(administrator.isActive());

        System.out.println(administrator);
    }
}
