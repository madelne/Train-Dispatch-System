package edu.ntnu.stud;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {
  
  private int departure_time;
  private String line;
  private int trainNumber;
  private String destination;
  private int track;
  private int delay;

  public TrainDispatchApp(int departure_time, String line, int trainNumber, String destination, int track, int delay) {
    this.departure_time = departure_time;
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.track = track;
    this.delay = delay;
  }
  
  

}
