package pl.pas.repositories;

import org.junit.Test;
import pl.pas.exceptions.NotFoundException;
import pl.pas.exceptions.UserAlreadyExistException;
import pl.pas.model.user.Administrator;
import pl.pas.model.user.Client;
import pl.pas.model.user.Employee;

import static org.junit.Assert.*;

public class UserRepositoryTest {
    UserRepository userRepository;
    Client client1;
    Client client2;
    Client client3;
    Employee employee;
    Administrator administrator;

    public UserRepositoryTest() {
        userRepository = new UserRepository();
        client1 = new Client("abraxas", "chałmi", "Michał", "Majchrowski", 21);
        client2 = new Client("kacper1410", "perkac", "Kacper", "Świercz", 21);
        client3 = new Client("logowanko", "nabadanko", "jak", "wół", 42);
        employee = new Employee("Martiego", "trykpa", "Patryk", "Kolanek");
        administrator = new Administrator("nero7410", "nielda", "Daniel", "Łondka");
    }

    @Test
    public void testAddUser() {
        assertEquals(userRepository.getAllUsers().size(), 0);

        try {
            userRepository.addUser(client1);
            userRepository.addUser(client2);
            userRepository.addUser(employee);
            userRepository.addUser(administrator);
        } catch (UserAlreadyExistException e) {
            e.printStackTrace();
        }

        assertEquals(userRepository.getAllUsers().size(), 4);
        assertEquals(userRepository.getAllUsers().get(0), client1);
        assertEquals(userRepository.getAllUsers().get(1), client2);
        assertEquals(userRepository.getAllUsers().get(2), employee);
        assertEquals(userRepository.getAllUsers().get(3), administrator);
    }



