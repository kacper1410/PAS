package pl.pas.controllers;

import pl.pas.managers.ResourceManager;
import pl.pas.model.resource.Resource;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ResourceController implements Serializable {

    private List<Resource> currentResource;

    @Inject
    private ResourceManager resourceManager;

    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    public List<Resource> getAllResource() {
        return currentResource;
    }

    public String resourceList() {
        return "resourceList";
    }

    public String removeResource(Resource resource) {
        resourceManager.removeResource(resource.getResourceId());
        updateList();
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true";
    }

    @PostConstruct
    public void updateList() {
        currentResource = resourceManager.getAllResources();
    }
}
