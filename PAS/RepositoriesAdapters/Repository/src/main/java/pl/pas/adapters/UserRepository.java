package pl.pas.adapters;

import pl.pas.data.model.exceptions.NotFoundExceptionEntity;
import pl.pas.data.model.exceptions.UserAlreadyExistExceptionEntity;
import pl.pas.data.model.user.AdministratorEntity;
import pl.pas.data.model.user.ClientEntity;
import pl.pas.data.model.user.EmployeeEntity;
import pl.pas.data.model.user.UserEntity;
import pl.pas.adapters.interfaces.IUserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class UserRepository implements IUserRepository, Serializable {

    private final List<UserEntity> users;

    public UserRepository() {
        users = new ArrayList<>();
    }

    @Override
    public void addUser(UserEntity user) throws UserAlreadyExistExceptionEntity {
        synchronized (users) {
            try {
                if (users.contains(getUser(user.getLogin()))) {
                    throw new UserAlreadyExistExceptionEntity();
                }
            } catch (NotFoundExceptionEntity ignored) {

            }

            user.setUserId(UUID.randomUUID());
            users.add(user);
        }
    }

    @Override
    public UserEntity getUser(long uuid) throws NotFoundExceptionEntity {
        synchronized (users) {
            for (UserEntity u: users) {
                if (u.getUserId() == uuid) {
                    return u;
                }
            }
            throw new NotFoundExceptionEntity();
        }
    }

    @Override
    public UserEntity getUser(String login) throws NotFoundExceptionEntity {
        synchronized (users) {
            for (UserEntity u: users) {
                if (u.getLogin().equals(login)) {
                    return u;
                }
            }
            throw new NotFoundExceptionEntity();
        }
    }

    @Override
    public List<UserEntity> getAllUsers() {
        synchronized (users) {
            return new ArrayList<>(users);
        }
    }

    @Override
    public void updateUser(long uuid, UserEntity newUser) {
        synchronized (users) {
            for (UserEntity u : users) {
                if (u.getUserId() == uuid) {
                    newUser.setUserId(uuid);
                    try {
                        newUser.setActive(getUser(uuid).isActive());
                    } catch (NotFoundExceptionEntity ignored) {
                    }
                    users.set(users.indexOf(u), newUser);
                }
            }
        }
    }

    @Override
    public List<ClientEntity> getAllClients() {
        synchronized (users) {
            ArrayList<ClientEntity> clients = new ArrayList<>();

            for (UserEntity user : users) {
                if (user instanceof ClientEntity) {
                    clients.add((ClientEntity) user);
                }
            }

            return clients;
        }
    }

    @Override
    public List<EmployeeEntity> getAllEmployees() {
        synchronized (users) {
            ArrayList<EmployeeEntity> employees = new ArrayList<>();

            for (UserEntity user : users) {
                if (user instanceof EmployeeEntity) {
                    employees.add((EmployeeEntity) user);
                }
            }

            return employees;
        }
    }

    @Override
    public List<AdministratorEntity> getAllAdministrators() {
        synchronized (users) {
            ArrayList<AdministratorEntity> administrators = new ArrayList<>();

            for (UserEntity user : users) {
                if (user instanceof AdministratorEntity) {
                    administrators.add((AdministratorEntity) user);
                }
            }

            return administrators;
        }
    }

    @Override
    public List<UserEntity> getAllActiveUsers() {
        synchronized (users) {
            ArrayList<UserEntity> activeUsers = new ArrayList<>();

            for (UserEntity user : users) {
                if (user.isActive()) {
                    activeUsers.add(user);
                }
            }

            return activeUsers;
        }
    }

    @Override
    public UserEntity getUserByLoginPasswordActive(String login, String password) throws NotFoundExceptionEntity {
        synchronized (users) {
            for (UserEntity u: users) {
                if (u.getLogin().equals(login) && u.getPassword().equals(password) && u.isActive()) {
                    return u;
                }
            }
        }
        throw new NotFoundExceptionEntity();
    }
}
