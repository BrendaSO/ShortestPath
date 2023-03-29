package Algorithm.models;

import Algorithm.TimeConv;

import java.util.Date;

public class Station
{
    private int id2;
    private String company;
    private String line;
    private TimeConv departure_time;
    private TimeConv arrival_time;
    private String start_stop;
    private String end_stop;
    private double start_stop_lat;
    private double start_stop_lon;
    private double end_stop_lat;
    private double end_stop_lon;

    public Station(int id2, String company, String line, TimeConv departure_time, TimeConv arrival_time, String start_stop, String end_stop, double start_stop_lat, double start_stop_lon, double end_stop_lat, double end_stop_lon) {
        this.id2 = id2;
        this.company = company;
        this.line = line;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
        this.start_stop = start_stop;
        this.end_stop = end_stop;
        this.start_stop_lat = start_stop_lat;
        this.start_stop_lon = start_stop_lon;
        this.end_stop_lat = end_stop_lat;
        this.end_stop_lon = end_stop_lon;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public TimeConv getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(TimeConv departure_time) {
        this.departure_time = departure_time;
    }

    public TimeConv getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(TimeConv arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getStart_stop() {
        return start_stop;
    }

    public void setStart_stop(String start_stop) {
        this.start_stop = start_stop;
    }

    public String getEnd_stop() {
        return end_stop;
    }

    public void setEnd_stop(String end_stop) {
        this.end_stop = end_stop;
    }

    public double getStart_stop_lat() {
        return start_stop_lat;
    }

    public void setStart_stop_lat(double start_stop_lat) {
        this.start_stop_lat = start_stop_lat;
    }

    public double getStart_stop_lon() {
        return start_stop_lon;
    }

    public void setStart_stop_lon(double start_stop_lon) {
        this.start_stop_lon = start_stop_lon;
    }

    public double getEnd_stop_lat() {
        return end_stop_lat;
    }

    public void setEnd_stop_lat(double end_stop_lat) {
        this.end_stop_lat = end_stop_lat;
    }

    public double getEnd_stop_lon() {
        return end_stop_lon;
    }

    public void setEnd_stop_lon(double end_stop_lon) {
        this.end_stop_lon = end_stop_lon;
    }
}
