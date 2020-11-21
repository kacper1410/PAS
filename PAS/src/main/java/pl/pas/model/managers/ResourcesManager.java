package pl.pas.model.managers;

import pl.pas.model.entities.resource.Book;
import pl.pas.model.entities.resource.Resource;
import pl.pas.model.repositories.ResourceRepository;
import pl.pas.model.repositories.interfaces.IBorrowRepository;
import pl.pas.model.repositories.interfaces.IResourceRepository;
import pl.pas.model.repositories.interfaces.IUserRepository;

import java.util.List;
import java.util.UUID;

public class ResourcesManager {
    IResourceRepository resourceRepository;
    IBorrowRepository borrowRepository;
    IUserRepository userRepository;

    public ResourcesManager(IResourceRepository resourceRepository, IBorrowRepository borrowRepository, IUserRepository userRepository) {
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

    public boolean removeResource(Resource resource) {
        return resourceRepository.deleteResource(resource.getResourceId());
    }

    public boolean removeResource(UUID uuid) {
        return resourceRepository.deleteResource(uuid);
    }

    public boolean createBook(long isbn, String title, String author, int publishYear) {
        return resourceRepository.addResource(new Book(isbn, title, author, publishYear), UUID.randomUUID());
    }

    public boolean createAudioBook(long isbn, String title, String author, int length) {
        return resourceRepository.addResource(new Book(isbn, title, author, length), UUID.randomUUID());
    }



}
