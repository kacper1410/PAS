package pl.pas.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.pas.model.SignableEntity;
import pl.pas.rest.jsonb.adapters.StringToEmptyAdapter;

import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.annotation.JsonbTypeAdapter;

@AllArgsConstructor
public abstract class User implements SignableEntity {

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

    @JsonbTransient
    public long getSignablePayload() {
        return userId;
    }

    @JsonbTypeAdapter(StringToEmptyAdapter.class)
    public String getPassword() {
        return password;
    }
}
