package pl.pas.model.managers;

import pl.pas.model.entities.Borrow;
import pl.pas.model.repositories.BorrowRepository;
import pl.pas.model.repositories.ResourceRepository;
import pl.pas.model.repositories.UserRepository;
import pl.pas.model.entities.user.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserManager {
    UserRepository userRepository;
    ResourceRepository resourceRepository;
    BorrowRepository borrowRepository;

    public UserManager(UserRepository userRepository, ResourceRepository resourceRepository, BorrowRepository borrowRepository) {
        this.userRepository = userRepository;
        this.resourceRepository = resourceRepository;
        this.borrowRepository = borrowRepository;
    }


    public boolean addUser(String login, String name, String lastName, UserType userType) {
        return userRepository.addUser(createUser(login, name, lastName, userType));
    }

    public boolean addUser(String login, String name, String lastName, int age, UserType userType) {
        return userRepository.addUser(createUser(login, name, lastName, age, userType));
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

    public boolean updateUser(User oldUser, String login,  String name, String lastName, UserType userType) {
        User newUser = createUser(login, name, lastName, userType);
        if (newUser == null && userRepository.getUser(oldUser.getUserId()) == null) {
            return false;
        }
        userRepository.updateUser(oldUser, newUser);
        return true;
    }

    public boolean updateUser(User oldUser, String login,  String name, String lastName, int age, UserType userType) {
        User newUser = createUser(login, name, lastName, age, userType);
        if (newUser == null && userRepository.getUser(oldUser.getUserId()) == null) {
            return false;
        }
        userRepository.updateUser(oldUser, newUser);
        return true;
    }

    private User createUser(String login,  String name, String lastName, UserType userType) {
        if (userType == UserType.CLIENT) {
            return null;
        }

        return createUser(login, name, lastName, 1, userType);
    }

    private User createUser(String login,  String name, String lastName, int age, UserType userType) {
        if (login == null || name == null || lastName == null || age > 0) {
            return null;
        }

        switch (userType) {
            case CLIENT:
                return new Client(login, name, lastName, age);
            case EMPLOYEE:
                return new Employee(login, name, lastName);
            case ADMINISTRATOR:
                return new Administrator(login, name, lastName);
        }

        return null;
    }



}
