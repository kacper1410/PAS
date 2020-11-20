package pl.pas.model;

import pl.pas.model.repositories.BorrowRepository;
import pl.pas.model.repositories.ResourceRepository;
import pl.pas.model.repositories.UserRepository;
import pl.pas.model.user.Administrator;
import pl.pas.model.user.Client;
import pl.pas.model.user.Employee;
import pl.pas.model.user.User;

import java.util.List;
import java.util.UUID;

public class Manager {
    UserRepository userRepository;
    ResourceRepository resourceRepository;
    BorrowRepository borrowRepository;

    public enum UserType {
        CLIENT,
        EMPLOYEE,
        ADMINISTRATOR
    }

    public Manager() {
        this.userRepository = new UserRepository();
        this.resourceRepository = new ResourceRepository();
        this.borrowRepository = new BorrowRepository();
    }

    public boolean addUser(String login, String name, String lastName, UserType userType) {
        return userRepository.add(createUser(login, name, lastName, userType));
    }

    public boolean addUser(String login, String name, String lastName, int age, UserType userType) {
        return userRepository.add(createUser(login, name, lastName, age, userType));
    }

    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    public User getUser(UUID id) {
        return userRepository.get(id);
    }

    public User getUser(String login) {
        return userRepository.get(login);
    }

    public boolean updateUser(User oldUser, String login,  String name, String lastName, UserType userType) {
        return userRepository.update(oldUser, createUser(login, name, lastName, userType));
    }

    public boolean updateUser(User oldUser, String login,  String name, String lastName, int age, UserType userType) {
        return userRepository.update(oldUser, createUser(login, name, lastName, age, userType));
    }

    private User createUser(String login,  String name, String lastName, UserType userType) {
        if (userType == UserType.CLIENT) {
            return null;
        }

        return createUser(login, name, lastName, 0, userType);
    }

    private User createUser(String login,  String name, String lastName, int age, UserType userType) {
        if (login == null || name == null || lastName == null) {
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
