package pl.pas.model.entities.user;

public class Administrator extends User {

    public Administrator(String login, String name, String lastName) {
        super(login, name, lastName);
    }

    //TODO
    // Methods for administrator

    @Override
    public String toString() {
        return "Administrator{} " + super.toString();
    }
}
