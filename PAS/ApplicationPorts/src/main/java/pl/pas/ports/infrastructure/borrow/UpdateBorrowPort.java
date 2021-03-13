package pl.pas.ports.infrastructure.borrow;

import pl.pas.model.Borrow;

public interface UpdateBorrowPort {
    void endBorrow(long uuid);
    void updateBorrow(long uuid, Borrow newBorrow);
}
