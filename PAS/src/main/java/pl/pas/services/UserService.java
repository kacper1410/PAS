package pl.pas.services;


import lombok.NoArgsConstructor;
import pl.pas.managers.UserManager;
import pl.pas.model.user.Administrator;
import pl.pas.model.user.Client;
import pl.pas.model.user.Employee;
import pl.pas.model.user.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
        return userManager.getAllClients();
    }

    @GET
    @Path("getAllActiveClients")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Client> getAllActiveClients() {
        return userManager.getAllActiveClients();
    }

    @GET
    @Path("getAllEmployees")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Employee> getAllEmployees() {
        return userManager.getAllEmployees();
    }

    @GET
    @Path("getAllAdministrators")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Administrator> getAllAdministrators() {
        return userManager.getAllAdministrators();
    }

    @GET
    @Path("getUserById/{uuid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User getUser(@PathParam("uuid") long uuid) {
        return userManager.getUser(uuid);
    }

    @GET
    @Path("getUserByLogin/{login}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User getUser(@PathParam("login") String login) {
        return userManager.getUser(login);
    }

    // TODO Post method should response something
    @POST
    @Path("addEmployee")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void addEmployee(Employee employee) {
        userManager.addEmployee(employee.getLogin(), employee.getName(), employee.getLastName());
    }

    @POST
    @Path("addAdministrator")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void addAdministrator(Administrator administrator) {
        userManager.addAdministrator(administrator.getLogin(), administrator.getName(), administrator.getLastName());
    }

    @POST
    @Path("addClient")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void addClient(Client client) {
        userManager.addClient(client.getLogin(), client.getName(), client.getLastName(), client.getAge());
    }

    @PUT
    @Path("updateAdministrator/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateEmployee(@PathParam("id") long id, Administrator administrator) {
        userManager.updateUser(userManager.getUser(id), administrator.getLogin(), administrator.getName(), administrator.getLastName());
    }

    @PUT
    @Path("updateEmployee/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateEmployee(@PathParam("id") long id, Employee employee) {
        userManager.updateUser(userManager.getUser(id), employee.getLogin(), employee.getName(), employee.getLastName());
    }

    @PUT
    @Path("updateClient/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateClient(@PathParam("id") long id, Client client) {
        userManager.updateClient(userManager.getUser(id), client.getLogin(), client.getName(), client.getLastName(), client.getAge());
    }
}
