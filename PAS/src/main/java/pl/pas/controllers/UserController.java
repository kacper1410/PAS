package pl.pas.controllers;

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

@Named
@SessionScoped
public class UserController implements Serializable {

    @Inject
    private UserManager userManager;
    private Client currentClient;
    private User currentUser;
    private Client newClient;
    private Employee newEmployee;
    private Administrator newAdministrator;
    private long userId;
    private String login;

    public void setLogin(String login) {
        this.login = login;
    }
    public String getLogin() {
        return login;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
    public long getUserId() {
        return userId;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public Client getNewClient() {
        return newClient;
    }

    public Employee getNewEmployee() {
        return newEmployee;
    }

    public Administrator getNewAdministrator() {
        return newAdministrator;
    }

    public void setNewClient(Client client) {
        newClient = client;
    }

    public void setNewEmployee(Employee employee) {
        newEmployee = employee;
    }

    public void setNewAdministrator(Administrator administrator) {
        newAdministrator = administrator;
    }

    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public UserController() {
        newClient = new Client();
        newEmployee = new Employee();
        newAdministrator = new Administrator();
    }

    public String viewClient(Client client) {
        setCurrentClient(client);
        System.out.println(client);

        return "client";
    }

    public String userList() {
        return "users";
    }

    public String processNewClient() {
        userManager.addClient(newClient.getLogin(), newClient.getName(), newClient.getLastName(), newClient.getAge());
        System.out.println(userManager.getAllClients());
        newClient = new Client();
        return "main";
    }
    public String processNewEmployee() {
        userManager.addEmployee(newEmployee.getLogin(), newEmployee.getName(), newEmployee.getLastName());
        newEmployee = new Employee();
        return "main";
    }
    public String processNewAdministrator() {
        userManager.addAdministrator(newAdministrator.getLogin(), newAdministrator.getName(), newAdministrator.getLastName());
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
        if (user.isActive()) {
            userManager.deactivateUser(user);
        } else {
            userManager.activateUser(user);
        }
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true";
    }

    public String searchId() {
        User user = userManager.getUser(userId);
        if (user instanceof Client) {
            currentClient = (Client) user;
            return "client";
        } else {
            currentUser = user;
            return "user";
        }
    }

    public String searchLogin() {
        User user = userManager.getUser(login);
        if (user instanceof Client) {
            currentClient = (Client) user;
            return "client";
        } else {
            currentUser = user;
            return "user";
        }
    }

    public String updateClient() {
        userManager.updateClient(currentClient, currentClient.getLogin(), currentClient.getName(), currentClient.getLastName(), currentClient.getAge());
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true";
    }

    public String updateUser() {
        userManager.updateUser(currentUser, currentUser.getLogin(), currentUser.getName(), currentUser.getLastName());
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true";
    }
}
