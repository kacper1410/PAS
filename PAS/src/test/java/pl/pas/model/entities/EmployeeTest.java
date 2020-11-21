package pl.pas.model.entities;

import org.junit.Test;
import pl.pas.model.entities.user.Employee;

import static org.junit.Assert.*;

public class EmployeeTest {
    @Test
    public void employeeTest() {
        Employee employee = new Employee("nice_employee", "Anastazjusz", "Meczyziomek");

        assertEquals("nice_employee", employee.getLogin());
        assertEquals("Anastazjusz", employee.getName());
        assertEquals("Meczyziomek", employee.getLastName());
        assertTrue(employee.isActive());

        employee.setActive(false);
        assertFalse(employee.isActive());

        System.out.println(employee);
    }
}
