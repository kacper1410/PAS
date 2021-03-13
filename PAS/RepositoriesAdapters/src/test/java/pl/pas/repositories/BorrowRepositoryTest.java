package pl.pas.repositories;

import org.junit.Test;
import pl.pas.data.model.BorrowEntity;
import pl.pas.data.model.exceptions.NotFoundExceptionEntity;
import pl.pas.data.model.exceptions.UserAlreadyExistExceptionEntity;
import pl.pas.data.model.resource.AudioBookEntity;
import pl.pas.data.model.resource.BookEntity;
import pl.pas.data.model.user.ClientEntity;

import java.util.Date;

import static org.junit.Assert.*;

public class BorrowRepositoryTest {

    @Test
    public void testAddBorrow() {
        BorrowRepository borrowRepository = new BorrowRepository();
        ResourceRepository resourceRepository = new ResourceRepository();
        UserRepository userRepository = new UserRepository();
        BookEntity book = new BookEntity(432345345, "Book", "Author", 2000);
        AudioBookEntity audioBook = new AudioBookEntity(432345345, "Audio Book", "Author", 200);
        resourceRepository.addResource(book);
        resourceRepository.addResource(audioBook);
        ClientEntity client = new ClientEntity("cLogin", "perkac", "Kacper", "Swiercz", 21);
        try {
            userRepository.addUser(client);
        } catch (UserAlreadyExistExceptionEntity e) {
            e.printStackTrace();
        }
        BorrowEntity borrow1 = new BorrowEntity(client, book, new Date());
        BorrowEntity borrow2 = new BorrowEntity(client, audioBook, new Date());
        borrowRepository.addBorrow(borrow1);
        borrowRepository.addBorrow(borrow2);

        try {
            assertEquals(borrowRepository.getBorrow(borrow1.getBorrowId()), borrow1);
            assertEquals(borrowRepository.getBorrow(borrow2.getBorrowId()), borrow2);
        } catch (NotFoundExceptionEntity e) {
            e.printStackTrace();
        }
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

    }
}
