package pl.pas.adapters.repositories;

import pl.pas.data.model.exceptions.UserAlreadyExistExceptionEntity;
import pl.pas.exceptions.NotFoundException;
import pl.pas.exceptions.UserAlreadyExistException;
import pl.pas.mappers.user.UserEntityMapper;
import pl.pas.model.user.Administrator;
import pl.pas.model.user.Client;
import pl.pas.model.user.Employee;
import pl.pas.ports.infrastructure.user.ReadUserPort;
import pl.pas.ports.infrastructure.user.UpdateUserPort;
import pl.pas.repositories.UserRepository;

import javax.inject.Named;
import java.util.List;

@Named
public class UserRepositoryAdapter implements CreateUserPort, ReadUserPort, UpdateUserPort {

    private UserRepository userRepository;

    @Override
    public void createClient(Client client) throws UserAlreadyExistException {
        try {
            userRepository.addUser(ClientEntityMapper.clientToClientEntity(client));
        } catch (UserAlreadyExistExceptionEntity userAlreadyExistExceptionEntity) {
            throw new UserAlreadyExistException();
        }
    }

    @Override
    public void createEmployee(Employee employee) throws UserAlreadyExistException {
        try {
            userRepository.addUser(EmployeeEntityMapper.employeeToEmployeeEntity(employee));
        } catch (UserAlreadyExistExceptionEntity userAlreadyExistExceptionEntity) {
            throw new UserAlreadyExistException();
        }
    }

    @Override
    public void createAdministrator(Administrator administrator) throws UserAlreadyExistException {
        try {
            userRepository.addUser(AdministratorEntityMapper.administratorToAdministratorEntity(administrator));
        } catch (UserAlreadyExistExceptionEntity userAlreadyExistExceptionEntity) {
            throw new UserAlreadyExistException();
        }
    }

    @Override
    public List<Client> readAllClients() {
        return userRepository.getAllClients()
                .stream()
                .map(userEntity -> (Client) UserEntityMapper.userEntityToUser(userEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> readAllEmployees() {
        return null;
    }

    @Override
    public List<Administrator> readAllAdministrators() {
        return null;
    }

    @Override
    public List<User> readAllActiveUsers() {
        return null;
    }

    @Override
    public User readUserByLoginPasswordActive(String login, String password) {
        return null;
    }

    @Override
    public User readUser(long id) {
        return null;
    }

    @Override
    public User readUser(String login) {
        return null;
    }

    @Override
    public void updateUser(long uuid, User newUser) {

    }
}
