package model;

import exceptions.InvPersonException;
import exceptions.InvSubjectException;
import exceptions.MaxCapacityException;
import model.Institution;
import model.Professor;
import model.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class SubjectTest {
    Subject subject;

    @BeforeEach
    public void beforeEach() {
        subject = new Subject("Math");
    }

    @Test
    public void testAddProf() {
        Professor p = new Professor("Mark", "John", subject);
        subject.addProf(p);
        assertEquals(1, subject.numProfs());
    }

    @Test
    public void testPrintProf() {
        Professor p = new Professor("Mark", "John", subject);
        Professor z = new Professor("Jack", "Coll", subject);
        subject.addProf(p);
        subject.addProf(z);
        assertEquals("Dr. Mark John, MathDr. Jack Coll, Math", subject.printProf());
    }

    @Test
    public void testGetSubject() {
        assertEquals("Math", subject.getSubject());
    }
}
