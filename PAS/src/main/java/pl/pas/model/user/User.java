package pl.pas.model.user;

public abstract class User {
    private long userId;
    private String login;
    private boolean active;

    public User(long userId, String login) {
        this.userId = userId;
        this.login = login;
        this.active = true;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", active=" + active +
                '}';
    }
}
