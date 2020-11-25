package pl.pas.controllers;

import pl.pas.managers.BorrowManager;
import pl.pas.model.resource.Resource;
import pl.pas.model.user.Client;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "borrowController")
@SessionScoped
public class BorrowController implements Serializable {

    private BorrowManager borrowManager;
    private Client currentClient;
    private Resource selectedResource;

    public BorrowController() {}

    public void setCurrentClient(Client client) {
        this.currentClient = client;
    }

    public void setSelectedResource(Resource resource) {
        this.selectedResource = resource;
    }

    void rentResource() {
        borrowManager.borrowResource(selectedResource.getResourceId(), currentClient.getUserId());
    }



}
