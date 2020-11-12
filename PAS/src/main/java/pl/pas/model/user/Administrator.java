package pl.pas.model.user;

public class Administrator extends User {
    private String name;
    private String lastName;

    public Administrator(String login, String name, String lastName) {
        super(login, name, lastName);
        this.name = name;
        this.lastName = lastName;
    }

    //TODO
    // Methods for administrator


    @Override
    public String toString() {
        return "Administrator{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                "} " + super.toString();
    }
}
