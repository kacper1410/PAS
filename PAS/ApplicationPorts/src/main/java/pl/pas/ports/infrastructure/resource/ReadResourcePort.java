package pl.pas.ports.infrastructure.resource;

import pl.pas.data.exceptions.NotFoundException;
import pl.pas.data.model.resource.AudioBook;
import pl.pas.data.model.resource.Book;
import pl.pas.data.model.resource.Resource;

import java.util.List;

public interface ReadResourcePort {
    Resource readResource(long uuid) throws NotFoundException;
    List<Resource> readAllResources();
    List<Book> readAllBooks();
    List<AudioBook> readAllAudioBooks();
}
