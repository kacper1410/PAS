package pl.pas.managers;

import pl.pas.exceptions.NotFoundException;
import pl.pas.exceptions.NotValidException;
import pl.pas.model.Borrow;
import pl.pas.model.resource.Resource;
import pl.pas.model.user.Client;
import pl.pas.model.user.User;
import pl.pas.ports.infrastructure.borrow.CreateBorrowPort;
import pl.pas.ports.infrastructure.borrow.ReadBorrowPort;
import pl.pas.ports.infrastructure.borrow.UpdateBorrowPort;
import pl.pas.ports.infrastructure.resource.ReadResourcePort;
import pl.pas.ports.infrastructure.user.ReadUserPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@ApplicationScoped
public class BorrowManager implements Serializable {
    @Inject
    private IResourceRepository resourceRepository;
    @Inject
    private IBorrowRepository borrowRepository;
    @Inject
    private IUserRepository userRepository;

    @Inject
    private ReadBorrowPort readBorrowPort;
    @Inject
    private UpdateBorrowPort updateBorrowPort;
    @Inject
    private CreateBorrowPort createBorrowPort;
    @Inject
    private ReadUserPort readUserPort;
    @Inject
    private ReadResourcePort readResourcePort;

    public BorrowManager() {
    }

    public BorrowManager(IResourceRepository resourceRepository, IBorrowRepository borrowRepository, IUserRepository userRepository) {
        this.resourceRepository = resourceRepository;
        this.borrowRepository = borrowRepository;
        this.userRepository = userRepository;
    }

    public void borrowResource(long resourceId, long clientId) throws NotFoundException, NotValidException {
        borrowResource(resourceId, clientId, new Date());
    }

    public void borrowResource(long resourceId, String login) throws NotFoundException, NotValidException {
        User user = userRepository.getUser(login);
        borrowResource(resourceId, user.getUserId(), new Date());
    }

    public void borrowResource(long resourceId, long clientId, Date date) throws NotFoundException, NotValidException {
        Resource resource = resourceRepository.getResource(resourceId);
        User user = userRepository.getUser(clientId);

        if (resource.isAvailable() && user instanceof Client && user.isActive()) {
            borrowRepository.addBorrow(new Borrow((Client) user, resource, date));
            resource.setAvailable(false);
            return;
        }
        throw new NotValidException();

    }

    public Borrow getBorrow(long uuid) throws NotFoundException {
        return borrowRepository.getBorrow(uuid);
    }

    public List<Borrow> getAllBorrows() {
        return borrowRepository.getAllBorrows();
    }

    public List<Borrow> getAllBorrowsForClient(Client client) throws NotFoundException {
        return getAllBorrowsForClient(client.getUserId());
    }

    public List<Borrow> getAllBorrowsForClient(long uuid) throws NotFoundException {
        Client c = (Client) userRepository.getUser(uuid);
        return borrowRepository.getBorrowsByUser(c.getUserId());
    }

    public List<Borrow> getAllBorrowsForResource(Resource resource) throws NotFoundException {
        return getAllBorrowsForResource(resource.getResourceId());
    }

    public List<Borrow> getAllBorrowsForResource(long uuid) throws NotFoundException {
        Resource res = resourceRepository.getResource(uuid);
        return borrowRepository.getBorrowsByResource(res.getResourceId());
    }

    public void endBorrow(Borrow borrow) throws NotValidException, NotFoundException {
        if (borrow == null
                || borrow.getReturnDate() != null) {
            throw new NotValidException();
        }
        borrowRepository.endBorrow(borrow.getBorrowId());
        borrow.getResource().setAvailable(true);
    }
}
