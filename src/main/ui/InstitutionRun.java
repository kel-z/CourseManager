package ui;

import model.Institution;

import java.util.Scanner;

public class InstitutionRun {
    public InstitutionRun() {
    }

    // EFFECTS: reads user input to perform tasks
    public void run(Institution i) {
        String input;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\ndo something:");
            input = scanner.nextLine();
            if (input.equals("stop")) {
                break;
            } else if (input.equals("announce")) {
                System.out.println(i.announce("hello"));
            } else if (input.equals("info")) {
                System.out.println(i);
            } else if (input.equals("fire")) {
                i.fire();
            } else {
                System.out.println("invalid option!");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name;
        String input = "";

        InstitutionRun ins = new InstitutionRun();

        System.out.println("Institution name:");
        name = scanner.nextLine();
        Institution inst = new Institution(name);

        ins.run(inst);

        System.out.println("\nend");
    }
}
