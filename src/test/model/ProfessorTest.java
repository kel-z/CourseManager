package model;

import model.Professor;
import model.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfessorTest {
    Professor prof;
    Subject math;
    @BeforeEach
    public void beforeEach() {
        math = new Subject("Math");
        prof = new Professor("John", "Smith", math);
    }

    @Test
    public void greetTest() {
        assertEquals("Hello. My name is Dr. Smith", prof.greet());
    }

    @Test
    public void testGetSubject() {
        assertEquals(math, prof.getSubject());
    }

    @Test
    public void testSleep() {
        prof.sleep();
        assertTrue(prof.isSleeping());
    }

    @Test
    public void testToString() {
        assertEquals("Dr. John Smith, Math", prof.toString());
    }
}
