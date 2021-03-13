package pl.pas.ports.infrastructure.resource;

import pl.pas.model.resource.AudioBook;
import pl.pas.model.resource.Book;

public interface CreateResourcePort {
    void createBook(Book book);
    void createAudioBook(AudioBook book);
}
