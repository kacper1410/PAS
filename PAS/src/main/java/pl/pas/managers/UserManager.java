package pl.pas.managers;

import pl.pas.model.user.Administrator;
import pl.pas.model.user.Client;
import pl.pas.model.user.Employee;
import pl.pas.model.user.User;
import pl.pas.repositories.interfaces.IBorrowRepository;
import pl.pas.repositories.interfaces.IResourceRepository;
import pl.pas.repositories.interfaces.IUserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Named
@ApplicationScoped
public class UserManager implements Serializable {
    @Inject
    private IUserRepository userRepository;
    @Inject
    private IResourceRepository resourceRepository;
    @Inject
    private IBorrowRepository borrowRepository;
    public final static int LOGIN_FIELD = 0;
    public final static int NAME_FIELD = 1;
    public final static int LASTNAME_FIELD = 2;
    public final static int AGE_FIELD = 3;

    public UserManager() {
    }

    public UserManager(IUserRepository userRepository, IResourceRepository resourceRepository, IBorrowRepository borrowRepository) {
        this.userRepository = userRepository;
        this.resourceRepository = resourceRepository;
        this.borrowRepository = borrowRepository;
    }


    public boolean addAdministrator(String login, String name, String lastName) {
        if (login == null || name == null || lastName == null) {
            return false;
        }
        return userRepository.addUser(new Administrator(login, name, lastName), UUID.randomUUID());
    }

    public boolean addEmployee(String login, String name, String lastName) {
        if (login == null || name == null || lastName == null) {
            return false;
        }
        return userRepository.addUser(new Employee(login, name, lastName), UUID.randomUUID());
    }

    public boolean addClient(String login, String name, String lastName, int age) {
        if (login == null || name == null || lastName == null || age > 0) {
            return false;
        }
        return userRepository.addUser(new Client(login, name, lastName, age), UUID.randomUUID());
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUser(UUID id) {
        if (id == null) {
            return null;
        }
        return userRepository.getUser(id);
    }

    public User getUser(String login) {
        if (login == null) {
            return null;
        }
        return userRepository.getUser(login);
    }

    public List<User> getAllClients() {
        return userRepository.getAllClients();
    }

    public List<User> getAllEmployees() {
        return userRepository.getAllEmployees();
    }

    public List<User> getAllAdministrators() {
        return userRepository.getAllAdministrators();
    }

    public List<User> getAllActiveUsers() {
        return userRepository.getAllActiveUsers();
    }

    public boolean updateClient(User oldUser, String login, String name, String lastName, int age) {
        if (oldUser == null || userRepository.getUser(oldUser.getUserId()) == null
                || login == null || name == null || lastName == null || age > 0 || !(oldUser instanceof Client)) {
            return false;
        }
        userRepository.updateUser(oldUser.getUserId(), new Client(login, name, lastName, age));
        return true;
    }

    public boolean updateEmployee(User oldUser, String login, String name, String lastName) {
        if (oldUser == null || userRepository.getUser(oldUser.getUserId()) == null
                || login == null || name == null || lastName == null || !(oldUser instanceof Employee)) {
            return false;
        }
        userRepository.updateUser(oldUser.getUserId(), new Employee(login, name, lastName));
        return true;
    }

    public boolean updateAdministrator(User oldUser, String login, String name, String lastName) {
        if (oldUser == null || userRepository.getUser(oldUser.getUserId()) == null
                || login == null || name == null || lastName == null || !(oldUser instanceof Administrator)) {
            return false;
        }
        userRepository.updateUser(oldUser.getUserId(), new Administrator(login, name, lastName));
        return true;
    }

    public boolean activateUser(User user) {
        if (user == null) {
            return false;
        }
        user.setActive(true);
        return true;
    }

    public boolean deactivateUser(User user) {
        if (user == null) {
            return false;
        }
        user.setActive(false);
        return true;
    }

}
