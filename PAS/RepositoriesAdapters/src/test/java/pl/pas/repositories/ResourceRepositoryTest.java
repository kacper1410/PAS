package pl.pas.repositories;

import org.junit.Test;
import org.opentest4j.TestAbortedException;
import pl.pas.data.model.exceptions.NotFoundExceptionEntity;
import pl.pas.data.model.resource.AudioBookEntity;
import pl.pas.data.model.resource.BookEntity;

import static org.junit.Assert.*;

public class ResourceRepositoryTest {
    ResourceRepository resourceRepository = new ResourceRepository();
    BookEntity book1;
    BookEntity book2;
    AudioBookEntity audioBook1;
    AudioBookEntity audioBook2;

    public ResourceRepositoryTest() {
        book1 = new BookEntity(432345345, "Book", "Author", 2000);
        book2 = new BookEntity(3232, "New Book", "Author", 2005);
        audioBook1 = new AudioBookEntity(432345, "Audio Book", "Author", 200);
        audioBook2 = new AudioBookEntity(32323, "New Audio Book", "Author", 205);
    }

    @Test
    public void testAddResource() {
        assertEquals(0, resourceRepository.getAllResources().size());

        resourceRepository.addResource(book1);
        resourceRepository.addResource(book2);
        resourceRepository.addResource(audioBook1);
        resourceRepository.addResource(audioBook2);

        assertEquals(4, resourceRepository.getAllResources().size());
    }

    @Test
    public void testGetResource() {
        resourceRepository.addResource(book1);
        resourceRepository.addResource(book2);
        resourceRepository.addResource(audioBook1);
        resourceRepository.addResource(audioBook2);

        try {
            assertEquals(book1, resourceRepository.getResource(book1.getResourceId()));
            assertEquals(book2, resourceRepository.getResource(book2.getResourceId()));
            assertEquals(audioBook1, resourceRepository.getResource(audioBook1.getResourceId()));
            assertEquals(audioBook2, resourceRepository.getResource(audioBook2.getResourceId()));
        } catch (NotFoundExceptionEntity e) {
            throw new TestAbortedException();
        }
    }

    @Test
    public void testGetAllResources() {
        resourceRepository.addResource(book1);
        resourceRepository.addResource(book2);
        resourceRepository.addResource(audioBook1);
        resourceRepository.addResource(audioBook2);

        assertEquals(book1, resourceRepository.getAllResources().get(0));
        assertEquals(book2, resourceRepository.getAllResources().get(1));
        assertEquals(audioBook1, resourceRepository.getAllResources().get(2));
        assertEquals(audioBook2, resourceRepository.getAllResources().get(3));
    }

