package pl.pas.managers;

import org.junit.Test;
import pl.pas.FillRepositories;
import pl.pas.exceptions.NotFoundException;
import pl.pas.exceptions.NotValidException;
import pl.pas.model.resource.Resource;
import pl.pas.model.user.Client;
import pl.pas.repositories.BorrowRepository;
import pl.pas.repositories.ResourceRepository;
import pl.pas.repositories.UserRepository;

import static org.junit.Assert.assertEquals;

public class BorrowManagerTest {
    private BorrowManager borrowManager;
    private UserRepository userRepository;
    private ResourceRepository resourceRepository;
    private static int SIZE;

    public BorrowManagerTest() {
        resourceRepository = new ResourceRepository();
        userRepository = new UserRepository();
        BorrowRepository borrowRepository = new BorrowRepository();
        FillRepositories fillRepositories = new FillRepositories(resourceRepository, userRepository, borrowRepository);
        fillRepositories.fill();
        borrowManager = new BorrowManager(resourceRepository, borrowRepository, userRepository);
        SIZE = borrowRepository.getAllBorrows().size();
    }

    @Test
    public void testAddBorrow() {
        Resource resource = resourceRepository.getAllBooks().get(4);
        Client client = userRepository.getAllClients().get(0);
        assertEquals(SIZE, borrowManager.getAllBorrows().size());
        try {
            borrowManager.borrowResource(resource.getResourceId(), client.getUserId());
        } catch (NotFoundException | NotValidException ignored) {

        }
        assertEquals(SIZE + 1, borrowManager.getAllBorrows().size());
    }
}
