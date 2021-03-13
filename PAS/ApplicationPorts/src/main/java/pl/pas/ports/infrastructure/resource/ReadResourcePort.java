package pl.pas.ports.infrastructure.resource;

import pl.pas.exceptions.NotFoundException;
import pl.pas.model.resource.AudioBook;
import pl.pas.model.resource.Book;
import pl.pas.model.resource.Resource;

import java.util.List;

public interface ReadResourcePort {
    Resource readResource(long uuid) throws NotFoundException;
    List<Resource> readAllResources();
    List<Book> getAllBooks();
    List<AudioBook> getAllAudioBooks();
}
