package pl.pas.model.repositories.interfaces;

import pl.pas.model.entities.Borrow;

import java.util.List;
import java.util.UUID;

public interface IBorrowRepository {
    // TODO
    // UUID in addBorrow method

    boolean addBorrow(Borrow borrow, UUID uuid);
    Borrow getBorrow(UUID uuid);
    List<Borrow> getBorrowsByUser(UUID uuid);
    List<Borrow> getBorrowsByResource(UUID uuid);
    List<Borrow> getAllBorrows();
    void updateBorrow(Borrow oldBorrow, Borrow newBorrow);
    void endBorrow(UUID uuid);
}
