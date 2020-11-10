package pl.pas.model;

public class AudioBook {
    private long id;
    private String title;
    private int length;
    private boolean isAvailable;

    public AudioBook(long id, String title, int length, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.length = length;
        this.isAvailable = isAvailable;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "AudioBook{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publishYear=" + length +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
