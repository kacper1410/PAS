package pl.pas.controllers;

import pl.pas.managers.ResourceManager;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.util.UUID;

@Named(value = "resourceController")
@SessionScoped
public class ResourceController {

    @EJB
    private ResourceManager resourceManager;

    public ResourceController() {}

    public void getResource(UUID uuid) {
        resourceManager.getResource(uuid);
    }


}
