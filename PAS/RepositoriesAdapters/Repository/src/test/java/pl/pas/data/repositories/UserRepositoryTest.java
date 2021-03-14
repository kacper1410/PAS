package pl.pas.data.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.TestAbortedException;
import pl.pas.data.model.exceptions.NotFoundExceptionEntity;
import pl.pas.data.model.exceptions.UserAlreadyExistExceptionEntity;
import pl.pas.data.model.user.AdministratorEntity;
import pl.pas.data.model.user.ClientEntity;
import pl.pas.data.model.user.EmployeeEntity;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private final UserRepository userRepository;
    private final ClientEntity client1;
    private final ClientEntity client2;
    private final ClientEntity client3;
    private final EmployeeEntity employee;
    private final AdministratorEntity administrator;

    public UserRepositoryTest() {
        userRepository = new UserRepository();
        client1 = new ClientEntity("abraxas", "chałmi", "Michał", "Majchrowski", 21);
        client2 = new ClientEntity("kacper1410", "perkac", "Kacper", "Świercz", 21);
        client3 = new ClientEntity("logowanko", "nabadanko", "jak", "wół", 42);
        employee = new EmployeeEntity("Martiego", "trykpa", "Patryk", "Kolanek");
        administrator = new AdministratorEntity("nero7410", "nielda", "Daniel", "Łondka");
    }

    @Test
    void addUser() {
        Assertions.assertEquals(userRepository.getAllUsers().size(), 0);

        try {
            userRepository.addUser(client1);
            userRepository.addUser(client2);
            userRepository.addUser(employee);
            userRepository.addUser(administrator);
        } catch (UserAlreadyExistExceptionEntity e) {
            throw new TestAbortedException();
        }

        Assertions.assertEquals(userRepository.getAllUsers().size(), 4);
        Assertions.assertEquals(userRepository.getAllUsers().get(0), client1);
        Assertions.assertEquals(userRepository.getAllUsers().get(1), client2);
        Assertions.assertEquals(userRepository.getAllUsers().get(2), employee);
        Assertions.assertEquals(userRepository.getAllUsers().get(3), administrator);
    }

    @Test
    void getUser() {
        try {
            userRepository.addUser(client1);
            userRepository.addUser(client2);
            userRepository.addUser(employee);
            userRepository.addUser(administrator);
        } catch (UserAlreadyExistExceptionEntity e) {
            throw new TestAbortedException();
        }

        try {
            //Test getUser for ID
            Assertions.assertEquals(userRepository.getUser(client1.getUserId()), client1);
            Assertions.assertEquals(userRepository.getUser(client2.getUserId()), client2);
            Assertions.assertEquals(userRepository.getUser(employee.getUserId()), employee);
            Assertions.assertEquals(userRepository.getUser(administrator.getUserId()), administrator);

            //Test getUser for login
            Assertions.assertEquals(userRepository.getUser("abraxas"), client1);
            Assertions.assertEquals(userRepository.getUser("kacper1410"), client2);
            Assertions.assertEquals(userRepository.getUser("Martiego"), employee);
            Assertions.assertEquals(userRepository.getUser("nero7410"), administrator);
        } catch (NotFoundExceptionEntity e) {
            throw new TestAbortedException();
        }
    }

    @Test
    void getAllUsers() {
        Assertions.assertEquals(userRepository.getAllUsers().size(), 0);

        try {
            userRepository.addUser(client1);
            userRepository.addUser(client2);
            userRepository.addUser(employee);
            userRepository.addUser(administrator);
        } catch (UserAlreadyExistExceptionEntity e) {
            throw new TestAbortedException();
        }

        Assertions.assertEquals(userRepository.getAllUsers().size(), 4);
    }

    @Test
    void updateUser() {
        try {
            userRepository.addUser(client1);
            userRepository.addUser(client2);
            userRepository.addUser(employee);
            userRepository.addUser(administrator);
            userRepository.addUser(client3);
        } catch (UserAlreadyExistExceptionEntity e) {
            throw new TestAbortedException();
        }

        // Test login change
        Assertions.assertEquals(userRepository.getAllUsers().get(1).getLogin(), "kacper1410");
        Assertions.assertEquals(userRepository.getAllUsers().get(1).getName(), "Kacper");
        Assertions.assertEquals(userRepository.getAllUsers().get(1).getLastName(), "Świercz");
        Assertions.assertEquals(((ClientEntity)userRepository.getAllUsers().get(1)).getAge(), 21);

        ClientEntity clientUpdated1 = new ClientEntity("Brudero", "perkac", "Kacper", "Świercz", 21);
        userRepository.updateUser(client2.getUserId(), clientUpdated1);

        Assertions.assertEquals(userRepository.getAllUsers().get(1).getLogin(), "Brudero");
        Assertions.assertEquals(userRepository.getAllUsers().get(1).getPassword(), "perkac");
        Assertions.assertEquals(userRepository.getAllUsers().get(1).getName(), "Kacper");
        Assertions.assertEquals(userRepository.getAllUsers().get(1).getLastName(), "Świercz");
        Assertions.assertEquals(((ClientEntity)userRepository.getAllUsers().get(1)).getAge(), 21);


        // Test name change
        Assertions.assertEquals(userRepository.getAllUsers().get(3).getLogin(), "nero7410");
        Assertions.assertEquals(userRepository.getAllUsers().get(3).getName(), "Daniel");
        Assertions.assertEquals(userRepository.getAllUsers().get(3).getLastName(), "Łondka");

        AdministratorEntity administratorUpdate = new AdministratorEntity("nero7410", "nielda", "Jacek", "Łondka");
        userRepository.updateUser(administrator.getUserId(), administratorUpdate);

        Assertions.assertEquals(userRepository.getAllUsers().get(3).getLogin(), "nero7410");
        Assertions.assertEquals(userRepository.getAllUsers().get(3).getPassword(), "nielda");
        Assertions.assertEquals(userRepository.getAllUsers().get(3).getName(), "Jacek");
        Assertions.assertEquals(userRepository.getAllUsers().get(3).getLastName(), "Łondka");


        // Test lastName change
        Assertions.assertEquals(userRepository.getAllUsers().get(2).getLogin(), "Martiego");
        Assertions.assertEquals(userRepository.getAllUsers().get(2).getName(), "Patryk");
        Assertions.assertEquals(userRepository.getAllUsers().get(2).getLastName(), "Kolanek");

        EmployeeEntity employeeUpdate3 = new EmployeeEntity("Martiego", "cekja", "Patryk", "Janiak");
        userRepository.updateUser(employee.getUserId(), employeeUpdate3);

        Assertions.assertEquals(userRepository.getAllUsers().get(2).getLogin(), "Martiego");
        Assertions.assertEquals(userRepository.getAllUsers().get(2).getName(), "Patryk");
        Assertions.assertEquals(userRepository.getAllUsers().get(2).getLastName(), "Janiak");

        // Test age change
        Assertions.assertEquals(userRepository.getAllUsers().get(0).getLogin(), "abraxas");
        Assertions.assertEquals(userRepository.getAllUsers().get(0).getName(), "Michał");
        Assertions.assertEquals(userRepository.getAllUsers().get(0).getLastName(), "Majchrowski");
        Assertions.assertEquals(((ClientEntity)userRepository.getAllUsers().get(0)).getAge(), 21);

        ClientEntity clientUpdate2 = new ClientEntity("abraxas", "chałmi", "Michał", "Majchrowski", 69);
        userRepository.updateUser(client1.getUserId(), clientUpdate2);

        Assertions.assertEquals(userRepository.getAllUsers().get(0).getLogin(), "abraxas");
        Assertions.assertEquals(userRepository.getAllUsers().get(0).getPassword(), "chałmi");
        Assertions.assertEquals(userRepository.getAllUsers().get(0).getName(), "Michał");
        Assertions.assertEquals(userRepository.getAllUsers().get(0).getLastName(), "Majchrowski");
        Assertions.assertEquals(((ClientEntity)userRepository.getAllUsers().get(0)).getAge(), 69);

        // Test password change
        Assertions.assertEquals(userRepository.getAllUsers().get(4).getLogin(), "logowanko");
        Assertions.assertEquals(userRepository.getAllUsers().get(4).getPassword(), "nabadanko");
        Assertions.assertEquals(userRepository.getAllUsers().get(4).getName(), "jak");
        Assertions.assertEquals(userRepository.getAllUsers().get(4).getLastName(), "wół");
        Assertions.assertEquals(((ClientEntity)userRepository.getAllUsers().get(4)).getAge(), 42);

        ClientEntity clientUpdate3 = new ClientEntity("logowanko", "morska", "jak", "wół", 42);
        userRepository.updateUser(client3.getUserId(), clientUpdate3);

        Assertions.assertEquals(userRepository.getAllUsers().get(4).getLogin(), "logowanko");
        Assertions.assertEquals(userRepository.getAllUsers().get(4).getPassword(), "morska");
        Assertions.assertEquals(userRepository.getAllUsers().get(4).getName(), "jak");
        Assertions.assertEquals(userRepository.getAllUsers().get(4).getLastName(), "wół");
        Assertions.assertEquals(((ClientEntity)userRepository.getAllUsers().get(4)).getAge(), 42);
    }

    @Test
    void getAllClients() {
        Assertions.assertEquals(0, userRepository.getAllClients().size());

        try {
            userRepository.addUser(client1);
            userRepository.addUser(client2);
            userRepository.addUser(employee);
            userRepository.addUser(administrator);
        } catch (UserAlreadyExistExceptionEntity e) {
            throw new TestAbortedException();
        }

        Assertions.assertEquals(2, userRepository.getAllClients().size());
        Assertions.assertEquals(client1, userRepository.getAllClients().get(0));
        Assertions.assertEquals(client2, userRepository.getAllClients().get(1));
    }

    @Test
    void getAllEmployees() {
        Assertions.assertEquals(0, userRepository.getAllEmployees().size());

        try {
            userRepository.addUser(client1);
            userRepository.addUser(client2);
            userRepository.addUser(employee);
            userRepository.addUser(administrator);
        } catch (UserAlreadyExistExceptionEntity e) {
            throw new TestAbortedException();
        }

        Assertions.assertEquals(1, userRepository.getAllEmployees().size());
        Assertions.assertEquals(employee, userRepository.getAllEmployees().get(0));
    }

    @Test
    void getAllAdministrators() {
        Assertions.assertEquals(0, userRepository.getAllAdministrators().size());

        try {
            userRepository.addUser(client1);
            userRepository.addUser(client2);
            userRepository.addUser(employee);
            userRepository.addUser(administrator);
        } catch (UserAlreadyExistExceptionEntity e) {
            throw new TestAbortedException();
        }

        Assertions.assertEquals(1, userRepository.getAllAdministrators().size());
        Assertions.assertEquals(administrator, userRepository.getAllAdministrators().get(0));
    }

    @Test
    void getAllActiveUsers() {
        Assertions.assertEquals(0, userRepository.getAllActiveUsers().size());

        try {
            userRepository.addUser(client1);
            userRepository.addUser(client2);
            userRepository.addUser(employee);
            userRepository.addUser(administrator);
        } catch (UserAlreadyExistExceptionEntity e) {
            throw new TestAbortedException();
        }

        client2.setActive(false);
        administrator.setActive(false);

        Assertions.assertEquals(2, userRepository.getAllActiveUsers().size());
        Assertions.assertEquals(client1, userRepository.getAllActiveUsers().get(0));
        Assertions.assertEquals(employee, userRepository.getAllActiveUsers().get(1));
    }

    @Test
    void getUserByLoginPasswordActive() {

        //User do not exist
        assertThrows(NotFoundExceptionEntity.class,
                () -> userRepository.getUserByLoginPasswordActive(
                        client1.getLogin(), client1.getPassword()
                ));

        try {
            userRepository.addUser(client1);
            userRepository.addUser(client2);
            userRepository.addUser(employee);
            userRepository.addUser(administrator);
        } catch (UserAlreadyExistExceptionEntity e) {
            throw new TestAbortedException();
        }

        //Correct
        try {
            Assertions.assertEquals(client1, userRepository.getUserByLoginPasswordActive(
                    client1.getLogin(), client1.getPassword()
                    ));
        } catch (NotFoundExceptionEntity notFoundExceptionEntity) {
            throw new TestAbortedException();
        }

        //Wrong password
        assertThrows(NotFoundExceptionEntity.class,
                () -> userRepository.getUserByLoginPasswordActive(
                        client1.getLogin(), client2.getPassword()
                ));

        //Wrong login
        assertThrows(NotFoundExceptionEntity.class,
                () -> userRepository.getUserByLoginPasswordActive(
                        client2.getLogin(), client1.getPassword()
                ));

        //Inactive user
        client1.setActive(false);
        assertThrows(NotFoundExceptionEntity.class,
                () -> userRepository.getUserByLoginPasswordActive(
                        client1.getLogin(), client1.getPassword()
                ));
    }
}
