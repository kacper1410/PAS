package pl.pas.model.entities.resource;

public class AudioBook extends Resource {
    private int length;

    public AudioBook(long ISBN, String title, String author, int length) {
        super(ISBN, title, author);
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return super.toString() + " AudioBook{" +
                "length=" + length +
                '}';
    }
}
