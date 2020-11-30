package pl.pas.controllers;

import pl.pas.managers.UserManager;
import pl.pas.model.user.Administrator;
import pl.pas.model.user.Client;
import pl.pas.model.user.Employee;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class UserController implements Serializable {

    @Inject
    private UserManager userManager;
    private Client currentClient;
    private Client newClient;
    private Employee newEmployee;
    private Administrator newAdministrator;

    public UserController() {
        newClient = new Client();
        newEmployee = new Employee();
        newAdministrator = new Administrator();
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

    public String viewClient(Client client) {
        setCurrentClient(client);
        System.out.println(client);
        return "client";
    }

    public String userList() {
        return "userList";
    }

    public String processNewClient() {
        userManager.addClient(newClient.getLogin(), newClient.getName(), newClient.getLastName(), newClient.getAge());
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
}
