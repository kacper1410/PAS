package pl.pas.model;

import org.junit.Test;
import pl.pas.model.resource.AudioBook;

import static org.junit.Assert.*;

public class AudioBookTest {
    @Test
    public void audioBookTest() {
        AudioBook audioBook = new AudioBook(1, "Harry Potter", "Author", 270);

        assertEquals(0, audioBook.getResourceId());
        assertEquals(1, audioBook.getISBN());
        assertEquals("Harry Potter", audioBook.getTitle());
        assertEquals("Author", audioBook.getAuthor());
        assertEquals(270, audioBook.getLength());
        assertTrue(audioBook.isAvailable());

        audioBook.setAvailable(false);
        assertFalse(audioBook.isAvailable());

        System.out.println(audioBook);
    }
}
