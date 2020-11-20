package pl.pas.model.repositories;

import pl.pas.model.repositories.interfaces.IResourcesRepository;
import pl.pas.model.resource.Resource;

import java.util.List;
import java.util.UUID;

public class ResourceRepository implements IResourcesRepository {

    @Override
    public boolean addResource(Resource resource) {
        return false;
    }

    @Override
    public Resource getResource(UUID uuid) {
        return null;
    }

    @Override
    public List<Resource> getAllResources() {
        return null;
    }

    @Override
    public boolean updateResource(Resource oldResource, Resource newResource) {
        return false;
    }

    @Override
    public boolean deleteResource(UUID uuid) {
        return false;
    }
}
