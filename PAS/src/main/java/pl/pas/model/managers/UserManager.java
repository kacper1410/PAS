package pl.pas.model.managers;

import pl.pas.model.entities.user.*;
import pl.pas.model.repositories.interfaces.IBorrowRepository;
import pl.pas.model.repositories.interfaces.IResourceRepository;
import pl.pas.model.repositories.interfaces.IUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserManager {
    IUserRepository userRepository;
    IResourceRepository resourceRepository;
    IBorrowRepository borrowRepository;
    public final static int LOGIN_FIELD = 0;
    public final static int NAME_FIELD = 1;
    public final static int LASTNAME_FIELD = 2;
    public final static int AGE_FIELD = 3;

    public UserManager(IUserRepository userRepository, IResourceRepository resourceRepository, IBorrowRepository borrowRepository) {
        this.userRepository = userRepository;
        this.resourceRepository = resourceRepository;
        this.borrowRepository = borrowRepository;
    }


    public boolean addAdministrator(String login, String name, String lastName) {
        if (login == null || name == null || lastName == null) {
            return false;
        }
        return userRepository.addUser(new Administrator(login, name, lastName));
    }

    public boolean addEmployee(String login, String name, String lastName) {
        if (login == null || name == null || lastName == null) {
            return false;
        }
        return userRepository.addUser(new Employee(login, name, lastName));
    }

    public boolean addClient(String login, String name, String lastName, int age) {
        if (login == null || name == null || lastName == null || age > 0) {
            return false;
        }
        return userRepository.addUser(new Client(login, name, lastName, age));
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUser(UUID id) {
        return userRepository.getUser(id);
    }

    public User getUser(String login) {
        return userRepository.getUser(login);
    }

    public List<User> getAllClients() {
        ArrayList<User> clients = new ArrayList<>();
        for (User user : userRepository.getAllUsers()) {
            if (user instanceof Client) {
                clients.add(user);
            }
        }
        return clients;
    }

    public List<User> getAllEmployees() {
        ArrayList<User> employees = new ArrayList<>();
        for (User user : userRepository.getAllUsers()) {
            if (user instanceof Employee) {
                employees.add(user);
            }
        }
        return employees;
    }

    public List<User> getAllAdministrators() {
        ArrayList<User> administrators = new ArrayList<>();
        for (User user : userRepository.getAllUsers()) {
            if (user instanceof Administrator) {
                administrators.add(user);
            }
        }
        return administrators;
    }

    public List<User> getAllActiveUsers() {
        ArrayList<User> users = new ArrayList<>();
        for (User user : userRepository.getAllUsers()) {
            if (user.isActive()) {
                users.add(user);
            }
        }
        return users;
    }

    public boolean updateClient(User oldUser, String login, String name, String lastName, int age) {
        if (login == null || name == null || lastName == null || age > 0) {
            return false;
        }
        userRepository.updateUser(oldUser, new Client(login, name, lastName, age));
        return true;
    }

    public boolean updateEmployee(User oldUser, String login, String name, String lastName) {
        if (login == null || name == null || lastName == null) {
            return false;
        }
        userRepository.updateUser(oldUser, new Employee(login, name, lastName));
        return true;
    }

    public boolean updateAdministrator(User oldUser, String login, String name, String lastName) {
        if (login == null || name == null || lastName == null) {
            return false;
        }
        userRepository.updateUser(oldUser, new Administrator(login, name, lastName));
        return true;
    }




}