    @Test
    public void testUpdateResource() {
        resourceRepository.addResource(book1);
        resourceRepository.addResource(book2);
        resourceRepository.addResource(audioBook1);
        resourceRepository.addResource(audioBook2);

        //test update ISBN
        assertEquals(resourceRepository.getAllBooks().get(0).getISBN(), book1.getISBN());
        assertEquals(resourceRepository.getAllBooks().get(0).getTitle(), book1.getTitle());
        assertEquals(resourceRepository.getAllBooks().get(0).getAuthor(), book1.getAuthor());
        assertEquals(resourceRepository.getAllBooks().get(0).getPublishYear(), book1.getPublishYear());

        BookEntity bookUpdatedISBN = new BookEntity(191919, book1.getTitle(), book1.getAuthor(), book1.getPublishYear());
        resourceRepository.updateResource(book1.getResourceId(), bookUpdatedISBN);

        assertEquals(book1.getResourceId(), bookUpdatedISBN.getResourceId());
        assertEquals(resourceRepository.getAllBooks().get(0).getISBN(), bookUpdatedISBN.getISBN());
        assertEquals(resourceRepository.getAllBooks().get(0).getTitle(), book1.getTitle());
        assertEquals(resourceRepository.getAllBooks().get(0).getAuthor(), book1.getAuthor());
        assertEquals(resourceRepository.getAllBooks().get(0).getPublishYear(), book1.getPublishYear());

        //test update title
        BookEntity bookUpdatedTitle = new BookEntity(book1.getISBN(), "Diff title", book1.getAuthor(), book1.getPublishYear());
        resourceRepository.updateResource(book1.getResourceId(), bookUpdatedTitle);

        assertEquals(book1.getResourceId(), bookUpdatedTitle.getResourceId());
        assertEquals(resourceRepository.getAllBooks().get(0).getISBN(), book1.getISBN());
        assertEquals(resourceRepository.getAllBooks().get(0).getTitle(), bookUpdatedTitle.getTitle());
        assertEquals(resourceRepository.getAllBooks().get(0).getAuthor(), book1.getAuthor());
        assertEquals(resourceRepository.getAllBooks().get(0).getPublishYear(), book1.getPublishYear());

        //test update author
        BookEntity bookUpdatedAuthor = new BookEntity(book1.getISBN(), book1.getTitle(),"Arek", book1.getPublishYear());
        resourceRepository.updateResource(book1.getResourceId(), bookUpdatedAuthor);

        assertEquals(book1.getResourceId(), bookUpdatedAuthor.getResourceId());
        assertEquals(resourceRepository.getAllBooks().get(0).getISBN(), book1.getISBN());
        assertEquals(resourceRepository.getAllBooks().get(0).getTitle(), book1.getTitle());
        assertEquals(resourceRepository.getAllBooks().get(0).getAuthor(), bookUpdatedAuthor.getAuthor());
        assertEquals(resourceRepository.getAllBooks().get(0).getPublishYear(), book1.getPublishYear());

        //test update publishYear
        BookEntity bookUpdatedPublishYear = new BookEntity(book1.getISBN(), book1.getTitle(),book1.getAuthor(), 15);
        resourceRepository.updateResource(book1.getResourceId(), bookUpdatedPublishYear);

        assertEquals(book1.getResourceId(), bookUpdatedPublishYear.getResourceId());
        assertEquals(resourceRepository.getAllBooks().get(0).getISBN(), book1.getISBN());
        assertEquals(resourceRepository.getAllBooks().get(0).getTitle(), book1.getTitle());
        assertEquals(resourceRepository.getAllBooks().get(0).getAuthor(), book1.getAuthor());
        assertEquals(resourceRepository.getAllBooks().get(0).getPublishYear(), bookUpdatedPublishYear.getPublishYear());

        //test update length
        AudioBookEntity audioBookUpdatedLength = new AudioBookEntity(audioBook1.getISBN(), audioBook1.getTitle(),audioBook1.getAuthor(), 300);
        resourceRepository.updateResource(audioBook1.getResourceId(), audioBookUpdatedLength);

        assertEquals(audioBook1.getResourceId(), audioBookUpdatedLength.getResourceId());
        assertEquals(resourceRepository.getAllAudioBooks().get(0).getISBN(), audioBook1.getISBN());
        assertEquals(resourceRepository.getAllAudioBooks().get(0).getTitle(), audioBook1.getTitle());
        assertEquals(resourceRepository.getAllAudioBooks().get(0).getAuthor(), audioBook1.getAuthor());
        assertEquals(resourceRepository.getAllAudioBooks().get(0).getLength(), audioBookUpdatedLength.getLength());
    }

    @Test
    public void testDeleteResource() {
        assertEquals(0, resourceRepository.getAllResources().size());

        resourceRepository.addResource(book1);
        resourceRepository.addResource(book2);
        resourceRepository.addResource(audioBook1);
        resourceRepository.addResource(audioBook2);

        resourceRepository.deleteResource(audioBook2.getResourceId());

        assertEquals(3, resourceRepository.getAllResources().size());

        assertEquals(book1, resourceRepository.getAllResources().get(0));
        assertEquals(book2, resourceRepository.getAllResources().get(1));
        assertEquals(audioBook1, resourceRepository.getAllResources().get(2));
    }

    @Test
    public void testGetAllBooks() {
        resourceRepository.addResource(book1);
        resourceRepository.addResource(book2);
        resourceRepository.addResource(audioBook1);
        resourceRepository.addResource(audioBook2);

        assertEquals(2, resourceRepository.getAllBooks().size());
        assertEquals(book1, resourceRepository.getAllBooks().get(0));
        assertEquals(book2, resourceRepository.getAllBooks().get(1));
    }

    @Test
    public void testGetAllAudioBooks() {
        resourceRepository.addResource(book1);
        resourceRepository.addResource(book2);
        resourceRepository.addResource(audioBook1);
        resourceRepository.addResource(audioBook2);

        assertEquals(2, resourceRepository.getAllAudioBooks().size());
        assertEquals(audioBook1, resourceRepository.getAllAudioBooks().get(0));
        assertEquals(audioBook2, resourceRepository.getAllAudioBooks().get(1));
    }
}
