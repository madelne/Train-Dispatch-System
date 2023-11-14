package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

/**
 * This is the test class for the program
 */

public class TrainDepartureTest {

    @Test
    void testConstructorPos1(){
        TrainDeparture train = new TrainDeparture(LocalTime.of(05, 15), "L1", 123, "Lillestrøm", 3, LocalTime.of(0, 10));
        assertEquals(LocalTime.of(05, 15), train.getDeparture_time());
        assertEquals("L1", train.getLine());
        assertEquals(123, train.getTrainNumber());
        assertEquals("Lillestrøm", train.getDestination());
        assertEquals(3, train.getTrack());
        assertEquals(LocalTime.of(0, 10), train.getDelay());
    }

    @Test
    void testConstructorPos2(){
        TrainDeparture train = new TrainDeparture(LocalTime.of(0, 0), "0", 1, "/+-#.", 100, LocalTime.of(0, 0));
        assertEquals(LocalTime.of(0, 0), train.getDeparture_time());
        assertEquals("0", train.getLine());
        assertEquals(1, train.getTrainNumber());
        assertEquals("/+-#.", train.getDestination());
        assertEquals(100, train.getTrack());
        assertEquals(LocalTime.of(0, 0), train.getDelay());
    }

    @Test
    void testConstructorNeg(){
        TrainDeparture train = new TrainDeparture(LocalTime.of(-1, -10), "", -30, "", -2, LocalTime.of(0, -10));
        assertFalse(null);
    }
    
}
