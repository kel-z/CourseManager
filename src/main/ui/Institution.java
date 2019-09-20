package ui;

import java.util.ArrayList;
import java.util.Scanner;

public class Institution {
    private String name;
    private String motto;
    private ArrayList<String> announceLog;

    private int fireAlarms;

    private static Scanner scanner = new Scanner(System.in);

    public Institution(String n) {
        name = n;
        fireAlarms = 0;
        motto = "";
        announceLog = new ArrayList<String>();
        if (n.toLowerCase().equals("ubc")) {
            motto = "Tuum Est";
        }
    }

    // EFFECTS: prints institution name, motto, and number of fire alarms
    public void getInfo() {
        System.out.println("Name: " + name);
        System.out.println("Motto: " + motto);
        System.out.println("Fire Alarms: " + getAlarm());
    }

    // MODIFIES: this
    // EFFECTS:
    public void announce() {
        String x;
        System.out.println("Announce what?:");
        x = scanner.nextLine();
        System.out.println("\nPSA: " + x);
        announceLog.add(x);
    }

    // EFFECTS:
    public void tripAlarm() {
        fireAlarms++;
        ringAlarm(10);
    }

    // EFFECTS:
    public void ringAlarm(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("ring");
        }
    }

    // EFFECTS: returns the number of fire alarms
    public int getAlarm() {
        return fireAlarms;
    }

    public void doStuff() {
        String input;

        while (true) {
            System.out.println("\ndo something (fire, announce, info):");
            input = scanner.nextLine();
            if (input.equals("stop")) {
                break;
            }
            if (input.equals("fire")) {
                tripAlarm();
            }
            if (input.equals("announce")) {
                announce();
            }
            if (input.equals("info")) {
                getInfo();
            }
        }
    }

    public static void main(String[] args) {
        String name;
        String input = "";
        Institution inst;
        System.out.println("Institution name:");
        name = scanner.nextLine();
        inst = new Institution(name);

        inst.doStuff();

        System.out.println("\nend");
    }

}
