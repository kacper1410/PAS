package pl.pas.model;

import org.junit.Test;
import pl.pas.model.resource.AudioBook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AudioBookTest {
    @Test
    public void audioBookTest() {
        AudioBook audioBook = new AudioBook(1, "Harry Potter", 270, true);

        assertEquals(audioBook.getISBN(), 1);
        assertEquals(audioBook.getTitle(), "Harry Potter");
        assertEquals(audioBook.getLength(), 270);
        assertTrue(audioBook.isAvailable());

        System.out.println(audioBook);
    }
}
