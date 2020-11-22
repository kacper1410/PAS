package pl.pas.model.managers;

import org.junit.Test;
import pl.pas.model.FillRepositories;
import pl.pas.model.entities.resource.Resource;
import pl.pas.model.repositories.BorrowRepository;
import pl.pas.model.repositories.ResourceRepository;
import pl.pas.model.repositories.UserRepository;

import static org.junit.Assert.*;

public class ResourceManagerTest {
    ResourceManager resourceManager;

    public ResourceManagerTest() {
        ResourceRepository resourceRepository = new ResourceRepository();
        UserRepository userRepository = new UserRepository();
        BorrowRepository borrowRepository = new BorrowRepository();
        FillRepositories fillRepositories = new FillRepositories(resourceRepository, userRepository, borrowRepository);
        fillRepositories.fill();
        resourceManager = new ResourceManager(resourceRepository, borrowRepository, userRepository);
    }

    @Test
    public void testGetResource() {
        Resource resource = resourceManager.getAllBooks().get(0);
        assertEquals(resource, resourceManager.getResource(resource.getResourceId()));
    }

    @Test
    public void testGetAllResources() {
        assertEquals(8, resourceManager.getAllResources().size());
    }

    @Test
    public void testGetAllBooks() {
        assertEquals(5, resourceManager.getAllBooks().size());
    }

    @Test
    public void testGetAllAudioBooks() {
        assertEquals(3, resourceManager.getAllAudioBooks().size());
    }

    @Test
    public void testRemoveResource() {
        Resource unavailableResource = resourceManager.getAllBooks().get(0);

        assertFalse(unavailableResource.isAvailable());
        assertFalse(resourceManager.removeResource(unavailableResource.getResourceId()));
        assertEquals(8, resourceManager.getAllResources().size());

        Resource availableResource = resourceManager.getAllBooks().get(4);

        assertTrue(availableResource.isAvailable());
        assertTrue(resourceManager.removeResource(availableResource.getResourceId()));
        assertEquals(7, resourceManager.getAllResources().size());
    }

    @Test
    public void testAddBook() {
        assertTrue(resourceManager.addBook(432345345, "Harry Potter", "Author", 2000));
        assertEquals(9, resourceManager.getAllResources().size());
    }

    @Test
    public void testAddAudioBook() {
        assertTrue(resourceManager.addAudioBook(432345345, "Harry Potter", "Author", 90));
        assertEquals(9, resourceManager.getAllResources().size());
    }
}
