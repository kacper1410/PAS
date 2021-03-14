package pl.pas.ports.infrastructure.user;

import pl.pas.domain.model.user.User;

public interface UpdateUserPort {
    void updateUser(long uuid, User newUser);
}
