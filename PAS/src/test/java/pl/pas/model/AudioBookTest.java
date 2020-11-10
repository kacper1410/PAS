package pl.pas.model;

import org.junit.Test;
import pl.pas.model.resource.AudioBook;

import static org.junit.Assert.*;

public class AudioBookTest {
    @Test
    public void audioBookTest() {
        AudioBook audioBook = new AudioBook(1, "Harry Potter", 270);

        assertTrue(audioBook.getResourceId().toString().matches("[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}"));
        assertEquals(audioBook.getISBN(), 1);
        assertEquals(audioBook.getTitle(), "Harry Potter");
        assertEquals(audioBook.getLength(), 270);
        assertTrue(audioBook.isAvailable());

        audioBook.setAvailable(false);
        assertFalse(audioBook.isAvailable());

        System.out.println(audioBook);
    }
}
