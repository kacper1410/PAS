package pl.pas.model.repositories;

import org.junit.Test;
import pl.pas.model.entities.user.Administrator;
import pl.pas.model.entities.user.Client;
import pl.pas.model.entities.user.Employee;
import pl.pas.model.entities.user.User;

import java.util.List;
import java.util.UUID;
import static org.junit.Assert.*;

public class UserRepositoryTest {

    @Test
    public void addUser() {
        UserRepository userRepository = new UserRepository();
        Client client1 = new Client("abraxas", "Michał", "Majchrowski", 21);
        Client client2 = new Client("kacper1410", "Kacper", "Świercz", 21);
        Employee employee = new Employee("Martiego", "Patryk", "Kolanek");
        Administrator administrator = new Administrator("nero7410", "Daniel", "Łondka");

        assertEquals(userRepository.getAllUsers().size(), 0);

        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        UUID uuid3 = UUID.randomUUID();
        UUID uuid4 = UUID.randomUUID();

        userRepository.addUser(client1, uuid1);
        userRepository.addUser(client2, uuid2);
        userRepository.addUser(employee, uuid3);
        userRepository.addUser(administrator, uuid4);

        assertEquals(userRepository.getAllUsers().size(), 4);
        assertEquals(userRepository.getAllUsers().get(0), client1);
        assertEquals(userRepository.getAllUsers().get(1), client2);
        assertEquals(userRepository.getAllUsers().get(2), employee);
        assertEquals(userRepository.getAllUsers().get(3), administrator);
    }

    @Test
    public void getUser() {
        // Get User by UUID
        UserRepository userRepository = new UserRepository();
        Client client1 = new Client("abraxas", "Michał", "Majchrowski", 21);
        Client client2 = new Client("kacper1410", "Kacper", "Świercz", 21);
        Employee employee = new Employee("Martiego", "Patryk", "Kolanek");
        Administrator administrator = new Administrator("nero7410", "Daniel", "Łondka");
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        UUID uuid3 = UUID.randomUUID();
        UUID uuid4 = UUID.randomUUID();

        client1.setUserId(uuid1);
        client2.setUserId(uuid2);
        employee.setUserId(uuid3);
        administrator.setUserId(uuid4);

        userRepository.getAllUsers().add(client1);
        userRepository.getAllUsers().add(client2);
        userRepository.getAllUsers().add(employee);
        userRepository.getAllUsers().add(administrator);

        assertEquals(userRepository.getUser(uuid1), client1);
        assertEquals(userRepository.getUser(uuid2), client2);
        assertEquals(userRepository.getUser(uuid3), employee);
        assertEquals(userRepository.getUser(uuid4), administrator);
    }

    @Test
    public void testGetUser() {
        // Get User by login
        UserRepository userRepository = new UserRepository();
        Client client1 = new Client("abraxas", "Michał", "Majchrowski", 21);
        Client client2 = new Client("kacper1410", "Kacper", "Świercz", 21);
        Employee employee = new Employee("Martiego", "Patryk", "Kolanek");
        Administrator administrator = new Administrator("nero7410", "Daniel", "Łondka");
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        UUID uuid3 = UUID.randomUUID();
        UUID uuid4 = UUID.randomUUID();

        client1.setUserId(uuid1);
        client2.setUserId(uuid2);
        employee.setUserId(uuid3);
        administrator.setUserId(uuid4);

        userRepository.getAllUsers().add(client1);
        userRepository.getAllUsers().add(client2);
        userRepository.getAllUsers().add(employee);
        userRepository.getAllUsers().add(administrator);

        assertEquals(userRepository.getUser("abraxas"), client1);
        assertEquals(userRepository.getUser("kacper1410"), client2);
        assertEquals(userRepository.getUser("Martiego"), employee);
        assertEquals(userRepository.getUser("nero7410"), administrator);
    }

    @Test
    public void getAllUsers() {

        UserRepository userRepository = new UserRepository();
        Client client1 = new Client("abraxas", "Michał", "Majchrowski", 21);
        Client client2 = new Client("kacper1410", "Kacper", "Świercz", 21);
        Employee employee = new Employee("Martiego", "Patryk", "Kolanek");
        Administrator administrator = new Administrator("nero7410", "Daniel", "Łondka");

        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        UUID uuid3 = UUID.randomUUID();
        UUID uuid4 = UUID.randomUUID();

        client1.setUserId(uuid1);
        client2.setUserId(uuid2);
        employee.setUserId(uuid3);
        administrator.setUserId(uuid4);

        assertEquals(userRepository.getAllUsers().size(), 0);

        userRepository.getAllUsers().add(client1);
        userRepository.getAllUsers().add(client2);
        userRepository.getAllUsers().add(employee);
        userRepository.getAllUsers().add(administrator);

        assertEquals(userRepository.getAllUsers().size(), 4);
    }


