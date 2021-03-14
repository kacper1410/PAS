package pl.pas.data.mappers.resource;

import pl.pas.data.model.resource.AudioBookEntity;
import pl.pas.data.model.resource.BookEntity;
import pl.pas.data.model.resource.ResourceEntity;
import pl.pas.domain.model.resource.AudioBook;
import pl.pas.domain.model.resource.Book;
import pl.pas.domain.model.resource.Resource;

public class ResourceEntityMapper {

    public static Resource resourceEntityToResource(ResourceEntity resourceEntity) {
        if (resourceEntity instanceof BookEntity) {
            Book book = new Book();
            book.setResourceId(resourceEntity.getResourceId());
            book.setISBN(resourceEntity.getISBN());
            book.setTitle(resourceEntity.getTitle());
            book.setAuthor(resourceEntity.getAuthor());
            book.setAvailable(resourceEntity.isAvailable());
            book.setPublishYear(((BookEntity) resourceEntity).getPublishYear());
            return book;
        } else if (resourceEntity instanceof AudioBookEntity) {
            AudioBook audioBook = new AudioBook();
            audioBook.setResourceId(resourceEntity.getResourceId());
            audioBook.setISBN(resourceEntity.getISBN());
            audioBook.setTitle(resourceEntity.getTitle());
            audioBook.setAuthor(resourceEntity.getAuthor());
            audioBook.setAvailable(resourceEntity.isAvailable());
            audioBook.setLength(((AudioBookEntity) resourceEntity).getLength());
            return audioBook;
        } else {
            return null;
        }
    }

    public static ResourceEntity resourceToResourceEntity(Resource resource) {
        if (resource instanceof Book) {
            BookEntity bookEntity = new BookEntity();
            bookEntity.setResourceId(resource.getResourceId());
            bookEntity.setISBN(resource.getISBN());
            bookEntity.setTitle(resource.getTitle());
            bookEntity.setAuthor(resource.getAuthor());
            bookEntity.setAvailable(resource.isAvailable());
            bookEntity.setPublishYear(((Book) resource).getPublishYear());
            return bookEntity;
        } else if (resource instanceof AudioBook) {
            AudioBookEntity audioBookEntity = new AudioBookEntity();
            audioBookEntity.setResourceId(resource.getResourceId());
            audioBookEntity.setISBN(resource.getISBN());
            audioBookEntity.setTitle(resource.getTitle());
            audioBookEntity.setAuthor(resource.getAuthor());
            audioBookEntity.setAvailable(resource.isAvailable());
            audioBookEntity.setLength(((AudioBook) resource).getLength());
            return audioBookEntity;
        } else {
            return null;
        }
    }
}
