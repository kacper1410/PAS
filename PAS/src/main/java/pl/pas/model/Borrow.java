package pl.pas.model;

import pl.pas.model.resource.Resource;
import pl.pas.model.user.Client;

import java.util.Date;

public class Borrow {
    private long borrowId;
    private Client client;
    private Resource resource;
    private Date borrowDate;
    private Date returnDate;

    public Borrow(Client client, Resource resource) {
        this.borrowId = 0;
        this.client = client;
        this.resource = resource;
        this.borrowDate = new Date();
        this.returnDate = null;
    }

    public Borrow(Client client, Resource resource, Date date) {
        this.borrowId = 0;
        this.client = client;
        this.resource = resource;
        this.borrowDate = date;
        this.returnDate = null;
    }

    public void setBorrowId(long borrowId) {
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

    public long getBorrowId() {
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
            return this.borrowId == borrow.borrowId && this.client.equals(borrow.getClient()) && this.resource.equals(borrow.getResource());
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
