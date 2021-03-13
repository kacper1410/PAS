package pl.pas.repositories;

import pl.pas.data.model.BorrowEntity;
import pl.pas.data.model.exceptions.NotFoundExceptionEntity;
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

    private final List<BorrowEntity> borrows;

    public BorrowRepository() {
        borrows = new ArrayList<>();
    }

    @Override
    public void addBorrow(BorrowEntity borrow) {
        synchronized (borrows) {
            borrow.setBorrowId(UUID.randomUUID());
            borrows.add(borrow);
        }
    }

    @Override
    public BorrowEntity getBorrow(long uuid) throws NotFoundExceptionEntity {
        synchronized (borrows) {
            for (BorrowEntity b : borrows) {
                if (b.getBorrowId() == uuid) return b;
            }
            throw new NotFoundExceptionEntity();
        }
    }

    @Override
    public List<BorrowEntity> getBorrowsByUser(long uuid) {
        synchronized (borrows) {
            List<BorrowEntity> clientsBorrows = new ArrayList<>();
            for (BorrowEntity b: borrows) {
                if (b.getClient().getUserId() == uuid) {
                    clientsBorrows.add(b);
                }
            }
            return clientsBorrows;
        }
    }

    @Override
    public List<BorrowEntity> getBorrowsByResource(long uuid) {
        synchronized (borrows) {
            List<BorrowEntity> resourceBorrows = new ArrayList<>();
            for (BorrowEntity b: borrows) {
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
    public List<BorrowEntity> getAllBorrows() {
        synchronized (borrows) {
            return new ArrayList<>(borrows);
        }
    }

    @Override
    public void updateBorrow(long uuid, BorrowEntity newBorrow) {
        synchronized (borrows) {
            for (BorrowEntity b : borrows) {
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
            } catch (NotFoundExceptionEntity ignored) {

            }
        }
    }

    @Override
    public void endBorrow(long uuid) throws NotFoundExceptionEntity {
        synchronized (borrows) {
            getBorrow(uuid).setReturnDate(new Date());
        }
    }
}
