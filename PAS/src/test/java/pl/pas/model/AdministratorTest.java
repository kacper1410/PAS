package pl.pas.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class AdministratorTest {
    @Test
    public void administratorTest() {
        Administrator administrator = new Administrator(22, "nice_administrator", "Anastazjusz", "Meczyziomek");

        assertEquals(administrator.getUserId(), 22);
        assertEquals(administrator.getLogin(), "nice_administrator");
        assertEquals(administrator.getName(), "Anastazjusz");
        assertEquals(administrator.getLastName(), "Meczyziomek");
        assertTrue(administrator.isActive());

        administrator.setActive(false);
        assertFalse(administrator.isActive());

        System.out.println(administrator);
    }
}
