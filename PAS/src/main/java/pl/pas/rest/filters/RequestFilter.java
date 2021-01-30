package pl.pas.rest.filters;

import pl.pas.rest.IdentitySignVerifier;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@SignatureValidatorFilterBinding
public class RequestFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String ifMatch = containerRequestContext.getHeaderString("If-match");

        if (ifMatch == null || ifMatch.isEmpty()) {
            containerRequestContext.abortWith(Response.status(Response.Status.BAD_REQUEST).build());
        } else if (!IdentitySignVerifier.isEntitySignatureValid(ifMatch)) {
            containerRequestContext.abortWith(Response.status(Response.Status.PRECONDITION_FAILED).build());
        }
    }
}
