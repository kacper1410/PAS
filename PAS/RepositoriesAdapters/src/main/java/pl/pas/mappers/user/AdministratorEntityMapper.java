package pl.pas.mappers.user;

import pl.pas.data.model.user.AdministratorEntity;
import pl.pas.model.user.Administrator;

public class AdministratorEntityMapper {

    public static Administrator administratorEntityToAdministrator(AdministratorEntity administratorEntity) {
        Administrator administrator = new Administrator();
        administrator.setLogin(administratorEntity.getLogin());
        administrator.setPassword(administratorEntity.getPassword());
        administrator.setName(administratorEntity.getName());
        administrator.setLastName(administratorEntity.getLastName());
        administrator.setActive(administratorEntity.isActive());
        return administrator;
    }

    public static AdministratorEntity administratorToAdministratorEntity(Administrator administrator) {
        AdministratorEntity administratorEntity = new AdministratorEntity();
        administratorEntity.setLogin(administrator.getLogin());
        administratorEntity.setPassword(administrator.getPassword());
        administratorEntity.setName(administrator.getName());
        administratorEntity.setLastName(administrator.getLastName());
        administratorEntity.setActive(administrator.isActive());
        return administratorEntity;
    }
}
