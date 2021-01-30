package pl.pas.rest.security;

import javax.security.enterprise.identitystore.CredentialValidationResult;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.util.Date;

public class JWTGenerator {

    public static String generateJWT(CredentialValidationResult credential) {
        try {
            JWSSigner signer = new MACSigner(MySecurityConstants.SECRET);

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(credential.getCallerPrincipal().getName())
                    .claim(MySecurityConstants.AUTH, String.join(",", credential.getCallerGroups()))
                    .issuer(MySecurityConstants.ISSUER)
                    .expirationTime(new Date(new Date().getTime() + MySecurityConstants.EXPIRATION_TIME))
                    .build();

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

            signedJWT.sign(signer);

            return signedJWT.serialize();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return "";
    }
}
