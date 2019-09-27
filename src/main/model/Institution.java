package model;

public class Institution {
    private String name;
    private String motto;
    private Building building;

    // MODIFIES: this
    // EFFECTS: the name of an institution is set. if name is ubc, motto is "Tuum Est"
    public Institution(String n) {
        name = n;
        motto = "";
        building = new Building("Nest");
        if (n.toLowerCase().equals("ubc")) {
            motto = "Tuum Est";
        }
    }

    // EFFECTS: trigger fire alarm in building
    public Boolean fire() {
        return building.tripAlarm();
    }

    // EFFECTS: return number of fire alarms in building
    public int getAlarms() {
        return building.getAlarm();
    }

    // EFFECTS: returns the institution's name
    public String getName() {
        return this.name;
    }

    // MODIFIES: this
    // EFFECTS: sets motto of institution to m
    public void setMotto(String m) {
        this.motto = m;
    }

    // EFFECTS: returns the institution's motto
    public String getMotto() {
        return this.motto;
    }

    // EFFECTS: a given string is announced
    public String announce(String a) {
        return ("PSA: " + a);
    }

    // EFFECTS: returns institution name and motto
    public String toString() {
        return ("Name: " + name) + ("\nMotto: " + motto);
    }
}
