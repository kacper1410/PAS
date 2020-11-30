package pl.pas.repositories.interfaces;

import pl.pas.model.resource.AudioBook;
import pl.pas.model.resource.Book;
import pl.pas.model.resource.Resource;

import java.util.List;

public interface IResourceRepository {
    boolean addResource(Resource resource);
    Resource getResource(long uuid);
    List<Resource> getAllResources();
    void updateResource(long uuid, Resource newResource);
    boolean deleteResource(long uuid);
    List<Book> getAllBooks();
    List<AudioBook> getAllAudioBooks();
}
