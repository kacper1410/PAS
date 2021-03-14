package pl.pas.data.mappers.user;

import pl.pas.data.model.user.AdministratorEntity;
import pl.pas.data.model.user.ClientEntity;
import pl.pas.data.model.user.EmployeeEntity;
import pl.pas.data.model.user.UserEntity;
import pl.pas.domain.model.user.Administrator;
import pl.pas.domain.model.user.Client;
import pl.pas.domain.model.user.Employee;
import pl.pas.domain.model.user.User;

public class UserEntityMapper {

    public static User userEntityToUser(UserEntity userEntity) {
        if (userEntity instanceof ClientEntity) {
            Client client = new Client();
            client.setUserId(userEntity.getUserId());
            client.setLogin(userEntity.getLogin());
            client.setPassword(userEntity.getPassword());
            client.setName(userEntity.getName());
            client.setLastName(userEntity.getLastName());
            client.setAge(((ClientEntity) userEntity).getAge());
            client.setActive(userEntity.isActive());
            return client;
        } else if (userEntity instanceof EmployeeEntity) {
            Employee employee = new Employee();
            employee.setUserId(userEntity.getUserId());
            employee.setLogin(userEntity.getLogin());
            employee.setPassword(userEntity.getPassword());
            employee.setName(userEntity.getName());
            employee.setLastName(userEntity.getLastName());
            employee.setActive(userEntity.isActive());
            return employee;
        } else if (userEntity instanceof AdministratorEntity) {
            Administrator administrator = new Administrator();
            administrator.setUserId(userEntity.getUserId());
            administrator.setLogin(userEntity.getLogin());
            administrator.setPassword(userEntity.getPassword());
            administrator.setName(userEntity.getName());
            administrator.setLastName(userEntity.getLastName());
            administrator.setActive(userEntity.isActive());
            return administrator;
        } else {
            return null;
        }
    }

    public static UserEntity userToUserEntity(User user) {
        if (user instanceof Client) {
            ClientEntity clientEntity = new ClientEntity();
            clientEntity.setUserId(user.getUserId());
            clientEntity.setLogin(user.getLogin());
            clientEntity.setPassword(user.getPassword());
            clientEntity.setName(user.getName());
            clientEntity.setLastName(user.getLastName());
            clientEntity.setAge(((Client) user).getAge());
            clientEntity.setActive(user.isActive());
            return clientEntity;
        } else if (user instanceof Employee) {
            EmployeeEntity employeeEntity = new EmployeeEntity();
            employeeEntity.setUserId(user.getUserId());
            employeeEntity.setLogin(user.getLogin());
            employeeEntity.setPassword(user.getPassword());
            employeeEntity.setName(user.getName());
            employeeEntity.setLastName(user.getLastName());
            employeeEntity.setActive(user.isActive());
            return employeeEntity;
        } else if (user instanceof Administrator) {
            AdministratorEntity administratorEntity = new AdministratorEntity();
            administratorEntity.setUserId(user.getUserId());
            administratorEntity.setLogin(user.getLogin());
            administratorEntity.setPassword(user.getPassword());
            administratorEntity.setName(user.getName());
            administratorEntity.setLastName(user.getLastName());
            administratorEntity.setActive(user.isActive());
            return administratorEntity;
        } else {
            return null;
        }
    }
}
