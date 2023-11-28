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
  LocalTime currentTime;

  /**
   * This method prints out a timetable for the trainDepartures in the register.
   */
  public void printTimeTable() {
    System.out.println("-----------------------------------------------------");
    System.out.println("                     Timetable                       ");
    System.out.println("-----------------------------------------------------");
    System.out.println("| Departures           | Line    | Track    | "
        + currentTime + " |");
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
   * @return Returns the two numbers as a LocalTime value
   */
  public LocalTime timeAsInput(String variable) {
    System.out.println(variable + " hour:");
    Scanner input = new Scanner(System.in);
    int departureTimeHours = Integer.parseInt(input.nextLine());
    System.out.println(variable + " minutes:");
    int departureTimeMinutes = Integer.parseInt(input.nextLine());
    return LocalTime.of(departureTimeHours, departureTimeMinutes);
  }

  /**
   * This method asks the user to enter an input in the category of the variable.
   *
   * @param variable The paramteter takes a variable that represents the string that is returned
   *
   * @return         Returns the variable as a string
   */
  public String stringAsInput(String variable) {
    System.out.println(variable + ":");
    Scanner input = new Scanner(System.in);
    String stringFromUser = input.nextLine();
    return stringFromUser;
  }

  /**
   * This method asks the user to enter an input in the category of the variable.
   *
   * @param variable The paramteter takes a variable that represents the integer that is returned
   *
   * @return         Returns the variable as an integer
   */
  public int integerAsInput(String variable) {
    System.out.println(variable + ":");
    Scanner input = new Scanner(System.in);
    int intFromUser = Integer.parseInt(input.nextLine());
    return intFromUser;
  }

  public void setTime(LocalTime newTime) {
    this.currentTime = newTime;
  }

  /**
   * This method creates a train departure with the right constructor, even if the 
   * track and delay is set to 0.
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
   */
  public TrainDeparture chooseTrainDepartureConstructor(LocalTime departureTime, 
      String line, int trainNumber, String destination, int track, LocalTime delay) {
    if (track == 0) {
      if (delay.equals(LocalTime.of(0, 0))) {
        return new TrainDeparture(departureTime, line, trainNumber, destination);
      } else {
        return new TrainDeparture(departureTime, line, trainNumber, destination, delay);
      }
    } else {
      if (delay.equals(LocalTime.of(0, 0))) {
        return new TrainDeparture(departureTime, line, trainNumber, destination, track);
      } else {
        return new TrainDeparture(departureTime, line, trainNumber, destination, track, delay);
      }
    }
  }

  void init() {
    this.currentTime = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute());
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
        + "5: Search by destination\n6: Empty register\n7: Add delay\n"
        + "8: Print updated timetable\n9: Set the current time\n10: Exit");
    Scanner input = new Scanner(System.in);
    int choice = input.nextInt();
    while (choice != 10) {
      switch (choice) {
        case 1:
          register.addTrainDeparture(chooseTrainDepartureConstructor(timeAsInput("Departure time"), 
              stringAsInput("Line"), integerAsInput("Train number"), stringAsInput("Destination"), 
              integerAsInput("Track (write 0 if the train departure has no track)"), 
              timeAsInput("Delay")));
          break;
        case 2:
          register.removeTrainDeparture(integerAsInput("The train departure's train number"));
          break;
        case 3:
          register.removePreviousDepartures();
          break;
        case 4:
          System.out.println(register.searchByTrainNumber(integerAsInput("Train number")));
          break;
        case 5:
          System.out.println(register.searchByDestination(stringAsInput("Destination")));
          break;
        case 6:
          this.register = new TrainDepartureRegister();
          break;
        default:
          break;
      }
      System.out.println("1: Add train departure\n2: Remove train departure\n"
          + "3: Remove all previous departures\n4: Search by train number\n"
          + "5: Search by destination\n6: Add new register\n7: Add delay\n"
          + "8: Print updated timetable\n9: Set the current time\n10: Exit");
      choice = input.nextInt();
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
