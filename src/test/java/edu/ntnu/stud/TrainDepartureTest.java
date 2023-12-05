package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

/**
 * This is the test class for the TrainDeparture class.
 */

public class TrainDepartureTest {
  
  private LocalTime currentTime = LocalTime.now().withSecond(0).withNano(0);
  private TrainDeparture trainDeparture;

  @Test
  void shouldInitialiseValidTrainDeparture() {
    trainDeparture = new TrainDeparture(currentTime.plusHours(1), "C30", 
        123, "Lillestrøm", 3, LocalTime.of(0, 10));
    assertEquals(currentTime.plusHours(1), trainDeparture.getDepartureTime());
    assertEquals("C30", trainDeparture.getLine());
    assertEquals(123, trainDeparture.getTrainNumber());
    assertEquals("Lillestrøm", trainDeparture.getDestination());
    assertEquals(3, trainDeparture.getTrack());
    assertEquals(LocalTime.of(0, 10), trainDeparture.getDelay());
  }

  @Test
  void shouldInitialiseValidTrainDepartureExtreme() {
    trainDeparture = new TrainDeparture(currentTime.plusHours(1), "0", 
        1, "/+-#.", 100, LocalTime.of(23, 0));
    assertEquals(currentTime.plusHours(1), trainDeparture.getDepartureTime());
    assertEquals("0", trainDeparture.getLine());
    assertEquals(1, trainDeparture.getTrainNumber());
    assertEquals("/+-#.", trainDeparture.getDestination());
    assertEquals(100, trainDeparture.getTrack());
    assertEquals(LocalTime.of(23, 0), trainDeparture.getDelay());
  }

