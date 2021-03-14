package pl.pas.data.adapters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.pas.data.model.Borrow;
import pl.pas.data.model.resource.Book;
import pl.pas.data.model.user.Client;
import pl.pas.data.repositories.BorrowRepository;

import java.util.Date;
import java.util.List;

class BorrowRepositoryAdapterTest {

    private final BorrowRepositoryAdapter borrowRepositoryAdapter;

    public BorrowRepositoryAdapterTest() {
        BorrowRepository borrowRepository = new BorrowRepository();
        borrowRepositoryAdapter = new BorrowRepositoryAdapter(borrowRepository);
    }

    @Test
    void createBorrow() {
        Client client = new Client("niezly_login", "nie", "Igrek", "Iksinski", 23);
        Book book = new Book(123, "Droga Królów", "Author", 2010);
        Date date = new Date();

        Borrow borrow = new Borrow(client, book, date);
        borrowRepositoryAdapter.createBorrow(borrow);

        List<Borrow> borrows = borrowRepositoryAdapter.readAllBorrows();
        borrow.setBorrowId(borrows.get(0).getBorrowId());
        Assertions.assertEquals(borrow, borrows.get(0));
    }

    @Test
    void deleteBorrow() {
    }

    @Test
    void readAllBorrows() {
        Client client = new Client("niezly_login", "nie", "Igrek", "Iksinski", 23);
        Client client1 = new Client("niezly_login1", "nie1", "Igrek1", "Iksinski1", 24);

        Book book = new Book(123, "Droga Królów", "Author", 2010);
        Book book1 = new Book(124, "Droga Królów1", "Author1", 2011);

        Date date = new Date();

        Borrow borrow = new Borrow(client, book, date);
        Borrow borrow1 = new Borrow(client1, book1, date);

        borrowRepositoryAdapter.createBorrow(borrow);
        borrowRepositoryAdapter.createBorrow(borrow1);

        List<Borrow> borrows = borrowRepositoryAdapter.readAllBorrows();

        borrow.setBorrowId(borrows.get(0).getBorrowId());
        borrow1.setBorrowId(borrows.get(1).getBorrowId());

        Assertions.assertEquals(borrow, borrows.get(0));
        Assertions.assertEquals(borrow1, borrows.get(1));
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
