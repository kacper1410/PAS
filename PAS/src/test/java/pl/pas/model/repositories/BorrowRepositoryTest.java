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

    @Test
    public void addBorrow() {
        BorrowRepository borrowRepository = new BorrowRepository();
        ResourceRepository resourceRepository = new ResourceRepository();
        UserRepository userRepository = new UserRepository();
        Book book = new Book(432345345, "Book", "Author", 2000);
        AudioBook audioBook = new AudioBook(432345345, "Audio Book", "Author", 200);
        resourceRepository.addResource(book, UUID.randomUUID());
        resourceRepository.addResource(audioBook, UUID.randomUUID());
        Client client = new Client("cLogin", "Kacper", "Swiercz", 21);
        userRepository.addUser(client, UUID.randomUUID());
        Borrow borrow1 = new Borrow(client, book, new Date());
        Borrow borrow2 = new Borrow(client, audioBook, new Date());
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        borrowRepository.addBorrow(borrow1, uuid1);
        borrowRepository.addBorrow(borrow2, uuid2);

        assertEquals(borrowRepository.getBorrow(uuid1), borrow1);
        assertEquals(borrowRepository.getBorrow(uuid2), borrow2);
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
