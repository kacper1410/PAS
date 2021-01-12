package pl.pas.repositories.interfaces;

import pl.pas.model.user.Administrator;
import pl.pas.model.user.Client;
import pl.pas.model.user.Employee;
import pl.pas.model.user.User;

import java.util.List;

public interface IUserRepository {
    boolean addUser(User user);
    User getUser(long uuid);
    User getUser(String login);
    List<User> getAllUsers();
    void updateUser(long uuid, User newUser);
    List<Client> getAllClients();
    List<Employee> getAllEmployees();
    List<Administrator> getAllAdministrators();
    List<User> getAllActiveUsers();
}
