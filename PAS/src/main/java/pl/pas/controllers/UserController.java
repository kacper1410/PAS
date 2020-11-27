package pl.pas.controllers;

import pl.pas.managers.UserManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ApplicationScoped
public class UserController implements Serializable {

    @Inject
    private UserManager userManager;

    public UserManager getUserManager() {
        return userManager;
    }

    public String userList() {
        return "userList";
    }
}
