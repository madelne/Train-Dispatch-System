package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

/**
 * This is the test class for the TrainDeparture class.
 */

public class TrainDepartureTest {
  
  public LocalTime currentTime = LocalTime.now().withSecond(0).withNano(0);

  @Test
  void shouldInitialiseValidTrainDeparture() {
    TrainDeparture train = new TrainDeparture(currentTime.plusHours(1), "C30", 
        123, "Lillestrøm", 3, LocalTime.of(0, 10));
    assertEquals(currentTime.plusHours(1), train.getDepartureTime());
    assertEquals("C30", train.getLine());
    assertEquals(123, train.getTrainNumber());
    assertEquals("Lillestrøm", train.getDestination());
    assertEquals(3, train.getTrack());
    assertEquals(LocalTime.of(0, 10), train.getDelay());
  }

  @Test
  void shouldInitialiseValidTrainDepartureExtreme() {
    TrainDeparture train = new TrainDeparture(currentTime.plusHours(1), "0", 
        1, "/+-#.", 100, LocalTime.of(23, 0));
    assertEquals(currentTime.plusHours(1), train.getDepartureTime());
    assertEquals("0", train.getLine());
    assertEquals(1, train.getTrainNumber());
    assertEquals("/+-#.", train.getDestination());
    assertEquals(100, train.getTrack());
    assertEquals(LocalTime.of(23, 0), train.getDelay());
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
    TrainDeparture train = new TrainDeparture(currentTime.plusHours(1), "M26", 44, "Asker", 2);
    assertEquals(currentTime.plusHours(1), train.getDepartureTime());
    assertEquals("M26", train.getLine());  
    assertEquals(44, train.getTrainNumber());
    assertEquals("Asker", train.getDestination());
    assertEquals(2, train.getTrack());
  }

  @Test
  void shouldInitialiseValidTrainDepartureWithoutDelayExtreme() {
    TrainDeparture train = new TrainDeparture(currentTime.plusHours(1), "+/#æøå", 100, 
        "+/#æøå", 100);
    assertEquals(currentTime.plusHours(1), train.getDepartureTime());
    assertEquals("+/#æøå", train.getLine());
    assertEquals(100, train.getTrainNumber());
    assertEquals("+/#æøå", train.getDestination());
    assertEquals(100, train.getTrack());
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
    TrainDeparture train = new TrainDeparture(currentTime.plusHours(1), "R12", 21, 
        "Gjøvik", LocalTime.of(0, 10));
    assertEquals(currentTime.plusHours(1), train.getDepartureTime());
    assertEquals("R12", train.getLine());
    assertEquals(21, train.getTrainNumber());
    assertEquals("Gjøvik", train.getDestination());
    assertEquals(LocalTime.of(0, 10), train.getDelay());
  }

  @Test
  void shouldInitialiseValidTrainDepartureWithoutTrackExtreme() {
    TrainDeparture train = new TrainDeparture(currentTime.plusHours(1), "+/#æøå", 
        100, "+/#æøå", LocalTime.of(23, 0));
    assertEquals(currentTime.plusHours(1), train.getDepartureTime());
    assertEquals("+/#æøå", train.getLine());
    assertEquals(100, train.getTrainNumber());
    assertEquals("+/#æøå", train.getDestination());
    assertEquals(LocalTime.of(23, 0), train.getDelay());
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
    TrainDeparture train = new TrainDeparture(currentTime.plusHours(1), "E9", 912, "Høvik");
    assertEquals(currentTime.plusHours(1), train.getDepartureTime());
    assertEquals("E9", train.getLine());
    assertEquals(912, train.getTrainNumber());
    assertEquals("Høvik", train.getDestination());
  }

  @Test
  void shouldInitialiseValidTrainDepartureWithoutDelayAndTrackExtreme() {
    TrainDeparture train = new TrainDeparture(currentTime.plusHours(1), 
        "+/#æøå", 100, "Blommenholm");
    assertEquals(currentTime.plusHours(1), train.getDepartureTime());
    assertEquals("+/#æøå", train.getLine());
    assertEquals(100, train.getTrainNumber());
    assertEquals("Blommenholm", train.getDestination());
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
    TrainDeparture trainDeparture = new TrainDeparture(
        currentTime.plusHours(3), "M7", 04, "Oslo", LocalTime.of(0, 10));
    assertEquals(LocalTime.of(0, 10), trainDeparture.getDelay());
  }

  @Test
  void shouldReturn0DelayWithNullDelay() {
    TrainDeparture trainDeparture = new TrainDeparture(
        currentTime.plusHours(3), "H3", 13, "Stabæk");
    assertEquals(LocalTime.of(0, 0), trainDeparture.getDelay());
  }

  @Test
  void shouldReturnDepartureTimeWithDelayWithValidDepartureAndDelay() {
    TrainDeparture trainDeparture = new TrainDeparture(
        currentTime.plusHours(5), "E09", 12, "Høvik", LocalTime.of(0, 5));
    assertEquals(currentTime.plusHours(5).plusMinutes(5), trainDeparture.departureTimeWithDelay());
  }

  @Test
  void shouldReturnDepartureTimeWithDelayPastMidnight() {
    TrainDeparture trainDeparture = new TrainDeparture(
        currentTime.plusHours(6), "K1", 22, "Asker", LocalTime.of(13, 0));
    assertEquals(currentTime.plusHours(6).plusHours(13), trainDeparture.departureTimeWithDelay());
  }

  @Test 
  void validateTrainDeparturePos() {
    /*fyll inn */
  }

  @Test
  void validateTrainDepartureNeg() {
    /*fyll inn */
  }
}
