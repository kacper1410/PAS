package pl.pas.data.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pas.data.model.SignableEntity;

import javax.json.bind.annotation.JsonbTransient;

//import pl.pas.rest.jsonb.adapters.StringToEmptyAdapter;

@Data
@AllArgsConstructor
public abstract class User implements SignableEntity {

    private long userId;
    private String login;
    private String password;
    private String name;
    private String lastName;
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
