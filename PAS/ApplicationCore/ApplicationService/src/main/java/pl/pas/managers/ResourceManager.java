package pl.pas.managers;

import pl.pas.data.exceptions.NotFoundException;
import pl.pas.data.exceptions.NotValidException;
import pl.pas.data.model.Borrow;
import pl.pas.data.model.resource.AudioBook;
import pl.pas.data.model.resource.Book;
import pl.pas.data.model.resource.Resource;
import pl.pas.ports.infrastructure.borrow.ReadBorrowPort;
import pl.pas.ports.infrastructure.resource.CreateResourcePort;
import pl.pas.ports.infrastructure.resource.DeleteResourcePort;
import pl.pas.ports.infrastructure.resource.ReadResourcePort;
import pl.pas.ports.infrastructure.resource.UpdateResourcePort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Named
@ApplicationScoped
public class ResourceManager implements Serializable {

    @Inject
    private ReadResourcePort readResourcePort;
    @Inject
    private ReadBorrowPort readBorrowPort;
    @Inject
    private DeleteResourcePort deleteResourcePort;
    @Inject
    private CreateResourcePort createResourcePort;
    @Inject
    private UpdateResourcePort updateResourcePort;

    public ResourceManager() {
    }

    public Resource getResource(long uuid) throws NotFoundException {
        return readResourcePort.readResource(uuid);
    }

    public List<Resource> getAllResources() {
        return readResourcePort.readAllResources();
    }

    public List<Resource> getAllAvailableResources() {
        List<Resource> availableResources = new ArrayList<>();
        for (Resource res: readResourcePort.readAllResources()) {
            if (res.isAvailable()) availableResources.add(res);
        }
        return availableResources;
    }

    public List<Book> getAllBooks() {
        return readResourcePort.readAllBooks();
    }

    public List<AudioBook> getAllAudioBooks() {
        return readResourcePort.readAllAudioBooks();
    }

    public void removeResource(Resource resource) throws NotValidException {
        removeResource(resource.getResourceId());
    }

    public void removeResource(long uuid) throws NotValidException {
        Resource resource;
        try {
            resource = readResourcePort.readResource(uuid);
        } catch (NotFoundException e) {
            return;
        }

        if (!resource.isAvailable()) {
            throw new NotValidException();
        }

        for (Borrow borrow : readBorrowPort.readAllBorrows()) {
            if (borrow.getResource() == resource) {
                borrow.setResource(null);
            }
        }

        deleteResourcePort.deleteResource(uuid);
    }

    public void addBook(long isbn, String title, String author, int publishYear) throws NotValidException {
        if (isbn == 0 || title == null || author == null || publishYear > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new NotValidException();
        }
        createResourcePort.createResource(new Book(isbn, title, author, publishYear));
    }

    public void addAudioBook(long isbn, String title, String author, int length) throws NotValidException {
        if (isbn == 0 || title == null || author == null || length <= 0) {
            throw new NotValidException();
        }
        createResourcePort.createResource(new Book(isbn, title, author, length));
    }

    public void updateBook(Resource oldResource, long ISBN, String title, String author, int publishYear) throws NotValidException {
        if (oldResource == null || ISBN <= 0 || title == null || author == null
                || publishYear > Calendar.getInstance().get(Calendar.YEAR)
                || !readResourcePort.readAllResources().contains(oldResource) || !(oldResource instanceof Book)
                || !oldResource.isAvailable()) {
            throw new NotValidException();
        }
        updateResourcePort.updateResource(oldResource.getResourceId(), new Book(ISBN, title, author, publishYear));
    }

    public void updateAudioBook(Resource oldResource, long ISBN, String title, String author, int length) throws NotValidException {
        if (oldResource == null || ISBN <= 0 || title == null || author == null || length < 0
                || !readResourcePort.readAllResources().contains(oldResource) || !(oldResource instanceof AudioBook)
                || !oldResource.isAvailable()) {
            throw new NotValidException();
        }
        updateResourcePort.updateResource(oldResource.getResourceId(), new AudioBook(ISBN, title, author, length));
    }

}
