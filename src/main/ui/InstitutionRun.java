package ui;

import model.Institution;
import model.Person;
import model.Student;
import model.UniversityStudent;

import java.io.IOException;
import java.util.Scanner;

public class InstitutionRun {
    Scanner scanner;
    String input;
    Institution inst;

    public InstitutionRun() {
        input = "";
        scanner = new Scanner(System.in);
        System.out.println("Institution name:");
        inst = new Institution(scanner.nextLine());
    }

    // MODIFIES: this
    // EFFECTS: takes user input to add a professor
    public void addProf() {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("\nFirst name: ");
        String firstName = scanner.nextLine();
        System.out.println("\nLast name: ");
        String lastName = scanner.nextLine();
        System.out.println("\nSubject: ");
        String subject = scanner.nextLine();
        inst.addProf(firstName, lastName, subject);
    }

    // MODIFIES: inst
    // EFFECTS: takes user input to specify person to add to population
    public boolean add() {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("\nAdd what?");
        input = scanner.nextLine();
        if (input.toLowerCase().equals("student")) {
            System.out.println("\nFirst name: ");
            String firstName = scanner.nextLine();
            System.out.println("\nLast name: ");
            String lastName = scanner.nextLine();
            System.out.println("\ngpa: ");
            double gpa = scanner.nextDouble();
            scanner.nextLine();
            inst.addStudent(firstName, lastName, gpa);
        } else if (input.toLowerCase().equals("professor")) {
            addProf();
        }
        return true;
    }

    // EFFECTS: reads user input to perform tasks
    public void run(Institution i) throws IOException {
        while (true) {
            System.out.println("\ndo something:");
            input = scanner.nextLine();
            if (input.equals("stop")) {
                // i.save("data.txt");  // TODO: fix save to work with professor class
                break;
            } else if (input.equals("announce")) {
                System.out.println(i.announce("hello"));
            } else if (input.equals("info")) {
                System.out.println(i);
            } else if (input.equals("fire")) {
                i.fire();
            } else if (input.equals("add")) {
                add();
            } else {
                System.out.println("invalid option!");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String name;
        String input = "";

        InstitutionRun ins = new InstitutionRun();


        // ins.inst.load("data.txt"); // TODO: fix load to work with professor class

        ins.run(ins.inst);
        ins.inst.printPopulation();
        System.out.println("\nend");
    }
}
