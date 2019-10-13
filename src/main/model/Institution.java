package model;

import exceptions.MaxCapacityException;

import java.util.ArrayList;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Double.valueOf;


public class Institution {
    private String name;
    private String motto;
    private ResBuilding building;
    private ArrayList<UniversityStudent> studPopulation;
    private ArrayList<Professor> profPopulation;
    private static final int MAX_POPULATION = 50000;

    // MODIFIES: this
    // EFFECTS: the name of an institution is set. if name is ubc, motto is "Tuum Est"
    public Institution(String n) {
        name = n;
        motto = "";
        building = new ResBuilding("Nest");
        studPopulation = new ArrayList<UniversityStudent>();
        profPopulation = new ArrayList<Professor>();

        if (n.toLowerCase().equals("ubc")) {
            motto = "Tuum Est";
        }
    }

    // EFFECTS: trigger fire alarm in building
    public Boolean fire() {
        return building.tripAlarm();
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
    // EFFECTS: sets motto of institution to m
    public void setMotto(String m) {
        this.motto = m;
    }

    // EFFECTS: returns the institution's motto
    public String getMotto() {
        return this.motto;
    }

    // EFFECTS: a given string is announced
    public String announce(String a) {
        return ("PSA: " + a);
    }

    // MODIFIES: this
    // EFFECTS: adds a university student person to the population
    public boolean addStudent(String firstName, String lastName, double gpa) throws MaxCapacityException {
        if (profPopulation.size() + studPopulation.size() == MAX_POPULATION) {
            throw new MaxCapacityException();
        } else {
            UniversityStudent s = new UniversityStudent(firstName, lastName, gpa);
            studPopulation.add(s);
        }
        return true;
    }

    // EFFECTS: print out the names of everyone in population
    public boolean printPopulation() {
        int i = 1;
        System.out.println("\nInstitution population:");
        System.out.println("Students:");
        for (Student p:studPopulation) {
            System.out.println(i + ". " + p);
            i++;
        }
        i = 1;
        System.out.println("Staff:");
        for (Professor p:profPopulation) {
            System.out.println(i + ". " + p);
            i++;
        }
        return true;
    }

    // EFFECTS: return total population
    public int size() {
        return studPopulation.size() + profPopulation.size();
    }

    // REQUIRES: txt file with name f
    // MODIFIES: txt file with name f
    // EFFECTS: saves population to txt file named f
    public boolean save(String f) throws IOException {
        PrintWriter writer = new PrintWriter(f,"UTF-8");
        for (UniversityStudent s:studPopulation) {
            writer.println("STUD " + s.firstName + " " + s.lastName + " " + s.gpa);
        }
        for (Professor p:profPopulation) {
            writer.println("PROF " + p.firstName + " " + p.lastName + " " + p.subject);
        }
        writer.close();
        return true;
    }

    // REQUIRES: txt file with name f
    // EFFECTS: loads population data from txt file named f
    public boolean load(String f) throws IOException, MaxCapacityException {
        List<String> lines = Files.readAllLines(Paths.get(f));
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
                addProf(first, last, n);
            }
        }
        return true;
    }

    // MODIFIES: this
    // EFFECTS: adds a professor to the population
    public void addProf(String fn, String ln, String sub) throws MaxCapacityException {
        if (studPopulation.size() + profPopulation.size() == MAX_POPULATION) {
            throw new MaxCapacityException();
        } else {
            Professor p = new Professor(fn, ln, sub);
            profPopulation.add(p);
        }
    }

    // EFFECTS: returns institution name and motto
    public String toString() {
        return ("Name: " + name) + ("\nMotto: " + motto) + ("\nFire Alarms: " + building.getAlarm());
    }
}
