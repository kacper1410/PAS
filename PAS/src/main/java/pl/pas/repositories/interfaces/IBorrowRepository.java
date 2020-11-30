package pl.pas.repositories.interfaces;

import pl.pas.model.Borrow;

import java.util.List;

public interface IBorrowRepository {

    boolean addBorrow(Borrow borrow);
    Borrow getBorrow(long uuid);
    List<Borrow> getBorrowsByUser(long uuid);
    List<Borrow> getBorrowsByResource(long uuid);
    List<Borrow> getAllBorrows();
    void updateBorrow(long uuid, Borrow newBorrow);
    boolean deleteBorrow(long uuid);
    void endBorrow(long uuid);
}
