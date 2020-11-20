package pl.pas.model;

import pl.pas.model.repositories.BorrowRepository;
import pl.pas.model.repositories.ResourceRepository;
import pl.pas.model.repositories.UserRepository;
import pl.pas.model.user.Administrator;
import pl.pas.model.user.Client;
import pl.pas.model.user.Employee;
import pl.pas.model.user.User;

import java.util.ArrayList;

public class Manager {
    UserRepository userRepository;
    ResourceRepository resourceRepository;
    BorrowRepository borrowRepository;

    public Manager() {
        this.userRepository = new UserRepository();
        this.resourceRepository = new ResourceRepository();
        this.borrowRepository = new BorrowRepository();
    }

    public boolean addUser(String login, String name, String lastName, UserType userType) {
        if (userType == UserType.CLIENT) {
            return false;
        }

        return addUser(login, name, lastName, 0, userType);
    }

    public boolean addUser(String login, String name, String lastName, int age, UserType userType) {
        if (login == null || name == null || lastName == null) {
            return false;
        }

        switch (userType) {
            case CLIENT:
                return userRepository.add(new Client(login, name, lastName, age));
            case EMPLOYEE:
                return userRepository.add(new Employee(login, name, lastName));
            case ADMINISTRATOR:
                return userRepository.add(new Administrator(login, name, lastName));
        }

        return false;
    }

    public ArrayList<User> getAllUsers() {
        return userRepository.getAll();
    }

    public enum UserType {
        CLIENT,
        EMPLOYEE,
        ADMINISTRATOR
    }


}
