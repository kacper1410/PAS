package pl.pas.model.resource;

public abstract class Resource {
    private long ISBN;
    private String title;
    private String author;
    private boolean available;
    private long resourceId;

    public Resource() {
    }

    public Resource(long ISBN, String title, String author) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.available = true;
        this.resourceId = 0;
    }

    public void setResourceId(long resourceId) {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public long getResourceId() {
        return resourceId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Resource) {
            Resource resource = (Resource) obj;
            return this.resourceId == resource.resourceId && this.ISBN == resource.ISBN;
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
