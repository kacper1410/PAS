package pl.pas.controllers;

import pl.pas.managers.BorrowManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ApplicationScoped
public class BorrowController implements Serializable {

    @Inject
    private BorrowManager borrowManager;

    public BorrowManager getBorrowManager() {
        return borrowManager;
    }
}
