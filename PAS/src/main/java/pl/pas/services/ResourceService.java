package pl.pas.services;

import lombok.NoArgsConstructor;
import pl.pas.managers.ResourceManager;
import pl.pas.model.resource.AudioBook;
import pl.pas.model.resource.Book;
import pl.pas.model.resource.Resource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@NoArgsConstructor
@RequestScoped
@Path("resource")
public class ResourceService {

    @Inject
    private ResourceManager resourceManager;

    @GET
    @Path("getAllResources")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Resource> getAllResources() {
        return resourceManager.getAllResources();
    }

    @GET
    @Path("getAllAvailableResources")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Resource> getAllAvailableResources() {
        return resourceManager.getAllAvailableResources();
    }

    @GET
    @Path("getAllBooks")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Book> getAllBooks() {
        return resourceManager.getAllBooks();
    }

    @GET
    @Path("getAllAudioBooks")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AudioBook> getAllAudioBooks() {
        return resourceManager.getAllAudioBooks();
    }

    @DELETE
    @Path("removeResource/{uuid}")
    public void removeResource(@PathParam("uuid") long uuid) {
        resourceManager.removeResource(uuid);
    }
}
