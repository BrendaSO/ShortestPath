package Algorithm.models.dij;

import java.util.Objects;

public class Vertex
{
    public Vertex(String station, double lat, double lon)
    {
        this.station = station;
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vertex vertex = (Vertex) obj;
        return Objects.equals(station, vertex.station);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(station);
    }

    @Override
    public String toString()
    {
        return station;
    }

    public String station;
    public double lat;
    public double lon;


}
