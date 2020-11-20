package pl.pas.model.repositories.interfaces;

import pl.pas.model.user.User;

import java.util.List;
import java.util.UUID;

public interface IUserRepository {
    boolean addUser(User user);
    User getUser(UUID uuid);
    List<User> getAllUsers();
    boolean updateUser(User oldUser, User newUser);
}
