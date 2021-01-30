package pl.pas.rest.services;

import lombok.NoArgsConstructor;
import pl.pas.exceptions.NotValidException;
import pl.pas.exceptions.UserAlreadyExistException;
import pl.pas.exceptions.UserNotFoundException;
import pl.pas.managers.UserManager;
import pl.pas.model.user.Administrator;
import pl.pas.model.user.Client;
import pl.pas.model.user.Employee;
import pl.pas.model.user.User;
import pl.pas.rest.filters.SignatureValidatorFilterBinding;
import pl.pas.rest.IdentitySignVerifier;

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
@Path("user")
public class UserService {

    @Inject
    private UserManager userManager;

    @GET
    @Path("getAllClients")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Client> getAllClients() {
        try {
            return userManager.getAllClients();
        } catch (Exception e) {
            throw new ClientErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("getAllActiveClients")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Client> getAllActiveClients() {
        try {
            return userManager.getAllActiveClients();
        } catch (Exception e) {
            throw new ClientErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("getAllEmployees")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Employee> getAllEmployees() {
        try {
            return userManager.getAllEmployees();
        } catch (Exception e) {
            throw new ClientErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("getAllAdministrators")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Administrator> getAllAdministrators() {
        try {
            return userManager.getAllAdministrators();
        } catch (Exception e) {
            throw new ClientErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("getUserById/{uuid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getUser(@PathParam("uuid") long uuid) {
        try {
            User user = userManager.getUser(uuid);

            return Response.ok()
                    .entity(user)
                    .tag(IdentitySignVerifier.calculateEntitySignature(user))
                    .build();
        } catch (UserNotFoundException e) {
            throw new ClientErrorException("User not found", Response.Status.NOT_FOUND);
        } catch (Exception e) {
            throw new ClientErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("getUserByLogin/{login}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getUser(@PathParam("login") String login) {
        try {
            User user = userManager.getUser(login);

            return Response.ok()
                    .entity(user)
                    .tag(IdentitySignVerifier.calculateEntitySignature(user))
                    .build();
        } catch (UserNotFoundException e) {
            throw new ClientErrorException("User not found", Response.Status.NOT_FOUND);
        } catch (Exception e) {
            throw new ClientErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Path("addEmployee")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void addEmployee(Employee employee) {
        try {
            userManager.addEmployee(employee.getLogin(), employee.getName(), employee.getLastName());
        } catch (UserAlreadyExistException e) {
            throw new ClientErrorException("User exist", Response.Status.CONFLICT);
        } catch (NotValidException e) {
            throw new ClientErrorException("Values not valid", Response.Status.NOT_ACCEPTABLE);
        } catch (Exception e) {
            throw new ClientErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Path("addAdministrator")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void addAdministrator(Administrator administrator) {
        try {
            userManager.addAdministrator(administrator.getLogin(), administrator.getName(), administrator.getLastName());
        } catch (UserAlreadyExistException e) {
            throw new ClientErrorException("User exist", Response.Status.CONFLICT);
        } catch (NotValidException e) {
            throw new ClientErrorException("Values not valid", Response.Status.NOT_ACCEPTABLE);
        } catch (Exception e) {
            throw new ClientErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Path("addClient")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void addClient(Client client) {
        try {
            userManager.addClient(client.getLogin(), client.getName(), client.getLastName(), client.getAge());
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
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @SignatureValidatorFilterBinding
    public void updateAdministrator(@PathParam("id") long id, @HeaderParam("If-match") @NotNull @NotEmpty String ifMatch, Administrator administrator) {
        try {
            if (!IdentitySignVerifier.isEntitySignatureValid(ifMatch, administrator)) {
                throw new ClientErrorException("If-match not valid", Response.Status.PRECONDITION_FAILED);
            }
            userManager.updateUser(userManager.getUser(id), administrator.getLogin(), administrator.getName(), administrator.getLastName());
        } catch (UserNotFoundException e) {
            throw new ClientErrorException("User not found", Response.Status.NOT_FOUND);
        } catch (NotValidException e) {
            throw new ClientErrorException("Values not valid", Response.Status.NOT_ACCEPTABLE);
        } catch (Exception e) {
            throw new ClientErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("updateEmployee/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @SignatureValidatorFilterBinding
    public void updateEmployee(@PathParam("id") long id, @HeaderParam("If-match") @NotNull @NotEmpty String ifMatch, Employee employee) {
        try {
            if (!IdentitySignVerifier.isEntitySignatureValid(ifMatch, employee)) {
                throw new ClientErrorException("If-match not valid", Response.Status.PRECONDITION_FAILED);
            }
            userManager.updateUser(userManager.getUser(id), employee.getLogin(), employee.getName(), employee.getLastName());
        } catch (UserNotFoundException e) {
            throw new ClientErrorException("User not found", Response.Status.NOT_FOUND);
        } catch (NotValidException e) {
            throw new ClientErrorException("Values not valid", Response.Status.NOT_ACCEPTABLE);
        } catch (Exception e) {
            throw new ClientErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("updateClient/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @SignatureValidatorFilterBinding
    public void updateClient(@PathParam("id") long id, @HeaderParam("If-match") @NotNull @NotEmpty String ifMatch, Client client) {
        try {
            if (!IdentitySignVerifier.isEntitySignatureValid(ifMatch, client)) {
                throw new ClientErrorException("If-match not valid", Response.Status.PRECONDITION_FAILED);
            }
            userManager.updateClient(userManager.getUser(id), client.getLogin(), client.getName(), client.getLastName(), client.getAge());
        } catch (UserNotFoundException e) {
            throw new ClientErrorException("User not found", Response.Status.NOT_FOUND);
        } catch (NotValidException e) {
            throw new ClientErrorException("Values not valid", Response.Status.NOT_ACCEPTABLE);
        } catch (Exception e) {
            throw new ClientErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
