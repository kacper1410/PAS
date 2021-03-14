package pl.pas.data.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.TestAbortedException;
import pl.pas.data.model.BorrowEntity;
import pl.pas.data.model.exceptions.NotFoundExceptionEntity;
import pl.pas.data.model.resource.AudioBookEntity;
import pl.pas.data.model.resource.BookEntity;
import pl.pas.data.model.user.ClientEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BorrowRepositoryTest {

    BorrowRepository borrowRepository;
    BorrowEntity borrow1;
    BorrowEntity borrow2;
    ClientEntity client;
    BookEntity book;
    AudioBookEntity audioBook;

    public BorrowRepositoryTest() {
        this.borrowRepository = new BorrowRepository();
        client = new ClientEntity("cLogin", "perkac", "Kacper", "Swiercz", 21);
        book = new BookEntity(432345345, "Book", "Author", 2000);
        audioBook = new AudioBookEntity(432345345, "Audio Book", "Author", 200);
        this.borrow1 = new BorrowEntity(client, book, new Date());
        this.borrow2 = new BorrowEntity(client, audioBook, new Date());
    }

    @Test
    void addBorrow() {
        Assertions.assertEquals(0, borrowRepository.getAllBorrows().size());

        borrowRepository.addBorrow(borrow1);
        borrowRepository.addBorrow(borrow2);

        Assertions.assertEquals(2, borrowRepository.getAllBorrows().size());
        Assertions.assertEquals(borrow1, borrowRepository.getAllBorrows().get(0));
        Assertions.assertEquals(borrow2, borrowRepository.getAllBorrows().get(1));
    }

    @Test
    void getBorrow() {
        assertThrows(NotFoundExceptionEntity.class,
                () -> borrowRepository.getBorrow(borrow1.getBorrowId()));

        borrowRepository.addBorrow(borrow1);
        borrowRepository.addBorrow(borrow2);

        Assertions.assertEquals(2, borrowRepository.getAllBorrows().size());
    }

    @Test
    void getBorrowsByUser() {
        assertEquals(0, borrowRepository.getBorrowsByUser(client.getUserId()).size());

        borrowRepository.addBorrow(borrow1);
        borrowRepository.addBorrow(borrow2);

        Assertions.assertEquals(2, borrowRepository.getBorrowsByUser(client.getUserId()).size());
        Assertions.assertEquals(borrow1, borrowRepository.getBorrowsByUser(client.getUserId()).get(0));
        Assertions.assertEquals(borrow2, borrowRepository.getBorrowsByUser(client.getUserId()).get(1));
    }

    @Test
    void getBorrowsByResource() {
        assertEquals(0, borrowRepository.getBorrowsByResource(book.getResourceId()).size());

        book.setResourceId(2);
        borrowRepository.addBorrow(borrow1);
        borrowRepository.addBorrow(borrow2);

        Assertions.assertEquals(1, borrowRepository.getBorrowsByResource(book.getResourceId()).size());
        Assertions.assertEquals(borrow1, borrowRepository.getBorrowsByResource(book.getResourceId()).get(0));
    }

    @Test
    void getAllBorrows() {
        Assertions.assertEquals(0, borrowRepository.getAllBorrows().size());

        borrowRepository.addBorrow(borrow1);
        borrowRepository.addBorrow(borrow2);

        Assertions.assertEquals(2, borrowRepository.getAllBorrows().size());
        Assertions.assertEquals(borrow1, borrowRepository.getAllBorrows().get(0));
        Assertions.assertEquals(borrow2, borrowRepository.getAllBorrows().get(1));
    }

    @Test
    void updateBorrow() {
        Assertions.assertEquals(0, borrowRepository.getAllBorrows().size());

        borrowRepository.addBorrow(borrow1);
        borrowRepository.addBorrow(borrow2);

        BookEntity book3 = new BookEntity(21412, "ttt", "authoor", 2020);
        BorrowEntity borrowEntity = new BorrowEntity(client, book3);

        Assertions.assertEquals(2, borrowRepository.getAllBorrows().size());
        Assertions.assertEquals(borrow1, borrowRepository.getAllBorrows().get(0));
        Assertions.assertEquals(borrow2, borrowRepository.getAllBorrows().get(1));

        borrowRepository.updateBorrow(borrow1.getBorrowId(), borrowEntity);

        try {
            assertEquals(borrowEntity, borrowRepository.getBorrow(borrow1.getBorrowId()));
        } catch (NotFoundExceptionEntity notFoundExceptionEntity) {
            throw new TestAbortedException();
        }

    }

    @Test
    void deleteBorrow() {
        Assertions.assertEquals(0, borrowRepository.getAllBorrows().size());

        borrowRepository.addBorrow(borrow1);
        borrowRepository.addBorrow(borrow2);

        Assertions.assertEquals(2, borrowRepository.getAllBorrows().size());
        Assertions.assertEquals(borrow1, borrowRepository.getAllBorrows().get(0));
        Assertions.assertEquals(borrow2, borrowRepository.getAllBorrows().get(1));

        borrowRepository.deleteBorrow(borrow1.getBorrowId());

        Assertions.assertEquals(1, borrowRepository.getAllBorrows().size());
        Assertions.assertEquals(borrow2, borrowRepository.getAllBorrows().get(0));
    }

    @Test
    void endBorrow() {
        Assertions.assertEquals(0, borrowRepository.getAllBorrows().size());

        borrowRepository.addBorrow(borrow1);
        borrowRepository.addBorrow(borrow2);

        try {
            assertNull(borrowRepository.getBorrow(borrow1.getBorrowId()).getReturnDate());
        } catch (NotFoundExceptionEntity notFoundExceptionEntity) {
            throw new TestAbortedException();
        }

        try {
            borrowRepository.endBorrow(borrow1.getBorrowId());
        } catch (NotFoundExceptionEntity notFoundExceptionEntity) {
            throw new TestAbortedException();
        }

        try {
            assertNotNull(borrowRepository.getBorrow(borrow1.getBorrowId()).getReturnDate());
        } catch (NotFoundExceptionEntity notFoundExceptionEntity) {
            throw new TestAbortedException();
        }

    }
}
