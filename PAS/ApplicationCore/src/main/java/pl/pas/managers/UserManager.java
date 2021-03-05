package pl.pas.managers;

import pl.pas.exceptions.NotValidException;
import pl.pas.exceptions.UserAlreadyExistException;
import pl.pas.exceptions.NotFoundException;
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


    public void addAdministrator(Administrator admin) throws UserAlreadyExistException, NotValidException {
        if (admin == null || admin.getLogin() == null || admin.getPassword() == null || admin.getName() == null
                || admin.getLastName() == null) {
            throw new NotValidException();
        }

        userRepository.addUser(new Administrator(admin.getLogin(), admin.getPassword(), admin.getName(), admin.getLastName()));
    }

    public void addEmployee(Employee employee) throws UserAlreadyExistException, NotValidException {
        if (employee == null || employee.getLogin() == null || employee.getPassword() == null || employee.getName() == null
                || employee.getLastName() == null) {
            throw new NotValidException();
        }

        userRepository.addUser(new Employee(employee.getLogin(), employee.getPassword(), employee.getName(), employee.getLastName()));
    }

    public void addClient(Client client) throws UserAlreadyExistException, NotValidException {
        if (client == null || client.getLogin() == null || client.getPassword() == null || client.getName() == null
                || client.getLastName() == null || client.getAge() < 0) {
            throw new NotValidException();
        }

        userRepository.addUser(new Client(client.getLogin(), client.getPassword(), client.getName(), client.getLastName(), client.getAge()));
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUser(long id) throws NotFoundException {
        return userRepository.getUser(id);
    }

    public User getUser(String login) throws NotFoundException {
        return userRepository.getUser(login);
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

    public void updateClient(long oldUserId, Client newClient) throws NotValidException, NotFoundException {
        User oldUser = userRepository.getUser(oldUserId);

        if (oldUserId <= 0
                || getUser(oldUserId) == null
                || newClient == null
                || newClient.getLogin() == null
                || !newClient.getPassword().equals("")
                || newClient.getName() == null
                || newClient.getAge() < 0
                || !(oldUser instanceof Client)) {
            throw new NotValidException();
        }
        userRepository.updateUser(oldUserId, new Client(oldUser.getLogin(),
                oldUser.getPassword(), newClient.getName(),
                newClient.getLastName(), newClient.getAge()));
    }

    public void updateUser(long oldUserId, User newUser) throws NotValidException, NotFoundException {
        User oldUser = userRepository.getUser(oldUserId);

        if (oldUserId <= 0
                || getUser(oldUserId) == null
                || newUser.getLogin() == null
                || !newUser.getPassword().equals("")
                || newUser.getName() == null
                || newUser.getLastName() == null) {
            throw new NotValidException();
        }

        if (oldUser instanceof Employee) {
            userRepository.updateUser(oldUserId, new Employee(oldUser.getLogin(),
                    oldUser.getPassword(), newUser.getName(), newUser.getLastName()));
        } else if (oldUser instanceof Administrator) {
            userRepository.updateUser(oldUserId, new Administrator(oldUser.getLogin(),
                    oldUser.getPassword(), newUser.getName(), newUser.getLastName()));
        }
    }


    public void activateUser(User user) throws NotValidException, NotFoundException {
        if (user == null) {
            throw new NotValidException();
        }
        userRepository.getUser(user.getLogin()).setActive(true);
    }

    public void deactivateUser(User user) throws NotValidException, NotFoundException {
        if (user == null) {
            throw new NotValidException();
        }
        userRepository.getUser(user.getLogin()).setActive(false);
    }

}
