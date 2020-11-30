package pl.pas.model.user;

public class Administrator extends User {

    public Administrator() {
        super();
    }

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
