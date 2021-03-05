package pl.pas.model;

import org.junit.Test;
import pl.pas.model.Borrow;
import pl.pas.model.resource.Book;
import pl.pas.model.user.Client;

import java.util.Date;

import static org.junit.Assert.*;

public class BorrowTest {
    @Test
    public void borrowTest() {
        Client client = new Client("niezly_login", "nie", "Igrek", "Iksinski", 23);
        Book book = new Book(123, "Droga Królów", "Author", 2010);
        Borrow borrow = new Borrow(client, book);

        assertEquals(client, borrow.getClient());
        assertEquals(book, borrow.getResource());
        assertNull(borrow.getReturnDate());

        borrow.endBorrow();
        assertNotNull(borrow.getReturnDate());
    }

    @Test
    public void borrowDateTest() {
        Client client = new Client("niezly_login", "tak", "Igrek", "Iksinski", 23);
        Book book = new Book(123, "Droga Królów", "Author", 2010);
        Date date = new Date();
        Borrow borrow = new Borrow(client, book, date);

        assertEquals(borrow.getBorrowDate(), date);

        Date returnDate = new Date();
        borrow.setReturnDate(returnDate);
        assertEquals(borrow.getReturnDate(), returnDate);
    }
}
