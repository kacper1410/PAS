package pl.pas.model;

import org.junit.Test;
import pl.pas.model.user.Employee;

import static org.junit.Assert.*;

public class EmployeeTest {
    @Test
    public void employeeTest() {
        Employee employee = new Employee("nice_employee", "Anastazjusz", "Meczyziomek");

        assertTrue(employee.getUserId().toString().matches("[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}"));
        assertEquals(employee.getLogin(), "nice_employee");
        assertEquals(employee.getName(), "Anastazjusz");
        assertEquals(employee.getLastName(), "Meczyziomek");
        assertTrue(employee.isActive());

        employee.setActive(false);
        assertFalse(employee.isActive());

        System.out.println(employee);
    }
}
