package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * This class represents a train departure. It has an indice for the train's departure time as 
 * a LocalTime value, because we want it portrayed as hh:mm. The departure time is set and can 
 * not be changed due to the schedule being a set plan. 
 * 
 * <p>The next indice is the line, which i have set as a string. This is because the line is a word 
 * or a name. 
 * 
 * <p>The next indice is the trainnumber, which is set as aninteger, due to it being a number. 
 * Neither the line og trainnumber has a set method, as they identify the train and they are 
 * not something we wish to change. 
 * 
 * <p>The destination is written as a string, since this is the name of a place. The tracks are 
 * named with numbers so it is set as an integer. As the schedule is unchangeable, the 
 * destination and track do not have a set method. 
 * 
 * <p>The delay is portrayed as hh:mm and is therefore set as a LocalTime value. The time of delay 
 * is unpredictable and has a set method. 
 * 
 * <p>UGYLDIG DATA:
 * ????????????
 */

public class TrainDeparture {

  private LocalTime departureTime;
  private String line;
  private int trainNumber;
  private String destination;
  private int track;
  private LocalTime delay;

  public TrainDeparture(LocalTime departureTime, String line, int trainNumber, 
        String destination, int track, LocalTime delay) {
    if (departureTime == null) {
      throw new IllegalArgumentException("The departure time can not be null");
    }
    if (line == "") {
      throw new IllegalArgumentException("The line can not be empty");
    }
    if (trainNumber <= 0) {
      throw new IllegalArgumentException("The train number can not be 0 or under");
    }
    if (destination == "") {
      throw new IllegalArgumentException("The destination can not be empty");
    }
    if (track <= 0) {
      throw new IllegalArgumentException("The track does not exist");
    }
    if (delay == LocalTime.of(0, 0)) {
      throw new IllegalArgumentException("The delay can not be 0");
    }
    this.departureTime = departureTime;
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.track = track;
    this.delay = delay;
  }

  public TrainDeparture(LocalTime departureTime, String line, 
      int trainNumber, String destination, int track) {
    if (departureTime == null) {
      throw new IllegalArgumentException("The departure time can not be null");
    }
    if (line == "") {
      throw new IllegalArgumentException("The line can not empty");
    }
    if (trainNumber <= 0) {
      throw new IllegalArgumentException("The train number can not be 0 or under");
    }
    if (destination == "") {
      throw new IllegalArgumentException("The destination can not be empty");
    }
    if (track <= 0) {
      throw new IllegalArgumentException("The track does not exist");
    }
    this.departureTime = departureTime;
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.track = track;
  }

  public TrainDeparture(LocalTime departureTime, String line, 
      int trainNumber, String destination, LocalTime delay) {
    if (departureTime == null) {
      throw new IllegalArgumentException("The departure time can not be null");
    }
    if (line == "") {
      throw new IllegalArgumentException("The line can not empty");
    }
    if (trainNumber <= 0) {
      throw new IllegalArgumentException("The train number can not be 0 or under");
    }
    if (destination == "") {
      throw new IllegalArgumentException("The destination can not be empty");
    }
    if (delay == LocalTime.of(0, 0)) {
      throw new IllegalArgumentException("The delay can not be 0");
    }
    this.departureTime = departureTime;
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.delay = delay;
  }

  public TrainDeparture(LocalTime departureTime, String line, 
      int trainNumber, String destination) {
    if (departureTime == null) {
      throw new IllegalArgumentException("The departure time can not be null");
    }
    if (line == "") {
      throw new IllegalArgumentException("The line can not empty");
    }
    if (trainNumber <= 0) {
      throw new IllegalArgumentException("The train number can not be 0 or under");
    }
    if (destination == "") {
      throw new IllegalArgumentException("The destination can not be empty");
    }
    this.departureTime = departureTime;
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
  }

    
    
  /** 
   * @return LocalTime
  */
  public LocalTime getDeparture_time() {
    return this.departureTime;
  }

    
  /** 
   * @return String
  */
  public String getLine() {
    return this.line;
  }

  public int getTrainNumber() {
    return this.trainNumber;
  }

  public String getDestination() {
    return this.destination;
  }

  public int getTrack() {
    return this.track;
  }

  public LocalTime getDelay() {
    return this.delay;
  }
    
  public void setDelay(LocalTime delay) {
    this.delay = delay;
  }


  @Override
  public String toString() {
    return "{" 
        + " departureTime='" + getDeparture_time() + "'" 
        + ", line='" + getLine() + "'" 
        + ", trainNumber='" + getTrainNumber() + "'" 
        + ", destination='" + getDestination() + "'" 
        + ", track='" + getTrack() + "'" 
        + ", delay='" + getDelay() + "'" 
        + "}";
  }

}
