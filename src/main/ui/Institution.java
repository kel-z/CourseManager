package ui;

import java.util.Scanner;
// to do: Res class, maybe define constant ring amount for each fire alarm (amt of times ring printed)

public class Institution {
    private String name;
    private String motto;

    private int fireAlarms;

    public Institution(String n) {
        name = n;
        fireAlarms = 0;
        motto = "";
        if (n.toLowerCase().equals("ubc")) {
            motto = "Tuum Est";
        }
    }

    public void getInfo() {
        System.out.println("Name: " + name);
        System.out.println("Motto: " + motto);
        System.out.println("Fire Alarms: " + getAlarm());
    }

    public void announce(String a) {
        System.out.println("PSA: " + a);
    }

    public void tripAlarm() {
        fireAlarms++;
        ringAlarm(10);
    }

    public void ringAlarm(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("ring");
        }
        System.out.println("over");
    }

    public int getAlarm() {
        return fireAlarms;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name;
        String input = "";

        System.out.println("Institution name:");
        name = scanner.nextLine();
        Institution ubc = new Institution(name);
        while (true) {
            System.out.println("do something:");
            input = scanner.nextLine();
            if (input.equals("stop")) {
                System.out.println();
                break;
            }
            if (input.equals("fire")) {
                ubc.tripAlarm();
            }
            if (input.equals("announce")) {
                ubc.announce("hello");
            }
            if (input.equals("info")) {
                ubc.getInfo();
            }
            System.out.println();
        }
        System.out.println("end");
    }

}
