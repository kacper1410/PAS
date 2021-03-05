package pl.pas.repositories;

import pl.pas.exceptions.NotFoundException;
import pl.pas.exceptions.UserAlreadyExistException;
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
    public void addUser(User user) throws UserAlreadyExistException {
        synchronized (users) {
            try {
                if (users.contains(getUser(user.getLogin()))) {
                    throw new UserAlreadyExistException();
                }
            } catch (NotFoundException ignored) {

            }

            user.setUserId(UUID.randomUUID());
            users.add(user);
        }
    }

    @Override
    public User getUser(long uuid) throws NotFoundException {
        synchronized (users) {
            for (User u: users) {
                if (u.getUserId() == uuid) {
                    return u;
                }
            }
            throw new NotFoundException();
        }
    }

    @Override
    public User getUser(String login) throws NotFoundException {
        synchronized (users) {
            for (User u: users) {
                if (u.getLogin().equals(login)) {
                    return u;
                }
            }
            throw new NotFoundException();
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
                    try {
                        newUser.setActive(getUser(uuid).isActive());
                    } catch (NotFoundException ignored) {
                    }
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
    public List<Employee> getAllEmployees() {
        synchronized (users) {
            ArrayList<Employee> employees = new ArrayList<>();

            for (User user : users) {
                if (user instanceof Employee) {
                    employees.add((Employee) user);
                }
            }

            return employees;
        }
    }

    @Override
    public List<Administrator> getAllAdministrators() {
        synchronized (users) {
            ArrayList<Administrator> administrators = new ArrayList<>();

            for (User user : users) {
                if (user instanceof Administrator) {
                    administrators.add((Administrator) user);
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

    @Override
    public User getUserByLoginPasswordActive(String login, String password) throws NotFoundException {
        synchronized (users) {
            for (User u: users) {
                if (u.getLogin().equals(login) && u.getPassword().equals(password) && u.isActive()) {
                    return u;
                }
            }
        }
        throw new NotFoundException();
    }
}
