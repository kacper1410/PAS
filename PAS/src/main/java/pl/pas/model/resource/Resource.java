package pl.pas.model.resource;

import java.util.UUID;

public abstract class Resource {
    private long ISBN;
    private String title;
    private boolean available;
    private UUID resourceId;

    public Resource(long ISBN, String title) {
        this.ISBN = ISBN;
        this.title = title;
        this.available = true;
        this.resourceId = UUID.randomUUID();
    }

    public void setResourceId(UUID resourceId) {
        this.resourceId = resourceId;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public UUID getResourceId() {
        return resourceId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Resource) {
            Resource resource = (Resource) obj;
            return this.resourceId.equals(resource.getResourceId());
        }
        return false;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "ISBN=" + ISBN +
                ", title='" + title + '\'' +
                ", available=" + available +
                '}';
    }
}
