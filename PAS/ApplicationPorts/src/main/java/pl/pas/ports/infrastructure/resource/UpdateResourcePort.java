package pl.pas.ports.infrastructure.resource;

import pl.pas.model.resource.Resource;

public interface UpdateResourcePort {
    void updateResource(long id, Resource newResource);
}
