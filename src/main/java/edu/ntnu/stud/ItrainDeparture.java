package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * This is the user interface ItrainDeparture.
 * 
 * 
 */
public class ItrainDeparture {

  void start() {
    TrainDeparture train1 = new TrainDeparture(LocalTime.of(13, 16), "L1", 
        1, "Spikkestad", 4, LocalTime.of(0, 3));
    TrainDeparture train2 = new TrainDeparture(LocalTime.of(14, 14), "L13", 
        2, "Dal", 3, LocalTime.of(0, 0));
    TrainDeparture train3 = new TrainDeparture(LocalTime.of(15, 0), "F2", 
        3, "Oslo Lufthavn", 3, LocalTime.of(1, 0));
    System.out.println(train1.toString());
    System.out.println(train2.toString());
    System.out.println(train3.toString());
  }


  void init() {

  }

}
