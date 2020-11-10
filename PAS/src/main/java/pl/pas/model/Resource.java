package pl.pas.model;

public abstract class Resource {
    private long ISBN;
    private String title;
    private boolean available;

    public Resource(long ISBN, String title, boolean available) {
        this.ISBN = ISBN;
        this.title = title;
        this.available = available;
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

    @Override
    public String toString() {
        return "Resource{" +
                "ISBN=" + ISBN +
                ", title='" + title + '\'' +
                ", available=" + available +
                '}';
    }
}
