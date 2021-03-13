package pl.pas.adapters.repositories;

import pl.pas.data.model.exceptions.NotFoundExceptionEntity;
import pl.pas.exceptions.NotFoundException;
import pl.pas.mappers.resource.ResourceEntityMapper;
import pl.pas.model.resource.AudioBook;
import pl.pas.model.resource.Book;
import pl.pas.model.resource.Resource;
import pl.pas.ports.infrastructure.resource.CreateResourcePort;
import pl.pas.ports.infrastructure.resource.DeleteResourcePort;
import pl.pas.ports.infrastructure.resource.ReadResourcePort;
import pl.pas.ports.infrastructure.resource.UpdateResourcePort;
import pl.pas.repositories.ResourceRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class ResourceRepositoryAdapter implements CreateResourcePort, UpdateResourcePort, DeleteResourcePort, ReadResourcePort {

    @Inject
    ResourceRepository resourceRepository;

    @Override
    public void createResource(Resource resource) {
        resourceRepository.addResource(ResourceEntityMapper.resourceToResourceEntity(resource));
    }

    @Override
    public void deleteResource(long id) {
        resourceRepository.deleteResource(id);
    }

    @Override
    public Resource readResource(long uuid) throws NotFoundException {
        try {
            return ResourceEntityMapper.resourceEntityToResource(resourceRepository.getResource(uuid));
        } catch (NotFoundExceptionEntity notFoundExceptionEntity) {
            throw new NotFoundException();
        }
    }

    @Override
    public List<Resource> readAllResources() {
        return resourceRepository.getAllResources()
                .stream()
                .map(ResourceEntityMapper::resourceEntityToResource)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> readAllBooks() {
        return resourceRepository.getAllBooks()
                .stream()
                .map(bookEntity -> (Book) ResourceEntityMapper.resourceEntityToResource(bookEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<AudioBook> readAllAudioBooks() {
        return resourceRepository.getAllAudioBooks()
                .stream()
                .map(audioBookEntity -> (AudioBook) ResourceEntityMapper.resourceEntityToResource(audioBookEntity))
                .collect(Collectors.toList());
    }

    @Override
    public void updateResource(long id, Resource newResource) {
        resourceRepository.updateResource(id, ResourceEntityMapper.resourceToResourceEntity(newResource));
    }
}
