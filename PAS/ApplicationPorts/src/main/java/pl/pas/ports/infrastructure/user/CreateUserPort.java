package pl.pas.ports.infrastructure.user;

import pl.pas.data.exceptions.UserAlreadyExistException;
import pl.pas.data.model.user.User;

public interface CreateUserPort {
    void createUser(User user) throws UserAlreadyExistException;
}
