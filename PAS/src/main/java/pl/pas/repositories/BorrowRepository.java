package pl.pas.repositories;

import pl.pas.model.Borrow;
import pl.pas.repositories.interfaces.IBorrowRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Named
@ApplicationScoped
public class BorrowRepository implements IBorrowRepository, Serializable {

    private final List<Borrow> borrows;

    public BorrowRepository() {
        borrows = new ArrayList<>();
    }

    @Override
    public boolean addBorrow(Borrow borrow, UUID uuid) {
        synchronized (borrows) {
            borrow.setBorrowId(uuid);
            return borrows.add(borrow);
        }
    }

    @Override
    public Borrow getBorrow(UUID uuid) {
        synchronized (borrows) {
            for (Borrow b : borrows) {
                if (b.getBorrowId().equals(uuid)) return b;
            }
            return null;
        }
    }

    @Override
    public List<Borrow> getBorrowsByUser(UUID uuid) {
        synchronized (borrows) {
            List<Borrow> clientsBorrows = new ArrayList<>();
            for (Borrow b: borrows) {
                if (b.getClient().getUserId().equals(uuid)) {
                    clientsBorrows.add(b);
                }
            }
            return clientsBorrows;
        }
    }

    @Override
    public List<Borrow> getBorrowsByResource(UUID uuid) {
        synchronized (borrows) {
            List<Borrow> resourceBorrows = new ArrayList<>();
            for (Borrow b: borrows) {
                if (b.getResource().getResourceId().equals(uuid)) {
                    resourceBorrows.add(b);
                }
            }
            return resourceBorrows;
        }
    }

    @Override
    public List<Borrow> getAllBorrows() {
        synchronized (borrows) {
            return borrows;
        }
    }

    @Override
    public void updateBorrow(UUID uuid, Borrow newBorrow) {
        synchronized (borrows) {
            for (Borrow b : borrows) {
                if (b.getBorrowId().equals(uuid)) {
                    newBorrow.setBorrowId(uuid);
                    borrows.set(borrows.indexOf(b), newBorrow);
                }
            }
        }
    }

    @Override
    public boolean deleteBorrow(UUID uuid) {
        synchronized (borrows) {
            return borrows.remove(getBorrow(uuid));
        }
    }

    @Override
    public void endBorrow(UUID uuid) {
        synchronized (borrows) {
            getBorrow(uuid).setReturnDate(new Date());
        }
    }
}
