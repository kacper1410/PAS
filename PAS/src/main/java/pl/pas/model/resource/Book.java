package pl.pas.model.resource;

public class Book extends Resource {
    private int publishYear;

    public Book(long ISBN, String title, int publishYear) {
        super(ISBN, title);
        this.publishYear = publishYear;
    }

    public Book() {
        super(123123L, "A");

    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    @Override
    public String toString() {
        return super.toString() + " Book{" +
                "publishYear=" + publishYear +
                '}';
    }
}
