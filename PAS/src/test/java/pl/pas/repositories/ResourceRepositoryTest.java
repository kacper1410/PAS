package pl.pas.repositories;

import org.junit.Test;
import pl.pas.model.resource.AudioBook;
import pl.pas.model.resource.Book;

import static org.junit.Assert.*;

public class ResourceRepositoryTest {
    @Test
    public void repositoryTest() {
        ResourceRepository resourceRepository = new ResourceRepository();
        Book book = new Book(432345345, "Book", "Author", 2000);
        Book newBook = new Book(3232, "New Book", "Author", 2005);
        AudioBook audioBook = new AudioBook(432345345, "Audio Book", "Author", 200);
        AudioBook newAudioBook = new AudioBook(32323, "New Audio Book", "Author", 205);

        resourceRepository.addResource(book);
        resourceRepository.addResource(audioBook);

        assertEquals(book, resourceRepository.getResource(book.getResourceId()));
        assertEquals(audioBook, resourceRepository.getResource(audioBook.getResourceId()));

        assertEquals(2, resourceRepository.getAllResources().size());

        resourceRepository.updateResource(book.getResourceId(), newBook);
        assertNotEquals(book, resourceRepository.getResource(book.getResourceId()));
        assertEquals(newBook, resourceRepository.getResource(newBook.getResourceId()));

        resourceRepository.updateResource(audioBook.getResourceId(), newAudioBook);
        assertNotEquals(audioBook, resourceRepository.getResource(audioBook.getResourceId()));
        assertEquals(newAudioBook, resourceRepository.getResource(newAudioBook.getResourceId()));

        resourceRepository.deleteResource(newBook.getResourceId());
        assertNull(resourceRepository.getResource(newBook.getResourceId()));

        resourceRepository.deleteResource(newAudioBook.getResourceId());
        assertNull(resourceRepository.getResource(newAudioBook.getResourceId()));

        assertEquals(0, resourceRepository.getAllResources().size());
    }
}
