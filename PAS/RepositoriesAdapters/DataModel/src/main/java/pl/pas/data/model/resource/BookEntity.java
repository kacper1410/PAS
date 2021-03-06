package pl.pas.data.model.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookEntity extends ResourceEntity {

    private int publishYear;

    public BookEntity() {
        super();
        this.publishYear = 0;
    }

    public BookEntity(long ISBN, String title, String author, int publishYear) {
        super(ISBN, title, author);
        this.publishYear = publishYear;
    }

    @Override
    public String toString() {
        return super.toString() + " Book{" +
                "publishYear=" + publishYear +
                '}';
    }
}
