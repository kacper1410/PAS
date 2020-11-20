package pl.pas.model;

import org.junit.Test;
import pl.pas.model.resource.Book;
import pl.pas.model.user.Client;

import java.util.Date;

import static org.junit.Assert.*;

public class BorrowDate {
    @Test
    public void borrowTest() {
        Client client = new Client("niezly_login", "Igrek", "Iksinski", 23);
        Book book = new Book(123, "Droga Królów", 2010);
        Borrow borrow = new Borrow(client, book);

        assertTrue(borrow.getBorrowId().toString().matches("[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}"));
        assertEquals(borrow.getClient(), client);
        assertEquals(borrow.getResource(), book);
        assertNull(borrow.getReturnDate());

        borrow.endBorrow();
        assertNotNull(borrow.getReturnDate());
    }

    @Test
    public void borrowDateTest() {
        Client client = new Client("niezly_login", "Igrek", "Iksinski", 23);
        Book book = new Book(123, "Droga Królów", 2010);
        Date date = new Date();
        Borrow borrow = new Borrow(client, book, date);

        assertEquals(borrow.getBorrowDate(), date);

        Date returnDate = new Date();
        borrow.setReturnDate(returnDate);
        assertEquals(borrow.getReturnDate(), returnDate);
    }
}
