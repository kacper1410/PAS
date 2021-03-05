package pl.pas.repositories.interfaces;

import pl.pas.exceptions.NotFoundException;
import pl.pas.model.resource.AudioBook;
import pl.pas.model.resource.Book;
import pl.pas.model.resource.Resource;

import java.util.List;

public interface IResourceRepository {
    void addResource(Resource resource);
    Resource getResource(long uuid) throws NotFoundException;
    List<Resource> getAllResources();
    void updateResource(long uuid, Resource newResource);
    void deleteResource(long uuid);
    List<Book> getAllBooks();
    List<AudioBook> getAllAudioBooks();
}
