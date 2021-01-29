package pl.pas.rest.services;

import lombok.NoArgsConstructor;
import pl.pas.managers.BorrowManager;
import pl.pas.model.Borrow;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@NoArgsConstructor
@RequestScoped
@Path("borrow")
public class BorrowService {

    @Inject
    private BorrowManager borrowManager;

    @GET
    @Path("getAllBorrows")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Borrow> getAllBorrows() {
        return borrowManager.getAllBorrows();
    }

    @GET
    @Path("getAllBorrowsForClientByUuid/{uuid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Borrow> getAllBorrowsForClientByUuid(@PathParam("uuid") long uuid) {
        return borrowManager.getAllBorrowsForClient(uuid);
    }

    @GET
    @Path("getAllBorrowsForResourceByUuid/{uuid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Borrow> getAllBorrowsForResourceByUuid(@PathParam("uuid") long uuid) {
        return borrowManager.getAllBorrowsForResource(uuid);
    }
}
