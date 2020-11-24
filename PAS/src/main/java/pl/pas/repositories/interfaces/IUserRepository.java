package pl.pas.repositories.interfaces;

import pl.pas.model.user.User;

import java.util.List;
import java.util.UUID;

public interface IUserRepository {
    // TODO
    // UUID in addUser method

    boolean addUser(User user, UUID uuid);
    User getUser(UUID uuid);
    User getUser(String login);
    List<User> getAllUsers();
    void updateUser(UUID uuid, User newUser);
    List<User> getAllClients();
    List<User> getAllEmployees();
    List<User> getAllAdministrators();
    List<User> getAllActiveUsers();
}
