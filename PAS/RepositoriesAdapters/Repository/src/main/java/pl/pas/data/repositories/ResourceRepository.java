package pl.pas.data.repositories;

import pl.pas.data.repositories.interfaces.IResourceRepository;
import pl.pas.data.model.exceptions.NotFoundExceptionEntity;
import pl.pas.data.model.resource.ResourceEntity;
import pl.pas.data.model.resource.AudioBookEntity;
import pl.pas.data.model.resource.BookEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class ResourceRepository implements IResourceRepository, Serializable {

    private final List<ResourceEntity> resources;

    public ResourceRepository() {
        resources = new ArrayList<>();
    }

    @Override
    public void addResource(ResourceEntity resource) {
        synchronized (resources) {
            resource.setResourceId(UUID.randomUUID());
            resources.add(resource);
        }
    }

    @Override
    public ResourceEntity getResource(long uuid) throws NotFoundExceptionEntity {
        synchronized (resources) {
            for (ResourceEntity r : resources) {
                if (r.getResourceId() == uuid) {
                    return r;
                }
            }
            throw new NotFoundExceptionEntity();
        }
    }

    @Override
    public List<ResourceEntity> getAllResources() {
        synchronized (resources) {
            return new ArrayList<>(resources);
        }
    }

    @Override
    public void updateResource(long uuid, ResourceEntity newResource) {
        synchronized (resources) {
            for (ResourceEntity r : resources) {
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
            } catch (NotFoundExceptionEntity ignored) {

            }
        }
    }

    @Override
    public List<BookEntity> getAllBooks() {
        synchronized (resources) {
            ArrayList<BookEntity> books = new ArrayList<>();
            for (ResourceEntity resource : resources) {
                if (resource instanceof BookEntity) {
                    books.add((BookEntity) resource);
                }
            }
            return books;
        }
    }

    @Override
    public List<AudioBookEntity> getAllAudioBooks() {
        synchronized (resources) {
            ArrayList<AudioBookEntity> audioBooks = new ArrayList<>();
            for (ResourceEntity resource : resources) {
                if (resource instanceof AudioBookEntity) {
                    audioBooks.add((AudioBookEntity) resource);
                }
            }
            return audioBooks;
        }
    }
}
