package pl.pas.ports.infrastructure.user;

import pl.pas.exceptions.UserAlreadyExistException;
import pl.pas.model.user.Administrator;
import pl.pas.model.user.Client;
import pl.pas.model.user.Employee;

public interface CreateUserPort {
    void createClient(Client client) throws UserAlreadyExistException;
    void createEmployee(Employee employee) throws UserAlreadyExistException;
    void createAdministrator(Administrator administrator) throws UserAlreadyExistException;
}
