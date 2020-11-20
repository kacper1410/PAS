package pl.pas.model.repositories;

import pl.pas.model.Borrow;

import java.util.UUID;

public class BorrowRepository extends Repository<Borrow> {

    public BorrowRepository() {
        super();
    }

    @Override
    public boolean update(Borrow newElem, Borrow oldElem) {
        if (getAll().contains(oldElem) && (newElem != null) && oldElem.getClass().equals(newElem.getClass())) {
            newElem.setBorrowId(oldElem.getBorrowId());
            getAll().set(getAll().indexOf(oldElem), newElem);
            return true;
        }
        return false;
    }


    @Override
    public Borrow get(UUID uuid) {
        for (Borrow borrow: getAll()) {
            if (borrow.getBorrowId().equals(uuid)) {
                return borrow;
            }
        }
        return null;
    }

    @Override
    public boolean remove(UUID id) {
        if (getAll().contains(get(id))) {
            getAll().remove(get(id));
            return true;
        }
        return false;
    }


}
