package edu.ntnu.stud;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {

  public static void main(String[]args){
    ItrainRegister newRegister = new ItrainRegister();

    newRegister.init();
    newRegister.start();
    
  }
  
}
