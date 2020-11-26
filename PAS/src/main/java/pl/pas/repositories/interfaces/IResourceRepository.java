package pl.pas.repositories.interfaces;

import pl.pas.model.resource.Resource;
import java.util.List;
import java.util.UUID;

public interface IResourceRepository {
    // TODO
    // UUID in addResource method

    boolean addResource(Resource resource);
    Resource getResource(UUID uuid);
    List<Resource> getAllResources();
    void updateResource(UUID uuid, Resource newResource);
    boolean deleteResource(UUID uuid);
    List<Resource> getAllBooks();
    List<Resource> getAllAudioBooks();
}
