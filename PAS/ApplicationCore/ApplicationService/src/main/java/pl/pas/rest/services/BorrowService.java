package pl.pas.rest.services;

import lombok.NoArgsConstructor;
import pl.pas.data.exceptions.NotFoundException;
import pl.pas.data.exceptions.NotValidException;
import pl.pas.managers.BorrowManager;
import pl.pas.data.model.Borrow;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@NoArgsConstructor
@RequestScoped
@Path("borrow")
public class BorrowService {

    @Inject
    private BorrowManager borrowManager;

    @GET
    @Path("getAllBorrows")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Borrow> getAllBorrows() {
        return borrowManager.getAllBorrows();
    }

    @GET
    @Path("getAllBorrowsForClientByUuid/{uuid}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Borrow> getAllBorrowsForClientByUuid(@PathParam("uuid") long uuid) {
        try {
            return borrowManager.getAllBorrowsForClient(uuid);
        } catch (NotFoundException e) {
            throw new ClientErrorException("User not found", Response.Status.NOT_FOUND);
        }
    }

    @GET
    @Path("getAllBorrowsForResourceByUuid/{uuid}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Borrow> getAllBorrowsForResourceByUuid(@PathParam("uuid") long uuid) {
        try {
            return borrowManager.getAllBorrowsForResource(uuid);
        } catch (NotFoundException e) {
            throw new ClientErrorException("Resource not found", Response.Status.NOT_FOUND);
        }
    }

    @POST
    @Path("allocate/{resUuid}")
    public void allocate(@PathParam("resUuid") long resUuid, @Context SecurityContext securityContext) {
        try {
            borrowManager.borrowResource(resUuid, securityContext.getUserPrincipal().getName());
        } catch (NotFoundException e) {
            throw new ClientErrorException("Client or resource not found", Response.Status.NOT_FOUND);
        } catch (NotValidException e) {
            throw new ClientErrorException("Values not valid", Response.Status.NOT_ACCEPTABLE);
        }
    }

    @PUT
    @Path("endBorrow/{uuid}")
    public void endBorrow(@PathParam("uuid") long uuid) {
        try {
            borrowManager.endBorrow(borrowManager.getBorrow(uuid));
        } catch (NotValidException e) {
            //throw new ClientErrorException("Values not valid", Response.Status.NOT_ACCEPTABLE);
        } catch (NotFoundException e) {
            throw new ClientErrorException("Borrow not found", Response.Status.NOT_FOUND);
        }
    }
}
