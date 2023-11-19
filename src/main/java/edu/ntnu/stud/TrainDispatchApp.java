package edu.ntnu.stud;

/**
 * This is the main class for the train dispatch application.
 *
 * @author Madeleine Negård
 */
public class TrainDispatchApp {

  /**
   * The main method.
   */
  public static void main(String[]args) {
    ItrainDeparture newDeparture = new ItrainDeparture();

    newDeparture.init();
    newDeparture.start();
    
  }
  
}
