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

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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
    public User getUser(@PathParam("uuid") long uuid) {
        try {
            return userManager.getUser(uuid);
        } catch (UserNotFoundException e) {
            throw new ClientErrorException("User not found", Response.Status.NOT_FOUND);
        } catch (Exception e) {
            throw new ClientErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("getUserByLogin/{login}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User getUser(@PathParam("login") String login) {
        try {
            return userManager.getUser(login);
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
    @Path("addAdministrator")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
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
    @Path("addClient")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
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
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateEmployee(@PathParam("id") long id, Administrator administrator) {
        try {
            userManager.updateUser(id, administrator);
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
    public void updateEmployee(@PathParam("id") long id, Employee employee) {
        try {
            userManager.updateUser(id, employee);
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
    public void updateClient(@PathParam("id") long id, Client client) {
        try {
            userManager.updateClient(id, client);
        } catch (UserNotFoundException e) {
            throw new ClientErrorException("User not found", Response.Status.NOT_FOUND);
        } catch (NotValidException e) {
            throw new ClientErrorException("Values not valid", Response.Status.NOT_ACCEPTABLE);
        } catch (Exception e) {
            throw new ClientErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
