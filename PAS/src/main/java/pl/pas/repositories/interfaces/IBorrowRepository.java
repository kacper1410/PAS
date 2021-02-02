package pl.pas.repositories.interfaces;

import pl.pas.exceptions.NotFoundException;
import pl.pas.model.Borrow;

import java.util.List;

public interface IBorrowRepository {
    void addBorrow(Borrow borrow);
    Borrow getBorrow(long uuid) throws NotFoundException;
    List<Borrow> getBorrowsByUser(long uuid);
    List<Borrow> getBorrowsByResource(long uuid);
    List<Borrow> getAllBorrows();
    void updateBorrow(long uuid, Borrow newBorrow);
    void deleteBorrow(long uuid);
    void endBorrow(long uuid) throws NotFoundException;
}
