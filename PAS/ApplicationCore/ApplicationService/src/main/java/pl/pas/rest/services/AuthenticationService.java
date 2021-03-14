package pl.pas.rest.services;

import pl.pas.data.exceptions.NotFoundException;
import pl.pas.managers.UserManager;
import pl.pas.data.model.user.User;
import pl.pas.rest.security.JWTGenerator;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@RequestScoped
@Path("authenticate")
public class AuthenticationService {

    @Inject
    private IdentityStoreHandler identityStoreHandler;

    @Inject
    private UserManager userManager;

    public AuthenticationService() {
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public Response authenticate(@NotNull LoginData loginData) {
        Credential credential = new UsernamePasswordCredential(loginData.getLogin(), loginData.getPassword());
        CredentialValidationResult result = identityStoreHandler.validate(credential);
        if (result.getStatus() == CredentialValidationResult.Status.VALID) {
            return Response.accepted()
                    .type("application/jwt")
                    .entity(JWTGenerator.generateJWT(result))
                    .build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @GET
    @Path("refreshJWT")
    @Produces({MediaType.TEXT_PLAIN})
    public Response refreshJWT(@Context SecurityContext securityContext) {
        CredentialValidationResult result;
        try {
            User user = userManager.getUser(securityContext.getUserPrincipal().getName());
            Credential credential = new UsernamePasswordCredential(user.getLogin(), user.getPassword());
            result = identityStoreHandler.validate(credential);
        } catch (NotFoundException e) {
            throw new InternalServerErrorException();
        }

        if (result.getStatus() == CredentialValidationResult.Status.VALID) {
            return Response.accepted()
                    .type("application/jwt")
                    .entity(JWTGenerator.generateJWT(result))
                    .build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    public static class LoginData {

        private String login;

        private String password;

        public void setLogin(String login) {
            this.login = login;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }

}
