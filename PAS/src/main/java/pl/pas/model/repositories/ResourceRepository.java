package pl.pas.model.repositories;

import pl.pas.model.resource.Resource;
import java.util.UUID;

public class ResourceRepository extends Repository<Resource> {

    ResourceRepository() {
        super();
    }

    @Override
    public Resource get(UUID uuid) {
        for (Resource res: getRepository()) {
            if (res.getResourceId().equals(uuid)) return res;
        }
        return null;
    }

    @Override
    public String toString() {
        return "ResourceRepository{ " + super.toString() + " }";
    }

    @Override
    public boolean update(UUID id, Resource element) {
        if (getRepository().contains(get(id)) && element != null) {
            element.setResourceId(id);
            for (int i = 0; i < getRepository().size(); i++) {
                if (getRepository().get(i).getResourceId().equals(id)) {
                    getRepository().set(i, element);
                    return true;
                }
            }
        }
        return false;
    }

}
