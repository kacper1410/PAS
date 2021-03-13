package pl.pas.mappers.user;

import pl.pas.data.model.user.EmployeeEntity;
import pl.pas.model.user.Employee;

public class EmployeeEntityMapper {

    public static Employee employeeEntityToEmployee(EmployeeEntity employeeEntity) {
        Employee employee = new Employee();
        employee.setLogin(employeeEntity.getLogin());
        employee.setPassword(employeeEntity.getPassword());
        employee.setName(employeeEntity.getName());
        employee.setLastName(employeeEntity.getLastName());
        employee.setActive(employeeEntity.isActive());
        return employee;
    }

    public static EmployeeEntity employeeToEmployeeEntity(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setLogin(employee.getLogin());
        employeeEntity.setPassword(employee.getPassword());
        employeeEntity.setName(employee.getName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setActive(employee.isActive());
        return employeeEntity;
    }
}
