package pl.pas.managers;

import pl.pas.domain.exceptions.NotValidException;
import pl.pas.domain.exceptions.UserAlreadyExistException;
import pl.pas.domain.exceptions.NotFoundException;
import pl.pas.domain.model.user.Administrator;
import pl.pas.domain.model.user.Client;
import pl.pas.domain.model.user.Employee;
import pl.pas.domain.model.user.User;
import pl.pas.ports.infrastructure.user.CreateUserPort;
import pl.pas.ports.infrastructure.user.ReadUserPort;
import pl.pas.ports.infrastructure.user.UpdateUserPort;

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
    private CreateUserPort createUserPort;
    @Inject
    private ReadUserPort readUserPort;
    @Inject
    private UpdateUserPort updateUser;

    public UserManager() {
    }

    public void addAdministrator(Administrator admin) throws UserAlreadyExistException, NotValidException {
        if (admin == null || admin.getLogin() == null || admin.getPassword() == null || admin.getName() == null
                || admin.getLastName() == null) {
            throw new NotValidException();
        }

        createUserPort.createUser(new Administrator(admin.getLogin(), admin.getPassword(), admin.getName(), admin.getLastName()));
    }

    public void addEmployee(Employee employee) throws UserAlreadyExistException, NotValidException {
        if (employee == null || employee.getLogin() == null || employee.getPassword() == null || employee.getName() == null
                || employee.getLastName() == null) {
            throw new NotValidException();
        }

        createUserPort.createUser(new Employee(employee.getLogin(), employee.getPassword(), employee.getName(), employee.getLastName()));
    }

    public void addClient(Client client) throws UserAlreadyExistException, NotValidException {
        if (client == null || client.getLogin() == null || client.getPassword() == null || client.getName() == null
                || client.getLastName() == null || client.getAge() < 0) {
            throw new NotValidException();
        }

        createUserPort.createUser(new Client(client.getLogin(), client.getPassword(), client.getName(), client.getLastName(), client.getAge()));
    }

    public List<User> getAllUsers() {
        return readUserPort.readAllUsers();
    }

    public User getUser(long id) throws NotFoundException {
        return readUserPort.readUser(id);
    }

    public User getUser(String login) throws NotFoundException {
        return readUserPort.readUser(login);
    }

    public List<Client> getAllClients() {
        return readUserPort.readAllClients();
    }

    public List<Employee> getAllEmployees() {
        return readUserPort.readAllEmployees();
    }

    public List<Administrator> getAllAdministrators() {
        return readUserPort.readAllAdministrators();
    }

    public List<User> getAllActiveUsers() {
        return readUserPort.readAllActiveUsers();
    }

    public List<Client> getAllActiveClients() {
        List<Client> activeClients = new ArrayList<>();

        for (Client client: readUserPort.readAllClients()) {
            if (client.isActive()) {
                activeClients.add(client);
            }
        }

        return activeClients;
    }

    public void updateClient(long oldUserId, Client newClient) throws NotValidException, NotFoundException {
        User oldUser = readUserPort.readUser(oldUserId);

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
        updateUser.updateUser(oldUserId, new Client(oldUser.getLogin(),
                oldUser.getPassword(), newClient.getName(),
                newClient.getLastName(), newClient.getAge()));
    }

    public void updateUser(long oldUserId, User newUser) throws NotValidException, NotFoundException {
        User oldUser = readUserPort.readUser(oldUserId);

        if (oldUserId <= 0
                || getUser(oldUserId) == null
                || newUser.getLogin() == null
                || !newUser.getPassword().equals("")
                || newUser.getName() == null
                || newUser.getLastName() == null) {
            throw new NotValidException();
        }

        if (oldUser instanceof Employee) {
            updateUser.updateUser(oldUserId, new Employee(oldUser.getLogin(),
                    oldUser.getPassword(), newUser.getName(), newUser.getLastName()));
        } else if (oldUser instanceof Administrator) {
            updateUser.updateUser(oldUserId, new Administrator(oldUser.getLogin(),
                    oldUser.getPassword(), newUser.getName(), newUser.getLastName()));
        }
    }


    public void activateUser(User user) throws NotValidException, NotFoundException {
        if (user == null) {
            throw new NotValidException();
        }
        readUserPort.readUser(user.getLogin()).setActive(true);
    }

    public void deactivateUser(User user) throws NotValidException, NotFoundException {
        if (user == null) {
            throw new NotValidException();
        }
        readUserPort.readUser(user.getLogin()).setActive(false);
    }

}
