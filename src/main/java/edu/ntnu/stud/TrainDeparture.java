package edu.ntnu.stud;

public class TrainDeparture {
    private int departure_time;
    private String line;
    private int trainNumber;
    private String destination;
    private int track;
    private int delay;

    public TrainDeparture(int departure_time, String line, int trainNumber, String destination, int track, int delay) {
        this.departure_time = departure_time;
        this.line = line;
        this.trainNumber = trainNumber;
        this.destination = destination;
        this.track = track;
        this.delay = delay;
    }


}
