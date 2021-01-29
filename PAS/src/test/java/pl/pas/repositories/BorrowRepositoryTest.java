package pl.pas.repositories;

import org.junit.Test;
import pl.pas.exceptions.UserAlreadyExistException;
import pl.pas.model.Borrow;
import pl.pas.model.resource.AudioBook;
import pl.pas.model.resource.Book;
import pl.pas.model.user.Client;
import java.util.Date;
import java.util.UUID;
import static org.junit.Assert.*;

public class BorrowRepositoryTest {

    @Test
    public void testAddBorrow() {
        BorrowRepository borrowRepository = new BorrowRepository();
        ResourceRepository resourceRepository = new ResourceRepository();
        UserRepository userRepository = new UserRepository();
        Book book = new Book(432345345, "Book", "Author", 2000);
        AudioBook audioBook = new AudioBook(432345345, "Audio Book", "Author", 200);
        resourceRepository.addResource(book);
        resourceRepository.addResource(audioBook);
        Client client = new Client("cLogin", "Kacper", "Swiercz", 21);
        try {
            userRepository.addUser(client);
        } catch (UserAlreadyExistException e) {
            e.printStackTrace();
        }
        Borrow borrow1 = new Borrow(client, book, new Date());
        Borrow borrow2 = new Borrow(client, audioBook, new Date());
        borrowRepository.addBorrow(borrow1);
        borrowRepository.addBorrow(borrow2);

        assertEquals(borrowRepository.getBorrow(borrow1.getBorrowId()), borrow1);
        assertEquals(borrowRepository.getBorrow(borrow2.getBorrowId()), borrow2);
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
