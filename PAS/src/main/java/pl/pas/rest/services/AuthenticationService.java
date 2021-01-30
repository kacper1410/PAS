package pl.pas.rest.services;

import pl.pas.rest.security.JWTGenerator;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("authenticate")
public class AuthenticationService {

    @Inject
    private IdentityStoreHandler identityStoreHandler;

    public AuthenticationService() {}

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
