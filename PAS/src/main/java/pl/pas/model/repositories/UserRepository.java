package pl.pas.model.repositories;

import pl.pas.model.user.User;
import java.util.UUID;

public class UserRepository extends Repository<User> {

    public UserRepository() {
        super();
    }

    @Override
    public User getByUUID(UUID uuid) {
        for (User user: this.getRepository()) {
            if (user.getUserId().equals(uuid))
                return user;
        }
        return null;
    }
}
