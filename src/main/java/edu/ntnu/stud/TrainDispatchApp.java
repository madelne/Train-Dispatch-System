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

  public TrainDepartureRegister register = new TrainDepartureRegister();

  /**
   * This method prints out a timetable for the trainDepartures in the register.
   */
  public void printTimeTable() {
    System.out.println("-----------------------------------------------------");
    System.out.println("                     Timetable                       ");
    System.out.println("-----------------------------------------------------");
    System.out.println("| Departures           | Line    | Track    | "
        + register.getCurrentTime() + " |");
    System.out.println("-----------------------------------------------------");
    register.sortHashMap().entrySet().forEach(train -> {
      String track = String.valueOf(train.getValue().getTrack());
      if (train.getValue().getTrack() == -1) {
        track = "";
      }
      System.out.println(String.format("| %-20s | %-7s | %-8s | %5s |", 
          train.getValue().getDestination(),
          train.getValue().getLine(),
          track, 
          train.getValue().departureTimeWithDelay()));  
        }
    );
    System.out.println("-----------------------------------------------------\n");
  }

  /**
   * This method takes in two numbers from the user, as hours and minutes.
   *
   * @return Returns the two numbers as a LocalTime value
   */
  public LocalTime timeAsInput(String variable) {
    System.out.println("\n" + variable + " hour:");
    Scanner input = new Scanner(System.in);
    String departureTimeHoursString = input.nextLine();
    int departureTimeHours = validateIntegerInput(departureTimeHoursString, 23, 0);
    System.out.println(variable + " minutes:");
    String departureTimeMinutesString = input.nextLine();
    int departureTimeMinutes = validateIntegerInput(departureTimeMinutesString, 59, 0);
    return LocalTime.of(departureTimeHours, departureTimeMinutes);
  }


  /**
   * This method asks the user to enter an input in the category of the variable.
   *
   * @param variable The paramteter takes a variable that represents the string that is returned
   *
   * @return         Returns the variable as a string
   */
  public String stringAsInput(String variable, int maxCharacters) {
    System.out.println("\n" + variable + ":");
    Scanner input = new Scanner(System.in);
    String stringFromUser = input.nextLine();
    String validString = validateStringInput(stringFromUser, maxCharacters);
    return validString;
  }

  /**
   * This method asks the user to enter an input in the category of the variable.
   *
   * @param variable The paramteter takes a variable that represents the integer that is returned
   * 
   * @param max      The maximum value limit
   * 
   * @param min      The minimum value limit
   *
   * @return         Returns the variable as an integer
   */
  public int integerAsInput(String variable, int max, int min) {
    System.out.println("\n" + variable + ":");
    Scanner input = new Scanner(System.in);
    String stringFromUser = input.nextLine();
    int intFromUser = validateIntegerInput(stringFromUser, max, min);
    return intFromUser;
  }

  /**
   * This method prints out a message if the input is not a number between the given 
   * maximum and minimum and asks for a new input.
   *
   * @param value The value is the input from the user
   * 
   * @param max   The maximum value limit
   * 
   * @param min   The minimum value limit
   * 
   * @return      The method returns the first valid input given by the user
   */
  public int validateIntegerInput(String value, int max, int min) {
    Scanner input = new Scanner(System.in);
    boolean valid = false;
    int validNumber = 0;
    while (valid == false) {
      try {
        validNumber = Integer.parseInt(value);
        if (validNumber <= max && validNumber >= min) {
          valid = true;
        } else {
          System.out.println("\nMust be a number between " + min + " and " + max 
              + "! Please try again:");
          value = input.nextLine();
        }
      } catch (NumberFormatException numberFormatException) {
        System.out.println("\nMust be a number between " + min + " and " + max
            + "! Please try again:");
        value = input.nextLine();
      }
    }
    return validNumber;
  }

  /**
   * This method asks for a new input if the parameter value has a number of characters over
   * the given maximum.
   *
   * @param value         The string input from the user
   *
   * @param maxCharacters The maximum number of characters
   *
   * @return              The method returns the first valid input given by the user
   */
  public String validateStringInput(String value, int maxCharacters) {
    Scanner input = new Scanner(System.in);
    boolean valid = false;
    while (valid == false) {
      if (value.length() <= maxCharacters) {
        valid = true;
      } else {
        System.out.println("\nNumber of characters is over the limit!"  
            + " Please enter a string with a maximum of " + maxCharacters + " letters:");
        value = input.nextLine();
      }
    }
    return value;
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
    register.setCurrentTime(LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute()));
    register.addTrainDeparture(new TrainDeparture(LocalTime.of(17, 45), "L1", 
        1, "Spikkestad", 4, LocalTime.of(0, 3)));
    register.addTrainDeparture(new TrainDeparture(LocalTime.of(14, 14), "L13", 
        2, "Dal", 3));
    register.addTrainDeparture(new TrainDeparture(LocalTime.of(20, 0), "F2", 
        3, "Oslo Lufthavn", LocalTime.of(1, 0)));
    register.addTrainDeparture(new TrainDeparture(LocalTime.of(17, 55), "H3",  
        4, "Hamar", 3, LocalTime.of(0, 13)));
    printTimeTable();
  }
  
  void start() {
    System.out.println("1: Add train departure\n2: Remove train departure\n"
        + "3: Remove all previous departures\n4: Search by train number\n"
        + "5: Search by destination\n6: Empty register\n7: Add delay\n"
        + "8: Print updated timetable\n9: Set the current time\n10: Exit\n");
    Scanner input = new Scanner(System.in);
    int choice = input.nextInt();
    while (choice != 10) {
      switch (choice) {
        case 1:
          register.addTrainDeparture(chooseTrainDepartureConstructor(timeAsInput("Departure time"), 
              stringAsInput("Line", 8), integerAsInput("Train number", 1000, 1), 
              stringAsInput("Destination", 21), 
              integerAsInput("Track (write 0 if the train departure has no track)", 100, 0), 
              timeAsInput("Delay")));
          break;
        case 2:
          register.removeTrainDeparture(integerAsInput(
              "The train departure's train number", 1000, 1));
          break;
        case 3:
          register.removePreviousAndTomorrowsDepartures();
          break;
        case 4:
          System.out.println(register.searchByTrainNumber(integerAsInput("Train number", 1000, 1)));
          break;
        case 5:
          System.out.println(register.searchByDestination(stringAsInput("Destination", 21)));
          break;
        case 6:
          boolean valid = false;
          String answer = stringAsInput(
              "Are you sure you want to delete all train departures? y/n", 1);
          while (valid == false) {
            if (answer.toLowerCase().equals("y")) {
              this.register = new TrainDepartureRegister();
              valid = true;
            } else if (answer.toLowerCase().equals("n")) {
              valid = true;
            } else {
              answer = stringAsInput("Please write 'y' for yes or 'n' for no", 1);
            }
          }
          break;
        case 7:
          try {
            register.getTrainDepartures().get(integerAsInput(
              "The train departure's train number", 1000, 1))
                .setDelay(timeAsInput("New delay"));
          } catch (NullPointerException nullPointerException) {
            System.out.println("The train departure does not exist!");
          }
          break;
        case 8:
          printTimeTable();
          break;
        case 9:
          register.setCurrentTime(timeAsInput("Set current time"));
          break;
        default:
          break;
      }
      System.out.println("\n1: Add train departure\n2: Remove train departure\n"
          + "3: Remove all previous departures\n4: Search by train number\n"
          + "5: Search by destination\n6: Empty register\n7: Add delay\n"
          + "8: Print updated timetable\n9: Set the current time\n10: Exit\n");
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
