package edu.ntnu.stud;
import java.time.LocalTime;

/**
 * This class represents a train departure. It has an indice for the train's departure time as a LocalTime value, because we want it 
 * portrayed as hh:mm. The departure time is set and can not be changed due to the schedule being a set plan. 
 * 
 * The next indice is the line, which i have set as a string. This is because the line is a word or a name. 
 * 
 * The next indice is the trainnumber, which is set as aninteger, due to it being a number. Neither the line og trainnumber has a set 
 * method, as they identify the train and they are not something we wish to change. 
 * 
 * The destination is written as a string, since this is the name of a place. 
 * The tracks are named with numbers so it is set as an integer. As the schedule is unchangeable, the destination and track do not 
 * have a set method. 
 * The delay is portrayed as hh:mm and is therefore set as a LocalTime value. The time of delay is unpredictable and has a set method. 
 * 
 * UGYLDIG DATA:
 * ????????????
 */

public class TrainDeparture {
    private LocalTime departure_time;
    private String line;
    private int trainNumber;
    private String destination;
    private int track;
    private LocalTime delay;

    public TrainDeparture(LocalTime departure_time, String line, int trainNumber, String destination, int track, LocalTime delay) {
        this.departure_time = departure_time;
        this.line = line;
        this.trainNumber = trainNumber;
        this.destination = destination;
        this.track = track;
        this.delay = delay;
    }

    public LocalTime getDeparture_time() {
        return this.departure_time;
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

    public LocalTime getDelay() {
        return this.delay;
    }

   
    


}
