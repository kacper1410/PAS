package pl.pas.model.entities.user;

public class Employee extends User {

    public Employee(String login, String name, String lastName) {
        super(login, name, lastName);
    }

    //TODO
    // Methods for employee

    @Override
    public String toString() {
        return "Employee{} " + super.toString();
    }
}
