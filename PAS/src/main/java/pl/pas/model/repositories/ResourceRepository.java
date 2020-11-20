package pl.pas.model.repositories;

import pl.pas.model.resource.Resource;
import java.util.UUID;

public class ResourceRepository extends Repository<Resource> {

    @Override
    public Resource get(UUID uuid) {
        for (Resource res: getAll()) {
            if (res.getResourceId().equals(uuid)) return res;
        }
        return null;
    }

    @Override
    public String toString() {
        return "ResourceRepository{ " + super.toString() + " }";
    }

    @Override
    public boolean remove(UUID id) {
        for (int i = 0; i < getAll().size(); i++) {
            if (getAll().get(i).getResourceId().equals(id)) {
                getAll().set(i, null);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(UUID id, Resource element) {
        if (getAll().contains(get(id)) && element != null) {
            element.setResourceId(id);

            for (int i = 0; i < getAll().size(); i++) {
                if (getAll().get(i).getResourceId().equals(id)) {
                    getAll().set(i, element);
                    return true;
                }
            }
        }
        return false;
    }

}
