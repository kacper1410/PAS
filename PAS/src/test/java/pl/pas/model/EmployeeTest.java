package pl.pas.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeTest {
    @Test
    public void employeeTest() {
        Employee employee = new Employee(2, "nice_employee", "Anastazjusz", "Meczyziomek");

        assertEquals(employee.getUserId(), 2);
        assertEquals(employee.getLogin(), "nice_employee");
        assertEquals(employee.getName(), "Anastazjusz");
        assertEquals(employee.getLastName(), "Meczyziomek");
        assertTrue(employee.isActive());

        employee.setActive(false);
        assertFalse(employee.isActive());

        System.out.println(employee);
    }
}
