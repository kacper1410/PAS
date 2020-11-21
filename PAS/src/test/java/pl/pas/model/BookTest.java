package pl.pas.model;

import org.junit.Test;
import pl.pas.model.entities.resource.Book;
import static org.junit.Assert.*;

public class BookTest {
    @Test
    public void bookTest() {
        Book book = new Book(432345345, "Harry Potter", 2000);

        assertTrue(book.getResourceId().toString().matches("[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}"));
        assertEquals(432345345, book.getISBN());
        assertEquals("Harry Potter", book.getTitle());
        assertEquals(2000, book.getPublishYear());
        assertTrue(book.isAvailable());

        book.setAvailable(false);
        assertFalse(book.isAvailable());

        System.out.println(book);
    }
}
