package pl.pas.model.repositories;

import pl.pas.model.repositories.interfaces.IResourceRepository;
import pl.pas.model.entities.resource.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ResourceRepository implements IResourceRepository {
    List<Resource> resources;

    public ResourceRepository() {
        resources = new ArrayList<>();
    }

    @Override
    public boolean addResource(Resource resource) {
        return resources.add(resource);
    }

    @Override
    public Resource getResource(UUID uuid) {
        for (Resource r : resources) {
            if (r.getResourceId().equals(uuid)) {
                return r;
            }
        }

        return null;
    }

    @Override
    public List<Resource> getAllResources() {
        return resources;
    }

    @Override
    public void updateResource(Resource oldResource, Resource newResource) {
        resources.set(resources.indexOf(oldResource), newResource);
    }

    @Override
    public boolean deleteResource(UUID uuid) {
        for (Resource r : resources) {
            if (r.getResourceId().equals(uuid)) {
                return resources.remove(r);
            }
        }
        return false;
    }
}
