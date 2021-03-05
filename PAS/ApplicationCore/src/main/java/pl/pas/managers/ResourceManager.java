package pl.pas.managers;

import pl.pas.exceptions.NotFoundException;
import pl.pas.exceptions.NotValidException;
import pl.pas.model.Borrow;
import pl.pas.model.resource.AudioBook;
import pl.pas.model.resource.Book;
import pl.pas.model.resource.Resource;
import pl.pas.repositories.interfaces.IBorrowRepository;
import pl.pas.repositories.interfaces.IResourceRepository;
import pl.pas.repositories.interfaces.IUserRepository;

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
    private IResourceRepository resourceRepository;
    @Inject
    private IBorrowRepository borrowRepository;
    @Inject
    private IUserRepository userRepository;

    public ResourceManager() {
    }

    public ResourceManager(IResourceRepository resourceRepository, IBorrowRepository borrowRepository, IUserRepository userRepository) {
        this.resourceRepository = resourceRepository;
        this.borrowRepository = borrowRepository;
        this.userRepository = userRepository;
    }

    public Resource getResource(long uuid) throws NotFoundException {
        return resourceRepository.getResource(uuid);
    }

    public List<Resource> getAllResources() {
        return resourceRepository.getAllResources();
    }

    public List<Resource> getAllAvailableResources() {
        List<Resource> availableResources = new ArrayList<>();
        for (Resource res: resourceRepository.getAllResources()) {
            if (res.isAvailable()) availableResources.add(res);
        }
        return availableResources;
    }

    public List<Book> getAllBooks() {
        return resourceRepository.getAllBooks();
    }

    public List<AudioBook> getAllAudioBooks() {
        return resourceRepository.getAllAudioBooks();
    }

    public void removeResource(Resource resource) throws NotValidException {
        removeResource(resource.getResourceId());
    }

    public void removeResource(long uuid) throws NotValidException {
        Resource resource;
        try {
            resource = resourceRepository.getResource(uuid);
        } catch (NotFoundException e) {
            return;
        }

        if (!resource.isAvailable()) {
            throw new NotValidException();
        }

        for (Borrow borrow : borrowRepository.getAllBorrows()) {
            if (borrow.getResource() == resource) {
                borrow.setResource(null);
            }
        }

        resourceRepository.deleteResource(uuid);
    }

    public void addBook(long isbn, String title, String author, int publishYear) throws NotValidException {
        if (isbn == 0 || title == null || author == null || publishYear > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new NotValidException();
        }
        resourceRepository.addResource(new Book(isbn, title, author, publishYear));
    }

    public void addAudioBook(long isbn, String title, String author, int length) throws NotValidException {
        if (isbn == 0 || title == null || author == null || length <= 0) {
            throw new NotValidException();
        }
        resourceRepository.addResource(new Book(isbn, title, author, length));
    }

    public void updateBook(Resource oldResource, long ISBN, String title, String author, int publishYear) throws NotValidException {
        if (oldResource == null || ISBN <= 0 || title == null || author == null
                || publishYear > Calendar.getInstance().get(Calendar.YEAR)
                || !resourceRepository.getAllResources().contains(oldResource) || !(oldResource instanceof Book)
                || !oldResource.isAvailable()) {
            throw new NotValidException();
        }
        resourceRepository.updateResource(oldResource.getResourceId(), new Book(ISBN, title, author, publishYear));
    }

    public void updateAudioBook(Resource oldResource, long ISBN, String title, String author, int length) throws NotValidException {
        if (oldResource == null || ISBN <= 0 || title == null || author == null || length < 0
                || !resourceRepository.getAllResources().contains(oldResource) || !(oldResource instanceof AudioBook)
                || !oldResource.isAvailable()) {
            throw new NotValidException();
        }
        resourceRepository.updateResource(oldResource.getResourceId(), new AudioBook(ISBN, title, author, length));
    }

}
