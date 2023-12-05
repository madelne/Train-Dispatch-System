package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * This class represents a train departure. It has a field for the train's departure time as 
 * a LocalTime value, because we want it portrayed as hh:mm. 
 * 
 * <p>The next field is the line, which i have set as a string. This is because the line is a word 
 * or a name. 
 * 
 * <p>The next field is the trainnumber, which is set as an integer, due to it being a number. 
 * 
 * <p>The field destination is a string.
 * 
 * <p>The schedule is a set plan and the fields that would change the schedule are departureTime and
 * destination. Therefore they are both private final fields with no set method.
 * 
 *<p>Neither the line og trainnumber has a set method and are both private final fields, as they 
 * identify the train and they are not something we wish to change. 
 * 
 * <p>The track is an integer and has a set method. The train's track would still follow the set schedule 
 * if it changed tracks so it is a private, but not final field. 
 * 
 * <p>The delay is portrayed as hh:mm and is therefore set as a LocalTime value. The time of delay 
 * is unpredictable and has a set method. The field is private, but not final.
 * 
 * <p>A train departure with departure time null, line as an empty string, train number below 0 or over 
 * 1000, destination as an empty string, track below 0 or over 100 or a 0 delay would not be a valid
 * train departure.
 *
 * <p>@author Madeleine Negård
 */
public class TrainDeparture {

  private final LocalTime departureTime;
  private final String line;
  private final int trainNumber;
  private final String destination;
  private int track;
  private LocalTime delay;

  /** 
   * Constructor for class TrainDeparture.
   *
   * @param departureTime The train's time of departure
   * 
   * @param line          The name of the line
   * 
   * @param trainNumber   The train's number
   * 
   * @param destination   The train's final destination
   * 
   * @param track         The track the train will departure from
   * 
   * @param delay         Amount of time the train is delayed
   * 
   * */ 
  public TrainDeparture(LocalTime departureTime, String line, int trainNumber, 
        String destination, int track, LocalTime delay) {
    if (departureTime == null) {
      throw new IllegalArgumentException("The departure time can not be null!");
    }
    if (line.equals("")) {
      throw new IllegalArgumentException("The line can not be empty!");
    }
    if (trainNumber <= 0 || trainNumber > 1000) {
      throw new IllegalArgumentException("The train number can not be 0 or under or over 1000!");
    }
    if (destination == "") {
      throw new IllegalArgumentException("The destination can not be empty!");
    }
    if (track < 0 || track > 100) {
      throw new IllegalArgumentException("The track does not exist!");
    }
    if (delay == LocalTime.of(0, 0)) {
      throw new IllegalArgumentException("The delay can not be 0!");
    }
    this.departureTime = departureTime.withSecond(0).withNano(0);
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.track = track;
    this.delay = delay;
  }


  /**
   * Constructor for class TrainDeparture without delay.
   *
   * @param departureTime The train's time of departure
   *  
   * @param line          The name of the line
   * 
   * @param trainNumber   The train's number
   * 
   * @param destination   The train's final destination
   * 
   * @param track         The track the train will departure from
   * 
   */
  public TrainDeparture(LocalTime departureTime, String line, 
      int trainNumber, String destination, int track) {
    if (departureTime == null) {
      throw new IllegalArgumentException("The departure time can not be null!");
    }
    if (line == "") {
      throw new IllegalArgumentException("The line can not empty!");
    }
    if (trainNumber <= 0 || trainNumber > 1000) {
      throw new IllegalArgumentException("The train number can not be 0 or under or over 1000!");
    }
    if (destination == "") {
      throw new IllegalArgumentException("The destination can not be empty!");
    }
    if (track < 0 || track > 100) {
      throw new IllegalArgumentException("The track does not exist!");
    }
    this.departureTime = departureTime.withSecond(0).withNano(0);
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.track = track;
  }

