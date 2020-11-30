package pl.pas.repositories.interfaces;

import pl.pas.model.user.User;

import java.util.List;

public interface IUserRepository {
    boolean addUser(User user);
    User getUser(long uuid);
    User getUser(String login);
    List<User> getAllUsers();
    void updateUser(long uuid, User newUser);
    List<User> getAllClients();
    List<User> getAllEmployees();
    List<User> getAllAdministrators();
    List<User> getAllActiveUsers();
}
