package pl.pas.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TicketTest {
    @Test
    public void ticketTest() {
        Movie movie = new Movie("Harry Portier");
        Ticket ticket = new Ticket(movie, 5, 12.20);

        assertEquals(ticket.getMovie(), movie);
        assertEquals(ticket.getSeat(), 5);
        assertEquals(ticket.getPrice(), 12.20, 0.0001);

        ticket = new Ticket(movie, -5, -12.20);
        assertEquals(ticket.getSeat(), 0);
        assertEquals(ticket.getPrice(), 0, 0.0001);
    }

}
