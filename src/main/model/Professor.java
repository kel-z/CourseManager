package model;

public class Professor extends Person {
    protected String subject;

    public Professor(String firstName, String lastName, String subject) {
        super(firstName, lastName);
        this.subject = subject;
    }

    // EFFECTS: prints a greeting
    public String greet() {
        String greeting = "Hello. My name is Dr. " + lastName;
        System.out.println(greeting);
        return greeting;
    }

    // MODIFY: this
    // EFFECTS: set professor to sleep
    public void sleep() {
        isSleeping = true;
    }

    public String toString() {
        return "Dr. " + firstName + " " + lastName + ", " + subject;
    }
}
