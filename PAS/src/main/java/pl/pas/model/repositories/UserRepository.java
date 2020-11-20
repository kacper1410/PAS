package pl.pas.model.repositories;

import pl.pas.model.repositories.interfaces.IUserRepository;
import pl.pas.model.user.User;

import java.util.List;
import java.util.UUID;

public class UserRepository implements IUserRepository {


    @Override
    public boolean addUser(User user) {
        return false;
    }

    @Override
    public User getUser(UUID uuid) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public boolean updateUser(User oldUser, User newUser) {
        return false;
    }
}
