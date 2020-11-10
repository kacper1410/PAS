package pl.pas.model;

public class Client extends User {
    private String name;
    private int age;

    public Client(long userId, String login, String name, int age) {
        super(userId, login);
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return super.toString() + " Client{" +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
