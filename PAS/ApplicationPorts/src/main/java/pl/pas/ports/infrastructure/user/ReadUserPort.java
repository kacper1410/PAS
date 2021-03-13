package pl.pas.ports.infrastructure.user;

import pl.pas.exceptions.NotFoundException;
import pl.pas.model.user.Administrator;
import pl.pas.model.user.Client;
import pl.pas.model.user.Employee;
import pl.pas.model.user.User;

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
