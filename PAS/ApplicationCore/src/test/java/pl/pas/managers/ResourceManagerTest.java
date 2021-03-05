package pl.pas.managers;

import org.junit.Test;
import org.opentest4j.TestAbortedException;
import pl.pas.FillRepositories;
import pl.pas.exceptions.NotFoundException;
import pl.pas.exceptions.NotValidException;
import pl.pas.model.resource.Resource;
import pl.pas.repositories.BorrowRepository;
import pl.pas.repositories.ResourceRepository;
import pl.pas.repositories.UserRepository;

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
        try {
            assertEquals(resource, resourceManager.getResource(resource.getResourceId()));
        } catch (NotFoundException e) {
            throw new TestAbortedException();
        }
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
//        Resource unavailableResource = resourceManager.getAllBooks().get(0);
//
//        assertFalse(unavailableResource.isAvailable());
//
//        assertThrows(NotValidException.class,() -> resourceManager.removeResource(unavailableResource.getResourceId()));
//
//        assertEquals(SIZE, resourceManager.getAllResources().size());
//
//        Resource availableResource = resourceManager.getAllBooks().get(4);
//
//        assertTrue(availableResource.isAvailable());
//
//        try {
//            resourceManager.removeResource(availableResource.getResourceId());
//        } catch (NotValidException e) {
//            throw new TestAbortedException();
//        }
//
//        assertEquals(SIZE - 1, resourceManager.getAllResources().size());
    }

    @Test
    public void testAddBook() {
        try {
            resourceManager.addBook(432345345, "Harry Potter", "Author", 2000);
        } catch (NotValidException e) {
            throw new TestAbortedException();
        }
        assertEquals(SIZE + 1, resourceManager.getAllResources().size());
    }

    @Test
    public void testAddAudioBook() {
        try {
            resourceManager.addAudioBook(432345345, "Harry Potter", "Author", 90);
        } catch (NotValidException e) {
            throw new TestAbortedException();
        }
        assertEquals(SIZE + 1, resourceManager.getAllResources().size());
    }

    @Test
    public void testUpdateBook() {
        Resource book = resourceManager.getAllResources().get(7);
        Resource audioBook = resourceManager.getAllResources().get(6);

        try {
            resourceManager.updateBook(book, 123, "New", "New", 111);
        } catch (NotValidException e) {
            throw new TestAbortedException();
        }
        assertThrows(NotValidException.class,() -> resourceManager.updateBook(audioBook, 123, "New", "New", 111));


        try {
            assertNotEquals(book, resourceManager.getResource(book.getResourceId()));
        } catch (NotFoundException e) {
            throw new TestAbortedException();
        }
    }

    @Test
    public void testUpdateAudioBook() {
        Resource book = resourceManager.getAllResources().get(7);
        Resource audioBook = resourceManager.getAllResources().get(6);

        try {
            resourceManager.updateAudioBook(audioBook, 123, "New", "New", 111);
        } catch (NotValidException e) {
            throw new TestAbortedException();
        }
        assertThrows(NotValidException.class,() -> resourceManager.updateAudioBook(book, 123, "New", "New", 111));

        try {
            assertNotEquals(audioBook, resourceManager.getResource(audioBook.getResourceId()));
        } catch (NotFoundException e) {
            throw new TestAbortedException();
        }
    }
}
