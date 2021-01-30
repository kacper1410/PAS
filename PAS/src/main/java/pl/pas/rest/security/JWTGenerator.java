package pl.pas.rest.security;

import javax.security.enterprise.identitystore.CredentialValidationResult;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.util.Date;

public class JWTGenerator {

    private static final String SECRET = "_S_ARRxC9B7vQ739YOLM1LLc3HVJ4Z3_BxB7sOxubowKoNo5NiRDXrNAHRR2_ydXp7YD3EMXmSaVnKi74yIvELQ73GaSpUxsmg-hqXkatx-K4d7aimrQebggNejo4WxaMsmfNF7Mh2BIWE_hqAvNAyK-J4b5SnruXg7fcZ8qGus";
    private static final int EXPIRATION_TIME = 10 * 60 * 1000;
    private static final String ISSUER = "PAS11";

    public static String generateJWT(CredentialValidationResult credential) {
        try {
            JWSSigner signer = new MACSigner(SECRET);

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(credential.getCallerPrincipal().getName())
                    .claim("auth", String.join(",", credential.getCallerGroups()))
                    .issuer(ISSUER)
                    .expirationTime(new Date(new Date().getTime() + EXPIRATION_TIME))
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
