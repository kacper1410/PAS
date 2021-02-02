package pl.pas.rest.security;

import com.nimbusds.jwt.SignedJWT;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@ApplicationScoped
public class JWTAuthenticationMechanism implements HttpAuthenticationMechanism {

    @Override
    public AuthenticationStatus validateRequest(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            HttpMessageContext httpMessageContext) throws AuthenticationException {

//        if (httpServletRequest.getRequestURL().toString().endsWith(MySecurityConstants.AUTHENTICATE_URL)) {
//            return httpMessageContext.doNothing();
//        }

        if (!httpServletRequest.getRequestURL().toString().contains("profile")
                && !httpServletRequest.getRequestURL().toString().contains("allocate")
                && !httpServletRequest.getRequestURL().toString().contains("refreshJWT")) {
            return httpMessageContext.doNothing();
        }

        String authorizationHeader = httpServletRequest.getHeader(MySecurityConstants.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith(MySecurityConstants.BEARER)) {
            return httpMessageContext.responseUnauthorized();
        }

        String jwtToken = authorizationHeader.substring(MySecurityConstants.BEARER.length()).trim();
        if (JWTVerifier.validateJWT(jwtToken)) {
            try {
                SignedJWT signedJWT = SignedJWT.parse(jwtToken);
                String login = signedJWT.getJWTClaimsSet().getSubject();
                String groups = signedJWT.getJWTClaimsSet().getStringClaim(MySecurityConstants.AUTH);

                Date expirationDate = (Date) signedJWT.getJWTClaimsSet().getClaim("exp");
                if (new Date().after(expirationDate)) {
                    return httpMessageContext.responseUnauthorized();
                }

                return httpMessageContext.notifyContainerAboutLogin(
                        login,
                        new HashSet<>(Arrays.asList(groups.split(","))));

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public AuthenticationStatus secureResponse(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) throws AuthenticationException {
        return null;
    }

    @Override
    public void cleanSubject(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) {

    }
}
