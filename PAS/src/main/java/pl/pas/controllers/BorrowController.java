package pl.pas.controllers;

import lombok.Data;
import pl.pas.managers.BorrowManager;
import pl.pas.model.Borrow;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Data
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

    public BorrowController() {
        borrowDate = new Date();
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

    public String returnResource(Borrow borrow) {
        borrowManager.endBorrow(borrow);

        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true";
    }

    public String cancelBorrow() {
        this.resourceId = 0;
        this.clientId = 0;
        this.borrowDate = new Date();
        return "main";
    }
}
