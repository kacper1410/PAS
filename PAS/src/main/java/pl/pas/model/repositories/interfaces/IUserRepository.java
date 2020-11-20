package pl.pas.model.repositories.interfaces;

import pl.pas.model.user.User;

import java.util.List;
import java.util.UUID;

public interface IUserRepository {
    boolean addUser(User user);
    User getUser(UUID uuid);
    User getUser(String login);
    List<User> getAllUsers();
    void updateUser(User oldUser, User newUser);
}
