package pl.pas.model.user;

public class Administrator extends Employee {

    public Administrator(long userId, String login, String name, String lastName) {
        super(userId, login, name, lastName);
    }

    //TODO
    // Methods for administrator

    @Override
    public String toString() {
        return super.toString();
    }
}
