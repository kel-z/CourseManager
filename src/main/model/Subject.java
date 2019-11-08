package model;

import java.util.ArrayList;
import java.util.Objects;

public class Subject {
    private String subject;
    private ArrayList<Professor> profs;

    public Subject(String sub) {
        this.subject = sub.toUpperCase().substring(0, 1) + sub.toLowerCase().substring(1);
        profs = new ArrayList<Professor>();
    }

    // MODIFIES: this
    // EFFECTS: adds p to list of professors who teach the subject
    public void addProf(Professor p) {
        if (!profs.contains(p)) {
            profs.add(p);
            p.setSubject(this);
        }
    }

    // EFFECTS: returns the number of profs who teach the subject
    public int numProfs() {
        return profs.size();
    }

    // EFFECTS: prints the profs who teach the subject
    public String printProf() {
        String s = "";
        for (Professor p : profs) {
            s += p;
            System.out.println(p);
        }
        return s;
    }

    // REQUIRES: prof teaches subject
    // MODIFIES: this
    // EFFECTS: removes p from profs who teach the subject
    public void removeProf(Professor p) {
        if (profs.contains(p)) {
            profs.remove(p);
            p.clearSubject();
        }
    }

    // EFFECTS: return subject name
    public String getSubject() {
        return subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Subject subject1 = (Subject) o;
        return subject.equals(subject1.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject);
    }

    @Override
    public String toString() {
        return subject;
    }
}
