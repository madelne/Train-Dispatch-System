package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * This class is a register that holds all train departures.
 */
public class TrainDepartureRegister {
        
  public ArrayList<TrainDeparture> trainDepartures = new ArrayList<TrainDeparture>();

  public TrainDepartureRegister(ArrayList<TrainDeparture> trainDepartures) {
    this.trainDepartures = trainDepartures;
  }

  /**
   *This method adds a new train departure to the register. If the train already exists, the method 
   *will throw an exception.
   *
   * @param train
   *
   */
  public void addTrainDeparture(TrainDeparture train) {
    for (TrainDeparture trainDeparture : trainDepartures) {
      if (trainDeparture.getTrainNumber() == train.getTrainNumber()) {
        throw new IllegalArgumentException("The train departure already exists");
      }
    } 
    this.trainDepartures.add(train);
  }

  /**
   * This method takes a train number as a parameter and finds the train with the matching train 
   * number.
   *
   * @param trainNumber
   * 
   * @return Returns the trainDeparture with the matching train number.
   * 
   * @throws IllegalArgumentException throws exception if the train does not exist. 
   */
  public TrainDeparture searchByTrainNumber(int trainNumber) {
    return trainDepartures.stream().filter(train -> train.getTrainNumber() == trainNumber)
    .findFirst().orElseThrow(() -> new IllegalArgumentException("Train departure not found"));
  }

  /**
   * This method takes a destination and puts all the trains with this 
   * destination in an ArrayList.
   *
   * @param destination
   * 
   * @return Returns an ArrayList with all the trains going to the given destination.
   *     If there are no trains going to the given destination, the method returns an empty 
   *     ArrayList.
   */
  public ArrayList<TrainDeparture> searchByDestination(String destination) {
    ArrayList<TrainDeparture> trainsWithDestination = new ArrayList<TrainDeparture>();
    trainDepartures.stream().filter(train -> train.getDestination() == destination)
    .forEach(train -> trainsWithDestination.add(train));
    return trainsWithDestination;
  }

  /**
   * This method removes all trains that have had departure time, including delay, 
   * prior to the current time.
   */
  public void removePreviousDepartures() {
    trainDepartures.stream().filter(train -> train.getDepartureTime().plusHours(
        train.getDelay().getHour()).plusMinutes(train.getDelay().getMinute())
        .isAfter(LocalTime.now()));
  }

}
