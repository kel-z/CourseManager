package ui;

import exceptions.MaxCapacityException;
import model.Institution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InstitutionTest {
    private Institution inst;
    @BeforeEach
    public void beforeEach() {
        inst = new Institution("ubc");
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
    public void testSetMotto() {
        Institution inst0 = new Institution("ubyssey");
        assertEquals("", inst0.getMotto());
        inst0.setMotto("String");
        assertEquals("String", inst0.getMotto());
    }

    @Test
    public void testAnnounce() {
        assertEquals("PSA: ", inst.announce(""));
        assertEquals("PSA: Hello", inst.announce("Hello"));
        assertEquals("PSA: Goodbye all", inst.announce("Goodbye all"));
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
        inst.addProf("John", "Henry", "Math");
        assertEquals(1, inst.size());
        inst.addProf("Bob", "Smith", "Math");
        inst.addProf("Mack", "More", "Math");
        assertEquals(3, inst.size());
    }

    @Test
    public void testAddProfException() {
        try {
            for (int i = 0; i<50001; i++) {
                inst.addProf("a", "b", "math");
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
        inst.addStudent("John", "Doe", 5.0);
        inst.addStudent("Bob", "Stog", 3.2);
        inst.addProf("Jack", "Dan", "Math");
        assertTrue(inst.save("testdata.txt"));
        Institution inst2 = new Institution("ubc");
        assertTrue(inst2.load("testdata.txt"));
        assertEquals(3, inst2.size());
        inst2.printPopulation();
    }

    @Test
    public void testToString() {
        assertEquals("Name: ubc\nMotto: Tuum Est\nFire Alarms: 0", inst.toString());
    }
}