package model;

public class Building {
    private String name;
    private int fireAlarms;

    public Building(String n) {
        this.name = name;
        fireAlarms = 0;
    }

    // EFFECTS: changes building name to n
    public void setName(String n) {
        this.name = n;
    }

    // EFFECTS: returns name of building
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: increments fire alarm counter by 1 and rings alarm
    public boolean tripAlarm() {
        fireAlarms++;
        return ringAlarm(10);
    }

    // REQUIRES: n > 0
    // EFFECTS: prints "ring" n amount of times
    public boolean ringAlarm(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("ring");
        }
        return true;
    }

    // EFFECTS: returns number of fire alarms
    public int getAlarm() {
        return fireAlarms;
    }
}
