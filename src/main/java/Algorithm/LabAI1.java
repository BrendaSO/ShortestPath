package Algorithm;

import Algorithm.models.dij.Connection;
import Algorithm.models.dij.Graph;
import Algorithm.models.Station;
import Algorithm.models.dij.Vertex;
import Algorithm.models.tabu.GraphForTabu;
import Algorithm.models.tabu.TabuSearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LabAI1
{
    static List<Station> stationList = new LinkedList<>();
    String A, B;
    double latA, latB, lonA, lonB;
    int hour, min;
    public static void main(String []args)
    {
        LabAI1 LAI1 = new LabAI1();
        LAI1.readCSV();
        LAI1.inputVar();
        LAI1.calculatePathDij();
        LAI1.tabuSearch();
    }

    public static List<Station> readCSV() {

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/Algorithm/connection_graph.csv"));)
        {
            br.readLine();
            String line = br.readLine();

            while (line != null) {

                String[] attributes = line.split(",");
                String[] time1 = attributes[4].split(":");
                String[] time2 = attributes[5].split(":");

                Station station = new Station(
                        Integer.parseInt(attributes[1]),
                        attributes[2],
                        attributes[3],
                        new TimeConv(Integer.parseInt(time1[0]), Integer.parseInt(time1[1]), Integer.parseInt(time1[2])),
                        new TimeConv(Integer.parseInt(time2[0]), Integer.parseInt(time2[1]), Integer.parseInt(time2[2])),
                        attributes[6],
                        attributes[7],
                        Double.parseDouble(attributes[8]),
                        Double.parseDouble(attributes[9]),
                        Double.parseDouble(attributes[10]),
                        Double.parseDouble(attributes[11])
                );
                stationList.add(station);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return stationList;
    }
    public void printCSV()
    {
        for (Station id : stationList) {
            Station s = stationList.get(Integer.parseInt(String.valueOf(id)));
            System.out.println("ID: " + id);
            System.out.println("Company: " + s.getCompany());
            System.out.println("Line: " + s.getLine());
            System.out.println("Departure Time: " + s.getDeparture_time());
            System.out.println();
        }

    }

    private void inputVar()
    {
        //System.out.println("\nChoose method of solving: ");
        //Scanner sc = new Scanner(System.in);
        //
        System.out.println("Enter the starting stop (A): ");
        A = "Tramwajowa";
        //A = sc.nextLine();
        System.out.println("Enter the starting latitude (A): ");
        latA = 51.10429053;
        //latA = Double.parseDouble(sc.nextLine());
        System.out.println("Enter the starting longitude (A): ");
        lonA = 17.08387717;
        //lonA = Double.parseDouble(sc.nextLine());
        //
        System.out.println("Enter the ending stop (B): ");
        B = "GALERIA DOMINIKAŃSKA";
        //B = sc.nextLine();
        System.out.println("Enter the starting latitude (B): ");
        latB = 51.10852894;
        //latB = Double.parseDouble(sc.nextLine());
        System.out.println("Enter the starting longitude (B): ");
        lonB = 17.03870108;
        //lonB = Double.parseDouble(sc.nextLine());

        System.out.println("Enter the approximate hour of start: ");
        hour = 14; //Integer.parseInt(sc.nextLine());
        System.out.println("Enter the approximate minute of start: ");
        min = 28; //Integer.parseInt(sc.nextLine());
        //add the optimization criterion: value t denotes travel time minimization, value
        //p means minimization of the number of transfers

        System.out.println("You are going from " + A + " to " + B);
    }

    private void calculatePathDij()
    {
        HashMap<Vertex, ArrayList<Connection>> nextList = new HashMap<Vertex, ArrayList<Connection>>();

        for (Station station : stationList) {

            Vertex start = new Vertex(station.getStart_stop(), station.getStart_stop_lat(), station.getStart_stop_lon());
            Vertex end = new Vertex(station.getEnd_stop(), station.getEnd_stop_lat(), station.getEnd_stop_lon());

            if (!nextList.containsKey(start))
                nextList.put(start, new ArrayList<>());
            if (!nextList.containsKey(end))
                nextList.put(end, new ArrayList<>());

            Connection connection = new Connection(start, end, station.getLine(), station.getDeparture_time(), station.getArrival_time());
            nextList.get(start).add(connection);
        }
        Graph graph = new Graph(nextList);

        graph.shortestTimePathDijkstra(new Vertex(A, latA,lonA), new Vertex(B, latB,lonB), new TimeConv(hour,min,0));
    }

    private void tabuSearch()
    {
        HashMap<Vertex, ArrayList<Connection>> nextList = new HashMap<Vertex, ArrayList<Connection>>();
        for (Station station : stationList) {

            Vertex start = new Vertex(station.getStart_stop(), station.getStart_stop_lat(), station.getStart_stop_lon());
            Vertex end = new Vertex(station.getEnd_stop(), station.getEnd_stop_lat(), station.getEnd_stop_lon());

            if (!nextList.containsKey(start))
                nextList.put(start, new ArrayList<>());
            if (!nextList.containsKey(end))
                nextList.put(end, new ArrayList<>());

            Connection connection = new Connection(start, end, station.getLine(), station.getDeparture_time(), station.getArrival_time());
            nextList.get(start).add(connection);
        }
        GraphForTabu graph = new GraphForTabu(nextList);
        ArrayList<Vertex> milestones = new ArrayList<>();
        milestones.add(new Vertex("Rondo",  51.09069023, 17.01712401));
        milestones.add(new Vertex("Opera",  51.10551571, 17.03206374));
        milestones.add(new Vertex("DWORZEC NADODRZE",  51.12484091, 17.03481634));
        milestones.add(new Vertex("Nowowiejska",  51.12391731, 17.0441528));
        milestones.add(new Vertex("pl. Bema",  51.11708725, 17.04326797));
        milestones.add(new Vertex("Chopina",  51.11673691, 17.08162462));

        TabuSearch tabuSearch = new TabuSearch(graph, milestones, new TimeConv(hour,min,0));
        tabuSearch.iterate(5);
    }

}
