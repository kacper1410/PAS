package pl.pas.model;

public class Ticket {
    private Movie movie;
    private int seat;
    private double price;

    public Ticket(Movie movie, int seat, double price) {
        this.movie = movie;
        this.seat = Math.max(seat, 0);
        this.price = Math.max(price, 0);
    }

    public Movie getMovie() {
        return movie;
    }

    public int getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "Movie: " + movie +
                "Seat: " + seat +
                "Price: " + price +
                '}';
    }
}
