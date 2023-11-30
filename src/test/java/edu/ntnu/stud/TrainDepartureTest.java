package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

/**
 * This is the test class for the TrainDeparture class.
 */

public class TrainDepartureTest {
  
  public LocalTime currentTime = LocalTime.now().withSecond(0).withNano(0);

  @Test
  void testConstructor1Pos() {
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
  void testConstructor1Extreme() {
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
  void testConstructor1Neg() {
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
    /*0 track should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> 
        new TrainDeparture(currentTime.plusHours(1), "H", 1, "Oslo", 0, LocalTime.of(0, 5)));
    /*0 delay should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> 
        new TrainDeparture(currentTime.plusHours(1), "H", 1, "Oslo", 1, LocalTime.of(0, 0)));
  }
    
  @Test
  void testConstructor2Pos() {
    TrainDeparture train = new TrainDeparture(currentTime.plusHours(1), "M26", 44, "Asker", 2);
    assertEquals(currentTime.plusHours(1), train.getDepartureTime());
    assertEquals("M26", train.getLine());  
    assertEquals(44, train.getTrainNumber());
    assertEquals("Asker", train.getDestination());
    assertEquals(2, train.getTrack());
  }

  @Test
  void testConstructor2Extreme() {
    TrainDeparture train = new TrainDeparture(currentTime.plusHours(1), "+/#æøå", 100, 
        "+/#æøå", 100);
    assertEquals(currentTime.plusHours(1), train.getDepartureTime());
    assertEquals("+/#æøå", train.getLine());
    assertEquals(100, train.getTrainNumber());
    assertEquals("+/#æøå", train.getDestination());
    assertEquals(100, train.getTrack());
  }

  @Test
  void testConstructor2Neg() {
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
    /*0 track should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> 
        new TrainDeparture(currentTime.plusHours(1), "H", 1, "Oslo", 0));
  }

  @Test
  void testConstructor3Pos() {
    TrainDeparture train = new TrainDeparture(currentTime.plusHours(1), "R12", 21, 
        "Gjøvik", LocalTime.of(0, 10));
    assertEquals(currentTime.plusHours(1), train.getDepartureTime());
    assertEquals("R12", train.getLine());
    assertEquals(21, train.getTrainNumber());
    assertEquals("Gjøvik", train.getDestination());
    assertEquals(LocalTime.of(0, 10), train.getDelay());
  }

  @Test
  void testConstructor3Extreme() {
    TrainDeparture train = new TrainDeparture(currentTime.plusHours(1), "+/#æøå", 
        100, "+/#æøå", LocalTime.of(23, 0));
    assertEquals(currentTime.plusHours(1), train.getDepartureTime());
    assertEquals("+/#æøå", train.getLine());
    assertEquals(100, train.getTrainNumber());
    assertEquals("+/#æøå", train.getDestination());
    assertEquals(LocalTime.of(23, 0), train.getDelay());
  }

  @Test
  void testConstructor3Neg() {
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
    /*0 delay should throw IllegalArgumentException*/
    assertThrows(IllegalArgumentException.class, () -> 
        new TrainDeparture(currentTime.plusHours(1), "H", 1, "Oslo", 1, LocalTime.of(0, 0)));

  }

  @Test
  void testConstructor4Pos() {
    TrainDeparture train = new TrainDeparture(currentTime.plusHours(1), "E9", 912, "Høvik");
    assertEquals(currentTime.plusHours(1), train.getDepartureTime());
    assertEquals("E9", train.getLine());
    assertEquals(912, train.getTrainNumber());
    assertEquals("Høvik", train.getDestination());
  }

  @Test
  void testConstructor4Extreme() {
    TrainDeparture train = new TrainDeparture(currentTime.plusHours(1), 
        "+/#æøå", 100, "Blommenholm");
    assertEquals(currentTime.plusHours(1), train.getDepartureTime());
    assertEquals("+/#æøå", train.getLine());
    assertEquals(100, train.getTrainNumber());
    assertEquals("Blommenholm", train.getDestination());
  }

  @Test 
  void testConstructor4Neg() {
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
}
