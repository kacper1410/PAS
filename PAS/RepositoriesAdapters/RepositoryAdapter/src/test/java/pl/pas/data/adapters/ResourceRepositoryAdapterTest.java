package pl.pas.data.adapters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.pas.data.repositories.ResourceRepository;
import pl.pas.domain.exceptions.NotFoundException;
import pl.pas.domain.model.resource.AudioBook;
import pl.pas.domain.model.resource.Book;
import pl.pas.domain.model.resource.Resource;

import java.util.List;

class ResourceRepositoryAdapterTest {

    private final ResourceRepositoryAdapter resourceRepositoryAdapter;

    private final Book book;
    private final Book book1;

    private final AudioBook audioBook;
    private final AudioBook audioBook1;

    public ResourceRepositoryAdapterTest() {
        ResourceRepository resourceRepository = new ResourceRepository();
        resourceRepositoryAdapter = new ResourceRepositoryAdapter(resourceRepository);

        book = new Book(432345345, "Book", "Author", 2000);
        book1 = new Book(3232, "New Book", "Author", 2005);

        audioBook = new AudioBook(432345, "Audio Book", "Author", 200);
        audioBook1 = new AudioBook(32323, "New Audio Book", "Author", 205);
    }

    @Test
    void createResource() {
        resourceRepositoryAdapter.createResource(book);
        resourceRepositoryAdapter.createResource(audioBook);

        List<Resource> resources = resourceRepositoryAdapter.readAllResources();

        book.setResourceId(resources.get(0).getResourceId());
        audioBook.setResourceId(resources.get(1).getResourceId());

        Assertions.assertEquals(2, resources.size());
        Assertions.assertEquals(book, resources.get(0));
        Assertions.assertEquals(audioBook, resources.get(1));
    }

    @Test
    void deleteResource() {
        resourceRepositoryAdapter.createResource(book);
        resourceRepositoryAdapter.createResource(audioBook);

        List<Resource> resources = resourceRepositoryAdapter.readAllResources();

        book.setResourceId(resources.get(0).getResourceId());
        audioBook.setResourceId(resources.get(1).getResourceId());

        Assertions.assertEquals(2, resources.size());
        Assertions.assertEquals(book, resources.get(0));
        Assertions.assertEquals(audioBook, resources.get(1));

        resourceRepositoryAdapter.deleteResource(book.getResourceId());

        resources = resourceRepositoryAdapter.readAllResources();

        Assertions.assertEquals(1, resources.size());
        Assertions.assertEquals(audioBook, resources.get(0));

        resourceRepositoryAdapter.deleteResource(audioBook.getResourceId());

        resources = resourceRepositoryAdapter.readAllResources();

        Assertions.assertEquals(0, resources.size());
    }

    @Test
    void readResource() throws NotFoundException {
        resourceRepositoryAdapter.createResource(book);
        resourceRepositoryAdapter.createResource(audioBook);

        List<Resource> resources = resourceRepositoryAdapter.readAllResources();

        book.setResourceId(resources.get(0).getResourceId());
        audioBook.setResourceId(resources.get(1).getResourceId());

        Assertions.assertEquals(2, resources.size());
        Assertions.assertEquals(book, resources.get(0));
        Assertions.assertEquals(audioBook, resources.get(1));

        Resource resource = resourceRepositoryAdapter.readResource(book.getResourceId());

        Assertions.assertEquals(book, resource);

        resource = resourceRepositoryAdapter.readResource(audioBook.getResourceId());

        Assertions.assertEquals(audioBook, resource);
    }

    @Test
    void readAllResources() {
        resourceRepositoryAdapter.createResource(book);
        resourceRepositoryAdapter.createResource(book1);

        resourceRepositoryAdapter.createResource(audioBook);
        resourceRepositoryAdapter.createResource(audioBook1);

        List<Resource> resources = resourceRepositoryAdapter.readAllResources();

        book.setResourceId(resources.get(0).getResourceId());
        book1.setResourceId(resources.get(1).getResourceId());

        audioBook.setResourceId(resources.get(2).getResourceId());
        audioBook1.setResourceId(resources.get(3).getResourceId());

        Assertions.assertEquals(4, resources.size());

        Assertions.assertEquals(book, resources.get(0));
        Assertions.assertEquals(book1, resources.get(1));

        Assertions.assertEquals(audioBook, resources.get(2));
        Assertions.assertEquals(audioBook1, resources.get(3));
    }

    @Test
    void readAllBooks() {
        resourceRepositoryAdapter.createResource(book);
        resourceRepositoryAdapter.createResource(book1);

        resourceRepositoryAdapter.createResource(audioBook);
        resourceRepositoryAdapter.createResource(audioBook1);

        List<Book> resources = resourceRepositoryAdapter.readAllBooks();

        book.setResourceId(resources.get(0).getResourceId());
        book1.setResourceId(resources.get(1).getResourceId());

        Assertions.assertEquals(2, resources.size());

        Assertions.assertEquals(book, resources.get(0));
        Assertions.assertEquals(book1, resources.get(1));
    }

    @Test
    void readAllAudioBooks() {
        resourceRepositoryAdapter.createResource(book);
        resourceRepositoryAdapter.createResource(book1);

        resourceRepositoryAdapter.createResource(audioBook);
        resourceRepositoryAdapter.createResource(audioBook1);

        List<AudioBook> resources = resourceRepositoryAdapter.readAllAudioBooks();

        audioBook.setResourceId(resources.get(0).getResourceId());
        audioBook1.setResourceId(resources.get(1).getResourceId());

        Assertions.assertEquals(2, resources.size());

        Assertions.assertEquals(audioBook, resources.get(0));
        Assertions.assertEquals(audioBook1, resources.get(1));
    }

    @Test
    void updateResource() {
        resourceRepositoryAdapter.createResource(book);

        List<Resource> resources = resourceRepositoryAdapter.readAllResources();

        book.setResourceId(resources.get(0).getResourceId());

        Assertions.assertEquals(1, resources.size());
        Assertions.assertEquals(book, resources.get(0));

        resourceRepositoryAdapter.updateResource(book.getResourceId(), book1);

        resources = resourceRepositoryAdapter.readAllResources();

        book1.setResourceId(resources.get(0).getResourceId());

        Assertions.assertEquals(1, resources.size());
        Assertions.assertEquals(book1, resources.get(0));
    }
}
