package pl.pas.model;

public class Employee extends User {
    private String name;
    private String lastName;

    public Employee(long userId, String login, String name, String lastName) {
        super(userId, login);
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //TODO
    // Methods for employee

    @Override
    public String toString() {
        return super.toString() + "Employee{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
