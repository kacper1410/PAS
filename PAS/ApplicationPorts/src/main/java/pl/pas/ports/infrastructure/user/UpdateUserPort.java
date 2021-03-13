package pl.pas.ports.infrastructure.user;

import pl.pas.model.user.Administrator;
import pl.pas.model.user.Client;
import pl.pas.model.user.Employee;
import pl.pas.model.user.User;

public interface UpdateUserPort {
    void updateUser(long uuid, User newUser);
}
