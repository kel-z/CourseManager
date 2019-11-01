package model;

public class Professor extends Person {
    protected Subject subject;

    public Professor(String firstName, String lastName, Subject subject) {
        super(firstName, lastName);
        this.subject = subject;
    }


    // EFFECTS: prints a greeting
    public String greet() {
        String greeting = "Hello. My name is Dr. " + lastName;
        System.out.println(greeting);
        return greeting;
    }

    // EFFECTS: returns subject
    public Subject getSubject() {
        return this.subject;
    }

    // MODIFY: this
    // EFFECTS: set professor to sleep
    public void sleep() {
        isSleeping = true;
    }

    @Override
    public String toString() {
        return "Dr. " + firstName + " " + lastName + ", " + subject;
    }
}
