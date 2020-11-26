package pl.pas.repositories;

import org.junit.Test;
import pl.pas.model.user.Administrator;
import pl.pas.model.user.Client;
import pl.pas.model.user.Employee;

import java.util.UUID;
import static org.junit.Assert.*;

public class UserRepositoryTest {
    UserRepository userRepository;
    Client client1;
    Client client2;
    Employee employee;
    Administrator administrator;

    public UserRepositoryTest() {
        userRepository = new UserRepository();
        client1 = new Client("abraxas", "Michał", "Majchrowski", 21);
        client2 = new Client("kacper1410", "Kacper", "Świercz", 21);
        employee = new Employee("Martiego", "Patryk", "Kolanek");
        administrator = new Administrator("nero7410", "Daniel", "Łondka");
    }

    @Test
    public void testAddUser() {
        assertEquals(userRepository.getAllUsers().size(), 0);

        userRepository.addUser(client1);
        userRepository.addUser(client2);
        userRepository.addUser(employee);
        userRepository.addUser(administrator);

        assertEquals(userRepository.getAllUsers().size(), 4);
        assertEquals(userRepository.getAllUsers().get(0), client1);
        assertEquals(userRepository.getAllUsers().get(1), client2);
        assertEquals(userRepository.getAllUsers().get(2), employee);
        assertEquals(userRepository.getAllUsers().get(3), administrator);
    }



    @Test
    public void testGetUser() {
        userRepository.addUser(client1);
        userRepository.addUser(client2);
        userRepository.addUser(employee);
        userRepository.addUser(administrator);

        //Test getUser for ID
        assertEquals(userRepository.getUser(client1.getUserId()), client1);
        assertEquals(userRepository.getUser(client2.getUserId()), client2);
        assertEquals(userRepository.getUser(employee.getUserId()), employee);
        assertEquals(userRepository.getUser(administrator.getUserId()), administrator);

        //Test getUser for login
        assertEquals(userRepository.getUser("abraxas"), client1);
        assertEquals(userRepository.getUser("kacper1410"), client2);
        assertEquals(userRepository.getUser("Martiego"), employee);
        assertEquals(userRepository.getUser("nero7410"), administrator);
    }

    @Test
    public void testGetAllUsers() {
        assertEquals(userRepository.getAllUsers().size(), 0);

        userRepository.addUser(client1);
        userRepository.addUser(client2);
        userRepository.addUser(employee);
        userRepository.addUser(administrator);

        assertEquals(userRepository.getAllUsers().size(), 4);
    }


    @Test
    public void testUpdateUser() {
        userRepository.addUser(client1);
        userRepository.addUser(client2);
        userRepository.addUser(employee);
        userRepository.addUser(administrator);

        // Test login change
        assertEquals(userRepository.getAllUsers().get(1).getLogin(), "kacper1410");
        assertEquals(userRepository.getAllUsers().get(1).getName(), "Kacper");
        assertEquals(userRepository.getAllUsers().get(1).getLastName(), "Świercz");
        assertEquals(((Client)userRepository.getAllUsers().get(1)).getAge(), 21);

        Client clientUpdated1 = new Client("Brudero", "Kacper", "Świercz", 21);
        userRepository.updateUser(client2.getUserId(), clientUpdated1);

        assertEquals(userRepository.getAllUsers().get(1).getLogin(), "Brudero");
        assertEquals(userRepository.getAllUsers().get(1).getName(), "Kacper");
        assertEquals(userRepository.getAllUsers().get(1).getLastName(), "Świercz");
        assertEquals(((Client)userRepository.getAllUsers().get(1)).getAge(), 21);


        // Test name change
        assertEquals(userRepository.getAllUsers().get(3).getLogin(), "nero7410");
        assertEquals(userRepository.getAllUsers().get(3).getName(), "Daniel");
        assertEquals(userRepository.getAllUsers().get(3).getLastName(), "Łondka");

        Administrator administratorUpdate = new Administrator("nero7410", "Jacek", "Łondka");
        userRepository.updateUser(administrator.getUserId(), administratorUpdate);

        assertEquals(userRepository.getAllUsers().get(3).getLogin(), "nero7410");
        assertEquals(userRepository.getAllUsers().get(3).getName(), "Jacek");
        assertEquals(userRepository.getAllUsers().get(3).getLastName(), "Łondka");


        // Test lastName change
        assertEquals(userRepository.getAllUsers().get(2).getLogin(), "Martiego");
        assertEquals(userRepository.getAllUsers().get(2).getName(), "Patryk");
        assertEquals(userRepository.getAllUsers().get(2).getLastName(), "Kolanek");

        Employee employeeUpdate3 = new Employee("Martiego", "Patryk", "Janiak");
        userRepository.updateUser(employee.getUserId(), employeeUpdate3);

        assertEquals(userRepository.getAllUsers().get(2).getLogin(), "Martiego");
        assertEquals(userRepository.getAllUsers().get(2).getName(), "Patryk");
        assertEquals(userRepository.getAllUsers().get(2).getLastName(), "Janiak");

        // Test age change
        assertEquals(userRepository.getAllUsers().get(0).getLogin(), "abraxas");
        assertEquals(userRepository.getAllUsers().get(0).getName(), "Michał");
        assertEquals(userRepository.getAllUsers().get(0).getLastName(), "Majchrowski");
        assertEquals(((Client)userRepository.getAllUsers().get(0)).getAge(), 21);

        Client clientUpdate2 = new Client("abraxas", "Michał", "Majchrowski", 69);
        userRepository.updateUser(client1.getUserId(), clientUpdate2);

        assertEquals(userRepository.getAllUsers().get(0).getLogin(), "abraxas");
        assertEquals(userRepository.getAllUsers().get(0).getName(), "Michał");
        assertEquals(userRepository.getAllUsers().get(0).getLastName(), "Majchrowski");
        assertEquals(((Client)userRepository.getAllUsers().get(0)).getAge(), 69);
    }
}