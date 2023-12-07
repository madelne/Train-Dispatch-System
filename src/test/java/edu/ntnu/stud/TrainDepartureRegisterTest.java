package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * This is the test class for the TrainDepartureRegister class.
 */
public class TrainDepartureRegisterTest {

  private LocalTime currentTime = LocalTime.of(0, 0);
  private TrainDeparture trainDeparture1;
  private TrainDeparture trainDeparture2;
  private TrainDeparture trainDeparture3;
  private TrainDepartureRegister register1;
  private TrainDepartureRegister register2;

  @BeforeEach
  public void init() {
    register1 = new TrainDepartureRegister();
    register2 = new TrainDepartureRegister();
  }
  
  @Test
  void shouldInitialiseTrainDepartureRegisterWithValidTrainDepartures() {
    trainDeparture1 = new TrainDeparture(currentTime.plusHours(3), "C3", 30, 
        "Sandefjord", 3, LocalTime.of(0, 3));
    trainDeparture2 = new TrainDeparture(currentTime.plusMinutes(3), "OD10", 
        100, "Blommenholm", 1);
    trainDeparture3 = new TrainDeparture(currentTime.plusMinutes(30), "J1", 0101, 
        "Gjøvik", LocalTime.of(0, 15));
    HashMap<Integer, TrainDeparture> trainDepartures = new HashMap<>();
    trainDepartures.put(trainDeparture1.getTrainNumber(), trainDeparture1);
    trainDepartures.put(trainDeparture2.getTrainNumber(), trainDeparture2);
    trainDepartures.put(trainDeparture3.getTrainNumber(), trainDeparture3);
    register1 = new TrainDepartureRegister(trainDepartures);
    assertEquals(trainDepartures, register1.getTrainDepartures());
  }

  @Test
  void shouldInitialiseEmptyTrainDepartureRegister() {
    HashMap<Integer, TrainDeparture> trainDepartures = new HashMap<>();
    assertEquals(trainDepartures, new TrainDepartureRegister().getTrainDepartures());
  }

  @Test
  void shouldAddValidTrainDeparturesToRegister() {
    HashMap<Integer, TrainDeparture> trainDepartures = new HashMap<>();
    trainDeparture1 = new TrainDeparture(currentTime.plusMinutes(30), "C3", 30, 
        "Sandefjord", 3, LocalTime.of(0, 3));
    trainDepartures.put(trainDeparture1.getTrainNumber(), trainDeparture1);
    register1.addTrainDeparture(trainDeparture1);
    assertEquals(trainDepartures, register1.getTrainDepartures());
  }

  @Test
  void shouldNotAddTrainDeparturesWithDuplicateTrainNumber() {
    trainDeparture1 = new TrainDeparture(
        currentTime.plusMinutes(5), "B1", 123, "Oslo");
    trainDeparture2 = new TrainDeparture(
        currentTime.plusMinutes(4), "C2", 123, "Bergen");
    register1.addTrainDeparture(trainDeparture1);
    register1.addTrainDeparture(trainDeparture2);
    register2.addTrainDeparture(trainDeparture1);
    assertEquals(register2.getTrainDepartures(), register1.getTrainDepartures());
  }

  @Test
  void shouldNotAddDepartedTrainDepartures() {
    register1.setCurrentTime(LocalTime.of(10, 0));
    trainDeparture1 = new TrainDeparture(LocalTime.of(10, 0).minusMinutes(3), "OD10", 
        100, "Blommenholm", 1);
    register1.addTrainDeparture(trainDeparture1);
    assertEquals(new TrainDepartureRegister().getTrainDepartures(), register1.getTrainDepartures());
  }

  @Test
  void shouldReturnTheTrainWithGivenTrainNumber() {
    trainDeparture1 = new TrainDeparture(currentTime.plusHours(4), "OD10", 
        100, "Blommenholm", 1);
    trainDeparture2 = new TrainDeparture(currentTime.plusHours(3), "C3", 30, 
        "Sandefjord", 3, LocalTime.of(0, 3));
    register1.addTrainDeparture(trainDeparture1);
    register1.addTrainDeparture(trainDeparture2);
    trainDeparture3 = register1.searchByTrainNumber(100);
    assertEquals(trainDeparture1, trainDeparture3);
  }

