package pl.pas.model.repositories;

import org.junit.Test;
import pl.pas.model.resource.AudioBook;
import pl.pas.model.resource.Book;

public class ResourceRepositoryTest {
    @Test
    public void repositoryTest() {
        ResourceRepository resourceRepository = new ResourceRepository();
        Book book = new Book(432345345, "Book", 2000);
        Book newBook = new Book(432345345, "New Book", 2005);
        AudioBook audioBook = new AudioBook(432345345, "Audio Book", 200);
        AudioBook newAudioBook = new AudioBook(432345345, "New Audio Book", 205);
    }
}
