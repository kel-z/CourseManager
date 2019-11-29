package model;

import exceptions.InvPersonException;
import exceptions.InvSubjectException;
import exceptions.MaxCapacityException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.lang.Double.parseDouble;
import static java.lang.Double.valueOf;


public class Institution extends Observable {
    boolean loading;
    private String name;
    private ResBuilding building;
    private ArrayList<UniversityStudent> studPopulation;
    private ArrayList<Subject> subjects;
    private Map<Subject, ArrayList<Professor>> subjectList;
    private static final int MAX_POPULATION = 50000;

    // MODIFIES: this
    // EFFECTS: the name of an institution is set. if name is ubc, motto is "Tuum Est"
    public Institution(String n, InstitutionMonitor obs) {
        name = n;
        building = new ResBuilding("Nest");
        studPopulation = new ArrayList<UniversityStudent>();
        subjects = new ArrayList<Subject>();
        subjectList = new HashMap<>();
        loading = true;
        addObserver(obs);
    }

    // EFFECTS: trigger fire alarm in building
    public Boolean fire() {
        return building.fire();
    }

    // EFFECTS: return number of fire alarms in building
    public int getAlarms() {
        return building.getAlarm();
    }

    // EFFECTS: returns the institution's name
    public String getName() {
        return this.name;
    }

    // MODIFIES: this
    // EFFECTS: adds a university student person to the population
    public boolean addStudent(String firstName, String lastName, double gpa) throws MaxCapacityException {
        if (size() == MAX_POPULATION) {
            throw new MaxCapacityException();
        } else {
            UniversityStudent s = new UniversityStudent(firstName, lastName, gpa);
            studPopulation.add(s);
            if (!loading) {
                setChanged();
                notifyObservers("addS");
            }
        }
        return true;
    }

//    // EFFECTS: print out the names of everyone in population
//    public boolean printPopulation() {
//        int i = 1;
//        System.out.println("\nInstitution population: " + size());
//        System.out.println("Students:");
//        for (Student p:studPopulation) {
//            System.out.println(i + ". " + p);
//            i++;
//        }
//        i = 1;
//        System.out.println("Staff:");
//        for (Subject s : subjects) {
//            for (Professor p : subjectList.get(s)) {
//                System.out.println(i + ". " + p);
//                i++;
//            }
//        }
//        return true;
//    }

    // EFFECTS: return the names of everyone in population
    public String printPopulation() {
        String str = "<html>";
        int i = 1;
        str += ("Institution population: " + size() + "<br/><br/>");
        str += ("Students: <br/>");
        for (Student p:studPopulation) {
            str += (i + ". " + p + "<br/>");
            i++;
        }
        i = 1;
        str += ("<br/>Staff: <br/>");
        for (Subject s : subjects) {
            for (Professor p : subjectList.get(s)) {
                str += (i + ". " + p + "<br/>");
                i++;
            }
        }
        return str;
    }

    // EFFECTS: return total population
    public int size() {
        int numProfs = 0;
        for (Subject s : subjects) {
            ArrayList<Professor> profs = subjectList.get(s);
            for (Professor p : profs) {
                numProfs++;
            }
        }
        return studPopulation.size() + numProfs;
    }

    // REQUIRES: txt file with name f
    // MODIFIES: txt file with name f
    // EFFECTS: saves population to txt file named f
    public boolean save(String f) throws IOException {
        //PrintWriter writer = new PrintWriter(f,"UTF-8");
        PrintWriter writer = new PrintWriter(".\\src\\main\\data\\" + f,"UTF-8");
        for (UniversityStudent s:studPopulation) {
            writer.println("STUD " + s.firstName + " " + s.lastName + " " + s.gpa);
        }
        for (Subject s : subjects) {
            for (Professor p : subjectList.get(s)) {
                writer.println("PROF " + p.firstName + " " + p.lastName + " " + p.subject);
            }
        }
        writer.close();
        return true;
    }

    // REQUIRES: txt file with name f
    // EFFECTS: loads population data from txt file named f
    public boolean load(String f) throws IOException, MaxCapacityException {
        List<String> lines = Files.readAllLines(Paths.get(".\\src\\main\\data\\" + f));
        for (String n: lines) {
            String pre = n.substring(0, n.indexOf(" "));
            n = n.substring(n.indexOf(" ") + 1);
            String first = n.substring(0, n.indexOf(" "));
            n = n.substring(n.indexOf(" ") + 1);
            String last = n.substring(0, n.indexOf(" "));
            n = n.substring(n.indexOf(" ") + 1);
            if (pre.equals("STUD")) {
                double gpa = parseDouble(n);
                addStudent(first, last, gpa);
            } else {
                Subject subject = subjectGet(n);
                addProf(first, last, subject);
            }
        }
        loading = false;
        return true;
    }

    // MODIFIES: this
    // EFFECTS: adds a professor to the population
    public void addProf(String fn, String ln, Subject sub) throws MaxCapacityException {
        if (size() == MAX_POPULATION) {
            throw new MaxCapacityException();
        } else {
            if (!subjectList.containsKey(sub)) {
                ArrayList<Professor> prof = new ArrayList<Professor>();
                prof.add(new Professor(fn, ln, sub));
                subjects.add(sub);
                subjectList.put(sub, prof);
            } else {
                ArrayList<Professor> prof = subjectList.get(sub);
                prof.add(new Professor(fn, ln, sub));
            }
            if (!loading) {
                setChanged();
                notifyObservers("addP");
            }
            sub.addProf(new Professor(fn, ln, sub));
            //System.out.println(subjectList);
        }
    }

    // REQUIRES: no professor has the same first name
    // MODIFIES: this
    // EFFECTS: removes professor given first name
    public String removeProf(String fn) throws InvPersonException {
        for (Subject s : subjects) {
            ArrayList<Professor> profs = subjectList.get(s);
            for (Professor p : profs) {
                if (p.firstName.toLowerCase().equals(fn.toLowerCase())) {
                    System.out.println((p.getSubject().numProfs() - 1) + " prof(s) teach "
                            + p.getSubject().getSubject().toLowerCase() + " now.");
                    profs.remove(p);
                    s.removeProf(p);
                    setChanged();
                    notifyObservers("removeP");
                    return (p.getSubject().numProfs() - 1) + " prof(s) teach "
                            + p.getSubject().getSubject().toLowerCase() + " now.";
                }
            }
        }
        throw new InvPersonException();
    }

    // EFFECTS: returns subject class with the name sub
    public Subject subjectGet(String sub) {
        for (Subject s : subjects) {
            if (s.getSubject().toLowerCase().equals(sub.toLowerCase())) {
                return s;
            }
        }
        return new Subject(sub);
    }

    // EFFECTS: print out professors that specialize in a given subject
    public void getSubjectList(String sub) throws InvSubjectException {
        for (Subject s : subjects) {
            if (s.getSubject().toLowerCase().equals(sub.toLowerCase())) {
                s.printProf();
                return;
            }
        }
        throw new InvSubjectException();
    }

    // EFFECTS: returns institution name and motto
    public String toString() {
        return ("Name: " + name) + ("\nFire Alarms: " + building.getAlarm());
    }
}
