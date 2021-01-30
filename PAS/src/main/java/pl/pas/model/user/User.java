package pl.pas.model.user;

import lombok.*;
import pl.pas.model.SignableEntity;

import javax.json.bind.annotation.JsonbTransient;

@Data
public abstract class User implements SignableEntity {
    private long userId;
    private String login;
    private String name;
    private String lastName;
    private boolean active;

    public User() {
        userId = 0;
        login = "";
        name = "";
        lastName = "";
        active = true;
    }

    public User(String login, String name, String lastName) {
        this.userId = 0;
        this.login = login;
        this.active = true;
        this.name = name;
        this.lastName = lastName;
    }

    @JsonbTransient
    public long getSignablePayload() {
        return userId;
    }
}
