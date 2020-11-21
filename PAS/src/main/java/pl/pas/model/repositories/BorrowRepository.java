package pl.pas.model.repositories;

import pl.pas.model.entities.Borrow;
import pl.pas.model.repositories.interfaces.IBorrowRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BorrowRepository implements IBorrowRepository {
    List<Borrow> borrows;

    public BorrowRepository() {
        borrows = new ArrayList<>();
    }

    @Override
    public boolean addBorrow(Borrow borrow) {
        return borrows.add(borrow);
    }

    @Override
    public Borrow getBorrow(UUID uuid) {
        for (Borrow b : borrows) {
            if (b.getBorrowId().equals(uuid)) {
                return b;
            }
        }

        return null;
    }

    @Override
    public List<Borrow> getAllBorrows() {
        return borrows;
    }

    @Override
    public void updateBorrow(Borrow oldBorrow, Borrow newBorrow) {
        borrows.set(borrows.indexOf(oldBorrow), newBorrow);
    }
}
