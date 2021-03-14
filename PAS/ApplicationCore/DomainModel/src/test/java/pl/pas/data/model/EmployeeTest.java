package pl.pas.data.model;

import org.junit.Test;
import pl.pas.domain.model.user.Employee;

import static org.junit.Assert.*;

public class EmployeeTest {
    @Test
    public void employeeTest() {
        Employee employee = new Employee("nice_employee", "antek", "Anastazjusz", "Meczyziomek");

        assertEquals(0, employee.getUserId());
        assertEquals("nice_employee", employee.getLogin());
        assertEquals("antek", employee.getPassword());
        assertEquals("Anastazjusz", employee.getName());
        assertEquals("Meczyziomek", employee.getLastName());
        assertTrue(employee.isActive());

        employee.setActive(false);
        assertFalse(employee.isActive());

        System.out.println(employee);
    }
}
