package pl.pas.model;

public class Book {
    private long ISBN;
    private String title;
    private int publishYear;
    private boolean isAvailable;

    public Book(long ISBN, String title, int publishYear, boolean isAvailable) {
        this.ISBN = ISBN;
        this.title = title;
        this.publishYear = publishYear;
        this.isAvailable = isAvailable;
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

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN=" + ISBN +
                ", title='" + title + '\'' +
                ", publishYear=" + publishYear +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
