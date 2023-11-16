package edu.ntnu.stud;

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

}
