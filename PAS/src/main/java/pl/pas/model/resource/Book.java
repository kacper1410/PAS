package pl.pas.model.resource;

public class Book extends Resource {
    private int publishYear;

    public Book() {
        super();
        this.publishYear = 0;
    }

    public Book(long ISBN, String title, String author, int publishYear) {
        super(ISBN, title, author);
        this.publishYear = publishYear;
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
