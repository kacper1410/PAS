package pl.pas.rest;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import pl.pas.data.model.SignableEntity;

import java.text.ParseException;

public class IdentitySignVerifier {

    private static final String SECRET = "_S_ARRxC9B7vQ739YOLM1LLc3HVJ4Z3_BxB7sOxubowKoNo5NiRDXrNAHRR2_ydXp7YD3EMXmSaVnKi74yIvELQ73GaSpUxsmg-hqXkatx-K4d7aimrQebggNejo4WxaMsmfNF7Mh2BIWE_hqAvNAyK-J4b5SnruXg7fcZ8qGus";

    public static String calculateEntitySignature(SignableEntity entity) {
        try {
            JWSSigner jwsSigner = new MACSigner(SECRET);
            JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload(String.valueOf(entity.getSignablePayload())));
            jwsObject.sign(jwsSigner);

            return jwsObject.serialize();
        } catch (JOSEException e) {
            return "Exception";
        }
    }

    public static boolean isEntitySignatureValid(String ifMatch) {
        try {
            JWSObject jwsObject = JWSObject.parse(ifMatch);
            JWSVerifier jwsVerifier = new MACVerifier(SECRET);

            return jwsObject.verify(jwsVerifier);
        } catch (JOSEException | ParseException e) {
            e.printStackTrace();

            return false;
        }
    }

    public static boolean isEntitySignatureValid(String ifMatch, long id) {
        try {
            String payloadFromIfMatch = JWSObject.parse(ifMatch).getPayload().toString();
            String payloadFromEntity = String.valueOf(id);

            return isEntitySignatureValid(ifMatch) && payloadFromIfMatch.equals(payloadFromEntity);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
