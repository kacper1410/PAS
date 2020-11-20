package pl.pas.model.repositories.interfaces;

import pl.pas.model.resource.Resource;

import java.util.List;
import java.util.UUID;

public interface IResourcesRepository {
    boolean addResource(Resource resource);
    Resource getResource(UUID uuid);
    List<Resource> getAllResources();
    boolean updateResource(Resource oldResource, Resource newResource);
    boolean deleteResource(UUID uuid);
}
