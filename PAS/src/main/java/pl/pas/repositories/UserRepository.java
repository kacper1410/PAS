package pl.pas.repositories;

import pl.pas.UUID;
import pl.pas.model.user.Administrator;
import pl.pas.model.user.Client;
import pl.pas.model.user.Employee;
import pl.pas.model.user.User;
import pl.pas.repositories.interfaces.IUserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class UserRepository implements IUserRepository, Serializable {

    private final List<User> users;

    public UserRepository() {
        users = new ArrayList<>();
    }

    @Override
    public boolean addUser(User user) {
        synchronized (users) {
            if (users.contains(getUser(user.getLogin()))) return false;

            user.setUserId(UUID.randomUUID());
            return users.add(user);
        }
    }

    @Override
    public User getUser(long uuid) {
        synchronized (users) {
            for (User u: users) {
                if (u.getUserId() == uuid) return u;
            }
            return null;
        }
    }

    @Override
    public User getUser(String login) {
        synchronized (users) {
            for (User u: users) {
                if (u.getLogin().equals(login)) return u;
            }
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        synchronized (users) {
            return new ArrayList<>(users);
        }
    }

    @Override
    public void updateUser(long uuid, User newUser) {
        synchronized (users) {
            for (User u : users) {
                if (u.getUserId() == uuid) {
                    newUser.setUserId(uuid);
                    users.set(users.indexOf(u), newUser);
                }
            }
        }
    }

    @Override
    public List<Client> getAllClients() {
        synchronized (users) {
            ArrayList<Client> clients = new ArrayList<>();
            for (User user : users) {
                if (user instanceof Client) {
                    clients.add((Client) user);
                }
            }
            return clients;
        }
    }

    @Override
    public List<User> getAllEmployees() {
        synchronized (users) {
            ArrayList<User> employees = new ArrayList<>();
            for (User user : users) {
                if (user instanceof Employee) {
                    employees.add(user);
                }
            }
            return employees;
        }
    }

    @Override
    public List<User> getAllAdministrators() {
        synchronized (users) {
            ArrayList<User> administrators = new ArrayList<>();
            for (User user : users) {
                if (user instanceof Administrator) {
                    administrators.add(user);
                }
            }
            return administrators;
        }
    }

    @Override
    public List<User> getAllActiveUsers() {
        synchronized (users) {
            ArrayList<User> activeUsers = new ArrayList<>();
            for (User user : users) {
                if (user.isActive()) {
                    activeUsers.add(user);
                }
            }
            return activeUsers;
        }
    }
}
