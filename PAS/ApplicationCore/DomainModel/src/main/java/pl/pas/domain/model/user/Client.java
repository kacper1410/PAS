package pl.pas.domain.model.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client extends User {
    private int age;

    public Client() {
        super();
        age = 0;
    }

    public Client(String login, String password, String name, String lastName, int age) {
        super(login, password, name, lastName);
        this.age = age;
    }

    @Override
    public String toString() {
        return "Client{" +
                "age=" + age +
                "} " + super.toString();
    }

    @Override
    public String getAccessGroup() {
        return "Clients";
    }
}