  @Test
  void shouldThrowExceptionWith0TrainNumber() {
    /*0 train number should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> register1.searchByTrainNumber(0));
  }

  @Test
  void shouldReturnNullIfTrainNumberDoesNotExistInRegister() {
    assertEquals(register1.searchByTrainNumber(88), null);
  }

  @Test
  void shouldReturnHashMapWithAllTrainDeparturesWithGivenDestination() {
    trainDeparture1 = new TrainDeparture(currentTime.plusMinutes(5), "OD10", 
        100, "Blommenholm", 1);
    trainDeparture2 = new TrainDeparture(currentTime.plusHours(1), "J1", 010, 
        "Gjøvik", LocalTime.of(0, 15));
    trainDeparture3 = new TrainDeparture(currentTime.plusHours(2), "C3", 30, 
        "Blommenholm", 3, LocalTime.of(0, 3));
    register1.addTrainDeparture(trainDeparture1);
    register1.addTrainDeparture(trainDeparture2);
    register1.addTrainDeparture(trainDeparture3);
    HashMap<Integer, TrainDeparture> trainsToBlommenholm = new HashMap<>();
    trainsToBlommenholm.put(trainDeparture1.getTrainNumber(), trainDeparture1);
    trainsToBlommenholm.put(trainDeparture3.getTrainNumber(), trainDeparture3);
    assertEquals(trainsToBlommenholm, register1.searchByDestination("Blommenholm"));
  }

  @Test
  void shouldThrowExceptionWithEmptyDestinationString() {
    /*Empty string destination should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> register1.searchByDestination(""));
    
  }

  @Test
  void shouldRemoveAllDepartedTrainDepartures() {
    trainDeparture1 = new TrainDeparture(
        currentTime.plusMinutes(3), "T15", 15, "Lillehammer");   
    register1.addTrainDeparture(trainDeparture1);
    register1.setCurrentTime(currentTime.plusMinutes(10));
    assertEquals(new TrainDepartureRegister().getTrainDepartures(), register1.getTrainDepartures());
  }

  @Test
  void shouldRemoveDeparturesDelayedToTomorrow() {
    trainDeparture1 = new TrainDeparture(
        currentTime.plusHours(1), "AM", 99, "Oslo", LocalTime.of(23, 0));
    register1.addTrainDeparture(trainDeparture1);
    assertEquals(new TrainDepartureRegister().getTrainDepartures(), register1.getTrainDepartures());
  }

  @Test
  void shouldReturnHashMapSortedByDepartureTime() {
    trainDeparture1 = new TrainDeparture(
        currentTime.plusMinutes(2), "H1", 66, "Hamar");
    trainDeparture2 = new TrainDeparture(
        currentTime.plusHours(1), "M14", 2, "Minsk");
    trainDeparture3 = new TrainDeparture(
        currentTime.plusMinutes(55), "S5", 55, "Stavanger");
    HashMap<Integer, TrainDeparture> trainsSorted = new HashMap<>();
    trainsSorted.put(trainDeparture2.getTrainNumber(), trainDeparture2);
    trainsSorted.put(trainDeparture1.getTrainNumber(), trainDeparture1);
    trainsSorted.put(trainDeparture3.getTrainNumber(), trainDeparture3);
    register1.addTrainDeparture(trainDeparture1);
    register1.addTrainDeparture(trainDeparture2);
    register1.addTrainDeparture(trainDeparture3);
    HashMap<Integer, TrainDeparture> sortedRegister = register1.sortHashMap();
    assertEquals(trainsSorted, sortedRegister); 
  }

  @Test
  void shouldRemoveTrainDepartureWithGivenTrainNumber() {
    trainDeparture1 = new TrainDeparture(
        currentTime.plusMinutes(3), "T15", 15, "Lillehammer");   
    register1.addTrainDeparture(trainDeparture1);
    register1.removeTrainDeparture(15);
    assertEquals(new TrainDepartureRegister().getTrainDepartures(), register1.getTrainDepartures());
  }

  @Test
  void shouldNotRemoveNonExistinTrainDeparture() {
    assertFalse(register1.getTrainDepartures().containsKey(10));
    register1.removeTrainDeparture(10);
  }
  
  @Test
  void shouldThrowExceptionWithNullHashMap() {
    assertThrows(IllegalArgumentException.class, () -> new TrainDepartureRegister(null));
  }

  @Test
  void shouldSetNewCurrentTimeWithValidParameter() {
    LocalTime firstCurrentTime = currentTime;
    register1.setCurrentTime(currentTime.plusHours(3));
    assertEquals(firstCurrentTime.plusHours(3), register1.getCurrentTime());
  }

  @Test
  void shouldNotChangeCurrentTimeWithEarlierNewTime() {
    register1.setCurrentTime(LocalTime.of(10, 0));
    register1.setCurrentTime(LocalTime.of(9, 0));
    assertEquals(LocalTime.of(10, 0), register1.getCurrentTime());
  }

}
