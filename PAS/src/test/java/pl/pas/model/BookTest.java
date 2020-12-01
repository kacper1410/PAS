package pl.pas.model;

import org.junit.Test;
import pl.pas.model.resource.Book;
import static org.junit.Assert.*;

public class BookTest {
    @Test
    public void bookTest() {
        Book book = new Book(432345345, "Harry Potter", "Author", 2000);

        assertEquals(0, book.getResourceId());
        assertEquals(432345345, book.getISBN());
        assertEquals("Harry Potter", book.getTitle());
        assertEquals("Author", book.getAuthor());
        assertEquals(2000, book.getPublishYear());
        assertTrue(book.isAvailable());

        book.setAvailable(false);
        assertFalse(book.isAvailable());

        System.out.println(book);
    }
}