  @Test
  void shouldThrowExceptionWithIllegalParameterInput() {
    /*Null departure time should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> 
        new TrainDeparture(null, "H", 1, "Oslo", 1, LocalTime.of(0, 5)));
    /*Empty string line should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> 
        new TrainDeparture(currentTime.plusHours(1), "", 1, "Oslo", 1, LocalTime.of(0, 5)));
    /*0 train number should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> 
        new TrainDeparture(currentTime.plusHours(1), "H", 0, "Oslo", 1, LocalTime.of(0, 5)));
    /*Empty string destination should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> 
        new TrainDeparture(currentTime.plusHours(1), "H", 1, "", 1, LocalTime.of(0, 5)));
    /*Under 0 track should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> 
        new TrainDeparture(currentTime.plusHours(1), "H", 1, "Oslo", -1, LocalTime.of(0, 5)));
    /*0 delay should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> 
        new TrainDeparture(currentTime.plusHours(1), "H", 1, "Oslo", 1, LocalTime.of(0, 0)));
  }
    
  @Test
  void shouldInitialiseValidTrainDepartureWithoutDelay() {
    trainDeparture = new TrainDeparture(currentTime.plusHours(1), "M26", 44, "Asker", 2);
    assertEquals(currentTime.plusHours(1), trainDeparture.getDepartureTime());
    assertEquals("M26", trainDeparture.getLine());  
    assertEquals(44, trainDeparture.getTrainNumber());
    assertEquals("Asker", trainDeparture.getDestination());
    assertEquals(2, trainDeparture.getTrack());
  }

  @Test
  void shouldInitialiseValidTrainDepartureWithoutDelayExtreme() {
    trainDeparture = new TrainDeparture(currentTime.plusHours(1), "+/#æøå", 100, 
        "+/#æøå", 100);
    assertEquals(currentTime.plusHours(1), trainDeparture.getDepartureTime());
    assertEquals("+/#æøå", trainDeparture.getLine());
    assertEquals(100, trainDeparture.getTrainNumber());
    assertEquals("+/#æøå", trainDeparture.getDestination());
    assertEquals(100, trainDeparture.getTrack());
  }

  @Test
  void shouldThrowExceptionWithIllegalParameterInputWithoutDelay() {
    /*Null departure time should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> new TrainDeparture(null, 
        "H", 1, "Oslo", 1));
    /*Empty string line should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> 
        new TrainDeparture(currentTime.plusHours(1), "", 1, "Oslo", 1));
    /*0 trainnumber should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> 
        new TrainDeparture(currentTime.plusHours(1), "H", 0, "Oslo", 1));
    /*Empty string destination should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> 
        new TrainDeparture(currentTime.plusHours(1), "H", 1, "", 1));
    /*Under 0 track should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> 
        new TrainDeparture(currentTime.plusHours(1), "H", 1, "Oslo", -1));
  }

  @Test
  void shouldInitialiseValidTrainDepartureWithoutTrack() {
    trainDeparture = new TrainDeparture(currentTime.plusHours(1), "R12", 21, 
        "Gjøvik", LocalTime.of(0, 10));
    assertEquals(currentTime.plusHours(1), trainDeparture.getDepartureTime());
    assertEquals("R12", trainDeparture.getLine());
    assertEquals(21, trainDeparture.getTrainNumber());
    assertEquals("Gjøvik", trainDeparture.getDestination());
    assertEquals(LocalTime.of(0, 10), trainDeparture.getDelay());
  }

  @Test
  void shouldInitialiseValidTrainDepartureWithoutTrackExtreme() {
    trainDeparture = new TrainDeparture(currentTime.plusHours(1), "+/#æøå", 
        100, "+/#æøå", LocalTime.of(23, 0));
    assertEquals(currentTime.plusHours(1), trainDeparture.getDepartureTime());
    assertEquals("+/#æøå", trainDeparture.getLine());
    assertEquals(100, trainDeparture.getTrainNumber());
    assertEquals("+/#æøå", trainDeparture.getDestination());
    assertEquals(LocalTime.of(23, 0), trainDeparture.getDelay());
  }

  @Test
  void shouldThrowExceptionWithIllegalParameterInputWithoutTrack() {
    /*Null departure time should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> 
        new TrainDeparture(null, "H", 1, "Oslo", LocalTime.of(0, 5)));
    /*Empty string line should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> 
        new TrainDeparture(currentTime.plusHours(1), "", 1, "Oslo",  LocalTime.of(0, 5)));
    /*0 train number should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> 
        new TrainDeparture(currentTime.plusHours(1), "H", 0, "Oslo", LocalTime.of(0, 5)));
    /*Empty string destination should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> 
        new TrainDeparture(currentTime.plusHours(1), "H", 1, "", LocalTime.of(0, 5)));
    /*0 delay should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> 
        new TrainDeparture(currentTime.plusHours(1), "H", 1, "Oslo", LocalTime.of(0, 0)));

  }

  @Test
  void shouldInitialiseValidTrainDepartureWithoutDelayAndTrack() {
    trainDeparture = new TrainDeparture(currentTime.plusHours(1), "E9", 912, "Høvik");
    assertEquals(currentTime.plusHours(1), trainDeparture.getDepartureTime());
    assertEquals("E9", trainDeparture.getLine());
    assertEquals(912, trainDeparture.getTrainNumber());
    assertEquals("Høvik", trainDeparture.getDestination());
  }

  @Test
  void shouldInitialiseValidTrainDepartureWithoutDelayAndTrackExtreme() {
    trainDeparture = new TrainDeparture(currentTime.plusHours(1), 
        "+/#æøå", 100, "Blommenholm");
    assertEquals(currentTime.plusHours(1), trainDeparture.getDepartureTime());
    assertEquals("+/#æøå", trainDeparture.getLine());
    assertEquals(100, trainDeparture.getTrainNumber());
    assertEquals("Blommenholm", trainDeparture.getDestination());
  }

  @Test 
  void shouldThrowExceptionWithIllegalParameterInputWithoutDelayAndTrack() {
    /*Null departure time should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> 
        new TrainDeparture(null, "H", 1, "Oslo", 1, LocalTime.of(0, 5)));
    /*Empty string line should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> 
        new TrainDeparture(currentTime.plusHours(1), "", 1, "Oslo", 1, LocalTime.of(0, 5)));
    /*0 train number should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> 
        new TrainDeparture(currentTime.plusHours(1), "H", 0, "Oslo", 1, LocalTime.of(0, 5)));
    /*Empty string destination should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> 
        new TrainDeparture(LocalTime.of(1, 1), "H", 1, "", 1, LocalTime.of(0, 5)));
  }

  @Test
  void shouldReturnValidDelay() {
    trainDeparture = new TrainDeparture(
        currentTime.plusHours(3), "M7", 04, "Oslo", LocalTime.of(0, 10));
    assertEquals(LocalTime.of(0, 10), trainDeparture.getDelay());
  }

  @Test
  void shouldReturn0DelayWithNullDelay() {
    trainDeparture = new TrainDeparture(
        currentTime.plusHours(3), "H3", 13, "Stabæk");
    assertEquals(LocalTime.of(0, 0), trainDeparture.getDelay());
  }

  @Test
  void shouldReturnDepartureTimeWithDelayWithValidDepartureAndDelay() {
    trainDeparture = new TrainDeparture(
        currentTime.plusHours(5), "E09", 12, "Høvik", LocalTime.of(0, 5));
    assertEquals(currentTime.plusHours(5).plusMinutes(5), trainDeparture.departureTimeWithDelay());
  }

  @Test
  void shouldReturnDepartureTimeWithDelayPastMidnight() {
    trainDeparture = new TrainDeparture(
        currentTime.plusHours(6), "K1", 22, "Asker", LocalTime.of(13, 0));
    assertEquals(currentTime.plusHours(6).plusHours(13), trainDeparture.departureTimeWithDelay());
  }

  @Test 
  void validateTrainDeparturePos() {
    /*Trenger jeg??? brukes i andre metoder */
  }

  @Test
  void validateTrainDepartureNeg() {
    /*Trenger jeg??? brukes i andre metoder */
  }
}
