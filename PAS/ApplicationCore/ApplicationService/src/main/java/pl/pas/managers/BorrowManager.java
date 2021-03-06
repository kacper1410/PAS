package pl.pas.managers;

import pl.pas.domain.exceptions.NotFoundException;
import pl.pas.domain.exceptions.NotValidException;
import pl.pas.domain.model.Borrow;
import pl.pas.domain.model.resource.Resource;
import pl.pas.domain.model.user.Client;
import pl.pas.domain.model.user.User;
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

    public void borrowResource(long resourceId, long clientId) throws NotFoundException, NotValidException {
        borrowResource(resourceId, clientId, new Date());
    }

    public void borrowResource(long resourceId, String login) throws NotFoundException, NotValidException {
        User user = readUserPort.readUser(login);
        borrowResource(resourceId, user.getUserId(), new Date());
    }

    public void borrowResource(long resourceId, long clientId, Date date) throws NotFoundException, NotValidException {
        Resource resource = readResourcePort.readResource(resourceId);
        User user = readUserPort.readUser(clientId);

        if (resource.isAvailable() && user instanceof Client && user.isActive()) {
            createBorrowPort.createBorrow(new Borrow((Client) user, resource, date));
            resource.setAvailable(false);
            return;
        }
        throw new NotValidException();

    }

    public Borrow getBorrow(long uuid) throws NotFoundException {
        return readBorrowPort.readBorrow(uuid);
    }

    public List<Borrow> getAllBorrows() {
        return readBorrowPort.readAllBorrows();
    }

    public List<Borrow> getAllBorrowsForClient(Client client) throws NotFoundException {
        return getAllBorrowsForClient(client.getUserId());
    }

    public List<Borrow> getAllBorrowsForClient(long uuid) throws NotFoundException {
        Client c = (Client) readUserPort.readUser(uuid);
        return readBorrowPort.readBorrowsByUser(c.getUserId());
    }

    public List<Borrow> getAllBorrowsForResource(Resource resource) throws NotFoundException {
        return getAllBorrowsForResource(resource.getResourceId());
    }

    public List<Borrow> getAllBorrowsForResource(long uuid) throws NotFoundException {
        Resource res = readResourcePort.readResource(uuid);
        return readBorrowPort.readBorrowsByResource(res.getResourceId());
    }

    public void endBorrow(Borrow borrow) throws NotValidException, NotFoundException {
        if (borrow == null
                || borrow.getReturnDate() != null) {
            throw new NotValidException();
        }
        updateBorrowPort.endBorrow(borrow.getBorrowId());
        borrow.getResource().setAvailable(true);
    }
}
