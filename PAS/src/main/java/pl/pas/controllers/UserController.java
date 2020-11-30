package pl.pas.controllers;

import pl.pas.managers.UserManager;
import pl.pas.model.user.Client;

import javax.enterprise.context.ApplicationScoped;
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


    public UserManager getUserManager() {
        return userManager;
    }

    public String userList() {
        return "userList";
    }
}
