package pl.pas.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Client {
    private UUID id;
    private String name;
    private int age;
    private int maxTickets;
    private List<Ticket> clientTickets;

    public Client(String name, int age, int maxTickets) {
        this.name = name;
        this.age = age;
        this.maxTickets = maxTickets;

        this.id = UUID.randomUUID();
        this.clientTickets = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getMaxTickets() {
        return maxTickets;
    }

    public List<Ticket> getClientTickets() {
        return clientTickets;
    }

    public void addTicket(Ticket ticket) {
        if (clientTickets.size() < maxTickets) {
            clientTickets.add(ticket);
        }
    }

    public Ticket getClientTicket(Ticket ticket) {
        for (Ticket t : clientTickets) {
            if (t.equals(ticket) ) {
                return t;
            }
        }

        return null;
    }

    public boolean removeTicket(Ticket ticket) {
        return clientTickets.remove(ticket);
    }

    @Override
    public String toString() {
        return "Client{" +
                "Client id: " + id +
                "Client name: " + name + '\'' +
                "Client age: " + age +
                "Client max tickets: " + maxTickets +
                '}';
    }
}
