package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * This class is a register that holds all train departures.
 *
 * @author Madeleine Negård
 */
public class TrainDepartureRegister {
        
  public HashMap<Integer, TrainDeparture> trainDepartures = new HashMap<>();

  /**
   * Constructor for class TrainDepartureRegister.
   *
   * @param trainDepartures A HashMap that holds the train number as a key and the train departure
   *     as a value.
   */
  public TrainDepartureRegister(HashMap<Integer, TrainDeparture> trainDepartures) {
    if (trainDepartures == null) {
      throw new IllegalArgumentException();
    }
    this.trainDepartures = trainDepartures;
  }

  /**
   * Constructor for class TrainDepartureRegister for empty register.
   */
  public TrainDepartureRegister() {
    HashMap<Integer, TrainDeparture> trainDepartures = new HashMap<>();
    this.trainDepartures = trainDepartures;
  }

  public HashMap<Integer, TrainDeparture> getTrainDepartures() {
    return this.trainDepartures;
  }

  /**
   *This method adds a new train departure to the register. If the train already exists, the method 
   *will throw an exception.
   *
   * @param train
   *
   */
  public void addTrainDeparture(TrainDeparture train) {
    if (trainDepartures.containsKey(train.getTrainNumber())) {
      throw new IllegalArgumentException();
    }
    trainDepartures.putIfAbsent(train.getTrainNumber(), train);
  }

  /**
   * This method takes a train number as a parameter and finds the train with the matching train 
   * number.
   *
   * @param trainNumber
   * 
   * @return Returns the trainDeparture with the matching train number.
   *
   */
  public TrainDeparture searchByTrainNumber(int trainNumber) {
    if (trainNumber == 0) {
      throw new IllegalArgumentException();
    }
    return trainDepartures.get(trainNumber);
  }

  /**
   * This method takes a destination and puts all the trains with this 
   * destination in a HashMap.
   *
   * @param destination
   * 
   * @return Returns a HashMap with all the trains going to the given destination.
   *     If there are no trains going to the given destination, the method returns an empty 
   *     HashMap.
   */
  public HashMap<Integer, TrainDeparture> searchByDestination(String destination) {
    if (destination == "") {
      throw new IllegalArgumentException();
    }
    HashMap<Integer, TrainDeparture> trainsWithDestination = new HashMap<>();
    trainDepartures.entrySet().stream()
        .filter(train -> train.getValue().getDestination() == destination)
        .forEach(train -> trainsWithDestination.put(train.getKey(), train.getValue()));
    return trainsWithDestination;
  }

  /**
   * This method adds the delay to the original departure time.
   *
   * @param train 
   *
   * @return Returns the new departure time as a LocalTime value.
   */
  public LocalTime departureTimeWithDelay(TrainDeparture train) {
    return train.getDepartureTime().plusHours(train.getDelay().getHour())
    .plusMinutes(train.getDelay().getMinute());
  }

  /**
   * This method removes all trains that have had departure time, including delay, 
   * prior to the current time.
   */
  public void removePreviousDepartures() {
    this.trainDepartures = trainDepartures.entrySet().stream()
      .filter(entry -> {
        TrainDeparture train = entry.getValue();
        LocalTime departureTimeWithDelay = departureTimeWithDelay(train);
        return departureTimeWithDelay.isAfter(LocalTime.now()); })
      .collect(Collectors.toMap(entry -> entry.getValue().getTrainNumber(), 
        Map.Entry::getValue, 
        (existing, replacement) -> existing, HashMap::new));

    
  }

  /**
   * This method makes a HashMap with all the train departures sorted by departure time.
   *
   * @return Returns the sorted HashMap.
   *
   */
  public HashMap<Integer, TrainDeparture> sortList() {
    HashMap<Integer, TrainDeparture> sortertListe = new HashMap<>();
    trainDepartures.entrySet().stream()
    .sorted((train1, train2) -> 
    (train1.getValue().getDepartureTime().getHour()
     - train2.getValue().getDepartureTime().getHour()) 
     * 100 + (train1.getValue().getDepartureTime().getMinute() 
     * - train2.getValue().getDepartureTime().getMinute()))
    .forEach(train -> sortertListe.put(train.getKey(), train.getValue()));
    return sortertListe;
  }

  /**
   * This method prints out a timetable for the trainDepartures in the register.
   */
  public void printTimeTable() {
    System.out.println("--------------------------------------------");
    System.out.println("                 Timetable                  ");
    System.out.println("--------------------------------------------");
    System.out.println("| Departures    | Line    | Track    |"
        + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + "|");
    System.out.println("--------------------------------------------");
    trainDepartures.entrySet().forEach(train -> System.out.println(train.getValue().getDepartureTime()));
  }


  @Override
  public String toString() {
    return "{" 
      + " trainDepartures='" + getTrainDepartures() + "'" 
      + "}";
  }
  
}
