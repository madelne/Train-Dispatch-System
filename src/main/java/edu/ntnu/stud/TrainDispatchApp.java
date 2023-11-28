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
  LocalTime currenTime;

  /**
   * This method prints out a timetable for the trainDepartures in the register.
   */
  public void printTimeTable() {
    System.out.println("-----------------------------------------------------");
    System.out.println("                     Timetable                       ");
    System.out.println("-----------------------------------------------------");
    System.out.println("| Departures           | Line    | Track    | "
        + currenTime + " |");
    System.out.println("-----------------------------------------------------");
    register.sortHashMap().entrySet().forEach(train -> 
        System.out.println(String.format("| %-20s | %-7s | %-8d | %5s |", 
        train.getValue().getDestination(),
        train.getValue().getLine(),
        train.getValue().getTrack(), 
        train.getValue().departureTimeWithDelay())));
    System.out.println("-----------------------------------------------------");
  }

  /**
   * This method takes in two numbers from the user, as hours and minutes.
   *
   * @return Returns the two numbers as a LocalTime value.
   */
  public LocalTime timeAsInput(String variable) {
    System.out.println(variable + " in format hh:mm:\n");
    Scanner input = new Scanner(System.in);
    input.useDelimiter(":");
    int departureTimeHours = input.nextInt();
    int departureTimeMinutes = input.nextInt();
    return LocalTime.of(departureTimeHours, departureTimeMinutes);
  }

  /**
   * This method takes a variable and asks the user to enter the variable as an input.
   *
   * @param variable
   *
   * @return Returns the variable as a string.
   */
  public String stringAsInput(String variable) {
    System.out.println(variable + ":");
    Scanner input = new Scanner(System.in);
    String stringFromUser = input.nextLine();
    input.close();
    return stringFromUser;
  }

  /**
   * This method takes a variable and asks the user to enter the variable as an integer input.
   *
   * @param variable
   *
   * @return Returns the variable as an integer.
   */
  public int integerAsInput(String variable) {
    System.out.println(variable + ":");
    Scanner input = new Scanner(System.in);
    int intFromUser = input.nextInt();
    input.close();
    return intFromUser;
  }

  public void setTime(LocalTime newTime) {
    this.currenTime = newTime;
  }

  void init() {
    this.currenTime = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute());
    register.addTrainDeparture(new TrainDeparture(LocalTime.of(17, 16), "L1", 
        1, "Spikkestad", 4, LocalTime.of(0, 3)));
    register.addTrainDeparture(new TrainDeparture(LocalTime.of(14, 14), "L13", 
        2, "Dal", 3));
    register.addTrainDeparture(new TrainDeparture(LocalTime.of(15, 0), "F2", 
        3, "Oslo Lufthavn", LocalTime.of(1, 0)));
    register.addTrainDeparture(new TrainDeparture(LocalTime.of(9, 7), "H3",  
        4, "Hamar", 3, LocalTime.of(0, 13)));
    printTimeTable();
  }
  
  void start() {
    System.out.println("1: Add train departure\n2: Remove train departure\n"
        + "3: Remove all previous departures\n4: Search by train number\n"
        + "5: Search by destination\n6: Add new register\n7: Add delay\n"
        + "8: Print updated timetable\n9: Set the current time\n10: Exit");
    Scanner input = new Scanner(System.in);
    int choice = input.nextInt();
    while (choice != 10) {
      switch (choice) {
        case 1:
          LocalTime departurTime = timeAsInput("Departure time");
          String line = stringAsInput("Line");
          int trainNumber = integerAsInput("Train number");
          String destination = stringAsInput("Destination");
          int track = integerAsInput("Track (write 0 if the train departure has no track)");
          LocalTime delay = timeAsInput("Delay");
          if (track == 0) {
            if (delay.equals(LocalTime.of(0, 0))) {
              register.addTrainDeparture(
                new TrainDeparture(departurTime, line, trainNumber, destination));
            } else {
              register.addTrainDeparture(
                new TrainDeparture(departurTime, line, trainNumber, destination, delay));
            }
          } else {
            if (delay.equals(LocalTime.of(0, 0))) {
              register.addTrainDeparture(
                new TrainDeparture(departurTime, line, trainNumber, destination, track));
            } else {
              register.addTrainDeparture(
                new TrainDeparture(departurTime, line, trainNumber, destination, track, delay));
            }
          }
          break;
        case 2:
          
        default:
          break;
      }
    }
    
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
