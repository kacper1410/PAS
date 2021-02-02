package pl.pas.repositories;

import pl.pas.exceptions.NotFoundException;
import pl.pas.model.resource.AudioBook;
import pl.pas.model.resource.Book;
import pl.pas.model.resource.Resource;
import pl.pas.repositories.interfaces.IResourceRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class ResourceRepository implements IResourceRepository, Serializable {

    private final List<Resource> resources;

    public ResourceRepository() {
        resources = new ArrayList<>();
    }

    @Override
    public void addResource(Resource resource) {
        synchronized (resources) {
            resource.setResourceId(UUID.randomUUID());
            resources.add(resource);
        }
    }

    @Override
    public Resource getResource(long uuid) throws NotFoundException {
        synchronized (resources) {
            for (Resource r : resources) {
                if (r.getResourceId() == uuid) {
                    return r;
                }
            }
            throw new NotFoundException();
        }
    }

    @Override
    public List<Resource> getAllResources() {
        synchronized (resources) {
            return new ArrayList<>(resources);
        }
    }

    @Override
    public void updateResource(long uuid, Resource newResource) {
        synchronized (resources) {
            for (Resource r : resources) {
                if (r.getResourceId() == uuid) {
                    newResource.setResourceId(uuid);
                    resources.set(resources.indexOf(r), newResource);
                }
            }
        }
    }

    @Override
    public void deleteResource(long uuid) {
        synchronized (resources) {
            try {
                resources.remove(getResource(uuid));
            } catch (NotFoundException ignored) {

            }
        }
    }

    @Override
    public List<Book> getAllBooks() {
        synchronized (resources) {
            ArrayList<Book> books = new ArrayList<>();
            for (Resource resource : resources) {
                if (resource instanceof Book) {
                    books.add((Book) resource);
                }
            }
            return books;
        }
    }

    @Override
    public List<AudioBook> getAllAudioBooks() {
        synchronized (resources) {
            ArrayList<AudioBook> audioBooks = new ArrayList<>();
            for (Resource resource : resources) {
                if (resource instanceof AudioBook) {
                    audioBooks.add((AudioBook) resource);
                }
            }
            return audioBooks;
        }
    }
}
