package ui;

import model.Professor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfessorTest {
    Professor prof;
    @BeforeEach
    public void beforeEach() {
        prof = new Professor("John", "Smith", "Math");
    }

    @Test
    public void greetTest() {
        assertEquals("Hello. My name is Dr. Smith", prof.greet());
    }

    @Test
    public void sleep() {
        prof.sleep();
        assertTrue(prof.isSleeping());
    }
}
