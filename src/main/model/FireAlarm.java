package model;

public class FireAlarm {
    int fireAlarms;

    public FireAlarm() {
        fireAlarms = 0;
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
