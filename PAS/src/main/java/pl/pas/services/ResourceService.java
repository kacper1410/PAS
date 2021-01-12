package pl.pas.services;

import lombok.NoArgsConstructor;
import pl.pas.managers.ResourceManager;
import pl.pas.model.resource.Resource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@NoArgsConstructor
@RequestScoped
@Path("resource")
public class ResourceService {

    @Inject
    private ResourceManager resourceManager;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Resource> getAllResources() {
        return resourceManager.getAllResources();
    }
}
