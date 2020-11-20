package pl.pas.model.repositories;

import pl.pas.model.resource.Resource;
import java.util.UUID;

public class ResourceRepository extends Repository<Resource> {

    public ResourceRepository() {
        super();
    }

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
    public boolean update(Resource newElem, Resource oldElem) {
        if (getAll().contains(oldElem) && (newElem != null) && oldElem.getClass().equals(newElem.getClass())) {
            newElem.setResourceId(oldElem.getResourceId());
            getAll().set(getAll().indexOf(oldElem), newElem);
            return true;
        }
        return false;
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
