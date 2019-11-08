package model;

public class Professor extends Person {
    protected Subject subject;

    public Professor(String firstName, String lastName, Subject subject) {
        super(firstName, lastName);
        this.subject = subject;
    }

    // MODIFIES: this, subject
    // EFFECTS: changes subject
    public void setSubject(Subject s) {
        if (!(subject == s)) {
            subject.removeProf(this);
        }
        this.subject = s;
        s.addProf(this);
    }

    // MODIFIES: this, subject
    // EFFECTS: clears subject and removes prof from list of profs who teach the subject
    public void clearSubject() {
        if (!(subject == null)) {
            subject.removeProf(this);
            subject = null;
        }
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
