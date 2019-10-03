package model;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Institution {
    private String name;
    private String motto;
    private ResBuilding building;
    private ArrayList<Person> population;

    // MODIFIES: this
    // EFFECTS: the name of an institution is set. if name is ubc, motto is "Tuum Est"
    public Institution(String n) {
        name = n;
        motto = "";
        building = new ResBuilding("Nest");
        population = new ArrayList<Person>();

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
    // EFFECTS: takes user input to specify person to population
    public boolean add() {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("\nAdd what?");
        input = scanner.nextLine();
        if (input.toLowerCase().equals("student")) {
            System.out.println("\nName:");
            input = scanner.nextLine();
            addStudent(input);
        }
        return true;
    }

    // MODIFIES: this
    // EFFECTS: adds a university student person to the population
    public boolean addStudent(String n) {
        Person s = new UniversityStudent(n);
        population.add(s);
        return true;
    }

    // EFFECTS: return amount of people in population
    public int size() {
        return population.size();
    }

    // EFFECTS: print out the names of everyone in population
    public boolean printPopulation() {
        int i = 1;
        System.out.println("Institution population:");
        for (Person p:population) {
            System.out.println(i + ". " + p);
            i++;
        }
        return true;
    }

    // REQUIRES: txt file with name f exists
    // MODIFIES: txt file with name f
    // EFFECTS: saves population to txt file named f
    public boolean save(String f) throws IOException {
        PrintWriter writer = new PrintWriter(f,"UTF-8");
        for (Person p:population) {
            writer.println(p);
        }
        writer.close();
        return true;
    }

    // REQUIRES: txt file with name f exists
    // EFFECTS: loads population data from txt file named f
    public boolean load(String f) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(f));
        for (String n: lines) {
            addStudent(n);
        }
        return true;
    }

    // EFFECTS: returns institution name and motto
    public String toString() {
        return ("Name: " + name) + ("\nMotto: " + motto) + ("\nFire Alarms: " + building.getAlarm());
    }
}
