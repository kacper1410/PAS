package pl.pas.model.user;

public class Employee extends User {

    public Employee() {
        super();
    }

    public Employee(String login, String password, String name, String lastName) {
        super(login, password, name, lastName);
    }

    @Override
    public String toString() {
        return "Employee{} " + super.toString();
    }
}
