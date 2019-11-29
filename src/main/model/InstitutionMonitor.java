package model;

import java.util.Observable;
import java.util.Observer;

public class InstitutionMonitor implements Observer {
    private int addedStud = 0;
    private int addedProf = 0;
    private int removeProf = 0;

    public InstitutionMonitor() {
    }

    // EFFECTS: prints out the stats collected
    public void printStats() {
        System.out.println();
        System.out.println("Students added: " + addedStud);
        System.out.println("Professors added: " + addedProf);
        System.out.println("Professors removed: " + removeProf);
    }

    // MODIFIES: this
    // EFFECTS: increment respective counter every time population is changed
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("***population change***");
        if (arg.equals("addS")) {
            addedStud++;
        } else if (arg.equals("addP")) {
            addedProf++;
        } else if (arg.equals("removeP")) {
            removeProf++;
        }
    }
}
