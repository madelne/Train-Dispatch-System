package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import java.util.HashMap;

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
    HashMap<Integer, TrainDeparture> trains = new HashMap<>();
    trains.put(train1.getTrainNumber(), train1);
    trains.put(train2.getTrainNumber(), train2);
    trains.put(train3.getTrainNumber(), train3) ;
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
    TrainDepartureRegister register = new TrainDepartureRegister();
    HashMap<Integer, TrainDeparture> emptyHashMap = new HashMap<>();
    assertEquals(emptyHashMap, register);
  }

  @Test
  void testConstructor2Neg() {
     /**
     * Negativ test for konstruktøren. fyll inn.
     */
  }


  @Test
  void testAddTrainDeparturePos() {
    HashMap<Integer, TrainDeparture> trains = new HashMap<>();
    TrainDeparture train = new TrainDeparture(LocalTime.of(15, 17), "C3", 30, 
        "Sandefjord", 3, LocalTime.of(0, 3));
    trains.put(train.getTrainNumber(), train);
    TrainDepartureRegister trainRegister = new TrainDepartureRegister();
    trainRegister.addTrainDeparture(train);
    assertEquals(trains, trainRegister);
  }

  @Test
  void testAddTrainDepartureNeg() {
     /**
     * Negativ test. Fyll inn.
     */
  }

  @Test
  void testSearchByTrainNumberPos() {
    TrainDeparture train = new TrainDeparture(LocalTime.of(16, 7), "OD10", 
        1007, "Blommenholm", 1);
    HashMap<Integer, TrainDeparture> trains = new HashMap<>();
    trains.put(train.getTrainNumber(), train);
    TrainDepartureRegister register = new TrainDepartureRegister(trains);
    TrainDeparture result = register.searchByTrainNumber(1007);
    assertEquals(train, result);
  }

}
