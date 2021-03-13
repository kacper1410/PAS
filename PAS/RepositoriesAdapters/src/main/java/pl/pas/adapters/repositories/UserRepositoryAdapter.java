package pl.pas.adapters.repositories;

import pl.pas.data.model.exceptions.NotFoundExceptionEntity;
import pl.pas.data.model.exceptions.UserAlreadyExistExceptionEntity;
import pl.pas.exceptions.NotFoundException;
import pl.pas.exceptions.UserAlreadyExistException;
import pl.pas.mappers.user.UserEntityMapper;
import pl.pas.model.user.Administrator;
import pl.pas.model.user.Client;
import pl.pas.model.user.Employee;
import pl.pas.model.user.User;
import pl.pas.ports.infrastructure.user.CreateUserPort;
import pl.pas.ports.infrastructure.user.ReadUserPort;
import pl.pas.ports.infrastructure.user.UpdateUserPort;
import pl.pas.repositories.UserRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class UserRepositoryAdapter implements CreateUserPort, ReadUserPort, UpdateUserPort {

    @Inject
    private UserRepository userRepository;

    @Override
    public void createUser(User user) throws UserAlreadyExistException {
        try {
            userRepository.addUser(UserEntityMapper.userToUserEntity(user));
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
        return userRepository.getAllEmployees()
                .stream()
                .map(userEntity -> (Employee) UserEntityMapper.userEntityToUser(userEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Administrator> readAllAdministrators() {
        return userRepository.getAllAdministrators()
                .stream()
                .map(userEntity -> (Administrator) UserEntityMapper.userEntityToUser(userEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> readAllActiveUsers() {
        return userRepository.getAllActiveUsers()
                .stream()
                .map(UserEntityMapper::userEntityToUser)
                .collect(Collectors.toList());
    }

    @Override
    public User readUserByLoginPasswordActive(String login, String password) throws NotFoundException {
        try {
            return UserEntityMapper.userEntityToUser(userRepository.getUserByLoginPasswordActive(login, password));
        } catch (NotFoundExceptionEntity notFoundExceptionEntity) {
            throw new NotFoundException();
        }
    }

    @Override
    public User readUser(long id) throws NotFoundException {
        try {
            return UserEntityMapper.userEntityToUser(userRepository.getUser(id));
        } catch (NotFoundExceptionEntity notFoundExceptionEntity) {
            throw new NotFoundException();
        }
    }

    @Override
    public User readUser(String login) throws NotFoundException{
        try {
            return UserEntityMapper.userEntityToUser(userRepository.getUser(login));
        } catch (NotFoundExceptionEntity notFoundExceptionEntity) {
            throw new NotFoundException();
        }
    }

    @Override
    public void updateUser(long uuid, User newUser) {
        userRepository.updateUser(uuid, UserEntityMapper.userToUserEntity(newUser));
    }

    @Override
    public List<User> readAllUsers() {
        return userRepository.getAllUsers()
                .stream()
                .map(UserEntityMapper::userEntityToUser)
                .collect(Collectors.toList());
    }
}
