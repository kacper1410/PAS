package pl.pas.controllers;

import pl.pas.managers.ResourceManager;
import pl.pas.model.resource.AudioBook;
import pl.pas.model.resource.Book;
import pl.pas.model.resource.Resource;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class ResourceController implements Serializable {

    @Inject
    private ResourceManager resourceManager;

    private Book newBook;
    private AudioBook newAudioBook;

    private List<Resource> currentResources;
    private List<Book> currentBooks;
    private List<AudioBook> currentAudioBooks;

    public ResourceController() {
        newBook = new Book();
        newAudioBook = new AudioBook();
    }

    public int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public String processNewBook() {
        this.resourceManager.addBook(newBook.getISBN(), newBook.getTitle(), newBook.getAuthor(), newBook.getPublishYear());
        this.newBook = new Book();
        updateList();
        return "main";
    }

    public String processNewAudioBook() {
        this.resourceManager.addAudioBook(newAudioBook.getISBN(), newAudioBook.getTitle(), newAudioBook.getAuthor(), newAudioBook.getLength());
        this.newAudioBook = new AudioBook();
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

    public Book getNewBook() {
        return newBook;
    }

    public void setNewBook(Book newBook) {
        this.newBook = newBook;
    }

    public AudioBook getNewAudioBook() {
        return newAudioBook;
    }

    public void setNewAudioBook(AudioBook newAudioBook) {
        this.newAudioBook = newAudioBook;
    }

    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    public List<Resource> getAllResource() {
        return currentResources;
    }

    public List<AudioBook> getAllAudioBooks() {
        return currentAudioBooks;
    }

    public List<Book> getAllBooks() {
        return currentBooks;
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
        currentResources = resourceManager.getAllResources();
        currentBooks = resourceManager.getAllBooks();
        currentAudioBooks = resourceManager.getAllAudioBooks();
    }
}
