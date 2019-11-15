package ui;

import exceptions.InvPersonException;
import exceptions.InvSubjectException;
import exceptions.MaxCapacityException;
import model.Institution;
import model.InstitutionMonitor;
import model.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InstitutionTest {
    private Institution inst;
    @BeforeEach
    public void beforeEach() {
        inst = new Institution("ubc", new InstitutionMonitor());
    }

    @Test
    public void testFire() {
        assertTrue(inst.fire());
        assertEquals(1, inst.getAlarms());
        inst.fire();
        inst.fire();
        assertEquals(3, inst.getAlarms());
    }

    @Test
    public void testAddStudent() throws MaxCapacityException {
        assertTrue(inst.addStudent("John", "Henry", 2.2));
        assertEquals(1, inst.size());
        inst.addStudent("Bob", "Smith", 1.5);
        inst.addStudent("Mack", "More", 4.0);
        assertEquals(3, inst.size());
    }

    @Test
    public void testAddProf() throws MaxCapacityException {
        Subject math = new Subject("Math");
        inst.addProf("John", "Henry", math);
        assertEquals(1, inst.size());
        inst.addProf("Bob", "Smith", math);
        inst.addProf("Mack", "More", math);
        assertEquals(3, inst.size());
    }

    @Test
    public void testRemoveProf() throws MaxCapacityException, InvPersonException {
        Subject math = new Subject("Math");
        inst.addProf("John", "Henry", math);
        assertEquals(1, inst.size());
        inst.removeProf("John");
        assertEquals(0, inst.size());
        try {
            inst.removeProf("Jack");
            fail("Should have failed");
        } catch (InvPersonException e) {

        }
    }

    @Test
    public void testSubjectGet() throws MaxCapacityException {
        Subject math = new Subject("math");
        inst.addProf("John", "Mark", math);
        assertEquals(math, inst.subjectGet("math"));
        assertEquals(new Subject("english"), inst.subjectGet("english"));
    }

    @Test
    public void testGetSubjectList() throws MaxCapacityException {
        Subject math = new Subject("math");
        inst.addProf("Mark", "John", math);
        inst.addProf("Jhon", "Mark", math);
        try {
            inst.getSubjectList("english");
            fail("Should have failed");
        } catch (InvSubjectException e) {

        }
        try {
            inst.getSubjectList("math");
        } catch (InvSubjectException e) {
            fail("Should not have failed");
        }
    }

    @Test
    public void testGetName() {
        assertEquals("ubc", inst.getName());
    }

    @Test
    public void testAddProfException() {
        Subject math = new Subject("Math");
        try {
            for (int i = 0; i<50001; i++) {
                inst.addProf("a", "b", math);
            }
            fail("Did not go beyond max capacity.");
        } catch (MaxCapacityException e) {
            System.out.println("Great!");
        }
    }

    @Test
    public void testAddStudException() {
        try {
            for (int i = 0; i<50001; i++) {
                inst.addStudent("a", "b", 2.0);
            }
            fail("Did not go beyond max capacity.");
        } catch (MaxCapacityException e) {
            System.out.println("Great!");
        }
    }

    @Test
    public void testPrintPopulation() {
        assertTrue(inst.printPopulation());
    }

    @Test
    public void testSave() throws IOException, MaxCapacityException {
        Subject math = new Subject("Math");
        inst.addStudent("John", "Doe", 5.0);
        inst.addStudent("Bob", "Stog", 3.2);
        inst.addProf("Jack", "Dan", math);
        assertTrue(inst.save("testdata.txt"));
        Institution inst2 = new Institution("ubc", new InstitutionMonitor());
        assertTrue(inst2.load("testdata.txt"));
        assertEquals(3, inst2.size());
        inst2.printPopulation();
    }

    @Test
    public void testToString() {
        assertEquals("Name: ubc\nFire Alarms: 0", inst.toString());
    }
}