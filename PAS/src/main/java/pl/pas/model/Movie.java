package pl.pas.model;

import java.util.Date;

public class Movie {
    private String title;
    /**
     * In minutes
     */
    private int length;
    private Date date;
    private int seatsNumber;
    private boolean[] takenSeats;

    public Movie(String title, int length, Date date, int seatsNumber) {
        this.title = title;
        this.length = length;
        this.date = date;
        this.seatsNumber = seatsNumber;

        this.takenSeats = new boolean[seatsNumber];
    }

    boolean takeSeat(int seat) {
        if (seat <= takenSeats.length && seat > 0) {
            if (!takenSeats[seat - 1]) {
                takenSeats[seat - 1] = true;
                return true;
            }
        }

        return false;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "Title: " + title + '\'' +
                "Movie length: " + length +
                "Date: " + date +
                "Seats number: " + seatsNumber +
                '}';
    }
}
