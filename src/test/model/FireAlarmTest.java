package model;

import model.FireAlarm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FireAlarmTest {
    public FireAlarm fireAlarm;
    @BeforeEach
    public void beforeEach() {
        fireAlarm = new FireAlarm();
    }

    @Test
    public void testTripAlarm() {
        assertTrue(fireAlarm.tripAlarm());
        assertEquals(1, fireAlarm.getAlarm());
        fireAlarm.tripAlarm();
        fireAlarm.tripAlarm();
        assertEquals(3, fireAlarm.getAlarm());
    }

    @Test
    public void testRingAlarm() {
        assertTrue(fireAlarm.ringAlarm(10));
    }
}
