package pl.pas.model.repositories.interfaces;

import pl.pas.model.entities.user.User;

import java.util.List;
import java.util.UUID;

public interface IUserRepository {
    // TODO
    // UUID in addUser method

    boolean addUser(User user, UUID uuid);
    User getUser(UUID uuid);
    User getUser(String login);
    List<User> getAllUsers();
    void updateUser(User oldUser, User newUser);
}
