package pl.pas.controllers;

import lombok.Data;
import pl.pas.managers.UserManager;
import pl.pas.exceptions.NotFoundException;
import pl.pas.logger.Priority;
import pl.pas.logger.Logger;
import pl.pas.model.user.User;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@SessionScoped
@Named
@Data
public class LoginController implements Serializable {

    @Inject
    private HttpServletRequest request;
    @Inject
    private UserManager userManager;
    @Inject
    private Logger logger;

    private String login;
    private String password;

    public String login() {
        try {
            User user = userManager.getUser(login);
            if (user == null) {
                logger.addLog(login, "There's no user with this login", Priority.WARNING);
                return "loginError";
            } else if (!user.isActive()) {
                logger.addLog(login, "User isn't active", Priority.WARNING);
                return "loginError";
            }
            request.login(login, password);
            logger.addLog(request.getUserPrincipal().getName(), "Welcome :)", Priority.INFO);
        } catch (ServletException | NotFoundException e) {
            logger.addLog("None", e.getMessage(), Priority.WARNING);
            return "loginError";
        }

        return "main";
    }

    public String logout() {
        request.getSession().invalidate();
        return "main";
    }
}

