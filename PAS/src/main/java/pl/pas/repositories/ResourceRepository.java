package pl.pas.repositories;

import pl.pas.model.resource.AudioBook;
import pl.pas.model.resource.Book;
import pl.pas.model.resource.Resource;
import pl.pas.repositories.interfaces.IResourceRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Named
@ApplicationScoped
public class ResourceRepository implements IResourceRepository, Serializable {

    private final List<Resource> resources;

    public ResourceRepository() {
        resources = new ArrayList<>();
    }

    @Override
    public boolean addResource(Resource resource) {
        synchronized (resources) {
            resource.setResourceId(UUID.randomUUID());
            return resources.add(resource);
        }
    }

    @Override
    public Resource getResource(UUID uuid) {
        synchronized (resources) {
            for (Resource r : resources) {
                if (r.getResourceId().equals(uuid)) {
                    return r;
                }
            }

            return null;
        }
    }

    @Override
    public List<Resource> getAllResources() {
        synchronized (resources) {
            return new ArrayList<>(resources);
        }
    }

    @Override
    public void updateResource(UUID uuid, Resource newResource) {
        synchronized (resources) {
            for (Resource r : resources) {
                if (r.getResourceId().equals(uuid)) {
                    newResource.setResourceId(uuid);
                    resources.set(resources.indexOf(r), newResource);
                }
            }
        }
    }

    @Override
    public boolean deleteResource(UUID uuid) {
        synchronized (resources) {
            return resources.remove(getResource(uuid));
        }
    }

    @Override
    public List<Resource> getAllBooks() {
        synchronized (resources) {
            ArrayList<Resource> books = new ArrayList<>();
            for (Resource resource : resources) {
                if (resource instanceof Book) {
                    books.add(resource);
                }
            }
            return books;
        }
    }

    @Override
    public List<Resource> getAllAudioBooks() {
        synchronized (resources) {
            ArrayList<Resource> audioBooks = new ArrayList<>();
            for (Resource resource : resources) {
                if (resource instanceof AudioBook) {
                    audioBooks.add(resource);
                }
            }
            return audioBooks;
        }
    }
}
