public class pet {
    private String name;
    private int age;

    public pet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getDetails() {
        return "Name: " + name + ", Age: " + age;
    }

    public String makeSound() {
        return "Pets make sounds!";
    }
}