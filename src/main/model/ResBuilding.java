package model;

public class ResBuilding implements Building {
    private String name;
    private FireAlarm fireAlarm;

    // MODIFIES: this
    // EFFECTS: the name is set
    public ResBuilding(String n) {
        this.name = n;
        fireAlarm = new FireAlarm();
    }

    // MODIFY: this
    // EFFECTS: changes building name to n
    public void setName(String n) {
        this.name = n;
    }

    // EFFECTS: returns name of building
    public String getName() {
        return name;
    }

    // EFFECTS: returns number of fire alarms
    public int getAlarm() {
        return fireAlarm.getAlarm();
    }

    // EFFECTS: trigger fire alarm
    public boolean fire() {
        return fireAlarm.tripAlarm();
    }
}
