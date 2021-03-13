package pl.pas.ports.infrastructure.borrow;

import pl.pas.model.Borrow;

import java.util.List;

public interface ReadBorrowPort {
    List<Borrow> readAllBorrows();
    List<Borrow> readBorrowsByResource(long uuid);
    List<Borrow> getBorrowsByUser(long uuid);
    Borrow getBorrow(long uuid);
}
