package pl.pas.data.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.pas.data.model.SignableEntity;

import javax.json.bind.annotation.JsonbTransient;

//import pl.pas.rest.jsonb.adapters.StringToEmptyAdapter;

@AllArgsConstructor
public abstract class User implements SignableEntity {

    @Getter
    @Setter
    private long userId;

    @Getter
    @Setter
    private String login;

    @Setter
    @Getter
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

    public abstract String getAccessGroup();

    @JsonbTransient
    public long getSignablePayload() {
        return userId;
    }

//    @JsonbTypeAdapter(StringToEmptyAdapter.class)
//    public String getPassword() {
//        return password;
//    }
}
