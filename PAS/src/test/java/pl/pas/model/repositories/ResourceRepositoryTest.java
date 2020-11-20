package pl.pas.model.repositories;

import org.junit.Test;
import pl.pas.model.resource.AudioBook;
import pl.pas.model.resource.Book;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ResourceRepositoryTest {
    @Test
    public void repositoryTest() {
        ResourceRepository resourceRepository = new ResourceRepository();
        Book book = new Book(432345345, "Book", 2000);
        Book newBook = new Book(432345345, "New Book", 2005);
        AudioBook audioBook = new AudioBook(432345345, "Audio Book", 200);
        AudioBook newAudioBook = new AudioBook(432345345, "New Audio Book", 205);

        resourceRepository.addResource(book);
        resourceRepository.addResource(audioBook);

        assertEquals(book, resourceRepository.getResource(book.getResourceId()));
        assertEquals(audioBook, resourceRepository.getResource(audioBook.getResourceId()));

        assertEquals(2, resourceRepository.getAllResources().size());

        resourceRepository.updateResource(book, newBook);
        assertNull(resourceRepository.getResource(book.getResourceId()));
        assertEquals(newBook, resourceRepository.getResource(newBook.getResourceId()));

        resourceRepository.updateResource(audioBook, newAudioBook);
        assertNull(resourceRepository.getResource(audioBook.getResourceId()));
        assertEquals(newAudioBook, resourceRepository.getResource(newAudioBook.getResourceId()));

        resourceRepository.deleteResource(newBook.getResourceId());
        assertNull(resourceRepository.getResource(newBook.getResourceId()));

        resourceRepository.deleteResource(newAudioBook.getResourceId());
        assertNull(resourceRepository.getResource(newAudioBook.getResourceId()));

        assertEquals(0, resourceRepository.getAllResources().size());
    }
}
