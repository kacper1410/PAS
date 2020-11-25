package pl.pas.controllers;

import pl.pas.managers.ResourceManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ApplicationScoped
public class ResourceController implements Serializable {

    @Inject
    private ResourceManager resourceManager;

    public ResourceManager getResourceManager() {
        return resourceManager;
    }
}
