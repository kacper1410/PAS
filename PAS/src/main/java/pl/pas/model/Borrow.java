package pl.pas.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.pas.model.resource.Resource;
import pl.pas.model.user.Client;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
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

    public void endBorrow() {
        this.returnDate = new Date();
    }
}
