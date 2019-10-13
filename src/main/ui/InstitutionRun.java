package ui;

import exceptions.InvOptionException;
import exceptions.InvPersonException;
import exceptions.MaxCapacityException;
import model.Institution;

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
        String subject = scanner.nextLine();
        inst.addProf(firstName, lastName, subject);
    }

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
        input = scanner.nextLine();
        if (input.toLowerCase().equals("student")) {
            addStud();
        } else if (input.toLowerCase().equals("professor")) {
            addProf();
        } else {
            throw new InvPersonException();
        }
        return true;
    }

    // EFFECTS: reads user input to perform tasks
    public void run(Institution i) throws IOException, InvPersonException, InvOptionException, MaxCapacityException {
        System.out.println("\ndo something:");
        input = scanner.nextLine();
        if (input.equals("stop")) {
            i.save("data.txt");
            flag = false;
        } else if (input.equals("announce")) {
            System.out.println(i.announce("hello"));
        } else if (input.equals("info")) {
            System.out.println(i);
        } else if (input.equals("fire")) {
            i.fire();
        } else if (input.equals("add")) {
            add();
        } else {
            throw new InvOptionException();
        }
    }

    public static void main(String[] args) throws IOException, MaxCapacityException {
        String name;
        InstitutionRun ins = new InstitutionRun();
        ins.inst.load("data.txt");
        while (ins.flag) {
            try {
                ins.run(ins.inst);
            } catch (InvPersonException e) {
                System.out.println("Invalid person!");
            } catch (InvOptionException e) {
                System.out.println("Invalid option!");
            } catch (MaxCapacityException e) {
                System.out.println("Capacity maxed!");
            } finally {
                ins.iterations++;
            }
        }
        ins.inst.printPopulation();
        System.out.println("\nend");
    }
}
