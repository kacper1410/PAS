package pl.pas.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ClientTest {
    @Test
    public void clientTest() {
        Client client = new Client("Jan Kowalski", 20, 5);
        assertEquals(client.getName(), "Jan Kowalski");
        assertEquals(client.getAge(), 20);
        assertEquals(client.getMaxTickets(), 5);
    }

    @Test
    public void clientWithTicketTest() {
        Client client = new Client("Adam Nowak", 25, 1);
        Ticket ticket1 = new Ticket(new Movie("Harry Potter", 90, new Date(), 10), 5, 12.50);
        Ticket ticket2 = new Ticket(new Movie("Potter Harry", 90, new Date(), 9), 6, 9.50);

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
