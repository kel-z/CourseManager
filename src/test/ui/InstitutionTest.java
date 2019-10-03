package ui;

import model.Institution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void testAddStudent() {
        assertTrue(inst.addStudent("John"));
        assertEquals(1, inst.size());
        inst.addStudent("Bob");
        inst.addStudent("Mack");
        assertEquals(3, inst.size());
    }

    @Test
    public void testPrintPopulation() {
        assertTrue(inst.printPopulation());
    }

    @Test
    public void testSave() throws IOException {
        inst.addStudent("John");
        inst.addStudent("Bob");
        assertTrue(inst.save("testdata.txt"));
    }

    @Test
    public void testLoad() throws IOException {
        assertTrue(inst.load("testdata.txt"));
        assertEquals(2, inst.size());
    }

    @Test
    public void testToString() {
        assertEquals("Name: ubc\nMotto: Tuum Est\nFire Alarms: 0", inst.toString());
    }
}