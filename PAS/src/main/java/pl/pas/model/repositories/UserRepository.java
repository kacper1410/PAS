package pl.pas.model.repositories;

import pl.pas.model.user.User;
import java.util.UUID;

public class UserRepository extends Repository<User> {

    public UserRepository() {
        super();
    }

    @Override
    public User get(UUID uuid) {
        for (User user: this.getRepository()) {
            if (user.getUserId().equals(uuid)) return user;
        }
        return null;
    }

    @Override
    public String toString() {
        return "UserRepository{ " + super.toString() + " }";
    }

    @Override
    public boolean remove(User element) {
        return false;
    }

    @Override
    public boolean remove(int index) {
        return false;
    }

    @Override
    public boolean remove(UUID id) {
        for (int i = 0; i < getRepository().size(); i++) {
            if (getRepository().get(i).getUserId().equals(id)) {
                getRepository().set(i, null);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(UUID id, User element) {
        if (getRepository().contains(get(id)) && element != null) {
            element.setUserId(id);
            for (int i = 0; i < getRepository().size(); i++) {
                if (getRepository().get(i).getUserId().equals(id)) {
                    getRepository().set(i, element);
                    return true;
                }
            }
        }
        return false;
    }

    public User get(String login) {
        for (User user: this.getRepository()) {
            if (user.getLogin().equals(login)) return user;
        }
        return null;
    }

    public boolean setActive(User user, boolean active) {
        if (user != null  && getRepository().contains(user)) {
            user.setActive(active);
            return update(user.getUserId(), user);
        }
        return false;
    }
}
