package pl.pas.controllers;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Named
@SessionScoped
public class IdentityUtils implements Serializable {

    @Inject
    private HttpServletRequest httpServletRequest;

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
}
