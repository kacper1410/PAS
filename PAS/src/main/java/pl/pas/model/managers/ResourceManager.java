package pl.pas.model.managers;

import pl.pas.model.entities.Borrow;
import pl.pas.model.entities.resource.AudioBook;
import pl.pas.model.entities.resource.Book;
import pl.pas.model.entities.resource.Resource;
import pl.pas.model.repositories.interfaces.IBorrowRepository;
import pl.pas.model.repositories.interfaces.IResourceRepository;
import pl.pas.model.repositories.interfaces.IUserRepository;

import java.util.*;

public class ResourceManager {
    IResourceRepository resourceRepository;
    IBorrowRepository borrowRepository;
    IUserRepository userRepository;

    public ResourceManager(IResourceRepository resourceRepository, IBorrowRepository borrowRepository, IUserRepository userRepository) {
        this.resourceRepository = resourceRepository;
        this.borrowRepository = borrowRepository;
        this.userRepository = userRepository;
    }

    public Resource getResource(UUID uuid) {
        if (uuid == null) {
            return null;
        }
        return resourceRepository.getResource(uuid);
    }

    public List<Resource> getAllResources() {
        return resourceRepository.getAllResources();
    }

    public List<Resource> getAllBooks() {
        ArrayList<Resource> books = new ArrayList<>();
        for (Resource resource : resourceRepository.getAllResources()) {
            if (resource instanceof Book) {
                books.add(resource);
            }
        }
        return books;
    }

    public List<Resource> getAllAudioBooks() {
        ArrayList<Resource> audioBooks = new ArrayList<>();
        for (Resource resource : resourceRepository.getAllResources()) {
            if (resource instanceof AudioBook) {
                audioBooks.add(resource);
            }
        }
        return audioBooks;
    }

    public boolean removeResource(Resource resource) {
        return removeResource(resource.getResourceId());
    }

    public boolean removeResource(UUID uuid) {
        if (uuid == null || resourceRepository.getResource(uuid) == null) {
            return false;
        }
        if (!resourceRepository.getResource(uuid).isAvailable()) {
            return false;
        }
        for (Borrow borrow : borrowRepository.getAllBorrows()) {
            if (borrow.getResource() == resourceRepository.getResource(uuid)) {
                borrow.setResource(null);
            }
        }
        return resourceRepository.deleteResource(uuid);
    }

    public boolean addBook(long isbn, String title, String author, int publishYear) {
        if (isbn == 0 || title == null || author == null || publishYear > Calendar.getInstance().get(Calendar.YEAR)) {
            return false;
        }
        return resourceRepository.addResource(new Book(isbn, title, author, publishYear), UUID.randomUUID());
    }

    public boolean addAudioBook(long isbn, String title, String author, int length) {
        if (isbn == 0 || title == null || author == null || length <= 0) {
            return false;
        }
        return resourceRepository.addResource(new Book(isbn, title, author, length), UUID.randomUUID());
    }

    public boolean updateBook(Resource oldResource, long ISBN, String title, String author, int publishYear) {
        if (oldResource == null || ISBN <= 0 || title == null || author == null
                || publishYear > Calendar.getInstance().get(Calendar.YEAR)
                || !resourceRepository.getAllResources().contains(oldResource) || !(oldResource instanceof Book)) {
            return false;
        }
        resourceRepository.updateResource(oldResource.getResourceId(), new Book(ISBN, title, author, publishYear));
        return true;
    }

    public boolean updateAudioBook(Resource oldResource, long ISBN, String title, String author, int length) {
        if (oldResource == null || ISBN <= 0 || title == null || author == null || length > 0
                || !resourceRepository.getAllResources().contains(oldResource) || !(oldResource instanceof AudioBook)) {
            return false;
        }
        resourceRepository.updateResource(oldResource.getResourceId(), new AudioBook(ISBN, title, author, length));
        return true;
    }

}
