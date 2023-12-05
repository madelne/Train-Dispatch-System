package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.stream.Collectors;
import java.util.*;

/**
 * This class is a register that holds all train departures.
 *
 * @author Madeleine Negård
 */
public class TrainDepartureRegister {
        
  private HashMap<Integer, TrainDeparture> trainDepartures = new HashMap<>();
  private LocalTime currentTime;

  /**
   * Constructor for class TrainDepartureRegister.
   *
   * @param trainDepartures A HashMap that holds the train number as a key and the train departure
   *                        as a value
   */
  public TrainDepartureRegister(HashMap<Integer, TrainDeparture> trainDepartures) {
    this.currentTime = LocalTime.now().withSecond(0).withNano(0);
    this.trainDepartures = trainDepartures;
    validateTrainDepartureRegister();
  }

  /**
   * Constructor for class TrainDepartureRegister for empty register.
   */
  public TrainDepartureRegister() {
    HashMap<Integer, TrainDeparture> trainDepartures = new HashMap<>();
    this.trainDepartures = trainDepartures;
    this.currentTime = LocalTime.now().withSecond(0).withNano(0);
  }

  public HashMap<Integer, TrainDeparture> getTrainDepartures() {
    return this.trainDepartures;
  }

  /**
   *This method adds a new train departure to the register. If the train already exists, the method 
   *will throw an exception.
   *
   * @param trainDeparture The train departure the user wish to add
   *
   */
  public void addTrainDeparture(TrainDeparture trainDeparture) {
    if (trainDepartures.containsKey(trainDeparture.getTrainNumber())) {
      System.out.println("A train departure with this train number already exists!");
    } else {
      trainDepartures.putIfAbsent(trainDeparture.getTrainNumber(), trainDeparture);
      trainDeparture.validateTrainDeparture(currentTime);
      removePreviousDepartures();
    }
    
  }

  /**
   * This method removes the traindeparture with the given train number as its key.
   *
   * @param trainNumber The parameter is the train number of 
   *                    the train departure the user wishes to remove
   */
  public void removeTrainDeparture(int trainNumber) {
    if (trainDepartures.containsKey(trainNumber)) {
      trainDepartures.remove(trainNumber);
    } else {
      System.out.println("The train departure does not exist!");
    }
    
  }

  /**
   * This method finds the train departure with the matching train number.
   *
   * @param trainNumber The parameter is the train number 
   *                    that is used to search through the register
   * 
   * @return            Returns the trainDeparture with the matching train number
   *
   */
  public TrainDeparture searchByTrainNumber(int trainNumber) {
    if (trainNumber == 0) {
      throw new IllegalArgumentException();
    }
    return trainDepartures.get(trainNumber);
  }

  /**
   * This method puts all the trains with the given destination in a HashMap.
   *
   * @param destination The parameter is the destination that is used to search 
   *                    through the register
   * 
   * @return            Returns a HashMap with all the trains going to the given destination.
   *                    If there are no trains going to the given destination, the method 
   *                    returns an empty HashMap
   */
  public HashMap<Integer, TrainDeparture> searchByDestination(String destination) {
    if (destination == "") {
      throw new IllegalArgumentException();
    } else {
      HashMap<Integer, TrainDeparture> trainsWithDestination = new HashMap<>();
      trainDepartures.entrySet().stream()
          .filter(trainDeparture -> trainDeparture.getValue().getDestination().equals(destination))
          .forEach(trainDeparture -> trainsWithDestination.put(trainDeparture.getKey(), 
          trainDeparture.getValue()));
      return trainsWithDestination;
    }
  }

  
  /**
   * This method removes all trains that have had departure time, including delay, 
   * prior to the current time.
   */
  public void removePreviousDepartures() {
    this.trainDepartures = trainDepartures.entrySet().stream()
      .filter(entry -> {
        TrainDeparture trainDeparture = entry.getValue();
        LocalTime departureTimeWithDelay = trainDeparture.departureTimeWithDelay();
        return departureTimeWithDelay.isAfter(currentTime); })
      .collect(Collectors.toMap(entry -> entry.getValue().getTrainNumber(), 
        Map.Entry::getValue, 
        (existing, replacement) -> existing, HashMap::new));

    
  }

  
  /**
   * This method makes a HashMap with all the train departures sorted by departure time.
   *
   * @return Returns the sorted HashMap
   *
   */
  public LinkedHashMap<Integer, TrainDeparture> sortHashMap() {
    LinkedHashMap<Integer, TrainDeparture> sortedHashMap = new LinkedHashMap<>();
    trainDepartures.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(
        Comparator.comparing(TrainDeparture::getDepartureTime)))
        .forEach(trainDeparture -> 
        sortedHashMap.put(trainDeparture.getKey(), trainDeparture.getValue()));
    return sortedHashMap;
  }

  /**
   * This method throws an IllegalArgumentExceptin if the train 
   * register is null. If the departure time is prior to the current time, 
   * the method prints out a message and removes the train departure from the register.
   */
  public void validateTrainDepartureRegister() {
    if (trainDepartures == null) {
      throw new IllegalArgumentException();
    }
    trainDepartures.entrySet()
        .forEach(trainDeparture ->
        trainDeparture.getValue().validateTrainDeparture(currentTime));
    removePreviousDepartures();
    trainDepartures.entrySet().stream()
        .filter(trainDeparture -> 
        trainDeparture.getValue().departureTimeWithDelay()
        .isBefore(trainDeparture.getValue().getDepartureTime()))
        .forEach(trainDeparture -> 
        removeTrainDeparture(trainDeparture.getKey()));
  }

  public LocalTime getCurrentTime() {
    return currentTime;
  }

  public void setCurrentTime(LocalTime newTime) {
    this.currentTime = newTime.withSecond(0).withNano(0);
  }

  @Override
  public String toString() {
    return "{" 
      + " trainDepartures='" + getTrainDepartures() + "'" 
      + "}";
  }
  
}
