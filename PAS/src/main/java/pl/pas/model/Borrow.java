package pl.pas.model;

import pl.pas.model.resource.Resource;
import pl.pas.model.user.Client;
import java.util.Date;

public class Borrow {
    private Client client;
    private Resource resource;
    private Date borrowDate;
    private Date returnDate;

    public Borrow(Client client, Resource resource) {
        this.client = client;
        this.resource = resource;
        this.borrowDate = new Date();
        this.returnDate = null;
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
