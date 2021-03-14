package pl.pas.ports.infrastructure.borrow;

import pl.pas.domain.exceptions.NotFoundException;
import pl.pas.domain.model.Borrow;

public interface UpdateBorrowPort {
    void endBorrow(long uuid) throws NotFoundException;
    void updateBorrow(long uuid, Borrow newBorrow);
}
