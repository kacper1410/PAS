package pl.pas.controllers;

import pl.pas.managers.UserManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class UserController {

    @Inject
    private UserManager userManager;

    public UserManager getUserManager() {
        return userManager;
    }
}
