package pl.pas.domain.model.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book extends Resource {
    private int publishYear;

    public Book() {
        super();
        this.publishYear = 0;
    }

    public Book(long ISBN, String title, String author, int publishYear) {
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
