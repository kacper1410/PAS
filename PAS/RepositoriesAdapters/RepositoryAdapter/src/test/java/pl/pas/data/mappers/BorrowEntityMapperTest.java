package pl.pas.data.mappers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.pas.data.model.BorrowEntity;
import pl.pas.data.model.resource.BookEntity;
import pl.pas.data.model.user.ClientEntity;
import pl.pas.data.mappers.resource.ResourceEntityMapper;
import pl.pas.data.model.Borrow;
import pl.pas.data.model.resource.Book;
import pl.pas.data.model.user.Client;

import java.util.Date;

class BorrowEntityMapperTest {

    @Test
    void borrowEntityToBorrow() {
        ClientEntity clientEntity = new ClientEntity("niezly_login", "nie", "Igrek", "Iksinski", 23);
        BookEntity bookEntity = new BookEntity(123, "Droga Królów", "Author", 2010);
        Date date = new Date();

        BorrowEntity borrowEntity = new BorrowEntity(clientEntity, bookEntity, date);

        Borrow borrow = BorrowEntityMapper.borrowEntityToBorrow(borrowEntity);
        Assertions.assertEquals(borrowEntity.getBorrowId(), borrow.getBorrowId());
//        Assertions.assertEquals(UserEntityMapper.userEntityToUser(borrowEntity.getClient()), borrow.getClient());
        Assertions.assertEquals(ResourceEntityMapper.resourceEntityToResource(borrowEntity.getResource()), borrow.getResource());
        Assertions.assertEquals(borrowEntity.getBorrowDate(), borrow.getBorrowDate());
        Assertions.assertEquals(borrowEntity.getReturnDate(), borrow.getReturnDate());
    }

    @Test
    void borrowToBorrowEntity() {
        Client client = new Client("niezly_login", "nie", "Igrek", "Iksinski", 23);
        Book book = new Book(123, "Droga Królów", "Author", 2010);
        Date date = new Date();

        Borrow borrow = new Borrow(client, book, date);

        BorrowEntity borrowEntity = BorrowEntityMapper.borrowToBorrowEntity(borrow);
        Assertions.assertEquals(borrow.getBorrowId(), borrowEntity.getBorrowId());
//        Assertions.assertEquals(UserEntityMapper.userToUserEntity(borrow.getClient()), borrowEntity.getClient());
        Assertions.assertEquals(ResourceEntityMapper.resourceToResourceEntity(borrow.getResource()), borrowEntity.getResource());
        Assertions.assertEquals(borrow.getBorrowDate(), borrowEntity.getBorrowDate());
        Assertions.assertEquals(borrow.getReturnDate(), borrowEntity.getReturnDate());
    }
}
