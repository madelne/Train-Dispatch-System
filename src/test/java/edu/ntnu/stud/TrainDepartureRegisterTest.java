package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

/**
 * This is the test class for the TrainDepartureRegister class.
 */
public class TrainDepartureRegisterTest {

  public LocalTime currentTime = LocalTime.now().withSecond(0).withNano(0);
  
  @Test
  void shouldInitialiseTrainDepartureRegisterWithValidTrainDepartures() {
    TrainDeparture trainDeparture1 = new TrainDeparture(currentTime.plusHours(3), "C3", 30, 
        "Sandefjord", 3, LocalTime.of(0, 3));
    TrainDeparture trainDeparture2 = new TrainDeparture(currentTime.plusMinutes(3), "OD10", 
        100, "Blommenholm", 1);
    TrainDeparture trainDeparture3 = new TrainDeparture(currentTime.plusMinutes(30), "J1", 0101, 
        "Gjøvik", LocalTime.of(0, 15));
    HashMap<Integer, TrainDeparture> trains = new HashMap<>();
    trains.put(trainDeparture1.getTrainNumber(), trainDeparture1);
    trains.put(trainDeparture2.getTrainNumber(), trainDeparture2);
    trains.put(trainDeparture3.getTrainNumber(), trainDeparture3);
    TrainDepartureRegister trainRegister = new TrainDepartureRegister(trains);
    assertEquals(trains, trainRegister.getTrainDepartures());
  }

  @Test
  void shouldThrowExceptionWithNullHashMap() {
    assertThrows(IllegalArgumentException.class, () -> new TrainDepartureRegister(null));
  }

