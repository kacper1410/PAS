package pl.pas.controllers;

import lombok.Data;
import pl.pas.managers.ResourceManager;
import pl.pas.exceptions.NotFoundException;
import pl.pas.exceptions.NotValidException;
import pl.pas.model.resource.AudioBook;
import pl.pas.model.resource.Book;
import pl.pas.model.resource.Resource;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Data
@Named
@SessionScoped
public class ResourceController implements Serializable {

    @Inject
    private ResourceManager resourceManager;
    @Inject
    private IdentityUtils identityUtils;

    private Book newBook;
    private AudioBook newAudioBook;
    private Book currentBook;
    private AudioBook currentAudioBook;
    private long resourceId;
    private List<Resource> currentResources;
    private List<Book> currentBooks;
    private List<AudioBook> currentAudioBooks;
    private List<Resource> currentlyAvailableResources;

    public ResourceController() {
        newBook = new Book();
        newAudioBook = new AudioBook();
        resourceId = 0;
    }

    public int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public String processNewBook() {
        if (identityUtils.isEmployee()){
            try {
                this.resourceManager.addBook(newBook.getISBN(), newBook.getTitle(),
                        newBook.getAuthor(), newBook.getPublishYear());
            } catch (NotValidException e) {
                e.printStackTrace();
            }
            this.newBook = new Book();
        }
        updateList();
        return "main";
    }

    public String processNewAudioBook() {
        if (identityUtils.isEmployee()) {
            try {
                this.resourceManager.addAudioBook(newAudioBook.getISBN(), newAudioBook.getTitle(),
                        newAudioBook.getAuthor(), newAudioBook.getLength());
            } catch (NotValidException e) {
                e.printStackTrace();
            }
            this.newAudioBook = new AudioBook();
        }
        updateList();
        return "main";
    }

    public String cancelNewBook() {
        newBook = new Book();
        return "main";
    }

    public String cancelNewAudioBook() {
        newAudioBook = new AudioBook();
        return "main";
    }

    public String resourceList() {
        return "resources";
    }

    public String removeResource(Resource resource) {
        if (identityUtils.isEmployee()) {
            try {
                resourceManager.removeResource(resource.getResourceId());
            } catch (NotValidException ignored) {

            }
        }
        updateList();
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true";
    }

    public String updateList() {
        initList();
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true";
    }

    @PostConstruct
    public void initList() {
        currentResources = resourceManager.getAllResources();
        currentBooks = resourceManager.getAllBooks();
        currentAudioBooks = resourceManager.getAllAudioBooks();
        currentlyAvailableResources = resourceManager.getAllAvailableResources();
    }

    public String search(long uuid) {
        Resource resource = null;
        try {
            resource = resourceManager.getResource(uuid);
        } catch (NotFoundException ignored) {

        }
        if (resource == null) {
            String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
            return viewId + "?faces-redirect=true";
        }
        if (resource instanceof Book) {
            currentBook = (Book) resource;
            return "book";
        } else {
            currentAudioBook = (AudioBook) resource;
            return "audiobook";
        }
    }

    public String removeCurrentBook() {
        if (identityUtils.isEmployee()) {
            removeResource(currentBook);
        }
        currentBook = new Book();
        return resourceList();
    }

    public String removeCurrentAudioBook() {
        if (identityUtils.isEmployee()) {
            removeResource(currentAudioBook);
        }
        currentAudioBook = new AudioBook();
        return resourceList();
    }

    public String updateBook() {
        if (identityUtils.isEmployee()) {
            try {
                resourceManager.updateBook(currentBook, currentBook.getISBN(), currentBook.getTitle(),
                        currentBook.getAuthor(), currentBook.getPublishYear());
            } catch (NotValidException e) {
                e.printStackTrace();
            }
        }
        updateList();
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true";
    }

    public String updateAudioBook() {
        if (identityUtils.isEmployee()) {
            try {
                resourceManager.updateAudioBook(currentAudioBook, currentAudioBook.getISBN(), currentAudioBook.getTitle(),
                        currentAudioBook.getAuthor(), currentAudioBook.getLength());
            } catch (NotValidException e) {
                e.printStackTrace();
            }
        }
        updateList();
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true";
    }
}
