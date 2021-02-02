package pl.pas.controllers;

import lombok.Data;
import pl.pas.exceptions.NotFoundException;
import pl.pas.exceptions.NotValidException;
import pl.pas.managers.BorrowManager;
import pl.pas.managers.UserManager;
import pl.pas.model.Borrow;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Named
@SessionScoped
public class BorrowController implements Serializable {

    @Inject
    private BorrowManager borrowManager;
    @Inject
    private IdentityUtils identityUtils;
    @Inject
    private UserManager userManager;
    @Inject
    private ResourceController resourceController;

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
        if (identityUtils.isClient() || identityUtils.isEmployee()) {
            if (identityUtils.isClient()) {
                try {
                    clientId = userManager.getUser(identityUtils.getMyLogin()).getUserId();
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
            }

            try {
                this.borrowManager.borrowResource(resourceId, clientId, borrowDate);
            } catch (NotFoundException | NotValidException e) {
                e.printStackTrace();
            }
            resourceController.updateList();
            this.resourceId = 0;
            this.clientId = 0;
            this.borrowDate = new Date();
        }

        return "main";
    }

    public String search() {
        try {
            currentBorrow = borrowManager.getBorrow(borrowId);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
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
        if (identityUtils.isEmployee() || (identityUtils.isClient() && identityUtils.getMyLogin().equals(borrow.getClient().getLogin()))) {
            try {
                borrowManager.endBorrow(borrow);
            } catch (NotValidException | NotFoundException ignored) {

            }
            resourceController.updateList();
        }

        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true";
    }

    @PostConstruct
    private void initBorrows() {
        currentBorrows = borrowManager.getAllBorrows();
    }
}
