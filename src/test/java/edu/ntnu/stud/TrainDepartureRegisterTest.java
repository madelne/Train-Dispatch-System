package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.NoSuchElementException;

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
    assertEquals(trains, trainRegister.getTrainDepartures());
  }

  @Test
  void testConstructor1Neg() {
    /*Null Hashmap should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> new TrainDepartureRegister(null));
  }

  @Test
  void testConstructor2Pos() {
    TrainDepartureRegister register = new TrainDepartureRegister();
    HashMap<Integer, TrainDeparture> emptyHashMap = new HashMap<>();
    assertEquals(emptyHashMap, register.getTrainDepartures());
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
    assertEquals(trains, trainRegister.getTrainDepartures());
  }

  @Test
  void testAddTrainDepartureNeg() {
     TrainDeparture train1 = new TrainDeparture(LocalTime.of(16, 16), "B1", 123, "Oslo");
     TrainDeparture train2 = new TrainDeparture(LocalTime.of(17, 17), "C2", 123, "Bergen");
     HashMap<Integer, TrainDeparture> trains = new HashMap<>();
     trains.put(train1.getTrainNumber(), train1);
     TrainDepartureRegister register = new TrainDepartureRegister(trains);
     /*Dublicate train number should throw IllegalArgumentException*/
     assertThrows(IllegalArgumentException.class, () -> register.addTrainDeparture(train2));
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

  @Test
  void testSearchByTrainNumberNeg() {
    TrainDepartureRegister register = new TrainDepartureRegister();
    /*0 train number should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> register.searchByTrainNumber(0));
  }

  @Test
  void testSearchByDestinationPos() {
    TrainDeparture train1 = new TrainDeparture(LocalTime.of(16, 7), "OD10", 
        1007, "Blommenholm", 1);
    TrainDeparture train2 = new TrainDeparture(LocalTime.of(1, 33), "J1", 0101, 
        "Gjøvik", LocalTime.of(0, 15));
    TrainDeparture train3 = new TrainDeparture(LocalTime.of(15, 17), "C3", 30, 
        "Blommenholm", 3, LocalTime.of(0, 3));
    HashMap<Integer, TrainDeparture> trains = new HashMap<>();
    trains.put(train1.getTrainNumber(), train1);
    trains.put(train2.getTrainNumber(), train2);
    trains.put(train3.getTrainNumber(), train3);
    HashMap<Integer, TrainDeparture> trainsToBlommenholm = new HashMap<>();
    trainsToBlommenholm.put(train1.getTrainNumber(), train1);
    trainsToBlommenholm.put(train3.getTrainNumber(), train3);
    TrainDepartureRegister register = new TrainDepartureRegister(trains);
    HashMap<Integer, TrainDeparture> result = register.searchByDestination("Blommenholm");
    assertEquals(trainsToBlommenholm, result);
  }

  @Test
  void testSearchByDestinationNeg() {
    TrainDepartureRegister register = new TrainDepartureRegister();
    /*Empty string destination should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> register.searchByDestination(""));
    
  }

  @Test
  void testRemovePreviousDeparturesPos() {
    TrainDeparture train = new TrainDeparture(LocalTime.now().minusMinutes(1), "T15", 1512, 
        "Lillehammer");   
    TrainDeparture train2 = new TrainDeparture(LocalTime.of(10, 10), "h", 1, "Oslo");
    HashMap<Integer, TrainDeparture> trains = new HashMap<>(); 
    trains.put(train.getTrainNumber(), train);
    trains.put(train2.getTrainNumber(), train2);
    TrainDepartureRegister register = new TrainDepartureRegister(trains);
    TrainDepartureRegister register2 = new TrainDepartureRegister();
    register.removePreviousDepartures();
    assertEquals(register2.getTrainDepartures(), register.getTrainDepartures());
  }

  @Test
  void testRemovePreviousDeparturesNeg() {
    /**
     * negativ test.
     */
  }

  @Test
  void testSortListPos() {
    TrainDeparture train1 = new TrainDeparture(LocalTime.of(15, 51), "H1", 66, "Hamar");
    TrainDeparture train2 = new TrainDeparture(LocalTime.of(13, 14), "M14", 2, "Minsk");
    TrainDeparture train3 = new TrainDeparture(LocalTime.of(18, 8), "S5", 55, "Stavanger");
    HashMap<Integer, TrainDeparture> trains = new HashMap<>();
    trains.put(train1.getTrainNumber(), train1);
    trains.put(train2.getTrainNumber(), train2);
    trains.put(train3.getTrainNumber(), train3);
    TrainDepartureRegister register = new TrainDepartureRegister(trains);
    HashMap<Integer, TrainDeparture> trainsSorted = new HashMap<>();
    trainsSorted.put(train2.getTrainNumber(), train2);
    trainsSorted.put(train1.getTrainNumber(), train1);
    trainsSorted.put(train3.getTrainNumber(), train3);
    assertEquals(trainsSorted, register.sortList()); 
  }

}
