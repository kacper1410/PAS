package pl.pas.data.model.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AudioBookEntity extends ResourceEntity {

    private int length;

    public AudioBookEntity() {
        super();
        this.length = 0;
    }

    public AudioBookEntity(long ISBN, String title, String author, int length) {
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