  @Test
  void shouldInitialiseEmptyTrainDepartureRegister() {
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
  void shouldAddValidTrainDeparturesToRegister() {
    HashMap<Integer, TrainDeparture> trains = new HashMap<>();
    TrainDeparture train = new TrainDeparture(currentTime.plusMinutes(30), "C3", 30, 
        "Sandefjord", 3, LocalTime.of(0, 3));
    trains.put(train.getTrainNumber(), train);
    TrainDepartureRegister trainRegister = new TrainDepartureRegister();
    trainRegister.addTrainDeparture(train);
    assertEquals(trains, trainRegister.getTrainDepartures());
  }

  @Test
  void shouldNotAddTrainDeparturesWithDuplicateTrainNumber() {
    TrainDeparture train1 = new TrainDeparture(currentTime.plusMinutes(5), "B1", 123, "Oslo");
    TrainDeparture train2 = new TrainDeparture(currentTime.plusMinutes(4), "C2", 123, "Bergen");
    TrainDepartureRegister register1 = new TrainDepartureRegister();
    register1.addTrainDeparture(train1);
    register1.addTrainDeparture(train2);
    TrainDepartureRegister register2 = new TrainDepartureRegister();
    register2.addTrainDeparture(train1);
    assertEquals(register2.getTrainDepartures(), register1.getTrainDepartures());
  }

  @Test
  void shouldNotAddDepartedTrainDepartures() {
    TrainDeparture train1 = new TrainDeparture(currentTime.plusHours(3), "C3", 30, 
        "Sandefjord", 3, LocalTime.of(0, 3));
    TrainDeparture train2 = new TrainDeparture(currentTime.minusMinutes(3), "OD10", 
        100, "Blommenholm", 1);
    TrainDepartureRegister register1 = new TrainDepartureRegister();
    register1.addTrainDeparture(train1);
    register1.addTrainDeparture(train2);
    TrainDepartureRegister register2 = new TrainDepartureRegister();
    register2.addTrainDeparture(train1);
    assertEquals(register1.getTrainDepartures(), register2.getTrainDepartures());
  }

  @Test
  void shouldReturnTheTrainWithGivenTrainNumber() {
    TrainDeparture train1 = new TrainDeparture(LocalTime.now().plusHours(4), "OD10", 
        100, "Blommenholm", 1);
    TrainDeparture train2 = new TrainDeparture(currentTime.plusHours(3), "C3", 30, 
        "Sandefjord", 3, LocalTime.of(0, 3));
    TrainDepartureRegister register = new TrainDepartureRegister();
    register.addTrainDeparture(train1);
    register.addTrainDeparture(train2);
    TrainDeparture result = register.searchByTrainNumber(100);
    assertEquals(train1, result);
  }

  @Test
  void shouldThrowExceptionWith0TrainNumber() {
    TrainDepartureRegister register = new TrainDepartureRegister();
    /*0 train number should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> register.searchByTrainNumber(0));
  }

  @Test
  void shouldReturnNullIfTrainNumberDoesNotExistInRegister() {
    TrainDepartureRegister register = new TrainDepartureRegister();
    assertEquals(register.searchByTrainNumber(4), null);
  }

  @Test
  void shouldReturnHashMapWithAllTrainDeparturesWithGivenDestination() {
    TrainDeparture train1 = new TrainDeparture(LocalTime.now().plusMinutes(5), "OD10", 
        100, "Blommenholm", 1);
    TrainDeparture train2 = new TrainDeparture(LocalTime.now().plusHours(1), "J1", 010, 
        "Gjøvik", LocalTime.of(0, 15));
    TrainDeparture train3 = new TrainDeparture(LocalTime.now().plusHours(2), "C3", 30, 
        "Blommenholm", 3, LocalTime.of(0, 3));
    TrainDepartureRegister register = new TrainDepartureRegister();
    register.addTrainDeparture(train1);
    register.addTrainDeparture(train2);
    register.addTrainDeparture(train3);
    HashMap<Integer, TrainDeparture> trainsToBlommenholm = new HashMap<>();
    trainsToBlommenholm.put(train1.getTrainNumber(), train1);
    trainsToBlommenholm.put(train3.getTrainNumber(), train3);
    assertEquals(trainsToBlommenholm, register.searchByDestination("Blommenholm"));
  }

  @Test
  void shouldThrowExceptionWithEmptyDestinationString() {
    TrainDepartureRegister register = new TrainDepartureRegister();
    /*Empty string destination should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> register.searchByDestination(""));
    
  }

  @Test
  void shouldRemoveAllDepartedTrainDepartures() {
    TrainDeparture train1 = new TrainDeparture(LocalTime.now().plusMinutes(3), "T15", 15, 
        "Lillehammer");   
    TrainDeparture train2 = new TrainDeparture(LocalTime.now().plusHours(1), "h", 1, "Oslo");
    TrainDepartureRegister register = new TrainDepartureRegister();
    TrainDepartureRegister register2 = new TrainDepartureRegister();
    register.addTrainDeparture(train1);
    register.addTrainDeparture(train2);
    register2.addTrainDeparture(train2);
    register.setCurrentTime(LocalTime.now().plusMinutes(10));
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
  void shouldReturnHashMapSortedByDepartureTime() {
    TrainDeparture train1 = new TrainDeparture(LocalTime.now().plusMinutes(2), "H1", 66, "Hamar");
    TrainDeparture train2 = new TrainDeparture(LocalTime.now().plusHours(1), "M14", 2, "Minsk");
    TrainDeparture train3 = new TrainDeparture(LocalTime.now().plusMinutes(55), "S5", 55,
        "Stavanger");
    HashMap<Integer, TrainDeparture> trainsSorted = new HashMap<>();
    trainsSorted.put(train2.getTrainNumber(), train2);
    trainsSorted.put(train1.getTrainNumber(), train1);
    trainsSorted.put(train3.getTrainNumber(), train3);
    TrainDepartureRegister register = new TrainDepartureRegister();
    register.addTrainDeparture(train1);
    register.addTrainDeparture(train2);
    register.addTrainDeparture(train3);
    HashMap<Integer, TrainDeparture> sortedRegister = register.sortHashMap();
    assertEquals(trainsSorted, sortedRegister); 
  }

  @Test
  void testSortHashMapNeg() {
    /*fylli inn */
  }

  @Test
  void shouldRemoveTrainDepartureWithGivenTrainNumber() {
    TrainDeparture train1 = new TrainDeparture(LocalTime.now().plusMinutes(3), "T15", 15, 
        "Lillehammer");   
    TrainDeparture train2 = new TrainDeparture(LocalTime.now().plusHours(1), "h", 1, "Oslo");
    TrainDepartureRegister register1 = new TrainDepartureRegister();
    register1.addTrainDeparture(train1);
    register1.addTrainDeparture(train2); 
    register1.removeTrainDeparture(train2.getTrainNumber());
    TrainDepartureRegister register2 = new TrainDepartureRegister();
    register2.addTrainDeparture(train1);
    assertEquals(register2.getTrainDepartures(), register1.getTrainDepartures());
  }

  @Test
  void removeTrainDepartureTestNeg() {
    /*full inn */
  }

}
