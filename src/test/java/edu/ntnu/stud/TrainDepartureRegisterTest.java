package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

/**
 * This is the test class for the TrainDepartureRegister class.
 */
public class TrainDepartureRegisterTest {

  private LocalTime currentTime = LocalTime.now().withSecond(0).withNano(0);
  
  @Test
  void shouldInitialiseTrainDepartureRegisterWithValidTrainDepartures() {
    TrainDeparture trainDeparture1 = new TrainDeparture(currentTime.plusHours(3), "C3", 30, 
        "Sandefjord", 3, LocalTime.of(0, 3));
    TrainDeparture trainDeparture2 = new TrainDeparture(currentTime.plusMinutes(3), "OD10", 
        100, "Blommenholm", 1);
    TrainDeparture trainDeparture3 = new TrainDeparture(currentTime.plusMinutes(30), "J1", 0101, 
        "Gjøvik", LocalTime.of(0, 15));
    HashMap<Integer, TrainDeparture> trainDepartures = new HashMap<>();
    trainDepartures.put(trainDeparture1.getTrainNumber(), trainDeparture1);
    trainDepartures.put(trainDeparture2.getTrainNumber(), trainDeparture2);
    trainDepartures.put(trainDeparture3.getTrainNumber(), trainDeparture3);
    TrainDepartureRegister trainRegister = new TrainDepartureRegister(trainDepartures);
    assertEquals(trainDepartures, trainRegister.getTrainDepartures());
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
    HashMap<Integer, TrainDeparture> trainDepartures = new HashMap<>();
    TrainDeparture trainDeparture = new TrainDeparture(currentTime.plusMinutes(30), "C3", 30, 
        "Sandefjord", 3, LocalTime.of(0, 3));
    trainDepartures.put(trainDeparture.getTrainNumber(), trainDeparture);
    TrainDepartureRegister trainRegister = new TrainDepartureRegister();
    trainRegister.addTrainDeparture(trainDeparture);
    assertEquals(trainDepartures, trainRegister.getTrainDepartures());
  }

  @Test
  void shouldNotAddTrainDeparturesWithDuplicateTrainNumber() {
    TrainDeparture trainDeparture1 = new TrainDeparture(
        currentTime.plusMinutes(5), "B1", 123, "Oslo");
    TrainDeparture trainDeparture2 = new TrainDeparture(
        currentTime.plusMinutes(4), "C2", 123, "Bergen");
    TrainDepartureRegister register1 = new TrainDepartureRegister();
    register1.addTrainDeparture(trainDeparture1);
    register1.addTrainDeparture(trainDeparture2);
    TrainDepartureRegister register2 = new TrainDepartureRegister();
    register2.addTrainDeparture(trainDeparture1);
    assertEquals(register2.getTrainDepartures(), register1.getTrainDepartures());
  }

  @Test
  void shouldNotAddDepartedTrainDepartures() {
    TrainDeparture trainDeparture = new TrainDeparture(currentTime.minusMinutes(3), "OD10", 
        100, "Blommenholm", 1);
    TrainDepartureRegister register = new TrainDepartureRegister();
    register.addTrainDeparture(trainDeparture);
    assertEquals(new TrainDepartureRegister().getTrainDepartures(), register.getTrainDepartures());
  }

