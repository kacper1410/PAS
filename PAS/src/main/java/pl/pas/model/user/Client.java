package pl.pas.model.user;

public class Client extends User {
    private int age;

    public Client(String login, String name, String lastName, int age) {
        super(login, name, lastName);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Client{" +
                "age=" + age +
                "} " + super.toString();
    }
}
