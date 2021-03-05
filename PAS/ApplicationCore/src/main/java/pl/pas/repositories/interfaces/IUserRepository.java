package pl.pas.repositories.interfaces;

import pl.pas.exceptions.NotFoundException;
import pl.pas.exceptions.UserAlreadyExistException;
import pl.pas.model.user.Administrator;
import pl.pas.model.user.Client;
import pl.pas.model.user.Employee;
import pl.pas.model.user.User;

import java.util.List;

public interface IUserRepository {
    void addUser(User user) throws UserAlreadyExistException;
    User getUser(long uuid) throws NotFoundException;
    User getUser(String login) throws NotFoundException;
    List<User> getAllUsers();
    void updateUser(long uuid, User newUser);
    List<Client> getAllClients();
    List<Employee> getAllEmployees();
    List<Administrator> getAllAdministrators();
    List<User> getAllActiveUsers();
    User getUserByLoginPasswordActive(String login, String password) throws NotFoundException;
}
