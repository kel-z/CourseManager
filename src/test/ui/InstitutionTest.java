package ui;

import model.Institution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void testToString() {
        assertEquals("Name: ubc\nMotto: Tuum Est", inst.toString());
    }
}