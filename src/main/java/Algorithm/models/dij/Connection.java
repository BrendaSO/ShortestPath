package Algorithm.models.dij;

import Algorithm.TimeConv;

import java.util.Objects;

public class Connection
{
    public Connection(Vertex start_stop, Vertex end_stop, String line, TimeConv departure_time, TimeConv arrival_time)
    {
        this.start_stop = start_stop;
        this.end_stop = end_stop;
        this.line = line;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connection that = (Connection) o;
        return Objects.equals(end_stop, that.end_stop) && Objects.equals(departure_time, that.departure_time) && Objects.equals(arrival_time, that.arrival_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(end_stop, departure_time, arrival_time);
    }

    public Vertex start_stop;
    public Vertex end_stop;
    public String line;
    public TimeConv departure_time;
    public TimeConv arrival_time;
}
