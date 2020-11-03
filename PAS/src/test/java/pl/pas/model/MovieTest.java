package pl.pas.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class MovieTest {

    @Test
    public void movieTest() {
        Date date = new Date();
        Movie movie = new Movie("Ron Granger", 90, date, 3);

        assertEquals(movie.getTitle(), "Ron Granger");
        assertEquals(movie.getLength(), 90);
        assertEquals(movie.getDate(), date);

        assertFalse(movie.takeSeat(0));
        assertTrue(movie.takeSeat(1));
        assertTrue(movie.takeSeat(2));
        assertTrue(movie.takeSeat(3));
        assertFalse(movie.takeSeat(4));

        assertFalse(movie.takeSeat(-1));
        assertFalse(movie.takeSeat(0));
        assertFalse(movie.takeSeat(1));
        assertFalse(movie.takeSeat(2));
        assertFalse(movie.takeSeat(3));
    }
}
