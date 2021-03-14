package pl.pas.ports.infrastructure.borrow;

import pl.pas.data.exceptions.NotFoundException;
import pl.pas.data.model.Borrow;

import java.util.List;

public interface ReadBorrowPort {
    List<Borrow> readAllBorrows();
    List<Borrow> readBorrowsByResource(long uuid);
    List<Borrow> readBorrowsByUser(long uuid);
    Borrow readBorrow(long uuid) throws NotFoundException;
}
