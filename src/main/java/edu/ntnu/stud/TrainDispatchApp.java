package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This is the user interface for the train dispatch system.
 *
 * @author Madeleine Negård
 */
public class TrainDispatchApp {

  TrainDepartureRegister register = new TrainDepartureRegister();

  void init() {
    register.addTrainDeparture(new TrainDeparture(LocalTime.of(17, 16), "L1", 
        1, "Spikkestad", 4, LocalTime.of(0, 3)));
    register.addTrainDeparture(new TrainDeparture(LocalTime.of(14, 14), "L13", 
        2, "Dal", 3));
    register.addTrainDeparture(new TrainDeparture(LocalTime.of(15, 0), "F2", 
        3, "Oslo Lufthavn", LocalTime.of(1, 0)));
    register.addTrainDeparture(new TrainDeparture(LocalTime.of(9, 7), "H3",  
        4, "Hamar", 3, LocalTime.of(0, 13)));
    register.printTimeTable();
  }
  
  void start() {
    System.out.println("1: Add train departure\n2: Remove train departure\n"
        + "3: Remove all previous departures\n4: Search by train number\n"
        + "5: Search by destination\n6: Add new register\n7: Add delay\n"
        + "8: Print updated timetable\n9: Exit");
    
    
  }
  
  /**
   * This is the main method.
   *
   * @param args
   */
  public static void main(String[]args) {
    TrainDispatchApp menu = new TrainDispatchApp();
    menu.init();
    menu.start();
    
  }
  
}
