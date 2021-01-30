package pl.pas.controllers;

import lombok.Data;
import pl.pas.exceptions.NotValidException;
import pl.pas.exceptions.UserAlreadyExistException;
import pl.pas.exceptions.UserNotFoundException;
import pl.pas.managers.UserManager;
import pl.pas.model.user.Administrator;
import pl.pas.model.user.Client;
import pl.pas.model.user.Employee;
import pl.pas.model.user.User;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Data
@Named
@SessionScoped
public class UserController implements Serializable {

    @Inject
    private UserManager userManager;
    @Inject
    private IdentityUtils identityUtils;

    private Client currentClient;
    private User currentUser;
    private Client newClient;
    private Employee newEmployee;
    private Administrator newAdministrator;
    private long userId;
    private String login;

    public UserController() {
        newClient = new Client();
        newEmployee = new Employee();
        newAdministrator = new Administrator();
    }

    public String viewClient(Client client) {
        setCurrentClient(client);
        return "client";
    }

    public String userList() {
        return "users";
    }

    public String processNewClient() {
        if (identityUtils.isAdmin()) {
            try {
                userManager.addClient(newClient);
            } catch (UserAlreadyExistException | NotValidException e) {
                e.printStackTrace();
            }
        }

        newClient = new Client();
        return "main";
    }
    public String processNewEmployee() {
        if (identityUtils.isAdmin()) {
            try {
                userManager.addEmployee(newEmployee);
            } catch (UserAlreadyExistException | NotValidException e) {
                e.printStackTrace();
            }
        }

        newEmployee = new Employee();
        return "main";
    }
    public String processNewAdministrator() {
        if (identityUtils.isAdmin()) {
            try {
                userManager.addAdministrator(newAdministrator);
            } catch (UserAlreadyExistException | NotValidException e) {
                e.printStackTrace();
            }
        }

        newAdministrator = new Administrator();
        return "main";
    }

    public String cancelNewClient() {
        newClient = new Client();
        return "main";
    }

    public String cancelNewEmployee() {
        newEmployee = new Employee();
        return "main";
    }

    public String cancelNewAdministrator() {
        newAdministrator = new Administrator();
        return "main";
    }

    public String changeActivity(User user) {
        if (identityUtils.isAdmin()) {
            if (user.isActive()) {
                userManager.deactivateUser(user);
            } else {
                userManager.activateUser(user);
            }
        }

        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true";
    }

    public String searchId(long uuid) {
        User user = null;

        try {
            user = userManager.getUser(uuid);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        if (user instanceof Client) {
            currentClient = (Client) user;
            return "client";
        } else {
            currentUser = user;
            return "user";
        }
    }

    public String searchLogin(String login) {
        User user = null;

        try {
            user = userManager.getUser(login);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        if (user instanceof Client) {
            currentClient = (Client) user;
            return "client";
        } else {
            currentUser = user;
            return "user";
        }
    }

    public String viewProfile() {
        return searchLogin(identityUtils.getMyLogin());
    }

    public String updateClient() {
        if (identityUtils.isAdmin()) {
            try {
                userManager.updateClient(currentClient.getUserId(), currentClient);
            } catch (NotValidException | UserNotFoundException e) {
                e.printStackTrace();
            }
        }

        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true";
    }

    public String updateUser() {
        if (identityUtils.isAdmin()) {
            try {
                userManager.updateUser(currentUser.getUserId(), currentUser);
            } catch (NotValidException | UserNotFoundException e) {
                e.printStackTrace();
            }
        }

        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true";
    }
}
