package pl.pas.model.repositories;

import pl.pas.model.entities.user.User;
import pl.pas.model.repositories.interfaces.IUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepository implements IUserRepository {
    private List<User> users;

    public UserRepository() {
        users = new ArrayList<>();
    }

    @Override
    public boolean addUser(User user, UUID uuid) {
        user.setUserId(uuid);
        return users.add(user);
    }

    @Override
    public User getUser(UUID uuid) {
        for (User u: users) {
            if (u.getUserId().equals(uuid)) return u;
        }
        return null;
    }

    @Override
    public User getUser(String login) {
        for (User u: users) {
            if (u.getLogin().equals(login)) return u;
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public void updateUser(UUID uuid, User newUser) {
        for (User u : users) {
            if (u.getUserId().equals(uuid)) {
                newUser.setUserId(uuid);
                users.set(users.indexOf(u), newUser);
            }
        }
    }
}
