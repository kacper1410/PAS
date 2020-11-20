package pl.pas.model.repositories;

import java.util.ArrayList;
import java.util.UUID;

public abstract class Repository<T> {
    private ArrayList<T> repository;

    public Repository() {
        repository = new ArrayList<>();
    }

    public boolean add(T element) {
        if (element != null) {
            repository.add(element);
            return true;
        }
        return false;
    }

    public boolean remove(T element) {
        if (element != null) {
            repository.remove(element);
            return true;
        }
        return false;
    }

    public boolean removeByIndex(int index) {
        if (index >= 0 && index < repository.size()) {
            repository.remove(index);
            return true;
        }
        return false;
    }

    public ArrayList<T> getRepository() {
        return repository;
    }

    public abstract T getByUUID(UUID uuid);
}
