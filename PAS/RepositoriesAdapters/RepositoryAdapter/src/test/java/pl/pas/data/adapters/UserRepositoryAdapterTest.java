package pl.pas.data.adapters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.pas.data.exceptions.NotFoundException;
import pl.pas.data.exceptions.UserAlreadyExistException;
import pl.pas.data.model.user.Administrator;
import pl.pas.data.model.user.Client;
import pl.pas.data.model.user.Employee;
import pl.pas.data.model.user.User;
import pl.pas.data.repositories.UserRepository;

import java.util.List;

class UserRepositoryAdapterTest {

    private final UserRepositoryAdapter userRepositoryAdapter;

    private final Client client;
    private final Client client1;

    private final Employee employee;

    private final Administrator administrator;

    public UserRepositoryAdapterTest() {
        UserRepository userRepository = new UserRepository();
        userRepositoryAdapter = new UserRepositoryAdapter(userRepository);

        client = new Client("abraxas", "chałmi", "Michał", "Majchrowski", 21);
        client1 = new Client("kacper1410", "perkac", "Kacper", "Świercz", 22);

        employee = new Employee("Martiego", "trykpa", "Patryk", "Kolanek");

        administrator = new Administrator("nero7410", "nielda", "Daniel", "Łondka");
    }

    @Test
    void createUser() throws UserAlreadyExistException {
        userRepositoryAdapter.createUser(client);
        userRepositoryAdapter.createUser(employee);
        userRepositoryAdapter.createUser(administrator);

        List<User> users = userRepositoryAdapter.readAllUsers();

        client.setUserId(users.get(0).getUserId());
        employee.setUserId(users.get(1).getUserId());
        administrator.setUserId(users.get(2).getUserId());

        Assertions.assertEquals(3, users.size());
        Assertions.assertEquals(client, users.get(0));
        Assertions.assertEquals(employee, users.get(1));
        Assertions.assertEquals(administrator, users.get(2));
    }

    @Test
    void readAllClients() throws UserAlreadyExistException {
        userRepositoryAdapter.createUser(client);
        userRepositoryAdapter.createUser(client1);
        userRepositoryAdapter.createUser(employee);
        userRepositoryAdapter.createUser(administrator);

        List<Client> clients = userRepositoryAdapter.readAllClients();

        client.setUserId(clients.get(0).getUserId());
        client1.setUserId(clients.get(1).getUserId());

        Assertions.assertEquals(2, clients.size());
        Assertions.assertEquals(client, clients.get(0));
        Assertions.assertEquals(client1, clients.get(1));
    }

    @Test
    void readAllEmployees() throws UserAlreadyExistException {
        userRepositoryAdapter.createUser(client);
        userRepositoryAdapter.createUser(employee);
        userRepositoryAdapter.createUser(administrator);

        List<Employee> employees = userRepositoryAdapter.readAllEmployees();

        employee.setUserId(employees.get(0).getUserId());

        Assertions.assertEquals(1, employees.size());
        Assertions.assertEquals(employee, employees.get(0));
    }

    @Test
    void readAllAdministrators() throws UserAlreadyExistException {
        userRepositoryAdapter.createUser(client);
        userRepositoryAdapter.createUser(employee);
        userRepositoryAdapter.createUser(administrator);

        List<Administrator> administrators = userRepositoryAdapter.readAllAdministrators();

        administrator.setUserId(administrators.get(0).getUserId());

        Assertions.assertEquals(1, administrators.size());
        Assertions.assertEquals(administrator, administrators.get(0));
    }

    @Test
    void readAllActiveUsers() throws UserAlreadyExistException {
        client.setActive(true);
        client1.setActive(false);
        employee.setActive(true);
        administrator.setActive(false);

        userRepositoryAdapter.createUser(client);
        userRepositoryAdapter.createUser(client1);
        userRepositoryAdapter.createUser(employee);
        userRepositoryAdapter.createUser(administrator);

        List<User> users = userRepositoryAdapter.readAllActiveUsers();

        client.setUserId(users.get(0).getUserId());
        employee.setUserId(users.get(1).getUserId());

        Assertions.assertEquals(2, users.size());
        Assertions.assertEquals(client, users.get(0));
        Assertions.assertEquals(employee, users.get(1));
    }

    @Test
    void readUserByLoginPasswordActive() throws UserAlreadyExistException, NotFoundException {
        userRepositoryAdapter.createUser(client);
        userRepositoryAdapter.createUser(employee);
        userRepositoryAdapter.createUser(administrator);

        List<User> users = userRepositoryAdapter.readAllUsers();

        client.setUserId(users.get(0).getUserId());
        employee.setUserId(users.get(1).getUserId());
        administrator.setUserId(users.get(2).getUserId());

        User user = userRepositoryAdapter.readUserByLoginPasswordActive("abraxas", "chałmi");

        Assertions.assertEquals(client, user);

        user = userRepositoryAdapter.readUserByLoginPasswordActive("Martiego", "trykpa");

        Assertions.assertEquals(employee, user);

        user = userRepositoryAdapter.readUserByLoginPasswordActive("nero7410", "nielda");

        Assertions.assertEquals(administrator, user);
    }

    @Test
    void readUser() throws UserAlreadyExistException, NotFoundException {
        userRepositoryAdapter.createUser(client);
        userRepositoryAdapter.createUser(employee);
        userRepositoryAdapter.createUser(administrator);

        List<User> users = userRepositoryAdapter.readAllUsers();

        client.setUserId(users.get(0).getUserId());
        employee.setUserId(users.get(1).getUserId());
        administrator.setUserId(users.get(2).getUserId());

        User user = userRepositoryAdapter.readUser(client.getUserId());

        Assertions.assertEquals(client, user);

        user = userRepositoryAdapter.readUser(employee.getUserId());

        Assertions.assertEquals(employee, user);

        user = userRepositoryAdapter.readUser(administrator.getUserId());

        Assertions.assertEquals(administrator, user);
    }

    @Test
    void updateUser() throws UserAlreadyExistException {
        userRepositoryAdapter.createUser(client);

        List<User> users = userRepositoryAdapter.readAllUsers();

        client.setUserId(users.get(0).getUserId());

        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals(client, users.get(0));

        userRepositoryAdapter.updateUser(client.getUserId(), client1);

        users = userRepositoryAdapter.readAllUsers();

        client1.setUserId(users.get(0).getUserId());

        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals(client1, users.get(0));
    }

    @Test
    void readAllUsers() throws UserAlreadyExistException {
        userRepositoryAdapter.createUser(client);
        userRepositoryAdapter.createUser(employee);
        userRepositoryAdapter.createUser(administrator);

        List<User> users = userRepositoryAdapter.readAllUsers();

        client.setUserId(users.get(0).getUserId());
        employee.setUserId(users.get(1).getUserId());
        administrator.setUserId(users.get(2).getUserId());

        Assertions.assertEquals(3, users.size());
        Assertions.assertEquals(client, users.get(0));
        Assertions.assertEquals(employee, users.get(1));
        Assertions.assertEquals(administrator, users.get(2));
    }
}
