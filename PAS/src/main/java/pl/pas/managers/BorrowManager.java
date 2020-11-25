package pl.pas.managers;

import pl.pas.model.Borrow;
import pl.pas.model.resource.Resource;
import pl.pas.model.user.Client;
import pl.pas.model.user.User;
import pl.pas.repositories.interfaces.IBorrowRepository;
import pl.pas.repositories.interfaces.IResourceRepository;
import pl.pas.repositories.interfaces.IUserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Named
@ApplicationScoped
public class BorrowManager implements Serializable {
    @Inject
    private IResourceRepository resourceRepository;
    @Inject
    private IBorrowRepository borrowRepository;
    @Inject
    private IUserRepository userRepository;

    public BorrowManager() {
    }

    public BorrowManager(IResourceRepository resourceRepository, IBorrowRepository borrowRepository, IUserRepository userRepository) {
        this.resourceRepository = resourceRepository;
        this.borrowRepository = borrowRepository;
        this.userRepository = userRepository;
    }

    public boolean borrowResource(UUID resourceId, UUID clientId) {
        return borrowResource(resourceId, clientId, new Date());
    }

    public boolean borrowResource(UUID resourceId, UUID clientId, Date date) {
        Resource resource = resourceRepository.getResource(resourceId);
        User user = userRepository.getUser(clientId);
        if (resource == null || user == null) {
            return false;
        }

        if (resource.isAvailable() && user instanceof Client && user.isActive()) {
            borrowRepository.addBorrow(new Borrow((Client) user, resource, date), UUID.randomUUID());
            resource.setAvailable(false);
            return true;
        }

        return false;
    }

    public Borrow getBorrow(UUID uuid) {
        if (uuid != null) {
            return borrowRepository.getBorrow(uuid);
        }
        return null;
    }

    public List<Borrow> getAllBorrows() {
        return borrowRepository.getAllBorrows();
    }

    public List<Borrow> getAllBorrowsForClient(Client client) {
        return getAllBorrowsForClient(client.getUserId());
    }

    public List<Borrow> getAllBorrowsForClient(UUID uuid) {
        return borrowRepository.getBorrowsByUser(uuid);
    }

    public List<Borrow> getAllBorrowsForResource(Resource resource) {
        return getAllBorrowsForResource(resource.getResourceId());
    }

    public List<Borrow> getAllBorrowsForResource(UUID uuid) {
        return borrowRepository.getBorrowsByResource(uuid);
    }

    public boolean endBorrow(Borrow borrow) {
        if (borrow == null
                || borrow.getReturnDate() != null
                || borrowRepository.getBorrow(borrow.getBorrowId()) == null) {
            return false;
        }
        borrow.getResource().setAvailable(true);
        borrowRepository.endBorrow(borrow.getBorrowId());
        return true;
    }

    public boolean cancelBorrow(Borrow borrow) {
        if (borrow == null
                || borrow.getReturnDate() != null
                || borrowRepository.getBorrow(borrow.getBorrowId()) == null) {
            return false;
        }

        return borrowRepository.deleteBorrow(borrow.getBorrowId());
    }

}