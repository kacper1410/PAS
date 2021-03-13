package pl.pas.data.model.user;

public class EmployeeEntity extends UserEntity {

    public EmployeeEntity() {
        super();
    }

    public EmployeeEntity(String login, String password, String name, String lastName) {
        super(login, password, name, lastName);
    }

    @Override
    public String toString() {
        return "Employee{} " + super.toString();
    }
}
