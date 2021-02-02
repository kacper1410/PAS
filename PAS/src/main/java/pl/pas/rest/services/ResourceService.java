package pl.pas.rest.services;

import lombok.NoArgsConstructor;
import pl.pas.exceptions.NotFoundException;
import pl.pas.exceptions.NotValidException;
import pl.pas.managers.ResourceManager;
import pl.pas.model.resource.AudioBook;
import pl.pas.model.resource.Book;
import pl.pas.model.resource.Resource;
import pl.pas.rest.IdentitySignVerifier;
import pl.pas.rest.filters.SignatureValidatorFilterBinding;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@NoArgsConstructor
@RequestScoped
@Path("resource")
public class ResourceService {

    @Inject
    private ResourceManager resourceManager;

    @GET
    @Path("getAllResources")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Resource> getAllResources() {
        return resourceManager.getAllResources();
    }

    @GET
    @Path("getAllAvailableResources")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Resource> getAllAvailableResources() {
        return resourceManager.getAllAvailableResources();
    }

    @GET
    @Path("getAllBooks")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Book> getAllBooks() {
        return resourceManager.getAllBooks();
    }

    @GET
    @Path("getAllAudioBooks")
    @Produces({MediaType.APPLICATION_JSON})
    public List<AudioBook> getAllAudioBooks() {
        return resourceManager.getAllAudioBooks();
    }

    @GET
    @Path("getResourceById/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getResourceById(@PathParam("id") long id) {
        try {
            Resource resource = resourceManager.getResource(id);

            return Response.ok()
                    .entity(resource)
                    .tag(IdentitySignVerifier.calculateEntitySignature(resource))
                    .build();
        } catch (NotFoundException e) {
            throw new ClientErrorException("Resource not found", Response.Status.NOT_FOUND);
        }
    }

    @POST
    @Path("addBook")
    @Consumes({MediaType.APPLICATION_JSON})
    public void addBook(Book book) {
        try {
            resourceManager.addBook(book.getISBN(), book.getTitle(), book.getAuthor(), book.getPublishYear());
        } catch (NotValidException e) {
            throw new ClientErrorException("Values not valid", Response.Status.NOT_ACCEPTABLE);
        }
    }

    @POST
    @Path("addAudioBook")
    @Consumes({MediaType.APPLICATION_JSON})
    public void addAudioBook(AudioBook audioBook) {
        try {
            resourceManager.addAudioBook(audioBook.getISBN(), audioBook.getTitle(), audioBook.getAuthor(), audioBook.getLength());
        } catch (NotValidException e) {
            throw new ClientErrorException("Values not valid", Response.Status.NOT_ACCEPTABLE);
        }
    }

    @PUT
    @Path("updateBookById/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @SignatureValidatorFilterBinding
    public void updateBookById(@PathParam("id") long id, @HeaderParam("If-match") @NotNull @NotEmpty String ifMatch, Book book) {
        if (!IdentitySignVerifier.isEntitySignatureValid(ifMatch, id)) {
            throw new ClientErrorException("If-match not valid", Response.Status.PRECONDITION_FAILED);
        }

        try {
            resourceManager.updateBook(resourceManager.getResource(id), book.getISBN(), book.getTitle(), book.getAuthor(), book.getPublishYear());
        } catch (NotValidException e) {
            throw new ClientErrorException("Values not valid", Response.Status.NOT_ACCEPTABLE);
        } catch (NotFoundException e) {
            throw new ClientErrorException("Resource not found", Response.Status.NOT_FOUND);
        }
    }

    @PUT
    @Path("updateAudioBookById/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @SignatureValidatorFilterBinding
    public void updateAudioBookById(@PathParam("id") long id, @HeaderParam("If-match") @NotNull @NotEmpty String ifMatch, AudioBook book) {
        if (!IdentitySignVerifier.isEntitySignatureValid(ifMatch, id)) {
            throw new ClientErrorException("If-match not valid", Response.Status.PRECONDITION_FAILED);
        }

        try {
            resourceManager.updateAudioBook(resourceManager.getResource(id), book.getISBN(), book.getTitle(), book.getAuthor(), book.getLength());
        } catch (NotValidException e) {
            throw new ClientErrorException("Values not valid", Response.Status.NOT_ACCEPTABLE);
        } catch (NotFoundException e) {
            throw new ClientErrorException("Resource not found", Response.Status.NOT_FOUND);
        }
    }

    @DELETE
    @Path("removeResource/{uuid}")
    public void removeResource(@PathParam("uuid") long uuid) {
        try {
            resourceManager.removeResource(uuid);
        } catch (NotValidException e) {
            throw new ClientErrorException("Values not valid", Response.Status.NOT_ACCEPTABLE);
        }
    }
}
