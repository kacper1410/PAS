package pl.pas.data.repositories.interfaces;

import pl.pas.data.model.BorrowEntity;
import pl.pas.data.model.exceptions.NotFoundExceptionEntity;

import java.util.List;

public interface IBorrowRepository {
    void addBorrow(BorrowEntity borrow);
    BorrowEntity getBorrow(long uuid) throws NotFoundExceptionEntity;
    List<BorrowEntity> getBorrowsByUser(long uuid);
    List<BorrowEntity> getBorrowsByResource(long uuid);
    List<BorrowEntity> getAllBorrows();
    void updateBorrow(long uuid, BorrowEntity newBorrow);
    void deleteBorrow(long uuid);
    void endBorrow(long uuid) throws NotFoundExceptionEntity;
}