    @Test
    public void updateUser() {
        UserRepository userRepository = new UserRepository();
        Client client1 = new Client("abraxas", "Michał", "Majchrowski", 21);
        Client client2 = new Client("kacper1410", "Kacper", "Świercz", 21);
        Employee employee = new Employee("Martiego", "Patryk", "Kolanek");
        Administrator administrator = new Administrator("nero7410", "Daniel", "Łondka");

        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        UUID uuid3 = UUID.randomUUID();
        UUID uuid4 = UUID.randomUUID();

        client1.setUserId(uuid1);
        client2.setUserId(uuid2);
        employee.setUserId(uuid3);
        administrator.setUserId(uuid4);

        userRepository.getAllUsers().add(client1);
        userRepository.getAllUsers().add(client2);
        userRepository.getAllUsers().add(employee);
        userRepository.getAllUsers().add(administrator);


        // Test login change
        assertEquals(userRepository.getAllUsers().get(1).getLogin(), "kacper1410");
        assertEquals(userRepository.getAllUsers().get(1).getName(), "Kacper");
        assertEquals(userRepository.getAllUsers().get(1).getLastName(), "Świercz");
        assertEquals(((Client)userRepository.getAllUsers().get(1)).getAge(), 21);

        Client clientUpdated1 = new Client("Brudero", "Kacper", "Świercz", 21);

        userRepository.updateUser(uuid2, clientUpdated1);

        assertEquals(userRepository.getAllUsers().get(1).getLogin(), "Brudero");
        assertEquals(userRepository.getAllUsers().get(1).getName(), "Kacper");
        assertEquals(userRepository.getAllUsers().get(1).getLastName(), "Świercz");
        assertEquals(((Client)userRepository.getAllUsers().get(1)).getAge(), 21);


        // Test name change
        assertEquals(userRepository.getAllUsers().get(3).getLogin(), "nero7410");
        assertEquals(userRepository.getAllUsers().get(3).getName(), "Daniel");
        assertEquals(userRepository.getAllUsers().get(3).getLastName(), "Łondka");

        Administrator administratorUpdate = new Administrator("nero7410", "Jacek", "Łondka");
        userRepository.updateUser(uuid4, administratorUpdate);

        assertEquals(userRepository.getAllUsers().get(3).getLogin(), "nero7410");
        assertEquals(userRepository.getAllUsers().get(3).getName(), "Jacek");
        assertEquals(userRepository.getAllUsers().get(3).getLastName(), "Łondka");


        // Test lastName change
        assertEquals(userRepository.getAllUsers().get(2).getLogin(), "Martiego");
        assertEquals(userRepository.getAllUsers().get(2).getName(), "Patryk");
        assertEquals(userRepository.getAllUsers().get(2).getLastName(), "Kolanek");

        Employee employeeUpdate3 = new Employee("Martiego", "Patryk", "Janiak");
        userRepository.updateUser(uuid3, employeeUpdate3);

        assertEquals(userRepository.getAllUsers().get(2).getLogin(), "Martiego");
        assertEquals(userRepository.getAllUsers().get(2).getName(), "Patryk");
        assertEquals(userRepository.getAllUsers().get(2).getLastName(), "Janiak");

        // Test age change
        assertEquals(userRepository.getAllUsers().get(0).getLogin(), "abraxas");
        assertEquals(userRepository.getAllUsers().get(0).getName(), "Michał");
        assertEquals(userRepository.getAllUsers().get(0).getLastName(), "Majchrowski");
        assertEquals(((Client)userRepository.getAllUsers().get(0)).getAge(), 21);

        Client clientUpdate2 = new Client("abraxas", "Michał", "Majchrowski", 69);
        userRepository.updateUser(uuid1, clientUpdate2);

        assertEquals(userRepository.getAllUsers().get(0).getLogin(), "abraxas");
        assertEquals(userRepository.getAllUsers().get(0).getName(), "Michał");
        assertEquals(userRepository.getAllUsers().get(0).getLastName(), "Majchrowski");
        assertEquals(((Client)userRepository.getAllUsers().get(0)).getAge(), 69);
    }
}