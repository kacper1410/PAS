package pl.pas.repositories.interfaces;

import pl.pas.data.model.exceptions.NotFoundExceptionEntity;
import pl.pas.data.model.resource.AudioBookEntity;
import pl.pas.data.model.resource.BookEntity;
import pl.pas.data.model.resource.ResourceEntity;

import java.util.List;

public interface IResourceRepository {
    void addResource(ResourceEntity resource);
    ResourceEntity getResource(long uuid) throws NotFoundExceptionEntity;
    List<ResourceEntity> getAllResources();
    void updateResource(long uuid, ResourceEntity newResource);
    void deleteResource(long uuid);
    List<BookEntity> getAllBooks();
    List<AudioBookEntity> getAllAudioBooks();
}
