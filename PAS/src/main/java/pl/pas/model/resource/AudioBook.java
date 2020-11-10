package pl.pas.model.resource;

public class AudioBook extends Resource {
    private int length;

    public AudioBook(long ISBN, String title, int length, boolean available) {
        super(ISBN, title, available);
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
