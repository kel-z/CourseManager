package model;

public class UniversityStudent extends Person implements Student  {
    private double gpa;
    private boolean isStudying;

    public UniversityStudent(String firstName, String lastName, double gpa) {
        super(firstName, lastName);
        this.isStudying = false;
        this.gpa = gpa;
    }

    // REQUIRES: student is not asleep
    // EFFECTS: prints out greeting and returns it
    public String greet() {
        String greeting = "Hi! My name is " + firstName + "!";
        System.out.println(greeting);
        return greeting;
    }

    // MODIFIES: this
    // EFFECTS: puts student to sleep. false if already sleeping
    public void sleep() {
        if (!isSleeping) {
            System.out.println("I am sleeping");
            isStudying = false;
            isSleeping = true;
        }
    }

    // MODIFIES: this
    // EFFECTS: sets student to studying if not sleeping, false otherwise
    public boolean study() {
        if (isSleeping) {
            System.out.println("zzz");
            return false;
        }
        System.out.println("Studying!");
        isStudying = true;
        return true;
    }

    public String toString() {
        return firstName + " " + lastName + ", " + gpa;
    }
}
