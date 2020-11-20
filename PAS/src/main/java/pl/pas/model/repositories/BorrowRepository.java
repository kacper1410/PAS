package pl.pas.model.repositories;

import pl.pas.model.Borrow;
import pl.pas.model.repositories.interfaces.IBorrowRepository;

import java.util.List;
import java.util.UUID;

public class BorrowRepository implements IBorrowRepository {

    @Override
    public boolean addBorrow(Borrow borrow) {
        return false;
    }

    @Override
    public Borrow getBorrow(UUID uuid) {
        return null;
    }

    @Override
    public List<Borrow> getAllBorrows() {
        return null;
    }

    @Override
    public boolean updateBorrow(Borrow oldBorrow, Borrow newBorrow) {
        return false;
    }
}
