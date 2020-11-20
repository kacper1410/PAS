package pl.pas.model.repositories;

import pl.pas.model.Borrow;

import java.util.UUID;

public class BorrowRepository extends Repository<Borrow> {
    @Override
    public boolean update(UUID id, Borrow element) {
        return false;
    }

    @Override
    public Borrow get(UUID uuid) {
        return null;
    }

    @Override
    public boolean remove(UUID id) {
        return false;
    }
}
