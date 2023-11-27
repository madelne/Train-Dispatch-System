package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.HashMap;

/**
 * This is the user interface ItrainDeparture.
 * 
 * 
 */
public class ItrainDispatchSystem {

  void init() {
    TrainDeparture train1 = new TrainDeparture(LocalTime.of(13, 16), "L1", 
        1, "Spikkestad", 4, LocalTime.of(0, 3));
    TrainDeparture train2 = new TrainDeparture(LocalTime.of(14, 14), "L13", 
        2, "Dal", 3);
    TrainDeparture train3 = new TrainDeparture(LocalTime.of(15, 0), "F2", 
        3, "Oslo Lufthavn", LocalTime.of(1, 0));
    TrainDeparture train4 = new TrainDeparture(LocalTime.of(9, 7), "H3",  
        4, "Hamar", 3, LocalTime.of(0, 13));
    HashMap<Integer, TrainDeparture> trains = new HashMap<>();
    trains.put(train1.getTrainNumber(), train1);
    trains.put(train2.getTrainNumber(), train2);
    trains.put(train3.getTrainNumber(), train3);
    trains.put(train4.getTrainNumber(), train4);
    new TrainDepartureRegister(trains);
  }

  void start() {
    
  }

}
