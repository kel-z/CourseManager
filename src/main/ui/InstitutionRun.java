package ui;

import exceptions.InvOptionException;
import exceptions.InvPersonException;
import exceptions.InvSubjectException;
import exceptions.MaxCapacityException;
import model.Institution;
import model.Subject;

import java.io.IOException;
import java.util.Scanner;

public class InstitutionRun {
    Scanner scanner;
    String input;
    Institution inst;
    Boolean flag;
    int iterations;

    public InstitutionRun() {
        input = "";
        scanner = new Scanner(System.in);
        System.out.println("Institution name:");
        inst = new Institution(scanner.nextLine());
        flag = true;
        iterations = 0;
    }

    // MODIFIES: this
    // EFFECTS: takes user input to add a professor
    public void addProf() throws MaxCapacityException {
        System.out.println("\nFirst name: ");
        String firstName = scanner.nextLine();
        System.out.println("\nLast name: ");
        String lastName = scanner.nextLine();
        System.out.println("\nSubject: ");
        String input = scanner.nextLine();
        Subject subject = inst.subjectGet(input);
        inst.addProf(firstName, lastName, subject);
    }

    // MODIFIES: this
    // EFFECTS: takes user input to add a student
    public void addStud() throws MaxCapacityException {
        System.out.println("\nFirst name: ");
        String firstName = scanner.nextLine();
        System.out.println("\nLast name: ");
        String lastName = scanner.nextLine();
        System.out.println("\ngpa: ");
        double gpa = scanner.nextDouble();
        scanner.nextLine();
        inst.addStudent(firstName, lastName, gpa);
    }

    // MODIFIES: inst
    // EFFECTS: takes user input to specify person to add to population
    public boolean add() throws InvPersonException, MaxCapacityException {
        System.out.println("\nAdd what?");
        input = scanner.nextLine().toLowerCase();
        if (input.equals("student")) {
            addStud();
        } else if (input.equals("professor")) {
            addProf();
        } else {
            throw new InvPersonException();
        }
        return true;
    }

    public void remove() throws InvPersonException {
        System.out.println("\nRemove what?");
        input = scanner.nextLine().toLowerCase();
        if (input.equals("professor")) {
            System.out.println("\nRemove who? (first name)");
            input = scanner.nextLine().toLowerCase();
            inst.removeProf(input);
        }
    }

    // MODIFIES: inst
    // EFFECTS: takes user input to get info
    public void info() throws InvSubjectException {
        System.out.println("\nGet what?");
        input = scanner.nextLine().toLowerCase();
        if (input.equals("info")) {
            System.out.println(inst);
        } else if (input.equals("subject")) {
            System.out.println("\nWhich subject?");
            input = scanner.nextLine();
            inst.getSubjectList(input);
        }
    }

    // EFFECTS: reads user input to perform tasks
    public void run(Institution i) throws IOException, InvPersonException, InvOptionException,
            MaxCapacityException, InvSubjectException {
        System.out.println("\nDo something:");
        input = scanner.nextLine().toLowerCase();
        if (input.equals("stop")) {
            i.save("data.txt");
            flag = false;
        } else if (input.equals("remove")) {
            remove();
        } else if (input.equals("info")) {
            info();
        } else if (input.equals("fire")) {
            i.fire();
        } else if (input.equals("add")) {
            add();
        } else {
            throw new InvOptionException();
        }
    }

    public static void main(String[] args) throws IOException, MaxCapacityException {
        InstitutionRun ins = new InstitutionRun();
        ins.inst.load("data.txt");
        while (ins.flag) {
            try {
                ins.run(ins.inst);
            } catch (MaxCapacityException e) {
                System.out.println("Capacity maxed!");
            } catch (InvSubjectException e) {
                System.out.println("No professors for subject!");
            } catch (Exception e) {
                System.out.println("Invalid option!");
            } finally {
                ins.iterations++;
            }
        }
        ins.inst.printPopulation();
        System.out.println("\nend");
    }
}
