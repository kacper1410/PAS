package pl.pas.data.model.resource;

import lombok.Data;

@Data
public abstract class ResourceEntity {

    private long ISBN;
    private String title;
    private String author;
    private boolean available;
    private long resourceId;

    public ResourceEntity() {
        this.ISBN = 0;
        this.title = "";
        this.author = "";
        this.resourceId = 0;
    }

    public ResourceEntity(long ISBN, String title, String author) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.available = true;
        this.resourceId = 0;
    }
}
