package pl.pas.repositories;

import pl.pas.exceptions.NotFoundException;
import pl.pas.model.Borrow;
import pl.pas.repositories.interfaces.IBorrowRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@ApplicationScoped
public class BorrowRepository implements IBorrowRepository, Serializable {

    private final List<Borrow> borrows;

    public BorrowRepository() {
        borrows = new ArrayList<>();
    }

    @Override
    public void addBorrow(Borrow borrow) {
        synchronized (borrows) {
            borrow.setBorrowId(UUID.randomUUID());
            borrows.add(borrow);
        }
    }

    @Override
    public Borrow getBorrow(long uuid) throws NotFoundException {
        synchronized (borrows) {
            for (Borrow b : borrows) {
                if (b.getBorrowId() == uuid) return b;
            }
            throw new NotFoundException();
        }
    }

    @Override
    public List<Borrow> getBorrowsByUser(long uuid) {
        synchronized (borrows) {
            List<Borrow> clientsBorrows = new ArrayList<>();
            for (Borrow b: borrows) {
                if (b.getClient().getUserId() == uuid) {
                    clientsBorrows.add(b);
                }
            }
            return clientsBorrows;
        }
    }

    @Override
    public List<Borrow> getBorrowsByResource(long uuid) {
        synchronized (borrows) {
            List<Borrow> resourceBorrows = new ArrayList<>();
            for (Borrow b: borrows) {
                if (b.getResource() != null) {
                    if (b.getResource().getResourceId() == uuid) {
                        resourceBorrows.add(b);
                    }
                }
            }
            return resourceBorrows;
        }
    }

    @Override
    public List<Borrow> getAllBorrows() {
        synchronized (borrows) {
            return new ArrayList<>(borrows);
        }
    }

    @Override
    public void updateBorrow(long uuid, Borrow newBorrow) {
        synchronized (borrows) {
            for (Borrow b : borrows) {
                if (b.getBorrowId() == uuid) {
                    newBorrow.setBorrowId(uuid);
                    borrows.set(borrows.indexOf(b), newBorrow);
                }
            }
        }
    }

    @Override
    public void deleteBorrow(long uuid) {
        synchronized (borrows) {
            try {
                borrows.remove(getBorrow(uuid));
            } catch (NotFoundException ignored) {

            }
        }
    }

    @Override
    public void endBorrow(long uuid) throws NotFoundException {
        synchronized (borrows) {
            getBorrow(uuid).setReturnDate(new Date());
        }
    }
}
