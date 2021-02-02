package pl.pas.rest.services;

import lombok.NoArgsConstructor;
import pl.pas.exceptions.NotFoundException;
import pl.pas.managers.BorrowManager;
import pl.pas.model.Borrow;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
}
