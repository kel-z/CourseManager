package ui;

import model.UniversityStudent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UniversityStudentTest {
    UniversityStudent stud;
    @BeforeEach
    public void beforeEach() {
        stud = new UniversityStudent("John", "Mulaney", 0.0);
    }

    @Test
    public void testGreet() {
        String greeting = "Hi! My name is John!";
        assertEquals(greeting, stud.greet());
    }

    @Test
    public void testGoodbye() {
        String goodbye = "I'll see you later";
        assertEquals(goodbye, stud.goodbye());
    }

    @Test
    public void testSleep() {
        stud.sleep();
        assertTrue(stud.isSleeping());
    }

    @Test
    public void testStudy() {
        assertTrue(stud.study());
        stud.sleep();
        assertFalse(stud.study());
    }
}
