package pl.pas.ports.infrastructure.user;

import pl.pas.domain.exceptions.UserAlreadyExistException;
import pl.pas.domain.model.user.User;

public interface CreateUserPort {
    void createUser(User user) throws UserAlreadyExistException;
}
