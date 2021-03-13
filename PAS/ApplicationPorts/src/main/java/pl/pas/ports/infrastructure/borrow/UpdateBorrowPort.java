package pl.pas.ports.infrastructure.borrow;

import pl.pas.exceptions.NotFoundException;
import pl.pas.model.Borrow;

public interface UpdateBorrowPort {
    void endBorrow(long uuid) throws NotFoundException;
    void updateBorrow(long uuid, Borrow newBorrow);
}