  /**
   * Constructor for class TrainDeparture without track.
   *
   * @param departureTime The train's time of departure
   * 
   * @param line          The name of the line
   * 
   * @param trainNumber   The train's number
   * 
   * @param destination   The train's final destination
   * 
   * @param delay         Amount of time the train is delayed
   * 
   */
  public TrainDeparture(LocalTime departureTime, String line, 
      int trainNumber, String destination, LocalTime delay) {
    if (departureTime == null) {
      throw new IllegalArgumentException("The departure time can not be null!");
    }
    if (line == "") {
      throw new IllegalArgumentException("The line can not empty!");
    }
    if (trainNumber <= 0 || trainNumber > 1000) {
      throw new IllegalArgumentException("The train number can not be 0 or under or over 1000!");
    }
    if (destination == "") {
      throw new IllegalArgumentException("The destination can not be empty!");
    }
    if (delay == LocalTime.of(0, 0)) {
      throw new IllegalArgumentException("The delay can not be 0!");
    }
    this.departureTime = departureTime.withSecond(0).withNano(0);
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.delay = delay;
    setTrack(-1);
  }


  /**
   * Constructor for class TrainDeparture without track and delay.
   *
   * @param departureTime The train's time of departure
   * 
   * @param line          The name of the line
   * 
   * @param trainNumber   The train's number
   * 
   * @param destination   The train's final destination
   * 
   */
  public TrainDeparture(LocalTime departureTime, String line, 
      int trainNumber, String destination) {
    if (departureTime == null) {
      throw new IllegalArgumentException("The departure time can not be null!");
    }
    if (line == "") {
      throw new IllegalArgumentException("The line can not empty!");
    }
    if (trainNumber <= 0 || trainNumber > 1000) {
      throw new IllegalArgumentException("The train number can not be 0 or under or over 1000!");
    }
    if (destination == "") {
      throw new IllegalArgumentException("The destination can not be empty!");
    }
    this.departureTime = departureTime.withSecond(0).withNano(0);
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    setTrack(-1);
  }

  /**
   * This method adds the delay to the original departure time.
   *
   * @return Returns the new departure time as a LocalTime value
   */
  public LocalTime departureTimeWithDelay() {
    return departureTime.plusHours(getDelay().getHour())
    .plusMinutes(getDelay().getMinute());
  }

  /**
   * This method prints out a message if the departure time is prior to the current time.
   *
   * @param currentTime The parameter is the current time.
   */
  public void validateTrainDeparture(LocalTime currentTime) {
    if (departureTimeWithDelay().isBefore(currentTime)) {
      System.out.println(
          "Train number " + trainNumber 
          + " has left the station and has been removed from the register!");
    }
  }

  public LocalTime getDepartureTime() {
    return this.departureTime;
  }
  
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

  private void setTrack(int track) {
    this.track = track;
  }

  /** 
   * Returns the delay. If the delay is null, the method returns 0 minutes and 0 hours.
   *
   * @return LocalTime
   */
  public LocalTime getDelay() {
    if (this.delay == null) {
      return LocalTime.of(0, 0);
    }
    return this.delay;
    
  }
    
  public void setDelay(LocalTime delay) {
    this.delay = delay;
  }

  @Override
  public String toString() {
    if (getDelay() == LocalTime.of(0, 0)) {
      return "{" 
        + " departureTime='" + getDepartureTime().getHour() + ":" 
        + getDepartureTime().getMinute() + "'" 
        + ", line='" + getLine() + "'" 
        + ", trainNumber='" + getTrainNumber() + "'" 
        + ", destination='" + getDestination() + "'" 
        + ", track='" + getTrack() + "'"  
        + "}";
    }
    return "{" 
        + " departureTime='" + getDepartureTime().getHour() + ":"
        + getDepartureTime().getMinute() + "'" 
        + ", line='" + getLine() + "'" 
        + ", trainNumber='" + getTrainNumber() + "'" 
        + ", destination='" + getDestination() + "'" 
        + ", track='" + getTrack() + "'" 
        + ", delay='" + getDelay() + "'" 
        + "}";
  }

}
