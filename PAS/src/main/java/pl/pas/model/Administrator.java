package pl.pas.model;

public class Administrator extends Employee {
    private String name;
    private String lastName;

    public Administrator(long userId, String login, String name, String lastName) {
        super(userId, login, name, lastName);
    }

    //TODO
    // Methods for administrator

    @Override
    public String toString() {
        return "Administrator{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
