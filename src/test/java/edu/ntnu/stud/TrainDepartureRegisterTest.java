package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * This is the test class for the TrainDepartureRegister class.
 */
public class TrainDepartureRegisterTest {
  
  @Test
  void testConstructorPos() {
    TrainDeparture train1 = new TrainDeparture(LocalTime.of(15, 17), "C3", 30, 
        "Sandefjord", 3, LocalTime.of(0, 3));
    TrainDeparture train2 = new TrainDeparture(LocalTime.of(16, 7), "OD10", 
        1007, "Blommenholm", 1);
    TrainDeparture train3 = new TrainDeparture(LocalTime.of(1, 33), "J1", 0101, 
        "Gjøvik", LocalTime.of(0, 15));
    ArrayList<TrainDeparture> trains = new ArrayList<TrainDeparture>();
    trains.add(train1);
    trains.add(train2);
    trains.add(train3);
    assertEquals(train1.toString(), trains.get(0).toString());
    assertEquals(train2.toString(), trains.get(1).toString());
    assertEquals(train3.toString(), trains.get(2).toString());
  }

  @Test
  void testConstructorNeg() {
    /**
     * Negativ test for konstruktøren. fyll inn.
     */
  }



}
