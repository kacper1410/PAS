package pl.pas.ports.infrastructure.user;

import pl.pas.data.exceptions.NotFoundException;
import pl.pas.data.model.user.Administrator;
import pl.pas.data.model.user.Client;
import pl.pas.data.model.user.Employee;
import pl.pas.data.model.user.User;

import java.util.List;

public interface ReadUserPort {
    List<Client> readAllClients();
    List<User> readAllUsers();
    List<Employee> readAllEmployees();
    List<Administrator> readAllAdministrators();
    List<User> readAllActiveUsers();
    User readUserByLoginPasswordActive(String login, String password) throws NotFoundException;
    User readUser(  long id) throws NotFoundException;
    User readUser(String login  ) throws NotFoundException;
}
