package pl.pas.model.user;

public class Administrator extends User {

    public Administrator() {
        super();
    }

    public Administrator(String login, String password, String name, String lastName) {
        super(login, password, name, lastName);
    }

    @Override
    public String toString() {
        return "Administrator{} " + super.toString();
    }

    @Override
    public String getAccessGroup() {
        return "Administrators";
    }
}
