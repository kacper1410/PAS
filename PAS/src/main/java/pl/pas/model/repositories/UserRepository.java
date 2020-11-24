package pl.pas.model.repositories;

import pl.pas.model.entities.user.Administrator;
import pl.pas.model.entities.user.Client;
import pl.pas.model.entities.user.Employee;
import pl.pas.model.entities.user.User;
import pl.pas.model.repositories.interfaces.IUserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Named
@ApplicationScoped
public class UserRepository implements IUserRepository {
    private List<User> users;

    public UserRepository() {
        users = new ArrayList<>();
    }

    @Override
    public boolean addUser(User user, UUID uuid) {
        if (users.contains(getUser(user.getLogin()))) return false;

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

    public List<User> getAllClients() {
        ArrayList<User> clients = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Client) {
                clients.add(user);
            }
        }
        return clients;
    }

    public List<User> getAllEmployees() {
        ArrayList<User> employees = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Employee) {
                employees.add(user);
            }
        }
        return employees;
    }

    public List<User> getAllAdministrators() {
        ArrayList<User> administrators = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Administrator) {
                administrators.add(user);
            }
        }
        return administrators;
    }

    public List<User> getAllActiveUsers() {
        ArrayList<User> activeUsers = new ArrayList<>();
        for (User user : users) {
            if (user.isActive()) {
                activeUsers.add(user);
            }
        }
        return activeUsers;
    }
}
