package model;

public class UniversityStudent implements Person, Student {
    String name;
    boolean isSleeping;
    boolean isStudying;

    public UniversityStudent(String n) {
        name = n;
        boolean isSleeping = false;
        boolean isStudying = false;
    }

    // REQUIRES: student is not asleep
    // EFFECTS: prints out greeting and returns it
    public String greet() {
        String greeting = "Hi! My name is " + name + ".";
        System.out.println(greeting);
        return greeting;
    }

    // REQUIRES: student is not asleep
    // EFFECTS: prints out a goodbye message and returns it
    public String goodbye() {
        String bye = "I'll see you later.";
        System.out.println(bye);
        return bye;
    }

    // MODIFIES: this
    // EFFECTS: puts student to sleep. false if already sleeping
    public boolean sleep() {
        if (!isSleeping) {
            System.out.println("I am sleeping");
            isStudying = false;
            isSleeping = true;
            return true;
        }
        return false;
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

    // getter
    public String toString() {
        return name;
    }
}