  @Test
  void shouldReturnTheTrainWithGivenTrainNumber() {
    TrainDeparture trainDeparture1 = new TrainDeparture(LocalTime.now().plusHours(4), "OD10", 
        100, "Blommenholm", 1);
    TrainDeparture trainDeparture2 = new TrainDeparture(currentTime.plusHours(3), "C3", 30, 
        "Sandefjord", 3, LocalTime.of(0, 3));
    TrainDepartureRegister register = new TrainDepartureRegister();
    register.addTrainDeparture(trainDeparture1);
    register.addTrainDeparture(trainDeparture2);
    TrainDeparture result = register.searchByTrainNumber(100);
    assertEquals(trainDeparture1, result);
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
    TrainDeparture trainDeparture1 = new TrainDeparture(LocalTime.now().plusMinutes(5), "OD10", 
        100, "Blommenholm", 1);
    TrainDeparture trainDeparture2 = new TrainDeparture(LocalTime.now().plusHours(1), "J1", 010, 
        "Gjøvik", LocalTime.of(0, 15));
    TrainDeparture trainDeparture3 = new TrainDeparture(LocalTime.now().plusHours(2), "C3", 30, 
        "Blommenholm", 3, LocalTime.of(0, 3));
    TrainDepartureRegister register = new TrainDepartureRegister();
    register.addTrainDeparture(trainDeparture1);
    register.addTrainDeparture(trainDeparture2);
    register.addTrainDeparture(trainDeparture3);
    HashMap<Integer, TrainDeparture> trainsToBlommenholm = new HashMap<>();
    trainsToBlommenholm.put(trainDeparture1.getTrainNumber(), trainDeparture1);
    trainsToBlommenholm.put(trainDeparture3.getTrainNumber(), trainDeparture3);
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
    TrainDeparture trainDeparture = new TrainDeparture(
        LocalTime.now().plusMinutes(3), "T15", 15, "Lillehammer");   
    TrainDepartureRegister register = new TrainDepartureRegister();
    register.addTrainDeparture(trainDeparture);
    register.setCurrentTime(LocalTime.now().plusMinutes(10));
    assertEquals(new TrainDepartureRegister().getTrainDepartures(), register.getTrainDepartures());
  }

  @Test
  void shouldRemoveDeparturesDelayedToTomorrow() {
    TrainDeparture trainDeparture = new TrainDeparture(
        currentTime.plusHours(1), "AM", 99, "Oslo", LocalTime.of(23, 0));
    TrainDepartureRegister register = new TrainDepartureRegister();
    register.addTrainDeparture(trainDeparture);
    assertEquals(new TrainDepartureRegister().getTrainDepartures(), register.getTrainDepartures());
  }

  @Test
  void testRemovePreviousDeparturesNeg() {
    /**
     * negativ test.
     */
  }

  @Test
  void shouldReturnHashMapSortedByDepartureTime() {
    TrainDeparture trainDeparture1 = new TrainDeparture(
        LocalTime.now().plusMinutes(2), "H1", 66, "Hamar");
    TrainDeparture trainDeparture2 = new TrainDeparture(
        LocalTime.now().plusHours(1), "M14", 2, "Minsk");
    TrainDeparture trainDeparture3 = new TrainDeparture(
        LocalTime.now().plusMinutes(55), "S5", 55, "Stavanger");
    HashMap<Integer, TrainDeparture> trainsSorted = new HashMap<>();
    trainsSorted.put(trainDeparture2.getTrainNumber(), trainDeparture2);
    trainsSorted.put(trainDeparture1.getTrainNumber(), trainDeparture1);
    trainsSorted.put(trainDeparture3.getTrainNumber(), trainDeparture3);
    TrainDepartureRegister register = new TrainDepartureRegister();
    register.addTrainDeparture(trainDeparture1);
    register.addTrainDeparture(trainDeparture2);
    register.addTrainDeparture(trainDeparture3);
    HashMap<Integer, TrainDeparture> sortedRegister = register.sortHashMap();
    assertEquals(trainsSorted, sortedRegister); 
  }

  @Test
  void testSortHashMapNeg() {
    /*fylli inn */
  }

  @Test
  void shouldRemoveTrainDepartureWithGivenTrainNumber() {
    TrainDeparture trainDeparture = new TrainDeparture(
        LocalTime.now().plusMinutes(3), "T15", 15, "Lillehammer");   
    TrainDepartureRegister register = new TrainDepartureRegister();
    register.addTrainDeparture(trainDeparture);
    register.removeTrainDeparture(15);
    assertEquals(new TrainDepartureRegister().getTrainDepartures(), register.getTrainDepartures());
  }

  @Test
  void shouldPrintMessageInsteadOfRemovingNonExistinTrainDeparture() {
    TrainDepartureRegister register = new TrainDepartureRegister();
    assertFalse(register.getTrainDepartures().containsKey(10));
    register.removeTrainDeparture(10);
  }

  @Test 
  void validTrainDepartureRegisterShouldDoNothing() {
    /*fyll inn */
  }

  @Test
  void shouldThrowExceptionWithNullHashMap() {
    assertThrows(IllegalArgumentException.class, () -> new TrainDepartureRegister(null));
  }

}
