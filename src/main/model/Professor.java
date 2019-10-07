package model;

public class Professor extends Person {
    private String subject;

    public Professor(String firstName, String lastName, String subject) {
        super(firstName, lastName);
        this.subject = subject;
    }

    public String greet() {
        String greeting = "Hello. My name is Dr. " + lastName;
        System.out.println(greeting);
        return greeting;
    }

    public void sleep() {
        isSleeping = true;
    }

}
