package pl.pas.adapters;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import pl.pas.data.model.exceptions.NotFoundExceptionEntity;
import pl.pas.data.model.exceptions.UserAlreadyExistExceptionEntity;
import pl.pas.data.model.user.AdministratorEntity;
import pl.pas.data.model.user.ClientEntity;
import pl.pas.data.model.user.EmployeeEntity;

public class UserRepositoryTest {
    UserRepository userRepository;
    ClientEntity client1;
    ClientEntity client2;
    ClientEntity client3;
    EmployeeEntity employee;
    AdministratorEntity administrator;

    public UserRepositoryTest() {
        userRepository = new UserRepository();
        client1 = new ClientEntity("abraxas", "chałmi", "Michał", "Majchrowski", 21);
        client2 = new ClientEntity("kacper1410", "perkac", "Kacper", "Świercz", 21);
        client3 = new ClientEntity("logowanko", "nabadanko", "jak", "wół", 42);
        employee = new EmployeeEntity("Martiego", "trykpa", "Patryk", "Kolanek");
        administrator = new AdministratorEntity("nero7410", "nielda", "Daniel", "Łondka");
    }

    @Test
    public void testAddUser() {
        Assertions.assertEquals(userRepository.getAllUsers().size(), 0);

        try {
            userRepository.addUser(client1);
            userRepository.addUser(client2);
            userRepository.addUser(employee);
            userRepository.addUser(administrator);
        } catch (UserAlreadyExistExceptionEntity e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(userRepository.getAllUsers().size(), 4);
        Assertions.assertEquals(userRepository.getAllUsers().get(0), client1);
        Assertions.assertEquals(userRepository.getAllUsers().get(1), client2);
        Assertions.assertEquals(userRepository.getAllUsers().get(2), employee);
        Assertions.assertEquals(userRepository.getAllUsers().get(3), administrator);
    }

    @Test
    public void testGetUser() {
        try {
            userRepository.addUser(client1);
            userRepository.addUser(client2);
            userRepository.addUser(employee);
            userRepository.addUser(administrator);
        } catch (UserAlreadyExistExceptionEntity e) {
            e.printStackTrace();
        }

        //Test getUser for ID
        try {
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
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAllUsers() {
        Assertions.assertEquals(userRepository.getAllUsers().size(), 0);

        try {
            userRepository.addUser(client1);
            userRepository.addUser(client2);
            userRepository.addUser(employee);
            userRepository.addUser(administrator);
        } catch (UserAlreadyExistExceptionEntity e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(userRepository.getAllUsers().size(), 4);
    }

    @Test
    public void testUpdateUser() {
        try {
            userRepository.addUser(client1);
            userRepository.addUser(client2);
            userRepository.addUser(employee);
            userRepository.addUser(administrator);
            userRepository.addUser(client3);
        } catch (UserAlreadyExistExceptionEntity e) {
            e.printStackTrace();
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
}
