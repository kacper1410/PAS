package pl.pas.repositories.interfaces;

import pl.pas.model.resource.AudioBook;
import pl.pas.model.resource.Book;
import pl.pas.model.resource.Resource;
import java.util.List;
import java.util.UUID;

public interface IResourceRepository {
    // TODO
    // UUID in addResource method

    boolean addResource(Resource resource);
    Resource getResource(UUID uuid);
    List<Resource> getAllResources();
    void updateResource(UUID uuid, Resource newResource);
    boolean deleteResource(UUID uuid);
    List<Book> getAllBooks();
    List<AudioBook> getAllAudioBooks();
}