    @Test
    public void testGetUser() {
        try {
            userRepository.addUser(client1);
            userRepository.addUser(client2);
            userRepository.addUser(employee);
            userRepository.addUser(administrator);
        } catch (UserAlreadyExistException e) {
            e.printStackTrace();
        }

        //Test getUser for ID
        try {
            assertEquals(userRepository.getUser(client1.getUserId()), client1);
            assertEquals(userRepository.getUser(client2.getUserId()), client2);
            assertEquals(userRepository.getUser(employee.getUserId()), employee);
            assertEquals(userRepository.getUser(administrator.getUserId()), administrator);

            //Test getUser for login
            assertEquals(userRepository.getUser("abraxas"), client1);
            assertEquals(userRepository.getUser("kacper1410"), client2);
            assertEquals(userRepository.getUser("Martiego"), employee);
            assertEquals(userRepository.getUser("nero7410"), administrator);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAllUsers() {
        assertEquals(userRepository.getAllUsers().size(), 0);

        try {
            userRepository.addUser(client1);
            userRepository.addUser(client2);
            userRepository.addUser(employee);
            userRepository.addUser(administrator);
        } catch (UserAlreadyExistException e) {
            e.printStackTrace();
        }

        assertEquals(userRepository.getAllUsers().size(), 4);
    }


    @Test
    public void testUpdateUser() {
        try {
            userRepository.addUser(client1);
            userRepository.addUser(client2);
            userRepository.addUser(employee);
            userRepository.addUser(administrator);
            userRepository.addUser(client3);
        } catch (UserAlreadyExistException e) {
            e.printStackTrace();
        }

        // Test login change
        assertEquals(userRepository.getAllUsers().get(1).getLogin(), "kacper1410");
        assertEquals(userRepository.getAllUsers().get(1).getName(), "Kacper");
        assertEquals(userRepository.getAllUsers().get(1).getLastName(), "Świercz");
        assertEquals(((Client)userRepository.getAllUsers().get(1)).getAge(), 21);

        Client clientUpdated1 = new Client("Brudero", "perkac", "Kacper", "Świercz", 21);
        userRepository.updateUser(client2.getUserId(), clientUpdated1);

        assertEquals(userRepository.getAllUsers().get(1).getLogin(), "Brudero");
        assertEquals(userRepository.getAllUsers().get(1).getPassword(), "perkac");
        assertEquals(userRepository.getAllUsers().get(1).getName(), "Kacper");
        assertEquals(userRepository.getAllUsers().get(1).getLastName(), "Świercz");
        assertEquals(((Client)userRepository.getAllUsers().get(1)).getAge(), 21);


        // Test name change
        assertEquals(userRepository.getAllUsers().get(3).getLogin(), "nero7410");
        assertEquals(userRepository.getAllUsers().get(3).getName(), "Daniel");
        assertEquals(userRepository.getAllUsers().get(3).getLastName(), "Łondka");

        Administrator administratorUpdate = new Administrator("nero7410", "nielda", "Jacek", "Łondka");
        userRepository.updateUser(administrator.getUserId(), administratorUpdate);

        assertEquals(userRepository.getAllUsers().get(3).getLogin(), "nero7410");
        assertEquals(userRepository.getAllUsers().get(3).getPassword(), "nielda");
        assertEquals(userRepository.getAllUsers().get(3).getName(), "Jacek");
        assertEquals(userRepository.getAllUsers().get(3).getLastName(), "Łondka");


        // Test lastName change
        assertEquals(userRepository.getAllUsers().get(2).getLogin(), "Martiego");
        assertEquals(userRepository.getAllUsers().get(2).getName(), "Patryk");
        assertEquals(userRepository.getAllUsers().get(2).getLastName(), "Kolanek");

        Employee employeeUpdate3 = new Employee("Martiego", "cekja", "Patryk", "Janiak");
        userRepository.updateUser(employee.getUserId(), employeeUpdate3);

        assertEquals(userRepository.getAllUsers().get(2).getLogin(), "Martiego");
        assertEquals(userRepository.getAllUsers().get(2).getName(), "Patryk");
        assertEquals(userRepository.getAllUsers().get(2).getLastName(), "Janiak");

        // Test age change
        assertEquals(userRepository.getAllUsers().get(0).getLogin(), "abraxas");
        assertEquals(userRepository.getAllUsers().get(0).getName(), "Michał");
        assertEquals(userRepository.getAllUsers().get(0).getLastName(), "Majchrowski");
        assertEquals(((Client)userRepository.getAllUsers().get(0)).getAge(), 21);

        Client clientUpdate2 = new Client("abraxas", "chałmi", "Michał", "Majchrowski", 69);
        userRepository.updateUser(client1.getUserId(), clientUpdate2);

        assertEquals(userRepository.getAllUsers().get(0).getLogin(), "abraxas");
        assertEquals(userRepository.getAllUsers().get(0).getPassword(), "chałmi");
        assertEquals(userRepository.getAllUsers().get(0).getName(), "Michał");
        assertEquals(userRepository.getAllUsers().get(0).getLastName(), "Majchrowski");
        assertEquals(((Client)userRepository.getAllUsers().get(0)).getAge(), 69);

        // Test password change
        assertEquals(userRepository.getAllUsers().get(4).getLogin(), "logowanko");
        assertEquals(userRepository.getAllUsers().get(4).getPassword(), "nabadanko");
        assertEquals(userRepository.getAllUsers().get(4).getName(), "jak");
        assertEquals(userRepository.getAllUsers().get(4).getLastName(), "wół");
        assertEquals(((Client)userRepository.getAllUsers().get(4)).getAge(), 42);

        Client clientUpdate3 = new Client("logowanko", "morska", "jak", "wół", 42);
        userRepository.updateUser(client3.getUserId(), clientUpdate3);

        assertEquals(userRepository.getAllUsers().get(4).getLogin(), "logowanko");
        assertEquals(userRepository.getAllUsers().get(4).getPassword(), "morska");
        assertEquals(userRepository.getAllUsers().get(4).getName(), "jak");
        assertEquals(userRepository.getAllUsers().get(4).getLastName(), "wół");
        assertEquals(((Client)userRepository.getAllUsers().get(4)).getAge(), 42);
    }
}
