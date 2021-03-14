package pl.pas.mappers.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.pas.data.model.user.AdministratorEntity;
import pl.pas.data.model.user.ClientEntity;
import pl.pas.data.model.user.EmployeeEntity;
import pl.pas.data.model.user.Administrator;
import pl.pas.data.model.user.Client;
import pl.pas.data.model.user.Employee;

class UserEntityMapperTest {

    @Test
    void userEntityToUser() {
        ClientEntity clientEntity = new ClientEntity("cool_jan++", "cool_jan--", "Jan", "Kowalski", 18);

        Client client = (Client) UserEntityMapper.userEntityToUser(clientEntity);
        Assertions.assertEquals(clientEntity.getUserId(), client.getUserId());
        Assertions.assertEquals(clientEntity.getLogin(), client.getLogin());
        Assertions.assertEquals(clientEntity.getPassword(), client.getPassword());
        Assertions.assertEquals(clientEntity.getName(), client.getName());
        Assertions.assertEquals(clientEntity.getLastName(), client.getLastName());
        Assertions.assertEquals(clientEntity.getAge(), client.getAge());

        EmployeeEntity employeeEntity = new EmployeeEntity("nice_employee", "antek", "Anastazjusz", "Meczyziomek");

        Employee employee = (Employee) UserEntityMapper.userEntityToUser(employeeEntity);
        Assertions.assertEquals(employeeEntity.getUserId(), employee.getUserId());
        Assertions.assertEquals(employeeEntity.getLogin(), employee.getLogin());
        Assertions.assertEquals(employeeEntity.getPassword(), employee.getPassword());
        Assertions.assertEquals(employeeEntity.getName(), employee.getName());
        Assertions.assertEquals(employeeEntity.getLastName(), employee.getLastName());

        AdministratorEntity administratorEntity = new AdministratorEntity("nice_administrator", "koko", "Anastazjusz", "Meczyziomek");

        Administrator administrator = (Administrator) UserEntityMapper.userEntityToUser(administratorEntity);
        Assertions.assertEquals(administratorEntity.getUserId(), administrator.getUserId());
        Assertions.assertEquals(administratorEntity.getLogin(), administrator.getLogin());
        Assertions.assertEquals(administratorEntity.getPassword(), administrator.getPassword());
        Assertions.assertEquals(administratorEntity.getName(), administrator.getName());
        Assertions.assertEquals(administratorEntity.getLastName(), administrator.getLastName());
    }

    @Test
    void userToUserEntity() {
        Client client = new Client("cool_jan++", "cool_jan--", "Jan", "Kowalski", 18);

        ClientEntity clientEntity = (ClientEntity) UserEntityMapper.userToUserEntity(client);
        Assertions.assertEquals(clientEntity.getUserId(), client.getUserId());
        Assertions.assertEquals(clientEntity.getLogin(), client.getLogin());
        Assertions.assertEquals(clientEntity.getPassword(), client.getPassword());
        Assertions.assertEquals(clientEntity.getName(), client.getName());
        Assertions.assertEquals(clientEntity.getLastName(), client.getLastName());
        Assertions.assertEquals(clientEntity.getAge(), client.getAge());

        Employee employee = new Employee("nice_employee", "antek", "Anastazjusz", "Meczyziomek");
        EmployeeEntity employeeEntity = (EmployeeEntity) UserEntityMapper.userToUserEntity(employee);
        Assertions.assertEquals(employee.getUserId(), employeeEntity.getUserId());
        Assertions.assertEquals(employee.getLogin(), employeeEntity.getLogin());
        Assertions.assertEquals(employee.getPassword(), employeeEntity.getPassword());
        Assertions.assertEquals(employee.getName(), employeeEntity.getName());
        Assertions.assertEquals(employee.getLastName(), employeeEntity.getLastName());

        Administrator administrator = new Administrator("nice_administrator", "koko", "Anastazjusz", "Meczyziomek");
        AdministratorEntity administratorEntity = (AdministratorEntity) UserEntityMapper.userToUserEntity(administrator);
        Assertions.assertEquals(administrator.getUserId(), administratorEntity.getUserId());
        Assertions.assertEquals(administrator.getLogin(), administratorEntity.getLogin());
        Assertions.assertEquals(administrator.getPassword(), administratorEntity.getPassword());
        Assertions.assertEquals(administrator.getName(), administratorEntity.getName());
        Assertions.assertEquals(administrator.getLastName(), administratorEntity.getLastName());
    }
}
