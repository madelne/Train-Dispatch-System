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
  void testConstructor1Pos() {
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
    TrainDepartureRegister trainRegister = new TrainDepartureRegister(trains);
    assertEquals(trains, trainRegister);
  }

  @Test
  void testConstructor1Neg() {
    /**
     * Negativ test for konstruktøren. fyll inn.
     */
  }

  @Test
  void testConstructor2Pos() {
    
  }


  @Test
  void testAddTrainDeparturePos() {
    ArrayList<TrainDeparture> trains = new ArrayList<TrainDeparture>();
    TrainDeparture train = new TrainDeparture(LocalTime.of(15, 17), "C3", 30, 
        "Sandefjord", 3, LocalTime.of(0, 3));
    trains.add(train);
    TrainDepartureRegister trainRegister = new TrainDepartureRegister();
    trainRegister.addTrainDeparture(train);
    assertEquals(trains, trainRegister);
  }

}
