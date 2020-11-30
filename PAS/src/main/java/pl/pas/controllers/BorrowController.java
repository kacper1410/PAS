package pl.pas.controllers;

import pl.pas.managers.BorrowManager;
import pl.pas.model.Borrow;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@ApplicationScoped
public class BorrowController implements Serializable {

    @Inject
    private BorrowManager borrowManager;

    private long resourceId;
    private long clientId;
    private Date borrowDate;
    private Borrow currentBorrow;
    private long borrowId;

    public void setCurrentBorrow(Borrow currentBorrow) {
        this.currentBorrow = currentBorrow;
    }

    public void setBorrowId(long borrowId) {
        this.borrowId = borrowId;
    }

    public Borrow getCurrentBorrow() {
        return currentBorrow;
    }

    public long getBorrowId() {
        return borrowId;
    }

    public BorrowController() {
        borrowDate = new Date();
    }

    public BorrowManager getBorrowManager() {
        return borrowManager;
    }

    public void setBorrowManager(BorrowManager borrowManager) {
        this.borrowManager = borrowManager;
    }

    public long getResourceId() {
        return resourceId;
    }

    public void setResourceId(long resourceId) {
        this.resourceId = resourceId;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String processBorrow() {
        this.borrowManager.borrowResource(resourceId, clientId, borrowDate);
        this.resourceId = 0;
        this.clientId = 0;
        this.borrowDate = new Date();
        return "main";
    }

    public String search() {
        currentBorrow = borrowManager.getBorrow(borrowId);
        return "borrow";
    }
}
