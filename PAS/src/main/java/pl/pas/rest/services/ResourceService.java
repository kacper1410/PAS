package pl.pas.rest.services;

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

    @GET
    @Path("getResourceById/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Resource getResourceById(@PathParam("id") long id) {
        return resourceManager.getResource(id);
    }

    @POST
    @Path("addBook")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void addBook(Book book) {
        resourceManager.addBook(book.getISBN(), book.getTitle(), book.getAuthor(), book.getPublishYear());
    }

    @POST
    @Path("addAudioBook")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void addAudioBook(AudioBook audioBook) {
        resourceManager.addAudioBook(audioBook.getISBN(), audioBook.getTitle(), audioBook.getAuthor(), audioBook.getLength());
    }

    @PUT
    @Path("updateBookById/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateBookById(@PathParam("id") long id, Book book) {
        resourceManager.updateBook(resourceManager.getResource(id), book.getISBN(), book.getTitle(), book.getAuthor(), book.getPublishYear());
    }

    @PUT
    @Path("updateAudioBookById/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateAudioBookById(@PathParam("id") long id, AudioBook book) {
        resourceManager.updateAudioBook(resourceManager.getResource(id), book.getISBN(), book.getTitle(), book.getAuthor(), book.getLength());
    }

    @DELETE
    @Path("removeResource/{uuid}")
    public void removeResource(@PathParam("uuid") long uuid) {
        resourceManager.removeResource(uuid);
    }
}
