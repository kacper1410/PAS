package pl.pas.model.repositories.interfaces;

import pl.pas.model.entities.Borrow;

import java.util.List;
import java.util.UUID;

public interface IBorrowRepository {
    boolean addBorrow(Borrow borrow);
    Borrow getBorrow(UUID uuid);
    List<Borrow> getAllBorrows();
    void updateBorrow(Borrow oldBorrow, Borrow newBorrow);
}
