package ui;

import model.Institution;

import java.io.IOException;
import java.util.Scanner;

public class InstitutionRun {
    Scanner scanner;
    String input;

    public InstitutionRun() {
        input = "";
        scanner = new Scanner(System.in);;
    }

    // EFFECTS: reads user input to perform tasks
    public void run(Institution i) throws IOException {
        while (true) {
            System.out.println("\ndo something:");
            input = scanner.nextLine();
            if (input.equals("stop")) {
                i.save("data.txt");
                break;
            } else if (input.equals("announce")) {
                System.out.println(i.announce("hello"));
            } else if (input.equals("info")) {
                System.out.println(i);
            } else if (input.equals("fire")) {
                i.fire();
            } else if (input.equals("add")) {
                i.add();
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

        System.out.println("Institution name:");
        name = scanner.nextLine();
        Institution inst = new Institution(name);
        inst.load("data.txt");

        ins.run(inst);

        inst.printPopulation();
        System.out.println("\nend");
    }
}
