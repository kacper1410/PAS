package pl.pas.ports.infrastructure.borrow;

import pl.pas.data.model.Borrow;

public interface CreateBorrowPort {
    void createBorrow(Borrow borrow);
}
