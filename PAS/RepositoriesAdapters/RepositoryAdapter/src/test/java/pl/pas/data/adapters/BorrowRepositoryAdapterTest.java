package pl.pas.data.adapters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.pas.data.model.Borrow;
import pl.pas.data.model.resource.Book;
import pl.pas.data.model.user.Client;
import pl.pas.data.repositories.BorrowRepository;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BorrowRepositoryAdapterTest {

    private BorrowRepositoryAdapter borrowRepositoryAdapter;

    public BorrowRepositoryAdapterTest() {
        borrowRepositoryAdapter = new BorrowRepositoryAdapter();
    }

    @Test
    void createBorrow() {
        Client client = new Client("niezly_login", "nie", "Igrek", "Iksinski", 23);
        Book book = new Book(123, "Droga Królów", "Author", 2010);
        Date date = new Date();

        Borrow borrow = new Borrow(client, book, date);
        borrowRepositoryAdapter.createBorrow(borrow);

        List<Borrow> borrows = borrowRepositoryAdapter.readAllBorrows();
        Assertions.assertEquals(borrow, borrows.get(0));
    }

    @Test
    void deleteBorrow() {
    }

    @Test
    void readAllBorrows() {
    }

    @Test
    void readBorrowsByResource() {
    }

    @Test
    void readBorrowsByUser() {
    }

    @Test
    void readBorrow() {
    }

    @Test
    void endBorrow() {
    }

    @Test
    void updateBorrow() {
    }
}