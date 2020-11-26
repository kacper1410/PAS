package pl.pas.repositories.interfaces;

import pl.pas.model.Borrow;

import java.util.List;
import java.util.UUID;

public interface IBorrowRepository {
    // TODO
    // UUID in addBorrow method

    boolean addBorrow(Borrow borrow);
    Borrow getBorrow(UUID uuid);
    List<Borrow> getBorrowsByUser(UUID uuid);
    List<Borrow> getBorrowsByResource(UUID uuid);
    List<Borrow> getAllBorrows();
    void updateBorrow(UUID uuid, Borrow newBorrow);
    boolean deleteBorrow(UUID uuid);
    void endBorrow(UUID uuid);
}
