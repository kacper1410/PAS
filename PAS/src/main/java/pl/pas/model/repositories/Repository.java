package pl.pas.model.repositories;

import java.util.ArrayList;
import java.util.UUID;

public abstract class Repository<T> {
    private ArrayList<T> repository;

    public Repository() {
        repository = new ArrayList<>();
    }

    @Override
    public String toString() {
        return repository.toString();
    }

    public boolean add(T element) {
        if (element != null) {
            return repository.add(element);
        }
        return false;
    }

    public boolean remove(T element) {
        return repository.remove(element);
    }

    public T get(int index) {
        return repository.get(index);
    }

    public abstract boolean update(UUID id, T element);

    public boolean remove(int index) {
        if (index >= 0 && index < repository.size()) {
            repository.remove(index);
            return true;
        }
        return false;
    }

    public ArrayList<T> getRepository() {
        return repository;
    }

    public abstract T get(UUID uuid);
}
