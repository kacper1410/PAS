package pl.pas.rest.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;

public class JWTVerifier {

    public static boolean validateJWT(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(MySecurityConstants.SECRET);
            return signedJWT.verify(verifier);
        } catch (JOSEException | ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
