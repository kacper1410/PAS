package pl.pas.model.repositories;

import org.junit.Test;
import pl.pas.model.entities.Borrow;
import pl.pas.model.entities.resource.AudioBook;
import pl.pas.model.entities.resource.Book;
import pl.pas.model.entities.user.Client;


import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

public class BorrowRepositoryTest {

    BorrowRepository borrowRepository = new BorrowRepository();

    public BorrowRepositoryTest() {
        Book book = new Book(432345345, "Book", 2000);
        AudioBook audioBook = new AudioBook(432345345, "Audio Book", 200);
        Client client = new Client("cLogin", "Kacper", "Swiercz", 21);
        Borrow borrow = new Borrow(client, book, new Date());

    }

    @Test
    public void addBorrow() {

    }

    @Test
    public void getBorrow() {
    }

    @Test
    public void getBorrowsByUser() {
    }

    @Test
    public void getBorrowsByResource() {
    }

    @Test
    public void getAllBorrows() {
    }

    @Test
    public void updateBorrow() {
    }

    @Test
    public void endBorrow() {
        Book book = new Book(432345345, "Book", 2000);
        Client client = new Client("cLogin", "Kacper", "Swiercz", 21);
        Borrow borrow = new Borrow(client, book, new Date());
        borrowRepository.addBorrow(borrow, UUID.randomUUID());

        try {
            Thread.sleep(6000);
        } catch (InterruptedException ignored) {}

        Date returnDate = new Date();
        borrowRepository.endBorrow(borrow.getBorrowId());

        assertEquals(borrowRepository.getBorrow(borrow.getBorrowId()).getReturnDate(), returnDate);

    }
}