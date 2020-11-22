package pl.pas.model.managers;

import org.junit.Test;
import pl.pas.model.FillRepositories;
import pl.pas.model.entities.resource.Resource;
import pl.pas.model.repositories.BorrowRepository;
import pl.pas.model.repositories.ResourceRepository;
import pl.pas.model.repositories.UserRepository;

import static org.junit.Assert.*;

public class ResourceManagerTest {
    private ResourceManager resourceManager;
    private static int SIZE;

    public ResourceManagerTest() {
        ResourceRepository resourceRepository = new ResourceRepository();
        UserRepository userRepository = new UserRepository();
        BorrowRepository borrowRepository = new BorrowRepository();
        FillRepositories fillRepositories = new FillRepositories(resourceRepository, userRepository, borrowRepository);
        fillRepositories.fill();
        resourceManager = new ResourceManager(resourceRepository, borrowRepository, userRepository);
        SIZE = resourceRepository.getAllResources().size();
    }

    @Test
    public void testGetResource() {
        Resource resource = resourceManager.getAllBooks().get(0);
        assertEquals(resource, resourceManager.getResource(resource.getResourceId()));
    }

    @Test
    public void testGetAllResources() {
        assertEquals(SIZE, resourceManager.getAllResources().size());
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
        assertEquals(SIZE, resourceManager.getAllResources().size());

        Resource availableResource = resourceManager.getAllBooks().get(4);

        assertTrue(availableResource.isAvailable());
        assertTrue(resourceManager.removeResource(availableResource.getResourceId()));
        assertEquals(SIZE - 1, resourceManager.getAllResources().size());
    }

    @Test
    public void testAddBook() {
        assertTrue(resourceManager.addBook(432345345, "Harry Potter", "Author", 2000));
        assertEquals(SIZE + 1, resourceManager.getAllResources().size());
    }

    @Test
    public void testAddAudioBook() {
        assertTrue(resourceManager.addAudioBook(432345345, "Harry Potter", "Author", 90));
        assertEquals(SIZE + 1, resourceManager.getAllResources().size());
    }

    @Test
    public void testUpdateBook() {
        Resource book = resourceManager.getAllResources().get(7);
        Resource audioBook = resourceManager.getAllResources().get(6);

        assertFalse(resourceManager.updateBook(audioBook, 123, "New", "New", 111));
        assertTrue(resourceManager.updateBook(book, 123, "New", "New", 111));

        assertNotEquals(book, resourceManager.getResource(book.getResourceId()));
    }

    @Test
    public void testUpdateAudioBook() {
        Resource book = resourceManager.getAllResources().get(7);
        Resource audioBook = resourceManager.getAllResources().get(6);

        assertFalse(resourceManager.updateAudioBook(book, 123, "New", "New", 111));
        assertTrue(resourceManager.updateAudioBook(audioBook, 123, "New", "New", 111));

        assertNotEquals(audioBook, resourceManager.getResource(audioBook.getResourceId()));
    }
}
