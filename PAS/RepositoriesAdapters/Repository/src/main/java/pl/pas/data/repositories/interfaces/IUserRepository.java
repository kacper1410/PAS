package pl.pas.data.repositories.interfaces;

import pl.pas.data.model.exceptions.NotFoundExceptionEntity;
import pl.pas.data.model.exceptions.UserAlreadyExistExceptionEntity;
import pl.pas.data.model.user.AdministratorEntity;
import pl.pas.data.model.user.ClientEntity;
import pl.pas.data.model.user.EmployeeEntity;
import pl.pas.data.model.user.UserEntity;

import java.util.List;

public interface IUserRepository {
    void addUser(UserEntity user) throws UserAlreadyExistExceptionEntity;
    UserEntity getUser(long uuid) throws NotFoundExceptionEntity;
    UserEntity getUser(String login) throws NotFoundExceptionEntity;
    List<UserEntity> getAllUsers();
    void updateUser(long uuid, UserEntity newUser);
    List<ClientEntity> getAllClients();
    List<EmployeeEntity> getAllEmployees();
    List<AdministratorEntity> getAllAdministrators();
    List<UserEntity> getAllActiveUsers();
    UserEntity getUserByLoginPasswordActive(String login, String password) throws NotFoundExceptionEntity;
}
