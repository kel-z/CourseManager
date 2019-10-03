package ui;

import model.ResBuilding;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResBuildingTest {
    ResBuilding building;

    @BeforeEach
    public void beforeEach() {
        building = new ResBuilding("building");
    }

    @Test
    public void testSetName() {
        building.setName("nest");
        assertEquals("nest", building.getName());
    }

    @Test
    public void testTripAlarm() {
        assertTrue(building.tripAlarm());
        assertEquals(1, building.getAlarm());
        building.tripAlarm();
        building.tripAlarm();
        assertEquals(3, building.getAlarm());
    }

    @Test
    public void testRingAlarm() {
        assertTrue(building.ringAlarm(10));
    }
}