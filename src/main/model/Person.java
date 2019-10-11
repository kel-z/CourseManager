package model;

public abstract class Person {
    protected String firstName;
    protected String lastName;
    protected boolean isSleeping;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isSleeping = false;
    }

    public abstract String greet();

    public String goodbye() {
        String goodbye = "I'll see you later";
        System.out.println(goodbye);
        return goodbye;
    }

    public abstract void sleep();

    // getter
    public boolean isSleeping() {
        return isSleeping;
    }

    public String toString() {
        return firstName + ", " + lastName;
    }
}
