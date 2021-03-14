package pl.pas.domain.model.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AudioBook extends Resource {
    private int length;

    public AudioBook() {
        super();
        this.length = 0;
    }

    public AudioBook(long ISBN, String title, String author, int length) {
        super(ISBN, title, author);
        this.length = length;
    }

    @Override
    public String toString() {
        return super.toString() + " AudioBook{" +
                "length=" + length +
                '}';
    }
}
