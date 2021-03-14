package pl.pas.data.model.resource;

import lombok.*;
import pl.pas.data.model.SignableEntity;

import javax.json.bind.annotation.JsonbTransient;

@Data
public abstract class Resource implements SignableEntity {

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

    @JsonbTransient
    public long getSignablePayload() {
        return resourceId;
    }
}
