package pl.pas.data.model;

import lombok.Data;
import pl.pas.data.model.resource.ResourceEntity;
import pl.pas.data.model.user.ClientEntity;

import java.util.Date;

@Data
public class BorrowEntity {

    private long borrowId;
    private ClientEntity client;
    private ResourceEntity resource;
    private Date borrowDate;
    private Date returnDate;

    public BorrowEntity() {
        borrowId = 0;
        client = new ClientEntity();
        borrowDate = new Date();
        returnDate = null;
    }

    public BorrowEntity(ClientEntity client, ResourceEntity resource) {
        this.borrowId = 0;
        this.client = client;
        this.resource = resource;
        this.borrowDate = new Date();
        this.returnDate = null;
    }

    public BorrowEntity(ClientEntity client, ResourceEntity resource, Date date) {
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
