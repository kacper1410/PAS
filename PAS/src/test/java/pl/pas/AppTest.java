package pl.pas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;
import pl.pas.model.Client;
import pl.pas.model.Movie;
import pl.pas.model.Ticket;

public class AppTest {
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void clientTest() {
        Client client = new Client("Jan Kowalski", 20, 5);
        assertEquals(client.getName(), "Jan Kowalski");
        assertEquals(client.getAge(), 20);
        assertEquals(client.getMaxTickets(), 5);
    }

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

    @Test
    public void clientWithTicketTest() {
        Client client = new Client("Adam Nowak", 25, 1);
        Ticket ticket1 = new Ticket(new Movie("Harry Potter"), 5, 12.50);
        Ticket ticket2 = new Ticket(new Movie("Potter Harry"), 6, 9.50);

        // Add one ticket
        client.addTicket(ticket1);
        assertEquals(client.getClientTickets().size(), 1);
        assertEquals(client.getClientTicket(ticket1), ticket1);

        // Add more ticket's then Client can buy
        client.addTicket(ticket2);
        Assert.assertEquals(client.getClientTickets().size(), 1);
        Assert.assertEquals(client.getClientTicket(ticket1), ticket1);
        Assert.assertNull(client.getClientTicket(ticket2));

        // Remove ticket
        client.removeTicket(ticket1);
        Assert.assertEquals(client.getClientTickets().size(), 0);
        Assert.assertNull(client.getClientTicket(ticket1));
    }
}
