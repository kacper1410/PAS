package pl.pas.model.repositories.interfaces;

import pl.pas.model.entities.resource.Resource;

import java.util.List;
import java.util.UUID;

public interface IResourceRepository {
    boolean addResource(Resource resource);
    Resource getResource(UUID uuid);
    List<Resource> getAllResources();
    void updateResource(Resource oldResource, Resource newResource);
    boolean deleteResource(UUID uuid);
}
