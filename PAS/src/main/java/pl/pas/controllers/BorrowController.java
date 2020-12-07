package pl.pas.controllers;

import lombok.Data;
import pl.pas.managers.BorrowManager;
import pl.pas.model.Borrow;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Named
@ApplicationScoped
public class BorrowController implements Serializable {

    @Inject
    private BorrowManager borrowManager;

    private long resourceId;
    private long filterResourceId;
    private long clientId;
    private long filterClientId;
    private Date borrowDate;
    private Borrow currentBorrow;
    private long borrowId;
    private List<Borrow> currentBorrows;

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

    public String filter() {
        initBorrows();
        String rId = filterResourceId != 0 ? String.valueOf(filterResourceId) : "";
        String cId = filterClientId != 0 ? String.valueOf(filterClientId) : "";

        currentBorrows.removeIf(borrow -> {
                    String borrowCId = String.valueOf(borrow.getClient().getUserId());
                    String borrowRId = String.valueOf(borrow.getResource().getResourceId());
                    return !borrowCId.contains(cId) || !borrowRId.contains(rId);
                }
        );

        return "borrows";
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

    @PostConstruct
    private void initBorrows() {
        currentBorrows = borrowManager.getAllBorrows();
    }
}
