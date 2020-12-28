package pl.pas.controllers;

import pl.pas.managers.UserManager;
import pl.pas.model.user.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@ApplicationScoped
public class IdentityUtils {

    @Inject
    private HttpServletRequest httpServletRequest;
    @Inject
    private UserManager userManager;

    public String getMyLogin() {
        return null == httpServletRequest.getUserPrincipal() ? "" : httpServletRequest.getUserPrincipal().getName();
    }

    public boolean isAdmin() {
        return httpServletRequest.isUserInRole("Administrator") && isActive();
    }

    public boolean isEmployee() {
        return httpServletRequest.isUserInRole("Employee") && isActive();
    }

    public boolean isClient() {
        return httpServletRequest.isUserInRole("Client") && isActive();
    }

    private boolean isActive() {
        User user = userManager.getUser(getMyLogin());
        return user != null && user.isActive();
    }
}
