package pl.pas.model.entities;

import org.junit.Test;
import pl.pas.model.entities.user.Employee;

import static org.junit.Assert.*;

public class EmployeeTest {
    @Test
    public void employeeTest() {
        Employee employee = new Employee("nice_employee", "Anastazjusz", "Meczyziomek");

        assertTrue(employee.getUserId().toString().matches("[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}"));
        assertEquals("nice_employee", employee.getLogin());
        assertEquals("Anastazjusz", employee.getName());
        assertEquals("Meczyziomek", employee.getLastName());
        assertTrue(employee.isActive());

        employee.setActive(false);
        assertFalse(employee.isActive());

        System.out.println(employee);
    }
}
