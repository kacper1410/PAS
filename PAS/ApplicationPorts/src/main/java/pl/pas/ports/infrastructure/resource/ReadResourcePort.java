package pl.pas.ports.infrastructure.resource;

import pl.pas.domain.exceptions.NotFoundException;
import pl.pas.domain.model.resource.AudioBook;
import pl.pas.domain.model.resource.Book;
import pl.pas.domain.model.resource.Resource;

import java.util.List;

public interface ReadResourcePort {
    Resource readResource(long uuid) throws NotFoundException;
    List<Resource> readAllResources();
    List<Book> readAllBooks();
    List<AudioBook> readAllAudioBooks();
}
