package pl.pas.model.entities.user;

import java.util.UUID;

public abstract class User {
    private UUID userId;
    private String login;
    private String name;
    private String lastName;
    private boolean active;

    public User(String login, String name, String lastName) {
        this.userId = null;
        this.login = login;
        this.active = true;
        this.name = name;
        this.lastName = lastName;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof User) {
            User user = (User) obj;
            return this.userId.equals(user.getUserId()) && this.login.equals(user.getLogin());
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", active=" + active +
                '}';
    }
}