package pl.pas.model.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Repository<T> {
    private List<T> repository;

    public Repository() {
        repository = new ArrayList<>();
    }

    @Override
    public String toString() {
        return repository.toString();
    }
    public boolean add(T element) {
        if (element != null && !repository.contains(element)) {
            return repository.add(element);
        }
        return false;
    }

    public boolean remove(T element) {
        return repository.remove(element);
    }

    public abstract boolean update(T newElem, T oldElem);

    public List<T> getAll() {
        return repository;
    }

    public abstract T get(UUID uuid);

    public abstract boolean remove(UUID id);
}
