package pl.pas.model.managers;

import pl.pas.model.entities.Borrow;
import pl.pas.model.entities.resource.Resource;
import pl.pas.model.entities.user.Client;
import pl.pas.model.entities.user.User;
import pl.pas.model.repositories.interfaces.IBorrowRepository;
import pl.pas.model.repositories.interfaces.IResourceRepository;
import pl.pas.model.repositories.interfaces.IUserRepository;

import java.util.UUID;

public class BorrowManager {
    IResourceRepository resourceRepository;
    IBorrowRepository borrowRepository;
    IUserRepository userRepository;

    public BorrowManager(IResourceRepository resourceRepository, IBorrowRepository borrowRepository, IUserRepository userRepository) {
        this.resourceRepository = resourceRepository;
        this.borrowRepository = borrowRepository;
        this.userRepository = userRepository;
    }

    public boolean borrowResource(UUID resourceId, UUID clientId) {
        Resource resource = resourceRepository.getResource(resourceId);
        User user = userRepository.getUser(clientId);

        if (resource.isAvailable() && user instanceof Client && user.isActive()) {
            borrowRepository.addBorrow(new Borrow((Client) user, resource), UUID.randomUUID());
            resource.setAvailable(false);
            return true;
        }

        return false;
    }
}
