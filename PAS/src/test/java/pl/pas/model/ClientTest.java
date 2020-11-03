package pl.pas.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ClientTest {
    @Test
    public void clientTest() {
        Client client = new NormalClient("Jan Kowalski", 20);
        assertEquals(client.getName(), "Jan Kowalski");
        assertEquals(client.getAge(), 20);
        assertEquals(client.getMaxTickets(), 2);

        client = new PremiumClient("Jan Kowalski", 20);
        assertEquals(client.getMaxTickets(), 10);
    }

    @Test
    public void clientWithTicketTest() {
        Client client = new NormalClient("Adam Nowak", 25);
        Ticket ticket1 = new Ticket(new Movie("Harry Potter", 90, new Date(), 10), 5, 12.50);
        Ticket ticket2 = new Ticket(new Movie("Potter Harry", 90, new Date(), 9), 6, 9.50);
        Ticket ticket3 = new Ticket(new Movie("Harter Potry", 90, new Date(), 9), 7, 19.50);


        client.addTicket(ticket1);
        client.addTicket(ticket2);
        assertEquals(client.getClientTickets().size(), 2);
        assertEquals(client.getClientTicket(ticket1), ticket1);
        assertEquals(client.getClientTicket(ticket2), ticket2);


        client.addTicket(ticket3);
        Assert.assertEquals(client.getClientTickets().size(), 2);
        Assert.assertEquals(client.getClientTicket(ticket1), ticket1);
        Assert.assertEquals(client.getClientTicket(ticket2), ticket2);
        Assert.assertNull(client.getClientTicket(ticket3));


        client.removeTicket(ticket1);
        Assert.assertEquals(client.getClientTickets().size(), 1);
        Assert.assertNull(client.getClientTicket(ticket1));
    }
}
