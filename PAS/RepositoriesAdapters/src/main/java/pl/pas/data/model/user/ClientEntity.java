package pl.pas.data.model.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientEntity extends UserEntity {

    private int age;

    public ClientEntity() {
        super();
        age = 0;
    }

    public ClientEntity(String login, String password, String name, String lastName, int age) {
        super(login, password, name, lastName);
        this.age = age;
    }

    @Override
    public String toString() {
        return "Client{" +
                "age=" + age +
                "} " + super.toString();
    }
}
