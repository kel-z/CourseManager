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
        stud = new UniversityStudent("John");
    }

    @Test
    public void testGreet() {
        String greeting = "Hi! My name is John.";
        assertEquals(greeting, stud.greet());
    }

    @Test
    public void testGoodbye() {
        String goodbye = "I'll see you later.";
        assertEquals(goodbye, stud.goodbye());
    }

    @Test
    public void testSleep() {
        assertTrue(stud.sleep());
        assertFalse(stud.sleep());
    }

    @Test
    public void testStudy() {
        assertTrue(stud.study());
        stud.sleep();
        assertFalse(stud.study());
    }
}
