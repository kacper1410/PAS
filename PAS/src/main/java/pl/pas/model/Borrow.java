package pl.pas.model;

import pl.pas.model.resource.Resource;
import pl.pas.model.user.Client;

import java.util.Date;
import java.util.UUID;

public class Borrow {
    private UUID borrowId;
    private Client client;
    private Resource resource;
    private Date borrowDate;
    private Date returnDate;

    public Borrow(Client client, Resource resource) {
        this.borrowId = null;
        this.client = client;
        this.resource = resource;
        this.borrowDate = new Date();
        this.returnDate = null;
    }

    public Borrow(Client client, Resource resource, Date date) {
        this.borrowId = UUID.randomUUID();
        this.client = client;
        this.resource = resource;
        this.borrowDate = date;
        this.returnDate = null;
    }

    public void setBorrowId(UUID borrowId) {
        this.borrowId = borrowId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public UUID getBorrowId() {
        return borrowId;
    }

    public void endBorrow() {
        this.returnDate = new Date();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Borrow) {
            Borrow borrow = (Borrow) obj;
            return this.borrowId.equals(borrow.getBorrowId()) && this.client.equals(borrow.getClient()) && this.resource.equals(borrow.getResource());
        }
        return false;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "client=" + client +
                ", resource=" + resource +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
