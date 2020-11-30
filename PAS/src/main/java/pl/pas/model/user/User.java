package pl.pas.model.user;

public abstract class User {
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

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getUserId() {
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
            return this.userId == user.userId && this.login.equals(user.getLogin());
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
