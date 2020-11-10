package pl.pas.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BookTest {
    @Test
    public void bookTest() {
        Book book = new Book(432345345, "Harry Potter", 2000, true);

        assertEquals(book.getISBN(), 432345345);
        assertEquals(book.getTitle(), "Harry Potter");
        assertEquals(book.getPublishYear(), 2000);
        assertTrue(book.isAvailable());

        System.out.println(book);
    }
}
