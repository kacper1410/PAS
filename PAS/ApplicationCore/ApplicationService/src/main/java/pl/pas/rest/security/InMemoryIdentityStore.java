package pl.pas.rest.security;

import pl.pas.data.exceptions.NotFoundException;
import pl.pas.data.model.user.User;
import pl.pas.ports.infrastructure.user.ReadUserPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class InMemoryIdentityStore implements IdentityStore {

    @Inject
    private ReadUserPort readUserPort;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        if (credential instanceof UsernamePasswordCredential) {
            UsernamePasswordCredential usernamePasswordCredential = (UsernamePasswordCredential) credential;
            User user = null;
            try {
                user = readUserPort.readUserByLoginPasswordActive(usernamePasswordCredential.getCaller(),
                        usernamePasswordCredential.getPasswordAsString());
            } catch (NotFoundException ignored) {

            }
            if (user != null) {
                return new CredentialValidationResult(user.getLogin(), new HashSet<>(Arrays.asList(user.getAccessGroup())));
            }
            return CredentialValidationResult.INVALID_RESULT;
        }
        return CredentialValidationResult.NOT_VALIDATED_RESULT;
    }

    @Override
    public Set<String> getCallerGroups(CredentialValidationResult validationResult) {
        return IdentityStore.super.getCallerGroups(validationResult);
    }
}
