package pl.pas.controllers;

import lombok.Data;

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

    public String login() {
        // TODO add log information for (un)successful login
        try {
            request.login(login, password);
            //login successful
        } catch (ServletException e) {
            e.printStackTrace();
            //login unsuccessful
            return "loginError";
        }
        return "main";
    }
}

