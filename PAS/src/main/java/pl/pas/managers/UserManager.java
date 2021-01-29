package pl.pas.managers;

import pl.pas.exceptions.NotValidException;
import pl.pas.exceptions.UserAlreadyExistException;
import pl.pas.exceptions.UserNotFoundException;
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
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class UserManager implements Serializable {
    @Inject
    private IUserRepository userRepository;
    @Inject
    private IResourceRepository resourceRepository;
    @Inject
    private IBorrowRepository borrowRepository;

    public UserManager() {
    }

    public UserManager(IUserRepository userRepository, IResourceRepository resourceRepository, IBorrowRepository borrowRepository) {
        this.userRepository = userRepository;
        this.resourceRepository = resourceRepository;
        this.borrowRepository = borrowRepository;
    }


    public void addAdministrator(String login, String name, String lastName) throws UserAlreadyExistException, NotValidException {
        if (login == null || name == null || lastName == null) {
            throw new NotValidException();
        }

        userRepository.addUser(new Administrator(login, name, lastName));
    }

    public void addEmployee(String login, String name, String lastName) throws UserAlreadyExistException, NotValidException {
        if (login == null || name == null || lastName == null) {
            throw new NotValidException();
        }

        userRepository.addUser(new Employee(login, name, lastName));
    }

    public void addClient(String login, String name, String lastName, int age) throws UserAlreadyExistException, NotValidException {
        if (login == null || name == null || lastName == null || age < 0) {
            throw new NotValidException();
        }

        userRepository.addUser(new Client(login, name, lastName, age));
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUser(long id) throws UserNotFoundException {
        User user = userRepository.getUser(id);

        if (user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }

    public User getUser(String login) throws UserNotFoundException {
        User user = userRepository.getUser(login);

        if (user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }

    public List<Client> getAllClients() {
        return userRepository.getAllClients();
    }

    public List<Employee> getAllEmployees() {
        return userRepository.getAllEmployees();
    }

    public List<Administrator> getAllAdministrators() {
        return userRepository.getAllAdministrators();
    }

    public List<User> getAllActiveUsers() {
        return userRepository.getAllActiveUsers();
    }

    public List<Client> getAllActiveClients() {
        List<Client> activeClients = new ArrayList<>();

        for (Client client: userRepository.getAllClients()) {
            if (client.isActive()) {
                activeClients.add(client);
            }
        }

        return activeClients;
    }

    public void updateClient(User oldUser, String login, String name, String lastName, int age) throws NotValidException {
        if (oldUser == null || userRepository.getUser(oldUser.getUserId()) == null
                || login == null || name == null || lastName == null || age < 0 || !(oldUser instanceof Client)) {
            throw new NotValidException();
        }

        userRepository.updateUser(oldUser.getUserId(), new Client(login, name, lastName, age));
    }

    public void updateUser(User oldUser, String login, String name, String lastName) throws NotValidException {
        if (oldUser == null || userRepository.getUser(oldUser.getUserId()) == null
                || login == null || name == null || lastName == null) {
            throw new NotValidException();
        }

        if (oldUser instanceof Employee) {
            userRepository.updateUser(oldUser.getUserId(), new Employee(login, name, lastName));
        } else if (oldUser instanceof Administrator) {
            userRepository.updateUser(oldUser.getUserId(), new Administrator(login, name, lastName));
        }
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
