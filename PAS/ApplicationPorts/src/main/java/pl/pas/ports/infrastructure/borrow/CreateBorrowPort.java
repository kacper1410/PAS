package pl.pas.ports.infrastructure.borrow;

import pl.pas.domain.model.Borrow;

public interface CreateBorrowPort {
    void createBorrow(Borrow borrow);
}
