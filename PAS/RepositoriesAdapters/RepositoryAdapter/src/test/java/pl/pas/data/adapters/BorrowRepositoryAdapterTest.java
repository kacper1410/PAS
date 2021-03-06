package pl.pas.data.adapters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.pas.domain.model.Borrow;
import pl.pas.domain.model.resource.Book;
import pl.pas.domain.model.user.Client;
import pl.pas.data.repositories.BorrowRepository;
import pl.pas.domain.exceptions.NotFoundException;

import java.util.Date;
import java.util.List;

class BorrowRepositoryAdapterTest {

    private final BorrowRepositoryAdapter borrowRepositoryAdapter;

    private final Client client;
    private final Client client1;

    private final Book book;
    private final Book book1;

    private final Borrow borrow;
    private final Borrow borrow1;

    private final Date date;

    public BorrowRepositoryAdapterTest() {
        BorrowRepository borrowRepository = new BorrowRepository();
        borrowRepositoryAdapter = new BorrowRepositoryAdapter(borrowRepository);

        client = new Client("niezly_login", "nie", "Igrek", "Iksinski", 23);
        client1 = new Client("niezly_login1", "nie1", "Igrek1", "Iksinski1", 24);

        book = new Book(123, "Droga Królów", "Author", 2010);
        book1 = new Book(124, "Droga Królów1", "Author1", 2011);

        date = new Date();

        borrow = new Borrow(client, book, date);
        borrow1 = new Borrow(client1, book1, date);

    }

    @Test
    void createBorrow() {
        borrowRepositoryAdapter.createBorrow(borrow);

        List<Borrow> borrows = borrowRepositoryAdapter.readAllBorrows();

        borrow.setBorrowId(borrows.get(0).getBorrowId());

        Assertions.assertEquals(1, borrows.size());
        Assertions.assertEquals(borrow, borrows.get(0));
    }

    @Test
    void deleteBorrow() {
        borrowRepositoryAdapter.createBorrow(borrow);

        List<Borrow> borrows = borrowRepositoryAdapter.readAllBorrows();

        borrow.setBorrowId(borrows.get(0).getBorrowId());

        Assertions.assertEquals(1, borrows.size());
        Assertions.assertEquals(borrow, borrows.get(0));

        borrowRepositoryAdapter.deleteBorrow(borrow.getBorrowId());

        borrows = borrowRepositoryAdapter.readAllBorrows();

        Assertions.assertEquals(0, borrows.size());
    }

    @Test
    void readAllBorrows() {
        borrowRepositoryAdapter.createBorrow(borrow);
        borrowRepositoryAdapter.createBorrow(borrow1);

        List<Borrow> borrows = borrowRepositoryAdapter.readAllBorrows();

        borrow.setBorrowId(borrows.get(0).getBorrowId());
        borrow1.setBorrowId(borrows.get(1).getBorrowId());

        Assertions.assertEquals(2, borrows.size());
        Assertions.assertEquals(borrow, borrows.get(0));
        Assertions.assertEquals(borrow1, borrows.get(1));
    }

    @Test
    void readBorrowsByResource() {
        borrowRepositoryAdapter.createBorrow(borrow);

        List<Borrow> borrows = borrowRepositoryAdapter.readAllBorrows();

        borrow.setBorrowId(borrows.get(0).getBorrowId());

        List<Borrow> readBorrow = borrowRepositoryAdapter.readBorrowsByResource(book.getResourceId());

        Assertions.assertEquals(1, readBorrow.size());
        Assertions.assertEquals(borrow, readBorrow.get(0));
    }

    @Test
    void readBorrowsByUser() {
        borrowRepositoryAdapter.createBorrow(borrow);

        List<Borrow> borrows = borrowRepositoryAdapter.readAllBorrows();

        borrow.setBorrowId(borrows.get(0).getBorrowId());

        List<Borrow> readBorrow = borrowRepositoryAdapter.readBorrowsByResource(client.getUserId());

        Assertions.assertEquals(1, readBorrow.size());
        Assertions.assertEquals(borrow, readBorrow.get(0));
    }

    @Test
    void readBorrow() throws NotFoundException {
        borrowRepositoryAdapter.createBorrow(borrow);
        borrowRepositoryAdapter.createBorrow(borrow1);

        List<Borrow> borrows = borrowRepositoryAdapter.readAllBorrows();

        borrow.setBorrowId(borrows.get(0).getBorrowId());
        borrow1.setBorrowId(borrows.get(1).getBorrowId());

        Assertions.assertEquals(2, borrows.size());
        Assertions.assertEquals(borrow, borrows.get(0));
        Assertions.assertEquals(borrow1, borrows.get(1));

        Borrow readBorrow = borrowRepositoryAdapter.readBorrow(borrow.getBorrowId());

        Assertions.assertEquals(borrow, readBorrow);

        readBorrow = borrowRepositoryAdapter.readBorrow(borrow1.getBorrowId());

        Assertions.assertEquals(borrow1, readBorrow);
    }

    @Test
    void endBorrow() throws NotFoundException {
        borrowRepositoryAdapter.createBorrow(borrow);

        List<Borrow> borrows = borrowRepositoryAdapter.readAllBorrows();

        borrow.setBorrowId(borrows.get(0).getBorrowId());

        Assertions.assertEquals(1, borrows.size());
        Assertions.assertEquals(borrow, borrows.get(0));
        Assertions.assertNull(borrows.get(0).getReturnDate());

        borrowRepositoryAdapter.endBorrow(borrow.getBorrowId());

        borrows = borrowRepositoryAdapter.readAllBorrows();

        Assertions.assertNotNull(borrows.get(0).getReturnDate());
    }

    @Test
    void updateBorrow() {
        borrowRepositoryAdapter.createBorrow(borrow);

        List<Borrow> borrows = borrowRepositoryAdapter.readAllBorrows();

        borrow.setBorrowId(borrows.get(0).getBorrowId());

        Assertions.assertEquals(1, borrows.size());
        Assertions.assertEquals(borrow, borrows.get(0));

        borrowRepositoryAdapter.updateBorrow(borrow.getBorrowId(), borrow1);

        borrows = borrowRepositoryAdapter.readAllBorrows();

        borrow1.setBorrowId(borrows.get(0).getBorrowId());

        Assertions.assertEquals(1, borrows.size());
        Assertions.assertEquals(borrow1, borrows.get(0));
    }
}
