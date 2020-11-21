package pl.pas.model.repositories;

import pl.pas.model.entities.Borrow;
import pl.pas.model.repositories.interfaces.IBorrowRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class BorrowRepository implements IBorrowRepository {
    List<Borrow> borrows;

    public BorrowRepository() {
        borrows = new ArrayList<>();
    }

    @Override
    public boolean addBorrow(Borrow borrow, UUID uuid) {
        borrow.setBorrowId(uuid);
        return borrows.add(borrow);
    }

    @Override
    public Borrow getBorrow(UUID uuid) {
        for (Borrow b : borrows) {
            if (b.getBorrowId().equals(uuid)) return b;
        }
        return null;
    }

    @Override
    public List<Borrow> getBorrowsByUser(UUID uuid) {
        List<Borrow> clientsBorrows = new ArrayList<>();
        for (Borrow b: borrows) {
            if (b.getClient().getUserId().equals(uuid)) {
                clientsBorrows.add(b);
            }
        }
        return clientsBorrows;
    }

    @Override
    public List<Borrow> getBorrowsByResource(UUID uuid) {
        List<Borrow> resourceBorrows = new ArrayList<>();
        for (Borrow b: borrows) {
            if (b.getResource().getResourceId().equals(uuid)) {
                resourceBorrows.add(b);
            }
        }
        return resourceBorrows;
    }

    @Override
    public List<Borrow> getAllBorrows() {
        return borrows;
    }

    @Override
    public void updateBorrow(Borrow oldBorrow, Borrow newBorrow) {
        borrows.set(borrows.indexOf(oldBorrow), newBorrow);
    }

    @Override
    public void endBorrow(UUID uuid) {
        getBorrow(uuid).setReturnDate(new Date());
    }
}
