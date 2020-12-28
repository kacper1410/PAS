package pl.pas.controllers;

import lombok.Data;
import pl.pas.managers.UserManager;
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
    private String login;
    private String password;
    @Inject
    private HttpServletRequest request;
    @Inject
    private UserManager userManager;

    public String login() {
        // TODO add log information for (un)successful login
        try {
            User user = userManager.getUser(login);
            if (user == null) {
                // Nie ma w repozytorium takiego loginu
                return "loginError";
            } else if (!user.isActive()) {
                // Użytkownik jest nieaktywny
                return "loginError";
            }
            request.login(login, password);
            // Poprawne uwierzytelnienie
        } catch (ServletException e) {
            // Brak uwierzytelnienia
            return "loginError";
        }
        return "main";
    }
}

