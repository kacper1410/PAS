package pl.pas.data.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class UserEntity {

    private long userId;
    private String login;
    private String password;
    private String name;
    private String lastName;
    private boolean active;

    public UserEntity() {
        userId = 0;
        login = "";
        password = "";
        name = "";
        lastName = "";
        active = true;
    }

    public UserEntity(String login, String password, String name, String lastName) {
        this.userId = 0;
        this.login = login;
        this.password = password;
        this.active = true;
        this.name = name;
        this.lastName = lastName;
    }
}
