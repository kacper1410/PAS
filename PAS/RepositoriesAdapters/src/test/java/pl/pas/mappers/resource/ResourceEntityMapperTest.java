package pl.pas.mappers.resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.pas.data.model.resource.AudioBookEntity;
import pl.pas.data.model.resource.BookEntity;
import pl.pas.model.resource.AudioBook;
import pl.pas.model.resource.Book;

class ResourceEntityMapperTest {

    @Test
    void resourceEntityToResource() {
        BookEntity bookEntity = new BookEntity(432345345, "Harry Potter", "Author", 2000);

        Book book = (Book) ResourceEntityMapper.resourceEntityToResource(bookEntity);
        Assertions.assertEquals(book.getResourceId(), book.getResourceId());
        Assertions.assertEquals(book.getISBN(), book.getISBN());
        Assertions.assertEquals(book.getTitle(), book.getTitle());
        Assertions.assertEquals(book.getAuthor(), book.getAuthor());
        Assertions.assertEquals(book.getPublishYear(), book.getPublishYear());
        Assertions.assertTrue(book.isAvailable());

        AudioBookEntity audioBookEntity = new AudioBookEntity(1, "Harry Potter", "Author", 270);

        AudioBook audioBook = (AudioBook) ResourceEntityMapper.resourceEntityToResource(audioBookEntity);
        Assertions.assertEquals(audioBook.getResourceId(), audioBook.getResourceId());
        Assertions.assertEquals(audioBook.getISBN(), audioBook.getISBN());
        Assertions.assertEquals(audioBook.getTitle(), audioBook.getTitle());
        Assertions.assertEquals(audioBook.getAuthor(), audioBook.getAuthor());
        Assertions.assertEquals(audioBook.getLength(), audioBook.getLength());
        Assertions.assertTrue(audioBook.isAvailable());
    }

    @Test
    void resourceToResourceEntity() {
        Book book = new Book(432345345, "Harry Potter", "Author", 2000);

        BookEntity bookEntity = (BookEntity) ResourceEntityMapper.resourceToResourceEntity(book);
        Assertions.assertEquals(book.getResourceId(), bookEntity.getResourceId());
        Assertions.assertEquals(book.getISBN(), bookEntity.getISBN());
        Assertions.assertEquals(book.getTitle(), bookEntity.getTitle());
        Assertions.assertEquals(book.getAuthor(), bookEntity.getAuthor());
        Assertions.assertEquals(book.getPublishYear(), bookEntity.getPublishYear());
        Assertions.assertTrue(bookEntity.isAvailable());

        AudioBook audioBook = new AudioBook(1, "Harry Potter", "Author", 270);

        AudioBookEntity audioBookEntity = (AudioBookEntity) ResourceEntityMapper.resourceToResourceEntity(audioBook);
        Assertions.assertEquals(audioBook.getResourceId(), audioBookEntity.getResourceId());
        Assertions.assertEquals(audioBook.getISBN(), audioBookEntity.getISBN());
        Assertions.assertEquals(audioBook.getTitle(), audioBookEntity.getTitle());
        Assertions.assertEquals(audioBook.getAuthor(), audioBookEntity.getAuthor());
        Assertions.assertEquals(audioBook.getLength(), audioBookEntity.getLength());
        Assertions.assertTrue(audioBookEntity.isAvailable());
    }
}
