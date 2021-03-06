package pl.pas.rest.services;

import lombok.NoArgsConstructor;
import pl.pas.domain.exceptions.NotValidException;
import pl.pas.domain.exceptions.UserAlreadyExistException;
import pl.pas.domain.exceptions.NotFoundException;
import pl.pas.managers.UserManager;
import pl.pas.rest.IdentitySignVerifier;
import pl.pas.rest.filters.SignatureValidatorFilterBinding;
import pl.pas.domain.model.user.Administrator;
import pl.pas.domain.model.user.Client;
import pl.pas.domain.model.user.Employee;
import pl.pas.domain.model.user.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@NoArgsConstructor
@RequestScoped
@Path("user")
public class UserService {

    @Inject
    private UserManager userManager;

    @GET
    @Path("allClients")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Client> getAllClients() {
        try {
            return userManager.getAllClients();
        } catch (Exception e) {
            throw new ClientErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("allActiveClients")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Client> getAllActiveClients() {
        try {
            return userManager.getAllActiveClients();
        } catch (Exception e) {
            throw new ClientErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("allEmployees")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Employee> getAllEmployees() {
        try {
            return userManager.getAllEmployees();
        } catch (Exception e) {
            throw new ClientErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("allAdministrators")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Administrator> getAllAdministrators() {
        try {
            return userManager.getAllAdministrators();
        } catch (Exception e) {
            throw new ClientErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("userById/{uuid}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUser(@PathParam("uuid") long uuid) {
        try {
            User user = userManager.getUser(uuid);

            return Response.ok()
                    .entity(user)
                    .tag(IdentitySignVerifier.calculateEntitySignature(user))
                    .build();
        } catch (NotFoundException e) {
            throw new ClientErrorException("User not found", Response.Status.NOT_FOUND);
        } catch (Exception e) {
            throw new ClientErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("userByLogin/{login}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUser(@PathParam("login") String login) {
        try {
            User user = userManager.getUser(login);

            return Response.ok()
                    .entity(user)
                    .tag(IdentitySignVerifier.calculateEntitySignature(user))
                    .build();
        } catch (NotFoundException e) {
            throw new ClientErrorException("User not found", Response.Status.NOT_FOUND);
        } catch (Exception e) {
            throw new ClientErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Path("employee")
    @Consumes({MediaType.APPLICATION_JSON})
    public void addEmployee(Employee employee) {
        try {
            userManager.addEmployee(employee);
        } catch (UserAlreadyExistException e) {
            throw new ClientErrorException("User exist", Response.Status.CONFLICT);
        } catch (NotValidException e) {
            throw new ClientErrorException("Values not valid", Response.Status.NOT_ACCEPTABLE);
        } catch (Exception e) {
            throw new ClientErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Path("administrator")
    @Consumes({MediaType.APPLICATION_JSON})
    public void addAdministrator(Administrator administrator) {
        try {
            userManager.addAdministrator(administrator);
        } catch (UserAlreadyExistException e) {
            throw new ClientErrorException("User exist", Response.Status.CONFLICT);
        } catch (NotValidException e) {
            throw new ClientErrorException("Values not valid", Response.Status.NOT_ACCEPTABLE);
        } catch (Exception e) {
            throw new ClientErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Path("client")
    @Consumes({MediaType.APPLICATION_JSON})
    public void addClient(Client client) {
        try {
            userManager.addClient(client);
        } catch (UserAlreadyExistException e) {
            throw new ClientErrorException("User exist", Response.Status.CONFLICT);
        } catch (NotValidException e) {
            throw new ClientErrorException("Values not valid", Response.Status.NOT_ACCEPTABLE);
        } catch (Exception e) {
            throw new ClientErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("updateAdministrator/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @SignatureValidatorFilterBinding
    public void updateAdministrator(@PathParam("id") long id, @HeaderParam("If-match") @NotNull @NotEmpty String ifMatch, Administrator administrator) {
        if (!IdentitySignVerifier.isEntitySignatureValid(ifMatch, id)) {
            throw new ClientErrorException("If-match not valid", Response.Status.PRECONDITION_FAILED);
        }

        try {
            userManager.updateUser(id, administrator);
        } catch (NotFoundException e) {
            throw new ClientErrorException("User not found", Response.Status.NOT_FOUND);
        } catch (NotValidException e) {
            throw new ClientErrorException("Values not valid", Response.Status.NOT_ACCEPTABLE);
        } catch (Exception e) {
            throw new ClientErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("employee/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @SignatureValidatorFilterBinding
    public void updateEmployee(@PathParam("id") long id, @HeaderParam("If-match") @NotNull @NotEmpty String ifMatch, Employee employee) {
        if (!IdentitySignVerifier.isEntitySignatureValid(ifMatch, id)) {
            throw new ClientErrorException("If-match not valid", Response.Status.PRECONDITION_FAILED);
        }

        try {
            userManager.updateUser(id, employee);
        } catch (NotFoundException e) {
            throw new ClientErrorException("User not found", Response.Status.NOT_FOUND);
        } catch (NotValidException e) {
            throw new ClientErrorException("Values not valid", Response.Status.NOT_ACCEPTABLE);
        } catch (Exception e) {
            throw new ClientErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("client/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @SignatureValidatorFilterBinding
    public void updateClient(@PathParam("id") long id, @HeaderParam("If-match") @NotNull @NotEmpty String ifMatch, Client client) {
        if (!IdentitySignVerifier.isEntitySignatureValid(ifMatch, id)) {
            throw new ClientErrorException("If-match not valid", Response.Status.PRECONDITION_FAILED);
        }

        try {
            userManager.updateClient(id, client);
        } catch (NotFoundException e) {
            throw new ClientErrorException("User not found", Response.Status.NOT_FOUND);
        } catch (NotValidException e) {
            throw new ClientErrorException("Values not valid", Response.Status.NOT_ACCEPTABLE);
        } catch (Exception e) {
            throw new ClientErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("/profile")
    @Produces({MediaType.APPLICATION_JSON})
    public Response profile(@Context SecurityContext securityContext) {
        User user;
        try {
            user = userManager.getUser(securityContext.getUserPrincipal().getName());
        } catch (NotFoundException e) {
            throw new ClientErrorException("User not found", Response.Status.NOT_FOUND);
        }
        return Response.ok()
                .entity(user)
                .tag(IdentitySignVerifier.calculateEntitySignature(user))
                .build();
    }

    @PUT
    @Path("/deactivate/{uuid}")
    public void deactivate(@PathParam("uuid") long id) {
        try {
            userManager.deactivateUser(userManager.getUser(id));
        } catch (NotValidException ignored) {
        } catch (NotFoundException e) {
            throw new ClientErrorException("User not found", Response.Status.NOT_FOUND);
        }
    }

    @PUT
    @Path("/activate/{uuid}")
    public void activate(@PathParam("uuid") long id) {
        try {
            userManager.activateUser(userManager.getUser(id));
        } catch (NotValidException ignored) {
        } catch (NotFoundException e) {
            throw new ClientErrorException("User not found", Response.Status.NOT_FOUND);
        }
    }
}
