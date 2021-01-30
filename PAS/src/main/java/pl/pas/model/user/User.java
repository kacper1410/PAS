package pl.pas.model.user;

import lombok.*;
import pl.pas.rest.jsonb.adapters.StringToEmptyAdapter;

import javax.json.bind.annotation.JsonbTypeAdapter;

@AllArgsConstructor
public abstract class User {

    @Getter
    @Setter
    private long userId;

    @Getter
    @Setter
    private String login;

    @Setter
    private String password;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private boolean active;

    public User() {
        userId = 0;
        login = "";
        password = "";
        name = "";
        lastName = "";
        active = true;
    }

    public User(String login, String password, String name, String lastName) {
        this.userId = 0;
        this.login = login;
        this.password = password;
        this.active = true;
        this.name = name;
        this.lastName = lastName;
    }

    @JsonbTypeAdapter(StringToEmptyAdapter.class)
    public String getPassword() {
        return password;
    }
}
