package pl.pas.ports.infrastructure.resource;

import pl.pas.domain.model.resource.Resource;

public interface CreateResourcePort {
    void createResource(Resource resource);
}
