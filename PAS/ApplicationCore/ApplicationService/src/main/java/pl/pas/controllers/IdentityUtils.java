package pl.pas.controllers;

import pl.pas.managers.UserManager;
import pl.pas.domain.exceptions.NotFoundException;
import pl.pas.domain.model.user.User;
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
    @Inject
    private LoginController loginController;

    public String getMyLogin() {
        return null == httpServletRequest.getUserPrincipal() ? "" : httpServletRequest.getUserPrincipal().getName();
    }

    public boolean isAdmin() {
        return httpServletRequest.isUserInRole("Administrator");
    }

    public boolean isEmployee() {
        return httpServletRequest.isUserInRole("Employee");
    }

    public boolean isClient() {
        return httpServletRequest.isUserInRole("Client");
    }

    public boolean isActive() {
        try {
            User user = userManager.getUser(getMyLogin());

            if (user == null || !user.isActive()) {
                loginController.logout();
                return false;
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        return true;
    }
}
