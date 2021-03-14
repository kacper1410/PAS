package pl.pas.ports.infrastructure.resource;

import pl.pas.data.model.resource.Resource;

public interface UpdateResourcePort {
    void updateResource(long id, Resource newResource);
}
