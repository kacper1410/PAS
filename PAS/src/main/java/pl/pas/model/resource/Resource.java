package pl.pas.model.resource;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public abstract class Resource {
    private long ISBN;
    private String title;
    private String author;
    private boolean available;
    private long resourceId;

    public Resource() {
        this.ISBN = 0;
        this.title = "";
        this.author = "";
        this.resourceId = 0;
    }

    public Resource(long ISBN, String title, String author) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.available = true;
        this.resourceId = 0;
    }
}
