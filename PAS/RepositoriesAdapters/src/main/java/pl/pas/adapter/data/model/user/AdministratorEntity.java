package pl.pas.adapter.data.model.user;

public class AdministratorEntity extends UserEntity {

    public AdministratorEntity() {
        super();
    }

    public AdministratorEntity(String login, String password, String name, String lastName) {
        super(login, password, name, lastName);
    }

    @Override
    public String toString() {
        return "Administrator{} " + super.toString();
    }
}
